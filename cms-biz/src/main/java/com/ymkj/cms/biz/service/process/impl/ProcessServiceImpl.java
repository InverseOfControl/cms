package com.ymkj.cms.biz.service.process.impl;
import com.bstek.uflo.model.ProcessDefinition;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.variable.Variable;
import com.bstek.uflo.query.ProcessInstanceQuery;
import com.bstek.uflo.query.ProcessQuery;
import com.bstek.uflo.query.ProcessVariableQuery;
import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.ymkj.cms.biz.service.process.IProcessService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class ProcessServiceImpl implements IProcessService{
	
	@Resource(name=ProcessService.BEAN_ID)
	private ProcessService processService;

	/**
	 * 开始流程
	 */
	public void startProcessByName(String proname,StartProcessInfo startProcessInfo){
		
		processService.startProcessByName(proname, startProcessInfo);
    }

	@Override
	public void saveProcessVariable(long processInstanceId, String key, Object value) {
		processService.saveProcessVariable( processInstanceId,  key,  value);
		
	}

	@Override
	public void saveProcessVariables(long processInstanceId, Map<String, Object> variables) {
		
		processService.saveProcessVariables(processInstanceId, variables);
	}

	@Override
	public boolean deleteProcessInstanceByLoanBaseId(String bussnesId) {
		boolean dflag= false;
		ProcessInstance processInstance= null;
		ProcessInstanceQuery pQuery =processService.createProcessInstanceQuery().businessId(bussnesId);
		List<ProcessInstance> list= pQuery.list();
		if(list !=null && list.size()>0){
			processInstance=list.get(0);
			processService.deleteProcessInstance(processInstance);
		}
		dflag= true;
		return dflag;
	}

	@Override
	public ProcessVariableQuery createProcessVariableQuery() {
		
		return processService.createProcessVariableQuery();
	}

	@Override
	public ProcessQuery createProcessQuery() {
		
		return processService.createProcessQuery();
	}

	@Override
	public String getChannelCdById(String bussnesId) {
		ProcessInstanceQuery pQuery=processService.createProcessInstanceQuery().businessId(bussnesId);
		ProcessInstance processInstance= null;
		List<ProcessInstance> list= pQuery.list();
		ProcessDefinition processDefinition =null;
		if(list !=null && list.size()>0){
			processInstance=list.get(0);
			long processId=	processInstance.getProcessId();
			ProcessQuery processQuery=	processService.createProcessQuery().id(processId);
			List<ProcessDefinition> list2= processQuery.list();
			if(list !=null && list.size()>0){
				processDefinition=list2.get(0);
			}
		}
		return processDefinition==null?"":processDefinition.getName();
	}

	@Override
	public List<Variable> getProcessVariables(long processId) {
		return processService.getProcessVariables(processId);
	}

	@Override
	public void saveVariableByLoanBseId(long loanBaseId, Map<String, Object> variables) {
		ProcessInstance processInstance =null;
		ProcessInstanceQuery pQuery=processService.createProcessInstanceQuery().businessId(String.valueOf(loanBaseId));
		List<ProcessInstance> list= pQuery.list();
		if(list !=null && list.size()>0){
			processInstance=list.get(0);
			processService.saveProcessVariables(processInstance.getId(), variables);
		}	
	}

	/**
	 * 获取流程的key值
	 * @param processId
	 * @return
	 */
    @Override
    public String getProcessKey(long processId) {
        return processService.getProcessById(processId).getKey();
    }


}
