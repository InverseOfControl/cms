package com.ymkj.cms.biz.service.sign.byapp;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractBankAccImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 签约 保存合同银行信息
 */
@Service
public class ByAppSaveLoanContractBankAccImpl extends SaveLoanContractBankAccImpl{
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    //风控审核提示语
    @Value("${blackListAuditMsg}")
    private String blackListAuditMsg;
    @Autowired
    private ITaskService taskService;
    private static final Log log = LogFactory.getLog(ByAppSaveLoanContractBankAccImpl.class);

    @Override
    public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        return super.before(reqLoanContractSignVo, res);
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        //获取当前任务节点
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        //查询出业务流水号
        BMSLoanExt bmsLoanExt = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        String state = bmsLoanExt.getAuditingState();
        if(state!=null){
            //拒绝的话保存不能往下走流程
            if(state.equals(EnumConstants.BaoyinAuditingState._002.getCode())){
                res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                res.setRepMsg(blackListAuditMsg);
                return true;
            }
        }
        return super.execute(reqLoanContractSignVo, res);
    }

    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        try {
            //如果拒绝则合同签订工作流结束
            if (BizErrorCode.BAOYIN_REFUSE.getErrorCode().equals(res.getRepCode())) {
                Task task = taskService.findTaskByLoanBaseId(String
                        .valueOf(reqLoanContractSignVo.getId()));
                if (task != null) {
                    taskService.completeTaskByLoanBaseId(reqLoanContractSignVo.getId(), EnumConstants.WFPH_JJQX);
                    return true;
                }
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, e.getMessage());
        }
        return super.after(reqLoanContractSignVo, res);
    }
}
