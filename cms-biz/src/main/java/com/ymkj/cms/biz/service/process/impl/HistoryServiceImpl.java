package com.ymkj.cms.biz.service.process.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bstek.uflo.model.HistoryActivity;
import com.bstek.uflo.model.HistoryProcessInstance;
import com.bstek.uflo.model.HistoryTask;
import com.bstek.uflo.query.HistoryProcessInstanceQuery;
import com.bstek.uflo.query.HistoryTaskQuery;
import com.bstek.uflo.service.HistoryService;
import com.ymkj.cms.biz.service.process.IHistoryService;

@Service
public class HistoryServiceImpl implements IHistoryService{
	@Resource(name=HistoryService.BEAN_ID)
	private HistoryService historyService;

	@Override
	public List<HistoryActivity> getHistoryActivitysByProcesssInstanceId(long processInstanceId) {
		
		return historyService.getHistoryActivitysByHistoryProcesssInstanceId(processInstanceId);
	}

	@Override
	public List<HistoryActivity> getHistoryActivitysByHistoryProcesssInstanceId(long historyProcessInstanceId) {
		
		return historyService.getHistoryActivitysByHistoryProcesssInstanceId(historyProcessInstanceId);
	}

	@Override
	public List<HistoryProcessInstance> getHistoryProcessInstances(long processId) {
	
		return historyService.getHistoryProcessInstances(processId);
	}

	@Override
	public HistoryProcessInstance getHistoryProcessInstance(long processInstanceId) {
		
		return historyService.getHistoryProcessInstance(processInstanceId);
	}

	@Override
	public List<HistoryTask> getHistoryTasks(long processInstanceId) {

		return historyService.getHistoryTasks(processInstanceId);
	}

	@Override
	public HistoryTask getHistoryTask(long taskId) {
		
		return historyService.getHistoryTask(taskId);
	}

	@Override
	public HistoryTaskQuery createHistoryTaskQuery() {
		
		return historyService.createHistoryTaskQuery();
	}

	@Override
	public HistoryProcessInstanceQuery createHistoryProcessInstanceQuery() {
		
		
		// TODO Auto-generated method stub
		return historyService.createHistoryProcessInstanceQuery();
	}
	
	
}
