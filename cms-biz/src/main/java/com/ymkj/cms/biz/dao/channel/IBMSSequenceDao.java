package com.ymkj.cms.biz.dao.channel;

import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.channel.BMSSequence;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:序列操作dao 接口
 */
public interface IBMSSequenceDao extends BaseDao<BMSSequence>{
	
	public BMSSequence getSequenceObj(String id);
	
	public int updateSequence(Map<String,Object> paraMap);
	

	

}
