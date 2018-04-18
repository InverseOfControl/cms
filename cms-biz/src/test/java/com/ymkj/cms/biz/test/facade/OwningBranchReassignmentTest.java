package com.ymkj.cms.biz.test.facade;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.cms.biz.api.service.apply.IOwningBranchReassignmentExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ReqApplyPhoneVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentListVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentSearchVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqOwningBranchReassignmentVO;
import com.ymkj.cms.biz.api.vo.request.apply.ResOwningBranchReassignmentSearchVO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class OwningBranchReassignmentTest {
	private Gson gson = new Gson();
	
	@Autowired
	private IOwningBranchReassignmentExecuter iOwningBranchReassignmentExecuter;
	
	
	
	
	
	
	@Test
	public void search(){
		ReqOwningBranchReassignmentSearchVO vo = new ReqOwningBranchReassignmentSearchVO();
		vo.setSysCode("cfs");
		vo.setPageNum(1);
		vo.setPageSize(15);
		vo.setOperatorCode("00219303");
		vo.setStatus("4");//1 录入修改、2 合同签约、3 合同确认,4录入复核
		/*vo.setMaxAccLmt(new BigDecimal("10000"));
		vo.setMinAccLmt(new BigDecimal("70000"));*/
		List  accList = new  ArrayList<>();
	/*	accList.add(12);
		accList.add(24);
		accList.add(36);*/
		vo.setAccTerms(accList);
		List<String> ows = new ArrayList<String>();
		ows.add("12827295");
		
		vo.setOwningBranchIds(ows);
		PageResponse<ResOwningBranchReassignmentSearchVO> search = iOwningBranchReassignmentExecuter.search(vo);
		
		System.out.println("结果------------------"+gson.toJson(search));
	}
	
	
	
	
	
	@Test
	public void reassignment(){
		ReqOwningBranchReassignmentListVO volist = new ReqOwningBranchReassignmentListVO();
		ReqOwningBranchReassignmentVO vo = new ReqOwningBranchReassignmentVO();
		vo.setSysCode("bms");
		vo.setLoanNo("20170315135152815657");
		vo.setStatus("LRFH");//1 录入修改、2 合同签约、3 合同确认
		vo.setName("00000");
		vo.setCode("00000");
		vo.setIsThroughTrain("1");
		vo.setBranchId("12827295");
		vo.setVersion("7");
		List<ReqOwningBranchReassignmentVO> list  = new ArrayList<ReqOwningBranchReassignmentVO>();
		list.add(vo);
		volist.setListReqOwningBranchReassignmentVO(list);
		
		volist.setSysCode("bms");
		iOwningBranchReassignmentExecuter.reassignment(volist);
	}
	
	
	
	
	
	
	@Test
	public void queryContactInfo(){
		ReqContactInfoVO vo = new ReqContactInfoVO();
		vo.setSysCode("111");
		vo.setLoanNo("20170315135152815657");
		iOwningBranchReassignmentExecuter.queryContactInfo(vo);
	}
	

	
	@Test
	public void updateContactInfo(){
		ReqContactInfoVO vo = new ReqContactInfoVO();
		vo.setSysCode("111");
		vo.setLoanNo("20170823DB4CE0");
//		vo.setContactCellPhone("11111111111");
		vo.setContactCellPhone_1("11111");
		vo.setContactEmpName("111111");
		vo.setType("1");
		vo.setVersion("1");
		vo.setSequenceNum(new Integer("4"));
		iOwningBranchReassignmentExecuter.updateContactInfo(vo);
	}
	
	@Test
	public void insertContactInfo(){
		ReqContactInfoVO contactInfoVO = new ReqContactInfoVO();
		contactInfoVO.setContactName("联系人信息111");			//姓名
		contactInfoVO.setContactRelation("亲人");		//关系
		contactInfoVO.setContacGender("男"); 			//性别
		contactInfoVO.setContactCellPhone("18611112222");// 手机
		contactInfoVO.setContactCellPhone_1("110");// 手机2
		contactInfoVO.setIfKnowLoan("y");			// 是否知晓贷款
		contactInfoVO.setContactCorpPhone("021123456");// 公司电话号码
		contactInfoVO.setContactCorpPhone_1("0211000");
		contactInfoVO.setContactCorpPost("业务员");	// 职务
		contactInfoVO.setContactEmpName("证大拇指");		//公司名称
		contactInfoVO.setSysCode("111");
		contactInfoVO.setLoanNo("20170315135152815657");
		
		iOwningBranchReassignmentExecuter.insertContactInfo(contactInfoVO);
	}
	
	@Test
	public void updateUserPhone(){
		ReqApplyPhoneVO reqApplyPhoneVO = new ReqApplyPhoneVO();
		reqApplyPhoneVO.setSysCode("bms");
		reqApplyPhoneVO.setLoanNo("20170525161420279386");
		reqApplyPhoneVO.setCellphone("1");
		reqApplyPhoneVO.setCellphoneSec(null);
		reqApplyPhoneVO.setCorpPhone("1");
		reqApplyPhoneVO.setCorpPhoneSec(null);
		
		iOwningBranchReassignmentExecuter.updateUserPhone(reqApplyPhoneVO);
	}
}
