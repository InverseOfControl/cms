package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 初审接口请求实体
 * @author YM10143
 *
 */
public class ReqAuditVo extends Request{

	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = 6739752274010697542L;
	
	private String flag;//标识1:初审；2:终审
	private String caseType;//案件标识	
	private String serviceCode;//员工工号/客服code	
	private int pageNum;//当前页数
	private int pageSize;//分页条数
	private String name;
	private String idNo;
	private String loanNo;
	/**
	 * 排序字段
	 */
	private String fieldSort;
	/**
	 * 排序规则
	 */
	private int rulesSort;//0 倒序  1正序  其他 顺序
	public ReqAuditVo() {
		super();
	}
	public ReqAuditVo(String id, String serviceCode, int pageNum,
			int pageSize) {
		super();
		this.serviceCode = serviceCode;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getFieldSort() {
		return fieldSort;
	}
	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}
	public int getRulesSort() {
		return rulesSort;
	}
	public void setRulesSort(int rulesSort) {
		this.rulesSort = rulesSort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

}
