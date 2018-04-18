package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSSysLoanLogDao;
import com.ymkj.cms.biz.entity.master.BMSSysLoanLog;
import com.ymkj.cms.biz.service.master.IBMSSysLoanLogService;
/**
 * 
 * @author YM10161
 *
 */
@Service
public class BMSSysLoanLogServiceImpl extends BaseServiceImpl<BMSSysLoanLog> implements IBMSSysLoanLogService {
	@Autowired
	private IBMSSysLoanLogDao bmsSysLoanLogDao;
	@Override
	protected BaseDao<BMSSysLoanLog> getDao() {
		return bmsSysLoanLogDao;
	}
	@Override
	public Long saveOrUpdate(BMSSysLoanLog sysLoanLog) {
		// TODO Auto-generated method stub
		long id = 0;
		if(sysLoanLog.getId() == null){
			long insert = bmsSysLoanLogDao.insert(sysLoanLog);
			if(insert > 0){
				id = sysLoanLog.getId();
			}
		}else{
			 long update = bmsSysLoanLogDao.update(sysLoanLog);
			 if(update > 0){
				 id = sysLoanLog.getId();
			 }
		}
		
		return id;
		 
	}
	@Override
	public List<BMSSysLoanLog> listPages(Map<String, Object> map) {
		return bmsSysLoanLogDao.listBy(map);
	}
	@Override
	public BMSSysLoanLog findFirstOldPassTime(String loanNo) {
		
		return bmsSysLoanLogDao.findFirstOldPassTime(loanNo);
	}

 

	

}
