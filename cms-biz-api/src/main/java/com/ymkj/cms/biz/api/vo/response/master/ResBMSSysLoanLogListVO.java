package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.List;

public class ResBMSSysLoanLogListVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300003196341566612L;
	
	 
	private 	List<ResBMSSysLoanLogVO>  ResBMSSysLoanLogList;


	public List<ResBMSSysLoanLogVO> getResBMSSysLoanLogList() {
		return ResBMSSysLoanLogList;
	}


	public void setResBMSSysLoanLogList(
			List<ResBMSSysLoanLogVO> resBMSSysLoanLogList) {
		ResBMSSysLoanLogList = resBMSSysLoanLogList;
	}
	
	
}
