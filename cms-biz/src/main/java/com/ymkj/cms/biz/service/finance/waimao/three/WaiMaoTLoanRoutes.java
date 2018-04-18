package com.ymkj.cms.biz.service.finance.waimao.three;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;
import com.ymkj.cms.biz.service.finance.waimao.three.impl.WaiMaoTBackLoanAuditImpl;
import com.ymkj.cms.biz.service.finance.waimao.three.impl.WaiMaoTBackLoanImpl;
import com.ymkj.cms.biz.service.finance.waimao.three.impl.WaiMaoTPassAuditLoanImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:放款陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class WaiMaoTLoanRoutes extends LoanRoutes {

	private static final Log log = LogFactory.getLog(WaiMaoTLoanRoutes.class);
	@Override
	public void config() {
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.WAIMAOT.getValue());
		
		/**放款审核*/
		add(EnumConstants.Sign.LOAN_TRIAL.getValue(), WaiMaoTPassAuditLoanImpl.class);
		
		/**放款审核退回*/
		add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), WaiMaoTBackLoanAuditImpl.class);
		
		/**放款确认退回*/
		add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), WaiMaoTBackLoanImpl.class);
		

		
	}
}
