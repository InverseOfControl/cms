package com.ymkj.cms.biz.service.sign.baoshangbanklimited;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.service.http.BaoShangHttpService;
import com.ymkj.cms.biz.service.sign.base.BackLoanImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: BaoShangBackLoanImpl 
 * @Description: 放款确认  退回
 * @author: lisc@yuminsoft.com
 * @date: 2017年6月15日 上午11:13:46
 */
@Service
public class BaoShangBackLoanImpl extends BackLoanImpl{
	
	@Autowired
	private BaoShangHttpService baoShangHttpService;
	@Override
	public boolean execute(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res) {
		if (baoShangHttpService.BaoShangLoanRepeal(reqLoanContractSignVo, res)) return false;
		return super.execute(reqLoanContractSignVo, res);
	}

}
