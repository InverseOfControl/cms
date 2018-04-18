package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.List;

/**
 * 签约改派响应实体
 * @author YM10166
 *
 */
public class ResBMSContractChangeVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7827687293651104399L;
	
	/**
	 * LOAN_BASE_ID
	 */
	private Integer id;

	/**
	 * 借款编号
	 */
	private String loanNo;
	
	/**
	 * 客户姓名
	 */
	private String personName;
	
	/**
	 * 渠道名称
	 */
	private String channelName;
	
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品code
	 */
	private String productCode;
	
	/**
	 * 录单网点
	 */
	private String owningBranch;
	/**
	 * 录单网点Id
	 */
	private Long owningBranchId;
	
	/**
	 * 签约网点Id
	 */
	private String contractBranchId;
	
	/**
	 * 签约网点
	 */
	private String contractBranch;
	
	/**
	 * 签约客服
	 */
	private String signName;
	/**
	 * 签约客服code
	 */
	private String signCode;
	
	/**
	 * 失败借款编号
	 */
	private List<String> isFailedLoanNoList;
	/**
	 * 是否加急   0不加急   1加急
	 */
	private String ifPri;
	/**
	 *  APP进件标识     app_input:进件
	 */
	private String appInputFlag;
	/**
	 * 是否疑似欺诈  Y 是  N否

	 */
	private String ifSuspectCheat;
	/**
	 * 是否优惠费率用户  Y 是  N 否
	 */
	private String ifPreferentialUser;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOwningBranch() {
		return owningBranch;
	}

	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}

	public String getContractBranch() {
		return contractBranch;
	}

	public void setContractBranch(String contractBranch) {
		this.contractBranch = contractBranch;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getOwningBranchId() {
		return owningBranchId;
	}

	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}

	public String getContractBranchId() {
		return contractBranchId;
	}

	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}

	public List<String> getIsFailedLoanNoList() {
		return isFailedLoanNoList;
	}

	public void setIsFailedLoanNoList(List<String> isFailedLoanNoList) {
		this.isFailedLoanNoList = isFailedLoanNoList;
	}

	public String getIfPri() {
		return ifPri;
	}

	public void setIfPri(String ifPri) {
		this.ifPri = ifPri;
	}

	public String getAppInputFlag() {
		return appInputFlag;
	}

	public void setAppInputFlag(String appInputFlag) {
		this.appInputFlag = appInputFlag;
	}

	public String getIfSuspectCheat() {
		return ifSuspectCheat;
	}

	public void setIfSuspectCheat(String ifSuspectCheat) {
		this.ifSuspectCheat = ifSuspectCheat;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getIfPreferentialUser() {
		return ifPreferentialUser;
	}

	public void setIfPreferentialUser(String ifPreferentialUser) {
		this.ifPreferentialUser = ifPreferentialUser;
	}
	
}
