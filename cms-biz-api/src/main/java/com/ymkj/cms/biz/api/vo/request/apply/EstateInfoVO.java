package com.ymkj.cms.biz.api.vo.request.apply;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class EstateInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
   
	private Long id;//房产信息ID
	private String estateType; // 房产类型
	private Long estateStateId; // 房产所在省ID
	private Long estateCityId; //房产所在市ID
	private Long estateZoneId; //房产所在区ID	
	
	private String estateState; // 房产所在省
	private String estateCity; //房产所在市
	private String estateZone; //房产所在区	
	
	private String estateAddress; //房产地址
	private String estateLoan; //房贷情况
	private Date estateBuyDate; //购买时间
	private BigDecimal estateAmt; //购买总价值/元	
	private BigDecimal referenceAmt; //市值参考价/元		
	private BigDecimal estateLoanAmt; //	房贷金额/元		
	
	private BigDecimal monthPaymentAmt; //	月供
	private Integer hasRepaymentNum; //	已还期数
	private Double builtupArea; //建筑面积
	private String houseOwnership; //房产所有权	
	private Double equityRate; //	产权比例
	private String ifMe; //	单据户名为本人
			
	private Long ifEmpty = 0L;//对象是否是否为空  0是  1不是
	
	private Date estateLoanIssueDate;//房贷发放年月
	private String estateSameRegistered;//家庭住址是否同户籍地址 Y 是  N 否
	
	
	
	public Date getEstateLoanIssueDate() {
		return estateLoanIssueDate;
	}
	public void setEstateLoanIssueDate(Date estateLoanIssueDate) {
		this.estateLoanIssueDate = estateLoanIssueDate;
	}
	public String getEstateSameRegistered() {
		return estateSameRegistered;
	}
	public void setEstateSameRegistered(String estateSameRegistered) {
		this.estateSameRegistered = estateSameRegistered;
	}
	public Long getIfEmpty() {
		return ifEmpty;
	}
	public void setIfEmpty(Long ifEmpty) {
		this.ifEmpty = ifEmpty;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getEstateLoanAmt() {
		return estateLoanAmt;
	}
	public void setEstateLoanAmt(BigDecimal estateLoanAmt) {
		this.estateLoanAmt = estateLoanAmt;
	}
	 
	public Date getEstateBuyDate() {
		return estateBuyDate;
	}
	public void setEstateBuyDate(Date estateBuyDate) {
		this.estateBuyDate = estateBuyDate;
	}
	public BigDecimal getEstateAmt() {
		return estateAmt;
	}
	public void setEstateAmt(BigDecimal estateAmt) {
		this.estateAmt = estateAmt;
	}
	public BigDecimal getReferenceAmt() {
		return referenceAmt;
	}
	public void setReferenceAmt(BigDecimal referenceAmt) {
		this.referenceAmt = referenceAmt;
	}
	public BigDecimal getMonthPaymentAmt() {
		return monthPaymentAmt;
	}
	public void setMonthPaymentAmt(BigDecimal monthPaymentAmt) {
		this.monthPaymentAmt = monthPaymentAmt;
	}
	public Integer getHasRepaymentNum() {
		return hasRepaymentNum;
	}
	public void setHasRepaymentNum(Integer hasRepaymentNum) {
		this.hasRepaymentNum = hasRepaymentNum;
	}
	public Double getBuiltupArea() {
		return builtupArea;
	}
	public void setBuiltupArea(Double builtupArea) {
		this.builtupArea = builtupArea;
	}
	public String getHouseOwnership() {
		return houseOwnership;
	}
	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}
	public Double getEquityRate() {
		return equityRate;
	}
	public void setEquityRate(Double equityRate) {
		this.equityRate = equityRate;
	}
	 

	public String getEstateType() {
		return estateType;
	}
	public void setEstateType(String estateType) {
		this.estateType = estateType;
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
	public String getEstateAddress() {
		return estateAddress;
	}
	public void setEstateAddress(String estateAddress) {
		this.estateAddress = estateAddress;
	}
	public String getEstateLoan() {
		return estateLoan;
	}
	public void setEstateLoan(String estateLoan) {
		this.estateLoan = estateLoan;
	}
	public String getIfMe() {
		return ifMe;
	}
	public void setIfMe(String ifMe) {
		this.ifMe = ifMe;
	}
	public EstateInfoVO(){	
	}
	public EstateInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
