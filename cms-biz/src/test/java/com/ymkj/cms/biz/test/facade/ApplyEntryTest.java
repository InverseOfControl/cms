package com.ymkj.cms.biz.test.facade;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.service.apply.IApplyEnterExecuter;
import com.ymkj.cms.biz.api.service.apply.IApplyValidateExecuter;
import com.ymkj.cms.biz.api.service.apply.IBMSLoanUrgentExecuter;
import com.ymkj.cms.biz.api.service.apply.IEntryAuditExecuter;
import com.ymkj.cms.biz.api.vo.request.apply.*;
import com.ymkj.cms.biz.api.vo.response.apply.ResCheckNewDataVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResYBRReturnVO;
import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.test.IdCardGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-consumer.xml")
public class ApplyEntryTest {
	
	// json 工具类
	private Gson gson = new Gson();
	private static Validator validator = null;

	
	
	// 请求实体

	private ReqDemoVO reqDemo = new ReqDemoVO("11111111");

	@Autowired
	private IApplyEnterExecuter iApplyEnterExecuter;
	
	@Autowired
	private IEntryAuditExecuter iEntryAuditExecuter;
	
	@Autowired
	private IBMSLoanUrgentExecuter iBMSLoanUrgentExecuter;
	
	@Autowired
	private IApplyValidateExecuter iApplyValidateExecuter;
	

//	@Test
//	public void save() {
//		//申请信息 start
//		ApplyInfoVO applytInfoVO = ApplyEntryPackaging.getApplyInfoVO();
//		//申请信息 end
//		
//		// 资产信息start
//		AssetsInfoVO assetsInfoVO = ApplyEntryPackaging.getAssetsInfoVO(applytInfoVO); 
//		//资产信息end
//
//		// 基本信息 start	
//		BasicInfoVO basicInfoVO = ApplyEntryPackaging.getBasicInfoVO();
//		// 基本信息 start	
//		
//		// 联系人信息 start
//		List<ContactInfoVO> contactInfoVOList = ApplyEntryPackaging.getContactInfoVOList();
//		// 联系人信息 end
//		
//		ApplyEntryVO applyEntryVO = new ApplyEntryVO();
//		applyEntryVO.setApplyInfoVO(applytInfoVO);	//申请信息
//		applyEntryVO.setAssetsInfoVO(assetsInfoVO);//资产信息
//		applyEntryVO.setBasicInfoVO(basicInfoVO);	//基本信息
//		applyEntryVO.setContactInfoVOList(contactInfoVOList); //联系人信息
//		
//		applyEntryVO.setCreator("chenybanbin");			//录入员工姓名
//		applyEntryVO.setCreatorId(new Long(111));	//录入员工id
//		applyEntryVO.setServiceCode("CYB001");		//录入员工Code
//		
//		applyEntryVO.setOwningBranch("证大拇指店");		//录入门店
//		applyEntryVO.setOwningBranchId(new Long("110"));//录入门店id
//		
//		applyEntryVO.setSysCode("pms");
//		iApplyEnterExecuter.save(applyEntryVO);
//	}
//
//	@Test
//	public void update() {
//		
//		//申请信息 start
//		ApplyInfoVO applytInfoVO = ApplyEntryPackaging.getApplyInfoVO();
//		applytInfoVO.setLoanBaseId(Long.parseLong("97"));
//		//申请信息 end
//		
//		// 资产信息start
//		AssetsInfoVO assetsInfoVO = ApplyEntryPackaging.getAssetsInfoVO(applytInfoVO); 
//		//资产信息end
//
//		// 基本信息 start
//		BasicInfoVO basicInfoVO = ApplyEntryPackaging.getBasicInfoVO();
//		// 基本信息 start
//		
//		// 联系人信息 start
//		List<ContactInfoVO> contactInfoVOList = ApplyEntryPackaging.getContactInfoVOList();
//		// 联系人信息 end
//		
//		ApplyEntryVO applyEntryVO = new ApplyEntryVO();
//		applyEntryVO.setApplyInfoVO(applytInfoVO);	//申请信息
//		applyEntryVO.setAssetsInfoVO(assetsInfoVO);//资产信息
//		applyEntryVO.setBasicInfoVO(basicInfoVO);	//基本信息
//		applyEntryVO.setContactInfoVOList(contactInfoVOList); //联系人信息
//		
//		applyEntryVO.setModifier("chen_yanbin");
//		applyEntryVO.setModifierId(new Long(222));
//		
//		applyEntryVO.setOptionModule("1");			//操作模型  	1 申请录入  2 录入修改 3 录入复核 4合同签约
//		applyEntryVO.setOptionType("101");				//操作类型  	101 提交 102保存 103 取消 104 改派
//		
//		applyEntryVO.setSysCode("pms");
//		iApplyEnterExecuter.update(applyEntryVO);
//	}
	
	
	
	
	@Test
	public void batchSaveOrUpdate() {
		IdCardGenerator g = new IdCardGenerator();
		for (int i = 0; i < 20; i++) {
		
		String name = "测试十月二十"+i;//身份证姓名
		String idNo = g.generate();//身份证号码
		
		
			long sysDate = System.currentTimeMillis();
			//申请信息 start
			ApplyInfoVO applytInfoVO = ApplyEntryPackaging.getApplyInfoVO(name,idNo);
			//申请信息 end
			
			// 资产信息start
			AssetsInfoVO assetsInfoVO = ApplyEntryPackaging.getAssetsInfoVO(applytInfoVO); 
			//资产信息end

			// 基本信息 start	
			BasicInfoVO basicInfoVO = ApplyEntryPackaging.getBasicInfoVO(name,idNo);
			// 基本信息 start	
			
			// 联系人信息 start
			List<ContactInfoVO> contactInfoVOList = ApplyEntryPackaging.getContactInfoVOList();
			// 联系人信息 end
			
			ApplyEntryVO applyEntryVO = new ApplyEntryVO();
			applyEntryVO.setApplyInfoVO(applytInfoVO);	//申请信息
			applyEntryVO.setAssetsInfoVO(assetsInfoVO);//资产信息
			applyEntryVO.setBasicInfoVO(basicInfoVO);	//基本信息
			applyEntryVO.setContactInfoVOList(contactInfoVOList); //联系人信息
			
			applyEntryVO.setCreator("00218655");			//新增员工code
			applyEntryVO.setCreatorId(new Long(1));		//新增员工id
			
			applyEntryVO.setServiceCode("00218655");			//录入员工Code
			applyEntryVO.setServiceName("花锦春");			//录入员工名称
			
			applyEntryVO.setOwningBranch("鞍山人民路营业部");			//录入门店
			applyEntryVO.setOwningBranchId(new Long("12827295"));//录入门店id  开发环境

			applyEntryVO.setModifier("chen_yanbin");
			applyEntryVO.setModifierId(new Long(222));
			
			applyEntryVO.setLoggedArea("0102");
			applyEntryVO.setLoggedAreaName("北区");
			
			String modul = "1";//操作模型  	1 申请录入  2 录入修改 3 录入复核
			String type = "102";//操作类型  	101 提交 102保存
			
			applyEntryVO.setOptionModule(modul);			//操作模型  	1 申请录入  2 录入修改 3 录入复核
			applyEntryVO.setOptionType(type);				//操作类型  	101 提交 102保存 
			
			applyEntryVO.setSysCode("cfs");
			Response<ReqApplyEntryVO> obj = iApplyEnterExecuter.saveOrUpdate(applyEntryVO);
			if(!obj.isSuccess()){
				System.out.println("错误==="+obj.getRepMsg());
				continue;
			}
			
			ReqApplyEntryVO loanBaseId = obj.getData();
			System.out.println("----------------------loanBaseId："+loanBaseId.getLoanBaseId());
			applytInfoVO.setLoanBaseId(loanBaseId.getLoanBaseId());
			long createDate = System.currentTimeMillis();
			System.out.println("创建耗时："+(createDate - sysDate));
			
			modul = "2";//操作模型  	1 申请录入  2 录入修改 3 录入复核
			type = "101";//操作类型  	101 提交 102保存
			
			applyEntryVO.setOptionModule(modul);			//操作模型  	1 申请录入  2 录入修改 3 录入复核
			applyEntryVO.setOptionType(type);				//操作类型  	101 提交 102保存 
			Response<ReqApplyEntryVO> obj1 = iApplyEnterExecuter.saveOrUpdate(applyEntryVO);
			if(!obj1.isSuccess()){
				System.out.println("错误=修改提交=="+obj1.getRepMsg());
				continue;
			}
			long createSubMitDate = System.currentTimeMillis();
			System.out.println("录入提交耗时："+(createSubMitDate - createDate));
			
			modul = "3";//操作模型  	1 申请录入  2 录入修改 3 录入复核
			type = "101";//操作类型  	101 提交 102保存
			
			applyEntryVO.setOptionModule(modul);			//操作模型  	1 申请录入  2 录入修改 3 录入复核
			applyEntryVO.setOptionType(type);				//操作类型  	101 提交 102保存 
			Response<ReqApplyEntryVO> obj2 = iApplyEnterExecuter.saveOrUpdate(applyEntryVO);
			if(!obj2.isSuccess()){
				System.out.println("错误=复核提交=="+obj2.getRepMsg());
				continue;
			}
			
			long SubMitDate = System.currentTimeMillis();
			System.out.println("复核提交耗时："+(SubMitDate - createSubMitDate));
			
			System.out.println("总耗时："+(System.currentTimeMillis() - sysDate));
		}
	}

	
	
