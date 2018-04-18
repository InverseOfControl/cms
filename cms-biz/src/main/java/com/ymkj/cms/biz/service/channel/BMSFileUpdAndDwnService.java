package com.ymkj.cms.biz.service.channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Map;

import com.ymkj.cms.biz.common.util.JschSftpUtils;

public interface BMSFileUpdAndDwnService {
	
	public void initFtpClient(String host, String port, String user, String password, String remotePath) throws SocketException, IOException;
	
	public String isExistEsignatureFtpFile(String dirName, String fileName) throws Exception;
	
	public ByteArrayOutputStream downloadEsignatureFileByFilePath(String remoteFileName) throws IOException;
	
	public String uploadEsignatureFile(InputStream in, String filePath, String fileName, String projectCode) throws IOException;
	
	public String dealEsignature(String httpUrl, String fileName);
	
	public ByteArrayOutputStream downloadEsignatureFileByHttpUrl(String httpUrl);
	
	public boolean uploadFtpBHXT(JschSftpUtils jschSftpUtils, Map<String, InputStream> inputStreamMap, String projectCode);
	
	public JschSftpUtils getFtpBhxtConnectJsch();
	
	public long createRequestManagerOperateRecord(String batchNum, String fileType, String operateType, String fileBatchNum);
}
