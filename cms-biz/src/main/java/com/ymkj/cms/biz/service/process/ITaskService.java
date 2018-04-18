package com.ymkj.cms.biz.service.process;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.service.TaskOpinion;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;


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
		 * 根据loanBaseId提交任务 并指定下一节点并添加流程变量
		 *
		 */
		@SuppressWarnings({ "rawtypes"})
		public void completeTaskByLoanBaseId(long loanBaseId,String path,Map varMap);
		
		
		/**
		 * 根据loanBaseId提交任务 并指定下一节点并添加option信息
		 *
		 */
		public void completeTaskByLoanBaseId(long loanBaseId,String path,TaskOpinion option);
		
		
		/**
		 * 根据loanBaseId提交任务 并指定下一节点并添加流程变量option信息
		 *
		 */
		public void completeTaskByLoanBaseId(Long id, String string, Map<String, Object> variables, TaskOpinion option);
		
		
		
		/**
		 * 根据loanBaseId提交任务 并指定下一节点并添加流程变量
		 *
		 */
		public void completeTaskByLoanBaseId(long loanBaseId,String path);

		/**
		 * 根据task得到TaskId
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public Task findTaskById(long taskId);
		
		/**
		 * 根据task得到TaskId
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public Task findTaskByLoanBaseId(String loanBaseId);
		
		
		
		/**
		 * 完成多人竞争任务
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public boolean compUsersTaskByLoanBaseId(long loanBaseId,String UserCode,String path);

		/**
		 * 完成多人竞争任务
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public void bacthCompTaskByList(List<BMSLoanBase> successList, String serviceCode, String path);

		/**
		 * 完成多人竞争任务 添加option
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public boolean compUsersTaskByLoanBaseId(long loanBaseId,String UserCode,String path,TaskOpinion option);

		
		 /**
		  * 更改任务处理人
		  * @param loanBaseId 申请ID
		  * @param username 新的处理人用户名
		  */
		public void changeTaskAssigneeByLoanBaseId(String loanBaseId,String userCode);
}
