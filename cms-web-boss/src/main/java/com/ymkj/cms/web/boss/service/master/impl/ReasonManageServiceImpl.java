package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.web.boss.facade.master.ReasonManageFacade;
import com.ymkj.cms.web.boss.service.master.IReasonManageService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Service
public class ReasonManageServiceImpl implements IReasonManageService {
	@Autowired
	private ReasonManageFacade reasonManageFacade;

	@Override
	public PageResult<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {
		
		return reasonManageFacade.listPage(reqReasonVO);
	}

	@Override
	public ResListVO<ResBMSReasonVO> findBMSReasonByValue(
			ReqBMSTMReasonVO reqReasonVO) {
		
		return reasonManageFacade.findBMSReasonByValue(reqReasonVO);
	}

	@Override
	public boolean addReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqBMSReasonVO.setCreator(vo.getUsercode());
		reqBMSReasonVO.setCreatorId(vo.getId().toString());
		int resMsg = reasonManageFacade.addReasonManagement(reqBMSReasonVO);
		return resMsg == 1 ? true : false;
	}
	@Override
	public boolean editReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqBMSReasonVO.setModifier(vo.getUsercode());
		reqBMSReasonVO.setModifierId(vo.getId());
		int resMsg = reasonManageFacade.editReasonManagement(reqBMSReasonVO);
		return resMsg >= 1 ? true : false;
	}

	public ResListVO<ResBMSReasonVO> findReasonExport(ReqBMSTMReasonVO reqBMSReasonVO) {
		
		return reasonManageFacade.findReasonExport(reqBMSReasonVO);
	}

	@Override
	public ResListVO<ResBMSReasonVO> findReasonByParame( ReqBMSReasonVO reqBMSReasonVO) {
		
		return reasonManageFacade.findReasonByParame(reqBMSReasonVO);
	}

	@Override
	public boolean delReasonByCode(ReqBMSReasonVO reqBMSReasonVO) {
		Integer count=	reasonManageFacade.delReasonByCode(reqBMSReasonVO);
		if(count>0){
			return true;
		} else{
			return false;
		}
	}
}
