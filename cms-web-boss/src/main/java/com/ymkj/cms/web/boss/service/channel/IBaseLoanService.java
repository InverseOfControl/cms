package com.ymkj.cms.web.boss.service.channel;

import java.util.List;

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

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理 service interface(customer)
 */
/**
 * @author YM10189
 * @date 2017年5月6日
 * @Description:
 */
public interface IBaseLoanService {

	/**
	 * 分页查询
	 * @param reqAppFormVo
	 * @return
	 */
	PageResult<ResLoanBaseVo> listPage(ReqLoanBaseVo reqLoanBaseVo);
	
	/**
	 * 批次生成
	 * @param reqBatchNumVo
	 * @return
	 */
	Response<ResBacthNumVO> createBacthNum(ReqBacthNumVO reqBatchNumVo);
	
	/**
	 * 批次更新
	 * @param reqBatchNumVo
	 * @return
	 */
	Response<String> updateBacthNum(ReqUpdBatchVo reqUpdBatchVo);
	
	/**
	 * 批次查询
	 * @param reqLoanBacthNumVo
	 * @return
	 */
	PageResult<ResLoanBacthNumVo> listBatchInfoPage(ReqLoanBacthNumVo reqLoanBacthNumVo);
	
	
	/**
	 * 查询借款审核数据记录
	 * @param paraMap
	 * @return
	 */
	public List<ResLoanCheckExpVo> listLoanCheckExp(ReqLoanBaseVo requestVo);
	
	/**
	 * 查询借款数据记录
	 * @param paraMap
	 * @return
	 */
	public List<ResLoanExpVo> listLoanExp(ReqLoanBaseVo requestVo);
	
	/**
	 * 查询渠道是外贸信托下某一个批次下的信息
	 */
	public Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo);
	/**
	 * 根据批次号查询对应的借款编号
	 */
	public Response<List<String>> findLoanNoByNum(ReqLoanBaseVo newReqLoanBaseVo);
	
	
	/**
	 * 查询渠道是外贸信托导出还款计划信息
	 */
	public Response<List<ResWMXTExportCheckVo>> wmxtExpLoanCheck(ReqLoanBaseVo requestVo);
	
	
	/**
	 * 查询申请书当天有没有下载过08类型的记录
	 */
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile);
	
	/**
	 * 判断 bms_request_file_operate_record 是否存在记录，存在新增不存在更新
	 */
	public void checkRequestManagerOperateRecord(ReqLoanBaseVo reqCheck);
	
	/**
	 * 查询渤海信托机构城市表
	 */
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVo);
}
