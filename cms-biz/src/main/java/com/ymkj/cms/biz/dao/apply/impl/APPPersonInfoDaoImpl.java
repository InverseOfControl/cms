package com.ymkj.cms.biz.dao.apply.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanImportVO;
import com.ymkj.cms.biz.dao.apply.APPPersonInfoDao;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;

@Repository
public class APPPersonInfoDaoImpl extends BaseDaoImpl<APPPersonInfoEntity> implements APPPersonInfoDao{
	
	@SuppressWarnings({ "rawtypes" })
	public APPPersonInfoEntity	findPersonByLoanNo(Map paramMap){	
		
		return super.getSqlSession().selectOne(super.getStatement("findPersonByLoanBaseId"), paramMap);		
	}

	@Override
	public ResBMSLoanImportVO findImportLoanInfo(Map<String,Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(super.getStatement("findImportLoanInfo"), paramMap);	
	}

	@Override
	public long updateAll(APPPersonInfoEntity appPersonInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateAll"), appPersonInfoEntity);
	}

	@Override
	public long updateUserPhone(APPPersonInfoEntity appPersonInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateUserPhone"), appPersonInfoEntity);
	}

	@Override
	public long updateById(APPPersonInfoEntity appPersonInfoEntity) {
		return super.getSqlSession().update(super.getStatement("updateById"), appPersonInfoEntity);
	}
}
