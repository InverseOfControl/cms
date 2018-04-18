package com.ymkj.cms.biz.service.ftp.imlp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.util.FTPUtils;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.audit.BMSAPPHistoryDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.audit.BMSAPPHistoryEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.ftp.IAiTeFtpService;

@Service
public class AiTeFtpServiceImpl implements IAiTeFtpService {
	@Autowired
	private FTPUtils fTPUtils;

	@Autowired
	private BMSAPPHistoryDao appHistoryDaoImpl;
	//央行征信地址
	@Value("#{env['peopleBankCreditURL']?:''}")
	private String peopleBankCreditURL;
	//爱特服务器地址
	@Value("#{env['serverIp']?:''}")
	private String serverIp;
	//爱特服务器端口
	@Value("#{env['serverPort']?:''}")
	private String serverPort;
	//爱特服务器访问用户名
	@Value("#{env['serverUsername']?:''}")
	private String serverUsername;
	//爱特服务器访问用户密码
	@Value("#{env['serverPassword']?:''}")
	private String serverPassword;

	@Override
	public String getPeopleBankCredit(APPPersonInfoEntity tmAppPersonInfo) {
		// 数据查询
		Map<String, Object> params = new HashMap<String, Object>();
		String url = "";

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String name = tmAppPersonInfo.getName();
		String idNum = tmAppPersonInfo.getIdNo();
		try {
			name = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(tmAppPersonInfo.getAuditEndTime() == null){
			throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, "审核结束时间（首次录入复核时间）不存在");
		}
		url = peopleBankCreditURL + "?" + "customerName=" + name + "&customerIdCard=" + idNum + "&queryDate="
				+ df.format(tmAppPersonInfo.getAuditEndTime()) + "&reportId=" + tmAppPersonInfo.getReportId()+"&sources="+EnumConstants.BMS_SYSCODE;
		
		//	   http://172.16.230.37:8080/creditzx-web/pbccrc/reportView?customerName=%E5%BC%80%E5%8F%91%E4%BA%BA%E5%91%98&customerIdCard=420222199207118339&queryDate=2016-06-07&reportId=100320&sources=bms		// 目前写死
//		url = "http://172.16.230.37:8080/creditzx-web/pbccrc/reportView?customerIdCard=4306211990006142525&customerName=Sam&queryDate=2016-06-07&reportId=100320&sources=bms";
		// 文件获取
		return fTPUtils.getUrlInfo(url);
	}

	@Override
	public boolean creditInvestigationUploadFile(String creditInvestigationStream,
			APPPersonInfoEntity tmAppPersonInfo) {
		boolean notifyFlag = false;

		String appNo = tmAppPersonInfo.getLoanNo();

		FTPClient ftp = fTPUtils.connect(serverIp, Integer.parseInt(serverPort), serverUsername, serverPassword);
		// 创建对应的文件夹 RH
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String mouth = String.valueOf(cal.get(Calendar.MONTH) + 1);
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		fTPUtils.createDir(ftp, year + "/");
		fTPUtils.createDir(ftp, year + "/" + mouth + "/");
		fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/");
		fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/" + appNo + "/");
		fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/" + appNo + "/" + "RH" + "/");
		ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(creditInvestigationStream.getBytes());
		BufferedInputStream inputStream = new BufferedInputStream(tInputStringStream);
		String RH = year + "/" + mouth + "/" + day + "/" + appNo + "/" + "RH/";
		// 开始上传央行报告
		System.err.println("上传央行报告路径:" + RH);
		long currentTime = System.currentTimeMillis();
		System.err.println("上传央行报告前的时间：[{}]" + currentTime);
		notifyFlag = fTPUtils.uploadFile(ftp, RH, tmAppPersonInfo.getIdNo() + ".html", inputStream);
		fTPUtils.close(ftp);
		System.err.println("上传央行报告后的时间：[{}]" + System.currentTimeMillis());
		System.err.println("上传央行报告用时：[{}]" + (System.currentTimeMillis() - currentTime));

		return notifyFlag;
	}

