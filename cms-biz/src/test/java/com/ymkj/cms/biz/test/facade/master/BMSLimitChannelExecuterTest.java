package com.ymkj.cms.biz.test.facade.master;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSEnumCodeExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLimitChannelExecuterTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("11111111");
	
	@Autowired
	private IBMSLimitChannelExecuter demoExecuter;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testListLimitChannelBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testListPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveProductLimit() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateChannelLimit() {
		reqDemo.setId(58L);
		reqDemo.setIsDisabled(1);
//		reqDemo.setIsDisabled(0);
		reqDemo.setIsDeleted(1L);
		reqDemo.setChannelId(1L);
		
		Response<ResBMSOrgLimitChannelVO> response = demoExecuter.updateChannelLimit(reqDemo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	@Test
	public void testLogicalDelete() {
		fail("Not yet implemented");
	}

}
