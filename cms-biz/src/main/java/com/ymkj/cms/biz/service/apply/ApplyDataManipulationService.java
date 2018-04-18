package com.ymkj.cms.biz.service.apply;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.ApplyEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.AuditAmendEntryVO;
import com.ymkj.cms.biz.api.vo.request.apply.PersonHistoryLoanVO;
import com.ymkj.cms.biz.api.vo.request.apply.ReqApplyEntryVO;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.master.BMSProduct;

public interface ApplyDataManipulationService {
	
	public Response<ReqApplyEntryVO> save(ApplyEntryVO applyEntryVO);
	
	
	public Response<ReqApplyEntryVO> update(ApplyEntryVO applyEntryVO);
	
	
	public Response<ReqApplyEntryVO> auditUpdate(AuditAmendEntryVO applyEntryVO);
	
	
	public Response<ApplyEntryVO> queryApplyEntry(PersonHistoryLoanVO personHistoryLoanVO);
	
	
	public Response<ApplyEntryVO> queryApplyValueEntry(PersonHistoryLoanVO personHistoryLoanVO);
	
	
	public void auditDifferences(String loanNo,String version);


	/**
	 * 获取央行征信报告
	 * @param personHistoryLoanVO
	 * @return
	 */
	public String fixedCreditReport(PersonHistoryLoanVO personHistoryLoanVO);
	
	public void query(String name, String idName, String loanNo);
	
	//查询借款审批表
		public LoanAuditEntity findByAudit(PersonHistoryLoanVO personHistoryLoanVO);
		//查询当前申请人的申请单前一笔信息
		public List<LoanBaseEntity> findLoanNoByNotBelongTo(PersonHistoryLoanVO personHistoryLoanVO);
		//获取费率和负债率
		public BMSProduct findProductData(String productCd);
		
		//查询负载率表返回总负债和内部负债率
		public Map<String,Object> findRadioByApplyType(String applyType);
		
		/**
		 * 获取央行征信报告
		 * @param personHistoryLoanVO
		 * @return
		 */
		public String fixedCreditReportByStatus(PersonHistoryLoanVO personHistoryLoanVO);
		
		/**
		 * 结清在贷用户，已还期数是否大于12期
		 * @param name
		 * @param idNo
		 * @return
		 */
		public boolean ifReloanGreaterThan12MonthsUser(String name,String idNo);
}
