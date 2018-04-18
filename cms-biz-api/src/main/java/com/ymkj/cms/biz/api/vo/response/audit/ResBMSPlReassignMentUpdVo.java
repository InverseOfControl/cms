package com.ymkj.cms.biz.api.vo.response.audit;

import java.io.Serializable;
import java.util.List;

public class ResBMSPlReassignMentUpdVo implements Serializable{

	/**
	 * 序列化对象
	 */
	private static final long serialVersionUID = -807151998893985788L;
	private List<String> successList;
	private List<String> failList;
	private List<Boolean> checkList;
	private List<ResBMSMessageVO> resList;



	public List<Boolean> getCheckList() {
		return checkList;
	}


	public void setCheckList(List<Boolean> checkList) {
		this.checkList = checkList;
	}


	public List<String> getSuccessList() {
		return successList;
	}


	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}


	public List<String> getFailList() {
		return failList;
	}


	public void setFailList(List<String> failList) {
		this.failList = failList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<ResBMSMessageVO> getResList() {
		return resList;
	}


	public void setResList(List<ResBMSMessageVO> resList) {
		this.resList = resList;
	}


	

	
}
