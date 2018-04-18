package com.ymkj.cms.biz.test.facade.master;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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
import com.ymkj.cms.biz.api.service.master.IBMSOrgLimitChannelExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class BMSOrgLimitChannelExecuterTest {
	// json 工具类
	private Gson gson = new Gson();
	
	// 请求实体
	private ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("11111111");
	
	@Autowired
	private IBMSOrgLimitChannelExecuter demoExecuter;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testListOrgLimitChannelBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testListPage() {
		// 请求参数
		reqDemo.setPage(1);
		reqDemo.setRows(10);
		reqDemo.setIsDisabled(null);
//		reqDemo.setIsDisabled(1);
//		reqDemo.setIsDisabled(0);
		PageResponse<ResBMSOrgLimitChannelVO> response = demoExecuter.listPage(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
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
	}

	@Test
	public void testFindByProductCodeList() {
		List<String> proList = new ArrayList<String>();
		proList.add("00001");
		proList.add("00003");
		proList.add("00004");
		// 请求参数
		reqDemo.setProductCodeList(proList);;
		ResListVO<ResBMSOrgLimitChannelVO> response = demoExecuter.findByProductCodeList(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
	}
	@Test
	public void findOrgByProductCodeListIntersect() {
		List<String> proList = new ArrayList<String>();
		proList.add("00020");
		proList.add("00001");
		proList.add("00003");
		proList.add("00004");
		// 请求参数
		reqDemo.setProductCodeList(proList);
		List<Long> contractBranchIdList = new ArrayList<Long>();
		contractBranchIdList.add(12827295L);
		contractBranchIdList.add(2L);
		contractBranchIdList.add(10L);
		reqDemo.setOrgIdList(contractBranchIdList);
		
		
		
		ResListVO<ResBMSOrgLimitChannelVO> response = demoExecuter.findOrgByProductCodeListIntersect(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
	}
	@Test
	public void branchProductRelevanceCheck() {
		
		
//		List<ReqBMSOrgLimitChannelVO> proList = new ArrayList<ReqBMSOrgLimitChannelVO>();
		ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("bms");
		reqDemo.setOrgId(152409L);
		reqDemo.setProductCode("00002");
		reqDemo.setLoanNo("1111111111");
		
//		proList.add(reqDemo);
		// 请求参数
		Response<ResBMSOrgLimitChannelVO> response = demoExecuter.branchProductRelevanceCheck(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
	}
	/**
	 * 查询出所有的网点配置的产品期限(根据网点id，产品id，申请额度)
	 * 可用配置
	 * @author lix YM10160
	 * @date 2017年4月20日下午2:29:06
	 */
	@Test
	public void listOrgProductLimitByOrgProApp() {
		//{"orgId":12827295,"page":0,"productCode":"00013","rows":0,"sysCode":"ams"} 
		ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("bms");
		reqDemo.setOrgId(12827295L);
//		reqDemo.setProductId(2L);
		reqDemo.setProductCode("00013");
		reqDemo.setSysCode("ams");
		reqDemo.setServiceCode("11111");
		reqDemo.setServiceName("11111");
		reqDemo.setIp("11111");
//		reqDemo.setApplyLmt(new BigDecimal(20000));
		// 请求参数
		Response<ResBMSOrgLimitChannelVO> response = demoExecuter.listOrgProductLimitByOrgProApp(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
	}
	/**
	 * 查询出所有的网点配置的产品期限(根据网点id，产品id，申请额度)
	 * 可用配置
	 * @author lix YM10160
	 * @date 2017年4月20日下午2:29:06
	 */
	@Test
	public void findOrgLimitChannelLimitUnion() {
		//{"orgId":12827295,"page":0,"productCode":"00013","rows":0,"sysCode":"ams"} 
		ReqBMSOrgLimitChannelVO reqDemo = new ReqBMSOrgLimitChannelVO("bms");
		reqDemo.setOrgId(12827295L);
//		reqDemo.setProductId(2L);
		reqDemo.setProductCode("00013");
		reqDemo.setSysCode("ams");
		reqDemo.setServiceCode("11111");
		reqDemo.setServiceName("11111");
		reqDemo.setIp("11111");
		reqDemo.setAuditLimit(12);
//		reqDemo.setApplyLmt(new BigDecimal(20000));
		// 请求参数
		Response<ResBMSOrgLimitChannelVO> response = demoExecuter.findOrgLimitChannelLimitUnion(reqDemo);
		
		System.out.println("-------------结果: "+gson.toJson(response));
		
		
	}

}
