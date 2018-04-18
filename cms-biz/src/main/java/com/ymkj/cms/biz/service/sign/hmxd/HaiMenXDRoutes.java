package com.ymkj.cms.biz.service.sign.hmxd;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.hmxd.impl.HaiMenXDSignLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class HaiMenXDRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(HaiMenXDRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.HMXD.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), HaiMenXDSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), HaiMenXDSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), HaiMenXDCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), HaiMenXDCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), HaiMenXDRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), HaiMenXDReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), HaiMenXDSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), HaiMenXDConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), HaiMenXDBackConfirmImpl.class);
	}
}
