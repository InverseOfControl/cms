package com.ymkj.cms.biz.test.facade.apply;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IApplyValidateExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqValidateVo;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateNameIdNoVO;
import com.ymkj.rule.biz.api.message.MapResponse;
import com.ymkj.rule.biz.api.message.RuleEngineRequest;
import com.ymkj.rule.biz.api.service.IRuleEngineExecuter;
import com.ymkj.rule.biz.api.vo.ApplyRuleBatchExecVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ApplyValidateExecuterTest {

	// json 工具类
	private Gson gson = new Gson();

	// 请求实体
	private ApplyEntryVO applyEntryVO = new ApplyEntryVO(EnumConstants.BMS_SYSCODE);

	// 请求实体
	
	@Autowired
	private IApplyValidateExecuter applyValidateExecuter;
	@Autowired
	private IRuleEngineExecuter ruleEngineExecuter;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidateNameIdNo() {
		ValidateNameIdNoVO validateVo = new ValidateNameIdNoVO();
		validateVo.setSysCode(EnumConstants.BMS_SYSCODE);
		
		validateVo.setName("张三");
		validateVo.setIdNo("412828198907011256");
		validateVo.setLoanNo("20170714161174");
		validateVo.setOwningBranchId(17493424L);
		validateVo.setProductCode("00015");
		
		//获取需要传给“益百利”数据
		Response<ReqValidateVo> resVo= applyValidateExecuter.validateNameIdNo(validateVo);
		
		System.out.println(gson.toJson(resVo));
		
		List<ApplyRuleBatchExecVo> validateList = new ArrayList<ApplyRuleBatchExecVo>();

		ReqValidateVo demoEntity = resVo.getData();
		ApplyRuleBatchExecVo ruleVO = new ApplyRuleBatchExecVo();
		BeanUtils.copyProperties(demoEntity, ruleVO);
		
		ruleVO.setExecuteType("LDPC009");
		validateList.add(ruleVO);
		//调用“益百利”接口
		RuleEngineRequest ruleRequest = new RuleEngineRequest();
		ruleRequest.setBizType("loanApplyBatch");
		ruleRequest.setSysCode(EnumConstants.BMS_SYSCODE);
		ruleRequest.setData(validateList);
		
		
		System.out.println(gson.toJson(ruleRequest));
		
		com.ymkj.rule.biz.api.message.Response resRule =  ruleEngineExecuter.executeRuleEngine(ruleRequest);
		if(resRule!=null && EnumConstants.RES_CODE_SUCCESS.equals(resRule.getRepCode())){
			MapResponse response_ = (MapResponse)resRule;
			List<Map<String, Object>> ruleMapList = response_.getMapList();
			for (Map<String, Object> map : ruleMapList) {
				System.out.println(gson.toJson(map));
			}
		}
	}

	@Test
	public void testQueryInfoByAppNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryPersonValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIfThirdOrgReturn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetThirdOrgReturnDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryProtectDays() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryLimitRefuseDays() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryLimitCancelDays() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlackList() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryLoanBaseValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuertyOrgInfoByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveValidateRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindHistoryLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryHolidays() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDefaultDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitDefaultDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testStrToDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryReason() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetApplyType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetApplyTypeYBR() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetThirdPartyInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryMainByIdNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testIfNull() {
		fail("Not yet implemented");
	}

	@Test
	public void testFixedCreditReportNotAppNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBadCreditInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckCreditUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCreditInnocenceMsg() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentMsg() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNoCurrentMsg() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCreditBadMsg() {
		fail("Not yet implemented");
	}

	@Test
	public void testHttpToTheLetter() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryApplyDataIsYBR() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRateLoanUrl() {
		fail("Not yet implemented");
	}

	@Test
	public void testMouthDiff() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindHistoryLoanTwo() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuditUpdaetRulesData() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryAuditRulesData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSalesman() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSalesman2() {
		fail("Not yet implemented");
	}

	@Test
	public void testPersonValidate() {
		fail("Not yet implemented");
	}

	@Test
	public void testOldConvertNewNtfState() {
		fail("Not yet implemented");
	}

}
