package com.ymkj.cms.biz.service.finance.aite;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;
import com.ymkj.cms.biz.service.finance.aite.impl.AiTeBackLoanAuditImpl;
import com.ymkj.cms.biz.service.finance.aite.impl.AiTeBackLoanImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:放款陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class AiTeLoanRoutes extends LoanRoutes {

	private static final Log log = LogFactory.getLog(AiTeLoanRoutes.class);
	@Override
	public void config() {
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.AITE.getValue());
		
		/**放款审核退回（陆金所）*/
		add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), AiTeBackLoanAuditImpl.class);
		
		/**放款确认退回（陆金所）*/
		add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), AiTeBackLoanImpl.class);
		

		
	}
}
