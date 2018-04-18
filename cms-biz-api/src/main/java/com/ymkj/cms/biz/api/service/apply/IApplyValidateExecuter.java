package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ApprovalOpinionsVO;
import com.ymkj.cms.biz.api.vo.request.apply.AuditRulesVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReasonVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqCreditCheckVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqValidateVo;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateNameIdNoVO;
import com.ymkj.cms.biz.api.vo.request.apply.ValidateRecordVO;
import com.ymkj.cms.biz.api.vo.response.apply.ResYBRReturnVO;

public interface IApplyValidateExecuter {
	
	/**
	 * 校验
	 * @param validateNameIdNoVO
	 * @return
	 */
	public Response<ReqValidateVo> validateNameIdNo(ValidateNameIdNoVO validateNameIdNoVO);
	
	/**
	 * 益博睿规则命中记录
	 * @param validateRecordVO
	 * @return
	 */
	public Response<String> saveValidateRecord(ValidateRecordVO validateRecordVO);
	
	/**
	 * 根据原因码获取一级二级原因
	 * @return
	 */
	public Response<ReasonVO> queryReason(ReasonVO reasonVO);
	
	/**
	 * 央行征信白户校验
	 * @param validateNameIdNoVO
	 * @return
	 */
	public Response<ReqCreditCheckVO> checkCreditUser(ValidateNameIdNoVO validateNameIdNoVO);
	
	/**
	 * 调用益博瑞接口返回信息
	 */
	public ApprovalOpinionsVO httpToTheLetter(PersonHistoryLoanVO personHistoryLoanVO);
	
	/**
	 * 审核系统对接益博瑞返回全部信息的接口
	 * hcr
	 */
	public Response<ResYBRReturnVO> queryApplyDataIsYBR(PersonHistoryLoanVO personHistoryLoanVO);
	
	/**
	 * 审核规则对接，更新返回数据入库
	 * @param auditRulesVO
	 * @return
	 */
	public Response<Object> auditUpdaetRulesData(AuditRulesVO auditRulesVO);
	
	/**
	 * 查询审核规则更新数据
	 * @param auditRulesVO
	 * @return
	 */
	public Response<AuditRulesVO> queryAuditRulesData(AuditRulesVO auditRulesVO);
}
