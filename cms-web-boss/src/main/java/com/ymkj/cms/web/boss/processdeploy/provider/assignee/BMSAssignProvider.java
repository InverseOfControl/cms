package com.ymkj.cms.web.boss.processdeploy.provider.assignee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
@Component
public class BMSAssignProvider implements AssigneeProvider{

	@Override
	public boolean disable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BMS角色";
	}

	@Override
	public Collection<String> getUsers(String entityId, Context arg1, ProcessInstance arg2) {
		List<String> list=new ArrayList<String>();
		list.add(entityId);
		return list;
	}

	@Override
	public boolean isTree() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void queryEntities(PageQuery<Entity> arg0, String arg1) {
		List<Entity> entitys= new ArrayList<Entity>();
		Entity user= new Entity("custMan","客户1");
		
		Entity user1= new Entity("custSer1","客服1");
		Entity user2= new Entity("custSer2","客服2");
		Entity user3= new Entity("custSer3","客服3");
		
		Entity manager1= new Entity("manager1","经理1");
		Entity manager2= new Entity("manager2","经理2");
		Entity manager3= new Entity("manager3","经理3");

		Entity user5= new Entity("checkman","复核员");
		//Entity user6= new Entity("moneyman","放款员");
		
		entitys.add(user);

		//entitys.add(user6);
		entitys.add(user5);
		entitys.add(user3);
		entitys.add(user2);
		entitys.add(user1);
		
		entitys.add(manager1);
		entitys.add(manager2);
		entitys.add(manager3);
		arg0.setRecordCount(entitys.size());
		arg0.setResult(entitys);
		
	}

}
