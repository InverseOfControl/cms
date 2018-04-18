package com.ymkj.cms.web.boss.service.sign;

import com.ymkj.base.core.web.result.JsonResult;

/**
 * 渠道签约包银API接口（接收包银>>网关>>信息）
 * Created by YM10140 on 2017/6/27.
 */
public interface IBaoshangBankLimitedService {
    /**
     * 包银授信结果通知
     * @return
     */
    JsonResult<String> riskManagementNotice(String receivePost);

    /**
     * 单据撤销通知
     * @param receivePost
     * @return
     */
    JsonResult<String> revokeNotice(String receivePost);

    /**
     * 授信成功后超时拒绝结果通知
     * @param receivePost
     * @return
     */
    JsonResult<String> timeOutRefuse(String receivePost);
}
