package com.ymkj.cms.biz.service.sign.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ymkj.cms.biz.service.sign.HttpClientService;

@Service
public class HttpClientServiceImpl implements HttpClientService {
	public static Logger logger = Logger.getLogger(HttpClientServiceImpl.class);
	
	@Value("#{env['uploadPicFoldUrl']?:''}")
	private String picFoldUrl;
	
	@Value("#{env['deletePicDirFileUrl']?:''}")
	private String picDelUrl;

	// 默认请求编码
	public static final String DEFAULT_REQUEST_ENCODING = "UTF-8";

	// 请求头类型
	public static final String DEFAULT_REQUEST_CONTENT_TYPE = "application/json";

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> signContract(String url, String param) {
		Gson gson = new GsonBuilder().create();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PostMethod postMethod = null;
		try {
			JSONObject jsonObj = new JSONObject(param);
			HttpClient httpClient = new HttpClient();
			postMethod = new PostMethod(url);
			RequestEntity requestEntity = new StringRequestEntity(jsonObj.toString(), DEFAULT_REQUEST_CONTENT_TYPE,DEFAULT_REQUEST_ENCODING);
			postMethod.setRequestEntity(requestEntity);
			httpClient.executeMethod(postMethod);
			String reaponse = postMethod.getResponseBodyAsString();
			logger.info("签章接口返回:"+reaponse);
			resultMap = gson.fromJson(reaponse, Map.class);
		} catch (FileNotFoundException e) {
			logger.error("签章合同不存在", e);
			return null;
		} catch (Exception e) {
			logger.error("电子签章接口调用异常", e);
			return null;
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> querySign(String url, String appNo) {
		Gson gson = new GsonBuilder().create();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PostMethod postMethod = null;
		try {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("appNo", appNo);
			HttpClient httpClient = new HttpClient();
			postMethod = new PostMethod(url);
			RequestEntity requestEntity = new StringRequestEntity(
					jsonObj.toString(), DEFAULT_REQUEST_CONTENT_TYPE,
					DEFAULT_REQUEST_ENCODING);
			postMethod.setRequestEntity(requestEntity);
			httpClient.executeMethod(postMethod);
			String reaponse = postMethod.getResponseBodyAsString();
			logger.info("签名接口返回:"+reaponse);
			resultMap = gson.fromJson(reaponse, Map.class);
		} catch (Exception e) {
			logger.error("电子签名接口调用异常", e);
			return null;
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> uploadContract(Map<String, String> paramMap) {
		String resultCode="";
		Map<String,Object> backMap=new HashMap<String,Object>();
		try {
			String filePath =paramMap.get("filePath");
			paramMap.remove("filePath");
			HttpClient httpClient = new HttpClient();
			StringBuffer urlBuf=new StringBuffer(picFoldUrl).append("?");
			Iterator<String> keys=paramMap.keySet().iterator();
			while(keys.hasNext()){
				String key=keys.next();
				urlBuf.append(key).append("=").append(URLEncoder.encode(String.valueOf(paramMap.get(key)),"utf-8")).append("&");
			}
			String url=urlBuf.toString().substring(0, urlBuf.toString().length()-1);
			PostMethod filePost = new PostMethod(url);
			File file = new File(filePath);
			FilePart filePart = new FilePart("file",file.getName(), file);
			String mimeType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
			filePart.setContentType(mimeType);
			Part[] parts =new Part[]{filePart};
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, filePost.getParams());
			filePost.setRequestEntity(mre);
//			filePost.setRequestHeader("Content-type", "multipart/form-data; charset=UTF-8");
			filePost.setRequestHeader("Content-Disposition", "form-data");  
			int status=httpClient.executeMethod(filePost);
			if(HttpStatus.SC_OK==status){
				String result=filePost.getResponseBodyAsString();
				JSONObject jsonObj = new JSONObject(result);
				if("000000".equals(jsonObj.getString("errorcode"))){
					resultCode="0000";
					JSONObject resultObj=jsonObj.getJSONObject("result");
					String fileUrl=resultObj.getString("url");
					fileUrl=fileUrl.substring(fileUrl.indexOf("/bms"),fileUrl.length());
					backMap.put("filePath",fileUrl);
				}
			}
			backMap.put("resultCode", resultCode);
		} catch (Exception e) {
			logger.error("pic文件上传接口调用异常", e);
		}
		return backMap;
	}
	
	@Override
	public Map<String, Object> delContract(Map<String, String> paramMap,String foldName) {
		String resultCode="";
		Map<String,Object> backMap=new HashMap<String,Object>();
		try {
			Map<String,Object> dataMap=new HashMap<String,Object>();
			dataMap.put("operator",paramMap.get("operator"));
			dataMap.put("jobNumber",paramMap.get("jobNumber"));
			dataMap.put("appNo",paramMap.get("appNo"));
			dataMap.put("subclassSort",foldName);
			HttpClient httpClient = new HttpClient();
			StringBuffer urlBuf=new StringBuffer(picDelUrl).append("?");
			Iterator<String> keys=dataMap.keySet().iterator();
			while(keys.hasNext()){
				String key=keys.next();
				urlBuf.append(key).append("=").append(URLEncoder.encode(String.valueOf(dataMap.get(key)),"utf-8")).append("&");
			}
			String url=urlBuf.toString().substring(0, urlBuf.toString().length()-1);
			PostMethod filePost = new PostMethod(url);
		    filePost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());  
			int status=httpClient.executeMethod(filePost);
			if(HttpStatus.SC_OK==status){
				String result=filePost.getResponseBodyAsString();
				JSONObject jsonObj = new JSONObject(result);
				if("000000".equals(jsonObj.getString("errorcode"))){
					resultCode="0000";
				}
			}
			backMap.put("resultCode", resultCode);
		} catch (Exception e) {
			logger.error("pic合同文件删除接口调用异常", e);
		}
		return backMap;
	}
	
	
}
