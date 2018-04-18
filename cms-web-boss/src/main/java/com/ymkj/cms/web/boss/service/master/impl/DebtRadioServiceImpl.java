package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSdebtRadioVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSDebtRadioVO;
import com.ymkj.cms.web.boss.facade.master.DebtRadioFacade;
import com.ymkj.cms.web.boss.service.master.IDebtRadioService;

@Service
public class DebtRadioServiceImpl implements IDebtRadioService {
	
	@Autowired
	private DebtRadioFacade debtRadioFacade;

	@Override
	public PageResult<ResBMSDebtRadioVO> listPage(ReqBMSdebtRadioVO debtRadioVO) {
		// TODO Auto-generated method stub
		return debtRadioFacade.listPage(debtRadioVO);
	}

	@Override
	public boolean updateById(ReqBMSdebtRadioVO debtRadioVO) {
		boolean falg=debtRadioFacade.updatebyId(debtRadioVO);
		if(falg){
			return true;
		}else{
			return false;
		}
	}



	@Override
	public ResBMSDebtRadioVO findByDebtRadioId(ReqBMSdebtRadioVO reqDebtRadio) {
		
		return debtRadioFacade.findByDebtRadioId(reqDebtRadio);
	}
	@Override
	public Integer testConnection(Request req) {

		return debtRadioFacade.testConnection(req);
	}

}
