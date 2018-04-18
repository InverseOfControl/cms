package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSTMReasonEntity;

public interface IBMSTMReasonDao extends BaseDao<BMSTMReasonEntity>{
	public List<BMSTMReasonEntity> twoLevelparents(Map<String, Object> paramMap);

	public BMSTMReasonEntity getByParam(Map<String, Object> reasonParamMap);
	/**
	 * 查询原因信息
	 */
	//public List<BMSTMReasonEntity> findReasonByOperType(Map<String, Object> map);
	
	public List<BMSTMReasonEntity> findFirstReasonByOperType(Map<String, Object> map);
	
	public List<BMSTMReasonEntity> findSecondReasonByOperType(Map<String, Object> map);
	

}