	@Test
	public void queryApplyEntry(){
		PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
		personHistoryLoanVO.setLoanNo("20170704000000730941");
		personHistoryLoanVO.setSysCode("bms");
		iApplyEnterExecuter.queryApplyEntry(personHistoryLoanVO);
	}
	
	@Test
	public void queryApplyValueEntry(){
		PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
		personHistoryLoanVO.setLoanNo("20170912EF9174");
		personHistoryLoanVO.setSysCode("bms");
		iApplyEnterExecuter.queryApplyValueEntry(personHistoryLoanVO);
	}
	
	
	@Test
	public void saveOrUpdate() {
		
		String name = "张三";//身份证姓名
		String idNo = "352227199107073511";//身份证号码
		
		String modul = "3";//操作模型  	1 申请录入  2 录入修改 3 录入复核
		String type = "101";//操作类型  	101 提交 102保存
		
		//申请信息 start
		ApplyInfoVO applytInfoVO = ApplyEntryPackaging.getApplyInfoVO(name,idNo);
		applytInfoVO.setLoanBaseId(new Long("3374"));
		//申请信息 end
		
		// 资产信息start
		AssetsInfoVO assetsInfoVO = ApplyEntryPackaging.getAssetsInfoVO(applytInfoVO); 
		//资产信息end

		// 基本信息 start	
		BasicInfoVO basicInfoVO = ApplyEntryPackaging.getBasicInfoVO(name,idNo);
		// 基本信息 start	
		
		// 联系人信息 start
		List<ContactInfoVO> contactInfoVOList = ApplyEntryPackaging.getContactInfoVOList();
		// 联系人信息 end
		
		ApplyEntryVO applyEntryVO = new ApplyEntryVO();
		applyEntryVO.setApplyInfoVO(applytInfoVO);	//申请信息
		applyEntryVO.setAssetsInfoVO(assetsInfoVO);//资产信息
		applyEntryVO.setBasicInfoVO(basicInfoVO);	//基本信息
		applyEntryVO.setContactInfoVOList(contactInfoVOList); //联系人信息
		
		applyEntryVO.setCreator("00219303");			//新增员工code
		applyEntryVO.setCreatorId(new Long(1));		//新增员工id
		
		applyEntryVO.setServiceCode("00219303");			//录入员工Code
		applyEntryVO.setServiceName("花锦春");			//录入员工名称
		
		applyEntryVO.setOwningBranch("鞍山人民路营业部");			//录入门店
		applyEntryVO.setOwningBranchId(new Long("12827295"));//录入门店id
		
		applyEntryVO.setModifier("chen_yanbin");
		applyEntryVO.setModifierId(new Long(222));
		
		applyEntryVO.setLoggedArea("0102");
		applyEntryVO.setLoggedAreaName("北区");
		
		applyEntryVO.setOptionModule(modul);			//操作模型  	1 申请录入  2 录入修改 3 录入复核
		applyEntryVO.setOptionType(type);				//操作类型  	101 提交 102保存 
		
		applyEntryVO.setSysCode("cfs");
		Response<ReqApplyEntryVO> obj = iApplyEnterExecuter.saveOrUpdate(applyEntryVO);
		if(!obj.isSuccess()){
			System.out.println("录入借款信息错误："+obj.getRepMsg());
		}else{
			System.out.println(gson.toJson(obj.getData()));
		}
	}
	
	
	@Test
	public void listPage(){
		
		ReqEntrySearchVO vo = new ReqEntrySearchVO();
		vo.setAgencyOrComplete("1"); //  1代办任务  2完成任务
		vo.setOptionModule("3");	 // 2 申请录入 3 录入复核
		vo.setPageNum(1);
		vo.setPageSize(15);
		vo.setCompletionTimeStart(DateUtil.getDateBefore(new Date(),100));
		vo.setCompletionTimeEnd(new Date());
		vo.setServiceCode("00219303");
		vo.setOwningBranchId("12827295");
		vo.setSysCode("111");
		iEntryAuditExecuter.listPage(vo);
	}
	
	
//	//@Test
//	public void test(){
//		PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
//		personHistoryLoanVO.setLoanNo("20170313182903832051");
//		personHistoryLoanVO.setSysCode("111");
//		iApplyEnterExecuter.test(personHistoryLoanVO);
//	}
	@Test
	public void queryInformationVO(){
		ReqInformationVO reqInformationVO = new ReqInformationVO();
		reqInformationVO.setLoanNo("20170603173450371798");
		
		reqInformationVO.setSysCode("111");
		iEntryAuditExecuter.queryInformationVO(reqInformationVO);
	}
	
	
//	@Test
//	public void auditUpdate() {
//		ApplyInfoVO applytInfoVO = new ApplyInfoVO();
//		applytInfoVO.setProductCd("");
//		
//		// 资产信息start
//		AssetsInfoVO assetsInfoVO = ApplyEntryPackaging.getAssetsInfoVO(applytInfoVO); 
//		//资产信息end
//
//		// 基本信息 start	
//		BasicInfoVO basicInfoVO = ApplyEntryPackaging.getBasicInfoVO();
//		// 基本信息 start	
//		
//		// 联系人信息 start
//		List<ContactInfoVO> contactInfoVOList = ApplyEntryPackaging.getContactInfoVOList();
//		// 联系人信息 end
//		
//		AuditAmendEntryVO applyEntryVO = new AuditAmendEntryVO();
//		applyEntryVO.setAssetsInfoVO(assetsInfoVO);//资产信息
//		applyEntryVO.setBasicInfoVO(basicInfoVO);	//基本信息
//		applyEntryVO.setContactInfoVOList(contactInfoVOList); //联系人信息
//		
//		applyEntryVO.setModifier("dong_ming_zhi");
//		applyEntryVO.setModifierId(new Long(222));
//		
//		applyEntryVO.setLoanBaseId(new Long("433"));
//		applyEntryVO.setLoanNo("20170323164232098192");
//		applyEntryVO.setProductCd("000001");
//		
//		applyEntryVO.setVersion("2");
//		
//		applyEntryVO.setSysCode("pms");
//		Response<ReqApplyEntryVO> obj = iApplyEnterExecuter.auditUpdate(applyEntryVO);
//	}
	
	
	@Test
	public void validateRepeatSingle(){
		ValidateNameIdNoVO validateNameIdNoVO = new ValidateNameIdNoVO();
		validateNameIdNoVO.setSysCode("1234");
		
		
		validateNameIdNoVO.setName("周润发");
		validateNameIdNoVO.setIdNo("430224199111011216");
		validateNameIdNoVO.setIfThroughTrain("0");
		iApplyEnterExecuter.validateRepeatSingle(validateNameIdNoVO);
	}
	
	
	@Test
	public void queryAuditDifferences(){
		ReqAuditDifferencesVO reqAuditDifferencesVO = new ReqAuditDifferencesVO();
		reqAuditDifferencesVO.setSysCode("bms");
		reqAuditDifferencesVO.setLoanNo("201707276DED64");
		reqAuditDifferencesVO.setVersion("1");
		iApplyEnterExecuter.queryAuditDifferences(reqAuditDifferencesVO);
	}
	
	
	@Test
	public void validateNameIdNo(){
		ValidateNameIdNoVO reqAuditDifferencesVO = new ValidateNameIdNoVO();
		reqAuditDifferencesVO.setName("");
		reqAuditDifferencesVO.setIdNo("");
		
		reqAuditDifferencesVO.setSysCode("bms");
		iApplyEnterExecuter.validateNameIdNo(reqAuditDifferencesVO);
	}
	
