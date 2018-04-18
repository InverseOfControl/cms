package com.ymkj.cms.biz.test.facade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.IDemoExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class AppTest {
	
	@Reference(version="1.0.0")
	private IDemoExecuter demoExecuter;
	
	
	@Test
	public void dubboTest(){
		// 请求参数
		ReqDemoVO reqDemo = new ReqDemoVO("1111111");
		reqDemo.setName("teset1");
		// 调用 soa 接口
		Response<ResDemoVO> response = demoExecuter.findByName(reqDemo);
		// 返回结果处理
		ResDemoVO resDemoVO = response.getData();
		System.out.println(resDemoVO.getName());


		System.out.println("kakkaaka");

	}
	
	public static void main(String[] args) {
		
	}
	
}
