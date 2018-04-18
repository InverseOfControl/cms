package com.ymkj.cms.biz.service.audit.last;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMProductUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqBMSReassignmentUpdVo;
import com.ymkj.cms.biz.api.vo.request.audit.ReqZsUpdVO;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.biz.entity.audit.BMSAutomaticDispatchEntity;
import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;

/**
 * 终审接口类
 * @author YM10172
 *
 */
public interface IBMSFinalAuditService extends BaseService<BMSFirstAuditEntity> {
	/**
	 * 更新借款单子终审环节信息
	 * @param reqZsUpdVO
	 * @return
	 */
	boolean updateZsLoanNoState(ReqZsUpdVO reqZsUpdVO);
	/**
	 * 验证流程节点状态是否在可办理的状态(终审)
	 * @param map
	 * @return
	 */
	boolean checkFinalRtfNodeStatus(Map<String,Object> map);
	/**
	 * 终审借款产品更新接口
	 * @param reqBMProductUpdVo
	 * @return
	 */
	public boolean updateZSProductInfo(ReqBMProductUpdVo reqBMProductUpdVo);
	/**
	 * 查询自动派单
	 * @return
	 */
	List<BMSAutomaticDispatchEntity> zSautomaticDispatchList(Map<String,Object> map);
	/**
	 * 终审批量改派接口
	 * @param reqBMSReassignmentUpdVo
	 * @return
	 */
	public Map<String, Object> updateZsReassignment(ReqBMSReassignmentUpdVo reqBMSReassignmentUpdVo);
	/**
	 * 查询信审终审状态是办理和挂起的申请件
	 */
	Integer findLastByStatus(String code);
	/**
	 * 查询所有的已完成的单子
	 */
	public List<BMSFirstAuditEntity>queryzSBmsLogByLoan(Map<String,Object> map);
	
	public boolean updateXsSystemReject(ReqZsUpdVO reqZsUpdVO);
	
	public BMSFirstAuditEntity findLogByReturn(Map<String, Object> map);
	
	public BMSFirstAuditEntity findXsSnapVersionInfo(Map<String, Object> map);
	
}
