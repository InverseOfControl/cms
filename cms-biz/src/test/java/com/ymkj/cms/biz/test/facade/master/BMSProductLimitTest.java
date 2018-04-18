package com.ymkj.cms.biz.test.facade.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSProductAuditLimitExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSProductLimitTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSProductAuditLimitVO reqDemo = new ReqBMSProductAuditLimitVO("cfs");
	
	@Autowired
	private IBMSProductAuditLimitExecuter demoExecuter;
	
	//@Test
	public void findById() {
		System.out.println("-------------test demo findById--------------");
		// 请求参数
		reqDemo.setAuditLimitId(1L);
		// 调用 soa 接口
		Response<ResBMSProductAuditLimitVO> response = demoExecuter.findById(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
		if(response.isSuccess()){
			ResBMSProductAuditLimitVO resDemoVO = response.getData();
			System.out.println("-------------vo: "+gson.toJson(response));
		}
		
	}
/*
	@Test
	public void findListBy() {
		// 请求参数
		reqDemo.setChannelId(27L);
		reqDemo.setUserCode("userCode");
		ResListVO<ResBMSProductAuditLimitVO> response = demoExecuter.findLimitByChaIdUserCode(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSProductAuditLimitVO resDemoVO = response.getData();
		
	}*/
	@Test
	public void findListByOrgId() {
		// 请求参数
		//reqDemo.setChannelId(1L);
		//reqDemo.setOrgId(12827295L);
		//reqDemo.setProductCode("00013");
		 demoExecuter.findLimitByChaIdUserCode(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(	 demoExecuter.findLimitByChaIdUserCode(reqDemo)));
		// 返回结果处理
		//ResBMSProductAuditLimitVO resDemoVO = response.getData();
		
	}
	

	
	
	
}
