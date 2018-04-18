package com.ymkj.cms.biz.test.facade.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.cms.biz.api.service.master.IBMSContractChangeExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSContractChangeTest {
	
	private ReqBMSContractChangeVo reqVo;
	
	@Autowired
	private IBMSContractChangeExecuter iBMSContractChangeExecuter;
	
	@Test
	public void queryTaskNumberQues() {
		reqVo = new ReqBMSContractChangeVo();
		reqVo.setSysCode("bms");
		iBMSContractChangeExecuter.listPage(reqVo);
	}

}
