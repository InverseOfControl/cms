package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResBMSRejectReasonListVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300003196341566612L;
	 
 
	private List<ResBMSRejectReasonVO>	 ResBMSRejectReasonList;


	public List<ResBMSRejectReasonVO> getResBMSRejectReasonList() {
		return ResBMSRejectReasonList;
	}


	public void setResBMSRejectReasonList(
			List<ResBMSRejectReasonVO> resBMSRejectReasonList) {
		ResBMSRejectReasonList = resBMSRejectReasonList;
	}
		 
}
