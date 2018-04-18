package com.ymkj.cms.biz.test.facade.sign;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractNoticeExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class AiTeLoanContractNoticeExecuterTest {
	
	private Gson gson = new Gson();
	// 请求实体
	private RequestVo requestVo = new RequestVo();
		
	@Autowired
	private IAiTeLoanContractNoticeExecuter loanContractNoticeExecuter;
	
	@Test
	public void bidSuccessNotice() {
		requestVo.setBorrowNo("20170303165731168805");
		Response<RequestVo> response = loanContractNoticeExecuter.bidSuccessNotice(requestVo);
		System.out.println("-------------结果: "+gson.toJson(response));
	}

}
