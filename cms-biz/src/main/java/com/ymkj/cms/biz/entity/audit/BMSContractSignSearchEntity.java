package com.ymkj.cms.biz.entity.audit;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class BMSContractSignSearchEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String loanNo;//借款编号
	private String name;//客户姓名
	private String channelName;//渠道名称
	private String productName;//产品名称
	private BigDecimal accLmt;//审批金额
	private Integer applyTerm;//产品期限
	private String owningBranch;//录单网点
	private Date endTime;//完成时间;
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getAccLmt() {
		return accLmt;
	}
	public void setAccLmt(BigDecimal accLmt) {
		this.accLmt = accLmt;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	

}
