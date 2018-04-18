package com.ymkj.cms.biz.test.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.audit.ISignReassignmentExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqSignReassignVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ISignReassignmentExecuterTest {
	
	private Gson gson = new Gson();

	@Autowired
	private ISignReassignmentExecuter iSignReassignmentExecuter;
	
	@Test
	public void signReassign() {
		
		ReqSignReassignVO reqSignReassignVO = new ReqSignReassignVO();
		
		Response<Object> response = iSignReassignmentExecuter.signReassign(reqSignReassignVO);
		
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}
}
