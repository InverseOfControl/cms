package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;

public interface IBMSTMReasonService extends BaseService<BMSTMReasonEntity>{

	public  List<BMSTMReasonEntity> twoLevelparents(Map<String, Object> paramMap);

	public BMSTMReasonEntity getByParam(Map<String, Object> reasonParamMap);
	
	//public List<BMSTMReasonEntity>findReasonByOperType(Map<String, Object> reasonParamMap);
	
	public List<BMSTMReasonEntity> findFirstReasonByOperType(Map<String, Object> map);
	
	public List<BMSTMReasonEntity> findSecondReasonByOperType(Map<String, Object> map);
}
