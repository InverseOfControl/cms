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
import com.ymkj.cms.biz.api.service.master.IBMSProductExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSProductVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSProductTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSProductVO reqDemo = new ReqBMSProductVO("11111111");
	
	@Autowired
	private IBMSProductExecuter demoExecuter;
	
	//@Test
	public void findById() {
		System.out.println("-------------test demo findById--------------");
		// 请求参数
		reqDemo.setProductId(1L);
		// 调用 soa 接口
		Response<ResBMSProductVO> response = demoExecuter.findById(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
		if(response.isSuccess()){
			ResBMSProductVO resDemoVO = response.getData();
			System.out.println("-------------vo: "+gson.toJson(response));
		}
		
	}

	//@Test
	public void findByName() {
		// 请求参数
		reqDemo.setName("随");
		Response<ResBMSProductVO> response = demoExecuter.findByName(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSProductVO resDemoVO = response.getData();
		
	}
	//@Test
	public void listProByCondition(){
		reqDemo.setOrgId(50134L);
		
		ResListVO<ResBMSProductVO> response = demoExecuter.listProByCondition(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSProductVO resDemoVO = response.getData();
	}
	
	//@Test
	public void listBy(){
		ResListVO<ResBMSProductVO> response = demoExecuter.listBy(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSProductVO resDemoVO = response.getData();
	}
	
	//@Test
	public void findPage() {
		reqDemo.setPageNum(1);
		reqDemo.setPageSize(2);
		PageResponse<ResBMSProductVO> response = demoExecuter.listPage(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		List<ResBMSProductVO> resDemoVO =  response.getRecords();
		System.out.println(resDemoVO);
		
	}
	@Test
	public void findProByChannelId(){
		reqDemo.setChannelId(1L);
		
		ResListVO<ResBMSProductVO> response = demoExecuter.findProByChannelId(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSProductVO resDemoVO = response.getData();
	}

	@Test
	public void findByOrgId(){
		ReqBMSProductVO reqBMSProductVO=new ReqBMSProductVO();
		reqBMSProductVO.setSysCode("cfs");
		reqBMSProductVO.setOrgId(12827295L);
		
		ResListVO<ResBMSProductVO> response = demoExecuter.findByOrgId(reqBMSProductVO);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSProductVO resDemoVO = response.getData();
	}
	
}
