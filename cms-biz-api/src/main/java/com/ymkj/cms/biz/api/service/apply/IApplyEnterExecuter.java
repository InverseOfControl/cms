package com.ymkj.cms.biz.api.service.apply;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.apply.*;
import com.ymkj.cms.biz.api.vo.response.apply.ResCheckNewDataVO;

/**
 * 申请录入 接口定义
 * @author wyj
 *
 */
public interface IApplyEnterExecuter {
 
	/**
	 * 校验
	 * @param validateNameIdNoVO
	 * @return
	 */
	public Response<Object> validateNameIdNo(ValidateNameIdNoVO validateNameIdNoVO);
	
	/**
	 * 校验是否重复入单
	 * @param validateNameIdNoVO
	 * @return
	 */
	public Response<Object> validateRepeatSingle(ValidateNameIdNoVO validateNameIdNoVO);
	
	/**
	 * 上个是否被拒
	 * @param validateNameIdNoVO
	 * @return
	 */
	public Response<Object> validateLastIfBeingRejected(ValidateNameIdNoVO validateNameIdNoVO);
	
	
	/**
	 * 是否在保护期
	 * @param validateNameIdNoVO
	 * @return
	 */
	public Response<Object> validateWhetherProtection(ValidateNameIdNoVO validateNameIdNoVO);
	
	/**
	 * 产品是否下架
	 * @param validateNameIdNoVO
	 * @return
	 */
	public Response<Object> productIfShelves(ValidateNameIdNoVO validateNameIdNoVO);
	

	/**
	 * 新增or修改
	 * @param applyEntryVO
	 * @return
	 */
	public Response<ReqApplyEntryVO> saveOrUpdate(ApplyEntryVO applyEntryVO);
	
	
	/**
	 * 查询接口详情接口
	 * @param personHistoryLoanVO
	 * @return
	 */
	public Response<ApplyEntryVO> queryApplyEntry(PersonHistoryLoanVO personHistoryLoanVO);
	
	/**
	 * 查询接口详情替换枚举
	 * @param personHistoryLoanVO
	 * @return
	 */
	public Response<ApplyEntryVO> queryApplyValueEntry(PersonHistoryLoanVO personHistoryLoanVO);
	
	/**
	 * 测试类
	 * @param personHistoryLoanVO
	 * @return
	 */
	public Response<Object> test(PersonHistoryLoanVO personHistoryLoanVO);
	
	/**
	 * 审核环节修改数据
	 * @param applyEntryVO
	 * @return
	 */
	public Response<ReqApplyEntryVO> auditUpdate(AuditAmendEntryVO applyEntryVO);
	
	/**
	 * 绑定征信报告
	 * @param personHistoryLoanVO
	 * @return
	 */
	public Response<Object> fixedCreditReport(PersonHistoryLoanVO personHistoryLoanVO);
	
	
	/**
	 * 审核标红查询接口
	 * @param reqAuditDifferencesVO
	 * @return
	 */
	public Response<AuditApplyEntryVO> queryAuditDifferences(ReqAuditDifferencesVO reqAuditDifferencesVO);
	
	/**
	 * 新增删除联系人接口
	 */
	public Response<Long> deleteApplyContactInfo(ReqAuditDifferencesVO reqAuditDifferencesVO);
	
	/**
	 * 检查必填模块校验
	 * @param applyEntryVO
	 * @return
	 */
	public Response<Object> checkProductCdMandatoryModel(AuditAmendEntryVO applyEntryVO);
	
	
	/**
	 * 返回老征审系统借款新系统 最新一笔贷款记录
	 */
	public Response<ResCheckNewDataVO> queryCheckNewData(PersonHistoryLoanVO personHistoryLoanVO);
	
}
