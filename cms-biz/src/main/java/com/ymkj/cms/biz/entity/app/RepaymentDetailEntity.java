package com.ymkj.cms.biz.entity.app;

import java.util.List;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class RepaymentDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private List<QueryRepaymentDetailEntity> repaymentDetail;
	
	public List<QueryRepaymentDetailEntity> getRepaymentDetail() {
		return repaymentDetail;
	}
	public void setRepaymentDetail(List<QueryRepaymentDetailEntity> repaymentDetail) {
		this.repaymentDetail = repaymentDetail;
	}

	
}
