package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.web.boss.facade.master.ReasonManagementFacade;
import com.ymkj.cms.web.boss.service.master.IReasonManagementService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Service
public class ReasonManagementServiceImpl implements IReasonManagementService{

	@Autowired
	private ReasonManagementFacade reasonManagementFacade;
	@Override
	public PageResult<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {		
		return reasonManagementFacade.listPage(reqReasonVO);
	}
	@Override
	public boolean addReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqBMSReasonVO.setCreator(vo.getUsercode());
		reqBMSReasonVO.setCreatorId(vo.getId().toString());
		int resMsg = reasonManagementFacade.addReasonManagement(reqBMSReasonVO);
		return resMsg == 1 ? true : false;
	}
	@Override
	public ResBMSReasonVO queryReasonManagementInit(ReqBMSReasonVO reqBMSReasonVO) {
		return reasonManagementFacade.queryReasonManagementInit(reqBMSReasonVO);
	}
	@Override
	public boolean editReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqBMSReasonVO.setModifier(vo.getUsercode());
		reqBMSReasonVO.setModifierId(vo.getId());
		int resMsg = reasonManagementFacade.editReasonManagement(reqBMSReasonVO);
		return resMsg == 1 ? true : false;
	}
	@Override
	public boolean deleteReasonManagement(ReqBMSReasonVO reqBMSReasonVO) {
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqBMSReasonVO.setModifier(vo.getUsercode());
		reqBMSReasonVO.setModifierId(vo.getId());
		int resMsg = reasonManagementFacade.deleteReasonManagement(reqBMSReasonVO);
		return resMsg == 1 ? true : false;
	}

}
