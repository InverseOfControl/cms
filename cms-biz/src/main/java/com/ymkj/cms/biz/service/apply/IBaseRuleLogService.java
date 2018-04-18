package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.BmsRuleLogEntity;

public interface IBaseRuleLogService extends BaseService<BmsRuleLogEntity>{
	
	public long insetBaseRuleLogList(List<BmsRuleLogEntity> listBmsRuleLog);

}
