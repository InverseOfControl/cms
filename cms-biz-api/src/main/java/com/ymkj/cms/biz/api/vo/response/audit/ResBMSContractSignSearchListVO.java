package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;
import java.util.List;

public class ResBMSContractSignSearchListVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2300003196341566612L;
	
	 
	private 	List<ResBMSContractSignSearchVO>  contractSignSearchVO;


	public List<ResBMSContractSignSearchVO> getContractSignSearchVO() {
		return contractSignSearchVO;
	}


	public void setContractSignSearchVO(
			List<ResBMSContractSignSearchVO> contractSignSearchVO) {
		this.contractSignSearchVO = contractSignSearchVO;
	}


	 
	
	
}
