package com.ymkj.cms.biz.service.sign.bohai.two;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.bohai.two.impl.BoHaiTwoSignLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class BoHaiTwoRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(BoHaiTwoRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.BH2.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), BoHaiTwoSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), BoHaiTwoSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), BoHaiTwoCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), BoHaiTwoCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), BoHaiTwoRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), BoHaiTwoReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), BoHaiTwoSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), BoHaiTwoConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), BoHaiTwoBackConfirmImpl.class);
	}
}
