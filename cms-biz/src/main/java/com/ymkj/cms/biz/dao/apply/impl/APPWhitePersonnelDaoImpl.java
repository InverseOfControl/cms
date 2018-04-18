package com.ymkj.cms.biz.dao.apply.impl;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.APPWhitePersonnelDao;
import com.ymkj.cms.biz.entity.apply.APPWhitePersonnelEntity;


@Repository
public class APPWhitePersonnelDaoImpl extends BaseDaoImpl<APPWhitePersonnelEntity> implements APPWhitePersonnelDao{

	@Override
	public int deleteVo(APPWhitePersonnelEntity appWhitePersonnelEntity) {
		return getSessionTemplate().delete("deleteVo", appWhitePersonnelEntity);
	}
 
}
