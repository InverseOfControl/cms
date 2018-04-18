package com.ymkj.cms.biz.service.audit;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentBatchVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsPlupdateStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsRefusePlupdateStatusVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdStatusVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqCsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.request.audit.ReqcsBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResReassignmentUpdVO;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;

/**
 * 初审接口service类
 * @author YM10143
 *
 */
public interface BMSFirstAuditService extends BaseService<BMSFirstAuditEntity>{
	
//	public PageBean<BMSFirstAuditEntity> listPageFirstAudit(PageParam pageParam, Map<String, Object> paramMap);
	
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
	 * 更新借款单子初审环节信息
	 * @throws Exception 
	 */
	boolean updateCsLoanNoState(ReqCsUpdVO reqCsUpdVO);
	
	
	/**
	 * 更新借款单子终审环节信息
	 * @param reqZsUpdVO
	 * @return
	 */
	boolean updateZsLoanNoState(ReqZsUpdVO reqZsUpdVO);
	
	
	/**
	 * 通过客服编码查询复核信息
	 * @param map
	 * @return
	 */
	/*List<BMSCheckEntity> queryCheckInfoByCode(Map<String,Object> map);*/
	
	/**
	 * 更新审批表,主表信息(改派)
	 */
	Map<String,Object> updateReassignment(ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo);
	
	
	/**
	 * 初审批量派单
	 */
	List<ResReassignmentUpdVO> updateReassignmentNew(ReqBMSReassignmentBatchVo reqBMSReassignmentUpdVo);
	
	/**
	 * 信审更新产品信息
	 */
	boolean updateProductInfo(ReqBMProductUpdVo reqBMProductUpdVo);
	
	/**
	 * 初审更新产品信息
	 */
	boolean updateCSProductInfo(ReqcsBMProductUpdVo reqBMProductUpdVo);
	
	
	/**
	 * 检测是否是已完成订单
	 * @param map
	 * @return
	 */
	boolean checkIsHositoryLoanNo(Map<String,Object> map);
	/**
	 * 批量更新初审拒绝或退回
	 * @param reqCsPlupdateStatusVo
	 * @return
	 */
	Map<String,Object> reassignmentPlUpdStatus(ReqCsPlupdateStatusVo reqCsPlupdateStatusVo,ReqCsUpdStatusVo reqCsUpdStatusVo);
	
	
	/**
	 * 批量操作方法
	 * @param reqCsRefusePlupdateStatusVO
	 * @return
	 */
	public List<ResReassignmentUpdVO> reassignmentPlUpdStatusNew(ReqCsRefusePlupdateStatusVO reqCsRefusePlupdateStatusVO);
	
	
	
	/**
	 * 终审批量操作方法
	 * @param reqCsRefusePlupdateStatusVO
	 * @return
	 */
	public List<ResReassignmentUpdVO> ultimatePlUpdStatusNew(ReqCsRefusePlupdateStatusVO reqCsRefusePlupdateStatusVO);
	
	
	List<BMSFirstAuditEntity> queryBmsLogByLoan(Map<String,Object> map);
	/**
	 * 验证流程节点状态是否在可办理的状态
	 * @param map
	 * @return
	 */
	boolean checkRtfNodeStatus(Map<String,Object> map);
	
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
	
	
	/**
	 * 根据初审CODE查询对应的申请件状态是办理和挂起的
	 */
	Integer findTrialByStatus(String code);
	/**
	 * 终审首次分派时间
	 * @param map
	 * @return
	 */
	public List<BMSFirstAuditEntity> queryFirstOperationTime(Map<String, Object> map);
	
	/**
	 * 初审首次分派时间
	 * @param map
	 * @return
	 */
	public List<BMSFirstAuditEntity> querycSFirstOperationTime( Map<String, Object> map);
	
	/**
	 * <p>Description:华征报告信息(学历、手机在网时长、实名认证)</p>
	 * @uthor YM10159
	 * @date 2017年8月22日 下午15:21:58
	 * @param paramsMap 入参
	 */
	public boolean getHZReportInfo(ReqCsUpdVO reqCsUpdVO);
}
