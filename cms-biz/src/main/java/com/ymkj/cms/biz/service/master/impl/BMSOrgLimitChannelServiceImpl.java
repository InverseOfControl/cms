package com.ymkj.cms.biz.service.master.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSOrgLimitChannelVO;
import com.ymkj.cms.biz.dao.master.IBMSOrgLimitChannelDao;
import com.ymkj.cms.biz.dao.master.IBMSProductAuditLimitDao;
import com.ymkj.cms.biz.entity.master.BMSOrgLimitChannel;
import com.ymkj.cms.biz.entity.master.BMSProductAuditLimit;
import com.ymkj.cms.biz.exception.BizException;
import com.ymkj.cms.biz.service.master.IBMSOrgLimitChannelService;

@Service
public class BMSOrgLimitChannelServiceImpl extends BaseServiceImpl<BMSOrgLimitChannel> implements IBMSOrgLimitChannelService{
	@Autowired
	private IBMSOrgLimitChannelDao orgLimitChannelDao;
	@Autowired
	private  IBMSProductAuditLimitDao auditLimitDao;
	@Override
	protected BaseDao<BMSOrgLimitChannel> getDao() {
		
		return orgLimitChannelDao;
	}
	@Override
	public Boolean saveOrgLimitChannel(ReqBMSOrgLimitChannelVO reqLimitChannel) {
		Boolean flag = false;
		//逻辑删除
		deleteHisData(reqLimitChannel);
		//构建产品渠道关系数据
		List<Map<String,Object>> paramList=buildHisParam(reqLimitChannel);
		for (Map<String, Object> map : paramList) {
			BMSOrgLimitChannel limitSave=null;
			List<BMSOrgLimitChannel> orgLimitChannelList = orgLimitChannelDao.findHistory(map);
			if(orgLimitChannelList != null && !orgLimitChannelList.isEmpty()){
				limitSave = orgLimitChannelList.get(0);
			}else{
				limitSave = new BMSOrgLimitChannel();
			}
			
			limitSave.setCreatorDate(new Date());
			limitSave.setCreator(reqLimitChannel.getServiceCode());
			//获取产品期限（BMS_PRODUCT_AUDIT_LIMIT）已配置上，下限
			BMSProductAuditLimit productAuditLimit = auditLimitDao.findByAuditLimitId((long) map.get("auditLimitId"));
			limitSave.setFloorLimit(productAuditLimit.getFloorLimit());
			limitSave.setUpperLimit(productAuditLimit.getUpperLimit());
			
			limitSave.setProductDeployCode(reqLimitChannel.getProductDeployCode());
			limitSave.setOrgId((Long) map.get("orgId"));
			limitSave.setIsDeleted(0L);
			limitSave.setChannelId((Long) map.get("channelId"));
			limitSave.setAuditLimitId((Long) map.get("auditLimitId"));
			
			if(orgLimitChannelList != null && !orgLimitChannelList.isEmpty()){
				orgLimitChannelDao.update(limitSave);
			}else{
				orgLimitChannelDao.insert(limitSave);
			}
		}

	flag = true;
	return flag;
	}
	
	public void deleteHisData(ReqBMSOrgLimitChannelVO reqLimitChannel){
		List<Long> orgs=reqLimitChannel.getOrgIds();
		List<String> channels=reqLimitChannel.getChannelIds();
		if("0".equals(reqLimitChannel.getStandard())&&orgs!=null){
			for (int j = 0; j < orgs.size(); j++) {
				BMSOrgLimitChannel limitDel = new BMSOrgLimitChannel();
				limitDel.setOrgId(orgs.get(j));
				orgLimitChannelDao.updateByCondition(limitDel);
			}
		}
		
		if("1".equals(reqLimitChannel.getStandard())&&channels!=null){
			for (int j = 0; j < channels.size(); j++) {
				BMSOrgLimitChannel limitDel = new BMSOrgLimitChannel();
				String channelCom=channels.get(j);
				String[] limitChaId=channelCom.split("_");
				limitDel.setChannelId(Long.parseLong(limitChaId[1]));
				limitDel.setAuditLimitId(Long.parseLong(limitChaId[0]));
				orgLimitChannelDao.updateByCondition(limitDel);
			}
		}
		
	}
	
