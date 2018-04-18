package com.ymkj.cms.biz.test.facade.audit.last;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.audit.last.IFinalAdultExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSReassignmentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class FinalAuditExecuterTest {

	private Gson gson = new Gson();
	
	@Autowired
	private IFinalAdultExecuter finalAdultExecuter;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testZSRejectUpd() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSHangupUpd() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSSubmitUpd() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSReturnUpd() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSProductUpd() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSreassignmentUpd() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSAutomaticDispatchList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSortValues1() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSlistPage() {
		//"appInputFlag":"1","caseType":"2","flag":"2","loginPersonCode":"final","pageNum":1,"pageSize":10,"sysCode":"ams"
		String json="{'caseIdentifyList':['3'],'caseType':'3','flag':'2','loginPersonCode':'zhaojianyun','pageNum':1,'pageSize':20,'rulesSort':0,'sysCode':'ams'}";
		
		//{"flag":"2","loginPersonCode":"Anne","pageNum":1,"pageSize":10,"sysCode":"ams","xsEndDate":"2017-05-26 23:59:59","xsStartDate":"2017-05-18 00:00:00"}
		ReqBMSReassignmentVo reqBMSReassignmentVo = gson.fromJson(json, ReqBMSReassignmentVo.class);
		PageResponse<ResBMSReassignmentVo> q=finalAdultExecuter.zSlistPage(reqBMSReassignmentVo);
		System.out.println(gson.toJson(q));
	}
	@Test
	public void testFindXsSnapVersionInfo() {
		ReqAuditVo reqAuditVo = new ReqAuditVo();
		reqAuditVo.setSysCode("ams");
		reqAuditVo.setLoanNo("201710112413DF");
		//reqAuditVo.setName("九月十四12");
		//reqAuditVo.setIdNo("140423199003069715");
		reqAuditVo.setFlag("2");
		Response<String> q=finalAdultExecuter.findXsSnapVersionInfo(reqAuditVo);
		System.out.println(gson.toJson(q));
	}
	@Test
	public void testChenckIsReturn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSortValues() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSAdultOffTheStocks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSortValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testZSWaitForTheStocks() {
		fail("Not yet implemented");
	}

	@Test
	public void testResAuditQueryInfoStringStringResBMSFinalAduitUpdVO() {
		fail("Not yet implemented");
	}

	@Test
	public void testResAudiInfo2() {
		fail("Not yet implemented");
	}

	@Test
	public void testResAuditQueryInfoStringStringPageResponseOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindLastByStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testISSuperAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testDataAsc() {
		fail("Not yet implemented");
	}

}
