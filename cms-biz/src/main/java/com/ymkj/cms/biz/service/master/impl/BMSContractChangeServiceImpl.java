package com.ymkj.cms.biz.service.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSContactChangeDao;
import com.ymkj.cms.biz.entity.master.BMSContractChange;
import com.ymkj.cms.biz.service.master.IBMSContractChangeService;

@Service
public class BMSContractChangeServiceImpl extends BaseServiceImpl<BMSContractChange> implements IBMSContractChangeService {
	
	@Autowired
	private IBMSContactChangeDao contractChangeDao;
	
	@Override
	protected BaseDao<BMSContractChange> getDao() {
		return contractChangeDao;
	}

	@Override
	public BMSContractChange getProductIsDisabled(Map<String,Object> params) {
		return contractChangeDao.getProductIsDisabled(params);
	}

	@Override
	public Integer updateLoanBase(Map<String, Object> map) {
		return contractChangeDao.updateLoanBase(map);
	}

	@Override
	public Integer getLoanBaseVersion(Integer id) {
		return contractChangeDao.getLoanBaseVersion(id);
	}

	@Override
	public Integer insertContractChangeFrequency(List<String> customerServiceList, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		
		//查询Frequency_base
		Integer frequencyBase = contractChangeDao.selectContractChangeFrequencyBase(map);
		if(frequencyBase == null) {
			frequencyBase = 0;
		}
		int result = 0;
		map.put("frequencyBase", frequencyBase);
		for (String usercode : customerServiceList) {
			map.put("usercode", usercode);
			result += contractChangeDao.insertContractChangeFrequency(map);
		}
		return result;
	}

	@Override
	public String getUserCodeFrequencyLow(Map<String, Object> map) {
		return contractChangeDao.getUserCodeFrequencyLow(map);
	}

	@Override
	public int deleteContractChangeFrequency(List<String> customerServiceList, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("customerServiceList", customerServiceList);
		
		//剔除已删除人员
		Integer deleteNum = contractChangeDao.deleteContractChangeFrequency(map);
		
		return deleteNum;
	}

	@Override
	public Integer increaseContractChangeFrequency(Map<String, Object> map) {
		return contractChangeDao.increaseContractChangeFrequency(map);
	}
	
}
