package com.ymkj.cms.biz.test.facade.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSAppPersonInfoExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSAppPersonInfoVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSAppPersonInfoVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLoanLog {
	// json 工具类
		private Gson gson = new Gson();
		
		@Autowired
		private IBMSAppPersonInfoExecuter iBMSAppPersonInfoExecuter;
		
		@Test
		public void getListPage(){
			try {
				ReqBMSAppPersonInfoVO reqBMSAppPersonInfoVO=new ReqBMSAppPersonInfoVO();
				reqBMSAppPersonInfoVO.setSysCode("11111111111111");
				reqBMSAppPersonInfoVO.setLoanNo("20170525161440623211");
				Response<ResBMSAppPersonInfoVO> response=iBMSAppPersonInfoExecuter.updateByLoanNo(reqBMSAppPersonInfoVO);
				System.out.println(gson.toJson(response));
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
}
