package  com.ymkj.cms.biz.process.handler.assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.variable.Variable;
import com.bstek.uflo.process.handler.AssignmentHandler;
import com.bstek.uflo.process.node.TaskNode;
import com.ymkj.cms.biz.service.process.IProcessService;

@Component
public class BMSContractConfirmHandler implements AssignmentHandler{
	@Autowired
	private IProcessService processService;
	@Override
	public Collection<String> handle(TaskNode taskNode, ProcessInstance processInstance, Context context) {	
	String branchAssistantManager=null;
	long processId=	processInstance.getId();
	List<Variable> variables= processService.getProcessVariables(processId);
	for (Variable variable : variables) {
		if("branchAssistantManager".equals(variable.getKey())){
			branchAssistantManager =	(String) variable.getValue();
		}
	}
	List<String> managerList =new ArrayList<String>();
	managerList.add(branchAssistantManager);
	return managerList;
	}
}