	@Test
	public void getLoanUrgentSize(){
		ReqLoanUrgentVO vo = new ReqLoanUrgentVO();
		vo.setSysCode("cfs");
		vo.setOwningBranchId(new Long("12827295"));
		
		iBMSLoanUrgentExecuter.getLoanUrgentSize(vo);
	}
	
	@Test
	public void validateNameIdNoTest(){
		ValidateNameIdNoVO validateNameIdNoVO = new ValidateNameIdNoVO();
		validateNameIdNoVO.setSysCode("cfs");
		validateNameIdNoVO.setLoanNo("201707119184B6");
		validateNameIdNoVO.setIdNo("500227197010199166");
		validateNameIdNoVO.setName("测试1");
		validateNameIdNoVO.setAppStatus("app-scxx");
		//validateNameIdNoVO.setOwningBranchId(666666666l);
		//validateNameIdNoVO.setIfThroughTrain("1");
		
//		validateNameIdNoVO.setIdNo("510121197504165298");
//		validateNameIdNoVO.setName("王贵前");
		Response<ReqValidateVo> r=iApplyValidateExecuter.validateNameIdNo(validateNameIdNoVO);
		System.out.println(r);
	}
	
	@Test
	public void test(){
		PersonHistoryLoanVO personHistoryLoanVO = new PersonHistoryLoanVO();
		personHistoryLoanVO.setSysCode("bms");
		
		personHistoryLoanVO.setIdNo("352227199107073511");
		personHistoryLoanVO.setName("陈陈");
		iApplyEnterExecuter.test(personHistoryLoanVO);
	}
	
	
	@Test
	public void checkProductCdMandatoryModel(){
		AuditAmendEntryVO applyEntryVO = new AuditAmendEntryVO();
		applyEntryVO.setSysCode("bms");
		
		applyEntryVO.setLoanNo("20170622134408450287");
		Response<Object> checkProductCdMandatoryModel = iApplyEnterExecuter.checkProductCdMandatoryModel(applyEntryVO);
		System.out.println("----------------------------结果："+gson.toJson(checkProductCdMandatoryModel));
	}
	
	
	@Test
	public void checkCreditUser(){
		ValidateNameIdNoVO applyEntryVO = new ValidateNameIdNoVO();
		applyEntryVO.setSysCode("bms");
//		101082
		applyEntryVO.setProductCode("00001");
		applyEntryVO.setIdNo("62100019801016482X");
		applyEntryVO.setName("789");
		
		Response<ReqCreditCheckVO> checkCreditUser = iApplyValidateExecuter.checkCreditUser(applyEntryVO);
		System.out.println("----------------------------结果："+gson.toJson(checkCreditUser));
	}

	@Test
	public void queryApplyDataIsYBR(){
		PersonHistoryLoanVO personHistoryLoanVO=new PersonHistoryLoanVO();
		personHistoryLoanVO.setSysCode("ams");
		personHistoryLoanVO.setLoanNo("81461247");
		
		
		//personHistoryLoanVO.setLoanNo("20170622134358227253");
		Response<ResYBRReturnVO> response=iApplyValidateExecuter.queryApplyDataIsYBR(personHistoryLoanVO);
		System.out.println(response);
	}


	

	@Test
	public void queryCheckNewData(){
		PersonHistoryLoanVO personHistoryLoanVO=new PersonHistoryLoanVO();
		personHistoryLoanVO.setSysCode("ams");
		//personHistoryLoanVO.setName("大黄蜂0");
		//personHistoryLoanVO.setIdNo("511800200409078817");
		personHistoryLoanVO.setName("毕琳珍");
		personHistoryLoanVO.setIdNo("882542199101186079");
		Response<ResCheckNewDataVO> response=iApplyEnterExecuter.queryCheckNewData(personHistoryLoanVO);
		System.out.println(response);
	}

}
