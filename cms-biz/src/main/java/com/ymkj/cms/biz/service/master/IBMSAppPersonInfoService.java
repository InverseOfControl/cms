package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;

public interface IBMSAppPersonInfoService extends BaseService<BMSAppPersonInfo>{
	BMSAppPersonInfo byLoanNoQueryInfo(Map<String,Object> map);
	
	public boolean updateByLoanNo(Map<String, Object> map);
	
	public List<Map<String, Object>> queryAdditionRecords(Map<String, String> paramMap);
	
	public Integer updatePhoneCellStatus(Map<String, Object> map);
	
	public List<Map<String,Object>> qyeryPhoneCellStatus(Map<String, Object> map);

}
