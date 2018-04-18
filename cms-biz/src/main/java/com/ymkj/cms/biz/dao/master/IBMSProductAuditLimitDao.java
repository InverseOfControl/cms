package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSProductAuditLimitVO;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface IBMSProductAuditLimitDao extends BaseDao<BMSProductAuditLimit>{
	
	//按auditLimitId查询BMSProductAuditLimit
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
	
	
	
	public List<BMSProductAuditLimit> findLimitByChaIdOrgId(Map<String, Object> paramMap) ;
	
	public Integer updateByProductId(ReqBMSProductAuditLimitVO productAuditLimitVO);
	
	/**
	 * 根据期限ID查询对应的网店产品下面该期限期限对应的信息
	 */
	public List<BMSOrgLimitChannel> findOutletByAuditLimitId(ReqBMSProductAuditLimitVO req);
	
	/**
	 * 更新网店产品对应的产品期限ID对应的信息
	 */
	public Boolean updateOrgLimitByAuditId(ReqBMSProductAuditLimitVO req);
	
	/**
	 * 根据ID查询对应网店产品信息
	 */
	public Boolean updateOrgLimitById(ReqBMSOrgLimitChannelVO channelVo);
}
