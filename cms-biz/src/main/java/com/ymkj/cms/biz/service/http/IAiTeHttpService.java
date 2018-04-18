package com.ymkj.cms.biz.service.http;

import java.util.Map;

import com.alibaba.dubbo.common.json.JSONObject;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;

public interface IAiTeHttpService {
	/**
	 * 标的推送
	 * @param httpParamMap
	 * @return
	 */
	public HttpResponse targetPushed(Map<String,String> httpParamMap);

	/**
	 * 终止借款
	 * @param requestMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午8:17:09
	 */
	public HttpResponse terminationLoan(Map<String, String> httpParamMap);
	
	/**
	 * 上海资信获取
	 * @param rquestMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月20日上午11:06:09
	 */
	public HttpResponse getCreditStandingAndRespectabilitySH(Map<String, String> httpParamMap);
	
	/**
	 * 人身份证信息获取
	 * @param rquestMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月20日下午1:44:15
	 */
	public HttpResponse getIDCard(Map<String, String> httpParamMap);

	/**
	 * 文件上传
	 * @param httpParamMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月21日上午9:44:15
	 */
	public HttpResponse uploadFile(Map<String, String> httpParamMap);
	
	/**
	 * 文件删除
	 * @param map
	 * @author lix YM10160
	 * @date 2017年4月1日下午5:25:03
	 */
	public HttpResponse deleteFile(Map<String, String> httpParamMap);
	
	/**
	 * 还款计划查询，核心
	 * @param map
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月18日下午4:26:18
	 */
	public HttpResponse queryRepaymentDetail(Map<String, String> httpParamMap);
	
	/**
	 * 调用合同确认通知捞财宝
	 * @param requestMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月20日上午10:21:31
	 */
	public HttpResponse contractConfirmationToAiTe(Map<String, String> httpParamMap);
	
	/**
	 * 爱特标的降额接口
	 * @param requestMap
	 * @return
	 * @author lix YM10160
	 * @date 2017年9月5日上午11:25:35
	 */
	public HttpResponse targetDerating(Map<String, String> requestMap);
}
