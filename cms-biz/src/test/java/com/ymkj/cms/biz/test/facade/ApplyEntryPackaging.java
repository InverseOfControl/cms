package com.ymkj.cms.biz.test.facade;

import com.ymkj.cms.biz.api.vo.request.apply.*;
import com.ymkj.cms.biz.common.util.DateUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ApplyEntryPackaging {
	
	/**
	 * 基本信息 
	 * @return
	 */
	public static BasicInfoVO getBasicInfoVO(String name,String idNo){
		//个人信息 start
		PersonInfoVO personInfoVO = getPersonInfoVO(name,idNo);
		//个人信息 end
		
		//工作信息	start
		WorkInfoVO workInfoVO = getWorkInfoVO();
		//工作信息	end
		
		//私营业主信息 start
		PrivateOwnerInfoVO privateOwnerInfoVO = getPrivateOwnerInfoVO();
		//私营业主信息 end
		
		BasicInfoVO basicInfoVO = new BasicInfoVO();
		basicInfoVO.setPersonInfoVO(personInfoVO);
		basicInfoVO.setPrivateOwnerInfoVO(privateOwnerInfoVO);
		basicInfoVO.setWorkInfoVO(workInfoVO);
		return basicInfoVO;
	}
	
	/**
	 * 申请信息
	 * @return
	 */
	public static ApplyInfoVO getApplyInfoVO(String name,String idNo){
		ApplyInfoVO applytInfoVO = new ApplyInfoVO();
		applytInfoVO.setProductCd("00013");  //申请产品
		applytInfoVO.setProductName("公积金贷");;  //申请产品
		applytInfoVO.setApplyLmt(new BigDecimal(15000));//申请金额
		applytInfoVO.setApplyTerm(12);//申请期限
		applytInfoVO.setName(name);//姓名
		applytInfoVO.setIdNo(idNo);		//证件号
		applytInfoVO.setBranchManagerCode("00213223");	//客户经理
		applytInfoVO.setBranchManagerName("赵佳丽");	//客户经理
		applytInfoVO.setCreditApplication("00001");	//贷款用途
		applytInfoVO.setContractBranch("鞍山人民路营业部");		//签约营业部
		applytInfoVO.setContractBranchId(new Long(12827295));	//签约营业部id
		applytInfoVO.setRemark("备注-申请信息填写");				//备注
		applytInfoVO.setIfPri(0);
		applytInfoVO.setCustomerSource("00001");
//		applytInfoVO.setAppInputFlag("app_input");	//APP进件标识	app_input 代表app进件
		
		
//		applytInfoVO.setApplyType("NEW"); 			//申请类型	 NEW 新申请    TOPUP 追加贷款	RELOAN 结清再贷
		applytInfoVO.setApplyInputFlag("applyInput");		//申请件渠道标识	applyInput 普通进件 directApplyInput 直通车进件
		return applytInfoVO;
	}
	
	/**
	 * 资产信息
	 * @return
	 */
	public static AssetsInfoVO getAssetsInfoVO(ApplyInfoVO applytInfoVO){
		
		
		
		// 房产信息 start
		EstateInfoVO estateInfoVO = new EstateInfoVO();
		// 房产信息 end
		if(applytInfoVO.getProductCd().equals("00011")){
			estateInfoVO = getEstateInfoVO();
		}
		
		// 车辆信息 start
		CarInfoVO carInfoVO = new CarInfoVO();
		// 车辆信息 end	
		if(applytInfoVO.getProductCd().equals("00010")){
			carInfoVO = getCarInfoVO();
		}
		
				
		// 保单信息 start
		PolicyInfoVO policyInfoVO = new PolicyInfoVO();
		// 保单信息 end
		if(applytInfoVO.getProductCd().equals("00014")){
			policyInfoVO = getPolicyInfoVO();
		}
		
		
		// 公积金信息 start
		ProvidentInfoVO providentInfoVO = new ProvidentInfoVO();
		// 公积金信息 end
		if(applytInfoVO.getProductCd().equals("00013")){
			providentInfoVO = getProvidentInfoVO();
		}
		
		// 卡友贷信息 start
		CardLoanInfoVO cardLoanInfoVO = new CardLoanInfoVO();
		// 卡友贷信息 end 
		if(applytInfoVO.getProductCd().equals("00014")){
			cardLoanInfoVO = getCardLoanInfoVO();
		}
		
		// 随薪贷信息 start
		SalaryLoanInfoVO salaryLoanInfoVO = new SalaryLoanInfoVO(); 
		// 随薪贷信息 end
		if(applytInfoVO.getProductCd().equals("00001")){
			salaryLoanInfoVO = getSalaryLoanInfoVO(); 
		}
		
		// 网购达人贷信息A start
		MasterLoanInfoVO masterLoanInfoVO = new MasterLoanInfoVO(); 
		// 网购达人贷信息A end
		if(applytInfoVO.getProductCd().equals("00015")){
			masterLoanInfoVO = getMasterLoanInfoVO(); 
		}
		
		// 淘宝商户贷信息 start
		MerchantLoanInfoVO merchantLoanInfoVO = new MerchantLoanInfoVO(); 
		// 淘宝商户贷信息 end
		if(applytInfoVO.getProductCd().equals("00016")){
			merchantLoanInfoVO = getMerchantLoanInfoVO(); 
		}
		
		AssetsInfoVO assetsInfoVO = new AssetsInfoVO(); 
		assetsInfoVO.setCardLoanInfoVO(cardLoanInfoVO);
		assetsInfoVO.setCarInfoVO(carInfoVO);
		assetsInfoVO.setEstateInfoVO(estateInfoVO);
		assetsInfoVO.setMasterLoanInfoVO(masterLoanInfoVO);
		assetsInfoVO.setMerchantLoanInfoVO(merchantLoanInfoVO);
		assetsInfoVO.setPolicyInfoVO(policyInfoVO);
		assetsInfoVO.setProvidentInfoVO(providentInfoVO);
		assetsInfoVO.setSalaryLoanInfoVO(salaryLoanInfoVO);
		return assetsInfoVO;
	}
	
	/**
	 * 多个联系人
	 * @return
	 */
	public static List<ContactInfoVO> getContactInfoVOList(){
		List<ContactInfoVO> contactInfoVOList = new ArrayList<ContactInfoVO>();
		for (int i = 1; i <= 6; i++) {
			contactInfoVOList.add(getContactInfoVO(i));
		}
		return contactInfoVOList;
	}
	
	/**
	 * 联系人信息
	 * @return
	 */
	public static ContactInfoVO getContactInfoVO(int i){
		ContactInfoVO contactInfoVO = new ContactInfoVO();
		contactInfoVO.setContactName("姓名");			//姓名
		contactInfoVO.setContactRelation("0000"+i);		//关系
//		contactInfoVO.setContacGender("M"); 			//性别
		contactInfoVO.setContactCellPhone("1861111222"+i);// 手机
		contactInfoVO.setContactCellPhone_1("1861111221"+i);// 手机2
		contactInfoVO.setIfKnowLoan("N");			// 是否知晓贷款
		contactInfoVO.setContactCorpPhone("0123-1234567-123");// 公司电话号码
		contactInfoVO.setContactCorpPost("D");	// 职务
		contactInfoVO.setContactEmpName("证大拇指");		//公司名称
		contactInfoVO.setSequenceNum(i);			// 排序号
		return contactInfoVO;
	}
	
	/**
	 * 私营业主信息
	 * @return
	 * @throws ParseException 
	 */
	public static PrivateOwnerInfoVO getPrivateOwnerInfoVO(){
		PrivateOwnerInfoVO privateOwnerInfoVO = new PrivateOwnerInfoVO();
		try {
			privateOwnerInfoVO.setSetupDate(DateUtil.strToDateTimeDay("2011-1-4"));
		} catch (ParseException e) {
			e.printStackTrace();
		}// 成立时间
		privateOwnerInfoVO.setEnterpriseRate(new Double(0));// 企业净利润率/%
		privateOwnerInfoVO.setSharesScale(new Double(0));// 占股比例/%
		privateOwnerInfoVO.setRegisterFunds(new BigDecimal(1000000));//注册资本/元
		privateOwnerInfoVO.setPriEnterpriseType("00001");//私营企业类型
		privateOwnerInfoVO.setBusinessPlace("00001");// 经营场所
		privateOwnerInfoVO.setMonthAmt(new BigDecimal(2000));// 每月净利润率/万元
		privateOwnerInfoVO.setEmployeeNum(11);//员工人数/人	
		privateOwnerInfoVO.setSharesName("股东姓名");//	股东姓名（除客户外最大股东）
		privateOwnerInfoVO.setSharesIdNO("352227199107071522");//股东身份证
		privateOwnerInfoVO.setMonthRent(new BigDecimal(10000));
		return privateOwnerInfoVO;
	}
	
	/**
	 * 工作信息
	 * @return
	 */
	public static WorkInfoVO getWorkInfoVO(){
		WorkInfoVO workInfoVO = new WorkInfoVO();
		workInfoVO.setCorpName("工作信息");				// 单位名称
		workInfoVO.setCusWorkType("00001");				// 客户工作类型	
		workInfoVO.setCorpProvinceId(new Long(110000));	// 公司所在省
		workInfoVO.setCorpProvince("北京市");
		workInfoVO.setCorpCityId(new Long(110100));		// 公司所在市
		workInfoVO.setCorpCity("市辖区");
		workInfoVO.setCorpZoneId(new Long(110105));		// 	公司所在区/县
		workInfoVO.setCorpZone("朝阳区");
		workInfoVO.setCorpAddress("大拇指");; //公司地址
		workInfoVO.setCorpPostcode("336111");; //公司邮编
		
		workInfoVO.setCorpDepapment("任职部门");			// 	任职部门
		workInfoVO.setCorpPost("00002");				// 	职务	 
		workInfoVO.setOccupation("00001");			// 职业
		workInfoVO.setCorpPhone("0123-1234567-123");		// 单电
		workInfoVO.setCorpPhoneSec("0123-1234567-112");	// 单电2
		try {
			workInfoVO.setCorpStandFrom(DateUtil.strToDateTimeDay("2014-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}	// 入职时间
		workInfoVO.setCorpPayWay("00001");				// 发薪方式
		workInfoVO.setMonthSalary(new BigDecimal(12221));// 	单位月收入/元
		workInfoVO.setOtherIncome(new BigDecimal(131));// 其他月收入
		workInfoVO.setTotalMonthSalary(new BigDecimal(12352));// 	月总收入/元	
		workInfoVO.setBusinessNetWork("A");; //工商网信息
		workInfoVO.setCorpStructure("00002");; //公司性质
		workInfoVO.setCorpType("00016"); ; //	公司行业类别
		return workInfoVO;
	}
	
	/**
	 * 个人信息 
	 * @return
	 */
	public static PersonInfoVO getPersonInfoVO(String name,String idNo){
		PersonInfoVO personInfoVO = new PersonInfoVO();
		personInfoVO.setName(name);				//姓名		
		personInfoVO.setIdNo(idNo);	//身份证号码
		personInfoVO.setBirthday(new Date());		//生日
		personInfoVO.setGender("男");				//性别
		personInfoVO.setAge(22);					//年龄
		
		
		personInfoVO.setMaritalStatus("00001");			//婚姻状况
		personInfoVO.setChildrenNum(1);				//子女数
		personInfoVO.setQualification("00002");		//最高学历	
		personInfoVO.setGraduationDate(new Date());	//毕业时间
		personInfoVO.setIssuerStateId(new Long(110000));	//户籍所在省
		personInfoVO.setIssuerState("北京市");
		personInfoVO.setIssuerCityId(new Long(110100));	//户籍所在市
		personInfoVO.setIssuerCity("市辖区");
		personInfoVO.setIssuerZoneId(new Long(110102));	//户籍所在区
		personInfoVO.setIssuerZone("西城区");
		personInfoVO.setIdIssuerAddress("江西 景德镇");	//户籍地址
		personInfoVO.setIssuerPostcode("336100");	//户籍邮编
		personInfoVO.setHomeSameRegistered(0);		//家庭住址是否同户籍地址
		personInfoVO.setHomeStateId(new Long(120000));	//家庭所在省 
		personInfoVO.setHomeState("天津市");
		personInfoVO.setHomeCityId(new Long(120100));	//家庭所在市
		personInfoVO.setHomeCity("市辖区");
		personInfoVO.setHomeZoneId(new Long(120105));	//家庭所在区
		personInfoVO.setHomeZone("河北区");
		personInfoVO.setHomeAddress("家庭地址");			//家庭地址
		personInfoVO.setHomePostcode("111222");//家庭住址邮编
		personInfoVO.setHouseType("00001");				//住宅类型
		personInfoVO.setFamilyMonthPay(new BigDecimal(400));//每月家庭支出
		personInfoVO.setMonthMaxRepay(new BigDecimal(700));//可接受的月最高还款
		personInfoVO.setHouseRent(new BigDecimal(2200));  //租金/元
		Random random = new Random();
		int nextInt = random.nextInt(9);
		personInfoVO.setCellphone("1316250"+nextInt+"55"+nextInt);	//手机1
		personInfoVO.setCellphoneSec("18617713220");//手机2
		personInfoVO.setHomePhone1("75478888");//宅电
		personInfoVO.setQqNum("123456");//QQ号
		personInfoVO.setWeChatNum("75478888");//微信号
		personInfoVO.setEmail("123456@qq.com");//电子邮箱
		return personInfoVO;
	}
	
	
	/**
	 * 淘宝商户贷信息
	 * @return
	 */
	public static MerchantLoanInfoVO getMerchantLoanInfoVO(){
		MerchantLoanInfoVO merchantLoanInfoVO = new MerchantLoanInfoVO(); 
		merchantLoanInfoVO.setSetupShopDate(new Date());
		merchantLoanInfoVO.setSellerCreditLevel("l");
		merchantLoanInfoVO.setSellerCreditType("t");
		merchantLoanInfoVO.setRegardedNum(11);
		merchantLoanInfoVO.setBillAmt1(new BigDecimal(100));
		merchantLoanInfoVO.setBillAmt2(new BigDecimal(200));
		merchantLoanInfoVO.setBillAmt3(new BigDecimal(300));
		merchantLoanInfoVO.setBillAmt4(new BigDecimal(400));
		merchantLoanInfoVO.setBillAmt5(new BigDecimal(500));
		merchantLoanInfoVO.setBillAmt6(new BigDecimal(600));
		merchantLoanInfoVO.setPayMonthAmt(new BigDecimal(88));
		return merchantLoanInfoVO;
	}
	
	/**
	 * 网购达人贷信息
	 * @return
	 */
	public static MasterLoanInfoVO getMasterLoanInfoVO(){
		MasterLoanInfoVO masterLoanInfoVO = new MasterLoanInfoVO(); 
		masterLoanInfoVO.setAcctRegisterDate(new Date());
		masterLoanInfoVO.setBuyerCreditLevel("1");
		masterLoanInfoVO.setBuyerCreditType("t");
		masterLoanInfoVO.setGoodRate(new BigDecimal(10.00));
		masterLoanInfoVO.setLastYearPayAmt(new BigDecimal(100000));
		masterLoanInfoVO.setSesameCreditValue(44);
		masterLoanInfoVO.setPayAmt1(new BigDecimal(100000));
		masterLoanInfoVO.setPayAmt2(new BigDecimal(200000));
//		masterLoanInfoVO.setPayAmt3(new BigDecimal(300000));
//		masterLoanInfoVO.setPayMonthAmt(new BigDecimal(400000));
		
		masterLoanInfoVO.setJiDongUserLevel("2");
		masterLoanInfoVO.setWhiteCreditValue(55);
		masterLoanInfoVO.setPastYearShoppingAmount(new BigDecimal(9999));
		return masterLoanInfoVO;
	}
	
	/**
	 * 随薪贷信息
	 * @return
	 */
	public static SalaryLoanInfoVO getSalaryLoanInfoVO(){
		SalaryLoanInfoVO salaryLoanInfoVO = new SalaryLoanInfoVO(); 
		salaryLoanInfoVO.setConditionType("00002");//条件类型
		return salaryLoanInfoVO;
	}
	
	/**
	 * 卡友贷信息
	 * @return
	 */
	public static CardLoanInfoVO getCardLoanInfoVO(){
		CardLoanInfoVO cardLoanInfoVO = new CardLoanInfoVO(); 
		cardLoanInfoVO.setIssuerDate(new Date());
		cardLoanInfoVO.setCreditLimit(new BigDecimal(5464655));
		cardLoanInfoVO.setBillAmt1(new BigDecimal(1464655));
		cardLoanInfoVO.setBillAmt2(new BigDecimal(2464655));
		cardLoanInfoVO.setBillAmt3(new BigDecimal(3464655));
		cardLoanInfoVO.setBillAmt4(new BigDecimal(4464655));
		cardLoanInfoVO.setPayMonthAmt(new BigDecimal(1111));
		return cardLoanInfoVO;
	}
	
	
	/**
	 * 公积金信息
	 * @return
	 */
	public static ProvidentInfoVO getProvidentInfoVO(){
		ProvidentInfoVO providentInfoVO = new ProvidentInfoVO();
		providentInfoVO.setOpenAccountDate(new Date());
		providentInfoVO.setDepositRate(new Double(111));
		providentInfoVO.setMonthDepositAmt(new BigDecimal(11));
		providentInfoVO.setDepositBase(new BigDecimal(11));
		providentInfoVO.setProvidentInfo("A");
		providentInfoVO.setPaymentUnit("Y");
		providentInfoVO.setPaymentMonthNum(1);
		return providentInfoVO;
	}
	
	/**
	 * 保单信息
	 * @return
	 */
	public static PolicyInfoVO getPolicyInfoVO(){
		PolicyInfoVO policyInfoVO = new PolicyInfoVO(); 
		policyInfoVO.setInsuranceAmt(new BigDecimal(100000));
		policyInfoVO.setInsuranceTerm(5);
		policyInfoVO.setPaidTerm(1);
		policyInfoVO.setLastPaymentDate(new Date());
		policyInfoVO.setPaymentMethod("M");
		policyInfoVO.setYearPaymentAmt(new BigDecimal(200000));
		policyInfoVO.setPolicyCheck("Y");
		policyInfoVO.setPolicyRelation("R");
		return policyInfoVO;
	}
	
	/**
	 * 车辆信息
	 * @return
	 */
	public static CarInfoVO getCarInfoVO(){
		CarInfoVO carInfoVO = new CarInfoVO(); 
		carInfoVO.setCarType("00001");
		carInfoVO.setCarLoan("Y");
		try {
			carInfoVO.setCarBuyDate(DateUtil.strToDateTimeDay("2015-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		carInfoVO.setNakedCarPrice(new BigDecimal(400000));
		carInfoVO.setCarBuyPrice(new BigDecimal(500000));
		carInfoVO.setCarLoanTerm(1);
		carInfoVO.setMonthPaymentAmt(new BigDecimal(600000));
		carInfoVO.setLocalPlate("Y");
		carInfoVO.setPlateNum("鄂-8888888");
		return carInfoVO;
	}
	
	/**
	 * 房产信息
	 * @return
	 */
	public static EstateInfoVO getEstateInfoVO(){
		EstateInfoVO estateInfoVO = new EstateInfoVO(); 
		estateInfoVO.setEstateType("lx");// 房产类型
		estateInfoVO.setEstateStateId(new Long(1));
		estateInfoVO.setEstateCityId(new Long(2));
		estateInfoVO.setEstateZoneId(new Long(3));
		estateInfoVO.setEstateAddress("房产信息");
		estateInfoVO.setEstateLoan("sl");
		estateInfoVO.setEstateBuyDate(new Date());
		estateInfoVO.setEstateAmt(new BigDecimal(100000));
		estateInfoVO.setReferenceAmt(new BigDecimal(100001));
		estateInfoVO.setEstateLoanAmt(new BigDecimal(200001));
		estateInfoVO.setMonthPaymentAmt(new BigDecimal(300001));
		estateInfoVO.setHasRepaymentNum(4);
		estateInfoVO.setBuiltupArea(new Double(15.54));
		estateInfoVO.setHouseOwnership("sh");
		estateInfoVO.setEquityRate(new Double(0.54));
		estateInfoVO.setIfMe("1");//	单据户名为本人
		return estateInfoVO;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtil.strToDateTimeDay("2014-01-01"));
	}
}
