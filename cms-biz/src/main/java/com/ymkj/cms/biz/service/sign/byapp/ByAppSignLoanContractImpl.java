package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.apply.LoanProductService;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.HttpClientService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.base.SignLoanContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 合同签订-包银渠道改造APP接口
 *
 * @author YM10138
 */
@Service
public class ByAppSignLoanContractImpl extends SignLoanContractImpl {
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ILoanBaseEntityService loanBaseEntityService;
    @Autowired
    private LoanProductService loanProductService;
    @Autowired
    private HttpClientService httpClientService;

    @Value("#{env['nodeKey']?:''}")
    private String nodeKey;
    //风控审核提示语
    @Value("${byAirControlMsg}")
    private String byAirControlMsg;
    //app完成提交提示语
    @Value("${byAppCommitMsg}")
    private String byAppCommitMsg;
    //包银状态值异常提示语
    @Value("${byStatusErrorMsg}")
    private String byStatusErrorMsg;
    @Value("${byElectronicSignatureMsg}")
    private String byElectronicSignatureMsg;
    //查询电子签章接口
    @Value(value = "#{env['get_sign_url']?:''}")
    private String getSignUrl;

    private static final Log log = LogFactory.getLog(ByAppSignLoanContractImpl.class);

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        //获取合同类型，电子版需要校验
        String contractTypeCode = loanProductService.getContractType(reqLoanContractSignVo.getLoanNo());
        if (contractTypeCode == null) {
            log.info("查询合同类型失败，loanNo:" + reqLoanContractSignVo.getLoanNo());
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"contractTypeCode"}), res);
            return false;
        }
        //0 纸质版 1 电子版
        if ("1".equals(contractTypeCode)) {
            Map<String, Object> result = httpClientService.querySign(getSignUrl, reqLoanContractSignVo.getLoanNo());
            log.info("调用电子签章签名查询接口地址返回：" + JsonUtils.encode(result));
            if (result == null) {
                throw new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, "电子签名接口调用异常!");
            }
            if ("000000".equals(result.get("resCode")) && !"OK".equals(result.get("attachment"))) {
                res.setRepCode(BizErrorCode.BAOYIN_DZQZ_UNDONE.getErrorCode());
                res.setRepMsg(byElectronicSignatureMsg);
                return false;
            }
        }

        //签订之前校验是否①　校验客户在包银消费APP上，是否已经完成提交动作（要通过授信查询接口主动查询，看状态是否为“交易已受，
        // 请稍后查询交易结果”或“通过”），若未提交时，系统提示“请登录APP完成提交！”
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        //先去库中查询，如果没有则发起主动查询
        String state = loanExtByLoanNo.getAuditingState();
        if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
            //主动查询风控App提交后的结果
            JSONObject dataJson = new JSONObject();
            dataJson.put("busNumber", loanExtByLoanNo.getBusNumber());// 业务申请流水号
            dataJson.put("txncd", "BYXY0004"); // 接口编号
            HttpResponse response = baoShangHttpService.baoShangUrl("bsb100004", dataJson);
            JSONObject jsonRes = new JSONObject(response.getContent());
            JSONObject infos = jsonRes.getJSONObject("infos");
            //保存包银返回的状态值
            Map<String, Object> paramExt = new HashMap<>();
            paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
            if (infos.has("data")) {
                JSONObject jo = infos.getJSONObject("data");
                String resptx = jo.get("resptx").toString();
                String respcd = jo.get("respcd").toString();
                if ("0000".equals(respcd)) {
                    //通过-----预授信通过或者审核通过
                    if (resptx.contains("0002")) {
                        //预授信通过，但是app还未完成提交
                        paramExt.put("windControlResult", resptx);
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._001.getCode());
                        res.setRepCode(BizErrorCode.BAOYIN_UNSUBMIT.getErrorCode());
                        res.setRepMsg(byAppCommitMsg);
                    } else if (resptx.contains("0105")) {
                        //预授信失败,但是app还未完成提交
                        paramExt.put("windControlResult", resptx);
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());
                        res.setRepCode(BizErrorCode.BAOYIN_UNSUBMIT.getErrorCode());
                        res.setRepMsg(byAppCommitMsg);
                    } else {
                        paramExt.put("windControlResult", resptx);
                        paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._004.getCode());//通过
                        paramExt.put("orgAuditState", EnumConstants.OrgAuditState.TG.getCode());//机构审核状态
                        res.setRepCode(respcd);
                    }
                    //更新借款系统包银返回的状态值
                    long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                    if (updateBySatus != 1) {
                        log.debug("*******************更新包银返回的状态值失败*******************");
                        throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
                    }
                    if (resptx.contains("0002") || resptx.contains("0105")) {
                        return false;
                    } else {
                        return super.execute(reqLoanContractSignVo, res);
                    }
                } else if ("0100".equals(respcd)) {
                    //拒绝
                    paramExt.put("windControlResult", resptx);
                    paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._002.getCode());//拒绝
                    paramExt.put("orgAuditState", EnumConstants.OrgAuditState.BTG.getCode());//机构审核状态
                    res.setRepMsg(byAirControlMsg+resptx);
                    res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                    //更新借款系统包银返回的状态值
                    long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                    if (updateBySatus != 1) {
                        log.debug("*******************更新包银返回的状态值失败*******************");
                        throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
                    }
                    return true;
                } else if ("0500".equals(respcd)) {
                    //审核中
                    paramExt.put("windControlResult", resptx);
                    paramExt.put("auditingState", EnumConstants.BaoyinAuditingState._000.getCode());
                    paramExt.put("orgAuditState", EnumConstants.OrgAuditState.SHZ.getCode());//机构审核状态
                    //更新借款系统包银返回的状态值
                    long updateBySatus = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                    if (updateBySatus != 1) {
                        log.debug("*******************更新包银返回的状态值失败*******************");
                        throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0, "保存包银渠道返回的状态值失败");
                    }
                    return super.execute(reqLoanContractSignVo, res);
                } else {
                    res.setRepMsg(resptx);
                    res.setRepCode(respcd);
                    return false;
                }
            } else {
                res.setRepCode("999999");
                String respDesc = jsonRes.getString("respDesc");
                res.setRepMsg(respDesc);
                return false;
            }
        } else {
            //审核状态 0审核中 1资料待上传（预授信通过）  2拒绝 3补件 4通过;
            if (EnumConstants.BaoyinAuditingState._004.getCode().equals(state) || EnumConstants.BaoyinAuditingState._000.getCode().equals(state)) {
                //0审核中 4通过
                return super.execute(reqLoanContractSignVo, res);
            } else if (EnumConstants.BaoyinAuditingState._002.getCode().equals(state)) {
                //2拒绝
                res.setRepMsg(byAirControlMsg+loanExtByLoanNo.getWindControlResult());
                res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                return true;
            } else if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
                //1 app未完成提交
                res.setRepCode(BizErrorCode.BAOYIN_UNSUBMIT.getErrorCode());
                res.setRepMsg(byAppCommitMsg);
                return false;
            } else {
                res.setRepMsg(byStatusErrorMsg);
                res.setRepCode("999999");
                return false;
            }
        }
    }

    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        // 查询版本号 并传给前端
        BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
        resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
        resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
        res.setData(resLoanSignVo);
        try {
            //如果拒绝则合同签订工作流结束
            if (BizErrorCode.BAOYIN_REFUSE.getErrorCode().equals(res.getRepCode())) {
                taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_JJQX);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage());
        }
        return super.after(reqLoanContractSignVo, res);
    }
}
