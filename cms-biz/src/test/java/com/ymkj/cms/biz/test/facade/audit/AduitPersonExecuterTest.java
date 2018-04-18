package com.ymkj.cms.biz.test.facade.audit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.IAduitPersonExecuter;
import com.ymkj.cms.biz.api.service.sign.aite.IAiTeLoanContractExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class AduitPersonExecuterTest {
	// json 工具类
	private Gson gson = new Gson();

	// 请求实体
	private ReqBMSAduitPersonVo reqBMSAduitPersonVo = new ReqBMSAduitPersonVo(EnumConstants.BMS_SYSCODE);

	// 请求实体
	
	@Autowired
	private IAduitPersonExecuter aduitPersonExecuter;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindAduitPersonInfo() {
//		reqBMSAduitPersonVo.setLoanNo("20170302100000005409");
		reqBMSAduitPersonVo.setLoanNo("20170302100000005401");
		Response<ResBMSAduitPersonVo> response = aduitPersonExecuter.findAduitPersonInfo(reqBMSAduitPersonVo);
		System.out.println("-------------结果: " + gson.toJson(response));
	}

}
