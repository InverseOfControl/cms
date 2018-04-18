package  com.ymkj.cms.web.boss.processdeploy.provider.handler.assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.AssignmentHandler;
import com.bstek.uflo.process.node.TaskNode;

@Component
public class BMSContractSignHandler implements AssignmentHandler{
	
	@Override
	public Collection<String> handle(TaskNode taskNode, ProcessInstance processInstance, Context context) {		
		//调用权限系统查询可以进行签约客服人员
		List<String> ServiceList  = new ArrayList<String>();
		ServiceList.add(processInstance.getPromoter());
		return ServiceList;
	}
}
