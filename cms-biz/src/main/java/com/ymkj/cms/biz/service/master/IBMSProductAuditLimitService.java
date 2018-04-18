package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;
/**
 * 产品期限 服务层
 * @author cj
 *
 */
public interface IBMSProductAuditLimitService extends BaseService<BMSProductAuditLimit>{
	/**
	 * 新增
	 * @param productLimit
	 * @return
	 */
	public Long saveProductLimit(BMSProductAuditLimit productLimit);
	/**
	 * 修改 参数为 BMSProductAuditLimit
	 * @param productLimit
	 */
	public void updateProductLimit(BMSProductAuditLimit productLimit);
	/**
	 * 删除
	 * @param productLimit
	 */
	public void deleteProductLimit(BMSProductAuditLimit productLimit);
	
	/**
	 * 修改，传入参数为reqBmsProductAuditLimtVO
	 * @param reqLimit
	 */
	public void updateProductLimit(ReqBMSProductAuditLimitVO reqLimit);
	
	/**
	 * 按auditLimitId查询信息
	 * @param auditLimitId
	 * @return
	 */
	public BMSProductAuditLimit findByAuditLimitId(long auditLimitId);
	
	
	/**
	 * 按传入的实体信息查询信息
	 * @param paramMap
	 * @return
	 */
	public  BMSProductAuditLimit  findByVO(ReqBMSProductAuditLimitVO productAuditLimitVO);
	
	/**
	 * 根据条件查询审批期限（渠道ID、orgId（根据用户code获取orgId））
	 * @param paramMap
	 * @return
	 */
	public List<BMSProductAuditLimit> findLimitByChaIdUserCode(Map<String, Object> paramMap);
	
	
	/**
	 * 根据条件查询审批期限（渠道ID、orgId）
	 * @param paramMap
	 * @return
	 */
	public List<BMSProductAuditLimit> findLimitByChaIdOrgId(Map<String, Object> paramMap);
	
	/**
	 * 修改，传入参数为reqBmsProductAuditLimtVO
	 * @param reqLimit
	 */
	public void updateByProductId(ReqBMSProductAuditLimitVO reqLimit);
	/**
	 * 修改状态
	 */
	public long deleteProductTerm(ReqBMSProductAuditLimitVO reqLimit);
	
	/**
	 * 根据期限ID查询对应网店产品下面对应的该期限的信息
	 */
	public List<BMSOrgLimitChannel> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req);
	
	/**
	 * 更新网店产品对应的产品期限ID对应的信息
	 */
	public Boolean updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req);
	
	/**
	 * 更具ID更新网店产品 信息
	 */
	public Boolean updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo);
}
