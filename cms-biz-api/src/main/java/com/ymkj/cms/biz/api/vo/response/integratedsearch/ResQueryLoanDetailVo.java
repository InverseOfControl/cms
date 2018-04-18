package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ResQueryLoanDetailVo</p>
 * <p>Description:查看借款详情</p>
 * @uthor YM10159
 * @date 2017年5月5日 上午10:08:07
 */
public class ResQueryLoanDetailVo extends Request{

	private static final long serialVersionUID = 561204444878610877L;

	private String loanNo;
	private String idNo;
	private String name;
	private String productName;
	private String applyTerm;
	private String applyLmt;
	private String creditApplication;
	private String creditApplicationName;
	private String branchManagerName;
	private String owningBranchName;
	private String ifPri;
	private String remark;
	private String appRst; //反欺诈得分
	private String stanFrdLevel; //反欺诈预警
	private String appRstRema; //欺诈 风险评级
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getApplyLmt() {
		return applyLmt;
	}
	public void setApplyLmt(String applyLmt) {
		this.applyLmt = applyLmt;
	}
	public String getCreditApplication() {
		return creditApplication;
	}
	public void setCreditApplication(String creditApplication) {
		this.creditApplication = creditApplication;
	}
	public String getBranchManagerName() {
		return branchManagerName;
	}
	public void setBranchManagerName(String branchManagerName) {
		this.branchManagerName = branchManagerName;
	}
	public String getOwningBranchName() {
		return owningBranchName;
	}
	public void setOwningBranchName(String owningBranchName) {
		this.owningBranchName = owningBranchName;
	}
	public String getIfPri() {
		return ifPri;
	}
	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAppRst() {
		return appRst;
	}
	public void setAppRst(String appRst) {
		this.appRst = appRst;
	}
	public String getStanFrdLevel() {
		return stanFrdLevel;
	}
	public void setStanFrdLevel(String stanFrdLevel) {
		this.stanFrdLevel = stanFrdLevel;
	}
	public String getAppRstRema() {
		return appRstRema;
	}
	public void setAppRstRema(String appRstRema) {
		this.appRstRema = appRstRema;
	}
	public String getCreditApplicationName() {
		return creditApplicationName;
	}
	public void setCreditApplicationName(String creditApplicationName) {
		this.creditApplicationName = creditApplicationName;
	}
	
}
