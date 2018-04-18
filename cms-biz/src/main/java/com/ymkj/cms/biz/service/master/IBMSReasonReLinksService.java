package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSReason;

public interface IBMSReasonReLinksService extends BaseService<BMSReason> {
	/**
	 * 动态查询原因数据
	 * @param map
	 * @return
	 */
	public BMSReason findBMSReLinksById(Map<String, Object> map);
	
	/**
	 * 新增原因数据
	 * @param bmsReason
	 * @return
	 */
	public long update(BMSReason bmsReason);
	
	/**
	 * 插入原因数据
	 * @param bmsReason
	 * @return
	 */
	public long insert(BMSReason bmsReason);
	
	/**
	 * 更新原因数据
	 * @param bmsReason
	 * @return
	 */
	public Integer updateBMSReasonByVal(BMSReason bmsReason);
	
	/**
	 * 根据关联CODE查询
	 * @param map
	 * @return
	 */
	public List<BMSReason> findByRelationCode (Map<String,Object> map);
	
	/**
	 * 删除关联数据
	 * @param map
	 * @return
	 */
	public Integer delReasonCode(Map<String, Object> map);
	
	/**
	 * 更新原因数据是否可见
	 * @param bmsReason
	 * @return
	 */
	public Integer updateBMSReasonIfShow(BMSReason bmsReason);

}
