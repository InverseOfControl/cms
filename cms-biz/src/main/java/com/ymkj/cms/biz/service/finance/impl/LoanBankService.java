package com.ymkj.cms.biz.service.finance.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.common.util.StringUtils;
import com.ymkj.cms.biz.dao.finance.IBMSLoanBankDao;
import com.ymkj.cms.biz.entity.finance.BMSLoanBank;
import com.ymkj.cms.biz.service.finance.ILoanBankService;

@Service
public class LoanBankService extends BaseServiceImpl<BMSLoanBank> implements  ILoanBankService {
	
	@Autowired
	private IBMSLoanBankDao bankDao;
	
	/**
	 * 接口中新增或更新银行操作
	 * @param loanBank
	 * @return
	 */
	@Override
	public BMSLoanBank saveOrUpdate(BMSLoanBank loanBank) {
		Long id = loanBank.getId();
		
		if (StringUtils.isEmpty(String.valueOf(id))) {
			/**新增银行信息**/
			bankDao.insert(loanBank);
		} else {
			/**更新银行信息**/
			bankDao.update(loanBank);
		}
		return loanBank;
	}

	@Override
	protected BaseDao<BMSLoanBank> getDao() {
	
		return bankDao;
	}

}
