package com.ymkj.cms.biz.service.master.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.dao.master.IBMSLimitChannelDao;
import com.ymkj.cms.biz.dao.master.IBMSOrgLimitChannelDao;
import com.ymkj.cms.biz.entity.master.BMSLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.service.master.IBMSLimitChannelService;

@Service
public class BMSLimitChannelServiceImpl extends BaseServiceImpl<BMSLimitChannel> implements IBMSLimitChannelService{
	@Autowired
	private IBMSLimitChannelDao limitChannelDao;
	@Autowired
	private IBMSOrgLimitChannelDao orgLimitChannelDao;
	@Override
	protected BaseDao<BMSLimitChannel> getDao() {
		
		return limitChannelDao;
	}
	@Override
	public Boolean saveLimitChannel(ReqBMSOrgLimitChannelVO reqLimitChannel) {
		Boolean flag = false;
		String[] limitChannelIds = reqLimitChannel.getChannelLimitIds().split(",");
		//先删除后插入 这里的删除改为逻辑删除
		//删除
		BMSLimitChannel limitDel = new BMSLimitChannel();
		limitDel.setChannelId(reqLimitChannel.getChannelId());
		limitChannelDao.updateByCondition(limitDel);
		
		
		//构造渠道产品期限信息保存
		BMSLimitChannel limitSave = new BMSLimitChannel();
		limitSave.setIsDeleted(0L);
		limitSave.setChannelId(reqLimitChannel.getChannelId());
		//数据还原更新，视作重新创建
		limitSave.setCreatorDate(new Date());
		limitSave.setCreator(reqLimitChannel.getServiceCode());
		for(String id : limitChannelIds){
			//空判断
			if(id == null || id.length() == 0){
				continue;
			}
			//		默认对应的，渠道id，产品期限，只能限制到一条数据
			Map<String, Object> historyParamMap = new HashMap<String, Object>();
			historyParamMap.put("channelId", reqLimitChannel.getChannelId());
			historyParamMap.put("auditLimitId", Long.parseLong(id));
			List<BMSLimitChannel> limitChannelList = limitChannelDao.findHistory(historyParamMap);
			if(limitChannelList != null && !limitChannelList.isEmpty()){//存在历史数据，进行还原
				limitSave.setId(limitChannelList.get(0).getId());
				limitSave.setAuditLimitId(Long.parseLong(id));
				limitChannelDao.update(limitSave);
			} else{//不存在历史数据，进行新增
				limitSave.setAuditLimitId(Long.parseLong(id));
				limitChannelDao.insert(limitSave);
			}
			
		}
		flag = true;
		return flag;
	}
	@Override
	public Boolean updateByCondition(ReqBMSOrgLimitChannelVO reqlimitChannel) {
		Boolean flag = false;
		BMSLimitChannel limitDel = new BMSLimitChannel();
		limitDel.setId(reqlimitChannel.getId());
		//0,启用，1禁用
		limitDel.setIsDisabled(reqlimitChannel.getIsDisabled());
		limitChannelDao.update(limitDel);
		flag = true;
		return flag;
	}
	
	@Override
	public long logicalDelete(BMSLimitChannel limitChannel) {
		return limitChannelDao.logicalDelete(limitChannel);
	}
	@Override
	public BMSLimitChannel getFULimit(BMSLimitChannel limitChannel) {

		return limitChannelDao.getFULimit(limitChannel);
	}
	@Override
	public Boolean updateOrgLimitChannelAllByCondition(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		 BMSLimitChannel limitChannel= limitChannelDao.getById(reqBMSOrgLimitChannelVO.getId());
		if(limitChannel != null){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("channelId", limitChannel.getChannelId());//渠道id
			paramMap.put("auditLimitId", limitChannel.getAuditLimitId());//产品期限id
			paramMap.put("isDisabled", reqBMSOrgLimitChannelVO.getIsDisabled());//启用禁用
			
			return orgLimitChannelDao.updateOrgLimitChannelAllByCondition(paramMap);
			
		}
		return false;
	}
	@Override
	public BMSLimitChannel getFULimitByOrgId(BMSLimitChannel limitChannel) {
		
		return limitChannelDao.getFULimitByOrgId(limitChannel);
	}
	@Override
	public long updateByAuLimitId(BMSLimitChannel limitChannel) {
		
		return limitChannelDao.updateByAuLimitId(limitChannel);
	}
	@Override
	public List<BMSLimitChannel> listByRate(Map<String, Object> paramMap) {
		
		return limitChannelDao.listByRate(paramMap);
	}
}
