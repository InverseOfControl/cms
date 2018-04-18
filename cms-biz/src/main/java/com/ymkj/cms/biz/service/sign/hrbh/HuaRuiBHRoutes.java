package com.ymkj.cms.biz.service.sign.hrbh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.sign.SignRoutes;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.hrbh.impl.HuaRuiBHSignLoanContractImpl;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class HuaRuiBHRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(HuaRuiBHRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.HRBH.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), HuaRuiBHSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), HuaRuiBHSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), HuaRuiBHCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), HuaRuiBHCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), HuaRuiBHRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), HuaRuiBHReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), HuaRuiBHSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), HuaRuiBHConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), HuaRuiBHBackConfirmImpl.class);
	}
}
