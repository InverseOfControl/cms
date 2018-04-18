package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请件请求参数VO
 * @author YM10161
 *
 */
public class ReqQualityInspectionVO extends Request {
	
	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = -1197572778196376229L;

	private String status;//申请件状态
	
	private String startDate;//审批开始时间
	private String endDate;//审批结束时间
	private List<String> list;//审核人员ID
	private String department;//营业部ID
	private String productType;//产品类型
	private String refuseReasonLevelOneCd;//一级拒绝code
	private String refuseReasonLevelTwoCd;//二级拒绝code
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRefuseReasonLevelOneCd() {
		return refuseReasonLevelOneCd;
	}
	public void setRefuseReasonLevelOneCd(String refuseReasonLevelOneCd) {
		this.refuseReasonLevelOneCd = refuseReasonLevelOneCd;
	}
	public String getRefuseReasonLevelTwoCd() {
		return refuseReasonLevelTwoCd;
	}
	public void setRefuseReasonLevelTwoCd(String refuseReasonLevelTwoCd) {
		this.refuseReasonLevelTwoCd = refuseReasonLevelTwoCd;
	}
}
