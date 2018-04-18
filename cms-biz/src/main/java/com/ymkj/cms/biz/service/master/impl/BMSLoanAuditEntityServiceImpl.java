package com.ymkj.cms.biz.service.master.impl;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanAuditEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanAudit;
import com.ymkj.cms.biz.service.master.IBMSLoanAuditEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BMSLoanAuditEntityServiceImpl extends BaseServiceImpl<BMSLoanAudit> implements IBMSLoanAuditEntityService{

	@Autowired
	private IBMSLoanAuditEntityDao bmsLoanAuditEntityDao;
	
	@Override
	protected BaseDao<BMSLoanAudit> getDao() {
		return bmsLoanAuditEntityDao;
	}

    @Override
    public BMSLoanAudit findBmsLoanAuditByLoanNo(String loanNo) {
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("loanNo",loanNo);
        return bmsLoanAuditEntityDao.findBmsLoanAuditByLoanNo(paramMap);
    }
}
