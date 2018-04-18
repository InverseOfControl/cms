package com.ymkj.cms.biz.api.vo.request.apply;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqValidateVo extends Request {
	
	private static final long serialVersionUID = 1L;
	
	private String loanNo;//借款编号
	private String name; //申请客户姓名
	private String idNo; //申请客户身份证
	
	private String ifHaveBreaks = "N";//上笔结清是否有减免
	private Integer protectDays;//保护天数 
	private List<Integer> repaymentTerm;//还款期数
	private List<Date> realityRepaymentDate;//实际还款日期
	private List<Date> shouldRepaymentDate;//应还日期
	private String ifHaveHistoryApply = "N";//是否有历史借款
	private List<Integer> repaymentStatue;//还款状态
	
	private String ifHaveValidate = "N"; //是否有借款 Y有  N无
	private String previousStatus;//上笔借款状态
	private String previousRtfState;//上笔借款环节 
	private Date previousRefuseDate;//上笔借款被拒绝时间
	private Date previousCancelDate;//上笔借款被取消时间
	private String previousOwningBranchId;//上笔借款录单营业部
	private Date previousFirstCommitDate;//上笔提交信审时间
	private Integer limitDays;//限制天数
	
	private Long issuerStateId; //户籍所在省 
	private Long issuerCityId; //户籍所在市 	 
	private Long issuerZoneId; //户籍所在区/县	
	private Long homeStateId; //	家庭所在省
	private Long homeCityId; //	家庭所在市
	private Long homeZoneId; //家庭所在区县
	private Long corpProvinceId; //公司所在省
	private Long corpCityId; //公司所在市
	private Long corpZoneId; //公司所在区/县
	private String  plateNum; //车牌号
	
	private String productCode;		//当前申请   借款产品
	private String ifBlacklist = "N";		//当前申请   是否匹配黑名单  Y匹配  N不匹配
	private String applyType;		//当前申请   申请类型
	
	private Date appServiceClaimDate;	//APP客服认领时间
	private String appApplyInput = "N";		//当前申请   是否APP进件   Y是  N否
	
	private Date applyDate;			//当前申请   申请创建时间
	private String owningBranchId;	//当前申请  进件营业部
	private String owningBranchCity;//当前申请  进件营业部城市
	private String owningBranchDivision;	//当前申请  进件营业部分部
	private String owningBranchArea;	//当前申请  进件营业部区域
	
	private Date firstCommitDate;//提交信审时间
	private Date firstInModifyDate;			//当前申请  首次进入录入修改时间
	private String rtfState="SQLR";//当前借款处于业务环节
	
	private List<String> ifThirdOrgReturn;//第三方接口是否返回结果
	private List<Date> thirdOrgReturnDate;//第三方接口是否返回结果时间
	
	private List<String> holidays;//	非工作日期		

	private String ifDispatchList = "N"; //是否在已分派名单(电销)
	
	private String ifHighQualityCustomers = "N";//是否是优质客户
	
	
	
	
	
	
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

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getIfDispatchList() {
		return ifDispatchList;
	}

	public void setIfDispatchList(String ifDispatchList) {
		this.ifDispatchList = ifDispatchList;
	}

	public String getIfHighQualityCustomers() {
		return ifHighQualityCustomers;
	}

	public void setIfHighQualityCustomers(String ifHighQualityCustomers) {
		this.ifHighQualityCustomers = ifHighQualityCustomers;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIfHaveBreaks() {
		return ifHaveBreaks;
	}

	public void setIfHaveBreaks(String ifHaveBreaks) {
		this.ifHaveBreaks = ifHaveBreaks;
	}

	public Integer getProtectDays() {
		return protectDays;
	}

	public void setProtectDays(Integer protectDays) {
		this.protectDays = protectDays;
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

	public String getIfHaveHistoryApply() {
		return ifHaveHistoryApply;
	}

	public void setIfHaveHistoryApply(String ifHaveHistoryApply) {
		this.ifHaveHistoryApply = ifHaveHistoryApply;
	}

	public List<Integer> getRepaymentStatue() {
		return repaymentStatue;
	}

	public void setRepaymentStatue(List<Integer> repaymentStatue) {
		this.repaymentStatue = repaymentStatue;
	}

	public String getIfHaveValidate() {
		return ifHaveValidate;
	}

	public void setIfHaveValidate(String ifHaveValidate) {
		this.ifHaveValidate = ifHaveValidate;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	public String getPreviousRtfState() {
		return previousRtfState;
	}

	public void setPreviousRtfState(String previousRtfState) {
		this.previousRtfState = previousRtfState;
	}

	public Date getPreviousRefuseDate() {
		return previousRefuseDate;
	}

	public void setPreviousRefuseDate(Date previousRefuseDate) {
		this.previousRefuseDate = previousRefuseDate;
	}

	public Date getPreviousCancelDate() {
		return previousCancelDate;
	}

	public void setPreviousCancelDate(Date previousCancelDate) {
		this.previousCancelDate = previousCancelDate;
	}

	public String getPreviousOwningBranchId() {
		return previousOwningBranchId;
	}

	public void setPreviousOwningBranchId(String previousOwningBranchId) {
		this.previousOwningBranchId = previousOwningBranchId;
	}

	public Date getPreviousFirstCommitDate() {
		return previousFirstCommitDate;
	}

	public void setPreviousFirstCommitDate(Date previousFirstCommitDate) {
		this.previousFirstCommitDate = previousFirstCommitDate;
	}

	public Integer getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Integer limitDays) {
		this.limitDays = limitDays;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getIfBlacklist() {
		return ifBlacklist;
	}

	public void setIfBlacklist(String ifBlacklist) {
		this.ifBlacklist = ifBlacklist;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public Date getAppServiceClaimDate() {
		return appServiceClaimDate;
	}

	public void setAppServiceClaimDate(Date appServiceClaimDate) {
		this.appServiceClaimDate = appServiceClaimDate;
	}

	public String getAppApplyInput() {
		return appApplyInput;
	}

	public void setAppApplyInput(String appApplyInput) {
		this.appApplyInput = appApplyInput;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public String getOwningBranchCity() {
		return owningBranchCity;
	}

	public void setOwningBranchCity(String owningBranchCity) {
		this.owningBranchCity = owningBranchCity;
	}

	public String getOwningBranchDivision() {
		return owningBranchDivision;
	}

	public void setOwningBranchDivision(String owningBranchDivision) {
		this.owningBranchDivision = owningBranchDivision;
	}

	public String getOwningBranchArea() {
		return owningBranchArea;
	}

	public void setOwningBranchArea(String owningBranchArea) {
		this.owningBranchArea = owningBranchArea;
	}

	public Date getFirstCommitDate() {
		return firstCommitDate;
	}

	public void setFirstCommitDate(Date firstCommitDate) {
		this.firstCommitDate = firstCommitDate;
	}

	public Date getFirstInModifyDate() {
		return firstInModifyDate;
	}

	public void setFirstInModifyDate(Date firstInModifyDate) {
		this.firstInModifyDate = firstInModifyDate;
	}

	public String getRtfState() {
		return rtfState;
	}

	public void setRtfState(String rtfState) {
		this.rtfState = rtfState;
	}

	public List<String> getIfThirdOrgReturn() {
		return ifThirdOrgReturn;
	}

	public void setIfThirdOrgReturn(List<String> ifThirdOrgReturn) {
		this.ifThirdOrgReturn = ifThirdOrgReturn;
	}

	public List<Date> getThirdOrgReturnDate() {
		return thirdOrgReturnDate;
	}

	public void setThirdOrgReturnDate(List<Date> thirdOrgReturnDate) {
		this.thirdOrgReturnDate = thirdOrgReturnDate;
	}

	public List<String> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<String> holidays) {
		this.holidays = holidays;
	}

	public void initDate() throws ParseException{
		Date initDate = strToDate("99991231", "yyyyMMdd");
		
		this.previousRefuseDate = initDate;//上笔借款被拒绝时间
		this.previousCancelDate = initDate;//上笔借款被取消时间
		this.previousFirstCommitDate = initDate;//上笔提交信审时间
		this.appServiceClaimDate = initDate;//APP客服认领时间
		this.applyDate = initDate;//当前申请   申请创建时间
		this.firstCommitDate = initDate;//提交信审时间
		this.firstInModifyDate = initDate;//当前申请  首次进入录入修改时间
	}
	
	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 * @return formatStr
	 * @throws ParseException
	 */
	public static Date strToDate(String str, String formatStr) throws ParseException {
		if (formatStr == null) {
			formatStr = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(str);
	}
}
