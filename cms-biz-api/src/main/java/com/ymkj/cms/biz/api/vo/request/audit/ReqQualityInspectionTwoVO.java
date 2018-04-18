package com.ymkj.cms.biz.api.vo.request.audit;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请件接口请求参数VO
 * @author YM10161
 *
 */
public class ReqQualityInspectionTwoVO extends Request {

	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = -26262028370112798L;
	
	private String custName;//客户姓名
	private String iDNo;//身份证号
	private List<String> loanList;//借款编号
	private String departmentId;//营业部ID
	private String rtfNodeState;//流程节点状态
	public String getiDNo() {
		return iDNo;
	}
	public void setiDNo(String iDNo) {
		this.iDNo = iDNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getRtfNodeState() {
		return rtfNodeState;
	}
	public void setRtfNodeState(String rtfNodeState) {
		this.rtfNodeState = rtfNodeState;
	}
	public List<String> getLoanList() {
		return loanList;
	}
	public void setLoanList(List<String> loanList) {
		this.loanList = loanList;
	}	
	
	
}
