package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSLoanAudit;

public interface IBMSLoanAuditEntityService extends BaseService<BMSLoanAudit>{
    /**
     * 根据loanNo查询审核表数据
     * @param loanNo
     * @return
     */
    BMSLoanAudit findBmsLoanAuditByLoanNo (String loanNo);

}
