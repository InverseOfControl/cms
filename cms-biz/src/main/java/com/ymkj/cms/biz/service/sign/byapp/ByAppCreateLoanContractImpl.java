package com.ymkj.cms.biz.service.sign.byapp;

import com.bstek.uflo.model.task.Task;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.process.ITaskService;
import com.ymkj.cms.biz.service.sign.ILoanBaseEntityService;
import com.ymkj.cms.biz.service.sign.baoshangbanklimited.CompareRepaymentContractImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *  生成合同接口-包银渠道改造APP接口
 */
@Service
public class ByAppCreateLoanContractImpl extends CompareRepaymentContractImpl {
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private ILoanBaseEntityService loanBaseEntityService;
    @Autowired
    private ITaskService taskService;
    //风控审核提示语
    @Value("${byAirControlMsg}")
    private String byAirControlMsg;
    private static final Log log = LogFactory.getLog(ByAppCreateLoanContractImpl.class);

    /**
     * ①　包银第二次审核结果是否为拒绝，若是，则提示“渠道审核未通过，请改签其他渠道！”
       ②　若校验通过，则弹框提示“信息将发送至渠道合作方，确定生成合同
     * @param reqLoanContractSignVo
     * @param res
     * @return
     */
    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        //审核状态 0审核中 1资料待上传（预授信通过）  2拒绝 3补件 4通过;
        String state = loanExtByLoanNo.getAuditingState();
        if(EnumConstants.BaoyinAuditingState._002.getCode().equals(state)){
            //第二次审核拒绝 2
            res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
            res.setRepMsg(byAirControlMsg+loanExtByLoanNo.getWindControlResult());
            return true;
        }else{
            //其他情况流程可以往下走
            return super.execute(reqLoanContractSignVo,res);
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
