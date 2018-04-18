package com.ymkj.cms.biz.api.vo.request.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class  ReqBMSTMReasonVO extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String reason; //原因
	private String code; //原因码
	private String reasonTexplain;//原因解释
	private String type;//分类(1级 2级)
	private Long parentId;//父原因ID
	private Long level;//排序
	private String operationModule;//操作模块
	private String operationType;//操作类型
	private String pemark;//备注
	private List<Long> parentIds;
	private int pageNum;    // 当前页数
	private int pageSize;
	
	private int rows;// 行数
	private int page;// 页数
    private Integer isDisabled;  //是否禁用
	
	private String conditionType;  //状态类型
	
	private String reasonType;  //原因类型 1.常规原因 2.特殊原因
	
	private int isBlackList; 
	
	private String text;
	public List<ReqBMSTMReasonVO>children;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReasonTexplain() {
		return reasonTexplain;
	}
	public void setReasonTexplain(String reasonTexplain) {
		this.reasonTexplain = reasonTexplain;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public String getOperationModule() {
		return operationModule;
	}
	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getPemark() {
		return pemark;
	}
	public void setPemark(String pemark) {
		this.pemark = pemark;
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
		this.setPageSize(rows);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.setPageNum(page);
	}
	public List<Long> getParentIds() {
		return parentIds;
	}
	public void setParentIds(List<Long> parentIds) {
		this.parentIds = parentIds;
	}
	public Integer getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public int getIsBlackList() {
		return isBlackList;
	}
	public void setIsBlackList(int isBlackList) {
		this.isBlackList = isBlackList;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ReqBMSTMReasonVO> getChildren() {
		return children;
	}
	public void setChildren(List<ReqBMSTMReasonVO> children) {
		this.children = children;
	}
	
}
