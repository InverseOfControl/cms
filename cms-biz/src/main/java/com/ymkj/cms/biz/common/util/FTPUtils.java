package com.ymkj.cms.biz.common.util;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
/**
 * FTP工具类，实现FTP登陆，浏览，文件下载，上传等功能
 * 
 * @author fudw
 * 
 */
@Controller
@Transactional
public class FTPUtils {

	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public FTPClient connect(String ip,int port, String username, String password) {
        try {
            FTPClient ftp = new FTPHTTPClient(ip, port, username, password);
            ftp = new FTPClient();
            int reply;
            ftp.connect(ip);
            logger.info("连接到：" + ip + ":" + port);
            logger.info(ftp.getReplyString());
            reply = ftp.getReplyCode();
 
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.info("FTP目标服务器积极拒绝.");
                System.exit(1);
                return null;
            }else{
                ftp.login(username, password);
                logger.info("登录ftp服务器成功.");
                ftp.enterLocalPassiveMode();
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftp.setBufferSize(100000);
                logger.info("已连接：" + ip + ":" + port);
                return ftp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
            return null;
        }
    }
	public boolean createDir(FTPClient ftp,String dirname) {
		boolean success=false;
	    try{
	        if(ftp.makeDirectory(dirname))
	        {
	        	logger.info("在目标服务器上成功建立了文件夹: " + dirname);
	        	success=true;
	        	return success;
	        }else
	        {
	        	return success;
	        }
	        
	    }catch(Exception ex){
	    	logger.info(ex.getMessage());
	    	return success;
	    }
		
	}
	public  boolean uploadFile(FTPClient ftp,String path, String filename, InputStream input) { 
	    boolean success = false; 
	    try { 
//	        int reply; 
	        ftp.changeWorkingDirectory(path); 
	        ftp.storeFile(filename, input);          
	        input.close(); 
	        success = true; 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    }
	    return success; 
	}
	public boolean close(FTPClient ftp)
	{
		boolean success = false; 
		try {
			ftp.logout();
			success=true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	                logger.info("关闭ftp连接: "+success);
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
		return success;
		
	}
	public String getUrlInfo(String url){
		String result = "";   
	     try {   
	         URL urlCon = new URL(url);   
	         java.net.HttpURLConnection conn = (java.net.HttpURLConnection)urlCon.openConnection();  
	         conn.setDoOutput(true);  
	         conn.setRequestMethod("POST");  
	         java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));  
	         String line;  
	         while ((line = in.readLine()) != null) {  
	        	 result += line;  
	         }  
	         in.close();  
	     } catch (Exception e) {  
	       logger.info("error in wapaction,and e is " + e.getMessage());  
	     }  
	  return  result;
	}
	public  BufferedInputStream getPic(String destUrl) {  
		 BufferedInputStream bis = null;  
		 HttpURLConnection httpUrl = null;  
		 URL url = null;  
		 try {  
		 url = new URL(destUrl);  
		 httpUrl = (HttpURLConnection) url.openConnection();  
		 httpUrl.connect();  
		 bis = new BufferedInputStream(httpUrl.getInputStream()); 
		 return bis;
		     } catch (IOException e) {  
		     } catch (ClassCastException e) {  
		     }catch (NullPointerException e) {  
		       }  
		 return bis;
	} 
	
	public boolean createDirs(FTPClient ftp,String dirname) {
		boolean success=false;
		try{
			String[]files=dirname.split("/");
			for (int i = 0; i < files.length; i++) {
				String foldName=files[i];
				ftp.makeDirectory(foldName);
				success=ftp.changeWorkingDirectory(foldName);
				if(!success){
					break;
				}
			}
		}catch(Exception ex){
			logger.info(ex.getMessage());
			return success;
		}
		return success;
}
}

