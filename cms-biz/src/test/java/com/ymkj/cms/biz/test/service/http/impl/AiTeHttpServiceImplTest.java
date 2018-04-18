package com.ymkj.cms.biz.test.service.http.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.common.http.HttpResponse;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.service.http.IAiTeHttpService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class AiTeHttpServiceImplTest {

	// json 工具类
	private Gson gson = new Gson();

	// 请求实体
	private ReqLoanContractSignVo reqLoanContractSignVo = new ReqLoanContractSignVo("11111111");

	// 请求实体
	private RequestVo requestVo = new RequestVo();
	
	@Autowired
	private IAiTeHttpService aiTeHttpService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTargetPushed() {
		fail("Not yet implemented");
	}

	@Test
	public void testTerminationLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCreditStandingAndRespectabilitySH() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIDCard() {
		//20170302100000005401 appno
		Map<String, String> rquestMap = new HashMap<String, String>();

		rquestMap.put("appNo", "20170302100000005401");
		rquestMap.put("subclass_sort", "B");
		// 身份证照片获取获取
		HttpResponse httpResponse = aiTeHttpService.getIDCard(rquestMap);
		System.out.println("-------------结果: " + gson.toJson(httpResponse));

	}

	@Test
	public void testUploadFile() {
		fail("Not yet implemented");
	}

}
