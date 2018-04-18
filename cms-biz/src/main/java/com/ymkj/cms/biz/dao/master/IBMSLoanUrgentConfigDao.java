package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.entity.master.BMSLoanUrgentConfig;

public interface IBMSLoanUrgentConfigDao extends BaseDao<BMSLoanUrgentConfig>{

	/**
	 * 根据营业部IDS查询对应的加急限制表里的加急信息
	 * @param paramMap
	 * @return
	 */
	public List<BMSLoanUrgentConfig> selectAllBmsLoanUrgentConfigs(Map<String, Object> map);
	
	/**
	 * 根据营业部ID查询该营业部在给定的期限日期里面的加急申请件
	 */
	public Integer getLongBaseCountById(Map<String, Object> map);
}
