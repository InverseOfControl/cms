package com.ymkj.cms.biz.service.sign.byapp;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSSysLogService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.base.ReturnLastStepImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ByAppReturnLastStepImpl extends ReturnLastStepImpl {
    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private IBMSSysLogService ibmsSysLogService;
    @Autowired
    private ITaskService taskService;
    private static final Log log = LogFactory.getLog(ByAppReturnLastStepImpl.class);

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        ResLoanContractSignVo resLoanContractSignVo = new ResLoanContractSignVo();
        //获取当前任务节点
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        //查询出业务流水号
        BMSLoanExt bmsLoanExt = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        String state = bmsLoanExt.getAuditingState();
        //如果是退件箱的上一步则需要特别处理
        Task task = taskService.findTaskByLoanBaseId(reqLoanContractSignVo.getId().toString());
        if (task != null && task.getTaskName().equals("退件箱")) {
            if (!StringUtils.isEmpty(state)) {
                resLoanContractSignVo.setAuditingState(state);
                res.setData(resLoanContractSignVo);
                return super.execute(reqLoanContractSignVo,res);
            }
        }
        //不是退件箱的上一步操作
        if (EnumConstants.BaoyinAuditingState._004.getCode().equals(state)
                || EnumConstants.BaoyinAuditingState._000.getCode().equals(state)) {
            resLoanContractSignVo.setAuditingState(state);
            res.setData(resLoanContractSignVo);
            //第二次审核中或者第二次审核通过上一步需要调用撤销接口
            if (baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) {
                try {
                    //完成当前任务，撤销成功之后重新开始签约流程
                    taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), "开始");
                } catch (Exception e) {
                    setError(new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常"), res);
                    return false;
                }
                //系统日志
                ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|上一步|return", "ILoanContractSignExecuter", "returnLastStep");
                return true;
            } else {
                log.info("调用包银撤销接口失败，loanNo：" + reqLoanContractSignVo.getLoanNo());
                //撤销失败
                return false;
            }
        } else if (EnumConstants.BaoyinAuditingState._001.getCode().equals(state)) {
            //主动查询风控App提交后的结果
            JSONObject dataJson = new JSONObject();
            dataJson.put("busNumber", bmsLoanExt.getBusNumber());// 业务申请流水号
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
                    if (resptx.contains("0002")) {
                        state = EnumConstants.BaoyinAuditingState._001.getCode();
                    } else if (resptx.contains("0105")) {
                        state = EnumConstants.BaoyinAuditingState._002.getCode();
                    } else {
                        state = EnumConstants.BaoyinAuditingState._004.getCode();
                    }
                } else if ("0500".equals(respcd)) {
                    state = EnumConstants.BaoyinAuditingState._000.getCode();
                } else if ("0100".equals(respcd)) {
                    state = EnumConstants.BaoyinAuditingState._002.getCode();
                }
                resLoanContractSignVo.setAuditingState(state);
                res.setData(resLoanContractSignVo);
                if ("0000".equals(respcd) || "0500".equals(respcd)) {
                    if (resptx.contains("0002") || resptx.contains("0105")) {//预授信阶段不调用撤销接口
                        return super.execute(reqLoanContractSignVo, res);
                    } else {
                        if (baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) {
                            try {
                                //完成当前任务，撤销成功之后重新开始签约流程
                                taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), "开始");
                            } catch (Exception e) {
                                setError(new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常"), res);
                                return false;
                            }
                            //系统日志
                            ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|上一步|return", "ILoanContractSignExecuter", "returnLastStep");
                            return true;
                        } else {
                            //撤销失败
                            log.info("调用包银撤销接口失败了，loanNo：" + reqLoanContractSignVo.getLoanNo());
                            return false;
                        }
                    }
                }
            } else {
                resLoanContractSignVo.setAuditingState(state);
                res.setData(resLoanContractSignVo);
                String respDesc = jsonRes.getString("respDesc");
                res.setRepMsg(respDesc);
                res.setRepCode("999999");
                return false;
            }
        } else if (EnumConstants.BaoyinAuditingState._002.getCode().equals(state)) {
            try {
                if (task != null && task.getTaskName().equals("保存信息")) {
                    taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), "上一步");
                }else{
                    taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), "开始");
                }
            } catch (Exception e) {
                setError(new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "工作流运行异常"), res);
                return false;
            }
            resLoanContractSignVo.setAuditingState(state);
            res.setData(resLoanContractSignVo);
            //系统日志
            ibmsSysLogService.recordSysLog(reqLoanContractSignVo, "合同签约|合同签约|上一步|return", "ILoanContractSignExecuter", "returnLastStep");
            return true;
        }
        resLoanContractSignVo.setAuditingState(state);
        res.setData(resLoanContractSignVo);
        return super.execute(reqLoanContractSignVo, res);
    }

    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        //查询任务节点传给前台
        Task task = taskService.findTaskByLoanBaseId(reqLoanContractSignVo.getId().toString());
        ResLoanContractSignVo resLoanContractSignVo = res.getData();
        String auditingState = resLoanContractSignVo.getAuditingState();
        resLoanContractSignVo.setTaskName(task.getTaskName());
        resLoanContractSignVo.setAuditingState(auditingState);
        res.setData(resLoanContractSignVo);
        return true;
    }
}
