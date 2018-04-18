package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.Validate;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.sign.BaseSign;
import com.ymkj.cms.biz.common.util.JsonUtils;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.http.ICoreHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 黑名单校验-包银渠道
 * Created by YM10140 on 2017/6/12.
 */
@Service
public class BaoShangSilverListCheckImpl extends BaseSign<ResLoanContractSignVo> {

    //包银渠道产品小类编号
    @Value("#{env['prodSubNo']?:''}")
    private String prodSubNo;
    //黑名单校验提示语
    @Value("${blackListAuditMsg}")
    private String blackListAuditMsg;
    @Autowired
    private IBMSAppPersonInfoService ibmsAppPersonInfoService;
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ILoanBaseEntityService loanBaseEntityService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private ICoreHttpService coreHttpService;
    private static final Log log = LogFactory.getLog(BaoShangSilverListCheckImpl.class);

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

        // 流程校验
        Task task = taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        BMSLoanBaseEntity bmsLoanBaseEntity1 = loanBaseEntityService
                .findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        if (task == null) {
            if (!reqLoanContractSignVo.getServiceCode().equals(bmsLoanBaseEntity1.getSignCode())) {
                setError(new BizException(BizErrorCode.OPRATEUSER_EOERR, "当前签约客服异常，请刷新"), res);
                return false;
            }
        } else if (EnumConstants.WF_HMDJY.equals(task.getTaskName())) {
            if (!reqLoanContractSignVo.getServiceCode().equals(task.getAssignee())) {
                setError(new BizException(BizErrorCode.OPRATEUSER_EOERR, "当前签约客服异常，请刷新"), res);
                return false;
            }
        } else {
            setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
            return false;
        }
        return true;
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response res) {
        log.info("****************************黑名单检验开始****************************loanNo:"+reqLoanContractSignVo.getLoanNo());
        //获取借款基本信息
        BMSLoanBaseEntity baseEntity = loanBaseEntityService.getLoanInfoByLoanNo(reqLoanContractSignVo.getLoanNo());
        //获取客户信息
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(paramMap);
        //保存包银返回的状态值
        Map<String, Object> paramExt = new HashMap<>();
        paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
        //包银接口参数设置
        JSONObject dataJson = new JSONObject();
        dataJson.put("prodSubNo", prodSubNo);// 产品小类编号
        dataJson.put("mercNo", baseEntity == null ? "" : baseEntity.getOwningBranchId());// 商户编号
        dataJson.put("mercName", baseEntity == null ? "" : baseEntity.getOwningBranch());// 商户名称
        dataJson.put("custName", appPersonInfo == null ? "" : appPersonInfo.getName()); // 客户姓名
        dataJson.put("idType", "01");// 证件类型
        dataJson.put("idNo", appPersonInfo == null ? "" : appPersonInfo.getIdNo()); // 证件号码
        dataJson.put("mobNo", appPersonInfo == null ? "" : appPersonInfo.getCellphone());// 手机号码
        if (appPersonInfo == null) {
            dataJson.put("gender", "");// 性别
        } else if ("男".equals(appPersonInfo.getGender()) || "M".equals(appPersonInfo.getGender())) {
            dataJson.put("gender", "M");// 性别
        } else {
            dataJson.put("gender", "F");// 性别
        }
        dataJson.put("transAmt", new BigDecimal(String.format("%.2f", new BigDecimal(baseEntity.getContractLmt().toString()))));// 审批金额
        dataJson.put("totalCnt", baseEntity.getContractTrem());// 总期数
        dataJson.put("txncd", "BYXY0002"); // 接口编号

        //调用包银接口-黑名单校验
        HttpResponse response = baoShangHttpService.baoShangUrl("bsb100002", dataJson);
        JSONObject jsonRes = new JSONObject(response.getContent());
        JSONObject infos = jsonRes.getJSONObject("infos");
        if (infos.has("data")) {
            JSONObject jo = infos.getJSONObject("data");
            if (jo.has("respcd") && "0000".equals(jo.get("respcd"))) {//交易通过
                /************返回给接口调用方业务申请流水号****/
                res.setFlowId(jo.getString("busNumber"));
                res.setRepCode("000000");
                res.setRepMsg(jo.get("resptx").toString());
                //黑名单校验通过--000000
                paramExt.put("byState", EnumConstants.BaoYinSatus._001.getCode());
                //保存包银返回的业务流水号
                paramExt.put("busNumber", jo.getString("busNumber"));
                //更新借款表包银状态值
                long update = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (update != 1) {
                    log.info("保存包银渠道状态，更新借款扩展表失败!");
                    //抛异常保证事务回滚
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"保存包银渠道返回的状态值失败");
                }
                /*******************同时将业务流水号推送给核心************************/
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("loanNo", reqLoanContractSignVo.getLoanNo());
                param.put("busNumber", res.getFlowId());
                // 调用核心系统推送业务流水号
                HttpResponse httpResponse = coreHttpService.saveOrUpdateBsyhBusNo(param);
                HttpContractReturnEntity contractReturnEntity = JsonUtils.decode(httpResponse.getContent(),
                        HttpContractReturnEntity.class);
                if (!"000000".equals(contractReturnEntity.getCode())) {
                    //抛异常保证事务回滚
                    log.info("包银返回的业务申请流水号推送给核心失败：" + contractReturnEntity.getMessage());
                    setError(new BizException(CoreErrorCode.FACADE_RESPONSE_FAIL, contractReturnEntity.getMessage()), res);
                    return false;
                }
            } else if(jo.has("respcd") && "0100".equals(jo.get("respcd"))){//交易拒绝
                //黑名单校验拒绝
                paramExt.put("byState", EnumConstants.BaoYinSatus._000.getCode());
                paramExt.put("byRefusalResult", jo.get("resptx").toString());//包银黑名单拒绝原因
                res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                res.setRepMsg(blackListAuditMsg);
                //更新借款表包银状态值
                long update = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (update != 1) {
                    log.info("保存包银渠道状态，更新借款扩展表失败!");
                    //抛异常保证事务回滚
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"保存包银渠道返回的状态值失败");
                }
            }else{//接口调用异常
                res.setRepCode(jo.get("respcd").toString());
                res.setRepMsg(jo.get("resptx").toString());
            }
        } else {
            String respDesc = jsonRes.getString("respDesc");
            res.setRepCode("999999");
            res.setRepMsg(respDesc);
        }
        log.debug("***黑名单检验完成***");
        return true;
    }


    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response res) {
        // 查询版本号 并传给前端
        BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
        resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
        resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
        res.setData(resLoanSignVo);
        try {
            //完成任务-黑名单校验如果黑名单校验通过则完成当前任务
            if ("000000".equals(res.getRepCode())) {
                taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(),
                        EnumConstants.WFPH_TG);
            } else if(BizErrorCode.BAOYIN_REFUSE.equals(res.getRepCode())){
                //返回拒绝的话当前工作流结束
                taskService.completeTaskByLoanBaseId(Long.valueOf(reqLoanContractSignVo.getId()), EnumConstants.WFPH_JJQX);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage());
        }
        return true;
    }

}
