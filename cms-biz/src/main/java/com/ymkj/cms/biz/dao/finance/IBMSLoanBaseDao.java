package com.ymkj.cms.biz.dao.finance;

import java.util.Map;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSLoanConfirmationQuery;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

public interface IBMSLoanBaseDao extends BaseDao<BMSLoanBase> {
	
	public PageBean<BMSLoanBase> doneListPage(PageParam pageParam, Map<String, Object> paramMap);
	
	public BMSLoanConfirmationQuery auditCommit(Map<String,Object> map);
	
	public Integer findLoanIdbyFeeInfo(Integer id);
	
}
