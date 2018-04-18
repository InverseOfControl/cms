package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.BackAuditImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @ClassName: BaoShangBackAuditImpl
 * @Description: 包银放款审核退回
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月15日 上午10:38:18
 */
@Service
public class BaoShangBackAuditImpl extends BackAuditImpl{
	
	@Autowired
	private BaoShangHttpService baoShangHttpService;
	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		if (baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) return false;
		return super.execute(reqLoanContractSignVo, res);
	}

}
