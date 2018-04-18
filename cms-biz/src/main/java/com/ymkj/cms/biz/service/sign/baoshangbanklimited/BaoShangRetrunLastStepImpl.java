package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.common.util.BmsLogger;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.base.ReturnLastStepImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 包银渠道上一步
 * Created by YM10140 on 2017/6/16.
 */
@Service
public class BaoShangRetrunLastStepImpl extends ReturnLastStepImpl {

    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Autowired
    private IBMSAppPersonInfoService ibmsAppPersonInfoService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private IBMSLoanExtEntityService ibmsLoanExtEntityService;
    @Override
    public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        //获取当前任务节点
        Task task = taskService.findTaskByLoanBaseId(String.valueOf(reqLoanContractSignVo.getId()));
        if (task != null) {
            reqLoanContractSignVo.setTaskName(task.getTaskName());
        } else {
            setError(new BizException(BizErrorCode.UFLOTASK_EOERR), res);
            return false;
        }
        return super.before(reqLoanContractSignVo, res);
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        BmsLogger.info("**********************包银渠道上一步操作开始***********************loanNo："+reqLoanContractSignVo.getLoanNo());
        //获取客户信息
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSAppPersonInfo appPersonInfo = ibmsAppPersonInfoService.byLoanNoQueryInfo(paramMap);
        //查询出业务流水号
        BMSLoanExt bmsLoanExt = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        String busNumber = bmsLoanExt.getBusNumber();
        //流水号不为空说明包银已经准入了
        if(busNumber!=null){
            //黑名单校验通过后的流程-银行卡鉴权，风控审核，生成合同上一步调用撤销接口
            String taskName = reqLoanContractSignVo.getTaskName();
            if (EnumConstants.WF_FKSHJY.equals(taskName) || EnumConstants.WF_YHKJQ.equals(taskName) || EnumConstants.WF_SCHT.equals(taskName)) {
                Map<String, String> loanRepeal = baoShangHttpService.loanRepeal("01", appPersonInfo.getIdNo(),  appPersonInfo.getName(),busNumber);
                if (null != loanRepeal && !loanRepeal.isEmpty()) {
                    if (!"000000".equals(loanRepeal.get("code"))) {
                        setError(new BizException(BizErrorCode.FACADE_RESPONSE_FAIL,loanRepeal.get("msg") ), res);
                        return false;
                    }else{
                        if(!EnumConstants.BaoyinAuditingState._002.getCode().equals(bmsLoanExt.getAuditingState())){
                            //包银撤销成功后置空包银状态值
                            BmsLogger.info("**********************包银撤销成功后置空包银状态值***********************");
                            long byStatusNull = ibmsLoanExtEntityService.updateByStatusNull( reqLoanContractSignVo.getLoanNo());
                            if(byStatusNull!=1){
                                BmsLogger.info("***********************包银撤销成功后置空包银状态值数据库操作异常***********************");
                                throw new BizException(CoreErrorCode.DB_UPDATE_RESULT_0,"包银撤销成功后置空包银状态值数据库操作异常！");
                            }
                        }
                    }
                } else {
                    setError(new BizException(CoreErrorCode.FACADE_ERROR, "撤销接口调用异常"), res);
                    return false;
                }
            }
        }
        BmsLogger.info("**********************包银渠道上一步操作结束***********************loanNo："+reqLoanContractSignVo.getLoanNo());
        return super.execute(reqLoanContractSignVo, res);
    }

}
