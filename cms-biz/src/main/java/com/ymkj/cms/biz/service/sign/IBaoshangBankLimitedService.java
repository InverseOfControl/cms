package com.ymkj.cms.biz.service.sign;

import java.util.Map;

/**
 *渠道签约包银API接口（接收包银>>网关>>信息）
 * Created by YM10140 on 2017/6/28.
 */
public interface IBaoshangBankLimitedService {
    /**
     * 包商银行-授信通知接口
     * @param busNumber
     * @param respcd
     * @param resptx
     * @param patchType
     * @return
     */
    Map<String,String> confirmRiskManagementNotice(String busNumber,String respcd,String resptx,String patchType);

    /**
     * 单据撤销通知
     * @param busNumber
     * @param respcd
     * @param resptx
     * @return
     */
    Map<String,String> confirmRevokeNotice(String busNumber, String respcd, String resptx);

    /**
     * 授信成功后超时拒绝结果通知
     * @param busNumber
     * @param respcd
     * @param resptx
     * @return
     */
    Map<String,String> confirmTimeOutRefuse(String busNumber, String respcd, String resptx);

    /**
     * 包银撤销接口
     * @param idType
     * @param idNo
     * @param custName
     * @param busNumber
     * @return
     */
    Map<String, String> loanRepeal(String idType, String idNo, String custName, String busNumber);
}
