package com.ymkj.cms.biz.service.master.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSChannelVO;
import com.ymkj.cms.biz.dao.master.IBMSChannelDao;
import com.ymkj.cms.biz.entity.master.BMSChannel;
import com.ymkj.cms.biz.service.master.IBMSChannelService;
@Service
public class BMSChannelServiceImpl extends BaseServiceImpl<BMSChannel> implements IBMSChannelService{

	@Autowired
	private IBMSChannelDao bmsChannelDao;
	
	@Override
	protected BaseDao<BMSChannel> getDao() {
		return this.bmsChannelDao;
	}

	@Override
	public long insert(BMSChannel channle) {
		return this.bmsChannelDao.insert(channle);
	}

	@Override
	public void delete(BMSChannel channel) {
		this.bmsChannelDao.deleteById(Long.valueOf(channel.getId()));
	}

	@Override
	public void update(BMSChannel channle) {
		this.bmsChannelDao.update(channle);
		
	}

	@Override
	public List<BMSChannel> selectAllChannel(Map<String,Object> param) {
		return this.bmsChannelDao.listBy(param);
	}

	@Override
	public BMSChannel findOne(ReqBMSChannelVO reqChannelVO) {
		return bmsChannelDao.findOne(reqChannelVO);
	}

	@Override
	public List<BMSChannel> getChannelByOrgId(Map<String, Object> paramMap) {
		return bmsChannelDao.getChannelByOrgId(paramMap);
	}

	@Override
	public List<BMSChannel> getChannelByProTermLmt(Map<String, Object> paramMap) {
		return bmsChannelDao.getChannelByProTermLmt(paramMap);
	}

	@Override
	public List<BMSChannel> getChannelByOrgProAlt(Map<String, Object> paramMap) {

		return  bmsChannelDao.getChannelByOrgProAlt(paramMap);
	}

	@Override
	public int checkIsChennelExits(Map<String, Object> paramMap) {
		int result=bmsChannelDao.checkIsChennelExits(paramMap);
		return result;
	}

	@Override
	public List<BMSChannel> findBy(Map<String, Object> paramMap) {
		
		return bmsChannelDao.findBy(paramMap);
	}

	@Override
	public int disabledLimitChannel(Map<String, Object> paramMap) {
		return bmsChannelDao.disabledLimitChannel(paramMap);
	}

	@Override
	public int disabledOrgLimitChannel(Map<String, Object> paramMap) {
		return bmsChannelDao.disabledOrgLimitChannel(paramMap);
	}

	@Override
	public List<BMSChannel> findChannelEqDate(Map<String, Object> paramMap) {
		return bmsChannelDao.findChannelEqDate(paramMap);
	}

	@Override
	public int disabledChannelBank(Map<String, Object> paramMap) {
		return bmsChannelDao.disabledChannelBank(paramMap);
	}

	@Override
	public int isExistInBMS(String loanNo) {
		return bmsChannelDao.isExistInBMS(loanNo);
	}

}
