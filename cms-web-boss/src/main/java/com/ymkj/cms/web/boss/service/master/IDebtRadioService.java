package com.ymkj.cms.web.boss.service.master;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSdebtRadioVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSDebtRadioVO;

public interface IDebtRadioService {
	
	public PageResult<ResBMSDebtRadioVO> listPage(ReqBMSdebtRadioVO debtRadioVO);
	
	public boolean updateById(ReqBMSdebtRadioVO debtRadioVO);
	
	

	ResBMSDebtRadioVO findByDebtRadioId(ReqBMSdebtRadioVO reqDebtRadio);
	
	public Integer testConnection(Request req);

}
