package com.ymkj.cms.biz.service.master.impl;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;
import com.ymkj.cms.biz.service.master.IBMSLoanExtEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BMSLoanExtEntityServiceImpl extends BaseServiceImpl<BMSLoanExt> implements IBMSLoanExtEntityService{

	@Autowired
	private IBMSLoanExtEntityDao bmsLoanExtEntityDao;
	
	@Override
	protected BaseDao<BMSLoanExt> getDao() {
		return bmsLoanExtEntityDao;
	}

    @Override
    public BMSLoanExt findLoanExtByLoanNo(Map<String, Object> param) {
        return bmsLoanExtEntityDao.findLoanExtByLoanNo(param);
    }

	@Override
	public long updateByStatusNull(String loanNo) {
		Map<String, Object> param=new HashMap<>();
		param.put("loanNo",loanNo);
		return bmsLoanExtEntityDao.updateByStatusNull(param);
	}
}
