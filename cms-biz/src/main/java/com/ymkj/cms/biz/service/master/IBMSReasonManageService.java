package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSReason;

public interface IBMSReasonManageService extends BaseService<BMSReason> {
	/**
	 * 根据传的值，查询原因
	 * @param map
	 * @return
	 */
	List<BMSReason>  findBMSReasonByValue(Map<String,Object> map);
	
	/**
	 * 新增数据
	 * @param bmsReason
	 * @return
	 */
	public long insert(BMSReason bmsReason);
	
	/**
	 * 更新数据
	 * @param bmsReason
	 * @return
	 */
	public long update(BMSReason bmsReason);
	
	/**
	 * 查询当前最大ID
	 * @return
	 */
	public Integer findMaxId();

	/**
	 * 根据父类ID查询
	 * @param parentId
	 * @return
	 */
	public BMSReason findReasonByPId(Long parentId);

	/**
	 * 动态更新数据
	 * @param reason
	 * @return
	 */
	public Integer updateById(BMSReason reason);

	/**
	 * 数据导出
	 * @param map
	 * @return
	 */
	public List<BMSReason> findReasonExport(Map<String,Object> map);

	/**
	 * 根据入参查询原因值
	 */
	public List<BMSReason> findReasonByParame(Map<String,Object> map);
}
