package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSRecordExportYD;

public interface IBMSRecordExportHomeTownDao extends BaseDao<BMSRecordExportYD>{

	public List<BMSRecordExportYD> uploadExcelYD(Map<String, Object> map);
}
