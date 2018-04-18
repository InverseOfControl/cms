package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSRecordExportDao;
import com.ymkj.cms.biz.dao.master.IBMSRecordExportHomeTownDao;
import com.ymkj.cms.biz.entity.master.BMSRecordExportYD;
import com.ymkj.cms.biz.service.master.IBMSRecordExportHomeTownService;

@Service
public class BMSRecordExportHomeTownServiceImpl extends BaseServiceImpl<BMSRecordExportYD> implements IBMSRecordExportHomeTownService{

	@Autowired
	private IBMSRecordExportHomeTownDao iBMSRecordExportHomeTownDao;
	@Override
	protected BaseDao<BMSRecordExportYD> getDao() {
		return iBMSRecordExportHomeTownDao;
	}
	
	
	@Override
	public List<BMSRecordExportYD> uploadExcelYD(Map<String, Object> map) {
		return iBMSRecordExportHomeTownDao.uploadExcelYD(map);
	}

}
