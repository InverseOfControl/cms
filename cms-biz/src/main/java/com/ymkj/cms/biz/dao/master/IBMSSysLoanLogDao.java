package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;

public interface IBMSSysLoanLogDao extends BaseDao<BMSSysLoanLog>{
	public BMSSysLoanLog findFirstOldPassTime(String loanNo);
	
}
