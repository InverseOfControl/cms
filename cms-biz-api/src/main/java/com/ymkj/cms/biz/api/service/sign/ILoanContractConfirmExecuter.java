package com.ymkj.cms.biz.api.service.sign;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

public interface ILoanContractConfirmExecuter {

	/**
	 * 确认待办任务   
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageResponse<ResLoanContractSignVo> undoneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 确认已完成任务   
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageResponse<ResLoanContractSignVo> doneContractConfirmListBy(ReqLoanContractSignVo reqLoanContractSignVo);


	/**
	 * 合同确认
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public  Response<ResLoanContractSignVo> confirmLoanContract(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 *合同确认回退
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public  Response<ResLoanContractSignVo> backConfirm(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 合同确认
	 * @param reqLoanContractSignVo
	 * @return
	 */
	/*public Response<ResLoanContractSignVo> returnBoxSubmit(ReqLoanContractSignVo reqLoanContractSignVo) ;*/
	
	/**
	 * @Description:合同确认待办任务数</p>
	 * @uthor YM10159
	 * @date 2017年7月3日 下午3:29:55
	 */
	public Response<Object> queryContractConfirmToDoCount(ReqLoanContractSignVo reqLoanContractSignVo);
}
