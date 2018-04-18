package com.ymkj.cms.biz.api.service.sign.aite;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

/**
 * 爱特（捞财宝）合同签约
 * @author 李璇
 * @date 2017年3月16日 下午3:48:00
 */
public interface IAiTeLoanContractExecuter {
	/**
	 * 取消
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> cancelLoan(ReqLoanContractSignVo reqLoanContractSignVo) ;
	

	/**
	 * 拒绝   
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> refuseLoan(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 标的推送给艾特
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> signLoanContractHasTargetPushed(ReqLoanContractSignVo reqLoanContractSignVo);
	

	/**
	 * 还款计划
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月18日下午4:06:56
	 */
	public Response<Object> queryRepaymentDetail(RequestVo requestVo);
	
}
