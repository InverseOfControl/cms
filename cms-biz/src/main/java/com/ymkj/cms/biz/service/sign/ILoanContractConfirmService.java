package com.ymkj.cms.biz.service.sign;


import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

public interface ILoanContractConfirmService {
	
	/**
	 * 合同确认
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean confirmLoanContract(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 *合同确认回退
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean backConfirm(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 *  确认待办任务
	 *
	 */
	public PageBean<BMSLoanBaseEntity> undoneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	

	/**
	 *  确认已完成任务
	 *
	 */
	public PageBean<BMSLoanBaseEntity> doneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * @Description:合同确认待办任务数</p>
	 * @uthor YM10159
	 * @date 2017年7月3日 下午3:37:55
	 */
	long queryContractConfirmToDoCount(Map<String,Object> paramsMap);
	
}
