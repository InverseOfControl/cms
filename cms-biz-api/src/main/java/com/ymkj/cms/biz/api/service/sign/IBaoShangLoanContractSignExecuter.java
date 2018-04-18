package com.ymkj.cms.biz.api.service.sign;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

public interface IBaoShangLoanContractSignExecuter {
    /**
     * 包银渠道审核通过与否查询
     *
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> ByChannelAuditResult(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 获取包银渠道短信验证码
     *
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> getVerificationCode(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 黑名单校验-包银渠道
     *
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> silverListCheck(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 银行卡鉴权-包银渠道
     *
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> authenticationCheck(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 风控审核-包银渠道
     *
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> airControlCheck(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 风控审核结果查询-包银渠道
     *
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> airControlCheckResult(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 风控审核通知
     *
     * @return
     */
    Response<ResLoanContractSignVo> riskManagementNotice(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     *单据撤销通知
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> revokeNotice(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 授信成功后超时拒绝结果通知
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> timeOutRefuse(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 包银撤销
     * @param reqLoanContractSignVo
     * @return
     */
    Response<Object> loanRepeal(ReqLoanContractSignVo reqLoanContractSignVo);

    /**
     * 包银app改造上一步之前需要判断第二次审核状态
     * @param reqLoanContractSignVo
     * @return
     */
    Response<ResLoanContractSignVo> byAppAuditStatus(ReqLoanContractSignVo reqLoanContractSignVo);
}
