package com.ymkj.cms.biz.api.service.task;

import com.ymkj.cms.biz.api.vo.request.task.ReqBMSTaskNumberVo;
import com.ymkj.cms.biz.api.vo.response.task.ResBMSTaskNumberVo;

/**
 * 任务数接口
 * @author YM10166
 *
 */
public interface ITaskNumberExecuter {
	
	/**
	 * 根据工号、审核节点查询正常队列数、优先队列数、挂起队列数
	 * @param reqBMSTaskNumberVo
	 * @return
	 */
	public ResBMSTaskNumberVo queryTaskNumberQues(ReqBMSTaskNumberVo reqBMSTaskNumberVo);
	
}
