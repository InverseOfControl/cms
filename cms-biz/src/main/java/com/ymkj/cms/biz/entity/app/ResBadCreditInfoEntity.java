package com.ymkj.cms.biz.entity.app;

import java.util.List;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class ResBadCreditInfoEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean ifCreditInnocence = false;//是否征信白户
	
	private boolean credit_ifCurrentOverdue = false;//信用卡		当前逾期造成的信用不良
	
	private boolean credit_ifNoCurrentOverdue = false;//信用卡		非当前逾期造成的信用不良
	
	private boolean loan_ifCurrentOverdue = false;//贷款		当前逾期造成的信用不良
	
	private boolean loan_ifNoCurrentOverdue = false;//贷款			非当前逾期造成的信用不良
	
	/** 信用卡 **/
	private List<BadCreditInfoValueEntity> card;
	
	/** 购房贷款 **/
	private List<BadCreditInfoValueEntity> houseLoan;

	public boolean isIfCreditInnocence() {
		return ifCreditInnocence;
	}

	public void setIfCreditInnocence(boolean ifCreditInnocence) {
		this.ifCreditInnocence = ifCreditInnocence;
	}

	public boolean isCredit_ifCurrentOverdue() {
		return credit_ifCurrentOverdue;
	}

	public void setCredit_ifCurrentOverdue(boolean credit_ifCurrentOverdue) {
		this.credit_ifCurrentOverdue = credit_ifCurrentOverdue;
	}

	public boolean isCredit_ifNoCurrentOverdue() {
		return credit_ifNoCurrentOverdue;
	}

	public void setCredit_ifNoCurrentOverdue(boolean credit_ifNoCurrentOverdue) {
		this.credit_ifNoCurrentOverdue = credit_ifNoCurrentOverdue;
	}

	public boolean isLoan_ifCurrentOverdue() {
		return loan_ifCurrentOverdue;
	}

	public void setLoan_ifCurrentOverdue(boolean loan_ifCurrentOverdue) {
		this.loan_ifCurrentOverdue = loan_ifCurrentOverdue;
	}

	public boolean isLoan_ifNoCurrentOverdue() {
		return loan_ifNoCurrentOverdue;
	}

	public void setLoan_ifNoCurrentOverdue(boolean loan_ifNoCurrentOverdue) {
		this.loan_ifNoCurrentOverdue = loan_ifNoCurrentOverdue;
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
 