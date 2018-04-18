package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.APPWhitePersonnelEntity;


public interface APPWhitePersonnelService extends BaseService<APPWhitePersonnelEntity>{
	
	public int deleteVo(APPWhitePersonnelEntity appWhitePersonnelEntity);
	
	public boolean saveList(List<APPWhitePersonnelEntity> appWhitePersonnelEntityBeans);
}
