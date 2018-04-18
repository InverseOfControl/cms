package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ymkj.cms.biz.api.enums.EnumChannelCode;

/**
 * 龙信小贷导入excel中的数据bean
 * @author YM10193
 *
 */
public class ReqImportExcelVO implements Serializable{

	private static final long serialVersionUID = -353348185201779486L;
	@NotBlank(message="序号不能为空")
	private String id;
	@NotBlank(message="合同编号不能为空")
	private String contractNum;
	@NotBlank(message="借款人姓名不能为空")
	private String borrowerName;
	@NotBlank(message="身份证号不能为空")
	private String idNum;
	@NotBlank(message="来源营业厅不能为空")
	private String requestPlace;
	@NotBlank(message="放款日期不能为空")
	private String loanDate;
	@NotBlank(message="贷款期限(月)不能为空")
	private String time;
	@NotNull(message="合同金额(元)不能为空")
	private BigDecimal pactMoney;
	@NotBlank(message="结果不能为空")
	private String result;
	@NotBlank(message="备注不能为空")
	private String desc;
	//反馈结果
	private String feedBack;
	//借款编号，这个不是表格内容，是为了和放款结果匹配
	private String loanNo;
	//渠道号，不是表格内容，为了查询放款信息用
	private String channelCode = EnumChannelCode.龙信小贷.getChannelCode();
	
	
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getRequestPlace() {
		return requestPlace;
	}
	public void setRequestPlace(String requestPlace) {
		this.requestPlace = requestPlace;
	}

	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BigDecimal getPactMoney() {
		return pactMoney;
	}
	public void setPactMoney(BigDecimal pactMoney) {
		this.pactMoney = pactMoney;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
