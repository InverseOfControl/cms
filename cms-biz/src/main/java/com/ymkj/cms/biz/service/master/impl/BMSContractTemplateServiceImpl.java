package com.ymkj.cms.biz.service.master.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSContractTemplateVO;
import com.ymkj.cms.biz.dao.master.IBMSContractChannelDao;
import com.ymkj.cms.biz.dao.master.IBMSContractTemplateDao;
import com.ymkj.cms.biz.entity.master.BMSContractChannel;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;
import com.ymkj.cms.biz.service.master.IBMSContractTemplateService;

@Service
public class BMSContractTemplateServiceImpl extends BaseServiceImpl<BMSContractTemplate> implements IBMSContractTemplateService {
	
	@Autowired
	private IBMSContractTemplateDao bmsContractDao;
	@Autowired
	private IBMSContractChannelDao bmsContractChannelDao;
	@Override
	protected BaseDao<BMSContractTemplate> getDao() {
		return bmsContractDao;
	}
	@Override
	public Long saveTemplate(BMSContractTemplate template) {
		Long id = bmsContractDao.insert(template);
		Long channelId = null;
		if(id !=null && id !=0L){
			BMSContractChannel entity = new BMSContractChannel();
			entity.setChannelId(template.getChannelId());
			entity.setTemplateId(template.getId());
			channelId  = bmsContractChannelDao.insert(entity);
			
		}
		return channelId;
	}
	/**
	 * 
	 * @param str
	 * @param strLength
	 * @return
	 */
	@SuppressWarnings("unused")
	private  String addZeroForNum(String str,int strLength) {
		  int strLen =str.length();
		  if (strLen <strLength) {
		   while (strLen< strLength) {
		    StringBuffer sb = new StringBuffer();
		    sb.append("0").append(str);//左补0
		    str= sb.toString();
		    strLen= str.length();
		   }
		  }

		  return str;
		 }
	@Override
	public Long updateTemplate(BMSContractTemplate template) {
		Long id = bmsContractDao.update(template);
		Long channelId = null;
		if(id !=null && id !=0L){
			BMSContractChannel entity = new BMSContractChannel();
			entity.setChannelId(template.getChannelId());
			entity.setTemplateId(template.getId());
			entity.setId(template.getContractChannelId());
			channelId  = bmsContractChannelDao.update(entity);
			
		}
	return channelId;
	}
	
	@Override
	public BMSContractTemplate findByVO(ReqBMSContractTemplateVO reqContractTemplateVO) {
		return bmsContractDao.findByVO(reqContractTemplateVO);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BMSContractTemplate> listByChannelCd(String channelCd) {
		Map paramMap =new HashMap();
		paramMap.put("channelCd", channelCd);
		
		return bmsContractDao.listByChannelCd(paramMap);
	}


}
