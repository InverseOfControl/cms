package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;

public interface IBMSAppPersonInfoDao extends BaseDao<BMSAppPersonInfo>{
	BMSAppPersonInfo byLoanNoQueryInfo(Map<String,Object> map);
	
	public Integer updateByLoanNo(Map<String,Object> map);
	
	public List<Map<String, Object>> queryAdditionRecords(Map<String, String> paramMap);
	
	public Integer updatePhoneCellStatus(Map<String, Object> map);
	
	public List<Map<String,Object>> qyeryPhoneCellStatus(Map<String, Object> map);
}
