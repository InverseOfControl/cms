package com.ymkj.cms.web.boss.test.controller.master;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.web.test.AbstractTestCase;
import com.ymkj.cms.web.boss.controller.master.ContractChangeController;
import com.ymkj.cms.web.boss.facade.apply.OrgLimitChannelFacade;

public class ContractChangeControllerTest extends AbstractTestCase{

	// json 工具类
	private Gson gson = new Gson();
		
    // 请求实体
	private ReqBMSOrgLimitChannelVO deomVo = new ReqBMSOrgLimitChannelVO();
	
	@Autowired
	private ContractChangeController contractChangeController;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testView() {
		fail("Not yet implemented");
	}

	@Test
	public void testListPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddChannelBankMethod() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrgByProductCodeListIntersect() {
		fail("Not yet implemented");
	}

	@Test
	public void testBranchProductRelevanceCheck() {
		
		String branchProductRelevances = "11111,11111";
		
		contractChangeController.branchProductRelevanceCheck(branchProductRelevances);
	}

	@Test
	public void testFindDataOrgIdsByAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindEmployeeByDeptAndRole() {
		fail("Not yet implemented");
	}

}
