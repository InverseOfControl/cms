package com.ymkj.cms.biz.dao.master.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanAuditEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanAudit;

import java.util.Map;

@Repository
public class BMSLoanAuditEntityDaoImpl extends BaseDaoImpl<BMSLoanAudit> implements IBMSLoanAuditEntityDao{

    @Override
    public BMSLoanAudit findBmsLoanAuditByLoanNo(Map<String, Object> paramMap) {
        return super.getSqlSession().selectOne("findBmsLoanAuditByLoanNo",paramMap);
    }
}
