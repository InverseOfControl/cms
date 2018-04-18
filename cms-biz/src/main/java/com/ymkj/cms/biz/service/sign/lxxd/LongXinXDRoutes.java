package com.ymkj.cms.biz.service.sign.lxxd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.lxxd.impl.LongXinXDSignLoanContractImpl;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class LongXinXDRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(LongXinXDRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.LXXD.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), LongXinXDSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), LongXinXDSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), LongXinXDCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), LongXinXDCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), LongXinXDRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), LongXinXDReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), LongXinXDSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), LongXinXDConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), LongXinXDBackConfirmImpl.class);
	}
}
