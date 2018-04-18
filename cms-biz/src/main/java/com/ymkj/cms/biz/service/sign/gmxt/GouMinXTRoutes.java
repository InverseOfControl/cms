package com.ymkj.cms.biz.service.sign.gmxt;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.gmxt.impl.GouMinXTSignLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class GouMinXTRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(GouMinXTRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.GMXT.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), GouMinXTSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), GouMinXTSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), GouMinXTCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), GouMinXTCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), GouMinXTRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), GouMinXTReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), GouMinXTSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), GouMinXTConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), GouMinXTBackConfirmImpl.class);
	}
}
