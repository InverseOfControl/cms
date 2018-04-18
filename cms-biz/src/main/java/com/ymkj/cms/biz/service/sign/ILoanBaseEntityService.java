package com.ymkj.cms.biz.service.sign;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSLoanBase;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;

import java.util.List;
import java.util.Map;

public interface ILoanBaseEntityService extends BaseService<BMSLoanBaseEntity>{
	/**
	 * 通过借款编号获取借款信息
	 * @param borrowNo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月15日下午1:53:48
	 */
	public BMSLoanBaseEntity getByLoanNo(String loanNo);
	
	/**
	 * 通过借款编号获取借款信息
	 * @param borrowNo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月15日下午1:53:48
	 */
	public BMSLoanBaseEntity findByloanBaseId(String id);

	/**
	 * 通过借款id获取经理和处理单子count
	 * @param borrowNo
	 * @return
	 * @author lifz YM10139
	 * @date 2017年4月30日下午1:53:48
	 */
	public List<Map<String, Object>> getManagerCodeListByOrgId(String orgId);

	/**
	 * 包银渠道获取借款信息
	 * @param loanNo
	 * @return
	 */
	public BMSLoanBaseEntity getLoanInfoByLoanNo(String loanNo);
}
