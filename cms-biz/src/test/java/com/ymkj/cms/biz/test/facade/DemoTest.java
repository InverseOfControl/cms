package com.ymkj.cms.biz.test.facade;

import javax.validation.Validator;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IDemoExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResDemoVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class DemoTest {
	// json 工具类
	private Gson gson = new Gson();
	private static Validator validator = null;

	// 请求实体
	private ReqDemoVO reqDemo = new ReqDemoVO("11111111");
	
	@Autowired
	private IDemoExecuter demoExecuter;
	
//	@Test
	public void findById() {
		System.out.println("-------------test demo findById--------------");
		// 请求参数
		reqDemo.setId(17l); 
		// 调用 soa 接口
		Response<ResDemoVO> response = demoExecuter.findById(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
		if(response.isSuccess()){
			ResDemoVO resDemoVO = response.getData();
			System.out.println("-------------vo: "+gson.toJson(response));
		}
		
	}

//	@Test
	public void findByName() {
		// 请求参数
//		reqDemo.setName("test1");
		// 调用 soa 接口
		reqDemo.setName("er");
		reqDemo.setAddress("sdfsdf");
		Response<ResDemoVO> response = demoExecuter.findByName(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResDemoVO resDemoVO = response.getData();
		
	}

	//@Test
	public void listPage() {
		// 请求参数
		reqDemo.setName("hahahmmmm");
		reqDemo.setPageNum(1);
		reqDemo.setPageSize(2);
		// 调用 soa 接口
		PageResponse<ResDemoVO> response = demoExecuter.listPage(reqDemo);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	@Test
	public void saveOrUpdate() {
		
		// 请求参数
		reqDemo.setId(21l);
		reqDemo.setName("pppm");
		reqDemo.setAddress("上海123");
		//reqDemo.setStatus(EnumConstants.CHECK.getValue());
		// 调用 soa 接口
		Response<Object> response = demoExecuter.saveOrUpdate(reqDemo);
		// 返回结果处理
		System.out.println("-------------结果: "+gson.toJson(response));
		System.out.println("--------1--------");
		System.out.println("---------2--------");
	}
}
