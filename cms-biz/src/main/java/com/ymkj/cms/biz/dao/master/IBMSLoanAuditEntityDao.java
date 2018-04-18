package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSLoanAudit;

import java.util.Map;

public interface IBMSLoanAuditEntityDao extends BaseDao<BMSLoanAudit>{

    BMSLoanAudit findBmsLoanAuditByLoanNo(Map<String, Object> paramMap);
}
