package com.ymkj.cms.biz.api.vo.request.sign;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @Description:陆金所进件通知外部接口实体</p>
 * @uthor YM10159
 * @date 2017年7月14日 下午4:40:44
 */
public class ReqLujsInformVo extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8431531336827339358L;

	/** 申请单号*/
	@NotEmpty(message = "申请单号不能为空！")
	private String loanNo;
	
	/** 通知类型*/
	@NotEmpty(message = "通知类型不能为空！")
	private String notifyType;
	
	/** 描述*/
	private String notifyDesc;
	
	/**当前任务节点*/
	private String currentTaskNode; 


	public String getLoanNo() {
		return loanNo;
	}


	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}


	public String getNotifyType() {
		return notifyType;
	}


	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}


	public String getNotifyDesc() {
		return notifyDesc;
	}


	public void setNotifyDesc(String notifyDesc) {
		this.notifyDesc = notifyDesc;
	}


	public String getCurrentTaskNode() {
		return currentTaskNode;
	}


	public void setCurrentTaskNode(String currentTaskNode) {
		this.currentTaskNode = currentTaskNode;
	}

}
