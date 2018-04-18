package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSDebtRadio;

public interface IBMSDebtRadioService extends BaseService<BMSDebtRadio> {
	
	public boolean updateById(BMSDebtRadio debtDadio);
    
	public BMSDebtRadio findDebtRadioById(BMSDebtRadio debtRadio);
	
	public Integer testConnection(Request req);
}
