package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.DemoEntity;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface IDemoService extends BaseService<DemoEntity>{
	
	public Long saveOrUpdate(DemoEntity demoEntity);
	
	public boolean saveDemos(List<DemoEntity> demoEntitys);
}
