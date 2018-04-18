package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.service.sign.base.SaveLoanContractSignImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存合同签约信息-包银渠道改造APP接口
 */
@Service
public class ByAppSaveLoanContractSignImpl extends SaveLoanContractSignImpl {
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    //风控审核提示语
    @Value("${blackListAuditMsg}")
    private String blackListAuditMsg;

    @Override
    public boolean before(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        return super.before(reqLoanContractSignVo, res);
    }

    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("loanNo", reqLoanContractSignVo.getLoanNo());
        //查询出业务流水号
        BMSLoanExt bmsLoanExt = ibmsLoanExtEntityDao.findLoanExtByLoanNo(paramMap);
        String state = bmsLoanExt.getAuditingState();
        if (state != null) {
            //拒绝的话保存不能往下走流程
            if (state.equals(EnumConstants.BaoyinAuditingState._002.getCode())) {
                res.setRepCode(BizErrorCode.BAOYIN_REFUSE.getErrorCode());
                res.setRepMsg(blackListAuditMsg);
                return false;
            }
        }
        return super.execute(reqLoanContractSignVo, res);
    }
}
