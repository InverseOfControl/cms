package com.ymkj.cms.biz.dao.app;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.app.input.AppApplyInfoEntity;

public interface IInitProductInfoDao extends BaseDao<AppApplyInfoEntity> {

	public List<Map<String, Object>> getRefuseReasonList();
	
}
