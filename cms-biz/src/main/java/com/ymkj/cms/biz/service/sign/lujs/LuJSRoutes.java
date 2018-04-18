package com.ymkj.cms.biz.service.sign.lujs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

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
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSSaveLoanContractPlatformAccImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LuJSSaveLoanContractSignImpl;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class LuJSRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(LuJSRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.LUJS.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), LuJSSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), LuJSSaveLoanContractBankAccImpl.class);
		
		/**保存陆金所用户信息（陆金所）*/
		add(EnumConstants.Sign.SAVE_PLATFROM_ACC.getValue(), LuJSSaveLoanContractPlatformAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), LuJSCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), LuJSCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), LuJSRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), LuJSReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), LUJSContractSignImpl.class);
		
		/**人工审核（陆金所）*/
		add(EnumConstants.Sign.MANUAL_AUDIT.getValue(), LUJSManualAuditImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), LUJSContractConfirmImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), LUJSBackConfirmImpl.class);
	}
}
