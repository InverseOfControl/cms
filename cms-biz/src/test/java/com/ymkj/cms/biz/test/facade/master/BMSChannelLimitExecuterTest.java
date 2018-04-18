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
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.master.IBMSChannelExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSLimitChannelExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSOrgLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.biz.service.master.IBMSChannelService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSChannelLimitExecuterTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("11111111");
	
	@Autowired
	private IBMSLimitChannelExecuter demoExecuter;
	@Autowired
	private IBMSChannelExecuter channelExecuter;
	
	@Before
	public void setUp() throws Exception {
	}

	/*@Test
	public void testListOrgLimitChannelBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testListPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveProductLimit() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProductLimit() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogicalDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDisabled() {
		fail("Not yet implemented");
	}*/


	@Test
	public void branchProductRelevanceCheck() {
		
		
//		List<ReqBMSOrgLimitChannelVO> proList = new ArrayList<ReqBMSOrgLimitChannelVO>();
		ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("bms");
		reqDemo.setAuditLimit(12);;
		reqDemo.setProductCode("00001");
		reqDemo.setChannelCode("00001");;
		
//		proList.add(reqDemo);
		// 请求参数
		ResBMSOrgLimitChannelVO response = demoExecuter.getFULimit(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
	}

	@Test
	public void getChannelByOrgProAlt(){
		
		
		String json ="{'accLmt':30000.00,'ifPreferentialUser':'N','ip':'10.8.30.70','owningBranchId':'12827295','page':0,'pageNum':0,'pageSize':0,'productCd':'00015','rows':0,'serviceCode':'00219303','serviceName':'花锦春','sysCode':'cfs'}";
		
		ReqLoanContractSignVo reqzsUpdVO=gson.fromJson(json, ReqLoanContractSignVo.class);
		Response<List<ResBMSChannelVO>> a=channelExecuter.getChannelByOrgProAlt(reqzsUpdVO);
		System.out.println("------------------------------------------------------------------------");
		System.out.println(a.getRepCode()+","+a.getRepMsg());
	}
}
