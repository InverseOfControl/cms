package com.ymkj.cms.web.boss.service.master;

import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRecordExportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportSZVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportYDVO;

public interface IRecordExportService {

	/**
	 * 根据请求VO 查询分页信息(深圳地区已经签过合同的申请件)
	 * @param resDemoVO
	 * @return
	 */
	public PageResult<ResBMSRecordExportSZVO> listPage(ReqBMSRecordExportVO reqBMSRecordExportVO);
	/**
	 *  导出深圳地区excel查询对应的深圳地区信息不分页
	 */
	public Response<List<ResBMSRecordExportSZVO>> uploadExcelSZ(ReqBMSRecordExportVO reqBMSRecordExportVO);
	
	
	
	
}
