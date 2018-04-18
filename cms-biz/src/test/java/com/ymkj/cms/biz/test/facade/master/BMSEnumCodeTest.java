package com.ymkj.cms.biz.test.facade.master;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSEnumCodeExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSEnumCodeTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSEnumCodeVO reqDemo = new ReqBMSEnumCodeVO("11111111");
	
	@Autowired
	private IBMSEnumCodeExecuter demoExecuter;
	
	//@Test
	public void findById() {
		System.out.println("-------------test demo findById--------------");
		// 请求参数
		reqDemo.setCodeId(1L);
		// 调用 soa 接口
		Response<ResBMSEnumCodeVO> response = demoExecuter.findById(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
		if(response.isSuccess()){
			ResBMSEnumCodeVO resDemoVO = response.getData();
			System.out.println("-------------vo: "+gson.toJson(response));
		}
		
	}

	@Test
	public void listEnumCodeBy() {
		// 请求参数
		reqDemo.setCodeType("MaritalStatus");
		ResListVO<ResBMSEnumCodeVO> response = demoExecuter.listEnumCodeBy(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSEnumCodeVO resDemoVO = response.getData();
		
	}
	//@Test
	public void findEnumCodeByCondition(){
		reqDemo.setProductId(1L);
		ResListVO<ResBMSEnumCodeVO> response = demoExecuter.findEnumCodeByCondition(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSEnumCodeVO resDemoVO = response.getData();
	}
	
	@Test
	public void getProducAssetsInfo(){
		reqDemo.setProductId(27L);
		Response<List<ResBMSEnumCodeVO>> response = demoExecuter.getProducAssetsInfo(reqDemo);
	}
	
	@Test
	public void voidfindCodeByProfessionTest (){
		ReqBMSEnumCodeVO reqBMSEnumCodeVO=new ReqBMSEnumCodeVO();
		reqBMSEnumCodeVO.setCode("00002");
		reqBMSEnumCodeVO.setParentCode("00002");
		reqBMSEnumCodeVO.setSysCode("pms");
		Response<List<ResBMSEnumCodeVO>> list=demoExecuter.findCodeByProfession(reqBMSEnumCodeVO);
		System.out.println(list);
	}

	@Test
	public void voidfindServcenTest (){
		ReqBMSEnumCodeVO reqBMSEnumCodeVO=new ReqBMSEnumCodeVO();
		reqBMSEnumCodeVO.setCode("00002");
		reqBMSEnumCodeVO.setParentCode("00002");
		reqBMSEnumCodeVO.setSysCode("pms");
		Response<List<ResBMSEnumCodeVO>> list=demoExecuter.findByservenProfession(reqBMSEnumCodeVO);
		System.out.println(list);
	}
}
