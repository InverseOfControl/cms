package com.ymkj.cms.biz.api.vo.response.task;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;

/**
 * 任务数接口响应实体
 * @author YM10166
 *
 */
public class ResBMSTaskNumberVo extends Response<ResBMSTaskNumberVo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8339733301888902607L;
	
	/**
	 * 任务数队列列表
	 */
	private List<TaskNumberQueVo> taskNumQues;

	public List<TaskNumberQueVo> getTaskNumQues() {
		return taskNumQues;
	}

	public void setTaskNumQues(List<TaskNumberQueVo> taskNumQues) {
		this.taskNumQues = taskNumQues;
	}

}
