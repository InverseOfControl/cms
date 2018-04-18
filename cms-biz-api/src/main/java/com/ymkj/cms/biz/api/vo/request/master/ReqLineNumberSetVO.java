package com.ymkj.cms.biz.api.vo.request.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;

public class ReqLineNumberSetVO extends Request{
	//客户姓名
	private String borrowerName;
	//客户省份证 
	private String idNum;
	//合同编号
	private String cobtractNum;
	//渠道名称 
	private String channelName;
	//银行卡所属地区
	private String queryAreaType;
	
	//设置行别行号 对应的银行卡所属机构 区域
	private String areaType;
	//勾选对应的信息的BMS_LOAN_BANK的ID
	private String bankIds;
	
	
	private List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs;
	
	private String importExcelAreaType;
	//当前登入人
	private String name;
	//当前等入人ID
	private String id;
	
	private int pageNum;     // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数
	
	//当前登入人CODE
	private String createName;
	private String createId;
	
	
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
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getCobtractNum() {
		return cobtractNum;
	}
	public void setCobtractNum(String cobtractNum) {
		this.cobtractNum = cobtractNum;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getQueryAreaType() {
		return queryAreaType;
	}
	public void setQueryAreaType(String queryAreaType) {
		this.queryAreaType = queryAreaType;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getBankIds() {
		return bankIds;
	}
	public void setBankIds(String bankIds) {
		this.bankIds = bankIds;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public List<ResLineNumberUploadExcelResultVO> getLineNumberUploadVOs() {
		return LineNumberUploadVOs;
	}
	public void setLineNumberUploadVOs(List<ResLineNumberUploadExcelResultVO> lineNumberUploadVOs) {
		LineNumberUploadVOs = lineNumberUploadVOs;
	}
	public String getImportExcelAreaType() {
		return importExcelAreaType;
	}
	public void setImportExcelAreaType(String importExcelAreaType) {
		this.importExcelAreaType = importExcelAreaType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
