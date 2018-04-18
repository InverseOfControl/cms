package com.ymkj.cms.biz.entity.master;

public class BMSOffLineOfferBankDic extends BMSProductBaseEntity{

	private Long id;
	//合同编号
	private String contractNum;
	//渠道名称
	private String channelName;
	//客户姓名 
	private String borrowerName;
	//身份证号码
	private String idNum;
	//银行账号
	private String account;
	//银行名称
	private String bank;
	//支行名称
	private String bankFullName;
	//行别
	private String lineDontDo;
	//行号
	private String lineNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankFullName() {
		return bankFullName;
	}
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	public String getLineDontDo() {
		return lineDontDo;
	}
	public void setLineDontDo(String lineDontDo) {
		this.lineDontDo = lineDontDo;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
	
	
}
