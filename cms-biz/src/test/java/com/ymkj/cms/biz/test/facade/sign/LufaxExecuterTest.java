package com.ymkj.cms.biz.test.facade.sign;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.cms.biz.api.service.apply.IFileFormExecuter;
import com.ymkj.cms.biz.api.service.sign.ILufaxExecuter;
import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLufax800001Vo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLujsInformVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class LufaxExecuterTest {

	private Gson gson = new Gson();
	
	// 请求实体
	private ReqLujsInformVo reqLoanVo = new ReqLujsInformVo();

	@Autowired
	private ILufaxExecuter lufaxExecuter;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInNoticeExternal() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindlujsInform() {
		reqLoanVo.setSysCode("bms");
		reqLoanVo.setLoanNo("201707136F8EEA");
		reqLoanVo.setNotifyType("1");
		lufaxExecuter.findlujsInform(reqLoanVo);
	}
	@Test
	public void getAuditReturnResult() {
		reqLoanVo.setSysCode("cfs");
		reqLoanVo.setLoanNo("201707132B6DC91");
		reqLoanVo.setCurrentTaskNode("LUJS_MANUAL_AUDIT");
		lufaxExecuter.dealWithAuditResult(reqLoanVo);
	}
	@Test
	public void inNoticeExternal() {
		ReqLufax800001Vo lufax800001Vo = new ReqLufax800001Vo();
		lufax800001Vo.setSysCode("bms");
		lufax800001Vo.setApply_no("ZDJR_201710179457DB");
		lufax800001Vo.setNotify_code("004");
		lufax800001Vo.setNotify_desc("通过");
		lufax800001Vo.setAps_apply_no("WD001CCCCCZDJR_2017071317408BAAAAAAAAAAA");
		lufax800001Vo.setNotify_type("反欺诈");
		lufax800001Vo.setDept_code("1");
		lufax800001Vo.setProduct_code("1");
		lufax800001Vo.setProject_code("1");
		lufaxExecuter.inNoticeExternal(lufax800001Vo);
	}

}
