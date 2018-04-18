package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSReason;

public interface IBMSReasonManageDao extends BaseDao<BMSReason>{
	
	/**
	 * 根据传的值，查询原因
	 * @param map
	 * @return
	 */
	public List<BMSReason> findBMSReasonByValue(Map<String,Object> map);

	/**
	 * 查询最大的ID值
	 * @return
	 */
	public Integer findMaxId();

	/**
	 * 根据父类ID查询数据
	 */
	public BMSReason findReasonByPId(Long parentId);

	/**
	 * 根据ID和CODE查询数据
	 * @param reason
	 * @return
	 */
	public Integer updateById(BMSReason reason);

	/**
	 * 查询所有数据,导出数据
	 */
	public List<BMSReason> findReasonExport(Map<String,Object> map);

	/**
	 * 根据入参查询原因值
	 */
	public List<BMSReason> findReasonByParame(Map<String,Object> map);

}
