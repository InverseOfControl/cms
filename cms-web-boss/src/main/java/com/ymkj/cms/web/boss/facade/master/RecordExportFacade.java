package com.ymkj.cms.web.boss.facade.master;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.master.IBMSRecordExportExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSRecordExportVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportSZVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSRecordExportYDVO;
import com.ymkj.cms.web.boss.exception.BusinessException;

@Component
public class RecordExportFacade extends BaseFacade{

	@Autowired
	private IBMSRecordExportExecuter iBMSRecordExportExecuter;
	
	public PageResult<ResBMSRecordExportSZVO> listPage(ReqBMSRecordExportVO reqBMSRecordExportVO) {

		// 业务调用
		PageResponse<ResBMSRecordExportSZVO> pageResponse = iBMSRecordExportExecuter.listPage(reqBMSRecordExportVO);
		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResBMSRecordExportSZVO> pageResult = new PageResult<ResBMSRecordExportSZVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	
	
	public Response<List<ResBMSRecordExportSZVO>> uploadExcelSZ(ReqBMSRecordExportVO reqBMSRecordExportVO){
		Response<List<ResBMSRecordExportSZVO>> response=iBMSRecordExportExecuter.uploadExcelSZ(reqBMSRecordExportVO);
		return response;
	}
	
	
	
	
}
