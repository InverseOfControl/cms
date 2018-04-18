package com.ymkj.cms.biz.service.finance.baoyin;

import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.common.loan.LoanRoutes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * 包银渠道放款路由
 * Created by YM10140 on 2017/7/24.
 */
@Component
public class BaoYinLoanRoutes extends LoanRoutes {
    private static final Log log = LogFactory.getLog(BaoYinLoanRoutes.class);
    @Override
    public void config() {
        log.info("------签约 渠道-包银 路由注册------");
        /**设置渠道*/
        setChannelCd(EnumConstants.channelCode.BAOSHANG.getValue());
        /**放款审核（包银）**/
        add(EnumConstants.Sign.LOAN_TRIAL.getValue(), ByPassAuditLoan2Impl.class);
        /**放款审核退回（包银）*/
        add(EnumConstants.Sign.LOAN_TRIAL_RETURN.getValue(), ByBackAudit2Impl.class);

        /**放款确认退回（包银）*/
        add(EnumConstants.Sign.LOAN_CONFIRM_RETURN.getValue(), ByBackLoan2Impl.class);

        log.info("------签约 渠道-包银 路由注册------/");
    }
}
