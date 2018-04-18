package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ReqBMSSysLogVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private String operatorCode; // 操作人工号
	private String firstLevelDir; // 一级目录code
	private String twoLevelDir; // 二级目录code
	private String operationType; //操作类型code

	private Date operationTimeStart; //操作时间
	private Date operationTimeEnd; //操作时间

	private int pageNum; // 当前页数
	private int pageSize;

	private int rows;// 行数
	private int page;// 页数
	
	
	
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
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getFirstLevelDir() {
		return firstLevelDir;
	}
	public void setFirstLevelDir(String firstLevelDir) {
		this.firstLevelDir = firstLevelDir;
	}
	public String getTwoLevelDir() {
		return twoLevelDir;
	}
	public void setTwoLevelDir(String twoLevelDir) {
		this.twoLevelDir = twoLevelDir;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Date getOperationTimeStart() {
		return operationTimeStart;
	}
	public void setOperationTimeStart(Date operationTimeStart) {
		this.operationTimeStart = operationTimeStart;
	}
	public Date getOperationTimeEnd() {
		return operationTimeEnd;
	}
	public void setOperationTimeEnd(Date operationTimeEnd) {
		this.operationTimeEnd = operationTimeEnd;
	}
	public ReqBMSSysLogVO(){	
	}
	public ReqBMSSysLogVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
