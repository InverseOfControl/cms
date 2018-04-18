package com.ymkj.cms.biz.dao.task;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.task.BMSTaskNumberQueEntity;

/**
 * 任务数接口Dao层
 * @author YM10166
 *
 */
public interface IBMSTaskNumberDao extends BaseDao<BMSTaskNumberQueEntity> {
	
	/**
	 * 根据工号、审核节点查询正常队列
	 * @param map
	 * @return
	 */
	public Integer queryNormalQueCount(Map<String,Object> map);
	
	/**
	 * 根据工号、审核节点查询优先队列
	 * @param map
	 * @return
	 */
	public Integer queryPriorityQueCount(Map<String,Object> map);
	
	/**
	 * 根据工号、审核节点查询挂起队列
	 * @param map
	 * @return
	 */
	public Integer queryPendingQueCount(Map<String,Object> map);

}
