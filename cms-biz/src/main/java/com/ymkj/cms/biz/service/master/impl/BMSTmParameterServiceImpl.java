package com.ymkj.cms.biz.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSTmParameterDao;
import com.ymkj.cms.biz.entity.master.BMSTmParameter;
import com.ymkj.cms.biz.service.master.IBMSTmParameterService;

@Service
public class BMSTmParameterServiceImpl extends BaseServiceImpl<BMSTmParameter> implements IBMSTmParameterService {

	@Autowired
	private IBMSTmParameterDao tmParameterDao;

	@Override
	public long insert(BMSTmParameter tmParameter) {
		return tmParameterDao.insert(tmParameter);
	}

	@Override
	public void update(BMSTmParameter tmParameter) {
		tmParameterDao.update(tmParameter);
	}

	@Override
	protected BaseDao<BMSTmParameter> getDao() {
		return tmParameterDao;
	}

	@Override
	public List<BMSTmParameter> findByCode(String code) {
		return tmParameterDao.findByCode(code);
	}

	@Override
	public List<BMSTmParameter> queryByCode(String code) {
		return tmParameterDao.queryByCode(code);
	}

}
