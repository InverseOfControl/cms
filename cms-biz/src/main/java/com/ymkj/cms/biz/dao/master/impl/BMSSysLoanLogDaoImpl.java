package com.ymkj.cms.biz.dao.master.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSSysLoanLogDao;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;

@Repository
public class BMSSysLoanLogDaoImpl extends BaseDaoImpl<BMSSysLoanLog> implements IBMSSysLoanLogDao{

	@Override
	public BMSSysLoanLog findFirstOldPassTime(String loanNo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("loanNo", loanNo);
		return this.getSqlSession().selectOne("findFirstOldPassTime", map);
	}

}
