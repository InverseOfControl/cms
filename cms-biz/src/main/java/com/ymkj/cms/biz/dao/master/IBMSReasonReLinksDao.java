package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSReason;

public interface IBMSReasonReLinksDao extends BaseDao<BMSReason>  {

	/**
	 * 动态查询原因数据
	 * @param map
	 * @return
	 */
	public BMSReason findBMSReLinksById(Map<String, Object> map);

	/**
	 * 更新原因关联环节数据
	 * @param bmsReason
	 * @return
	 */
	public Integer updateBMSReasonByVal(BMSReason bmsReason);

	/**
	 * 根据关联Code查询管理数据
	 * @param relationCode
	 * @return
	 */
	public  List<BMSReason> findByRelationCode(Map<String,Object> map);

	/**
	 * 删除关联数据
	 * @param map
	 * @return
	 */
	public Integer delReasonCode(Map<String,Object> map);

	/**
	 * 更新该原因是否显示
	 * @param bmsReason
	 * @return
	 */
	public Integer updateBMSReasonIfShow(BMSReason bmsReason);
}
