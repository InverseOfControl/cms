package com.ymkj.cms.biz.api.service.audit;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqApplicationPartVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionTwoVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqQualityInspectionVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSApplicationPartVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAudiUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSQualityInspectionVO;

/**
 * 申请件接口-接口类
 * @author YM10161
 *
 */
public interface IQualityInspectionExecuter {
	/**
	 * 
	 * @param reqQualityInspectionVO
	 * @return
	 */
	ResBMSQualityInspectionVO queryQualityInsInfo(ReqQualityInspectionVO reqQualityInspectionVO);
	/**
	 * 
	 * @param reqQualityInspectionTwoVO
	 * @return
	 */
	ResBMSQualityInspectionVO queryQualityInsInfoTwo(ReqQualityInspectionTwoVO reqQualityInspectionTwoVO);
	/**
	 * 
	 * @param reqApplicationPartVO
	 * @return
	 */
	PageResponse<ResBMSApplicationPartVO> getApplicationInfo(ReqApplicationPartVO reqApplicationPartVO);
	
	/**
	 * 申请件维护更新接口
	 */
	ResBMSAudiUpdVo  updateApplicationInfo(ReqApplicationPartUpdVO reqApplicationPartUpdVO);
	
	
	/**
	 * 申请件维护更新接口（改造）
	 * @param reqApplicationPartUpVO
	 * @return
	 */
	Response<Object> updateApplicationInfoNew(ReqApplicationPartUpVO reqApplicationPartUpVO);
	
	/**
	 * 将字段更新成新生件
	 * @param reqApplicationPartUpVO
	 * @return
	 */
	Response<Object> updateIfNewLoanNo(PersonHistoryLoanVO personHistoryLoanVO);
} 
