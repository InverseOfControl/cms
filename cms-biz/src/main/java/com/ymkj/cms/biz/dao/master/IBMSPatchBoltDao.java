package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSPatchBolt;

public interface IBMSPatchBoltDao extends BaseDao<BMSPatchBolt>{
	List<BMSPatchBolt> listPage(Map<String,Object> map);
}
