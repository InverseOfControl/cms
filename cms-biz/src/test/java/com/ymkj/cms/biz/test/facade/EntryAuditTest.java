package com.ymkj.cms.biz.test.facade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.IEntryAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntryAuditOperationVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqEntryCancelVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqPersonalInformation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class EntryAuditTest {
	
	@Autowired
	private IEntryAuditExecuter iEntryAuditExecuter;
	
	@Test
	public void cancel(){
		ReqEntryCancelVO test = new ReqEntryCancelVO();
		test.setLoanNo("20170320133216288452");//借款编号
		
		test.setLoanBaseId(new Long("107"));
		test.setOptionModule("SQLR");//操作模块
		test.setFirstLevelReason("1");//一级原因
		test.setFirstLevelReasonCode("个人原因");//一级原因
		test.setTwoLevelReason("2");//二级原因
		test.setTwoLevelReasonCode("个人原因");//二级原因
		test.setServiceCode("1");
		test.setServiceId(new Long("1"));
		test.setServiceName("1");
		
		
		
		test.setIp("127.0.0.7");
		test.setSysCode("123");
		iEntryAuditExecuter.cancel(test);
	}
	
	@Test
	public void reviewReturn(){
		ReqEntryAuditOperationVO test = new ReqEntryAuditOperationVO();
		test.setLoanNo("20170321154126191483");//借款编号
		
		test.setFirstLevelReason("1");//一级原因
		test.setFirstLevelReasonCode("个人原因");//一级原因
		test.setTwoLevelReason("2");//二级原因
		test.setTwoLevelReasonCode("个人原因");//二级原因
		test.setServiceCode("1");
		test.setServiceName("1");
		
		
		test.setIp("127.0.0.7");
		test.setSysCode("123");
		iEntryAuditExecuter.reviewReturn(test);
	}
	
//	private String corpName; //单位名称
//	private Long corpProvinceId; //公司所在省
//	private Long corpCityId; //公司所在市
//	private Long corpZoneId; //公司所在区/县
//	private String corpAddress; //公司地址
//	private Long homeStateId; //	家庭所在省
//	private Long homeCityId; //	家庭所在市
//	private Long homeZoneId; //家庭所在区县
//	private String homeAddress; //家庭地址
//	
//	private String serviceCode;			//操作人工号
//	private String serviceName;			//操作人姓名
//	private String ip;					//操作ip
	
	@Test
	public void updatePersonalInformation(){
		ReqPersonalInformation reqPersonalInformation = new ReqPersonalInformation();
		
		reqPersonalInformation.setCorpName("aaaaa");
		reqPersonalInformation.setCorpProvinceId(new Long("2"));
		reqPersonalInformation.setCorpCityId(new Long("2"));; //公司所在市
		reqPersonalInformation.setCorpZoneId(new Long("3"));; //公司所在区/县
		reqPersonalInformation.setCorpAddress("aaaa");; //公司地址
		reqPersonalInformation.setHomeStateId(new Long("1"));; //	家庭所在省
		reqPersonalInformation.setHomeCityId(new Long("2"));; //	家庭所在市
		reqPersonalInformation.setHomeZoneId(new Long("3"));; //家庭所在区县
		reqPersonalInformation.setHomeAddress("111");; //家庭地址
		reqPersonalInformation.setServiceCode("111");;			//操作人工号
		reqPersonalInformation.setServiceName("111");;			//操作人姓名
		reqPersonalInformation.setIp("111");;					//操作ip
		reqPersonalInformation.setLoanNo("20170511154800282408");
		reqPersonalInformation.setTheThirdPartyNote("111111");
		reqPersonalInformation.setTheThirdPartyNoteDetails("22222222");
		reqPersonalInformation.setVersion("21");
		reqPersonalInformation.setSysCode("1111");
		Response<String> s=iEntryAuditExecuter.updatePersonalInformation(reqPersonalInformation);
		System.out.println(s);
	}

	

	@Test
	public void invalid(){
		ReqEntryCancelVO test = new ReqEntryCancelVO();
		test.setLoanNo("20170705103324447530");//借款编号
		
		test.setLoanBaseId(new Long("3391"));
		test.setOptionModule("3");//操作模块
		test.setFirstLevelReason("1");//一级原因
		test.setFirstLevelReasonCode("个人原因");//一级原因
		test.setTwoLevelReason("2");//二级原因
		test.setTwoLevelReasonCode("个人原因");//二级原因
		test.setServiceCode("1");
		test.setServiceId(new Long("1"));
		test.setServiceName("1");
		
		
		
		test.setIp("127.0.0.7");
		test.setSysCode("123");
		iEntryAuditExecuter.invalid(test);
	}
}
