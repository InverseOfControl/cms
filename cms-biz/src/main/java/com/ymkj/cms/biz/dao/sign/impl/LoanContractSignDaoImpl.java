package com.ymkj.cms.biz.dao.sign.impl;

import org.springframework.stereotype.Repository;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.sign.ILoanContractSignDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;

import java.util.Map;

@Repository
public class LoanContractSignDaoImpl extends BaseDaoImpl<BMSLoanProduct> implements ILoanContractSignDao {


    /**
     * 查询借款产品信息
     *
     * @param param
     * @return
     */
    @Override
    public BMSLoanProduct findBMSLoanProductByLoanNo(Map<String, Object> param) {
        return super.getSqlSession().selectOne("findBMSLoanProductByLoanNo",param);
    }
}
