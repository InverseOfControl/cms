package com.ymkj.cms.biz.service.channel.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymkj.cms.biz.common.util.DateUtil;
import com.ymkj.cms.biz.dao.channel.IBMSSequenceDao;
import com.ymkj.cms.biz.entity.channel.BMSSequence;
import com.ymkj.cms.biz.service.channel.IBMSSequenceService;

/**
 * @author YM10189
 * @date 2017年5月16日
 * @Description:序列操作接口实现
 */
@Service
public class BMSSequenceServiceImpl implements IBMSSequenceService {
	
	@Autowired
	private IBMSSequenceDao bmsSequenceDao;

	@Override
	public int queryBatchSequence(String sequenceId) {
		Map<String,Object> paraMap=new HashMap<String,Object>();
		int sequenceNum=0;
		BMSSequence sequence=bmsSequenceDao.getSequenceObj(sequenceId);
		paraMap.put("id", sequence.getId());
		if(DateUtil.defaultFormatDay(new Date()).equals(DateUtil.defaultFormatDay(sequence.getUpdateTime()))){
			sequenceNum=sequence.getSeqCurlNum()+sequence.getSeqIncret();
		}else{
			sequenceNum=sequence.getSeqStartNum();
		}
		paraMap.put("seqCurlNum", sequenceNum);
		bmsSequenceDao.updateSequence(paraMap);
		return sequenceNum;
	}
	
}
