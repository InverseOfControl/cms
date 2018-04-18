package com.ymkj.cms.biz.service.http.impl;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.ILuJSHttpService;
import com.ymkj.cms.biz.service.sign.ILoanSignDataOprateService;

import org.apache.commons.lang.ObjectUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class LuJSHttpServiceImpl implements ILuJSHttpService {

	// 陆金所网关地址
	@Value("#{env['requestByUrl']?:''}")
	private String lufaxGateURL;
	
	@Value("#{env['requestByKey']?:''}")
	private String sxKey;

	@Autowired
	private ILoanSignDataOprateService loanSignDataOprateService;

	@Override
	public HttpResponse lufaxGate(Map<String, Object> httpParamMap) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("funcId", httpParamMap.get("funcId"));
		strMap.put("params", httpParamMap.get("params"));
		strMap.put("key", httpParamMap.get("key"));
		strMap.put("sign", httpParamMap.get("sign"));
		
		String urlParam= JsonUtils.encode(strMap);
		try {
			// 调用放款推送接口，得到返回的信息
			BmsLogger.info("调用陆金所接口,传送url：[{}]" + lufaxGateURL);
			BmsLogger.info("调用陆金所接口,传送数据：[{}]" + urlParam);
			HttpResponse httpResponse = HttpUtil.post(lufaxGateURL, urlParam, false);
			//解码
			httpResponse.setContent(URLDecoder.decode(httpResponse.getContent(),"UTF-8"));
			BmsLogger.info("调用陆金所接口,返回json：[{}]" + JsonUtils.encode(httpResponse));
			return httpResponse;
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			throw new BizException(CoreErrorCode.FACADE_ERROR, "调用核心陆金所合同确认接口");
		}

	}
	

	@Override
	public HttpResponse lufaxGate(JSONObject obj) {
/*		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("funcId", httpParamMap.get("funcId"));
		strMap.put("params", httpParamMap.get("params"));
		strMap.put("key", httpParamMap.get("key"));
		strMap.put("sign", httpParamMap.get("sign"));*/
		
		//String urlParam= JsonUtils.encode(strMap);
		try {
			// 调用放款推送接口，得到返回的信息
			BmsLogger.info("调用陆金所接口,传送url：[{}]" + lufaxGateURL);
			BmsLogger.info("调用陆金所接口,传送数据：[{}]" + ObjectUtils.toString(obj));
			HttpResponse httpResponse = HttpUtil.post(lufaxGateURL, ObjectUtils.toString(obj), true);
			//解码
			httpResponse.setContent(URLDecoder.decode(httpResponse.getContent(),"UTF-8"));
			BmsLogger.info("调用陆金所接口,返回json：[{}]" + JsonUtils.encode(httpResponse));
			return httpResponse;
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			throw new BizException(CoreErrorCode.FACADE_ERROR, "调用核心陆金所合同确认接口");
		}

	}

	@Override
	public HttpResponse lufaxPreGataway(JSONObject obj) {
		try {
			HttpResponse httpResponse = HttpUtil.post(lufaxGateURL, ObjectUtils.toString(obj), true);
			
			httpResponse.setContent(URLDecoder.decode(httpResponse.getContent(),"UTF-8"));
			BmsLogger.info("调用陆金所前置网关接口,返回结果：" + httpResponse.getContent());
			return httpResponse;
		} catch (Exception e) {
			BmsLogger.info(e.getMessage());
			throw new BizException(CoreErrorCode.FACADE_ERROR, "调用陆金所前置网关接口异常");
		}
	}
}
