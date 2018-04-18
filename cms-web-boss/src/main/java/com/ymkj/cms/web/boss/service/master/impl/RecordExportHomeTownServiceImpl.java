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
import com.ymkj.cms.web.boss.facade.master.RecordExportHomeTownFacade;
import com.ymkj.cms.web.boss.service.master.IRecordExportHomeTownService;
@Service
public class RecordExportHomeTownServiceImpl implements IRecordExportHomeTownService{

	@Autowired
	private RecordExportHomeTownFacade recordExportHomeTownFacade;
	
	@Override
	public PageResult<ResBMSRecordExportYDVO> listPageYd(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		PageResult<ResBMSRecordExportYDVO> pageResult = recordExportHomeTownFacade.listPageYd(reqBMSRecordExportVO);
		return pageResult;
	}

	@Override
	public Response<List<ResBMSRecordExportYDVO>> uploadExcelYD(ReqBMSRecordExportVO reqBMSRecordExportVO) {
		Response<List<ResBMSRecordExportYDVO>> response = recordExportHomeTownFacade.uploadExcelYD(reqBMSRecordExportVO);
		return response;
	}
}
