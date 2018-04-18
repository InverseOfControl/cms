package com.ymkj.cms.biz.api.service.audit;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSAdultOffTheStocksVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqChcekVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsPlupdateStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAutomaticDispatchVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSCheckVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSPlReassignMentUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResOffTheStocksAuditVO;

/**
 * 初审接口-接口类
 * @author YM10143
 *
 */
public interface IFirstAdultExecuter {
	/**
	 * 分页数据查询
	 */
	public PageResponse<ResBMSAuditVo> listPage(ReqAuditVo reqFirstAuditVo);
	
	/**
	 * 查询自动派单
	 * @return
	 */
	ResBMSAutomaticDispatchVO automaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO);
	
	/**
	 * 初审查询自动派单
	 * @return
	 */
	ResBMSAutomaticDispatchVO csAutomaticDispatchList(ReqAutomaticDispatchVO reqAutomaticDispatchVO);
	
	/**
	 * 初审更新接口(针对办理中的单子提交、挂起操作)
	 */
	ResBMSAudiUpdVo  updateCsLoanNoState(ReqCsUpdVO reqCsUpdVO);
	
	/**
	 * 终审更新接口
	 */
	ResBMSAudiUpdVo  updateZsLoanNoState(ReqZsUpdVO reqZsUpdVO);


	
	
	/**
	 * 复核确认接口
	 */
	PageResponse<ResBMSCheckVO> queryCheckInfoByCode(ReqChcekVO reqChcekVO);
	
	/**
	 * 初，终改版接口更新
	 * @param reqBMSReassignmentUpdVo
	 * @return
	 */
	Response<ResBMSPlReassignMentUpdVo> reassignmentUpd(ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo);
	/**
	 * 初、终审申请产品更新
	 */
	Response<Object> xsProductUpd(ReqBMProductUpdVo reqBMProductUpdVo);
	/**
	 * 初、终审已完成任务查询接口
	 */
	PageResponse<ResOffTheStocksAuditVO> adultOffTheStocks(ReqBMSAdultOffTheStocksVo ReqBMSAdultOffTheStocksVo);
	/**
	 * 初审批量更新流程节点状态
	 * @return
	 */
	Response<ResBMSPlReassignMentUpdVo> reassignmentPlUpdStatus(ReqCsPlupdateStatusVo reqCsPlupdateStatusVo);
	
}
