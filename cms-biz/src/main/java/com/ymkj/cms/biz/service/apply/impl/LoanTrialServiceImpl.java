package com.ymkj.cms.biz.service.apply.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.base.core.common.http.HttpUtil;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.ILoanTrialService;

@Service
public class LoanTrialServiceImpl implements ILoanTrialService {

	@Value("#{env['createLoanTrial']?:''}")
	private String createLoanTrial;

	@Override
	public HttpResponse createLoanTrial(Map<String, Object> paramsMap) {
		
		HttpResponse  httpResponse = null;
		
		String urlParam= StringUtils.map2UrlParams(paramsMap);
		long startTime = System.currentTimeMillis();
		
		try {
			httpResponse = HttpUtil.post(createLoanTrial,urlParam,false);
			System.err.println("调用贷前试算接口用时："+(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			throw new BizException(CoreErrorCode.FACADE_ERROR,"调用贷前试算接口发生异常");
		}
		return httpResponse;
	}

}
