package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.BackConfirmImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: BaoShangBackConfirmImpl 
 * @Description: 包银 合同确认 退回
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月15日 上午10:38:54
 */
@Service
public class BaoShangBackConfirmImpl extends BackConfirmImpl{

	@Autowired
	private BaoShangHttpService baoShangHttpService;
	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		if (!baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) return false;
		return super.execute(reqLoanContractSignVo, res);
	}
}
