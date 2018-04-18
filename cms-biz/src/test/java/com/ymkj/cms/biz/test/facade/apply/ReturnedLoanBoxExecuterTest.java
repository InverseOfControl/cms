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
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IEntryAuditExecuter;
import com.ymkj.cms.biz.api.service.apply.IReturnedLoanBoxExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntrySearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqReturnedLoanBoxSearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResEntrySearchVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResReturnedLoanBoxSearchVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ReturnedLoanBoxExecuterTest {
	// json 工具类
	private Gson gson = new Gson();

	// 请求实体
	private ReqReturnedLoanBoxSearchVO entryVO = new ReqReturnedLoanBoxSearchVO(EnumConstants.BMS_SYSCODE);

	// 请求实体
	
	@Autowired
	private IReturnedLoanBoxExecuter returnedLoanBoxExecuter;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSearch() {
		entryVO.setServiceCode("00219303");
		entryVO.setServiceName("00219303");
		entryVO.setPageNum(1);
		entryVO.setPageSize(1);
		entryVO.setReturnType("XSHJ");
		entryVO.setIp("172.0.0.1");
		
		
		PageResponse<ResReturnedLoanBoxSearchVO> response = returnedLoanBoxExecuter.search(entryVO);
		System.out.println("-------------结果: " + gson.toJson(response));
	}

	@Test
	public void testQueryMessageCount() {
		fail("Not yet implemented");
	}

}
