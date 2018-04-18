package com.ymkj.cms.biz.api.service.sign;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

public interface ILoanContractPatchBoltExecuter {

	
	/**
	 * 补件待办任务
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageResponse<ResLoanContractSignVo> contractPatchBoltListPage(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 更新补件标识
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> updatePatchBoltInSign(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	
	/**
	 * 补件待办任务记录数
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<Object> queryContractPatchBoltToDoCount(ReqLoanContractSignVo reqLoanContractSignVo);

}
