package com.ymkj.cms.web.boss.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRecordExportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportSZVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportYDVO;
import com.ymkj.cms.web.boss.facade.master.RecordExportFacade;
import com.ymkj.cms.web.boss.service.master.IRecordExportService;

@Service
public class RecordExportServiceImpl implements IRecordExportService{

	@Autowired
	private RecordExportFacade recordExportFacade;
	
	@Override
	public PageResult<ResBMSRecordExportSZVO> listPage(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		PageResult<ResBMSRecordExportSZVO> pageResult = recordExportFacade.listPage(reqBMSRecordExportVO);
		return pageResult;
	}

	@Override
	public Response<List<ResBMSRecordExportSZVO>> uploadExcelSZ(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		Response<List<ResBMSRecordExportSZVO>> response = recordExportFacade.uploadExcelSZ(reqBMSRecordExportVO);
		return response;
	}

	

}
