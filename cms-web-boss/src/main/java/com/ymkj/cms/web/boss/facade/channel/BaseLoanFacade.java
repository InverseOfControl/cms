package com.ymkj.cms.web.boss.facade.channel;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.channel.IBacthNumExecuter;
import com.ymkj.cms.biz.api.service.channel.IBaseLoanExecuter;
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
import com.ymkj.cms.web.boss.common.SystemCode;

/**
 * @author YM10189
 * @date 2017年5月6日
 * @Description:批次管理
 */
@Component
public class BaseLoanFacade extends BaseFacade {

	@Autowired
	private IBaseLoanExecuter bmsBaseLoanExecuter;

	@Autowired
	private IBacthNumExecuter batchNumExecuter;

	public PageResult<ResLoanBaseVo> listPage(ReqLoanBaseVo reqLoanBaseVo) {
		PageResult<ResLoanBaseVo> pageResult = new PageResult<ResLoanBaseVo>();
		reqLoanBaseVo.setSysCode("1111111111");
		PageResponse<ResLoanBaseVo> pageResponse = bmsBaseLoanExecuter.searchLoanListby(reqLoanBaseVo);
		BeanUtils.copyProperties(pageResponse, pageResult);
		return pageResult;
	}
	
	public Response<ResBacthNumVO> createBatch(ReqBacthNumVO reqBatchNumVo) {
		reqBatchNumVo.setSysCode("1111111111");
		Response<ResBacthNumVO> resBatchNumVo = batchNumExecuter.createBacthNum(reqBatchNumVo);
		return resBatchNumVo;
	}
	
	public Response<String> updateBatch(ReqUpdBatchVo reqUpdBatchVo) {
		reqUpdBatchVo.setSysCode("1111111111");
		Response<String> response = bmsBaseLoanExecuter.updateBacth(reqUpdBatchVo);
		return response;
	}

	public PageResult<ResLoanBacthNumVo> listLoanBatchNumPage(ReqLoanBacthNumVo reqLoanBatchNumVo) {
		PageResult<ResLoanBacthNumVo> pageResult = new PageResult<ResLoanBacthNumVo>();
		reqLoanBatchNumVo.setSysCode(SystemCode.SysCode);
		PageResponse<ResLoanBacthNumVo> pageResponse = batchNumExecuter.searchBacthNum(reqLoanBatchNumVo);
		BeanUtils.copyProperties(pageResponse, pageResult);
		return pageResult;
	}
	
	public List<ResLoanCheckExpVo> listLoanCheckExp(ReqLoanBaseVo reqLoanBaseVo){
		reqLoanBaseVo.setSysCode(SystemCode.SysCode);
		Response<List<ResLoanCheckExpVo>> response=bmsBaseLoanExecuter.exportLoanCheck(reqLoanBaseVo);
		return response.getData();
	}
	
	public List<ResLoanExpVo> listLoanLoanExp(ReqLoanBaseVo reqLoanBaseVo){
		reqLoanBaseVo.setSysCode(SystemCode.SysCode);
		Response<List<ResLoanExpVo>> response=bmsBaseLoanExecuter.exportLoan(reqLoanBaseVo);
		return response.getData();
	}
	
	public Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo){
		Response<List<ResWMXTExportVo>> response=bmsBaseLoanExecuter.wmxtExpLoanQuery(requestVo);
		return response;
	}

	
	public Response<List<String>> findLoanNoByNum(ReqLoanBaseVo newReqLoanBaseVo){
		return bmsBaseLoanExecuter.findLoanNoByNum(newReqLoanBaseVo);
	}
	
	
	public Response<List<ResWMXTExportCheckVo>>  wmxtExpLoanCheck(ReqLoanBaseVo requestVo){
		return bmsBaseLoanExecuter.wmxtExpLoanCheck(requestVo);
	}
	
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile){
		return bmsBaseLoanExecuter.findFileSeqByBatchNum(reqLoanBaseVoQuerySeqFile);
		
	}
	
	public void checkRequestManagerOperateRecord(ReqLoanBaseVo reqCheck){
		bmsBaseLoanExecuter.checkRequestManagerOperateRecord(reqCheck);
	}
	
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVo){
		return bmsBaseLoanExecuter.findCodebyParentIds(listResWMXTDataVo);
	}
}
