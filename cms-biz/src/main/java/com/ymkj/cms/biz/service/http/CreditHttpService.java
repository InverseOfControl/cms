package com.ymkj.cms.biz.service.http;

import java.util.Map;

import org.json.JSONObject;

import com.ymkj.base.core.common.http.HttpResponse;

public interface CreditHttpService {
	
	/**
	 * 查询央行征信报告
	 * @param appDataMap
	 * @return
	 */
	public JSONObject queryBMSReport(Map<String,Object> appDataMap);
	
	/**
	 * 查询央行征信报告
	 * @param appDataMap
	 * @return
	 */
	public JSONObject queryBMSReportNotAppNo(Map<String,Object> appDataMap);
	
	/**
	 * 获取上海资信报告
	 * @param appDataMap
	 * @return
	 */
	public HttpResponse queryShangHaiCreditReport(Map<String,Object> appDataMap);
	
	
	/**
	 * 查询反欺诈信息
	 * @param appDataMap
	 * @return
	 */
	public JSONObject querySuanHuaAntifraud(Map<String,Object> appDataMap);
	
	/**
	 * 解析央行征信报告
	 * @param appDataMap
	 * @return
	 */
	public JSONObject getBadCreditInfo(Map<String,Object> appDataMap);
	
	
	/**
	 * 获取第三方接口数据
	 * @param appDataMap
	 * @return
	 */
	public JSONObject getThirdPartyInfo(Map<String,Object> appDataMap);
	
	/**
	 * 老政审  获取上笔借款数据
	 * @param appDataMap
	 * @return
	 */
	public HttpResponse queryMainByIdNo(Map<String,Object> appDataMap);
	
	/**
	 * 查询用户是否在已分派名单
	 * @param appDataMap
	 * @return
	 */
	public HttpResponse getSalesman(Map<String,Object> appDataMap);
	/**
	 * 查询用户是否在已分派名单
	 * @param appDataMap
	 * @return
	 */
	public HttpResponse getSalesman2(Map<String,Object> appDataMap);
	/**
	 * 查询是否是优质客户名单
	 * @param appDataMap
	 * @return
	 */
	public HttpResponse personValidate(Map<String,Object> appDataMap);
	
	/**
	 * 陆金所征信报告解析
	 * @param appDataMap
	 */
	public JSONObject creditReportAnalyze(Map<String,Object> appDataMap);
	
	/**
	 * 央行报告限制条件
	 * @param appDataMap
	 * @return
	 */
	public HttpResponse applyCreditReportRule(Map<String,Object> appDataMap);
	
	/**
	 * 查询老政审最近一次对应的拒绝时间和取消时间还有拒绝时间对应的限制天数 
	 * 
	 */
	public HttpResponse queryOldRefuseAndCancelAndLimitDaysData(Map<String,Object> map);
}
