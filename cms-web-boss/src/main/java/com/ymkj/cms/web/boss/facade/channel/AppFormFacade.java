package com.ymkj.cms.web.boss.facade.channel;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.facade.BaseFacade;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.service.channel.IAppBookManageExecuter;
import com.ymkj.cms.biz.api.vo.request.channel.ReqAppFormVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqdealEsignatureVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppFormVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;


@Component
public class AppFormFacade extends BaseFacade{
	
	@Autowired
	private IAppBookManageExecuter appBookManageExecuter;
	
	public PageResult<ResAppFormVO> listPage(ReqAppFormVO reqAppFormVo) {
		PageResult<ResAppFormVO> pageResult = new PageResult<ResAppFormVO>();
		reqAppFormVo.setSysCode("1111111111"); 
		PageResponse<ResAppFormVO> pageResponse=appBookManageExecuter.listPages(reqAppFormVo);
		BeanUtils.copyProperties(pageResponse, pageResult);
		return pageResult;
	}
	
	public ResAppBookVo findAppBookPdfXls(ReqBatchNumVo reqVo){
		reqVo.setSysCode("1111111111");
		Response<ResAppBookVo> response=appBookManageExecuter.findAppBookPdfXls(reqVo);
		return response.getData();
	}
	
	
	public List<LoanApplyDetailVo> findLoanAppBookXls(ReqBatchNumVo reqVo){
		reqVo.setSysCode("1111111111");
		Response<List<LoanApplyDetailVo>> response=appBookManageExecuter.findLoanAppBookXls(reqVo);
		return response.getData();
	}
	
	
	public List<ResRepaymentExpVo> exportLoanRepayment(ReqBatchNumVo reqVo){
		reqVo.setSysCode("1111111111");
		Response<List<ResRepaymentExpVo>> response=appBookManageExecuter.exportLoanRepayment(reqVo);
		return response.getData();
	}
	
	public Response<byte[]> dealEsignature(ReqdealEsignatureVo requestVo){
		requestVo.setSysCode("1111111111");
		return appBookManageExecuter.dealEsignature(requestVo);
	}
	
	public Response<String>importAppBook(ReqdealEsignatureVo requestVo){
		requestVo.setSysCode("1111111111");
		return appBookManageExecuter.importAppBook(requestVo);
	}
	
	public Response<String> findRequestFileBatchNum(ReqFileBatchNumVo requestVo){
		requestVo.setSysCode("1111111111");
		return appBookManageExecuter.findRequestFileBatchNum(requestVo);
	}
	
	public Response<Boolean> isUploadApplyBook(ReqAppFormVO reqAppFormVo) {
		return appBookManageExecuter.isUploadApplyBook(reqAppFormVo);
	}

	public Response<Boolean> isCurrentDayDownloadFirst(ReqAppFormVO reqAppFormVo) {
		return appBookManageExecuter.isCurrentDayDownloadFirst(reqAppFormVo);
	}

}
