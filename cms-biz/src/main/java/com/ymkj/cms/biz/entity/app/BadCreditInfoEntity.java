package com.ymkj.cms.biz.entity.app;

import java.util.List;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BadCreditInfoEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/** 其他贷款 **/
	private List<BadCreditInfoValueEntity> otherLoan;
	
	/** 信用卡 **/
	private List<BadCreditInfoValueEntity> card;
	
	/** 购房贷款 **/
	private List<BadCreditInfoValueEntity> houseLoan;
	

	public List<BadCreditInfoValueEntity> getOtherLoan() {
		return otherLoan;
	}

	public void setOtherLoan(List<BadCreditInfoValueEntity> otherLoan) {
		this.otherLoan = otherLoan;
	}

	public List<BadCreditInfoValueEntity> getCard() {
		return card;
	}

	public void setCard(List<BadCreditInfoValueEntity> card) {
		this.card = card;
	}

	public List<BadCreditInfoValueEntity> getHouseLoan() {
		return houseLoan;
	}

	public void setHouseLoan(List<BadCreditInfoValueEntity> houseLoan) {
		this.houseLoan = houseLoan;
	}
	
	
}
