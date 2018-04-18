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
import com.ymkj.cms.biz.api.enums.EnumConstants;
import com.ymkj.cms.biz.api.service.master.IBMSLineNumberSetExecuter;
import com.ymkj.cms.biz.api.service.master.IBMSLoanUrgentConfigExecuter;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSUrgentLimitListVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSLoanUrgentConfigVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberSetVO;
import com.ymkj.cms.biz.api.vo.response.master.ResLineNumberUploadExcelResultVO;
import com.ymkj.cms.web.boss.exception.BusinessException;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Component
public class LineNumberSetFacade extends BaseFacade{
	@Autowired
	private IBMSLineNumberSetExecuter iBMSLineNumberSetExecuter;

	
	public PageResult<ResLineNumberSetVO> listPage(ReqLineNumberSetVO reqLineNumberSetVO) {

		// 业务调用
		PageResponse<ResLineNumberSetVO> pageResponse = iBMSLineNumberSetExecuter.listPage(reqLineNumberSetVO);

		// 响应结果处理
		if (pageResponse.isSuccess()) {
			PageResult<ResLineNumberSetVO> pageResult = new PageResult<ResLineNumberSetVO>();
			BeanUtils.copyProperties(pageResponse, pageResult);
			return pageResult;
		} else {
			throw new BusinessException(CoreErrorCode.FACADE_RESPONSE_FAIL, this.getResMsg(pageResponse));
		}
	}
	
	public Response<Integer> updateLineNumber(ReqLineNumberSetVO reqLineNumberSetVO){
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqLineNumberSetVO.setCreateName(vo.getUsercode());
		reqLineNumberSetVO.setCreateId(vo.getId().toString());
		Response<Integer> res=iBMSLineNumberSetExecuter.updateLineNumber(reqLineNumberSetVO);
		return res;
	};
	
	public void insertOrUpdateDic(List<ResLineNumberUploadExcelResultVO> LineNumberUploadVOs,String importExcelAreaType){
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		String name=vo.getUsercode();
		String id=vo.getId().toString();
		ReqLineNumberSetVO reqLineNumberSetVO=new ReqLineNumberSetVO();
		reqLineNumberSetVO.setSysCode(EnumConstants.BMS_SYSTEM_CODE);
		reqLineNumberSetVO.setLineNumberUploadVOs(LineNumberUploadVOs);
		reqLineNumberSetVO.setImportExcelAreaType(importExcelAreaType);
		reqLineNumberSetVO.setId(id);
		reqLineNumberSetVO.setName(name);
		iBMSLineNumberSetExecuter.insertOrUpdateDic(reqLineNumberSetVO);
	};
}
