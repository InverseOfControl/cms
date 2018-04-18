package com.ymkj.cms.biz.dao.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;

public interface ISignReassignmentDao extends BaseDao<LoanBaseEntity>{
	
	/**
	 * <p>Description:获取已经签约分派的借款进行签约改派的列表</p>
	 * @uthor YM10159
	 * @date 2017年3月22日 下午5:04:57
	 */
	public List<LoanBaseEntity> getSignReassignList();
	
	/**
	 * <p>Description:根据机构ID获取机构下的已经签约的客服</p>
	 * @uthor YM10159
	 * @date 2017年3月23日 上午9:26:45
	 * @param @return
	 */
	public List<Map<String,Object>> getSignCodeListByOrgId(String orgId);
	
	/**
	 * <p>Description:签约改派</p>
	 * @uthor YM10159
	 * @date 2017年3月23日 下午2:37:22
	 * @param @param loanBaseEntity
	 */
	public int signReassign(LoanBaseEntity loanBaseEntity);
	
	/**
	 * <p>Description:获取黑名单客服</p>
	 * @uthor YM10159
	 * @date 2017年4月11日 下午4:33:25
	 */
	public List<Map<String,Object>> getWhitePerson();

	/**
	 * <p>Description:根据机构ID获取机构下的已经签约的客服</p>
	 * @uthor YM10159
	 * @date 2017年3月23日 上午9:26:45
	 * @param @return
	 */
	public List<Map<String, Object>> getSignCodeListBy(Map<String, Object> paramMap);
	
}
