package com.ymkj.cms.biz.service.sign.waimao.three;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
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
public class WaiMaoTRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(WaiMaoTRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.WAIMAOT.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), WaiMaoTSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), WaiMaoTSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), WaiMaoTCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), WaiMaoTCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), WaiMaoTRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), WaiMaoTReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), WaiMaoTSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), WaiMaoTConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), WaiMaoTBackConfirmImpl.class);
	}
}
