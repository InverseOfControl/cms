package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSRecordExportHomeTownDao;
import com.ymkj.cms.biz.entity.master.BMSRecordExportYD;

@Repository
public class BMSRecordExportHomeTownDaoImpl extends BaseDaoImpl<BMSRecordExportYD> implements IBMSRecordExportHomeTownDao{

	@Override
	public List<BMSRecordExportYD> uploadExcelYD(Map<String, Object> map) {
		return super.getSqlSession().selectList(super.getStatement("uploadExcelYD"), map);
	}

}
