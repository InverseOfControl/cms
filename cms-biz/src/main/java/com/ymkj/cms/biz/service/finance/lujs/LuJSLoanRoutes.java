package com.ymkj.cms.biz.service.finance.lujs;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;
import com.ymkj.cms.biz.service.sign.lujs.impl.LUJSContractConfirmImpl;
import com.ymkj.cms.biz.service.sign.lujs.impl.LUJSManualAuditImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:放款陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class LuJSLoanRoutes extends LoanRoutes {

	private static final Log log = LogFactory.getLog(LuJSLoanRoutes.class);
	@Override
	public void config() {
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.LUJS.getValue());
		
		/**放款审核（陆金所）*/
		add(EnumConstants.Sign.LOAN_TRIAL.getValue(), LuJSPassAuditLoan2Impl.class);
		
		/**放款审核退回（陆金所）*/
		add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), LuJSBackLoanAudit2Impl.class);
		
		/**放款确认退回（陆金所）*/
		add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), LuJSBackLoan2Impl.class);
		

		
	}
}
