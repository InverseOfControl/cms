package com.ymkj.cms.biz.dao.app.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.app.ISaveApplyInfoDao;
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

@Repository
public class SaveApplyInfoDaoImpl extends BaseDaoImpl<BMSSaveApplyInfoEntity> implements ISaveApplyInfoDao{

	@Override
	public int insertAppPerson(AppAppPersonEntity appAppPersonEntity) {
		return getSessionTemplate().insert("insertAppPerson",appAppPersonEntity);
	}

	@Override
	public int insertLoanBase(AppLoanBaseEntity appLoanBaseEntity) {
		return getSessionTemplate().insert("insertLoanBase",appLoanBaseEntity);
	}

	@Override
	public int updateLoanBase(AppLoanBaseEntity appLoanBaseEntity) {
		return getSessionTemplate().insert("updateLoanBaseInfo",appLoanBaseEntity);
	}

	@Override
	public int insertLoanProduct(AppLoanProductEntity appLoanProductEntity) {
		return getSessionTemplate().insert("insertLoanProduct",appLoanProductEntity);
	}

	@Override
	public int insertLoanExt(AppLoanExtEntity appLoanExtEntity) {
		return getSessionTemplate().insert("insertLoanExt",appLoanExtEntity);
	}

	@Override
	public int insertAppPersonInfo(AppAppPersonInfoEntity appAppPersonInfoEntity) {
		return getSessionTemplate().insert("insertAppPersonInfo",appAppPersonInfoEntity);
	}

	@Override
	public int insertContactHead(AppAppContactHeadEntity appAppContactHeadEntity) {
		return getSessionTemplate().insert("insertContactHead",appAppContactHeadEntity);
	}

	@Override
	public int insertContactInfo(AppAppContactInfoEntity appAppContactInfoEntity) {
		return getSessionTemplate().insert("insertContactInfo",appAppContactInfoEntity);
	}

	@Override
	public int insertsalaryLoanInfo(AppAalaryLoanInfo appAalaryLoanInfo) {
		return getSessionTemplate().insert("insertsalaryLoanInfo",appAalaryLoanInfo);
	}

	@Override
	public int insertEstateInfo(AppEstateInfo appEstateInfo) {
		return getSessionTemplate().insert("insertEstateInfo",appEstateInfo);
	}

	@Override
	public int insertCarInfo(AppCarInfo appCarInfo) {
		return getSessionTemplate().insert("insertCarInfo",appCarInfo);
	}

	@Override
	public int insertPolicyInfo(AppPolicyInfo appPolicyInfo) {
		return getSessionTemplate().insert("insertPolicyInfo",appPolicyInfo);
	}

	@Override
	public int insertProvidentInfo(AppProvidentInfo appProvidentInfo) {
		return getSessionTemplate().insert("insertProvidentInfo",appProvidentInfo);
	}

	@Override
	public int insertCardLoanInfo(AppCardLoanInfo appCardLoanInfo) {
		return getSessionTemplate().insert("insertCardLoanInfo",appCardLoanInfo);
	}

	@Override
	public int insertMasterLoanInfo(AppMasterLoanInfo appMasterLoanInfo) {
		return getSessionTemplate().insert("insertMasterLoanInfo",appMasterLoanInfo);
	}

	@Override
	public int insertMerchantLoanInfo(AppMerchantLoanInfo appMerchantLoanInfo) {
		return getSessionTemplate().insert("insertMerchantLoanInfo",appMerchantLoanInfo);
	}

	@Override
	public List<Map<String, Object>> getAreaInfo(String areaCode) {
		return getSessionTemplate().selectList("getAreaInfo",areaCode);
	}

	@Override
	public int insertLoanBaseRela(LoanBaseRelaEntity loanBaseRelaEntity) {
		return getSessionTemplate().insert("insertLoanBaseRela",loanBaseRelaEntity);
	}

	@Override
	public long findIdByLoanNo(String loanNo) {
		Object obj=	getSessionTemplate().selectOne("findIdByLoanNo",loanNo);
		if(obj ==null){
			return 0L;
		}
		return Long.parseLong(String.valueOf(obj));
	}

	@Override
	public List<Map<String,Object>> getApplyServiceCount(Map<String,Object> map) {
		return getSessionTemplate().selectList("getApplyServiceCount",map);
	}

	@Override
	public List<Map<String, Object>> getFieldInfoList(Map<String, Object> map) {
		return getSessionTemplate().selectList("getFieldInfoList",map);
	}

	@Override
	public Map<String, Object> getLatestAssignService(String orgId) {
		return getSessionTemplate().selectOne("getLatestAssignService",orgId);
	}
}
