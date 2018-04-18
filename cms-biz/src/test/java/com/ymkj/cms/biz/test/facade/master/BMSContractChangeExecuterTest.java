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
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSContractChangeExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractChangeVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractChangeVo;
import com.ymkj.pms.biz.api.vo.request.ReqParamVO;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSContractChangeExecuterTest {

	
	// json 工具类
	private Gson gson = new Gson();
			
    // 请求实体
	private ReqBMSContractChangeVo reqDemo = new ReqBMSContractChangeVo();
			
	@Autowired
	private IBMSContractChangeExecuter contractChangeExecuter;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testListPage() {
		
		reqDemo.setLoanNo("201708176A6C2A");
//		reqDemo.setPersonName("得到");
//		reqDemo.setChannelCode("00016");
//		reqDemo.setProductCode("00001");
//		reqDemo.setContractBranchId("10");
//		reqDemo.setSignCode("00219303");
		reqDemo.setPageNum(1);
		reqDemo.setPageSize(10);
//		List<String> contractBranchIdList = new ArrayList<String>();
//		contractBranchIdList.add("12827295");
//		contractBranchIdList.add("2");
//		contractBranchIdList.add("10");
//		reqDemo.setContractBranchIdList(contractBranchIdList);
		
		
		PageResponse<ResBMSContractChangeVo> res = contractChangeExecuter.listPage(reqDemo);
		
		System.out.println("------------------"+gson.toJson(res));
		
	}

	@Test
	public void testChangeContract() {
		
		reqDemo.setContractBranchId("12827295");
		reqDemo.setContractBranch("鞍山人民路营业部");
		
//		reqDemo.setIds("915");
		reqDemo.setLoanNos("201707136748B8");
//		reqDemo.setIds("48,80,90,96,97,283");
		reqDemo.setSysCode("bms");
		
		ReqParamVO vo = new ReqParamVO();
//		vo.setOrgId(Long.valueOf("6913"));
		vo.setOrgId(Long.valueOf("152409"));
		vo.setRoleCode("customerService");
		vo.setSysCode("bms");
		//平均分配原则
		List<String> customerServiceList = new ArrayList<String>();
		customerServiceList.add("00213223");
		reqDemo.setCustomerServiceList(customerServiceList);
		
		String singCodeRejects = "00219303";
//		yangh001
//		上海浦东大道营业部
//		"00222476"
//		String singCodeRejects = "00219304,00219303";
		reqDemo.setSignCodeRejects(singCodeRejects);
		
		
		
		
		Response<ResBMSContractChangeVo> res = contractChangeExecuter.changeContract(reqDemo);
		System.out.println("------------------"+gson.toJson(res));
	}

}
