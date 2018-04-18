package com.ymkj.cms.web.boss.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSCreditRatingLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCreditRatingLimitVO;
import com.ymkj.cms.web.boss.facade.apply.BankFacade;
import com.ymkj.cms.web.boss.facade.master.CreditRatingLimitFacade;
import com.ymkj.cms.web.boss.service.master.ICreditRatingLimitService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

@Service
public class CreditRatingLimitServiceImpl implements ICreditRatingLimitService{

	@Autowired
	private CreditRatingLimitFacade creditRatingLimitFacade;

	@Override
	public PageResult<ResBMSCreditRatingLimitVO> listPage(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		PageResult<ResBMSCreditRatingLimitVO> pageResult = creditRatingLimitFacade.listPage(reqBMSCreditRatingLimitVO);
		return pageResult;
	}

	@Override
	public Response<List<Map<String, Object>>> findByProductAll(ReqBMSCreditRatingLimitVO v) {
		return creditRatingLimitFacade.findByProductAll(v);
	}

	@Override
	public Response<Integer> addCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
		reqBMSCreditRatingLimitVO.setCreatorId(resEmployeeVO.getId());
		reqBMSCreditRatingLimitVO.setCreator(resEmployeeVO.getUsercode());
		return creditRatingLimitFacade.addCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> deleteCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
		reqBMSCreditRatingLimitVO.setCreatorId(resEmployeeVO.getId());
		reqBMSCreditRatingLimitVO.setCreator(resEmployeeVO.getUsercode());
		return creditRatingLimitFacade.deleteCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<ResBMSCreditRatingLimitVO> loadCreditRatingLimit(
			ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return creditRatingLimitFacade.loadCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> updateCreditRatingLimit(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
		reqBMSCreditRatingLimitVO.setCreatorId(resEmployeeVO.getId());
		reqBMSCreditRatingLimitVO.setCreator(resEmployeeVO.getUsercode());
		return creditRatingLimitFacade.updateCreditRatingLimit(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> findAddIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return creditRatingLimitFacade.findAddIsRepeat(reqBMSCreditRatingLimitVO);
	}

	@Override
	public Response<Integer> findUpdateIsRepeat(ReqBMSCreditRatingLimitVO reqBMSCreditRatingLimitVO) {
		return creditRatingLimitFacade.findUpdateIsRepeat(reqBMSCreditRatingLimitVO);
	}
}
