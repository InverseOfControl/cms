package com.ymkj.cms.biz.dao.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;

/**
 * 初审接口DAO层
 * @author YM10143
 *
 */
public interface BMSFirstAuditDao extends BaseDao<BMSFirstAuditEntity>{
	
	/**
	 * 查询自动派单
	 * @return
	 */
	List<BMSAutomaticDispatchEntity> automaticDispatchList(Map<String,Object> map);
	
	
	/**
	 * 初审查询自动派单
	 * @return
	 */
	List<BMSAutomaticDispatchEntity> csAutomaticDispatchList(Map<String,Object> map);
	
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
	 * 更新借款扩展表
	 */
	Integer updLoanAdExt(Map<String,Object> map);
	
	/**
	 * 更新借款扩展表
	 */
	Integer updLoanAdExtReject(Map<String,Object> map);
	
	/**
	 * 删除黑名单
	 */
	Integer updLoanAdExtIsBlackNull(Map<String,Object> map);
	/**
	 * 通过客服编码查询复核信息
	 * @param map
	 * @return
	 */
	/*List<BMSCheckEntity> queryCheckInfoByCode(Map<String,Object> map);*/
//	public PageBean<BMSFirstAuditEntity> listPageFirstAudit(PageParam pageParam, Map<String, Object> paramMap, String sqlId) ;
	
	/**
	 * 更新申请主表
	 * @param map
	 * @return
	 */
	
	Integer plUpdLoanBase(Map<String,Object> map);
	
	/**
	 * 更新审批主表
	 * @param map
	 * @return
	 */
	Integer plUpdAuditNo(Map<String,Object> map);
	/**
	 * 更新借款信息产品表
	 * @return
	 */
	Integer updBmsLoanProduct(Map<String,Object> map);
	/**
	 * 检测是否是已完成订单
	 * @param map
	 * @return
	 */
	Integer checkIsHositoryLoanNo(Map<String,Object> map);
	
	
	List<BMSFirstAuditEntity> queryBmsLogByLoan(Map<String,Object> map);
	/**
	 * 验证流程节点状态是否在可办理的状态
	 * @param map
	 * @return
	 */
	int checkRtfNodeStatus(Map<String,Object> map);
	/**
	 * 通过借款单号查询协审人员code
	 * @param map
	 * @return
	 */
	String byLoanNoQueryXieShengCode(Map<String,Object> map);
	/**
	 * 通过登录人编码判断是否是协审
	 */
	int byPersonCodeQueryJuse(String serviceCode);
	
	List<String> byAppRovalPersonCodeQueryLoanNo(String serviceCode);
	
	Integer findTrialByStatus(String code);
	
	/**
	 * 查询当前登录人终审初次分派时间
	 * @param map
	 * @return
	 */
	List<BMSFirstAuditEntity>queryFirstOperationTime(Map<String,Object> map);
	/**
	 * 查询当前登录人初审首次分派时间
	 */
	List<BMSFirstAuditEntity>querycSFirstOperationTime(Map<String,Object> map);
	/**
	 * 更新申请主表状态version不加1
	 * @return
	 */
	Integer updLoanBaseTwo(Map<String,Object> map);
	
	/**
	 * 修改网购达人贷信息
	 * @param map
	 * @return
	 */
	int updateOnlineAWithinMonthsAddress(Map<String,Object> map);
	
	/**
	 * 修改保单贷信息
	 * @param map
	 * @return
	 */
	int updatePolicyCheckIsVerify(Map<String,Object> map);
	
	/**
	 * 修改车辆贷信息
	 * @param map
	 * @return
	 */
	int updateCarCheckIsVerify(Map<String,Object> map);
	
	/**
	 * 获取申请人多个手机号信息
	 * @param loanNo 借款编号
	 */
	Map<String,Object> getPersonPhone(String loanNo);
	
	/**
	 * 插入华征报告ID
	 * @param paramsMap
	 */
	int updateHZReportId(Map<String,Object> paramsMap);
	
	/**
	 * 华征报告绑定前置条件
	 * @param paramsMap
	 */
	Map<String,Object> hzReportIdPreCondition(String loanNo);
	
	/**
	 * 更新华征信息版本快照
	 * @param paramsMap
	 */
	int updateHZReportChangeInfo(Map<String,Object> paramsMap);
}
