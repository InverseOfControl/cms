package com.ymkj.cms.biz.service.sign;

import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

public interface ILoanContractPatchBoltService {

	/**
	 * @Description:合同签约补件待办</p>
	 * @uthor YM10139
	 * @date  2017年8月8日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public PageBean<BMSLoanBaseEntity> undoneListPatchBoltPage(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * @Description:合同签约补件已完成</p>
	 * @uthor YM10139
	 * @date  2017年8月8日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public PageBean<BMSLoanBaseEntity> doneListPatchBoltPage(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	
	/**
	 * @Description:更新补件标识</p>
	 * @uthor YM10139
	 * @date  2017年8月8日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public boolean  updatePatchBoltSign(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * @Description:合同签约补件待办任务数</p>
	 * @uthor YM10139
	 * @date  2017年8月8日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public long queryContractPatchBoltToDoCount(Map<String, Object> paramsMap);
	
}
