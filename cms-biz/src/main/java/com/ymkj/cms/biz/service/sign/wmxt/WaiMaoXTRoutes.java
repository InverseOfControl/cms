package com.ymkj.cms.biz.service.sign.wmxt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.wmxt.impl.WaiMaoXTSignLoanContractImpl;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class WaiMaoXTRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(WaiMaoXTRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.WMXT.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), WaiMaoXTSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), WaiMaoXTSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), WaiMaoXTCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), WaiMaoXTCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), WaiMaoXTRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), WaiMaoXTReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), WaiMaoXTSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), WaiMaoXTConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), WaiMaoXTBackConfirmImpl.class);
	}
}
