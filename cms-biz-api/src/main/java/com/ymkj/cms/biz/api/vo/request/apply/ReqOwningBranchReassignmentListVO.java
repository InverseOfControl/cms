package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqOwningBranchReassignmentListVO  extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<ReqOwningBranchReassignmentVO> listReqOwningBranchReassignmentVO;

	public List<ReqOwningBranchReassignmentVO> getListReqOwningBranchReassignmentVO() {
		return listReqOwningBranchReassignmentVO;
	}


	public void setListReqOwningBranchReassignmentVO(
			List<ReqOwningBranchReassignmentVO> listReqOwningBranchReassignmentVO) {
		this.listReqOwningBranchReassignmentVO = listReqOwningBranchReassignmentVO;
	}
	
	
	

}
