package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取包银渠道短信验证码
 * Created by YM10140 on 2017/6/9.
 */
@Service
public class BaoShangGetVerificationCodeImpl extends BaseSign<ResLoanContractSignVo> {
    @Autowired
    private IBMSAppPersonInfoService ibmsAppPersonInfoService;
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private ILoanContractSignService loanContractSignService;
    private static final Log log = LogFactory.getLog(BaoShangGetVerificationCodeImpl.class);

    @Override
    public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response res) {
        // 参数校验
        Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
        if (!validateResponse.isSuccess()) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
            return false;
        }
        if (reqLoanContractSignVo.getChannelCd() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getId() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getBankPhone() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"bankPhone"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getLoanNo() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
            return false;
        }
        return true;
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response res) {
        log.info("*************************获取短信验证码开始*************************loanNo:"+reqLoanContractSignVo.getLoanNo());
        //查询出客户的信息
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(paramMap);
        if (appPersonInfo == null) {
            setError(new BizException(CoreErrorCode.DB_RESULT_ISNULL, "客户个人信息表记录为空"), res);
            return false;
        }
        //查询出借款产品信息
        BMSLoanProduct bmsLoanProduct = loanContractSignService.findBMSLoanProductByLoanNo(reqLoanContractSignVo.getLoanNo());
        //包银参数接口设置
        JSONObject dataJson = new JSONObject();
        dataJson.put("bankCardNo", bmsLoanProduct == null ? "" : bmsLoanProduct.getApplyBankCardNo()); // 银行卡号
        dataJson.put("custName", appPersonInfo.getName()); // 客户姓名
        dataJson.put("idType", "01");// 证件类型
        dataJson.put("idNo", appPersonInfo.getIdNo()); // 证件号码
        dataJson.put("mobNo", reqLoanContractSignVo.getBankPhone());// 手机号码
        dataJson.put("txncd", "BYXY0012"); // 接口编号

        //调用包银获取短信验证码接口接口
        HttpResponse response = baoShangHttpService.baoShangUrl("bsb100012", dataJson);
        JSONObject jsonRes = new JSONObject(response.getContent());
        JSONObject infos = jsonRes.getJSONObject("infos");
        if (infos.has("data")) {
            JSONObject jo = infos.getJSONObject("data");
            if (jo.has("respcd") && "0000".equals(jo.get("respcd"))) {
                res.setRepCode("000000");
                res.setRepMsg(jo.get("resptx").toString());
            } else {
                res.setRepCode("999999");
                res.setRepMsg(jo.get("resptx").toString());
            }
        } else {
            res.setRepCode("999999");
            String respDesc = jsonRes.getString("respDesc");
            res.setRepMsg(respDesc);
        }
        log.info("*************************获取短信验证码完成*************************");
        return true;
    }

}
