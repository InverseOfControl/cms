package com.ymkj.cms.web.boss.service.process.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.query.TaskQuery;
import com.bstek.uflo.service.TaskService;
import com.ymkj.cms.web.boss.service.process.ITaskService;


@Service
public class TaskServiceImpl implements ITaskService{
	
	@Resource(name=TaskService.BEAN_ID)
	private  TaskService taskService;
	
		/**
		 * 查询待办
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
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
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		public void completeTask(long taskId,String path){
			//获得任务bean
			taskService.start(taskId);
			taskService.complete(taskId, path); //完成任务
		}

		
		/**
		 * 提交任务 
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		@Override
		public void completeTask(long taskId) {
			taskService.start(taskId);
			taskService.complete(taskId);

		}
		
		
		/**
		 * 根据ID查找任务
		 * @param req
		 * @param resp
		 * @throws ServletException
		 * @throws IOException
		 */
		@Override
		public Task findTaskById(long taskId) {
			Task task=taskService.getTask(taskId);
			return task;
		}

}
