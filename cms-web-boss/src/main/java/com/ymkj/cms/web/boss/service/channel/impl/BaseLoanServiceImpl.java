package com.ymkj.cms.web.boss.service.channel.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBacthNumVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBacthNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUpdBatchVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBacthNumVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBacthNumVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBaseVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;
import com.ymkj.cms.web.boss.facade.channel.BaseLoanFacade;
import com.ymkj.cms.web.boss.service.channel.IBaseLoanService;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;
import com.ymkj.sso.client.ShiroUtils;

/**
 * @author YM10189
 * @date 2017年5月2日
 * @Description:批次管理service impl(customer)
 */
@Service
public class BaseLoanServiceImpl implements IBaseLoanService {
	public static Logger logger = Logger.getLogger(BaseLoanServiceImpl.class);

	@Autowired
	private BaseLoanFacade baseLoanFacde;

	@Override
	public PageResult<ResLoanBaseVo> listPage(ReqLoanBaseVo reqLoanBaseVo) {
		PageResult<ResLoanBaseVo> pageResult = baseLoanFacde.listPage(reqLoanBaseVo);
		return pageResult;
	}

	@Override
	public Response<ResBacthNumVO> createBacthNum(ReqBacthNumVO reqBatchNumVo) {
		Response<ResBacthNumVO> resBatchNumVo = baseLoanFacde.createBatch(reqBatchNumVo);
		return resBatchNumVo;
	}

	@Override
	public PageResult<ResLoanBacthNumVo> listBatchInfoPage(ReqLoanBacthNumVo reqLoanBacthNumVo) {
		PageResult<ResLoanBacthNumVo> pageResult = baseLoanFacde.listLoanBatchNumPage(reqLoanBacthNumVo);
		return pageResult;
	}

	@Override
	public List<ResLoanCheckExpVo> listLoanCheckExp(ReqLoanBaseVo requestVo) {
		return baseLoanFacde.listLoanCheckExp(requestVo);
	}

	@Override
	public List<ResLoanExpVo> listLoanExp(ReqLoanBaseVo requestVo) {
		return baseLoanFacde.listLoanLoanExp(requestVo);
	}

	@Override
	public Response<String> updateBacthNum(ReqUpdBatchVo reqUpdBatchVo) {
		return baseLoanFacde.updateBatch(reqUpdBatchVo);
	}

	@Override
	public Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo) {
		return baseLoanFacde.wmxtExpLoanQuery(requestVo);
	}

	@Override
	public Response<List<String>> findLoanNoByNum(ReqLoanBaseVo newReqLoanBaseVo) {
		return baseLoanFacde.findLoanNoByNum(newReqLoanBaseVo);
	}

	@Override
	public Response<List<ResWMXTExportCheckVo>> wmxtExpLoanCheck(ReqLoanBaseVo requestVo) {
		return baseLoanFacde.wmxtExpLoanCheck(requestVo);
	}

	@Override
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile) {
		return baseLoanFacde.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
	}

	@Override
	public void checkRequestManagerOperateRecord(ReqLoanBaseVo reqCheck) {
		ResEmployeeVO resEmployeeVO=ShiroUtils.getCurrentUser();
		reqCheck.setCreate(resEmployeeVO.getUsercode());
		reqCheck.setCreateId(resEmployeeVO.getId());
		baseLoanFacde.checkRequestManagerOperateRecord(reqCheck);
	}

	@Override
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVo) {
		return baseLoanFacde.findCodebyParentIds(listResWMXTDataVo);
	}

}
