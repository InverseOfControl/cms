package com.ymkj.cms.biz.entity.app;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BadCreditInfoValueEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 征信报告编号 **/
	private Integer no;  

	/** 
	 * overdueType
	 * 
	 * 当前逾期0, 非当前逾期1
	 *  **/
	private Integer overdueType;
	
	/** 
	 * 	信用卡或贷款类型
	 * 
	 *  农户贷款, 个人住房贷款, 个人住房公积金贷款, 个人经营性贷款, 个人消费贷款, 个人汽车贷款, 个人助学贷款, 其他贷款, 购房贷款, 贷记卡, 准贷记卡
	 *  
	 *  **/
	private String type;
	
	/** 单条报告信息 **/
	private String content;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getOverdueType() {
		return overdueType;
	}

	public void setOverdueType(Integer overdueType) {
		this.overdueType = overdueType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
}
