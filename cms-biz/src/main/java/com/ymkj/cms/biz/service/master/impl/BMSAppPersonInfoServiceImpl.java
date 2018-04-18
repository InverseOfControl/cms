package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;

@Service
public class BMSAppPersonInfoServiceImpl extends BaseServiceImpl<BMSAppPersonInfo> implements IBMSAppPersonInfoService{

	@Autowired
	private IBMSAppPersonInfoDao bmsAppPersonInfoDao;
	@Override
	protected BaseDao<BMSAppPersonInfo> getDao() {
		return bmsAppPersonInfoDao;
	}
	@Override
	public BMSAppPersonInfo byLoanNoQueryInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bmsAppPersonInfoDao.byLoanNoQueryInfo(map);
	}
	@Override
	public boolean updateByLoanNo(Map<String, Object> map) {

		Integer result= bmsAppPersonInfoDao.updateByLoanNo(map);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<Map<String, Object>> queryAdditionRecords(Map<String, String> paramMap) {
		return bmsAppPersonInfoDao.queryAdditionRecords(paramMap);
	}
	
	@Override
	public Integer updatePhoneCellStatus(Map<String, Object> map) {
		return bmsAppPersonInfoDao.updatePhoneCellStatus(map);
	}
	@Override
	public List<Map<String, Object>> qyeryPhoneCellStatus(Map<String, Object> map) {
		return bmsAppPersonInfoDao.qyeryPhoneCellStatus(map);
	}
	
	
}
