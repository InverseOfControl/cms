package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.master.IBMSRecordExportDao;
import com.ymkj.cms.biz.entity.master.BMSRecordExportSZ;

@Repository
public class BMSRecordExportDaoImpl extends BaseDaoImpl<BMSRecordExportSZ> implements IBMSRecordExportDao{

	public List<BMSRecordExportSZ> uploadExcelSZ(Map<String, Object> map){
		return super.getSqlSession().selectList(super.getStatement("uploadExcelSZ"), map);
	};
}
