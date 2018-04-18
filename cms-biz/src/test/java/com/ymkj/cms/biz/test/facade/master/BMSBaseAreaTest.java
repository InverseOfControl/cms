package com.ymkj.cms.biz.test.facade.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.service.master.IBMSBaseAreaExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaTreeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBaseAreaVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSBaseAreaTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSBaseAreaVO reqDemo = new ReqBMSBaseAreaVO("11111111");
	
	@Autowired
	private IBMSBaseAreaExecuter demoExecuter;
	
	//@Test
	public void findById() {
		System.out.println("-------------test demo findById--------------");
		// 请求参数
		reqDemo.setAreaId(1L);
		// 调用 soa 接口
		Response<ResBMSBaseAreaVO> response = demoExecuter.findById(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
		if(response.isSuccess()){
			ResBMSBaseAreaVO resDemoVO = response.getData();
			System.out.println("-------------vo: "+gson.toJson(response));
		}
		
	}

	//@Test
	public void findByName() {
		// 请求参数
		reqDemo.setName("苏");
		reqDemo.setDeep(1L);
		ResListVO<ResBMSBaseAreaVO> response = demoExecuter.findByName(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSBaseAreaVO resDemoVO = response.getData();
		
	}
	
	//:[{"parentId":2396,"deep":3,"pageNum":0,"pageSize":0,"sysCode":"cfs"}]
	@Test
	public void listBy(){
		reqDemo.setParentId(2396L);
		reqDemo.setDeep(3L);
		ResListVO<ResBMSBaseAreaVO> response = demoExecuter.listBy(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResBMSBaseAreaVO resDemoVO = response.getData();
	}
	
	
	//@Test
	public void deleteAll(){
		
		demoExecuter.deletelAllBaseArea(reqDemo);
		/*System.out.println(long.class.getName());
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		ResProductBaseListVO resDemoVO = response.getData();
		if (response.isSuccess()) {
			System.out.println(Integer.valueOf(response.getRepMsg()));
		} else {
			System.out.println("-------------结果: "+gson.toJson(response));
		}
		System.out.println("-------------结果: "+gson.toJson(response));*/
	}
	
	
	//@Test
	public void addBaseArea(){
		ReqBMSBaseAreaVO reqBmsBaseAreaVO=new ReqBMSBaseAreaVO();
		String arr[] = "110000=北京市".split("=");
		reqBmsBaseAreaVO.setAreaId(new Long((long)1));
		reqBmsBaseAreaVO.setName(arr[1]);
		reqBmsBaseAreaVO.setCode(arr[0]);
		reqBmsBaseAreaVO.setParentId(new Long((long)0));
		reqBmsBaseAreaVO.setDeep(new Long((long)1));
		reqBmsBaseAreaVO.setAreaId(new Long((long)0));
		reqBmsBaseAreaVO.setCode("110102");
		reqBmsBaseAreaVO.setName("西城区");
		reqBmsBaseAreaVO.setDeep(new Long((long)3));
		Response<ResBMSBaseAreaVO> response =demoExecuter.addBaseArea(reqBmsBaseAreaVO);
		// 响应结果处理, 如果失败 则抛出 处理失败异常
		if (response.isSuccess()) {
			System.out.println(Integer.valueOf(response.getRepMsg()));
		} else {
			System.out.println("-------------结果: "+gson.toJson(response));
		}
		// 返回结果处理
		/*ResProductBaseListVO resDemoVO = response.getData();*/
	}
	
	
	//@Test
	public void deleteById() {
		System.out.println("-------------test demo deleteById--------------");
		// 请求参数
		reqDemo.setAreaId((long) 1);
		// 调用 soa 接口
		Response<ResBMSBaseAreaVO> response = demoExecuter.deleteById(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
		
		if(response.isSuccess()){
			ResBMSBaseAreaVO resDemoVO = response.getData();
			System.out.println("-------------vo: "+gson.toJson(response));
		}
		
	}
	
	
	@Test
	public void listByTree() {
		System.out.println("-------------test demo deleteById--------------");
		// 请求参数
		reqDemo.setAreaId((long) 1);
		reqDemo.setDeep(new Long("1"));
		// 调用 soa 接口
		Response<ResBMSBaseAreaTreeVO> response = demoExecuter.listByTree(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		// 返回结果处理
	}
		

	
}
