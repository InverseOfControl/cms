package com.ymkj.cms.biz.dao.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.dao.master.IBMSBankDao;
import com.ymkj.cms.biz.entity.apply.BMSChannelBank;
import com.ymkj.cms.biz.entity.master.BMSBank;

@Repository
public class BMSBankDaoImpl extends BaseDaoImpl<BMSBank> implements IBMSBankDao {

	@Override
	public BMSBank findOne(ReqBMSBankVO reqBankVO) {
		return super.getSqlSession().selectOne(super.getStatement("findOne"), reqBankVO);
	}

	@Override
	public List<BMSBank> getBankByChannelId(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("getBankByChannelId"), paramMap);
	}

	@Override
	public List<BMSChannelBank> findChannelBankById(ReqBMSBankVO reqBankVO) {
		Map<String,Long> map=new HashMap<String,Long>();
		map.put("id", reqBankVO.getId());
		return super.getSqlSession().selectList(super.getStatement("findChannelBankById"), map);
	}

	@Override
	public int accordingBankIdUpdateChannelBank(ReqBMSBankVO reqBankVO) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", reqBankVO.getId());
		map.put("isDisabled", reqBankVO.getIsDisabled());
		return super.getSqlSession().update(super.getStatement("accordingBankIdUpdateChannelBank"), map);
	}

}
