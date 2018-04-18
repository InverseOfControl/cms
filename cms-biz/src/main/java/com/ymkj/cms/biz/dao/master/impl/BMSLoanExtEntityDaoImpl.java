package com.ymkj.cms.biz.dao.master.impl;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSLoanExtEntityDao;
import com.ymkj.cms.biz.entity.master.BMSLoanExt;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BMSLoanExtEntityDaoImpl extends BaseDaoImpl<BMSLoanExt> implements IBMSLoanExtEntityDao{

    @Override
    public BMSLoanExt findLoanExtByLoanNo(Map<String, Object> param) {
        return super.getSqlSession().selectOne("findLoanExtByLoanNo",param);
    }

    @Override
    public long updateBySatus(Map<String, Object> param) {
        return super.getSqlSession().update("updateBySatus",param);
    }

    @Override
    public long updateByStatusNull(Map<String, Object> param) {
        return super.getSqlSession().update("updateByStatusNull",param);
    }
}
