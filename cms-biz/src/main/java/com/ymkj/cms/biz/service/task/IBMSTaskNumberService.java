package com.ymkj.cms.biz.service.task;

import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.task.BMSTaskNumberQueEntity;

/**
 * 任务数接口Service层
 * @author YM10166
 *
 */
public interface IBMSTaskNumberService extends BaseService<BMSTaskNumberQueEntity> {
	
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
