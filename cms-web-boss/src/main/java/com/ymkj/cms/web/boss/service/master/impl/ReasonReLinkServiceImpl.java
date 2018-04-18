package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSTMReasonVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSReasonVO;
import com.ymkj.cms.web.boss.facade.master.ReasonReLinksFacade;
import com.ymkj.cms.web.boss.service.master.IReasonReLinkService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Service
public class ReasonReLinkServiceImpl implements IReasonReLinkService {
	@Autowired
	private ReasonReLinksFacade reasonReLinksFacade;

	@Override
	public PageResult<ResBMSReasonVO> listPage(ReqBMSTMReasonVO reqReasonVO) {
		
		return reasonReLinksFacade.listPage(reqReasonVO);
	}

	@Override
	public ResBMSReasonVO queryReasonManagementInit( ReqBMSReasonVO reqBMSReasonVO) {
		
		return reasonReLinksFacade.queryReasonManagementInit(reqBMSReasonVO);
	}
	@Override
	public boolean editReasonReLinks(ReqBMSReasonVO reqBMSReasonVO) {
		ResEmployeeVO vo=ShiroUtils.getCurrentUser();
		reqBMSReasonVO.setModifier(vo.getUsercode());
		reqBMSReasonVO.setModifierId(vo.getId());
		int resMsg = reasonReLinksFacade.editReasonReLinks(reqBMSReasonVO);
		return resMsg >= 1 ? true : true;
	}
	
	

}
