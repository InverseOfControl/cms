package com.ymkj.cms.biz.test.facade.apply;

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
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.apply.IApplyEnterExecuter;
import com.ymkj.cms.biz.api.service.audit.IAduitPersonExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.AssetsInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.BasicInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.CarInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.CardLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ContactInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.EstateInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.MasterLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.MerchantLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PolicyInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.PrivateOwnerInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ProvidentInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqAuditDifferencesVO;
import com.ymkj.cms.biz.api.vo.request.apply.SalaryLoanInfoVO;
import com.ymkj.cms.biz.api.vo.request.apply.WorkInfoVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAduitPersonVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAduitPersonVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ApplyEnterExecuterTest {
	// json 工具类
	private Gson gson = new Gson();

	// 请求实体
	private ApplyEntryVO applyEntryVO = new ApplyEntryVO(EnumConstants.BMS_SYSCODE);

	// 请求实体
	
	@Autowired
	private IApplyEnterExecuter applyEnterExecuter;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSaveOrUpdate() {
		
		ApplyInfoVO applyInfoVO = new ApplyInfoVO();         // 申请信息
		applyInfoVO.setLoanBaseId(1291L);
		applyInfoVO.setProductCd("00001");
		applyInfoVO.setProductName("随薪贷");
		applyInfoVO.setApplyLmt(new BigDecimal(10000));
		applyInfoVO.setApplyTerm(24);
		applyInfoVO.setName("为企鹅完全");
		applyInfoVO.setIdNo("340826199001012238");
		applyInfoVO.setContractBranch("鞍山人民路营业部");
		applyInfoVO.setContractBranchId(12827295L);
		applyInfoVO.setIfPri(0);
		applyInfoVO.setCustomerSource("00003");
		applyInfoVO.setCreditApplication("00002");
		applyInfoVO.setBranchManagerCode("00213223");
		applyInfoVO.setBranchManagerName("赵佳丽");
		applyInfoVO.setApplyInputFlag("applyInput");
		
		applyEntryVO.setApplyInfoVO(applyInfoVO);
		
		AssetsInfoVO assetsInfoVO = new AssetsInfoVO();   	 // 资产信息
		
		
		applyEntryVO.setAssetsInfoVO(assetsInfoVO);
		
		EstateInfoVO estateInfoVO = new EstateInfoVO();
		
		assetsInfoVO.setEstateInfoVO(estateInfoVO);
		CarInfoVO carInfoVO = new CarInfoVO();
		carInfoVO.setCarLoan("Y");
		carInfoVO.setLocalPlate("Y");
		
		assetsInfoVO.setCarInfoVO(carInfoVO);
		
		PolicyInfoVO policyInfoVO = new PolicyInfoVO(); // 基本信息
		assetsInfoVO.setPolicyInfoVO(policyInfoVO);
		
		ProvidentInfoVO providentInfoVO = new ProvidentInfoVO(); // 基本信息
		providentInfoVO.setDepositRate(0.0);
		assetsInfoVO.setProvidentInfoVO(providentInfoVO);
		
		
		CardLoanInfoVO cardLoanInfoVO = new CardLoanInfoVO(); // 基本信息
		assetsInfoVO.setCardLoanInfoVO(cardLoanInfoVO);
		SalaryLoanInfoVO salaryLoanInfoVO = new SalaryLoanInfoVO(); // 基本信息
		assetsInfoVO.setSalaryLoanInfoVO(salaryLoanInfoVO);
		MasterLoanInfoVO masterLoanInfoVO = new MasterLoanInfoVO(); // 基本信息
		assetsInfoVO.setMasterLoanInfoVO(masterLoanInfoVO);
		MerchantLoanInfoVO merchantLoanInfoVO = new MerchantLoanInfoVO(); // 基本信息
		assetsInfoVO.setMerchantLoanInfoVO(merchantLoanInfoVO);
		
		BasicInfoVO basicInfoVO = new BasicInfoVO(); // 基本信息
		applyEntryVO.setBasicInfoVO(basicInfoVO);
		PersonInfoVO personInfoVO = new PersonInfoVO(); // 基本信息
		personInfoVO.setName("为企鹅完全");
		personInfoVO.setIdNo("340826199001012238");
		personInfoVO.setGender("男");
		personInfoVO.setAge(27);
		personInfoVO.setHomeSameRegistered(1);
		basicInfoVO.setPersonInfoVO(personInfoVO);
		
		
		WorkInfoVO workInfoVO = new WorkInfoVO(); // 基本信息
		basicInfoVO.setWorkInfoVO(workInfoVO);
		PrivateOwnerInfoVO privateOwnerInfoVO = new PrivateOwnerInfoVO(); // 基本信息
		basicInfoVO.setPrivateOwnerInfoVO(privateOwnerInfoVO);
		List<ContactInfoVO> contactInfoVOList = new ArrayList<ContactInfoVO>(); // 联系人信息
		applyEntryVO.setContactInfoVOList(contactInfoVOList);
		
		ContactInfoVO contactInfoVO = new ContactInfoVO(); // 基本信息
		contactInfoVO.setIfKnowLoan("N");
		contactInfoVO.setSequenceNum(1);
		
		contactInfoVOList.add(contactInfoVO);
		
		applyEntryVO.setModifierId(13061590L);
		applyEntryVO.setModifier("00219303");
		applyEntryVO.setServiceCode("00219303");
		applyEntryVO.setServiceName("花锦春");
		applyEntryVO.setOwningBranchId(12827295L);
		applyEntryVO.setOwningBranch("鞍山人民路营业部");
		applyEntryVO.setOwningBranchAttribute("O");
		applyEntryVO.setOptionModule("2");
		applyEntryVO.setOptionType("102");
		applyEntryVO.setSysCode("cfs");
		
		
		
		
		Response<ReqApplyEntryVO> response = applyEnterExecuter.saveOrUpdate(applyEntryVO);
		System.out.println("-------------结果: " + gson.toJson(response));
	}

	@Test
	public void testInsetSysLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateNameIdNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryApplyEntry() {
		fail("Not yet implemented");
	}

	@Test
	public void testTest() {
		fail("Not yet implemented");
	}

	@Test
	public void testRapidReplicationVersion() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetApplyType() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuditUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFixedCreditReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateRepeatSingle() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateLastIfBeingRejected() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateWhetherProtection() {
		fail("Not yet implemented");
	}

	@Test
	public void testProductIfShelves() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryApplyValueEntry() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void deleteApplyContactInfo() {
		ReqAuditDifferencesVO v=new ReqAuditDifferencesVO();
		v.setSysCode("ams");
		v.setLoanNo("20170615123146974632");	
		v.setSequenceNum("2");
		Response<Long> res=applyEnterExecuter.deleteApplyContactInfo(v);
		System.out.println(res.getData());
	}

}
