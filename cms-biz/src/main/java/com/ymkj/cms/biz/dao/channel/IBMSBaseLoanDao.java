package com.ymkj.cms.biz.dao.channel;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.channel.ReqLoanBaseVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqWMXTDataVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResBhxtCitOrgVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanCheckExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResLoanExpVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResRequestFileOperateRecordVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportCheckVo;
import com.ymkj.cms.biz.api.vo.response.channel.ResWMXTExportVo;
import com.ymkj.cms.biz.entity.channel.BMSLoanBatch;
import com.ymkj.cms.biz.entity.channel.RequestFileOperateRecord;

/**
 * @author YM10189
 * @date 2017年5月5日
 * @Description:批次管理 dao interface
 */
public interface IBMSBaseLoanDao extends BaseDao<BMSLoanBatch>{
	
	/**
	 * 批次生成
	 * @return
	 */
	public long addBatch(List<BMSLoanBatch> bmsLoanBatch);
	
	/**
	 * 查询借款审核数据记录
	 * @param paraMap
	 * @return
	 */
	public List<ResLoanCheckExpVo> listLoanCheckExp(Map<String,Object> paraMap);
	
	/**
	 * 查询借款数据记录
	 * @param paraMap
	 * @return
	 */
	public List<ResLoanExpVo> listLoanExp(Map<String,Object> paraMap);
	
	
	/**
	 * 批次删除
	 * @return
	 */
	public long delBatch(List<String> loanNos);
	
	/**
	 *  查询外贸信托下某一批次下的信息
	 */
	public Response<List<ResWMXTExportVo>> wmxtExpLoanQuery(ReqLoanBaseVo requestVo);
	
	/**
	 * 根据批次号查询对应的借款编号
	 */
	public Response<List<String>> findLoanNoByNum(String num);
	
	/**
	 * 查询外贸信托下还款计划信息
	 */
	public Response<List<ResWMXTExportCheckVo>> wmxtExpLoanCheck(ReqLoanBaseVo requestVo);
	/**
	 * 查询申请书表示下载的类型是08的信息
	 */
	public Response<ResRequestFileOperateRecordVo> findFileSeqByBatchNum(ReqLoanBaseVo reqLoanBaseVoQuerySeqFile);
	/**
	 * 根据信息查询bms_request_file_operate_record是否存在数据
	 */
	public  RequestFileOperateRecord checkRequestManagerOperateRecord(Map<String,Object> map);
	
	/**
	 * 新增bms_request_file_operate_record
	 */
	public int insertRequestManagerOperateRecord(Map<String,Object> map);
	/**
	 * 更新 bms_request_file_operate_record
	 */
	public int updateRequestManagerOperateRecord(Map<String,Object> map);
	
	/**
	 * 根据对应营业部的父节点ID查询对应的渤海信托城市机构表
	 */
	public Response<List<ResBhxtCitOrgVo>> findCodebyParentIds(List<ReqWMXTDataVo> listResWMXTDataVos);
	
}
