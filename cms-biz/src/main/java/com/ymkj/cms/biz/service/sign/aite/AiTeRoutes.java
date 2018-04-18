package com.ymkj.cms.biz.service.sign.aite;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.aite.impl.AiTeSignLoanContractImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LUJSBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LUJSContractConfirmImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LUJSContractSignImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LUJSManualAuditImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.waimao.three.impl.WaiMaoTSignLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class AiTeRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(AiTeRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.AITE.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), AiTeSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), AiTeSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), AiTeCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), AiTeCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), AiTeRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), AiTeReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), AiTeSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), AiTeConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), AiTeBackConfirmImpl.class);
	}
}
