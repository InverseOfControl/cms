package com.ymkj.cms.biz.service.sign.aite.impl;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.sign.IAiTeLoanContractService;
import com.ymkj.cms.biz.service.sign.base.ConfirmLoanContractImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 合同确认  确认
 * 
 * @author YM10138
 *
 */
@Service
public class AiTeConfirmLoanContractImpl extends ConfirmLoanContractImpl {

	@Autowired
	private IAiTeLoanContractService aiTeLoanContractService;

	@Override
	public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		// 参数校验
		return super.before(reqLoanContractSignVo, res);
	}

	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		boolean notifyFalg = false;
		
		// 调用合同确认通知捞财宝
		notifyFalg = aiTeLoanContractService.contractConfirmationToAiTe(reqLoanContractSignVo);
		if (!notifyFalg) {
			throw new BizException(BizErrorCode.FACADE_RESPONSE_FAIL, "合同确认通知捞财宝失败");
		}
		
		return super.execute(reqLoanContractSignVo, res);
	}

	@Override
	public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		return super.after(reqLoanContractSignVo, res);
	}

}
