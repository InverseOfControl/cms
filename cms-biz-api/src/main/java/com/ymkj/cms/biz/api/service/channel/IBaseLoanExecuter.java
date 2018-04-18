package com.ymkj.cms.biz.api.service.channel;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.channel.ReqAccessoryVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqUpdBatchVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResAccessoryVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanBaseVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理
 */
public interface IBaseLoanExecuter {

	/**
	 * 根据条件查询借款信息
	 * 
	 * @param reqLoanBaseVo
	 * @return
	 */
	PageResponse<ResLoanBaseVo> searchLoanListby(ReqLoanBaseVo reqLoanBaseVo);

	/**
	 * 根据借款编号查询借款审核数据记录
	 * 
	 * @param reqLoanBaseVo
	 * @return
	 */
	Response<List<ResLoanCheckExpVo>> exportLoanCheck(ReqLoanBaseVo reqLoanBaseVo);

	/**
	 * 根据借款编号导出数据记录
	 * 
	 * @param reqLoanBaseVo
	 * @return
	 */
	Response<List<ResLoanExpVo>> exportLoan(ReqLoanBaseVo reqLoanBaseVo);

	/**
	 * 更新批次号
	 * 
	 * @param reqLoanBaseVo
	 * @return
	 */
	Response<String> updateBacth(ReqUpdBatchVo reqLoanBaseVo);

	/**
	 * 查询外贸信托下某一批次下的信息
	 */
	Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo);

	/**
	 * 根据批次号查询对应的借款信息
	 */
	Response<List<String>> findLoanNoByNum(ReqLoanBaseVo newReqLoanBaseVo);

	/**
	 * 根据批次号查询对应的还款计划
	 */
	Response<List<ResWMXTExportCheckVo>> wmxtExpLoanCheck(ReqLoanBaseVo requestVo);
	
	/**
	 * 附件下载
	 * @return
	 */
	Response<ResAccessoryVo> downAccessory(ReqAccessoryVo requestVo);
	
	/**
	 * 查询申请书表对应的当天日期有没有下载的08类型的信息
	 */
	Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile);
	
	/**
	 * 查询bms_request_file_operate_record表，如果存在数据更新不存在新增
	 */
	void checkRequestManagerOperateRecord(ReqLoanBaseVo reqCheck);
	
	/**
	 * 查询渤海信托机构城市表
	 */
	Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVo);

}