	public List<Map<String,Object>> buildHisParam(ReqBMSOrgLimitChannelVO reqLimitChannel){
		Map<String, Object> historyParamMap =null;
		List<Long> channelNoRepeatIds = new ArrayList<Long>();
		//获取所有的渠道id
		List<Long> channelRepeatIds = new ArrayList<Long>();
		//获取审批期限
		List<Long> AuditLimitIds = new ArrayList<Long>();
		List<Long> orgids=reqLimitChannel.getOrgIds();
		List<String> channels=reqLimitChannel.getChannelIds();
		if(channels!=null){
			for(String channelLimStr : channels){
				if(StringUtils.isEmpty(channelLimStr)){
					continue;
				}
				String limitChaId[] = channelLimStr.split("_");
				Long limitId= Long.parseLong(limitChaId[0]);
				Long channelId= Long.parseLong(limitChaId[1]);
				if(!channelNoRepeatIds.contains(channelId)){
					channelNoRepeatIds.add(channelId);
				}
				AuditLimitIds.add(limitId);
				channelRepeatIds.add(channelId);
			}
		}
		
		List<Map<String, Object>> listParamMap = new ArrayList<Map<String, Object>>();
		if(orgids!=null){
			for (int i = 0; i < orgids.size(); i++) {
				Long orgId=orgids.get(i);
				for(int j=0;j<AuditLimitIds.size();j++){
					historyParamMap = new HashMap<String, Object>();
					historyParamMap.put("orgId",orgId);
					historyParamMap.put("channelId", channelRepeatIds.get(j));
					historyParamMap.put("auditLimitId", AuditLimitIds.get(j));
					listParamMap.add(historyParamMap);
				}
			}
		}
		
		return listParamMap;
	}
	
	
	@Override
	public Boolean saveRateOrgChannelLimit(ReqBMSOrgLimitChannelVO reqLimitChannel) {
		Boolean flag = false;
		
		//"1_12,2_12,"
		//判断orgId是否为空，不为空，证明是网点录单产品配置，为空，则是产品网点录单配置
		if(reqLimitChannel.getOrgId() ==null){
			String orgIdStr = "";
			//空判断
			if(reqLimitChannel.getOrgIdStr() == null || reqLimitChannel.getOrgIdStr().length() == 0){
				orgIdStr = "";
			} else {
				orgIdStr = reqLimitChannel.getOrgIdStr().substring(0, reqLimitChannel.getOrgIdStr().length()-1);
			}
			String[] orgIdArray  = orgIdStr.split(",");
			//第一个为审批期限id，第二个为渠道id
			String[] limitChaId= reqLimitChannel.getChannelLimitStr().split("_");
			//先删除后插入
			//删除为逻辑删除，即是将isDeleted设为1,条件为产品期限ID,渠道ID
			BMSOrgLimitChannel limitDel = new BMSOrgLimitChannel();
			limitDel.setChannelId(Long.parseLong(limitChaId[1]));
			limitDel.setAuditLimitId(Long.parseLong(limitChaId[0]));
			limitDel.setIsCanPreferential(1);
			orgLimitChannelDao.updateByParams(limitDel);
			
			
			Map<String, Object> historyParamMap = new HashMap<String, Object>();
			historyParamMap.put("channelId", Long.parseLong(limitChaId[1]));
			historyParamMap.put("auditLimitId", Long.parseLong(limitChaId[0]));
			//循环更新数据
			//构造门店渠道产品期限信息保存
			for(String orgId :orgIdArray){
				//空判断
				if(orgId == null || orgId.length() == 0){
					continue;
				}
				//如果配置已存在则不需从新插入，需要更新deleted 字段,
//				默认对应的，网点id，渠道id，产品期限，只能限制到一条数据
				historyParamMap.put("orgId",orgId);
				historyParamMap.put("isDeleted", 0);
				List<BMSOrgLimitChannel> orgLimitChannelList = orgLimitChannelDao.findHistory(historyParamMap);
				if(orgLimitChannelList != null && !orgLimitChannelList.isEmpty()){
					//历史配置过，进行更新
					BMSOrgLimitChannel limitSave = orgLimitChannelList.get(0);
					
					limitSave.setIsCanPreferential(0);
					orgLimitChannelDao.update(limitSave);
					
				}
			}
			
		}else{
			String channelLimitStr = "";
			//空判断
			if(reqLimitChannel.getChannelLimitStr() == null || reqLimitChannel.getChannelLimitStr().length() == 0){
				channelLimitStr = "";
			} else {
				channelLimitStr = reqLimitChannel.getChannelLimitStr().substring(0, reqLimitChannel.getChannelLimitStr().length()-1);
			}
			String[] limitChannelIds = channelLimitStr.split(",");
			//获取不重复的渠道ID
			List<Long> channelNoRepeatIds = new ArrayList<Long>();
			//获取所有的渠道id
			List<Long> channelRepeatIds = new ArrayList<Long>();
			//获取审批期限
			List<Long> AuditLimitIds = new ArrayList<Long>();
			
			//第一个为审批期限id，第二个为渠道id
			//1_12  2_12
			for(String channelLimStr : limitChannelIds){
				//空判断
				if(channelLimStr == null || channelLimStr.length() == 0){
					continue;
				}
				String limitChaId[] = channelLimStr.split("_");
				Long limitId= Long.parseLong(limitChaId[0]);
				Long channelId= Long.parseLong(limitChaId[1]);
				if(!channelNoRepeatIds.contains(channelId)){
					channelNoRepeatIds.add(channelId);
				}
				AuditLimitIds.add(limitId);
				channelRepeatIds.add(channelId);
			}
			//循环逻辑删除
			/*for(Long id :channelNoRepeatIds){
				//先删除后插入
				//删除为逻辑删除，即是将isDeleted设为1,条件为门店ID,渠道ID
				BMSOrgLimitChannel limitDel = new BMSOrgLimitChannel();
				limitDel.setChannelId(id);
				limitDel.setOrgId(reqLimitChannel.getOrgId());
				orgLimitChannelDao.updateByCondition(limitDel);				
			}*/
			
			//产品勾选为空，门店下所有配置要删除
			BMSOrgLimitChannel limitDel = new BMSOrgLimitChannel();
			limitDel.setOrgId(reqLimitChannel.getOrgId());
			limitDel.setIsCanPreferential(1);
			orgLimitChannelDao.updateByParams(limitDel);
			
			//默认对应的，网点id，渠道id，产品期限，只能限制到一条数据
			Map<String, Object> historyParamMap = new HashMap<String, Object>();
			historyParamMap.put("orgId",reqLimitChannel.getOrgId());
			//循环插入
			for(int i=0;i<AuditLimitIds.size();i++){
				historyParamMap.put("channelId", channelRepeatIds.get(i));
				historyParamMap.put("auditLimitId", AuditLimitIds.get(i));
				historyParamMap.put("isDeleted", 0);
				List<BMSOrgLimitChannel> orgLimitChannelList = orgLimitChannelDao.findHistory(historyParamMap);
				if(orgLimitChannelList != null && !orgLimitChannelList.isEmpty()){
					BMSOrgLimitChannel limitSave = orgLimitChannelList.get(0);
					
					limitSave.setIsCanPreferential(0);
					orgLimitChannelDao.update(limitSave);
				}
			}
		}
		flag = true;
		return flag;
	}
	@Override
	public Boolean updateOrgLimitChannel(ReqBMSOrgLimitChannelVO reqlimitChannel) {
		Boolean flag = false;
		//先更新审批期限与是否可用信息（审批期限表与门店渠道审批期限表）
		//构建审批期限更新对象
		/*BMSProductAuditLimit auditLimit = new BMSProductAuditLimit();
		auditLimit.setAuditLimitId(reqlimitChannel.getAuditLimitId());
		auditLimit.setFloorLimit(reqlimitChannel.getFloorLimit());
		auditLimit.setUpperLimit(reqlimitChannel.getUpperLimit());
		auditLimitDao.update(auditLimit);*/
		//构建门店渠道产品更新对象
		BMSOrgLimitChannel limitChannel = new BMSOrgLimitChannel();
		limitChannel.setId(reqlimitChannel.getId());
		limitChannel.setIsDisabled(reqlimitChannel.getIsDisabled());
		limitChannel.setFloorLimit(reqlimitChannel.getFloorLimit());
		limitChannel.setUpperLimit(reqlimitChannel.getUpperLimit());
		limitChannel.setConfigureConflict("N");
		orgLimitChannelDao.update(limitChannel);
		flag = true;
		return flag;
	}
	
