package com.ymkj.cms.biz.service.apply;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.AppContactHeadEntity;

public interface APPContactHeadService  extends BaseService<AppContactHeadEntity>{
	
	
	public Long saveOrUpdate(AppContactHeadEntity appContactHeadEntity);

}
