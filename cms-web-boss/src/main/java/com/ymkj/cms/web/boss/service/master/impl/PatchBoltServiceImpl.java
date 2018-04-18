package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSPatchBoltVO;
import com.ymkj.cms.biz.api.vo.response.integratedsearch.ResQueryLoanVo;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSPatchBoltVO;
import com.ymkj.cms.web.boss.facade.apply.PatchBoltFacade;
import com.ymkj.cms.web.boss.service.master.IPatchBoltService;
@Service
public class PatchBoltServiceImpl implements IPatchBoltService{
	@Autowired
	private PatchBoltFacade patchBoltFacade;
	@Override
	public PageResponse<ResQueryLoanVo> listPage(ReqBMSPatchBoltVO reqBMSPatchBoltVO) {
		PageResponse<ResQueryLoanVo> pageResult=patchBoltFacade.listPage(reqBMSPatchBoltVO);
		return pageResult;
	}

}
