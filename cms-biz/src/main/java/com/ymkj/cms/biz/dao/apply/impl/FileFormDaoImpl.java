package com.ymkj.cms.biz.dao.apply.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.IFileFormDao;
import com.ymkj.cms.biz.entity.apply.FileFormEntity;

@Repository
public class FileFormDaoImpl extends BaseDaoImpl<FileFormEntity> implements IFileFormDao{

	@Override
	public FileFormEntity getByLoanNo(Map<String, Object> paramMap) {
		
		return getSessionTemplate().selectOne("getByLoanNo", paramMap);
	}

}
