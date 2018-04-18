package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.CancelLoanImpl;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 包银渠道 取消-调用撤销接口
 * Created by YM10140 on 2017/7/19.
 */

public class BaoShangCancelLoanImpl extends CancelLoanImpl {

    @Autowired
    private BaoShangHttpService baoShangHttpService;
    @Override
    public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
        if (!baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) return false;
        return super.execute(reqLoanContractSignVo, res);
    }
}
