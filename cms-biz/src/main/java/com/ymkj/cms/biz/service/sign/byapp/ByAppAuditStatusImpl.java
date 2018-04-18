package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 包银app改造上一步之前需要判断第二次审核状态
 */
@Service
public class ByAppAuditStatusImpl extends BaseSign<ResLoanContractSignVo> {
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;

    private static final Log log = LogFactory.getLog(ByAppAuditStatusImpl.class);

    @Override
    public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        // 参数校验
        Response<Object> validateResponse = Validate.getInstance().validate(reqLoanContractSignVo);
        if (!validateResponse.isSuccess()) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISBLANK, validateResponse.getRepMsg()), res);
            return false;
        }
        if (reqLoanContractSignVo.getId() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"id"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getLoanNo() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getChannelCd() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"}), res);
            return false;
        }
        return true;
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        ResLoanContractSignVo resLoanContractSignVo = new ResLoanContractSignVo();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        String state = loanExtByLoanNo.getAuditingState();
        //先去库中查询，如果没有则发起主动查询
        String busNumber = loanExtByLoanNo.getBusNumber();
        if (busNumber != null && state.equals("1")) {
            //主动查询风控App提交后的结果
            JSONObject dataJson = new JSONObject();
            dataJson.put("txncd", "BYXY0004"); // 接口编号
            dataJson.put("busNumber", busNumber);// 业务申请流水号
            HttpResponse response = baoShangHttpService.baoShangUrl("bsb100004", dataJson);
            JSONObject jsonRes = new JSONObject(response.getContent());
            JSONObject infos = jsonRes.getJSONObject("infos");
            //保存包银返回的状态值
            Map<String, Object> paramExt = new HashMap<>();
            paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
            if (infos.has("data")) {
                JSONObject jo = infos.getJSONObject("data");
                String respcd = jo.get("respcd").toString();
                String resptx = jo.get("resptx").toString();
                if ("0000".equals(respcd)) {
                    //通过-----预授信通过或者审核通过
                    if (resptx.contains("0002")) {
                        //预授信通过，但是app还未完成提交
                        paramExt.put("windControlResult", resptx);
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._001.getCode());
                        state=EnumConstants.BaoyinAuditingState._001.getCode();
                    } else if (resptx.contains("0105")) {
                        //预授信失败,但是app还未完成提交
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());
                        paramExt.put("orgAuditState", EnumConstants.OrgAuditState.BTG.getCode());//机构审核状态
                        paramExt.put("windControlResult", resptx);
                        state=EnumConstants.BaoyinAuditingState._002.getCode();
                    } else {
                        paramExt.put("windControlResult", resptx);
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._004.getCode());//通过
                        paramExt.put("orgAuditState", EnumConstants.OrgAuditState.TG.getCode());//机构审核状态
                        state=EnumConstants.BaoyinAuditingState._004.getCode();
                    }
                } else if ("0100".equals(respcd)) {
                    //拒绝
                    paramExt.put("windControlResult", resptx);
                    paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());//拒绝
                    paramExt.put("orgAuditState", EnumConstants.OrgAuditState.BTG.getCode());//机构审核状态
                    state=EnumConstants.BaoyinAuditingState._002.getCode();
                } else if ("0500".equals(respcd)) {
                    //审核中
                    paramExt.put("windControlResult", resptx);
                    paramExt.put("orgAuditState", EnumConstants.OrgAuditState.SHZ.getCode());//机构审核状态
                    paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._000.getCode());
                    state=EnumConstants.BaoyinAuditingState._000.getCode();
                }else{
                    paramExt.put("windControlResult", resptx);
                }
                //更新借款系统包银返回的状态值
                long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (updateBySatus != 1) {
                    log.debug("*******************更新包银返回的状态值失败*******************");
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
                }
                res.setRepMsg(resptx);
                res.setRepCode("000000");
            } else {
                res.setRepCode("999999");
                String respDesc = jsonRes.getString("respDesc");
                res.setRepMsg(respDesc);
                return false;
            }
        }
        resLoanContractSignVo.setAuditingState(state);
        res.setRepCode("000000");
        res.setRepMsg("查询状态成功");
        res.setData(resLoanContractSignVo);
        return true;
    }
}
