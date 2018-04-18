package com.ymkj.cms.biz.service.finance.zhengdaptp;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;
import com.ymkj.cms.biz.service.finance.zhengdaptp.impl.ZhengDaPTPBackAuditImpl;
import com.ymkj.cms.biz.service.finance.zhengdaptp.impl.ZhengDaPTPBackLoanImpl;
import com.ymkj.cms.biz.service.finance.zhengdaptp.impl.ZhengDaPTPGrantLoanImpl;
import com.ymkj.cms.biz.service.finance.zhengdaptp.impl.ZhengDaPTPPassAuditLoanImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:放款陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class ZhengDaPTPLoanRoutes extends LoanRoutes {

	private static final Log log = LogFactory.getLog(ZhengDaPTPLoanRoutes.class);
	@Override
	public void config() {
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.ZDP2P.getValue());
		
		/**放款审核*/
		add(EnumConstants.Sign.LOAN_TRIAL.getValue(), ZhengDaPTPPassAuditLoanImpl.class);
		
		/**放款审核退回*/
		add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), ZhengDaPTPBackAuditImpl.class);
		
		/**放款确认*/
		add(EnumConstants.Sign.LOAN_CONFIRM.getValue(), ZhengDaPTPGrantLoanImpl.class);
		
		/**放款确认退回*/
		add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), ZhengDaPTPBackLoanImpl.class);
		

		
	}
}
