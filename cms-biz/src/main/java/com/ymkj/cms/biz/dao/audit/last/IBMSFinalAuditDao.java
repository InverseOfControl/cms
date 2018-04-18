package com.ymkj.cms.biz.dao.audit.last;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;

public interface IBMSFinalAuditDao  extends BaseDao<BMSFirstAuditEntity> {
	
	/**
	 * 验证流程节点状态是否可用
	 * @param map
	 * @return
	 */
	int checkFinalRtfNodeStatus(Map<String,Object> map);
	/**
	 * 查询自动派单
	 * @return
	 */
	List<BMSAutomaticDispatchEntity> zSautomaticDispatchList(Map<String,Object> map);
	/**
	 * 更新审批表状态
	 * @return
	 */
	Integer updAuditNo(Map<String,Object> map);
	/**
	 * 更新申请主表状态
	 * @return
	 */
	Integer updLoanBase(Map<String,Object> map);
	/**
	 * 更新借款信息产品表
	 * @return
	 */
	Integer updBmsLoanProduct(Map<String,Object> map);
	/**
	 * 查询loan_base_id
	 */
	Long findByLoanNo(String loanNo);
	
	Integer findLastByStatus(String code);
	
	List<BMSFirstAuditEntity> queryzSBmsLogByLoan(Map<String, Object> map);
	
	/**
	 * 更新申请主表状态version不加1
	 * @return
	 */
	Integer updLoanBaseTwo(Map<String,Object> map);
	
	/**
	 * 退回时查询操作该单子的初审人员
	 * @param map
	 * @return
	 */
	public BMSFirstAuditEntity findByCheckCode(Map<String,Object> map);
	
	public BMSFirstAuditEntity findLogByReturn(Map<String,Object> map);
	
	public BMSFirstAuditEntity findXsSnapVersionInfo(Map<String, Object> map);

}
