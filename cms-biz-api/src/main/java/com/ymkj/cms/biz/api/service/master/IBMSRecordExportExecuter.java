package com.ymkj.cms.biz.api.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRecordExportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportSZVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportYDVO;

public interface IBMSRecordExportExecuter {
	//查询深圳地区的签约过的申请件
	public PageResponse<ResBMSRecordExportSZVO> listPage(ReqBMSRecordExportVO reqBMSRecordExportVO);
	
	//导出深圳地区excel之前的查询
	public Response<List<ResBMSRecordExportSZVO>> uploadExcelSZ(ReqBMSRecordExportVO reqBMSRecordExportVO);
	
	
	
}
