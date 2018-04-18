package com.ymkj.cms.biz.api.service.audit.first;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksNewVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentBatchVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefusePlupdateStatusVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqcsBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSReassignmentVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;


/**
 * 初审对外提供的服务接口
 * @author YM10161
 *
 */
public interface ISplitContractFirstAuditExecuter {
	/**
	 * 初审自动派单接口(拆分)
	 * @param request
	 * @return
	 */
	Response<ResBMSAutomaticDispatchVO> csAutomaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO);
	/**
	 * 初审拒绝更新接口(拆分)
	 * @return
	 */
	Response<ResBMSAudiUpdVo> cSRejectUpd(ReqCsUpdVO request);
	/**
	 * 初审提交（终审，高审）更新接口(拆分)
	 * @return
	 */
	Response<ResBMSAudiUpdVo> cSSubmitUpd(ReqCsUpdVO request);
	
	/**
	 * 初审挂起更新接口(拆分)
	 * @return
	 */
	Response<ResBMSAudiUpdVo> cSHangupUpd(ReqCsUpdVO request);
	/**
	 * 初审退回更新接口(拆分)
	 * @return
	 */
	Response<ResBMSAudiUpdVo> cSReturnUpd(ReqCsUpdVO request);
	
	/**
	 * 初审申请产品更新(拆分)
	 */
	Response<Object> csProductUpd(ReqcsBMProductUpdVo reqcsBMProductUpdVo);
	
	
	/**
	 * 初审复核确认(同意)(不同意)(拆分)
	 * @param request
	 * @return
	 */
	Response<ResBMSAudiUpdVo> cSConfirmationReviewAgreeOrDisagreeUpd(ReqCsUpdVO request);
	
	
	/**
	 * 批量初审改派更新接口(拆分)
	 * @param reqBMSReassignmentUpdVo
	 * @return
	 */
	Response<List<ResReassignmentUpdVO>> plCsReassignmentUpd(ReqBMSReassignmentBatchVo reqBMSReassignmentUpdVo);
	
	/**
	 * 初审批量拒绝更新接口(拆分)
	 * @param reqCsPlupdateStatusVo
	 * @return
	 */
	Response<List<ResReassignmentUpdVO>> refusePlRejectUpdStatus(ReqCsRefusePlupdateStatusVO reqCsPlupdateStatusVo);
	
	/**
	 * 初审批量退回更新接口(拆分)
	 * @param reqCsPlupdateStatusVo
	 * @return
	 */
	Response<List<ResReassignmentUpdVO>> reassignmentPlReturnUpdStatus(ReqCsRefusePlupdateStatusVO reqCsRefusePlupdateStatusVO);
	
	
	/**
	 * 查询信审初审办理，挂起的数量
	 */
	Response<Integer> findTrialByStatus(ReqCsUpdVO request);
	
	/**
	 * 初审查询改派接口 (拆分)
	 */
	PageResponse<ResBMSReassignmentVo>  firstTrialReassignmentQuery(ReqBMSReassignmentVo reqBMSReassignmentVo);
	
	
	/**
	 * 初审完成列表
	 * @param ReqBMSAdultOffTheStocksVo
	 * @return
	 */
	PageResponse<ResOffTheStocksAuditVO> cSAduditOffTheStocks(ReqBMSAdultOffTheStocksVo reqBMSAdultOffTheStocksVo);
	/**
	 * 初审待办任务查询
	 */
	PageResponse<ResBMSAuditVo> cSWaitForTheStocks(ReqAuditVo reqFirstAuditVo);
	
	/**
	 * 查询华征报告ID
	 * @param request
	 */
	Response<Object> getHZReportIDInfo(ReqCsUpdVO reqCsUpdVO);
	
	/**
	 * 信审同步华征报告ID给借款
	 * @param reqCsUpdVO
	 */
	Response<Object> syncHZReportID(ReqCsUpdVO reqCsUpdVO);
}
