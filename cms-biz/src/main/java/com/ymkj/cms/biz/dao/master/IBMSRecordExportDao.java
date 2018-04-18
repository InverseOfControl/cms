package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSRecordExportSZ;
import com.ymkj.cms.biz.entity.master.BMSRecordExportYD;

public interface IBMSRecordExportDao extends BaseDao<BMSRecordExportSZ>{

	public List<BMSRecordExportSZ> uploadExcelSZ(Map<String,Object> map);
	
	
	
}
