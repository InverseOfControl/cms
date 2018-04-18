package com.ymkj.cms.web.boss.service.audit.impl;

import org.springframework.stereotype.Service;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.audit.ReqAuditVo;
import com.ymkj.cms.biz.api.vo.response.audit.ResBMSAuditVo;
import com.ymkj.cms.web.boss.service.audit.IFirstAuditService;

/**
 * 初审接口service实现
 * @author YM10143
 *
 */
@Service
public class IFirstAuditServiceImpl implements IFirstAuditService{

	@Override
	public PageResult<ResBMSAuditVo> listPage(ReqAuditVo reqFirstAuditVo) {
		
		return null;
	}

}
