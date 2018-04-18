package com.ymkj.cms.biz.service.app;

import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.app.BMSExtendsionFieldEntity;

public interface IInitFieldService extends BaseService<BMSExtendsionFieldEntity> {
	
	public Map<String,Object> initField(String appCurrentTime);
}
