package com.ymkj.cms.biz.service.finance.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSLoanFeeInfoVO;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBaseDao;
import com.ymkj.cms.biz.dao.finance.IBMSLoanFeeInfoDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBase;
import com.ymkj.cms.biz.entity.master.BMSLoanFeeInfo;
import com.ymkj.cms.biz.service.finance.ILoanFeeInfoService;

@Service
public class LoanFeeInfoServiceImpl extends BaseServiceImpl<BMSLoanFeeInfo> implements ILoanFeeInfoService{

	@Autowired
	private IBMSLoanFeeInfoDao iBMSLoanFeeInfoDao;
	
	@Override
	protected BaseDao<BMSLoanFeeInfo> getDao() {
		return iBMSLoanFeeInfoDao;
	}
	
	@Override
	public ReqBMSLoanFeeInfoVO insert(ReqBMSLoanFeeInfoVO vo) {
		BMSLoanFeeInfo info=new BMSLoanFeeInfo();
		BeanUtils.copyProperties(vo, info);
		iBMSLoanFeeInfoDao.insert(info);
		BeanUtils.copyProperties(info,vo);
		return vo;
	}

	

}
