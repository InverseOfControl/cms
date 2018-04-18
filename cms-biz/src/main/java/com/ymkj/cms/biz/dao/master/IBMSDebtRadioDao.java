package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSDebtRadio;

public interface IBMSDebtRadioDao extends BaseDao<BMSDebtRadio>{
	
	public BMSDebtRadio findDebtRadioById(BMSDebtRadio debtRadio);
	
	public Integer testConnection(Request req);

}
