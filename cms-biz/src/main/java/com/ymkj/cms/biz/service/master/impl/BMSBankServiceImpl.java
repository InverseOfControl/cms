package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.dao.master.IBMSBankDao;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;
import com.ymkj.cms.biz.entity.master.BMSBank;
import com.ymkj.cms.biz.service.master.IBMSBankService;

@Service
public class BMSBankServiceImpl extends BaseServiceImpl<BMSBank> implements IBMSBankService {

	@Autowired
	private IBMSBankDao bmsBankDao;
	public static final String CHECK_BANK_IS_EXITS_SQL="checkBankIsExits";
	@Override
	protected BaseDao<BMSBank> getDao() {
		return bmsBankDao;
	}

	@Override
	public long insert(BMSBank bank) {
		return this.bmsBankDao.insert(bank);
	}

	@Override
	public void delete(BMSBank bank) {
		this.bmsBankDao.deleteById(Long.valueOf(bank.getId()));
	}

	@Override
	public void update(BMSBank bank) {
		this.bmsBankDao.update(bank);
	}

	@Override
	public List<BMSBank> selectAllBank(Map<String, Object> param) {
		return this.bmsBankDao.listBy(param);
	}

	@Override
	public BMSBank findOne(ReqBMSBankVO reqBankVO) {
		return bmsBankDao.findOne(reqBankVO);
	}

	@Override
	public List<BMSBank> getBankByChannelId(Map<String, Object> paramMap) {
		return bmsBankDao.getBankByChannelId(paramMap);
	}

	@Override
	public boolean checkBankIsExits(Map<String, Object> paramMap) {
		boolean flag=true;
		Integer result=(Integer) bmsBankDao.getBy(paramMap, CHECK_BANK_IS_EXITS_SQL);
		if(result>0){
			return flag;
		}else{
			flag=false;
		}
		return flag;
	}

	@Override
	public Boolean findChannelBankById(ReqBMSBankVO reqBankVO) {
		List<BMSChannelBank> banks=bmsBankDao.findChannelBankById(reqBankVO);
		if(banks!=null&&banks.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void accordingBankIdUpdateChannelBank(ReqBMSBankVO reqBankVO) {
		bmsBankDao.accordingBankIdUpdateChannelBank(reqBankVO);
		
	}
}
