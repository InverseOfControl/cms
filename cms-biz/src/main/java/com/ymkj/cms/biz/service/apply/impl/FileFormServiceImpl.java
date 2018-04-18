package com.ymkj.cms.biz.service.apply.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.apply.IFileFormDao;
import com.ymkj.cms.biz.entity.apply.FileFormEntity;
import com.ymkj.cms.biz.service.apply.FileFormService;

@Service
public class FileFormServiceImpl extends BaseServiceImpl<FileFormEntity> implements FileFormService {
	
	@Autowired
	private IFileFormDao ifileFormDao;

	@Override
	protected BaseDao<FileFormEntity> getDao() {
		return ifileFormDao;
	}

	@Override
	public Long saveFileForm(FileFormEntity fileFormEntity) {
		return ifileFormDao.insert(fileFormEntity);
	}

	@Override
	public Long updateArchives(FileFormEntity fileFormEntity) {
		return ifileFormDao.update(fileFormEntity);
	}

	@Override
	public FileFormEntity queryArchives(Map<String, Object> paramMap) {

		return ifileFormDao.getByLoanNo(paramMap);
	}
}
