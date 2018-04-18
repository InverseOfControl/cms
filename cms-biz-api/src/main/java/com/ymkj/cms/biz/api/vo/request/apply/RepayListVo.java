package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.List;

public class RepayListVo {
	
	/**
	 * 借款数据次序与借款期数对应
	 */
	private List<RepayVo> repayList;//借款列表

	public List<RepayVo> getRepayList() {
		return repayList;
	}

	public void setRepayList(List<RepayVo> repayList) {
		this.repayList = repayList;
	}
	
	
	
}
