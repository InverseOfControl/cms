package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;
import com.ymkj.cms.biz.entity.master.BMSBank;

public interface IBMSBankDao extends BaseDao<BMSBank>{

	BMSBank findOne(ReqBMSBankVO reqBankVO);
	/**
	 * 获取银行根据渠道ID
	 * @param paramMap
	 * @return
	 */
	public List<BMSBank> getBankByChannelId( Map<String, Object> paramMap);
	
	/**
	 * 根据银行ID查询该银行是否签约
	 */
	public List<BMSChannelBank> findChannelBankById(ReqBMSBankVO reqBankVO);
	
	public int accordingBankIdUpdateChannelBank(ReqBMSBankVO reqBankVO);
}
