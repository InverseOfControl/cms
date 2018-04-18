package com.ymkj.cms.biz.service.master;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactInfo;

import java.util.List;
import java.util.Map;

public interface IBMSTmAppContactInfoService  extends BaseService<BMSTmAppContactInfo>{
    /**
     * 根据借款编号查询借款人的联系人列表
     * @param param
     * @return
     */
    public List<BMSTmAppContactInfo> listByLoanNo(Map<String,Object> param);
}
