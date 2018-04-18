package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.Date;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ApplyEntryVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	
	private ApplyInfoVO applyInfoVO;         // 申请信息
	private AssetsInfoVO assetsInfoVO;   	 // 资产信息
	private BasicInfoVO basicInfoVO;  // 基本信息
	private List<ContactInfoVO> contactInfoVOList; // 联系人信息
	private Long modifierId; //更新员工id
	private String modifier; //更新员工姓名
	
	private Long creatorId; //新增员工id
	private String creator; //新增员工姓名
	
	private String serviceCode; //操作员工Code
	private String serviceName; //操作名称
	
	private Long owningBranchId;//门店ID
	private String owningBranch;//门店
	private String owningBranchAttribute;//门店属性
	
	private String loggedArea;	//进件门店区域
	private String loggedAreaName;	//进件门店区域名称
	
	private String optionModule;//操作模块		1 申请录入  2 录入修改 3 录入复核 10退件箱
	private String optionType;//操作类型	101 提交 102保存 
	
	private Long version;  //当前版本号
	
	private Long ifFirstSubmit;//是否第一次提交     0 不是  1是
	
	
	private Date applyEndTime;//录入结束时间（复核开始时间）
	private Date auditEndTime;//复核提交时间
	
	private ApprovalOpinionsVO approvalOpinionsVO;//新增审核接口返回回来的数据对象VO 2017-06-27 hcr 新增
	private FindHisVO findHisVO;//新增核心接口返回数据VO 2017-06-27 hcr 新增
	
	
	public Date getAuditEndTime() {
		return auditEndTime;
	}
	public void setAuditEndTime(Date auditEndTime) {
		this.auditEndTime = auditEndTime;
	}
	public Date getApplyEndTime() {
		return applyEndTime;
	}
	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}
	public String getOwningBranchAttribute() {
		return owningBranchAttribute;
	}
	public void setOwningBranchAttribute(String owningBranchAttribute) {
		this.owningBranchAttribute = owningBranchAttribute;
	}
	public String getLoggedAreaName() {
		return loggedAreaName;
	}
	public void setLoggedAreaName(String loggedAreaName) {
		this.loggedAreaName = loggedAreaName;
	}
	public String getLoggedArea() {
		return loggedArea;
	}
	public void setLoggedArea(String loggedArea) {
		this.loggedArea = loggedArea;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Long getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	public String getOptionModule() {
		return optionModule;
	}
	public void setOptionModule(String optionModule) {
		this.optionModule = optionModule;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
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
	public AssetsInfoVO getAssetsInfoVO() {
		return assetsInfoVO;
	}
	public void setAssetsInfoVO(AssetsInfoVO assetsInfoVO) {
		this.assetsInfoVO = assetsInfoVO;
	}	
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public ApplyInfoVO getApplyInfoVO() {
		return applyInfoVO;
	}
	public void setApplyInfoVO(ApplyInfoVO applyInfoVO) {
		this.applyInfoVO = applyInfoVO;
	}
	 
	public BasicInfoVO getBasicInfoVO() {
		return basicInfoVO;
	}
	public void setBasicInfoVO(BasicInfoVO basicInfoVO) {
		this.basicInfoVO = basicInfoVO;
	}
	public List<ContactInfoVO> getContactInfoVOList() {
		return contactInfoVOList;
	}
	public void setContactInfoVOList(List<ContactInfoVO> contactInfoVOList) {
		this.contactInfoVOList = contactInfoVOList;
	}
	 
	public ApplyEntryVO(){	
	}
	public ApplyEntryVO(String sysCode){
		super.setSysCode(sysCode);
	}
	public Long getIfFirstSubmit() {
		return ifFirstSubmit;
	}
	public void setIfFirstSubmit(Long ifFirstSubmit) {
		this.ifFirstSubmit = ifFirstSubmit;
	}
	public ApprovalOpinionsVO getApprovalOpinionsVO() {
		return approvalOpinionsVO;
	}
	public void setApprovalOpinionsVO(ApprovalOpinionsVO approvalOpinionsVO) {
		this.approvalOpinionsVO = approvalOpinionsVO;
	}
	public FindHisVO getFindHisVO() {
		return findHisVO;
	}
	public void setFindHisVO(FindHisVO findHisVO) {
		this.findHisVO = findHisVO;
	}
		
}
