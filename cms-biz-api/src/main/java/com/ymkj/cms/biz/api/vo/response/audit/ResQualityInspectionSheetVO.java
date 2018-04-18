package com.ymkj.cms.biz.api.vo.response.audit;

import java.util.ArrayList;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;

public class ResQualityInspectionSheetVO extends Response<ResQualityInspectionSheetVO>{
	//通过件数据
	private List<ResQualityInspectionSheetResultVO> passList=new ArrayList<ResQualityInspectionSheetResultVO>();
	//拒绝件数据
	private List<ResQualityInspectionSheetResultVO> rejectList=new ArrayList<ResQualityInspectionSheetResultVO>();
	//当天查询出来的通过件总数
	private Integer passCount;
	//当天查询出来的拒绝件总数
	private Integer rejectCount;
	//当天按比率查询出来的通过件总数
	private Integer passScaleCount;
	//当天按比率查询出来的拒绝件总数
	private Integer rejectScaleCount;
	
	//拒绝件走过终审通过并且被申请件信息维护拒绝总数
	private Integer countZsJj;

	public List<ResQualityInspectionSheetResultVO> getPassList() {
		return passList;
	}

	public void setPassList(List<ResQualityInspectionSheetResultVO> passList) {
		this.passList = passList;
	}

	public List<ResQualityInspectionSheetResultVO> getRejectList() {
		return rejectList;
	}

	public void setRejectList(List<ResQualityInspectionSheetResultVO> rejectList) {
		this.rejectList = rejectList;
	}

	public Integer getPassCount() {
		return passCount;
	}

	public void setPassCount(Integer passCount) {
		this.passCount = passCount;
	}

	public Integer getRejectCount() {
		return rejectCount;
	}

	public void setRejectCount(Integer rejectCount) {
		this.rejectCount = rejectCount;
	}

	public Integer getPassScaleCount() {
		return passScaleCount;
	}

	public void setPassScaleCount(Integer passScaleCount) {
		this.passScaleCount = passScaleCount;
	}

	public Integer getRejectScaleCount() {
		return rejectScaleCount;
	}

	public void setRejectScaleCount(Integer rejectScaleCount) {
		this.rejectScaleCount = rejectScaleCount;
	}

	public Integer getCountZsJj() {
		return countZsJj;
	}

	public void setCountZsJj(Integer countZsJj) {
		this.countZsJj = countZsJj;
	}
	

	
	
}
