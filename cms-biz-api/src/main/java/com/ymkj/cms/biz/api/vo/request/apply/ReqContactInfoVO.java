package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqContactInfoVO  extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	
	
	private Long id;
	private String loanBaseId; //借款主键
	private String loanNo;	   //借款编号
	
	private String contactName;         // 姓名	
	private String contactRelation;         //与申请人关系
	private String contacGender;         //性别
	private String contactCellPhone;         // 手机
	private String contactCellPhone_1;  //手机号2
	private String ifKnowLoan;         // 是否知晓贷款
	private String contactEmpName;         //公司名称
	private String contactCorpPost;         // 职务
	private String contactCorpPhone;         // 公司电话号码
	private String contactCorpPhone_1;   //公司电话号码2
	private Integer sequenceNum;         // 排序号
	
	private String serviceCode;			//操作人工号
	private String serviceName;			//操作人姓名
	private String ip;					//操作ip

	private String type ;//操作类型  0修改   1删除 
	
	private String version;//版本号
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(String loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	 
	public String getContactEmpName() {
		return contactEmpName;
	}
	public void setContactEmpName(String contactEmpName) {
		this.contactEmpName = contactEmpName;
	}
	 
	public String getContactCorpPost() {
		return contactCorpPost;
	}
	public void setContactCorpPost(String contactCorpPost) {
		this.contactCorpPost = contactCorpPost;
	}
	public String getContactCorpPhone() {
		return contactCorpPhone;
	}
	public void setContactCorpPhone(String contactCorpPhone) {
		this.contactCorpPhone = contactCorpPhone;
	}
	public String getContactRelation() {
		return contactRelation;
	}
	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation;
	}
	public String getContacGender() {
		return contacGender;
	}
	public void setContacGender(String contacGender) {
		this.contacGender = contacGender;
	}
	public String getContactCellPhone() {
		return contactCellPhone;
	}
	public void setContactCellPhone(String contactCellPhone) {
		this.contactCellPhone = contactCellPhone;
	}
	public String getIfKnowLoan() {
		return ifKnowLoan;
	}
	public void setIfKnowLoan(String ifKnowLoan) {
		this.ifKnowLoan = ifKnowLoan;
	}
	public Integer getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public ReqContactInfoVO(){
		
	}
	public ReqContactInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
	public String getContactCellPhone_1() {
		return contactCellPhone_1;
	}
	public void setContactCellPhone_1(String contactCellPhone_1) {
		this.contactCellPhone_1 = contactCellPhone_1;
	}
	public String getContactCorpPhone_1() {
		return contactCorpPhone_1;
	}
	public void setContactCorpPhone_1(String contactCorpPhone_1) {
		this.contactCorpPhone_1 = contactCorpPhone_1;
	}
	 
	
}
