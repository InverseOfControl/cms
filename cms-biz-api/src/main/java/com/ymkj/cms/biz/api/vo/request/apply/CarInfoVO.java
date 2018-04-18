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
public class CarInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	private Long id; // 车辆信息ID
	private String carType; // 车辆类型
	private String carLoan; // 是否有车贷
	private Date 	carBuyDate	; // 购买时间
	private BigDecimal 	nakedCarPrice; // 	裸车价/元	
	private BigDecimal 	carBuyPrice; // 	购买价/元
	private Integer carLoanTerm; // 贷款剩余期数
	private BigDecimal monthPaymentAmt; // 	月供
	private String localPlate; // 	本地车牌
	private String plateNum; // 车牌号
	 
	private Long ifEmpty = 0L;//对象是否是否为空  0是  1不是
	
	private String carCheckIsVerify;//车辆贷是否核实
	
	private Date carLoanIssueDate;//车贷发放年月贷
	
	
	public Date getCarLoanIssueDate() {
		return carLoanIssueDate;
	}
	public void setCarLoanIssueDate(Date carLoanIssueDate) {
		this.carLoanIssueDate = carLoanIssueDate;
	}
	public String getCarCheckIsVerify() {
		return carCheckIsVerify;
	}
	public void setCarCheckIsVerify(String carCheckIsVerify) {
		this.carCheckIsVerify = carCheckIsVerify;
	}
	public Long getIfEmpty() {
		return ifEmpty;
	}
	public void setIfEmpty(Long ifEmpty) {
		this.ifEmpty = ifEmpty;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarLoan() {
		return carLoan;
	}
	public void setCarLoan(String carLoan) {
		this.carLoan = carLoan;
	}
	public Date getCarBuyDate() {
		return carBuyDate;
	}
	public void setCarBuyDate(Date carBuyDate) {
		this.carBuyDate = carBuyDate;
	}
	 
	public BigDecimal getNakedCarPrice() {
		return nakedCarPrice;
	}
	public void setNakedCarPrice(BigDecimal nakedCarPrice) {
		this.nakedCarPrice = nakedCarPrice;
	}
	public BigDecimal getCarBuyPrice() {
		return carBuyPrice;
	}
	public void setCarBuyPrice(BigDecimal carBuyPrice) {
		this.carBuyPrice = carBuyPrice;
	}
	public Integer getCarLoanTerm() {
		return carLoanTerm;
	}
	public void setCarLoanTerm(Integer carLoanTerm) {
		this.carLoanTerm = carLoanTerm;
	}
	public BigDecimal getMonthPaymentAmt() {
		return monthPaymentAmt;
	}
	public void setMonthPaymentAmt(BigDecimal monthPaymentAmt) {
		this.monthPaymentAmt = monthPaymentAmt;
	}
	public String getLocalPlate() {
		return localPlate;
	}
	public void setLocalPlate(String localPlate) {
		this.localPlate = localPlate;
	}
	 
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public CarInfoVO(){	
	}
	public CarInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
