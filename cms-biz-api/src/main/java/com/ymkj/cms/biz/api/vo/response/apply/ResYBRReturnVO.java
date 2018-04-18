package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResYBRReturnVO implements Serializable{
	//--bms_loan_base
	private Long enterBranchId;//进件门店（复核保存）
	private String enterBranch;//进件门店（复核保存）
	
	private String productCd	; //申请产品
	private String productName; //产品名称
	private Double applyLmt	; //申请金额
	private Integer applyTerm	; //	申请期限
	private String applyType;//申请类型	 NEW 新申请    TOPUP 追加贷款	RELOAN 结清再贷
	private String nameAndidNoIsBlack;//姓名身份证是否匹配黑名单
	
	private String enterBranchCity;//进件营业部所属城市
	private String enterBranchSubsection;//进件营业部所属分部
	private String enterBranchArea;//进件营业部所属区域
	
	private Date commitDate;//提交时间
	private Float amoutIncome;//收入证明
	private String ifCreditRecord;//有无信用记录
	
	private String antiFraudScore;//  反欺诈评分
	private String antiFraudWarning;//   反欺诈预警
	private String antiRiskRate;//  欺诈风险评估
	private String lastTimeStatus;//最近一次借款申请状态
	
	//--房产
	private Long estateStateId; // 房产所在省ID
	private Long estateCityId; //房产所在市ID
	private Long estateZoneId; //房产所在区ID	
	
	private String estateState; // 房产所在省
	private String estateCity; //房产所在市
	private String estateZone; //房产所在区	
	
	private String estateLoan; //房贷情况
	private Date estateBuyDate; //购买时间
	
	private Double monthPaymentAmt; //	月供
	
	//--车产
	private Date carBuyDate	; // 购买时间
	private Double 	nakedCarPrice; // 	裸车价/元	
	private Double 	carBuyPrice; // 	购买价/元
	private String plateNum; // 车牌号
	
	//--保单
	private Integer paidTerm; // 	已缴年限	
	private Double yearPaymentAmt; //年缴金额
	
	//--公积金
	private Double monthDepositAmt; // 月缴存额
	private Double depositBase; // 缴存基数
	
	//--卡友贷
	private Date issuerDate; //发卡时间
	private Double creditLimit; //额度
	private Double carbillAmt1; //近4个月账单金额依次为1    冲突
	private Double carbillAmt2; //4个月账单金额依次为2金额依次为1		 冲突	
	private Double carbillAmt3; //近4个月账单金额依次为3		 冲突		
	private Double carbillAmt4; //近4个月账单金额依次为4		 冲突	
	private Double carpayMonthAmt; //月均                            冲突
	
	//--网购达人
	private String buyerCreditLevel; //买家信用等级
	private String buyerCreditType; //买家信用类型
	private Double goodRate; //	好评率/%
	private Double lastYearPayAmt; //	上一年度支出额
	private Integer sesameCreditValue; //	芝麻信用分
	private Double payAmt3; //	近3个月支出总额3
	private Double payAmt2; //	近3个月支出总额2
	private Double payAmt1; //	近3个月支出总额1
	private Double masterpayMonthAmt; //月均	                  冲突
			
	private String jiDongUserLevel; //	京东用户等级
	private Integer whiteCreditValue; //	小白信用分
	private Double pastYearShoppingAmount; //	近一年实际消费金额
	
	//--淘宝商户
	private Date setupShopDate; //开店时间
	private String sellerCreditLevel; //卖家信用等级
	private String sellerCreditType; //卖家信用类型
	private Integer regardedNum; //	近半年好评数 
	private Double taobillAmt1; //	近6个月账单金额依次为1    冲突
	private Double taobillAmt2; //	近6个月账单金额依次为2    冲突
	private Double taobillAmt3; //	近6个月账单金额依次为3    冲突
	private Double taobillAmt4; //	近6个月账单金额依次为4    冲突
	private Double taobillAmt5; //	近6个月账单金额依次为5    冲突
	private Double taobillAmt6; //	近6个月账单金额依次为6    冲突
	private Double taopayMonthAmt; //	月均   冲突
	
	//--个人信息 
	private String idNo;        //身份证号码
	private String gender;      //性别
	private Integer age;        //年龄
	private String maritalStatus;        //婚姻状况
	private String qualification;        //最高学历
	private Date graduationDate;        //毕业时间
	private Long issuerStateId;        //户籍所在省ID
	private Long issuerCityId;        //户籍所在市ID
	private Long issuerZoneId;        //户籍所在区ID
	private String issuerState;	//户籍所在省
	private String issuerCity;	//户籍所在市
	private String issuerZone;	//户籍所在区
	
	private Long homeStateId;        //家庭所在省ID
	private Long homeCityId;        //家庭所在市ID
	private Long homeZoneId;        //家庭所在区ID
	private String homeState;		//家庭所在省名称
	private String homeCity;		//家庭所在市
	private String homeZone;		//家庭所在区
	private String houseType;        //住宅类型
	private Double familyMonthPay; //每月家庭支出
	private Double monthMaxRepay;  //可接受的月最高还款
	
	
	//--工作信息
	private String cusWorkType; // 客户工作类型		
	private Long corpProvinceId; // 公司所在省ID
	private Long corpCityId; // 公司所在市ID
	private Long corpZoneId; // 	公司所在区/县ID
	
	private String corpProvince; // 公司所在省
	private String corpCity; // 公司所在市
	private String corpZone; // 	公司所在区/县
	
	private String corpStructure; //公司性质
	private String corpType ; //	公司行业类别
	
	private String corpPayWay; // 发薪方式
	private Double monthSalary; // 	单位月收入/元
	private Double otherIncome; // 其他月收入
	private Double totalMonthSalary; // 	月总收入/元
	
	//--私营业主信息
	private Date setupDate; // 成立时间	
	private Double sharesScale; // 占股比例/%
	private Double registerFunds; //注册资本/万元
	private String priEnterpriseType; //私营企业类型
	
	//--吊审核接口返回
	private List<Integer> personalWater = new ArrayList<Integer>();// 个人流水
	private List<Integer> personMonthAverage = new ArrayList<Integer>();// 个人流水月均
	private List<Integer> publicWater = new ArrayList<Integer>();// 对公流水
	private List<Integer> publicMonthAverage = new ArrayList<Integer>();// 对公流水月均
	private Integer monthAverage; // 月平均金额
	private Integer threeMonthsCount;// 近三个月查询
	private Integer oneMonthsCount;// 近一个月查询
	private Integer creditLoanDebt;// 信用卡贷款负债
	private Integer outDebtTotal;// 外部负债总额
	private String approvalProductCd;// 审批产品
	private Integer approvalCheckIncome;// 核实收入
	private Integer approvalLimit;// 审批额度
	private Integer approvalTerm;// 审批期限
	
	//--吊核心接口返回
	private String ifHaveBreaks = "N";//上笔结清是否有减免
	private List<Integer> repaymentTerm;//还款期数
	
	private List<Date> realityRepaymentDate;//实际还款日期
	private List<Date> shouldRepaymentDate;//应还日期
	private List<Integer> repaymentStatue;//还款状态
	private List<Double> grantMoney;//放款金额
	
	//--新增的返回值
	private List<String> huazhengNetPlayLong;//华征手机在网时长  目前没给值
	private List<String> huazhengNameLook;//华征手机实名认证   目前没给值
	
	private Double productRate;//产品费率  产品表
	private Double productLetterRate;//产品总信用负债率  产品表
	private Double applyTypeLetterRate;//申请类型总信用负债率 
	private Double applyTypeInsideRate;//申请类型内部负债率  
	private Double onUnclearedRate;//当前未结清借款放款产品费率
	private String rtfNodeState;//三级节点状态取得是环节状态         这个状态目前信审系统那边自己转换了，不用去管
	private List<String> ccRuleSet =new ArrayList<String>();//cc 
	private Date faimalyGrantDate;// 房贷发放年月
	private Double anticipationIncome;//系统预判核实收入（建议核实收入，取信审调用规则6个字段更新的接口里面的数据）
	
	private Double internalDebtRatio;//内部负债率(取信审调用规则6个字段更新的接口里面的数据)
	private Double totalDebtRatio;//总负债率(取信审调用规则6个字段更新的接口里面的数据)
	public Long getEnterBranchId() {
		return enterBranchId;
	}
	public void setEnterBranchId(Long enterBranchId) {
		this.enterBranchId = enterBranchId;
	}
	public String getEnterBranch() {
		return enterBranch;
	}
	public void setEnterBranch(String enterBranch) {
		this.enterBranch = enterBranch;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getNameAndidNoIsBlack() {
		return nameAndidNoIsBlack;
	}
	public void setNameAndidNoIsBlack(String nameAndidNoIsBlack) {
		this.nameAndidNoIsBlack = nameAndidNoIsBlack;
	}
	public String getEnterBranchCity() {
		return enterBranchCity;
	}
	public void setEnterBranchCity(String enterBranchCity) {
		this.enterBranchCity = enterBranchCity;
	}
	public String getEnterBranchSubsection() {
		return enterBranchSubsection;
	}
	public void setEnterBranchSubsection(String enterBranchSubsection) {
		this.enterBranchSubsection = enterBranchSubsection;
	}
	public String getEnterBranchArea() {
		return enterBranchArea;
	}
	public void setEnterBranchArea(String enterBranchArea) {
		this.enterBranchArea = enterBranchArea;
	}
	public Date getCommitDate() {
		return commitDate;
	}
	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}
	
	public Float getAmoutIncome() {
		return amoutIncome;
	}
	public void setAmoutIncome(Float amoutIncome) {
		this.amoutIncome = amoutIncome;
	}
	public String getIfCreditRecord() {
		return ifCreditRecord;
	}
	public void setIfCreditRecord(String ifCreditRecord) {
		this.ifCreditRecord = ifCreditRecord;
	}
	public String getAntiFraudScore() {
		return antiFraudScore;
	}
	public void setAntiFraudScore(String antiFraudScore) {
		this.antiFraudScore = antiFraudScore;
	}
	public String getAntiFraudWarning() {
		return antiFraudWarning;
	}
	public void setAntiFraudWarning(String antiFraudWarning) {
		this.antiFraudWarning = antiFraudWarning;
	}
	public String getAntiRiskRate() {
		return antiRiskRate;
	}
	public void setAntiRiskRate(String antiRiskRate) {
		this.antiRiskRate = antiRiskRate;
	}
	public String getLastTimeStatus() {
		return lastTimeStatus;
	}
	public void setLastTimeStatus(String lastTimeStatus) {
		this.lastTimeStatus = lastTimeStatus;
	}
	public Long getEstateStateId() {
		return estateStateId;
	}
	public void setEstateStateId(Long estateStateId) {
		this.estateStateId = estateStateId;
	}
	public Long getEstateCityId() {
		return estateCityId;
	}
	public void setEstateCityId(Long estateCityId) {
		this.estateCityId = estateCityId;
	}
	public Long getEstateZoneId() {
		return estateZoneId;
	}
	public void setEstateZoneId(Long estateZoneId) {
		this.estateZoneId = estateZoneId;
	}
	public String getEstateState() {
		return estateState;
	}
	public void setEstateState(String estateState) {
		this.estateState = estateState;
	}
	public String getEstateCity() {
		return estateCity;
	}
	public void setEstateCity(String estateCity) {
		this.estateCity = estateCity;
	}
	public String getEstateZone() {
		return estateZone;
	}
	public void setEstateZone(String estateZone) {
		this.estateZone = estateZone;
	}
	public String getEstateLoan() {
		return estateLoan;
	}
	public void setEstateLoan(String estateLoan) {
		this.estateLoan = estateLoan;
	}
	public Date getEstateBuyDate() {
		return estateBuyDate;
	}
	public void setEstateBuyDate(Date estateBuyDate) {
		this.estateBuyDate = estateBuyDate;
	}
	
	public Date getCarBuyDate() {
		return carBuyDate;
	}
	public void setCarBuyDate(Date carBuyDate) {
		this.carBuyDate = carBuyDate;
	}
	public Double getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(Double applyLmt) {
		this.applyLmt = applyLmt;
	}
	public Double getMonthPaymentAmt() {
		return monthPaymentAmt;
	}
	public void setMonthPaymentAmt(Double monthPaymentAmt) {
		this.monthPaymentAmt = monthPaymentAmt;
	}
	public Double getNakedCarPrice() {
		return nakedCarPrice;
	}
	public void setNakedCarPrice(Double nakedCarPrice) {
		this.nakedCarPrice = nakedCarPrice;
	}
	public Double getCarBuyPrice() {
		return carBuyPrice;
	}
	public void setCarBuyPrice(Double carBuyPrice) {
		this.carBuyPrice = carBuyPrice;
	}
	public Integer getPaidTerm() {
		return paidTerm;
	}
	public void setPaidTerm(Integer paidTerm) {
		this.paidTerm = paidTerm;
	}
	public Double getYearPaymentAmt() {
		return yearPaymentAmt;
	}
	public void setYearPaymentAmt(Double yearPaymentAmt) {
		this.yearPaymentAmt = yearPaymentAmt;
	}
	public Double getMonthDepositAmt() {
		return monthDepositAmt;
	}
	public void setMonthDepositAmt(Double monthDepositAmt) {
		this.monthDepositAmt = monthDepositAmt;
	}
	public Double getDepositBase() {
		return depositBase;
	}
	public void setDepositBase(Double depositBase) {
		this.depositBase = depositBase;
	}
	public Date getIssuerDate() {
		return issuerDate;
	}
	public void setIssuerDate(Date issuerDate) {
		this.issuerDate = issuerDate;
	}
	public Double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public Double getCarbillAmt1() {
		return carbillAmt1;
	}
	public void setCarbillAmt1(Double carbillAmt1) {
		this.carbillAmt1 = carbillAmt1;
	}
	public Double getCarbillAmt2() {
		return carbillAmt2;
	}
	public void setCarbillAmt2(Double carbillAmt2) {
		this.carbillAmt2 = carbillAmt2;
	}
	public Double getCarbillAmt3() {
		return carbillAmt3;
	}
	public void setCarbillAmt3(Double carbillAmt3) {
		this.carbillAmt3 = carbillAmt3;
	}
	public Double getCarbillAmt4() {
		return carbillAmt4;
	}
	public void setCarbillAmt4(Double carbillAmt4) {
		this.carbillAmt4 = carbillAmt4;
	}
	public Double getCarpayMonthAmt() {
		return carpayMonthAmt;
	}
	public void setCarpayMonthAmt(Double carpayMonthAmt) {
		this.carpayMonthAmt = carpayMonthAmt;
	}
	public String getBuyerCreditLevel() {
		return buyerCreditLevel;
	}
	public void setBuyerCreditLevel(String buyerCreditLevel) {
		this.buyerCreditLevel = buyerCreditLevel;
	}
	public String getBuyerCreditType() {
		return buyerCreditType;
	}
	public void setBuyerCreditType(String buyerCreditType) {
		this.buyerCreditType = buyerCreditType;
	}
	public Double getGoodRate() {
		return goodRate;
	}
	public void setGoodRate(Double goodRate) {
		this.goodRate = goodRate;
	}
	public Double getLastYearPayAmt() {
		return lastYearPayAmt;
	}
	public void setLastYearPayAmt(Double lastYearPayAmt) {
		this.lastYearPayAmt = lastYearPayAmt;
	}
	public Integer getSesameCreditValue() {
		return sesameCreditValue;
	}
	public void setSesameCreditValue(Integer sesameCreditValue) {
		this.sesameCreditValue = sesameCreditValue;
	}
	public Double getPayAmt3() {
		return payAmt3;
	}
	public void setPayAmt3(Double payAmt3) {
		this.payAmt3 = payAmt3;
	}
	public Double getPayAmt2() {
		return payAmt2;
	}
	public void setPayAmt2(Double payAmt2) {
		this.payAmt2 = payAmt2;
	}
	public Double getPayAmt1() {
		return payAmt1;
	}
	public void setPayAmt1(Double payAmt1) {
		this.payAmt1 = payAmt1;
	}
	public Double getMasterpayMonthAmt() {
		return masterpayMonthAmt;
	}
	public void setMasterpayMonthAmt(Double masterpayMonthAmt) {
		this.masterpayMonthAmt = masterpayMonthAmt;
	}
	public String getJiDongUserLevel() {
		return jiDongUserLevel;
	}
	public void setJiDongUserLevel(String jiDongUserLevel) {
		this.jiDongUserLevel = jiDongUserLevel;
	}
	public Integer getWhiteCreditValue() {
		return whiteCreditValue;
	}
	public void setWhiteCreditValue(Integer whiteCreditValue) {
		this.whiteCreditValue = whiteCreditValue;
	}
	public Double getPastYearShoppingAmount() {
		return pastYearShoppingAmount;
	}
	public void setPastYearShoppingAmount(Double pastYearShoppingAmount) {
		this.pastYearShoppingAmount = pastYearShoppingAmount;
	}
	public Date getSetupShopDate() {
		return setupShopDate;
	}
	public void setSetupShopDate(Date setupShopDate) {
		this.setupShopDate = setupShopDate;
	}
	public String getSellerCreditLevel() {
		return sellerCreditLevel;
	}
	public void setSellerCreditLevel(String sellerCreditLevel) {
		this.sellerCreditLevel = sellerCreditLevel;
	}
	public String getSellerCreditType() {
		return sellerCreditType;
	}
	public void setSellerCreditType(String sellerCreditType) {
		this.sellerCreditType = sellerCreditType;
	}
	public Integer getRegardedNum() {
		return regardedNum;
	}
	public void setRegardedNum(Integer regardedNum) {
		this.regardedNum = regardedNum;
	}
	public Double getTaobillAmt1() {
		return taobillAmt1;
	}
	public void setTaobillAmt1(Double taobillAmt1) {
		this.taobillAmt1 = taobillAmt1;
	}
	public Double getTaobillAmt2() {
		return taobillAmt2;
	}
	public void setTaobillAmt2(Double taobillAmt2) {
		this.taobillAmt2 = taobillAmt2;
	}
	public Double getTaobillAmt3() {
		return taobillAmt3;
	}
	public void setTaobillAmt3(Double taobillAmt3) {
		this.taobillAmt3 = taobillAmt3;
	}
	public Double getTaobillAmt4() {
		return taobillAmt4;
	}
	public void setTaobillAmt4(Double taobillAmt4) {
		this.taobillAmt4 = taobillAmt4;
	}
	public Double getTaobillAmt5() {
		return taobillAmt5;
	}
	public void setTaobillAmt5(Double taobillAmt5) {
		this.taobillAmt5 = taobillAmt5;
	}
	public Double getTaobillAmt6() {
		return taobillAmt6;
	}
	public void setTaobillAmt6(Double taobillAmt6) {
		this.taobillAmt6 = taobillAmt6;
	}
	public Double getTaopayMonthAmt() {
		return taopayMonthAmt;
	}
	public void setTaopayMonthAmt(Double taopayMonthAmt) {
		this.taopayMonthAmt = taopayMonthAmt;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	public Long getIssuerStateId() {
		return issuerStateId;
	}
	public void setIssuerStateId(Long issuerStateId) {
		this.issuerStateId = issuerStateId;
	}
	public Long getIssuerCityId() {
		return issuerCityId;
	}
	public void setIssuerCityId(Long issuerCityId) {
		this.issuerCityId = issuerCityId;
	}
	public Long getIssuerZoneId() {
		return issuerZoneId;
	}
	public void setIssuerZoneId(Long issuerZoneId) {
		this.issuerZoneId = issuerZoneId;
	}
	public String getIssuerState() {
		return issuerState;
	}
	public void setIssuerState(String issuerState) {
		this.issuerState = issuerState;
	}
	public String getIssuerCity() {
		return issuerCity;
	}
	public void setIssuerCity(String issuerCity) {
		this.issuerCity = issuerCity;
	}
	public String getIssuerZone() {
		return issuerZone;
	}
	public void setIssuerZone(String issuerZone) {
		this.issuerZone = issuerZone;
	}
	public Long getHomeStateId() {
		return homeStateId;
	}
	public void setHomeStateId(Long homeStateId) {
		this.homeStateId = homeStateId;
	}
	public Long getHomeCityId() {
		return homeCityId;
	}
	public void setHomeCityId(Long homeCityId) {
		this.homeCityId = homeCityId;
	}
	public Long getHomeZoneId() {
		return homeZoneId;
	}
	public void setHomeZoneId(Long homeZoneId) {
		this.homeZoneId = homeZoneId;
	}
	public String getHomeState() {
		return homeState;
	}
	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public String getHomeZone() {
		return homeZone;
	}
	public void setHomeZone(String homeZone) {
		this.homeZone = homeZone;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public Double getFamilyMonthPay() {
		return familyMonthPay;
	}
	public void setFamilyMonthPay(Double familyMonthPay) {
		this.familyMonthPay = familyMonthPay;
	}
	public Double getMonthMaxRepay() {
		return monthMaxRepay;
	}
	public void setMonthMaxRepay(Double monthMaxRepay) {
		this.monthMaxRepay = monthMaxRepay;
	}
	public String getCusWorkType() {
		return cusWorkType;
	}
	public void setCusWorkType(String cusWorkType) {
		this.cusWorkType = cusWorkType;
	}
	public Long getCorpProvinceId() {
		return corpProvinceId;
	}
	public void setCorpProvinceId(Long corpProvinceId) {
		this.corpProvinceId = corpProvinceId;
	}
	public Long getCorpCityId() {
		return corpCityId;
	}
	public void setCorpCityId(Long corpCityId) {
		this.corpCityId = corpCityId;
	}
	public Long getCorpZoneId() {
		return corpZoneId;
	}
	public void setCorpZoneId(Long corpZoneId) {
		this.corpZoneId = corpZoneId;
	}
	public String getCorpProvince() {
		return corpProvince;
	}
	public void setCorpProvince(String corpProvince) {
		this.corpProvince = corpProvince;
	}
	public String getCorpCity() {
		return corpCity;
	}
	public void setCorpCity(String corpCity) {
		this.corpCity = corpCity;
	}
	public String getCorpZone() {
		return corpZone;
	}
	public void setCorpZone(String corpZone) {
		this.corpZone = corpZone;
	}
	public String getCorpStructure() {
		return corpStructure;
	}
	public void setCorpStructure(String corpStructure) {
		this.corpStructure = corpStructure;
	}
	public String getCorpType() {
		return corpType;
	}
	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	public String getCorpPayWay() {
		return corpPayWay;
	}
	public void setCorpPayWay(String corpPayWay) {
		this.corpPayWay = corpPayWay;
	}
	public Double getMonthSalary() {
		return monthSalary;
	}
	public void setMonthSalary(Double monthSalary) {
		this.monthSalary = monthSalary;
	}
	public Double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}
	public Double getTotalMonthSalary() {
		return totalMonthSalary;
	}
	public void setTotalMonthSalary(Double totalMonthSalary) {
		this.totalMonthSalary = totalMonthSalary;
	}
	public Date getSetupDate() {
		return setupDate;
	}
	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}
	public Double getSharesScale() {
		return sharesScale;
	}
	public void setSharesScale(Double sharesScale) {
		this.sharesScale = sharesScale;
	}
	public Double getRegisterFunds() {
		return registerFunds;
	}
	public void setRegisterFunds(Double registerFunds) {
		this.registerFunds = registerFunds;
	}
	public String getPriEnterpriseType() {
		return priEnterpriseType;
	}
	public void setPriEnterpriseType(String priEnterpriseType) {
		this.priEnterpriseType = priEnterpriseType;
	}
	public List<Integer> getPersonalWater() {
		return personalWater;
	}
	public void setPersonalWater(List<Integer> personalWater) {
		this.personalWater = personalWater;
	}
	public List<Integer> getPersonMonthAverage() {
		return personMonthAverage;
	}
	public void setPersonMonthAverage(List<Integer> personMonthAverage) {
		this.personMonthAverage = personMonthAverage;
	}
	public List<Integer> getPublicWater() {
		return publicWater;
	}
	public void setPublicWater(List<Integer> publicWater) {
		this.publicWater = publicWater;
	}
	public List<Integer> getPublicMonthAverage() {
		return publicMonthAverage;
	}
	public void setPublicMonthAverage(List<Integer> publicMonthAverage) {
		this.publicMonthAverage = publicMonthAverage;
	}
	public Integer getMonthAverage() {
		return monthAverage;
	}
	public void setMonthAverage(Integer monthAverage) {
		this.monthAverage = monthAverage;
	}
	public Integer getThreeMonthsCount() {
		return threeMonthsCount;
	}
	public void setThreeMonthsCount(Integer threeMonthsCount) {
		this.threeMonthsCount = threeMonthsCount;
	}
	public Integer getOneMonthsCount() {
		return oneMonthsCount;
	}
	public void setOneMonthsCount(Integer oneMonthsCount) {
		this.oneMonthsCount = oneMonthsCount;
	}
	public Integer getCreditLoanDebt() {
		return creditLoanDebt;
	}
	public void setCreditLoanDebt(Integer creditLoanDebt) {
		this.creditLoanDebt = creditLoanDebt;
	}
	public Integer getOutDebtTotal() {
		return outDebtTotal;
	}
	public void setOutDebtTotal(Integer outDebtTotal) {
		this.outDebtTotal = outDebtTotal;
	}
	public String getApprovalProductCd() {
		return approvalProductCd;
	}
	public void setApprovalProductCd(String approvalProductCd) {
		this.approvalProductCd = approvalProductCd;
	}
	public Integer getApprovalCheckIncome() {
		return approvalCheckIncome;
	}
	public void setApprovalCheckIncome(Integer approvalCheckIncome) {
		this.approvalCheckIncome = approvalCheckIncome;
	}
	public Integer getApprovalLimit() {
		return approvalLimit;
	}
	public void setApprovalLimit(Integer approvalLimit) {
		this.approvalLimit = approvalLimit;
	}
	public Integer getApprovalTerm() {
		return approvalTerm;
	}
	public void setApprovalTerm(Integer approvalTerm) {
		this.approvalTerm = approvalTerm;
	}
	public String getIfHaveBreaks() {
		return ifHaveBreaks;
	}
	public void setIfHaveBreaks(String ifHaveBreaks) {
		this.ifHaveBreaks = ifHaveBreaks;
	}
	public List<Integer> getRepaymentTerm() {
		return repaymentTerm;
	}
	public void setRepaymentTerm(List<Integer> repaymentTerm) {
		this.repaymentTerm = repaymentTerm;
	}
	public List<Date> getRealityRepaymentDate() {
		return realityRepaymentDate;
	}
	public void setRealityRepaymentDate(List<Date> realityRepaymentDate) {
		this.realityRepaymentDate = realityRepaymentDate;
	}
	public List<Date> getShouldRepaymentDate() {
		return shouldRepaymentDate;
	}
	public void setShouldRepaymentDate(List<Date> shouldRepaymentDate) {
		this.shouldRepaymentDate = shouldRepaymentDate;
	}
	public List<Integer> getRepaymentStatue() {
		return repaymentStatue;
	}
	public void setRepaymentStatue(List<Integer> repaymentStatue) {
		this.repaymentStatue = repaymentStatue;
	}
	public List<Double> getGrantMoney() {
		return grantMoney;
	}
	public void setGrantMoney(List<Double> grantMoney) {
		this.grantMoney = grantMoney;
	}
	
	
	public List<String> getHuazhengNetPlayLong() {
		return huazhengNetPlayLong;
	}
	public void setHuazhengNetPlayLong(List<String> huazhengNetPlayLong) {
		this.huazhengNetPlayLong = huazhengNetPlayLong;
	}
	public List<String> getHuazhengNameLook() {
		return huazhengNameLook;
	}
	public void setHuazhengNameLook(List<String> huazhengNameLook) {
		this.huazhengNameLook = huazhengNameLook;
	}
	public Double getProductRate() {
		return productRate;
	}
	public void setProductRate(Double productRate) {
		this.productRate = productRate;
	}
	public Double getProductLetterRate() {
		return productLetterRate;
	}
	public void setProductLetterRate(Double productLetterRate) {
		this.productLetterRate = productLetterRate;
	}
	public Double getApplyTypeLetterRate() {
		return applyTypeLetterRate;
	}
	public void setApplyTypeLetterRate(Double applyTypeLetterRate) {
		this.applyTypeLetterRate = applyTypeLetterRate;
	}
	public Double getApplyTypeInsideRate() {
		return applyTypeInsideRate;
	}
	public void setApplyTypeInsideRate(Double applyTypeInsideRate) {
		this.applyTypeInsideRate = applyTypeInsideRate;
	}
	public Double getOnUnclearedRate() {
		return onUnclearedRate;
	}
	public void setOnUnclearedRate(Double onUnclearedRate) {
		this.onUnclearedRate = onUnclearedRate;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	
	public List<String> getCcRuleSet() {
		return ccRuleSet;
	}
	public void setCcRuleSet(List<String> ccRuleSet) {
		this.ccRuleSet = ccRuleSet;
	}
	public Date getFaimalyGrantDate() {
		return faimalyGrantDate;
	}
	public void setFaimalyGrantDate(Date faimalyGrantDate) {
		this.faimalyGrantDate = faimalyGrantDate;
	}
	public Double getAnticipationIncome() {
		return anticipationIncome;
	}
	public void setAnticipationIncome(Double anticipationIncome) {
		this.anticipationIncome = anticipationIncome;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public Double getInternalDebtRatio() {
		return internalDebtRatio;
	}
	public void setInternalDebtRatio(Double internalDebtRatio) {
		this.internalDebtRatio = internalDebtRatio;
	}
	public Double getTotalDebtRatio() {
		return totalDebtRatio;
	}
	public void setTotalDebtRatio(Double totalDebtRatio) {
		this.totalDebtRatio = totalDebtRatio;
	}
	
	
	
}
