package com.ymkj.cms.biz.dao.master.impl;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSTmAppContactInfoDao;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactInfo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BMSTmAppContactInfoDaoImpl extends BaseDaoImpl<BMSTmAppContactInfo> implements IBMSTmAppContactInfoDao {

    /**
     * 根据借款编号查询借款人的联系人列表
     *
     * @param param
     * @return
     */
    @Override
    public List<BMSTmAppContactInfo> listByLoanNo(Map<String, Object> param) {
        return getSqlSession().selectList(getStatement("listByLoanNo"), param);
    }
}
