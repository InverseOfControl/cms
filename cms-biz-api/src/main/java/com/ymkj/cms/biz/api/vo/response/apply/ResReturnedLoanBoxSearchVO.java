package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ResReturnedLoanBoxSearchVO</p>
 * <p>Description:退件箱请求响应对象</p>
 * @uthor YM10159
 * @date 2017年3月7日 上午11:20:35
 */
public class ResReturnedLoanBoxSearchVO  implements Serializable {

	private static final long serialVersionUID = 6913626728893890359L;
	
	private String loanBaseId;
	/**
	 * 借款编号
	 */
	private String loanNo;
	/**
	 * 客户姓名
	 */
	private String personName;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 借款产品
	 */
	private String productName;
	/**
	 * 申请金额
	 */
	private String applyLmt;
	/**
	 * 申请期限
	 */
	private String applyTerm;
	/**
	 * 退回备注
	 */
	private String remark;
	/**
	 * 退回环节
	 */
	private String returnType;
	/**
	 * 客户经理
	 */
	private String managerName;
	/**
	 * 主任
	 */
	private String directorName;
	/**
	 * 开始时间
	 */
	private String stratTime;
	/**
	 * 完成时间
	 */
	private String endTime;
	/**
	 * 是否加急
	 */
	private String ifPri;
	/**
	 * 颜色标识0，无色，1黄色色（#F2FC96）
	 */
	private Long logoFlag;
	
	private String ifPreferentialUser; //是否优惠费率用户  Y 是  N 否
	
	private String initProductName;
	
	
	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}
	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getStratTime() {
		return stratTime;
	}
	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(String loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	public Long getLogoFlag() {
		return logoFlag;
	}
	public void setLogoFlag(Long logoFlag) {
		this.logoFlag = logoFlag;
	}
	public String getInitProductName() {
		return initProductName;
	}
	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}
	
	
	
}
