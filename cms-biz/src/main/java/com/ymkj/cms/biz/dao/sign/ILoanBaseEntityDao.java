package com.ymkj.cms.biz.dao.sign;

import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.base.core.biz.common.PageParam;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanApp;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

import java.util.List;
import java.util.Map;

public interface ILoanBaseEntityDao extends BaseDao<BMSLoanBaseEntity>{
	
	
	public PageBean<BMSLoanBaseEntity> donelistPage(PageParam pageParam, Map<String, Object> paramMap);

	public PageBean<BMSLoanBaseEntity> undoneContractConfirmListPage(PageParam pageParam, Map<String, Object> paramMap);

	public PageBean<BMSLoanBaseEntity> doneContractConfirmListPage(PageParam pageParam, Map<String, Object> paramMap);
	
	@SuppressWarnings("rawtypes")
	 public BMSLoanApp  findLoanEntity(Map paramMap) ;

	public BMSLoanBaseEntity findSignInfo(Map paramMap);


	public BMSLoanBaseEntity findByloanBaseId(Map<String, Object> param);
	
	 
	public List<Map<String, Object>> getManagerCodeListByOrgId(String orgId);

	public BMSLoanBaseEntity getLoanInfoByLoanNo(Map<String, Object> paramMap);
	
	/**
	 * @Description:合同签约待办任务数</p>
	 * @uthor YM10159
	 * @date 2017年7月3日 下午2:58:38
	 */
	public long queryContractSignToDoCount(Map<String,Object> paramsMap);
	
	/**
	 * @Description:合同确认待办任务数</p>
	 * @uthor YM10159
	 * @date 2017年7月3日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public long queryContractConfirmToDoCount(Map<String,Object> paramsMap);
	
	
	/**
	 * @Description:合同签约补件待办</p>
	 * @uthor YM10139
	 * @date  2017年8月8日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public PageBean<BMSLoanBaseEntity> undoneListPatchBoltPage(PageParam pageParam,Map<String, Object> paramMap);
	
	
	/**
	 * @Description:合同签约补件待办</p>
	 * @uthor YM10139
	 * @date  2017年8月8日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public PageBean<BMSLoanBaseEntity> doneListPatchBoltPage(PageParam pageParam,Map<String, Object> paramMap);
	
	
	/**
	 * @Description:合同签约补件待办任务数</p>
	 * @uthor YM10139
	 * @date  2017年8月8日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public long queryContractPatchBoltToDoCount(Map<String, Object> paramsMap);
	
	/**
	 * @Description:签约待认领待办任务数</p>
	 * @uthor YM10139
	 * @date  2017年9月11日 下午3:32:55
	 * @param paramsMap
	 * @return
	 */
	public PageBean<BMSLoanBaseEntity> unclaimedContractSignListBy(PageParam pageParam, Map<String, Object> paramMap);


	
	
}
