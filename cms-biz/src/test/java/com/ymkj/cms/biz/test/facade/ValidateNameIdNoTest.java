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
import com.ymkj.cms.biz.api.vo.request.apply.ReqDemoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateNameIdNoVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ValidateNameIdNoTest {
	// json 工具类
	private Gson gson = new Gson();
	private static Validator validator = null;

	// 请求实体
	
	private ReqDemoVO reqDemo = new ReqDemoVO("11111111");
	
	@Autowired
	private IApplyEnterExecuter iApplyEnterExecuter;
	
	@Test
	public void validateNameIdNo() {
		
		ValidateNameIdNoVO validateNameIdNoVO =new ValidateNameIdNoVO();
		validateNameIdNoVO.setIdNo("14010919841221007X");
		validateNameIdNoVO.setName("王二小");
		validateNameIdNoVO.setOwningBranch("上海营业部");
		validateNameIdNoVO.setOwningBranchId(new Long(0)) ;
		validateNameIdNoVO.setSysCode("111");
		Response<Object> mm=	iApplyEnterExecuter.validateNameIdNo(validateNameIdNoVO);
		System.out.println("--------");
		System.out.println(mm.getData());
		
		
	}


}
