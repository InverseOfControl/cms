package com.ymkj.cms.biz.api.vo.request.audit;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ReqContractSignSearchVO extends Request{
	/**	
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	
	private String loanNo;//借款编号;
	private String name;//	客户姓名
	private String productCd;//产品code;
	private String channelCode;//渠道code;
	private String contractBranchId;//签约网点;
	private String signCode;//	签约客服code;
	private String agencyOrComplete;//页签标识;  代办任务 ，完成任务
	private String ip;
	 
	private int pageNum; // 当前页数
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
	private int pageSize;

	private int rows;// 行数
	private int page;// 页数
	
	
	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getContractBranchId() {
		return contractBranchId;
	}
	public void setContractBranchId(String contractBranchId) {
		this.contractBranchId = contractBranchId;
	}
	public String getSignCode() {
		return signCode;
	}
	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}
	
	public String getAgencyOrComplete() {
		return agencyOrComplete;
	}
	public void setAgencyOrComplete(String agencyOrComplete) {
		this.agencyOrComplete = agencyOrComplete;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public ReqContractSignSearchVO(){	
	}
	public ReqContractSignSearchVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
