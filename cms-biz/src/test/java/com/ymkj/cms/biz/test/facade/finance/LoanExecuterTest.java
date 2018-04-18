package com.ymkj.cms.biz.test.facade.finance;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.finance.ILoanExecuter;
import com.ymkj.cms.biz.api.service.job.IBMSLoanJobExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.job.ReqLoanJobVO;
import com.ymkj.cms.biz.api.vo.response.finance.ResLoanVo;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.exception.BizException;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class LoanExecuterTest {

	private Gson gson = new Gson();

	@Autowired
	private ILoanExecuter loanExecuter;
	
	// 请求实体
	private ReqLoanVo reqLoanVo = new ReqLoanVo();
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPassAuditLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testBacthPassAudit() {
		fail("Not yet implemented");
	}

	@Test
	public void testBackAudit() {
		reqLoanVo.setPageNum(1);
		reqLoanVo.setPageSize(1);
		reqLoanVo.setServiceCode("00214054");
		reqLoanVo.setServiceName("郎玖申");
		reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.FKSHRETURN.getValue());
		reqLoanVo.setRtfState(EnumConstants.RtfState.FKSH.getValue());
		reqLoanVo.setLoanNo("20171019F86D5E");
		reqLoanVo.setChannelCd("00016");
		reqLoanVo.setId(1454480L);
		reqLoanVo.setIp("172.0.0.1");
		reqLoanVo.setFirstLevleReasonsCode("RM99999999");
		reqLoanVo.setFirstLevleReasons("其他");
		reqLoanVo.setSysCode(EnumConstants.CMS_SYSTEM_CODE);
		Response<ResLoanVo> response = loanExecuter.backAudit(reqLoanVo);
		
		System.out.println("-------------结果: "+gson.toJson(response));

	}

	@Test
	public void testGrantLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testBackLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testBacthBackLoanAudit() {
		fail("Not yet implemented");
	}

	@Test
	public void testBacthBackLoanConfirm() {
		fail("Not yet implemented");
	}

	@Test
	public void testListPage() {
		reqLoanVo.setPageNum(1);
		reqLoanVo.setPageSize(1);
		reqLoanVo.setServiceCode("00214054");
		reqLoanVo.setRtfNodeState(EnumConstants.RtfNodeState.HTQRSUBMIT.getValue());
		reqLoanVo.setRtfState(EnumConstants.RtfState.FKSH.getValue());
		reqLoanVo.setSysCode("bms");
		Response<ResLoanVo> response = loanExecuter.listPage(reqLoanVo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
	}

	@Test
	public void testFindLoanExportInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoneListPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testValiProductIsDisabled() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuditCommit() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindLoanIdbyFeeInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryUserLoanInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryLoanCoreState() {
		fail("Not yet implemented");
	}

}
