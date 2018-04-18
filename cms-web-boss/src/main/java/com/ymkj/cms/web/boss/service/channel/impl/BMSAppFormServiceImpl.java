package com.ymkj.cms.web.boss.service.channel.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.channel.ReqAppFormVO;
import com.ymkj.cms.biz.api.vo.request.channel.ReqBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqFileBatchNumVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqdealEsignatureVo;
import com.ymkj.cms.biz.api.vo.response.channel.LoanApplyDetailVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppBookVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAppFormVO;
import com.ymkj.cms.biz.api.vo.response.channel.ResRepaymentExpVo;
import com.ymkj.cms.web.boss.facade.channel.AppFormFacade;
import com.ymkj.cms.web.boss.service.channel.IBMSAppFormService;


/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书管理service impl(customer)
 */
@Service     
public class BMSAppFormServiceImpl implements IBMSAppFormService{
	public static Logger logger=Logger.getLogger(BMSAppFormServiceImpl.class);

	@Autowired
	private AppFormFacade appFormFacade;
	
	@Override
	public PageResult<ResAppFormVO> listPage(ReqAppFormVO reqAppFormVo) {
		PageResult<ResAppFormVO> pageResult =appFormFacade.listPage(reqAppFormVo);
		return pageResult;
	}

	@Override
	public ResAppBookVo findAppBookPdfXls(ReqBatchNumVo reqVo) {
		return appFormFacade.findAppBookPdfXls(reqVo);
	}

	@Override
	public List<LoanApplyDetailVo> findLoanAppBookXls(ReqBatchNumVo reqVo) {
		return appFormFacade.findLoanAppBookXls(reqVo);
	}

	@Override
	public List<ResRepaymentExpVo> exportLoanRepayment(ReqBatchNumVo reqVo) {
		return appFormFacade.exportLoanRepayment(reqVo);
	}

	@Override
	public Response<byte[]> dealEsignature(ReqdealEsignatureVo requestVo) {
		return appFormFacade.dealEsignature(requestVo);
	}

	@Override
	public Response<String> importAppBook(ReqdealEsignatureVo requestVo) {
		return appFormFacade.importAppBook(requestVo);
	}

	@Override
	public Response<String> findRequestFileBatchNum(ReqFileBatchNumVo requestVo) {
		return appFormFacade.findRequestFileBatchNum(requestVo);
	}

	@Override
	public boolean isUploadApplyBook(ReqAppFormVO reqAppFormVo) {
		return appFormFacade.isUploadApplyBook(reqAppFormVo).getData();
	}

	@Override
	public boolean isCurrentDayDownloadFirst(ReqAppFormVO reqAppFormVo) {
		return appFormFacade.isCurrentDayDownloadFirst(reqAppFormVo).getData();
	}
}
