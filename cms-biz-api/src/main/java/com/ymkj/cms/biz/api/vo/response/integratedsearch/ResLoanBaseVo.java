package com.ymkj.cms.biz.api.vo.response.integratedsearch;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;


/**
 * 查询核心债权数据
 * @author YM10152
 *
 */
public class ResLoanBaseVo extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	//借款编号
	private String loanNo;
	//状态
	private String loanState;	
	//创建时间
	private Date createTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanState() {
		return loanState;
	}
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	

	
}
