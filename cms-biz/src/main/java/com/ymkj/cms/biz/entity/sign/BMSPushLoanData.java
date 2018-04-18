package com.ymkj.cms.biz.entity.sign;

import java.io.Serializable;
import java.util.List;

import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPCardLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPContactInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMasterLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPMerchantLoanInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPolicyInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPProvidentInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPSalaryLoanInfoEntity;
import com.ymkj.cms.biz.entity.audit.BMSAPPHistoryEntity;


/**
 * 申请信息对应的接口类
 * @author user
 *
 */
public class BMSPushLoanData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
    private APPCarInfoEntity tmAppCarInfo;//车辆信息表
	
	private APPEstateInfoEntity tmAppEstateInfo;//房产信息表
    
    private List<BMSAPPHistoryEntity> tmAppHistories;//申请审批操作历史表
	
	private APPSalaryLoanInfoEntity tmAppSalaryLoanInfo;//随薪贷信息表
	
	private APPCardLoanInfoEntity tmAppCardLoanInfo;//卡友贷信息
	
	private APPMerchantLoanInfoEntity tmAppMerchantLoanInfo;//淘宝商户贷信息表
	
	private APPProvidentInfoEntity tmAppProvidentInfo;//公积金信息表
	
	private APPMasterLoanInfoEntity tmAppMasterLoanInfo;//淘宝达人贷信息表
	    
	private  BMSLoanApp tmAppMain;
	
    private APPPolicyInfoEntity tmAppPolicyInfo;//保单信息表
    
	private APPPersonInfoEntity tmAppPersonInfo;//申请人信息
    
    private List<APPContactInfoEntity> tmAppContactInfos;//联系人信息
   


	
	public APPCarInfoEntity getTmAppCarInfo() {
		return tmAppCarInfo;
	}

	public void setTmAppCarInfo(APPCarInfoEntity tmAppCarInfo) {
		this.tmAppCarInfo = tmAppCarInfo;
	}

	public APPEstateInfoEntity getTmAppEstateInfo() {
		return tmAppEstateInfo;
	}

	public void setTmAppEstateInfo(APPEstateInfoEntity tmAppEstateInfo) {
		this.tmAppEstateInfo = tmAppEstateInfo;
	}

	public APPPersonInfoEntity getTmAppPersonInfo() {
		return tmAppPersonInfo;
	}

	public void setTmAppPersonInfo(APPPersonInfoEntity tmAppPersonInfo) {
		this.tmAppPersonInfo = tmAppPersonInfo;
	}

	public BMSLoanApp getTmAppMain() {
		return tmAppMain;
	}

	public void setTmAppMain(BMSLoanApp tmAppMain) {
		this.tmAppMain = tmAppMain;
	}

	public List<APPContactInfoEntity> getTmAppContactInfos() {
		return tmAppContactInfos;
	}

	public void setTmAppContactInfos(List<APPContactInfoEntity> tmAppContactInfos) {
		this.tmAppContactInfos = tmAppContactInfos;
	}

	public List<BMSAPPHistoryEntity> getTmAppHistories() {
		return tmAppHistories;
	}

	public void setTmAppHistories(List<BMSAPPHistoryEntity> tmAppHistories) {
		this.tmAppHistories = tmAppHistories;
	}


	public APPPolicyInfoEntity getTmAppPolicyInfo() {
		return tmAppPolicyInfo;
	}

	public void setTmAppPolicyInfo(APPPolicyInfoEntity tmAppPolicyInfo) {
		this.tmAppPolicyInfo = tmAppPolicyInfo;
	}

	public APPProvidentInfoEntity getTmAppProvidentInfo() {
		return tmAppProvidentInfo;
	}

	public void setTmAppProvidentInfo(APPProvidentInfoEntity tmAppProvidentInfo) {
		this.tmAppProvidentInfo = tmAppProvidentInfo;
	}

	public APPCardLoanInfoEntity getTmAppCardLoanInfo() {
		return tmAppCardLoanInfo;
	}

	public void setTmAppCardLoanInfo(APPCardLoanInfoEntity tmAppCardLoanInfo) {
		this.tmAppCardLoanInfo = tmAppCardLoanInfo;
	}

	public APPSalaryLoanInfoEntity getTmAppSalaryLoanInfo() {
		return tmAppSalaryLoanInfo;
	}

	public void setTmAppSalaryLoanInfo(APPSalaryLoanInfoEntity tmAppSalaryLoanInfo) {
		this.tmAppSalaryLoanInfo = tmAppSalaryLoanInfo;
	}

	public APPMerchantLoanInfoEntity getTmAppMerchantLoanInfo() {
		return tmAppMerchantLoanInfo;
	}

	public void setTmAppMerchantLoanInfo(APPMerchantLoanInfoEntity tmAppMerchantLoanInfo) {
		this.tmAppMerchantLoanInfo = tmAppMerchantLoanInfo;
	}

	public APPMasterLoanInfoEntity getTmAppMasterLoanInfo() {
		return tmAppMasterLoanInfo;
	}

	public void setTmAppMasterLoanInfo(APPMasterLoanInfoEntity tmAppMasterLoanInfo) {
		this.tmAppMasterLoanInfo = tmAppMasterLoanInfo;
	}


    
    
}
