package com.ymkj.cms.biz.api.vo.request.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqBMSReasonVO extends Request{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5695240175189784172L;

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
	
	private String creatorId;
	private String creator;
	
	private Long version;
	
	private Long modifierId;
	private String[] operationModules;
	private String modifier;
	private String firstReason;
	private Integer isDisabled;
	private String moduleName;
	private String conditionType;  //状态类型
	private String relationCode; //关联环节CODE
	private String reasonType;
	
	private String disPlayRelation;  //显示环节
	private String operationRelation;  //操作环节
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
	/**
	 *限制再申请天数
	 */
	private String canRequestDays;
	
	public String getCanRequestDays() {
		return canRequestDays;
	}
	public void setCanRequestDays(String canRequestDays) {
		this.canRequestDays = canRequestDays;
	}
	/**
	 * 用来存储一级原因(用于导入excel,生成sql)
	 */
	private String theFirstCellReason;
	/**
	 * 用来存储上级原因名称(用于导入excel,生成sql)
	 */
	private String theUpperLevelReason; 
	public String getTheFirstCellReason() {
		return theFirstCellReason;
	}
	public void setTheFirstCellReason(String theFirstCellReason) {
		this.theFirstCellReason = theFirstCellReason;
	}
	public String getTheUpperLevelReason() {
		return theUpperLevelReason;
	}
	public void setTheUpperLevelReason(String theUpperLevelReason) {
		this.theUpperLevelReason = theUpperLevelReason;
	}
	/**
	 * 申请录入(用于导入excel,生成sql)
	 */
	private String shenQingLuRu;
	/**
	 * 录入复合(用于导入excel,生成sql)
	 */
	private String luRuFuHe;
	/**
	 * 信审初审(用于导入excel,生成sql)
	 */
	private String xinShenChuShen;
	/**
	 * 信审终审(用于导入excel,生成sql)
	 */
	private String xinShenZhongShen;
	/**
	 * 合同签约(用于导入excel,生成sql)
	 */
	private String heTonQianYue;
	/**
	 * 合同确认(用于导入excel,生成sql)
	 */
	private String heTonQueRen;
	/**
	 * 放款审核(用于导入excel,生成sql)
	 */
	private String fangKuanShenHe;
	/**
	 * 放款确认(用于导入excel,生成sql)
	 */
	private String fangKuanQueRen;
    /**
     * 终审退回门店
     */
	private String zhongShenReturnMD;
	 /**
     * 终审退回初审
     */
	private String zhongShenReturnCS;
	/**
	 * 初审挂起
	 */
	private String chuShenGuaQi;
	/**
	 * 终审挂起
	 */
	private String zhongShenGuaQi;
	/**
	 * 申请件信息维护
	 */
	private String sqjxiwh;
	public String getShenQingLuRu() {
		return shenQingLuRu;
	}
	public void setShenQingLuRu(String shenQingLuRu) {
		this.shenQingLuRu = shenQingLuRu;
	}
	public String getLuRuFuHe() {
		return luRuFuHe;
	}
	public void setLuRuFuHe(String luRuFuHe) {
		this.luRuFuHe = luRuFuHe;
	}
	public String getXinShenChuShen() {
		return xinShenChuShen;
	}
	public void setXinShenChuShen(String xinShenChuShen) {
		this.xinShenChuShen = xinShenChuShen;
	}
	public String getXinShenZhongShen() {
		return xinShenZhongShen;
	}
	public void setXinShenZhongShen(String xinShenZhongShen) {
		this.xinShenZhongShen = xinShenZhongShen;
	}
	public String getHeTonQianYue() {
		return heTonQianYue;
	}
	public void setHeTonQianYue(String heTonQianYue) {
		this.heTonQianYue = heTonQianYue;
	}
	public String getHeTonQueRen() {
		return heTonQueRen;
	}
	public void setHeTonQueRen(String heTonQueRen) {
		this.heTonQueRen = heTonQueRen;
	}
	public String getFangKuanShenHe() {
		return fangKuanShenHe;
	}
	public void setFangKuanShenHe(String fangKuanShenHe) {
		this.fangKuanShenHe = fangKuanShenHe;
	}
	public String getFangKuanQueRen() {
		return fangKuanQueRen;
	}
	public void setFangKuanQueRen(String fangKuanQueRen) {
		this.fangKuanQueRen = fangKuanQueRen;
	}
	
	private List<ReqBMSReasonVO> reasonList;

	public List<ReqBMSReasonVO> getReasonList() {
		return reasonList;
	}
	public void setReasonList(List<ReqBMSReasonVO> reasonList) {
		this.reasonList = reasonList;
	}
	public Integer getIsBlacklist() {
		return isBlacklist;
	}
	public void setIsBlacklist(Integer isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getModifierId() {
		return modifierId;
	}
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getZhongShenReturnMD() {
		return zhongShenReturnMD;
	}
	public void setZhongShenReturnMD(String zhongShenReturnMD) {
		this.zhongShenReturnMD = zhongShenReturnMD;
	}
	public String getZhongShenReturnCS() {
		return zhongShenReturnCS;
	}
	public void setZhongShenReturnCS(String zhongShenReturnCS) {
		this.zhongShenReturnCS = zhongShenReturnCS;
	}
	public String getChuShenGuaQi() {
		return chuShenGuaQi;
	}
	public void setChuShenGuaQi(String chuShenGuaQi) {
		this.chuShenGuaQi = chuShenGuaQi;
	}
	public String getZhongShenGuaQi() {
		return zhongShenGuaQi;
	}
	public void setZhongShenGuaQi(String zhongShenGuaQi) {
		this.zhongShenGuaQi = zhongShenGuaQi;
	}
	public String getFirstReason() {
		return firstReason;
	}
	public void setFirstReason(String firstReason) {
		this.firstReason = firstReason;
	}
	public Integer getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Integer isDisabled) {
		this.isDisabled = isDisabled;
	}
	public String[] getOperationModules() {
		return operationModules;
	}
	public void setOperationModules(String[] operationModules) {
		this.operationModules = operationModules;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
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
	public String getSqjxiwh() {
		return sqjxiwh;
	}
	public void setSqjxiwh(String sqjxiwh) {
		this.sqjxiwh = sqjxiwh;
	}
	
	
	

}
