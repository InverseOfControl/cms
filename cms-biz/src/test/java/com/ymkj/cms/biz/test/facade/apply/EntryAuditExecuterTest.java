package com.ymkj.cms.biz.test.facade.apply;

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
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IApplyEnterExecuter;
import com.ymkj.cms.biz.api.service.apply.IEntryAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntrySearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResEntrySearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResWaitToDealCount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class EntryAuditExecuterTest {

	// json 工具类
	private Gson gson = new Gson();

	// 请求实体
	private ReqEntrySearchVO entryVO = new ReqEntrySearchVO(EnumConstants.BMS_SYSCODE);

	// 请求实体
	
	@Autowired
	private IEntryAuditExecuter entryAuditExecuter;
		
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testListPage() {
		entryVO.setPageNum(1);
		entryVO.setPageSize(15);
		entryVO.setServiceCode("00218655");
		entryVO.setServiceName("花锦春");
		entryVO.setAgencyOrComplete("2");
		entryVO.setOptionModule("3");
		entryVO.setOwningBranchId("12827295");
		entryVO.setRows(0);
		entryVO.setPage(0);
		entryVO.setIp("10.8.30.89");
		entryVO.setSysCode("cfs");
		
		PageResponse<ResEntrySearchVO> response = entryAuditExecuter.listPage(entryVO);
		System.out.println("-------------结果: " + gson.toJson(response));
	}

	@Test
	public void testCancel() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryInformationVO() {
		fail("Not yet implemented");
	}

	@Test
	public void testReviewReturn() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryWaitToDealCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePersonalInformation() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeOutRefuse() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testQueryInputModifyCount() {
		entryVO.setServiceCode("00219303");
		entryVO.setOwningBranchId("12827295");
		
		Response<Object> response = entryAuditExecuter.queryInputModifyCount(entryVO);
		System.out.println("-------------结果: " + gson.toJson(response));
	}

}
