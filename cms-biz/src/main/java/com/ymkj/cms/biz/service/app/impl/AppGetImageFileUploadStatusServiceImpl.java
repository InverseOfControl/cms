package com.ymkj.cms.biz.service.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.app.IAppDataInfoDao;
import com.ymkj.cms.biz.dao.app.IAppGetImageFileUploadStatusDao;
import com.ymkj.cms.biz.entity.app.AppDataInfoEntity;
import com.ymkj.cms.biz.entity.app.BMSGetImageFileUploadEntity;
import com.ymkj.cms.biz.service.app.IAppGetImageFileUploadStatusService;
@Service
public class AppGetImageFileUploadStatusServiceImpl extends BaseServiceImpl<BMSGetImageFileUploadEntity> implements IAppGetImageFileUploadStatusService{

	
	@Autowired
	private IAppGetImageFileUploadStatusDao iAppGetImageFileUploadStatusDao;
	
	@Override
	protected BaseDao<BMSGetImageFileUploadEntity> getDao() {
		return iAppGetImageFileUploadStatusDao;
	}




}
