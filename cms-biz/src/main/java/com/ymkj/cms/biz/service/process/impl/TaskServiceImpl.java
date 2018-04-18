package com.ymkj.cms.biz.service.process.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.model.task.TaskParticipator;
import com.bstek.uflo.query.TaskQuery;
import com.bstek.uflo.service.TaskOpinion;
import com.bstek.uflo.service.TaskService;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.process.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService{
	
	@Resource(name=TaskService.BEAN_ID)
	private  TaskService taskService;
	
		/**
		 * 查询待办
		 */
		public List<Task> findNotDoTasks(Map<String,Object> variables) {
			String loginUser=(String)variables.get("loginUser");
			//查询创建的任务
			TaskQuery query=taskService.createTaskQuery();
			query.addAssignee(loginUser);
			List<Task> tasks=query.list();
			return tasks;
		}

		/**
		 * 提交任务 并指定下一节点
		 *
		 */
		public void completeTask(long taskId,String path){
			//获得任务bean
			taskService.start(taskId);
			taskService.complete(taskId, path); //完成任务
		}

		
		/**
		 * 根据loanBaseId提交任务 并指定下一节点
		 *
		 */
		public void completeTaskByLoanBaseId(long loanBaseId,String path){
			//获得任务bean
			Task task = findTaskByLoanBaseId(String.valueOf(loanBaseId));
			if(task !=null){
				taskService.start(task.getId());
				taskService.complete(task.getId(), path); //完成任务
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
		}
		
		
		/**
		 * 根据loanBaseId获取处理人列表
		 *
		 */
		public List<String> getUserByLoanBaseId(long loanBaseId,String path){
			List<String> handlerList=null;
			//获得任务bean
			Task task = findTaskByLoanBaseId(String.valueOf(loanBaseId));
			if(task !=null){
				List<TaskParticipator> participators=	taskService.getTaskParticipators(task.getId());
				if(participators !=null && participators.size()>0){
					handlerList= new ArrayList<String>();
					for (TaskParticipator taskParticipator : participators) {
						handlerList.add(taskParticipator.getUser());
					}
				}
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
			return handlerList;
		}
		
		
		/**
		 * 完成竞争任务
		 *
		 */
		public boolean compUsersTaskByLoanBaseId(long loanBaseId,String UserCode,String path){
			boolean compFlag =false;
			//获得任务bean
			Task task = findTaskByLoanBaseId(String.valueOf(loanBaseId));
			if(task !=null){
				taskService.claim(task.getId(),UserCode);
				this.completeTask(task.getId(),path);
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
			return compFlag;
		}
		
		
		/**
		 * 完成竞争任务
		 *
		 */
		public boolean compUsersTaskByLoanBaseId(long loanBaseId,String UserCode,String path,TaskOpinion option){
			boolean compFlag =false;
			//获得任务bean
			Task task = findTaskByLoanBaseId(String.valueOf(loanBaseId));
			if(task !=null){
				taskService.claim(task.getId(),UserCode);
				taskService.start(task.getId());
				taskService.complete(task.getId(),path,option);
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
			return compFlag;
		}
		
		@Override
		public void bacthCompTaskByList(List<BMSLoanBase> successList, String serviceCode, String path) {
			for (BMSLoanBase bmsLoanBase : successList) {
				if(bmsLoanBase.getId() !=null){
					this.compUsersTaskByLoanBaseId(bmsLoanBase.getId(),serviceCode,path);
				}
				
			}
			
		}

		
		
		/**
		 * 认领任务
		 *
		 */
		public boolean claimUsersTaskByLoanBaseId(long loanBaseId,String UserCode){
			boolean compFlag =false;
			//获得任务bean
			Task task = findTaskByLoanBaseId(String.valueOf(loanBaseId));
			if(task !=null){
				taskService.claim(task.getId(),UserCode);
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
			return compFlag;
		}
		
		/**
		 * 批量认领任务
		 *
		 */
		public void bacthClaimUsersTask(List<BMSLoanBase> successList, String serviceCode, String path){
			for (BMSLoanBase bmsLoanBase : successList) {
				if(bmsLoanBase.getId() !=null){
					this.claimUsersTaskByLoanBaseId(bmsLoanBase.getId(),serviceCode);
				}
			}
		}
		
		
		/**
		 * 根据loanBaseId提交任务 并指定下一节点并添加流程变量
		 *
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void completeTaskByLoanBaseId(long loanBaseId,String path,Map varMap){
			//获得任务bean
			Task task = findTaskByLoanBaseId(String.valueOf(loanBaseId));
			if(task !=null){
				taskService.start(task.getId());
				taskService.complete(task.getId(), path, varMap); //完成任务
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
		}
		
		/**
		 * 根据loanBaseId提交任务 并指定下一节点并添加流程变量
		 *
		 */

		public void completeTaskByLoanBaseId(long loanBaseId,String path,TaskOpinion option){
			//获得任务bean
			Task task = findTaskByLoanBaseId(String.valueOf(loanBaseId));
			if(task !=null){
				taskService.start(task.getId());
				taskService.complete(task.getId(), path, option); //完成任务
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
		}
		
		

		@Override
		public void completeTaskByLoanBaseId(Long id, String path, Map<String, Object> variables,
				TaskOpinion option) {
			Task task = findTaskByLoanBaseId(String.valueOf(id));
			if(task !=null){
				taskService.start(task.getId());
				taskService.complete(task.getId(), path,variables, option); //完成任务
			}else{
				throw new BizException(BizErrorCode.UFLOWORKFLOW_EOERR, "未找到相应的任务");
			}
			
		}

		
		/**
		 * 提交任务 
		 */
		@Override
		public void completeTask(long taskId) {
			taskService.start(taskId);
			taskService.complete(taskId);

		}
		
		
		/**
		 * 根据ID查找任务
		 */
		@Override
		public Task findTaskById(long taskId) {
			Task task=taskService.getTask(taskId);
			return task;
		}

		
		/**
		 * 根据loan_base_id查找任务 获取task
		 */
		@Override
		public Task findTaskByLoanBaseId(String loanBaseId) {
			Task task=null;
			TaskQuery taskQ=taskService.createTaskQuery();
			taskQ.businessId(loanBaseId);
			List<Task> list= taskQ.list();
			if(list !=null && list.size()>0){
				task= list.get(0);
			}
			return task;
		}


		@Override
		public void changeTaskAssigneeByLoanBaseId(String loanBaseId, String userCode) {
			Task task=null;
			TaskQuery taskQ=taskService.createTaskQuery();
			taskQ.businessId(loanBaseId);
			List<Task> list= taskQ.list();
			if(list !=null && list.size()>0){
				task= list.get(0);
			}
			taskService.changeTaskAssignee(task.getId(),userCode);
		}

}
