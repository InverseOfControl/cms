package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.RefuseLoanImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: BaoShangRefuseLoanImpl 
 * @Description: 包银渠道 拒绝-调用撤销接口
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月15日 下午2:18:25
 */
@Service
public class BaoShangRefuseLoanImpl extends RefuseLoanImpl{
	
	@Autowired
	private BaoShangHttpService baoShangHttpService;
	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		if (!baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) return false;
		return super.execute(reqLoanContractSignVo, res);
	}

}
