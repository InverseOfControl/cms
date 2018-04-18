package com.ymkj.cms.biz.test.facade.job;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.audit.ISignReassignmentExecuter;
import com.ymkj.cms.biz.api.service.job.IBMSLoanJobExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqSignReassignVO;
import com.ymkj.cms.biz.api.vo.request.job.ReqLoanJobVO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSLoanJobExecuterTest {

	private Gson gson = new Gson();

	@Autowired
	private IBMSLoanJobExecuter loanJobExecuter;
	@Autowired
	private ISignReassignmentExecuter iSignReassignmentExecuter;
	
	// 请求实体
	private ReqLoanJobVO reqDemo = new ReqLoanJobVO();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testRecordTimeOutRefuseAPP() {
		reqDemo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		//app进件标志
//		reqDemo.setAppInputFlag("app_input");
		reqDemo.setAppInputFlagFalse("app_input");
		reqDemo.setTestLoanNo("20170710D030FD");
		
		
		Response<Object> response = loanJobExecuter.recordTimeOutRefuseAPP(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	@Test
	public void testSupplementTimeOutCancel() {
		reqDemo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		reqDemo.setTestLoanNo("219371");
		
		
		Response<Object> response = loanJobExecuter.supplementTimeOutCancel(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	@Test
	public void testSignTimeOutCancel() {
		reqDemo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		Response<Object> response = loanJobExecuter.signTimeOutCancel(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	@Test
	public void testBindCreditReport() {
		reqDemo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqDemo.setTestLoanNo("20170710D030FD");
		Response<Object> response = loanJobExecuter.bindCreditReport(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
	}

	
	@Test
	public void grantLoanUpdateByCore() {
		reqDemo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqDemo.setTestLoanNo("20170629094525497873");
		
		
		Response<Object> response = loanJobExecuter.grantLoanUpdateByCore(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
	}
	@Test
	public void recordLogoProcessing() {
		reqDemo.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		
		reqDemo.setTestLoanNo("20170425193404480251");
		
		Response<Object> response = loanJobExecuter.recordLogoProcessing(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));

	}

	@Test
	public void testGetIntervalDays() {
		fail("Not yet implemented");
	}
	@Test
	public void signReassign() {
		ReqSignReassignVO reqSignReassignVO = new ReqSignReassignVO();
		reqSignReassignVO.setSysCode("bms");
		iSignReassignmentExecuter.signReassign(reqSignReassignVO);
	}

}
