package com.ymkj.cms.biz.test.facade.master;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.service.master.IBMSBankExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSBankTest {
	// json 工具类
		private Gson gson = new Gson();
			
	    // 请求实体
		private ReqBMSBankVO reqDemo = new ReqBMSBankVO("11111111");
			
		@Autowired
		private IBMSBankExecuter bankDemoTest;
		
		@Test
		public void getAllList() {
			//中国进出口银行
			reqDemo.setName("中国");
			ResListVO<ResBMSBankVO> response = bankDemoTest.getAllBank(reqDemo);
			System.out.println(response);
			//reqDemo.setChannelId(27L);
			// 调用 soa 接口
			//ResListVO<ResBMSBankVO> response = bankDemoTest.getBankByChannelId(reqDemo);
			
			//System.out.println("-------------结果: "+gson.toJson(response));
			// 返回结果处理
			
//			if(response.isSuccess()){
//				List<ResBMSBankVO> arrayList = response.getCollections();
//				for(ResBMSBankVO c : arrayList) {
//					System.out.println(c.getName());
//				}
//				System.out.println("-------------vo: "+gson.toJson(response));
//			}
		}
		
		
		//@Test
		public void listPage(){
			reqDemo.setPage(1);
			reqDemo.setRows(2);
			/*PageResponse<ResBMSOrgLimitChannelVO> response = demoExecuter.listPage(reqDemo);*/
			PageResponse<ResBMSBankVO> pageResult = bankDemoTest.listPage(reqDemo);
			
			System.out.println("-------------结果: "+gson.toJson(pageResult));
			// 返回结果处理
			ResBMSBankVO resDemoVO = pageResult.getData();
			/*System.out.println(resDemoVO.getCode());*/
		}

}
