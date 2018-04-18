package com.ymkj.cms.biz.service.apply;

import java.util.Map;

import com.ymkj.base.core.common.http.HttpResponse;

public interface ILoanTrialService {

	/**
	 * <p>Description:从核心获取贷前试算接口数据</p>
	 * @uthor YM10159
	 * @date 2017年3月8日 下午2:49:39
	 */
	public HttpResponse createLoanTrial(Map<String,Object> paramsMap);
}
