package com.ymkj.cms.biz.dao.master;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactInfo;

import java.util.List;
import java.util.Map;

public interface IBMSTmAppContactInfoDao extends BaseDao<BMSTmAppContactInfo>{
    /**
     * 根据借款编号查询借款人的联系人列表
     * @param param
     * @return
     */
    List<BMSTmAppContactInfo> listByLoanNo(Map<String,Object> param);
}
