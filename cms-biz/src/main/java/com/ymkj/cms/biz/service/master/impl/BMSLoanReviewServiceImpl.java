package com.ymkj.cms.biz.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.master.IBMSAppPersonDao;
import com.ymkj.cms.biz.dao.master.IBMSLoanReviewDao;
import com.ymkj.cms.biz.entity.master.BMSAppPerson;
import com.ymkj.cms.biz.entity.master.BMSLoanReview;
import com.ymkj.cms.biz.service.master.IBMSAppPersonService;
import com.ymkj.cms.biz.service.master.IBMSLoanReviewService;
@Service
public class BMSLoanReviewServiceImpl extends BaseServiceImpl<BMSLoanReview> implements IBMSLoanReviewService{

	@Autowired
	private IBMSLoanReviewDao bmsLoanReviewDao;
	@Override
	protected BaseDao<BMSLoanReview> getDao() {
		return bmsLoanReviewDao;
	}
}
