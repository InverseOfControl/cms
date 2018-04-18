package com.ymkj.cms.biz.api.vo.response.apply;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实体类 对应 表 demo
 */
public class ResPersonHistoryLoanVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	 
  
	  private    ResLoanProductVO loanProductVO  ;
	  private   ResLoanExtVO loanExtVO  ;
	  private   ResAPPPersonInfoVO appPersonInfoVO  ;	
	  private   ResLoanBaseVO loanBaseVO  ;	
	  private   ResAPPPersonVO appPersonVO  ;
	  private   ResAPPEstateInfoVO estateInfoVO ;
	  private   ResAPPCarInfoVO appCarInfoVO ;
	  private  ResAPPPolicyInfoVO appPolicyVO ;
	  private  ResAPPProvidentInfoVO appProvidentInfoVO ;
	  private  ResAPPCardLoanInfoVO appCardLoanLoanVO  ;
	  private  ResAPPSalaryLoanInfoVO appSalaryLoanInfoVO  ;
	  private  ResAPPMasterLoanInfoVO appMasterLoanInfoVO  ;
	  private  ResMerchantLoanInfoVO appMerchantLoanInfoVO  ;
	  private  List<ResAPPContactInfoVO> appContactInfoVOList;
	public ResLoanProductVO getLoanProductVO() {
		return loanProductVO;
	}
	public void setLoanProductVO(ResLoanProductVO loanProductVO) {
		this.loanProductVO = loanProductVO;
	}
	public ResLoanExtVO getLoanExtVO() {
		return loanExtVO;
	}
	public void setLoanExtVO(ResLoanExtVO loanExtVO) {
		this.loanExtVO = loanExtVO;
	}
	public ResAPPPersonInfoVO getAppPersonInfoVO() {
		return appPersonInfoVO;
	}
	public void setAppPersonInfoVO(ResAPPPersonInfoVO appPersonInfoVO) {
		this.appPersonInfoVO = appPersonInfoVO;
	}
	public ResLoanBaseVO getLoanBaseVO() {
		return loanBaseVO;
	}
	public void setLoanBaseVO(ResLoanBaseVO loanBaseVO) {
		this.loanBaseVO = loanBaseVO;
	}
	public ResAPPPersonVO getAppPersonVO() {
		return appPersonVO;
	}
	public void setAppPersonVO(ResAPPPersonVO appPersonVO) {
		this.appPersonVO = appPersonVO;
	}
	public ResAPPEstateInfoVO getEstateInfoVO() {
		return estateInfoVO;
	}
	public void setEstateInfoVO(ResAPPEstateInfoVO estateInfoVO) {
		this.estateInfoVO = estateInfoVO;
	}
	public ResAPPCarInfoVO getAppCarInfoVO() {
		return appCarInfoVO;
	}
	public void setAppCarInfoVO(ResAPPCarInfoVO appCarInfoVO) {
		this.appCarInfoVO = appCarInfoVO;
	}
	public ResAPPPolicyInfoVO getAppPolicyVO() {
		return appPolicyVO;
	}
	public void setAppPolicyVO(ResAPPPolicyInfoVO appPolicyVO) {
		this.appPolicyVO = appPolicyVO;
	}
	public ResAPPProvidentInfoVO getAppProvidentInfoVO() {
		return appProvidentInfoVO;
	}
	public void setAppProvidentInfoVO(ResAPPProvidentInfoVO appProvidentInfoVO) {
		this.appProvidentInfoVO = appProvidentInfoVO;
	}
	public ResAPPCardLoanInfoVO getAppCardLoanLoanVO() {
		return appCardLoanLoanVO;
	}
	public void setAppCardLoanLoanVO(ResAPPCardLoanInfoVO appCardLoanLoanVO) {
		this.appCardLoanLoanVO = appCardLoanLoanVO;
	}
	public ResAPPSalaryLoanInfoVO getAppSalaryLoanInfoVO() {
		return appSalaryLoanInfoVO;
	}
	public void setAppSalaryLoanInfoVO(ResAPPSalaryLoanInfoVO appSalaryLoanInfoVO) {
		this.appSalaryLoanInfoVO = appSalaryLoanInfoVO;
	}
	public ResAPPMasterLoanInfoVO getAppMasterLoanInfoVO() {
		return appMasterLoanInfoVO;
	}
	public void setAppMasterLoanInfoVO(ResAPPMasterLoanInfoVO appMasterLoanInfoVO) {
		this.appMasterLoanInfoVO = appMasterLoanInfoVO;
	}
	public ResMerchantLoanInfoVO getAppMerchantLoanInfoVO() {
		return appMerchantLoanInfoVO;
	}
	public void setAppMerchantLoanInfoVO(ResMerchantLoanInfoVO appMerchantLoanInfoVO) {
		this.appMerchantLoanInfoVO = appMerchantLoanInfoVO;
	}
	public List<ResAPPContactInfoVO> getAppContactInfoVOList() {
		return appContactInfoVOList;
	}
	public void setAppContactInfoVOList(
			List<ResAPPContactInfoVO> appContactInfoVOList) {
		this.appContactInfoVOList = appContactInfoVOList;
	}
 
 

}
