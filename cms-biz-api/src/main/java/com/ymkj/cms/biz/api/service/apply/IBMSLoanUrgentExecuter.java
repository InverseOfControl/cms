package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ReqLoanUrgentVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResLoanUrgentVO;
/**
 * 加急件限制接口
 */
public interface IBMSLoanUrgentExecuter {
	
	/**
	 * 根据营业部ID，syscode,系统对应的当前日期YYYY-MM 查询该营业部对应系统月份是否可以加急
	 */
	Response<ResLoanUrgentVO> getLoanUrgentSize(ReqLoanUrgentVO vo);
}
