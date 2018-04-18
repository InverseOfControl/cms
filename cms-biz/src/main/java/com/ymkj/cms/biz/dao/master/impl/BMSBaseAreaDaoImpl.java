package com.ymkj.cms.biz.dao.master.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBaseAreaVO;
import com.ymkj.cms.biz.dao.master.IBMSBaseAreaDao;
import com.ymkj.cms.biz.entity.master.BMSBaseArea;
@Repository
public class BMSBaseAreaDaoImpl extends BaseDaoImpl<BMSBaseArea> implements IBMSBaseAreaDao{
	


	/*@Autowired
	protected SqlSessionFactory sqlSessionFactory;*/
	@Override
	public void deletelAll(BMSBaseArea bmsBaseArea) {
		getSessionTemplate().delete(getStatement("deletelAll"));
	}

	/*@Override
	public Integer insert(ReqBMSBaseAreaVO reqBMSBaseAreaVO) {
		return  sqlSessionTemplate.insert(getStatement("insert"), reqBMSBaseAreaVO);
	}*/
	
}
