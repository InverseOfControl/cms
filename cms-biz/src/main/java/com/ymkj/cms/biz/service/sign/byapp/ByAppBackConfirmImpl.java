package com.ymkj.cms.biz.service.sign.byapp;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.BackConfirmImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *  合同确认 退回 -包银渠道改造APP接口
 */
@Service
public class ByAppBackConfirmImpl extends BackConfirmImpl{
    @Autowired
    private IBMSLoanExtEntityDao ibmsLoanExtEntityDao;
    @Autowired
    private BaoShangHttpService baoShangHttpService;

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
        if(!StringUtils.isEmpty(state)){
            //拒绝时不掉撤销接口
           if(state.equals(EnumConstants.BaoyinAuditingState._000.getCode()) || state.equals(EnumConstants.BaoyinAuditingState._004.getCode())){
                //审核中的和通过的退回时调用撤销接口
                if (!baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) return false;
                return super.execute(reqLoanContractSignVo, res);
           }
        }
        return super.execute(reqLoanContractSignVo, res);
    }

    @Override
    public boolean after(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        return super.after(reqLoanContractSignVo, res);
    }
}
