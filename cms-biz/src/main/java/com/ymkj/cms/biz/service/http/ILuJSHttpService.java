package com.ymkj.cms.biz.service.http;

import java.util.Map;

import org.json.JSONObject;

import com.ymkj.base.core.common.http.HttpResponse;

public interface ILuJSHttpService {
	
	/**
	 * 调用陆金所前置请求接口
	 * @param httpParamMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年7月11日下午3:38:43
	 */
	HttpResponse lufaxGate(Map<String, Object> httpParamMap);
	
	
	/**
	 * 调用陆金所前置请求接口
	 * @param httpParamMap
	 * @return
	 * @author lifz YM10139
	 * @date 2017年7月11日下午3:38:43
	 */
	public HttpResponse lufaxGate(JSONObject obj);
	/**
	 * @Description:陆金所文件上传</p>
	 * @uthor YM10159
	 * @date 2017年7月12日 下午7:36:34
	 * @param obj 请求参数
	 */
	HttpResponse lufaxPreGataway(JSONObject obj);
}
