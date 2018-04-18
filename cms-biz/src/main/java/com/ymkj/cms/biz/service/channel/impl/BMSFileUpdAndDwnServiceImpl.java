package com.ymkj.cms.biz.service.channel.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumBH2Constants;
import com.ymkj.cms.biz.common.util.JschSftpUtils;
import com.ymkj.cms.biz.dao.channel.IBMSAppFormDao;
import com.ymkj.cms.biz.entity.channel.RequestFileOperateRecord;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.channel.BMSFileUpdAndDwnService;
import com.zendaimoney.esignature.websvc.DealProcessorService;
import com.zendaimoney.esignature.websvc.req.EsignVo;
import com.zendaimoney.esignature.websvc.req.Request;
import com.zendaimoney.esignature.websvc.req.SealInfoVo;
import com.zendaimoney.esignature.websvc.req.SealVo;
import com.zendaimoney.esignature.websvc.resp.Response;

/**
 * @author YM10189
 * @date 2017年5月23日
 * @Description:文件上传下载
 */
@Service
public class BMSFileUpdAndDwnServiceImpl implements BMSFileUpdAndDwnService {
	@Autowired
	private DealProcessorService dealProcessorWsService;

	@Autowired
	private IBMSAppFormDao bmsAppFormDao;

	private FTPClient ftpClient;

	public static final int BINARY_FILE_TYPE = FTP.BINARY_FILE_TYPE;

	public static final int ASCII_FILE_TYPE = FTP.ASCII_FILE_TYPE;

	// 传输超时时间
	public static int dateTimes = 1200000;
	// 连接超时时间
	public static int connectTimes = 20000;

	public void connectServer(String host, String port, String user, String password, String remotePath) throws SocketException, IOException {
		connectServer(host, Integer.parseInt(port), user, password, remotePath);
	}

	public void connectServer(String server, int port, String user, String password, String path) throws SocketException, IOException {
		ftpClient = new FTPClient();
		ftpClient.connect(server, port);
		System.out.println("Connected to " + server + ".");
		System.out.println(ftpClient.getReplyCode());
		ftpClient.login(user, password);
		// 设置被传输文件的编码类型
		ftpClient.setFileType(BINARY_FILE_TYPE);
		// 设置输出文件的传输模式
		ftpClient.setFileTransferMode(BINARY_FILE_TYPE);
		if (path.length() != 0) {
			ftpClient.changeWorkingDirectory(path);
		}
	}

	public void initFtpClient(String host, String port, String user, String password, String remotePath) throws SocketException, IOException {
		connectServer(host, port, user, password, remotePath);
	}

	public String isExistEsignatureFtpFile(String dirName, String fileName) throws Exception {
		if (!changeDirectory(dirName)) {
			return null;
		}
		if (!existFile(fileName)) {
			return null;
		}
		return dirName + "/" + fileName;
	}

	public boolean changeDirectory(String path) throws IOException {
		return ftpClient.changeWorkingDirectory(path);
	}

	public boolean existFile(String path) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		for (FTPFile ftpFile : ftpFileArr) {
			if (ftpFile.isFile() && ftpFile.getName().equalsIgnoreCase(path)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean download(String remoteFileName, OutputStream out) throws IOException {
		boolean flag = false;
		try {
			flag = ftpClient.retrieveFile(remoteFileName, out);
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {

		}
		return flag;
	}

	public ByteArrayOutputStream downloadEsignatureFileByFilePath(String remoteFileName) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		download(remoteFileName, out);
		return out;
	}

	public String uploadEsignatureFile(InputStream in, String filePath, String fileName, String projectCode) throws IOException {
		// 切换到要上传的目录
		changeDirectory(filePath);
		if (!isChildDirectory(filePath, projectCode)) {
			if (!createDirectory(filePath + "/" + projectCode)) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "上传目录不存在！");
			}
		}
		changeDirectory(filePath + "/" + projectCode);
		boolean uploadStatus = uploadFile(in, fileName);
		if (uploadStatus) {
			return "http://172.16.250.69:8080" + "/qianzhang/upload" + "/" + projectCode;
		}
		return "";
	}

	public boolean isChildDirectory(String parentPath, String tarPath) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(parentPath);
		for (FTPFile ftpFile : ftpFileArr) {
			System.out.println("是否目录：" + ftpFile.isDirectory() + ":目录:" + ftpFile.getName());
			if (ftpFile.isDirectory() && ftpFile.getName().equalsIgnoreCase(tarPath)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean createDirectory(String pathName) throws IOException {
		return ftpClient.makeDirectory(pathName);
	}

	public boolean uploadFile(String fileName, String newName) throws IOException {
		boolean flag = false;
		InputStream iStream = null;
		try {
			iStream = new FileInputStream(fileName);
			flag = ftpClient.storeFile(newName, iStream);
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (iStream != null) {
				iStream.close();
			}
		}
		return flag;
	}

	public boolean uploadFile(String fileName) throws IOException {
		return uploadFile(fileName, fileName);
	}

	public boolean uploadFile(InputStream iStream, String newName) throws IOException {
		boolean flag = false;
		try {
			flag = ftpClient.storeFile(newName, iStream);
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (iStream != null) {
				iStream.close();
			}
		}
		return flag;
	}

	public String dealEsignature(String httpUrl, String fileName) {
		// 定义盖章实体
		EsignVo body = new EsignVo();
		List<SealVo> sealFiles = new ArrayList<SealVo>();
		SealVo sealVo = new SealVo();
		sealVo.setFileNo(fileName);
		sealVo.setFilePath(httpUrl + "/" + fileName);
		sealVo.setIsSecondSign("1");
		List<SealInfoVo> sealInfos = new ArrayList<SealInfoVo>();// 一个文件中请求盖章的列表
		SealInfoVo sealInfoVo1 = new SealInfoVo();
		sealInfoVo1.setSealType("0");
		sealInfoVo1.setName("上海证大投资咨询有限公司业务公章");
		sealInfos.add(sealInfoVo1);
		sealVo.setSealInfos(sealInfos);
		sealFiles.add(sealVo);
		body.setSealFiles(sealFiles);
		// 组装盖章参数
		String reqId = UUID.randomUUID().toString().substring(0, 8) + "020001";
		Request request = new Request();
		request.setSysId("credit");
		request.setReqId(reqId);
		request.setReqCode("020001");
		request.setBody(body);
		// 调用盖章接口
		try {
			Response response = dealProcessorWsService.dispatchCommand(request);
			if (null == response || !"0000".equals(response.getCode())) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "签章失败!");
			}
			List<SealVo> list = response.getSealFiles();
			if (list == null || list.size() <= 0) {
				throw new BizException(CoreErrorCode.SYSTEM_ERROR, "签章失败!");
			}
			for (SealVo vo : list) {
				return vo.getFilePath();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, "签章失败！");
		}
		return null;
	}

