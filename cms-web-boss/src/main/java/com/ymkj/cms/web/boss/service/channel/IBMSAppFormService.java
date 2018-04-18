package com.ymkj.cms.web.boss.service.channel;

import java.util.List;

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


/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书管理 service interface(customer)
 */
public interface IBMSAppFormService {

	/**
	 * 分页查询
	 * 
	 * @param reqAppFormVo
	 * @return
	 */
	PageResult<ResAppFormVO> listPage(ReqAppFormVO reqAppFormVo);
	
	/**
	 * 划拨申请导出查询
	 * @param reqVo
	 * @return
	 */
	public ResAppBookVo findAppBookPdfXls(ReqBatchNumVo reqVo);
	
	/**
	 * 放款明细查询
	 * @param reqVo
	 * @return
	 */
	public List<LoanApplyDetailVo> findLoanAppBookXls(ReqBatchNumVo reqVo);
	
	/**
	 * 还款计划导出
	 * @param reqVo
	 * @return
	 */
	public List<ResRepaymentExpVo> exportLoanRepayment(ReqBatchNumVo reqVo);
	
	/**
	 * 签章
	 * @param requestVo
	 * @return
	 */
	public Response<byte[]> dealEsignature(ReqdealEsignatureVo requestVo);
	
	/**
	 * 申请书导入
	 * @param requestVo
	 * @return
	 */
	public Response<String> importAppBook(ReqdealEsignatureVo requestVo);
	
	/**
	 * 文件下载批次
	 * @param paraMap
	 * @return
	 */
	public Response<String> findRequestFileBatchNum(ReqFileBatchNumVo requestVo);
	
	/**
	 * 是否上传过申请书
	 * @param batchNum
	 * @return
	 */
	public boolean isUploadApplyBook(ReqAppFormVO reqAppFormVo);
	
	/**
	 * 是否为当天第一次下载
	 * @param paraMap
	 * @return
	 */
	public boolean isCurrentDayDownloadFirst(ReqAppFormVO reqAppFormVo);
}
