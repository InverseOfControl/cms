package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonInfoDao;
import com.ymkj.cms.biz.dao.master.IBMSTmAppContactInfoDao;
import com.ymkj.cms.biz.entity.master.BMSAppPersonInfo;
import com.ymkj.cms.biz.entity.master.BMSTmAppContactInfo;
import com.ymkj.cms.biz.service.master.IBMSAppPersonInfoService;
import com.ymkj.cms.biz.service.master.IBMSTmAppContactInfoService;

import java.util.List;
import java.util.Map;

@Service
public class BMSTmAppContactInfoServiceImpl extends BaseServiceImpl<BMSTmAppContactInfo> implements IBMSTmAppContactInfoService{
	@Autowired
	private IBMSTmAppContactInfoDao bmsTmAppContactInfoDao;
	@Override
	protected BaseDao<BMSTmAppContactInfo> getDao() {
		return bmsTmAppContactInfoDao;
	}

	/**
	 * 根据借款编号查询借款人的联系人列表
	 *
	 * @param param
	 * @return
	 */
	@Override
	public List<BMSTmAppContactInfo> listByLoanNo(Map<String,Object> param) {
		return bmsTmAppContactInfoDao.listByLoanNo(param);
	}
}