	public ByteArrayOutputStream downloadEsignatureFileByHttpUrl(String httpUrl) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
		try {
			InputStream inputStream = URLGet(httpUrl);
			byte[] bytes = new byte[1024];
			int i = 0;
			while ((i = inputStream.read(bytes)) != -1) {
				byteArrayOutputStream.write(bytes, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

	public static InputStream URLGet(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setUseCaches(false);
		con.setConnectTimeout(10000);
		con.setReadTimeout(20000);
		HttpURLConnection.setFollowRedirects(true);
		return con.getInputStream();
	}

	public boolean uploadFtpBHXT(JschSftpUtils jschSftpUtils, Map<String, InputStream> inputStreamMap, String projectCode) {
		Map<String, String> fileMap = new HashMap<String, String>();
		boolean delSatus = false;
		for (Map.Entry<String, InputStream> entry : inputStreamMap.entrySet()) {
			String fileName = entry.getKey();
			String pathName = this.getUploadBhFtpPatch(fileName, projectCode);
			if (pathName == null) {
				delSatus = true;
				break;
			}
			jschSftpUtils.changeDir(pathName);
			boolean status = jschSftpUtils.uploadFile(pathName, fileName, entry.getValue());
			fileMap.put(fileName, pathName);
			if (!status) {
				delSatus = true;
				break;
			}
		}
		if (delSatus) {
			for (Map.Entry<String, String> entry : fileMap.entrySet()) {
				if (jschSftpUtils.changeDir(entry.getValue())) {
					jschSftpUtils.delFile(entry.getKey());
				}
			}
			return false;
		}
		for (Map.Entry<String, String> entry : fileMap.entrySet()) {
			if (jschSftpUtils.changeDir(entry.getValue())) {
				uploadFlagFile("flag.ok", jschSftpUtils, entry.getValue());
			}
		}
		return true;
	}

	private String getUploadBhFtpPatch(String fileName, String projectCode) {
		if (EnumBH2Constants.渤海项目简码.getCode().equals(projectCode)) {
			if (fileName.indexOf(EnumBH2Constants.划拨申请书pdf.getName()) != -1) {
				return "/ZD01/receive/10";
			} else if (fileName.indexOf(EnumBH2Constants.放款申请书xls.getName()) != -1) {
				return "/ZD01/receive/13";
			}
		}
		return "";
	}

	public void uploadFlagFile(String fileName, JschSftpUtils jschSftpUtils, String pathName) {
		FileInputStream fileInputStream = null;
		try {
			String classpath = this.getClass().getClassLoader().getResource("").getPath();
			File file = new File(classpath + "//" + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileInputStream = new FileInputStream(file);
			if (jschSftpUtils.changeDir(pathName)) {
				boolean uploadStatus = jschSftpUtils.uploadFile(pathName, fileName, fileInputStream);
				if (uploadStatus) {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JschSftpUtils getFtpBhxtConnectJsch() {
		JschSftpUtils jschSftpUtils = new JschSftpUtils("114.251.243.97", 20000, 300000, "zdtest", "91889Pcy");
		return jschSftpUtils;
	}

	public long createRequestManagerOperateRecord(String batchNum, String fileType, String operateType, String fileBatchNum) {
		String fileSeq = leftExcludeChar(fileBatchNum.substring(9), '0');
		RequestFileOperateRecord record = new RequestFileOperateRecord();
		record.setBatchNum(batchNum);
		record.setFileType("01");
		record.setOperateType(operateType);
		// 如果已上传过则更新上传次数times,否则新增上传记录
		List<RequestFileOperateRecord> recordList = bmsAppFormDao.findRequestFileOperateRecord(record);
		if (recordList == null || recordList.size() <= 0) {
			record.setOperateDate(new Date());
			record.setTimes(1);
			record.setFileSeq(Integer.parseInt(fileSeq));
			long backNum=bmsAppFormDao.insertRequestFileOpr(record);
			return backNum;
		}
		record = recordList.get(0);
		record.setTimes(record.getTimes() + 1);
		long backNum=bmsAppFormDao.updateRequestFileOpr(record);
		return backNum;
	}

	public static String leftExcludeChar(String str, char ch) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		int len = str.length();// 取得字符串的长度
		int index = 0;// 预定义第一个非零字符串的位置

		char strs[] = str.toCharArray();// 将字符串转化成字符数组
		for (int i = 0; i < len; i++) {
			if (ch != strs[i]) {
				index = i;// 找到非零字符串并跳出
				break;
			}
		}
		String strLast = str.substring(index, len);// 截取字符串
		return strLast;
	}
}
