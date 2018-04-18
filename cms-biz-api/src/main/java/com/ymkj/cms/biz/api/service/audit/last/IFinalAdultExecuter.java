package com.ymkj.cms.biz.api.service.audit.last;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSPlReassignMentUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;
import com.ymkj.cms.biz.api.vo.response.audit.last.ResBMSFinalAduitUpdVO;

public interface IFinalAdultExecuter {
	/***
	 * 终审更新接口(拒绝)
	 */
	public ResBMSFinalAduitUpdVO zSRejectUpd(ReqZsUpdVO reqFinalVo);
	/***
	 * 终审更新接口(挂起)
	 */
	public ResBMSFinalAduitUpdVO zSHangupUpd(ReqZsUpdVO reqFinalVo);
	/***
	 * 终审更新接口(提交)
	 */
	public ResBMSFinalAduitUpdVO zSSubmitUpd(ReqZsUpdVO reqFinalVo);
	/***
	 * 终审更新接口(退回)
	 */
	public ResBMSFinalAduitUpdVO zSReturnUpd(ReqZsUpdVO reqFinalVo);
	/***
	 * 终审借款产品更新接口
	 */
	Response<Object> zSProductUpd(ReqBMProductUpdVo reqBMProductUpdVo);
	/***
	 * 批量改派更新接口
	 */
	Response<ResBMSPlReassignMentUpdVo> zSreassignmentUpd(ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo);
	/***
	 * 初审自动派单接口
	 */
	ResBMSAutomaticDispatchVO zSAutomaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO);
	
	/**
	 * 查询信审终审状态是办理和挂起的申请件
	 */
	Response<Integer> findLastByStatus(ReqZsUpdVO reqFinalVo);
	/**
	 * 查询改派接口
	 */
	public 	PageResponse<ResBMSReassignmentVo> zSlistPage(ReqBMSReassignmentVo reqBMSReassignmentVo);
	
    /**
     * 查询终审已完成任务列表
     * @param reqBMSAdultOffTheStocksVo
     * @return
     */
	public PageResponse<ResOffTheStocksAuditVO> zSAdultOffTheStocks(ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo); 
	/**
	 * 终审待办任务列表
	 */
	public PageResponse<ResBMSAuditVo>zSWaitForTheStocks(ReqAuditVo reqFirstAuditVo);
	/**
	 * 信审分派拒绝接口
	 */
	public Response<Object> xsSystemReject(ReqZsUpdVO reqFinalVo);
	
	/**
	 * 信审身份证变更校验接口
	 */
	public Response<Object>xSInfoChangeCheck(ReqAuditVo reqAuditVo);
	
	/**
	 * 查询信审最新一次快照信息
	 */
	Response<String> findXsSnapVersionInfo(ReqAuditVo reqAuditVo);
}
