package com.ymkj.cms.biz.dao.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.dao.master.IBMSChannelDao;
import com.ymkj.cms.biz.entity.master.BMSChannel;

@Repository
public class BMSChannelDaoImpl extends BaseDaoImpl<BMSChannel> implements IBMSChannelDao {

	@Override
	public BMSChannel findOne(ReqBMSChannelVO reqChannelVO) {
		return super.getSqlSession().selectOne(super.getStatement("findOne"), reqChannelVO);
	}

	@Override
	public List<BMSChannel> getChannelByOrgId(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("getChannelByOrgId"), paramMap);
	}

	@Override
	public List<BMSChannel> getChannelByProTermLmt(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("getChannelByProTermLmt"), paramMap);
	}

	@Override
	public List<BMSChannel> getChannelByOrgProAlt(Map<String, Object> paramMap) {
	
	return super.getSqlSession().selectList(super.getStatement("getChannelByOrgProAlt"), paramMap);
	}

	@Override
	public int checkIsChennelExits(Map<String, Object> paramMap) {
		
		return getSqlSession().selectOne(getStatement("checkIsChennelExits"), paramMap);
	}

	@Override
	public List<BMSChannel> findBy(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("findBy"), paramMap);

	}

	@Override
	public int disabledLimitChannel(Map<String, Object> paramMap) {
		return super.getSqlSession().update(super.getStatement("disabledLimitChannel"), paramMap);
	}

	@Override
	public int disabledOrgLimitChannel(Map<String, Object> paramMap) {
		return super.getSqlSession().update(super.getStatement("disabledOrgLimitChannel"), paramMap);
	}

	@Override
	public List<BMSChannel> findChannelEqDate(Map<String, Object> paramMap) {
		return super.getSqlSession().selectList(super.getStatement("findChannelEqDate"), paramMap);
	}

	@Override
	public int disabledChannelBank(Map<String, Object> paramMap) {
		return super.getSqlSession().update(super.getStatement("disabledChannelBank"), paramMap);
	}

	@Override
	public int isExistInBMS(String loanNo) {
		return super.getSqlSession().selectOne(super.getStatement("isExistInBMS"), loanNo);
	}	
}
