package com.ymkj.cms.biz.service.finance.hrbh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;
import com.ymkj.cms.biz.service.finance.hrbh.impl.HuaRuiBHBackLoanAuditImpl;
import com.ymkj.cms.biz.service.finance.hrbh.impl.HuaRuiBHBackLoanImpl;
import com.ymkj.cms.biz.service.finance.hrbh.impl.HuaRuiBHPassAuditLoanImpl;

/**
 * @Description:放款陆金所渠道路由</p>
 * @uthor YM10159
 * @date 2017年7月6日 下午2:52:08
 */
@Component
public class HuaRuiBHLoanRoutes extends LoanRoutes {

	private static final Log log = LogFactory.getLog(HuaRuiBHLoanRoutes.class);
	@Override
	public void config() {
		/**设置渠道*/
		setChannelCd(EnumConstants.channelCode.HRBH.getValue());
		
		/**放款审核*/
		add(EnumConstants.Sign.LOAN_TRIAL.getValue(), HuaRuiBHPassAuditLoanImpl.class);
		
		/**放款审核退回*/
		add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), HuaRuiBHBackLoanAuditImpl.class);
		
		/**放款确认退回*/
		add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), HuaRuiBHBackLoanImpl.class);
		

		
	}
}
