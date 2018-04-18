package com.ymkj.cms.biz.dao.app;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.app.BMSSaveApplyInfoEntity;
import com.ymkj.cms.biz.entity.app.asset.AppAalaryLoanInfo;
import com.ymkj.cms.biz.entity.app.asset.AppCarInfo;
import com.ymkj.cms.biz.entity.app.asset.AppCardLoanInfo;
import com.ymkj.cms.biz.entity.app.asset.AppEstateInfo;
import com.ymkj.cms.biz.entity.app.asset.AppMasterLoanInfo;
import com.ymkj.cms.biz.entity.app.asset.AppMerchantLoanInfo;
import com.ymkj.cms.biz.entity.app.asset.AppPolicyInfo;
import com.ymkj.cms.biz.entity.app.asset.AppProvidentInfo;
import com.ymkj.cms.biz.entity.app.input.AppAppContactHeadEntity;
import com.ymkj.cms.biz.entity.app.input.AppAppContactInfoEntity;
import com.ymkj.cms.biz.entity.app.input.AppAppPersonEntity;
import com.ymkj.cms.biz.entity.app.input.AppAppPersonInfoEntity;
import com.ymkj.cms.biz.entity.app.input.AppLoanBaseEntity;
import com.ymkj.cms.biz.entity.app.input.AppLoanExtEntity;
import com.ymkj.cms.biz.entity.app.input.AppLoanProductEntity;
import com.ymkj.cms.biz.entity.apply.LoanBaseRelaEntity;

public interface ISaveApplyInfoDao extends BaseDao<BMSSaveApplyInfoEntity>{
	
	//插入客户主表
	public int insertAppPerson(AppAppPersonEntity appAppPersonEntity);
	
	//插入申请主表信息
	public int insertLoanBase(AppLoanBaseEntity appLoanBaseEntity);
	
	//插入申请产品表信息
	public int insertLoanProduct(AppLoanProductEntity appLoanProductEntity);
	
	//插入申请扩展表信息
	public int insertLoanExt(AppLoanExtEntity appLoanExtEntity);
	
	//插入客户信息表
	public int insertAppPersonInfo(AppAppPersonInfoEntity appAppPersonInfoEntity);
	
	//插入联系人主表
	public int insertContactHead(AppAppContactHeadEntity appAppContactHeadEntity);
	
	//插入联系人信息表
	public int insertContactInfo(AppAppContactInfoEntity appAppContactInfoEntity);
	
	//插入随薪贷信息
	public int insertsalaryLoanInfo(AppAalaryLoanInfo appAalaryLoanInfo);
	
	//插入房产信息
	public int insertEstateInfo(AppEstateInfo appEstateInfo);
	
	//插入车辆信息
	public int insertCarInfo(AppCarInfo appCarInfo);
	
	//插入保单信息
	public int insertPolicyInfo(AppPolicyInfo appPolicyInfo);
	
	//插入公积金信息
	public int insertProvidentInfo(AppProvidentInfo appProvidentInfo);
	
	//卡友贷信息
	public int insertCardLoanInfo(AppCardLoanInfo appCardLoanInfo);
	
	//网购达人信息
	public int insertMasterLoanInfo(AppMasterLoanInfo appMasterLoanInfo);
	
	//淘宝商户信息
	public int insertMerchantLoanInfo(AppMerchantLoanInfo appMerchantLoanInfo);
	
	//获取省市区
	public List<Map<String,Object>> getAreaInfo(String areaCode);
	
	//插入主键信息
	public int insertLoanBaseRela(LoanBaseRelaEntity loanBaseRelaEntity);
	
	//获取营业部下在处理申请中状态单子的客服数量
	public List<Map<String,Object>> getApplyServiceCount(Map<String,Object> map);
	
	
	//更新借款信息
	public int updateLoanBase(AppLoanBaseEntity appLoanBaseEntity);

	//根据借款编号查询id
	public long findIdByLoanNo(String loanNo);
	
	//获取校验字段信息
	public List<Map<String,Object>> getFieldInfoList(Map<String,Object> map);
	
	//获取营业部 下最新分派的客服信息
	public Map<String,Object> getLatestAssignService(String orgId);

}
