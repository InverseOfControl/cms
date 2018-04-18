package com.ymkj.cms.biz.service.finance.lxxd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;
import com.ymkj.cms.biz.service.finance.lxxd.impl.LongXinXDBackLoanAuditImpl;
import com.ymkj.cms.biz.service.finance.lxxd.impl.LongXinXDBackLoanImpl;
import com.ymkj.cms.biz.service.finance.lxxd.impl.LongXinXDPassAuditLoanImpl;

/**
 * @Description:放款陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class LongXinXDLoanRoutes extends LoanRoutes {

	private static final Log log = LogFactory.getLog(LongXinXDLoanRoutes.class);
	@Override
	public void config() {
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.LXXD.getValue());
		
		/**放款审核*/
		add(EnumConstants.Sign.LOAN_TRIAL.getValue(), LongXinXDPassAuditLoanImpl.class);
		
		/**放款审核退回*/
		add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), LongXinXDBackLoanAuditImpl.class);
		
		/**放款确认退回*/
		add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), LongXinXDBackLoanImpl.class);
		

		
	}
}
