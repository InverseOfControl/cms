package com.ymkj.cms.biz.test.facade;

import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.IApplyEnterExecuter;
import com.ymkj.cms.biz.api.service.apply.IPersonHistoryLoanExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateNameIdNoVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResPersonHistoryLoanVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class PersonHistoryLoanNoTest {
	// json 工具类
	private Gson gson = new Gson();
	private static Validator validator = null;

	// 请求实体
	
	private ReqDemoVO reqDemo = new ReqDemoVO("11111111");
	
	@Autowired
	private IPersonHistoryLoanExecuter iPersonHistoryLoanExecuter;
	
	@Test
	public void test() {
		PersonHistoryLoanVO personHistoryLoanVO =new PersonHistoryLoanVO();
		personHistoryLoanVO.setIdNo("420222199207118339");
		personHistoryLoanVO.setName("王二小");
		personHistoryLoanVO.setSysCode("111");
		Response<ResPersonHistoryLoanVO> mm=	iPersonHistoryLoanExecuter.loanInfo(personHistoryLoanVO);
		System.out.println("--------");
		System.out.println(mm.getData());
		
		
	}


}
