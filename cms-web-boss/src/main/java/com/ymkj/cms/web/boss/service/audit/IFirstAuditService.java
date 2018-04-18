package com.ymkj.cms.web.boss.service.audit;

import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;

/**
 * 初审接口消费端service
 * @author YM10143
 *
 */
public interface IFirstAuditService {
	/**
	 * 分页查询
	 */
	public PageResult<ResBMSAuditVo> listPage(ReqAuditVo reqFirstAuditVo);
		
}
