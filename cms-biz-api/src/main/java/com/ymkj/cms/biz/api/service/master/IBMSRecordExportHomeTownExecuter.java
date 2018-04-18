package com.ymkj.cms.biz.api.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRecordExportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportYDVO;

public interface IBMSRecordExportHomeTownExecuter {

	//查询异地的签约过的申请件
	public PageResponse<ResBMSRecordExportYDVO> listPage(ReqBMSRecordExportVO reqBMSRecordExportVO);
	
	//导出异地excel之前的查询
		public Response<List<ResBMSRecordExportYDVO>> uploadExcelYD(ReqBMSRecordExportVO reqBMSRecordExportVO);
}
