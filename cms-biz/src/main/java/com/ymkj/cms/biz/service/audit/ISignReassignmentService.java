package com.ymkj.cms.biz.service.audit;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.LoanBaseEntity;

public interface ISignReassignmentService extends BaseService<LoanBaseEntity> {
	
	/**
	 * <p>Description:获取需要签约改派的列表信息</p>
	 * @uthor YM10159
	 * @date 2017年3月23日 上午9:06:15
	 * @param @return
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
	 * <p>Description:获取不能接单的客服。也就是黑名单客服</p>
	 * @uthor YM10159
	 * @date 2017年4月11日 下午4:32:16
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
