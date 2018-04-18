package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;

import java.util.Map;

public interface IBMSLoanExtEntityDao extends BaseDao<BMSLoanExt>{

    BMSLoanExt findLoanExtByLoanNo(Map<String, Object> param);

    long updateBySatus(Map<String, Object> param);

    /**
     * 撤销接口调用成功后置空包银相关的状态值
     * @param param
     * @return
     */
    long updateByStatusNull(Map<String, Object> param);
}
