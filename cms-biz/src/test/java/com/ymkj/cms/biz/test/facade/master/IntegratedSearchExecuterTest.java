package com.ymkj.cms.biz.test.facade.master;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.cms.biz.api.service.master.IIntegratedSearchExecuter;
import com.ymkj.cms.biz.api.vo.request.integratedsearch.ReqIntegratedSearchVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class IntegratedSearchExecuterTest {
	//json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqIntegratedSearchVO reqDemo = new ReqIntegratedSearchVO("bms");
	
	@Autowired
	private IIntegratedSearchExecuter demoExecuter;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetSortValue() {
		
		
		
		
		
		
		fail("Not yet implemented");
	}

	@Test
	public void testSearch() {
		reqDemo.setServiceCode("Anne");
		reqDemo.setServiceName("安妮");
		reqDemo.setIp("10.8.30.89");
		reqDemo.setPageNum(1);
		reqDemo.setPageSize(10);
	/*	List<String> caseIdentifyList = new ArrayList<String>();
	caseIdentifyList.add("0");
	caseIdentifyList.add("1");
	caseIdentifyList.add("2");
		caseIdentifyList.add("3");
		reqDemo.setCaseIdentifyList(caseIdentifyList);*/
		List<String> addressList=new ArrayList<String>();
		addressList.add("1");
		addressList.add("2");
		
		reqDemo.setAddressList(addressList);
		reqDemo.setAddress("绿绿绿绿");
		reqDemo.setSysCode("ams");
		//reqDemo.setCorpName("上海123");
    	//reqDemo.setLoanNo("11111");
		demoExecuter.search(reqDemo);
	}

	@Test
	public void testQueryLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryRepaymentSummary() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryRepaymentDetailAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryflow() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryLoanLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCheckLongLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryLoanLogByRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryLoanDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testDataAsc() {
		fail("Not yet implemented");
	}

}
