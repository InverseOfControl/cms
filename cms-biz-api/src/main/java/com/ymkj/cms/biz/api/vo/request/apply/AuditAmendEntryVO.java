package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class AuditAmendEntryVO  extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private AssetsInfoVO assetsInfoVO;   	 // 资产信息
	private BasicInfoVO basicInfoVO;  // 基本信息
	private List<ContactInfoVO> contactInfoVOList; // 联系人信息
	
	private Long loanBaseId	; //申请信息Id
	private String loanNo;	 //借款编号
	private String productCd	; //申请产品
	
	private Long modifierId; //更新员工id
	private String modifier; //更新员工姓名
	
	private String version;//修改的版本号
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public AssetsInfoVO getAssetsInfoVO() {
		return assetsInfoVO;
	}
	public void setAssetsInfoVO(AssetsInfoVO assetsInfoVO) {
		this.assetsInfoVO = assetsInfoVO;
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
	public Long getLoanBaseId() {
		return loanBaseId;
	}
	public void setLoanBaseId(Long loanBaseId) {
		this.loanBaseId = loanBaseId;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
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
	
	
	
}