	@Override
	public long logicalDelete(BMSOrgLimitChannel orgLimitChannel) {
		return orgLimitChannelDao.logicalDelete(orgLimitChannel);
	}
	
	@Override
	public boolean isDisabled(BMSOrgLimitChannel orgLimitChannel) {
		
		return orgLimitChannelDao.isDisabled(orgLimitChannel);
	}
	@Override
	public List<BMSOrgLimitChannel> findByProductCodeList(List<String> productCodeList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productCodeList", productCodeList);
		return orgLimitChannelDao.findByParams(params);
	}
	
	@Override
	public List<BMSOrgLimitChannel> findOrgByProductCodeListIntersect(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> paramMap;
		try {
			paramMap = BeanKit.bean2map(reqBMSOrgLimitChannelVO);
			paramMap.put("productCodeListSize", reqBMSOrgLimitChannelVO.getProductCodeList().size());
			return orgLimitChannelDao.findOrgIntersectByParams(paramMap);
		} catch (Exception e) {
			// 抛出自定义异常
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		}
	}
	@Override
	public boolean branchProductRelevanceCheck(ReqBMSOrgLimitChannelVO reqBMSOrgLimitChannelVO) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", reqBMSOrgLimitChannelVO.getOrgId());
		params.put("productCode", reqBMSOrgLimitChannelVO.getProductCode());
		return orgLimitChannelDao.branchProductRelevanceCheck(params);
	}
	@Override
	public boolean isDisabledInSign(Map<String, Object> params) {
	
		return orgLimitChannelDao.isDisabledInSign(params);
	}
	@Override
	public List<BMSOrgLimitChannel> listOrgProductLimitByOrgProApp(Map<String, Object> paramMap) {
		return orgLimitChannelDao.listOrgProductLimitByOrgProApp(paramMap);
	}
	@Override
	public long updateByOrgLimitId(BMSOrgLimitChannel orgLimitChannel) {
		
		return orgLimitChannelDao.updateByOrgLimitId(orgLimitChannel);
	}
	@Override
	public BMSOrgLimitChannel findOrgLimitChannelLimitUnion(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return orgLimitChannelDao.findOrgLimitChannelLimitUnion(paramMap);
	}
	@Override
	public List<BMSOrgLimitChannel> listOrgLimitChannelRateBy(Map<String, Object> paramMap) {
		return orgLimitChannelDao.listOrgLimitChannelRateBy(paramMap);
	}
	
}
