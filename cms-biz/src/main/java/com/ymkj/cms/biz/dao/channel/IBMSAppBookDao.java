package com.ymkj.cms.biz.dao.channel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.channel.BMSApplyBookInfo;

/**
 * @author YM10189
 * @date 2017年5月3日
 * @Description:申请书管理dao inerface
 */
public interface IBMSAppBookDao extends BaseDao<BMSApplyBookInfo> {

	public int queryCurrentDayCount(Map<String, Object> paraMap);

	public List<String> findBatchNumByFundsSources(Map<String, Object> paraMap);

	public BMSApplyBookInfo queryApplyBookInfoBybatchNum(Map<String, Object> paraMap);

	public List<BMSApplyBookInfo> queryDayApplyBookInfos(Map<String, Object> paraMap);

	public BigDecimal queryAlreadyGrantMoney(List<String> batchNums);

}
