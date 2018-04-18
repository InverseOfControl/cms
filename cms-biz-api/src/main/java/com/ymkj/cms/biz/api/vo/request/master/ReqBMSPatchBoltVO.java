package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSPatchBoltVO extends Request{

	/**
	 * 对象序列化
	 */
	private static final long serialVersionUID = -3695602222035386817L;
	
	private String customerName;//客户姓名
	private String customerIDNO;//客户身份证号
	private String patchBoltUrl;//补件接口地址
	private int pageNum;//当前页
	private int pageSize;//一页显示行数
	private int page;//页数
	private int rows;//行数
	
	public String getPatchBoltUrl() {
		return patchBoltUrl;
	}
	public void setPatchBoltUrl(String patchBoltUrl) {
		this.patchBoltUrl = patchBoltUrl;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}
	public int getPage() {
			return page;
	}
	public void setPage(int page) {
			this.page = page;
		this.setPageNum(page);
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIDNO() {
		return customerIDNO;
	}
	public void setCustomerIDNO(String customerIDNO) {
		this.customerIDNO = customerIDNO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
