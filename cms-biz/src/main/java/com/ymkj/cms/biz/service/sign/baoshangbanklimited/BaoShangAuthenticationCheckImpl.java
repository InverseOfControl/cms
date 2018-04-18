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
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.ILoanContractSignService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行卡鉴权-包银渠道
 * Created by YM10140 on 2017/6/12.
 */
@Service
public class BaoShangAuthenticationCheckImpl extends BaseSign<ResLoanContractSignVo> {
    //包银渠道产品小类编号
    @Value("#{env['prodSubNo']?:''}")
    private String prodSubNo;
    //银行卡鉴权提示语
    @Value("${bankNoAuditMsg}")
    private String bankNoAuditMsg;
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
    private ILoanContractSignService loanContractSignService;
    private static final Log log = LogFactory.getLog(BaoShangAuthenticationCheckImpl.class);

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
        if (reqLoanContractSignVo.getChannelCd() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"channelCd"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getLoanNo() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"loanNo"}), res);
            return false;
        }
        if (reqLoanContractSignVo.getVerifyCode() == null) {
            setError(new BizException(CoreErrorCode.REQUEST_PARAM_ISNULL, new Object[]{"verifyCode"}), res);
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
        } else if (EnumConstants.WF_YHKJQ.equals(task.getTaskName())) {
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
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        log.info("*************************银行卡鉴权开始*************************loanNo:"+reqLoanContractSignVo.getLoanNo());
        JSONObject dataJson = new JSONObject();
        //查询出借款产品信息
        BMSLoanProduct bmsLoanProduct = loanContractSignService.findBMSLoanProductByLoanNo(reqLoanContractSignVo.getLoanNo());
        //获取客户信息
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(paramMap);
        //查询出业务流水号
        BMSLoanExt bmsLoanExt = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        //保存包银返回的状态值
        Map<String, Object> paramExt = new HashMap<>();
        paramExt.put("loanNo", reqLoanContractSignVo.getLoanNo());
        dataJson.put("bankCardNo", bmsLoanProduct == null ? "" : bmsLoanProduct.getApplyBankCardNo()); // 银行卡号
        dataJson.put("mobileNo", reqLoanContractSignVo.getBankPhone());// 手机号码
        dataJson.put("busNumber", bmsLoanExt == null ? "" : bmsLoanExt.getBusNumber()); // 业务流水号
        dataJson.put("prodSubNo", prodSubNo); // 产品小类编号
        dataJson.put("bankCardType", "1"); // 银行卡类型
        dataJson.put("idType", "01");// 证件类型
        dataJson.put("custName", appPersonInfo.getName()); // 客户姓名
        dataJson.put("idNo", appPersonInfo.getIdNo()); // 证件号码
        dataJson.put("shtMessVerCd", reqLoanContractSignVo.getVerifyCode()); // 短信验证码
        dataJson.put("txncd", "BYXY0013"); // 接口编号

        //调用包银银行卡鉴权接口
        HttpResponse response = baoShangHttpService.baoShangUrl("bsb100013", dataJson);
        JSONObject jsonRes = new JSONObject(response.getContent());
        JSONObject infos = jsonRes.getJSONObject("infos");
        if (infos.has("data")) {
            JSONObject jo = infos.getJSONObject("data");
            if (jo.has("respcd") && "0000".equals(jo.get("respcd"))) {
                paramExt.put("byState", EnumConstants.BaoYinSatus._003.getCode());//银行卡鉴权通过
                res.setRepCode("000000");
                res.setRepMsg(jo.get("resptx").toString());
                //更新借款表包银状态值
                long update = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (update != 1) {
                    log.info("保存包银渠道状态，更新借款扩展表失败！");
                    //抛异常保证事务回滚
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"保存包银渠道返回的状态值失败");
                }
            } else if(jo.has("respcd") && "3400".equals(jo.get("respcd"))){
                paramExt.put("byRefusalResult",jo.get("resptx"));//拒绝原因记录
                paramExt.put("byState", EnumConstants.BaoYinSatus._002.getCode());//银行卡鉴权拒绝
                res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                res.setRepMsg(bankNoAuditMsg+jo.get("resptx").toString());
                //更新借款表包银状态值
                long update = ibmsLoanExtEntityDao.updateBySatus(paramExt);
                if (update != 1) {
                    log.info("保存包银渠道状态，更新借款扩展表失败！");
                    //抛异常保证事务回滚
                    throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"保存包银渠道返回的状态值失败");
                }
            }else{
                res.setRepCode(jo.get("respcd").toString());
                res.setRepMsg(jo.get("resptx").toString());
            }
        } else {
            String respDesc = jsonRes.getString("respDesc");
            res.setRepCode("999999");
            res.setRepMsg(respDesc);
        }
        log.info("*************************银行卡鉴权完成*************************");
        return true;
    }

    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        // 查询版本号 并传给前端
        BMSLoanBaseEntity bmsLoanBaseEntity = loanBaseEntityService.findByloanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        ResLoanContractSignVo resLoanSignVo = new ResLoanContractSignVo();
        resLoanSignVo.setPversion(bmsLoanBaseEntity.getPversion());
        resLoanSignVo.setVersion(bmsLoanBaseEntity.getVersion());
        res.setData(resLoanSignVo);
        // 完成任务-银行卡鉴权
        try {
            if ("000000".equals(res.getRepCode())) {
                taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_TG);
            }
        } catch (Exception e) {
            setError(new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage()), res);
            return false;
        }
        return true;
    }
}
