package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSRecordExportDao;
import com.ymkj.cms.biz.entity.master.BMSRecordExportSZ;
import com.ymkj.cms.biz.entity.master.BMSRecordExportYD;
import com.ymkj.cms.biz.service.master.IBMSRecordExportService;

@Service
public class BMSRecordExportServiceImpl extends BaseServiceImpl<BMSRecordExportSZ> implements IBMSRecordExportService{
	@Autowired
	private IBMSRecordExportDao iBMSRecordExportDao;
	@Override
	protected BaseDao<BMSRecordExportSZ> getDao() {
		return iBMSRecordExportDao;
	}
	@Override
	public List<BMSRecordExportSZ> uploadExcelSZ(Map<String, Object> map) {
		return iBMSRecordExportDao.uploadExcelSZ(map);
	}
	

}
