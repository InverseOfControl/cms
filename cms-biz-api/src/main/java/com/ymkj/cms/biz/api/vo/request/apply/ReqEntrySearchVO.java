package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ReqEntrySearchVO extends Request{
	/**	
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	
	private String loanNo;//借款编号
	private String personName;//客户姓名
	private String idNo;//身份证号
	private String appFlag;			//是否app进件 ：1是 0否
	private Date completionTimeStart;//起始完成时间
	private Date completionTimeEnd;	//截止完成时间
	private String serviceCode;		//客服Code;
	private String serviceName;		//客服姓名;
	private String agencyOrComplete;//页签标识;  1代办任务 ，2完成任务
	private String optionModule;	//操作模块;2录入修改查询  3 录入复核查询
	private String owningBranchId;	//门店ID
	
	private String initProductName;
	private int pageNum;     // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数
	
	private String ip;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(String owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOptionModule() {
		return optionModule;
	}
	public void setOptionModule(String optionModule) {
		this.optionModule = optionModule;
	}
	public String getAgencyOrComplete() {
		return agencyOrComplete;
	}
	public void setAgencyOrComplete(String agencyOrComplete) {
		this.agencyOrComplete = agencyOrComplete;
	}
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getAppFlag() {
		return appFlag;
	}
	public void setAppFlag(String appFlag) {
		this.appFlag = appFlag;
	}
	 
	public Date getCompletionTimeStart() {
		return completionTimeStart;
	}
	public void setCompletionTimeStart(Date completionTimeStart) {
		this.completionTimeStart = completionTimeStart;
	}
	public Date getCompletionTimeEnd() {
		return completionTimeEnd;
	}
	public void setCompletionTimeEnd(Date completionTimeEnd) {
		this.completionTimeEnd = completionTimeEnd;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public ReqEntrySearchVO(){	
	}
	public ReqEntrySearchVO(String sysCode){
		super.setSysCode(sysCode);
	}
	public String getInitProductName() {
		return initProductName;
	}
	public void setInitProductName(String initProductName) {
		this.initProductName = initProductName;
	}
	
	
		
}
