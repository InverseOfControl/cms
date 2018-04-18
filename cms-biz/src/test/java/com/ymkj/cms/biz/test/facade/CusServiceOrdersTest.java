package com.ymkj.cms.biz.test.facade;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.cms.biz.api.service.apply.ICusServiceOrdersExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCusServiceOrdersOperationVo;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCusServiceOrdersSearchVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class CusServiceOrdersTest {
	
	@Autowired
	private ICusServiceOrdersExecuter iCusServiceOrdersExecuter;
	
	
	@Test
	public void search(){
		ReqCusServiceOrdersSearchVO vo= new ReqCusServiceOrdersSearchVO();
		vo.setSysCode("1111");
		
		vo.setManageBranchCode("10"); //机构ID
		vo.setStaffCode("");		//员工ID
		vo.setUserName("");			//员工姓名
		vo.setIfAccept("Y");			//是否接单	Y:允许接单 N:禁止接单  传空默认查全量
		
		List<String> listRole = new ArrayList<String>();
		listRole.add("director");
		vo.setRoleCodes(listRole);
		
		iCusServiceOrdersExecuter.search(vo);
	}
	
	
	@Test
	public void enableOrClose(){
		ReqCusServiceOrdersOperationVo vo = new ReqCusServiceOrdersOperationVo();
		vo.setSysCode("11111");
		
		List<String> list = new ArrayList<String>();
		list.add("00210025");
		
		vo.setUserIdList(list);
		vo.setIfAccept("N");
		vo.setServiceCode("JWC0001");
		vo.setServiceName("李四");
		iCusServiceOrdersExecuter.enableOrClose(vo);
	}
	
	
}
