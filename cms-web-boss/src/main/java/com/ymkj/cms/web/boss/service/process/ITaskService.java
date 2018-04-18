package com.ymkj.cms.web.boss.service.process;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import com.bstek.uflo.model.task.Task;




public interface ITaskService {
	
		/**
		 * 查询待办
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public List<Task> findNotDoTasks(Map<String,Object> variables) ;
		
		
		/**
		 * 提交任务 
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public void completeTask(long taskId);
		
		/**
		 * 提交任务 并指定下节点
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public void completeTask(long taskId,String path);

		/**
		 * 根据task得到TaskId
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public Task findTaskById(long taskId);

}
