package com.ymkj.cms.biz.service.sign.zhengdaptp;

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
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPBackConfirmImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPCancelLoanImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPConfirmLoanContractImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPCreateLoanContractImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPRefuseLoanImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPReturnLastStepImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPSaveLoanContractBankAccImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPSaveLoanContractSignImpl;
import com.ymkj.cms.biz.service.sign.zhengdaptp.impl.ZhengDaPTPSignLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:签约陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class ZhengDaPTPRoutes extends SignRoutes {

	private static final Log log = LogFactory.getLog(ZhengDaPTPRoutes.class);

	@Override
	public void config() {
		
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.ZDP2P.getValue());
		
		/**保存合同签约信息*/
		add(EnumConstants.Sign.SAVE.getValue(), ZhengDaPTPSaveLoanContractSignImpl.class);
		
		/**保存合同银行信息*/
		add(EnumConstants.Sign.SAVE_BANK_ACC.getValue(), ZhengDaPTPSaveLoanContractBankAccImpl.class);
		
		/**生成合同*/
		add(EnumConstants.Sign.CREATE_CONTRACT.getValue(), ZhengDaPTPCreateLoanContractImpl.class);
		
		/**取消*/
		add(EnumConstants.Sign.CANCEL.getValue(), ZhengDaPTPCancelLoanImpl.class);
		
		/**拒绝*/
		add(EnumConstants.Sign.REFUSE.getValue(), ZhengDaPTPRefuseLoanImpl.class);
		
		/**上一步*/
		add(EnumConstants.Sign.RETURN_LAST_STEP.getValue(), ZhengDaPTPReturnLastStepImpl.class);
		
		/**合同签订*/
		add(EnumConstants.Sign.SIGN_CONTRACT.getValue(), ZhengDaPTPSignLoanContractImpl.class);
		
		/**合同确认*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM.getValue(), ZhengDaPTPConfirmLoanContractImpl.class);
		
		/**合同确认 退回*/
		add(EnumConstants.Sign.CONTRACT_CONFIRM_RETURN.getValue(), ZhengDaPTPBackConfirmImpl.class);
	}
}
