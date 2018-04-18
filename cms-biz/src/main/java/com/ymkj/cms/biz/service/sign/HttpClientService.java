package com.ymkj.cms.biz.service.sign;

import java.util.Map;

/**
 * @author YM10189
 * http-client
 */
public interface HttpClientService {
	/**
	 * 电子签章接口调用
	 * @param url
	 * @param param
	 */
	public Map<String,Object> signContract(String url,String param);
	
	public Map<String, Object> querySign(String url, String param);
	
	public Map<String,Object> uploadContract(Map<String,String> paramMap);
	
	public Map<String,Object>delContract(Map<String,String> paramMap,String foldName);
	
}
