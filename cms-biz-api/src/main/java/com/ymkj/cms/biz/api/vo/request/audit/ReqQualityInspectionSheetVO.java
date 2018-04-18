package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqQualityInspectionSheetVO extends Request{
	//初审人员CODE
	private List<String> userCode;
	//当前日期
	private Date curttenDate;
	//历史抽取的通过件总数
	private Integer historyPassCount;
	//历史抽取的拒绝件总数
	private Integer historyRejectCount;
	//历史按比率抽取通过件总数
	private Integer historyPassScaleCount;
	//历史按比率抽取拒绝件总数
	private Integer historyRejectScaleCount;
	//比率
	private Double scale;
	
	public List<String> getUserCode() {
		return userCode;
	}
	public void setUserCode(List<String> userCode) {
		this.userCode = userCode;
	}
	public Date getCurttenDate() {
		return curttenDate;
	}
	public void setCurttenDate(Date curttenDate) {
		this.curttenDate = curttenDate;
	}
	public Integer getHistoryPassCount() {
		return historyPassCount;
	}
	public void setHistoryPassCount(Integer historyPassCount) {
		this.historyPassCount = historyPassCount;
	}
	public Integer getHistoryRejectCount() {
		return historyRejectCount;
	}
	public void setHistoryRejectCount(Integer historyRejectCount) {
		this.historyRejectCount = historyRejectCount;
	}
	public Integer getHistoryPassScaleCount() {
		return historyPassScaleCount;
	}
	public void setHistoryPassScaleCount(Integer historyPassScaleCount) {
		this.historyPassScaleCount = historyPassScaleCount;
	}
	public Integer getHistoryRejectScaleCount() {
		return historyRejectScaleCount;
	}
	public void setHistoryRejectScaleCount(Integer historyRejectScaleCount) {
		this.historyRejectScaleCount = historyRejectScaleCount;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	
	
}
