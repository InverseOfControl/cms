package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.service.sign.base.ConfirmLoanContractImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 合同确认-包银渠道改造APP接口
 *
 * @author YM10138
 */
@Service
public class ByAppConfirmLoanContractImpl extends ConfirmLoanContractImpl {
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    //风控审核提示语
    @Value("${byAirControlMsg}")
    private String byAirControlMsg;
    //app完成提交提示语
    @Value("${byAppCommitMsg}")
    private String byAppCommitMsg;
    //包银状态值异常提示语
    @Value("${byStatusErrorMsg}")
    private String byStatusErrorMsg;
    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        //	若包银处于审核中：可以继续确认，流转到【放款审核-待办】
        //	若包银返回结果为拒绝：提示“渠道审核未通过，请改签其他渠道！”。不能确认，只能退回，退回时不调用撤销接口
        //	若包银返回结果为通过：可以退回（调撤销接口）、也可以确认通过
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        BMSLoanExt loanExtByLoanNo = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        //审核状态 1资料待上传（预授信通过）  2拒绝 3补件 4通过;
        String state = loanExtByLoanNo.getAuditingState();
        if(EnumConstants.BaoyinAuditingState._000.getCode().equals(state) ||EnumConstants.BaoyinAuditingState._004.getCode().equals(state)){
            //0审核中 4通过
            return super.execute(reqLoanContractSignVo,res);
        }else if(EnumConstants.BaoyinAuditingState._002.getCode().equals(state)){
            //2拒绝
            res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
            res.setRepMsg(byAirControlMsg+loanExtByLoanNo.getWindControlResult());
            return false;
        }else if(EnumConstants.BaoyinAuditingState._001.getCode().equals(state)){
            res.setRepMsg(byAppCommitMsg);
            res.setRepCode(BizErrorCode.BAOYIN_UNSUBMIT.getErrorCode());
            return false;
        }else{
            res.setRepMsg(byStatusErrorMsg);
            res.setRepCode("999999");
            return false;
        }
    }
}
