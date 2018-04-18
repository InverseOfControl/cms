package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;

import java.util.Map;

public interface IBMSLoanExtEntityService extends BaseService<BMSLoanExt>{

    BMSLoanExt findLoanExtByLoanNo(Map<String, Object> param);

    /**
     * 包银撤销接口调用成功后置空包银相关字段
     * @param loanNo
     * @return
     */
    long updateByStatusNull(String loanNo);
}