	@Override
	public boolean creditReportUploadFile(String sinputStreamString, APPPersonInfoEntity tmAppPersonInfo) {
		boolean notifyFlag = false;
		ByteArrayInputStream sInputStringStream = null;
		BufferedInputStream sinputStream = null;
		try {
			sInputStringStream = new ByteArrayInputStream(sinputStreamString.getBytes());
			sinputStream = new BufferedInputStream(sInputStringStream);
			FTPClient ftp = fTPUtils.connect(serverIp, Integer.parseInt(serverPort), serverUsername, serverPassword);
	
			String appNo = tmAppPersonInfo.getLoanNo();
			// 创建对应的文件夹 SH
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String mouth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			fTPUtils.createDir(ftp, year + "/");
			fTPUtils.createDir(ftp, year + "/" + mouth + "/");
			fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/");
			fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/" + appNo + "/");
			fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/" + appNo + "/" + "SH" + "/");
			// 开始上传上海资信报告
			String SH = year + "/" + mouth + "/" + day + "/" + appNo + "/" + "SH/";
			System.err.println("上传上海资信报告路径:" + SH);
			long currentTime = System.currentTimeMillis();
			System.err.println("上传上海资信报告前的时间：[{}]" + currentTime);
			notifyFlag = fTPUtils.uploadFile(ftp, SH, tmAppPersonInfo.getIdNo() + ".txt", sinputStream);
			fTPUtils.close(ftp);
			System.err.println("上传上海资信报告后的时间：[{}]" + System.currentTimeMillis());
			System.err.println("上传上海资信报告用时：[{}]" + (System.currentTimeMillis() - currentTime));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//流关闭
			if(sInputStringStream != null){
				try {
					sInputStringStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(sinputStream != null){
				try {
					sinputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return notifyFlag;
	}

	@Override
	public boolean IDCardUploadFile(String jsonHttp, APPPersonInfoEntity tmAppPersonInfo) {
		boolean notifyFlag = false;
		Map<String,Object> resMap = JsonUtils.decode(jsonHttp, Map.class);
		//接口调用成功验证
		if (Response.SUCCESS_RESPONSE_CODE.equals(resMap.get("errorcode"))) { // 查询成功
			//结果判断
			if(resMap.get("result") != null && (resMap.get("result") instanceof List)){
//				FTPClient ftp = new FTPClient();
				FTPClient ftp = fTPUtils.connect(serverIp, Integer.parseInt(serverPort), serverUsername, serverPassword);
				String appNo = tmAppPersonInfo.getLoanNo();
				// 创建对应的文件夹 SF
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String year = String.valueOf(cal.get(Calendar.YEAR));
				String mouth = String.valueOf(cal.get(Calendar.MONTH) + 1);
				String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
				fTPUtils.createDir(ftp, year + "/");
				fTPUtils.createDir(ftp, year + "/" + mouth + "/");
				fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/");
				fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/" + appNo + "/");
				fTPUtils.createDir(ftp, year + "/" + mouth + "/" + day + "/" + appNo + "/" + "SF" + "/");
				String SF = year + "/" + mouth + "/" + day + "/" + appNo + "/" + "SF/";
				
				List<Map<String,Object>> pictureList = (List<Map<String,Object>>) resMap.get("result");
				if(pictureList.isEmpty()){
					//关闭流
					fTPUtils.close(ftp);
					throw new BizException(BizErrorCode.DB_RESULT_ISNULL, "该申请件身份证明获取失败，没有对应的身份证明上传到爱特ftp服务器");
				}
				//图片信息集合处理，
				for (int i = 1; i <= pictureList.size(); i++) {
					Map<String, Object> picture = pictureList.get(i-1);
					if(picture.get("url") != null && !"".equals(picture.get("url"))){
						//缺少请求协议，
						String url = "http:"+picture.get("url").toString();
						
						System.out.println("获取第" + i + "张图片;url=" + url);
						BufferedInputStream bis = fTPUtils.getPic(url);

						// 发送身份证照片
						notifyFlag = fTPUtils.uploadFile(ftp, SF, url.substring(url.indexOf(EnumConstants.PicPaperstype.IDENTIFICATION_PAPER.getValue()+"/") + (1+EnumConstants.PicPaperstype.IDENTIFICATION_PAPER.getValue().length())), bis);
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
				fTPUtils.close(ftp);
				notifyFlag = true;
			} else {
				throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, "该申请件身份证明获取失败，没有对应的身份证明上传到爱特ftp服务器");

			}
		} else {
			throw new BizException(CoreErrorCode.DB_RESULT_ISNULL, resMap.get("crrormsg"));

		}
		
		return notifyFlag;
	}

}
