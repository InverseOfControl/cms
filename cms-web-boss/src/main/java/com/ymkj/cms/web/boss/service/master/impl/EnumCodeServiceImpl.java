package com.ymkj.cms.web.boss.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.master.ResProductBaseListVO;
import com.ymkj.cms.web.boss.facade.apply.EnumCodeFacade;
import com.ymkj.cms.web.boss.service.master.IEnumCodeService;

@Service
public class EnumCodeServiceImpl implements IEnumCodeService {

	@Autowired
	private EnumCodeFacade enumCodeFacade;

	@Override
	public PageResult<ResBMSEnumCodeVO> listPage(ReqBMSEnumCodeVO reqDemoVO) {
		return enumCodeFacade.listPage(reqDemoVO);
	}

	@Override
	public boolean addEnumCode(ReqBMSEnumCodeVO reqDemoVO) {
		int resMsg = this.enumCodeFacade.addEnumCode(reqDemoVO);
		return resMsg == 1 ? true : false;
	}

	@Override
	public boolean updateEnumCode(ReqBMSEnumCodeVO reqDemoVO) {
		return this.enumCodeFacade.updateEnumCode(reqDemoVO);
	}

	@Override
	public ResBMSEnumCodeVO findById(Long id) {
		return enumCodeFacade.findById(id);
	}

	@Override
	public ResListVO<ResBMSEnumCodeVO> findEnumCodeByCondition(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		return enumCodeFacade.findEnumCodeByCondition(reqBMSEnumCodeVO);
	}
	
	@Override
	public ResListVO<ResBMSEnumCodeVO> listEnumCodeBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO){
		return enumCodeFacade.listEnumCodeBy(reqBMSEnumCodeVO);
	}

	@Override
	public ResListVO<ResBMSEnumCodeVO> listBy(ReqBMSEnumCodeVO reqBMSEnumCodeVO) {
		
		return enumCodeFacade.listby(reqBMSEnumCodeVO);
	}
}
