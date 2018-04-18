package com.ymkj.cms.biz.api.vo.request.apply;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Request;

public class AuditEntryVO  extends Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ApplyInfoVO applyInfoVO;         // 申请信息
	private AssetsInfoVO assetsInfoVO;   	 // 资产信息
	private BasicInfoVO basicInfoVO;  // 基本信息
	private List<ContactInfoVO> contactInfoVOList; // 联系人信息
	private Long version;//当前版本的版本号   历史数据无此版本号
	
	public ApplyInfoVO getApplyInfoVO() {
		return applyInfoVO;
	}
	public void setApplyInfoVO(ApplyInfoVO applyInfoVO) {
		this.applyInfoVO = applyInfoVO;
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
