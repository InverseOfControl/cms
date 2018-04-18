package com.ymkj.cms.biz.entity.master;

/**
 * 原因表对应的实体类
 * @author YM10115
 *
 */
public class BMSReason extends BMSProductBaseEntity{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1383311437219191615L;
	
	/**
	 * 主键ID
	 */
	private Long Id;
	
	/**
	 * 原因
	 */
	private String reason;
	/**
	 * 原因码
	 */
	private String code;
	/**
	 * 原因解释
	 */
	private String reasonTexplain;
	/**
	 * 分类(1级,2级)
	 */
	private String type;
	/**
	 * 父原因ID
	 */
	private Long parentId;
	/**
	 * 排序
	 */
	private Integer levelOrder;
	/**
	 * 类型
	 */
	private String operationType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 操作模块
	 */
	private String operationModule;
	/**
	 * 是否擦入黑名单
	 */
	private Integer isBlacklist;
	/**
	 * 限制再申请天数
	 */
	private Integer canRequestDays;
	
	private String firstReason;  //一级原因
	
	private String moduleName;
	
	private Integer isDisabled;  //是否禁用
	
	private String conditionType;  //状态类型
	
	private String relationCode;  //关联code
	
	private String disPlayRelation;  //显示环节
	private String operationRelation;  //操作环节
	private String texPlain;  //是否显示
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	public Integer getLevelOrder() {
		return levelOrder;
	}
	public void setLevelOrder(Integer levelOrder) {
		this.levelOrder = levelOrder;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperationModule() {
		return operationModule;
	}
	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}
	public Integer getIsBlacklist() {
		return isBlacklist;
	}
	public void setIsBlacklist(Integer isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	public Integer getCanRequestDays() {
		return canRequestDays;
	}
	public void setCanRequestDays(Integer canRequestDays) {
		this.canRequestDays = canRequestDays;
	}
	public String getFirstReason() {
		return firstReason;
	}
	public void setFirstReason(String firstReason) {
		this.firstReason = firstReason;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
	public String getRelationCode() {
		return relationCode;
	}
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	public String getDisPlayRelation() {
		return disPlayRelation;
	}
	public void setDisPlayRelation(String disPlayRelation) {
		this.disPlayRelation = disPlayRelation;
	}
	public String getOperationRelation() {
		return operationRelation;
	}
	public void setOperationRelation(String operationRelation) {
		this.operationRelation = operationRelation;
	}
	public String getTexPlain() {
		return texPlain;
	}
	public void setTexPlain(String texPlain) {
		this.texPlain = texPlain;
	}
	
	
	
	

}
