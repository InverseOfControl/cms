package com.ymkj.cms.biz.test.facade.master;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSOrgLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSOrgLimitChannelTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("11111111");
	
	@Autowired
	private IBMSOrgLimitChannelExecuter demoExecuter;
	

	//@Test
	public void findListBy() {
		// 请求参数
		reqDemo.setOrgId(50134L);
		//reqDemo.setAuditLimitId(22L);
		//reqDemo.setChannelId(11L);
		Response<ResProductBaseListVO> response = demoExecuter.listOrgLimitChannelBy(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResProductBaseListVO resDemoVO = response.getData();
		
	}
	
	//@Test
	public void findPage() {
		reqDemo.setPage(1);
		reqDemo.setRows(2);
		PageResponse<ResBMSOrgLimitChannelVO> response = demoExecuter.listPage(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		List<ResBMSOrgLimitChannelVO> resDemoVO =  response.getRecords();
		System.out.println(resDemoVO);
		
	}
	
	//@Test
	public void isDisable() {
		reqDemo.setOrgId(12827295L);
		reqDemo.setProductCode("00001");
		
		Response<ResBMSOrgLimitChannelVO> response = demoExecuter.isDisabled(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
	
		System.out.println(response.getRepMsg());
		
	}
	
	@Test
	public void findFULimit() {
		reqDemo.setPage(1);
		reqDemo.setRows(2);
		PageResponse<ResBMSOrgLimitChannelVO> response = demoExecuter.listPage(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		List<ResBMSOrgLimitChannelVO> resDemoVO =  response.getRecords();
		System.out.println(resDemoVO);
		
	}

	
}
