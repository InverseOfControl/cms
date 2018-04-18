package com.ymkj.cms.biz.service.finance.bohai.two;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;
import com.ymkj.cms.biz.service.finance.bohai.two.impl.BoHaiTwoBackLoanAuditImpl;
import com.ymkj.cms.biz.service.finance.bohai.two.impl.BoHaiTwoBackLoanImpl;
import com.ymkj.cms.biz.service.finance.bohai.two.impl.BoHaiTwoPassAuditLoanImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:放款陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class BoHaiTwoLoanRoutes extends LoanRoutes {

	private static final Log log = LogFactory.getLog(BoHaiTwoLoanRoutes.class);
	@Override
	public void config() {
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.BH2.getValue());
		
		/**放款审核*/
		add(EnumConstants.Sign.LOAN_TRIAL.getValue(), BoHaiTwoPassAuditLoanImpl.class);
		
		/**放款审核退回*/
		add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), BoHaiTwoBackLoanAuditImpl.class);
		
		/**放款确认退回*/
		add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), BoHaiTwoBackLoanImpl.class);
		

		
	}
}
