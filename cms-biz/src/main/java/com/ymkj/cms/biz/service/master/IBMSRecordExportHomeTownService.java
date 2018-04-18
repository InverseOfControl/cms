package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSRecordExportSZ;
import com.ymkj.cms.biz.entity.master.BMSRecordExportYD;

public interface IBMSRecordExportHomeTownService extends BaseService<BMSRecordExportYD>{
	public List<BMSRecordExportYD> uploadExcelYD(Map<String,Object> map);
}
