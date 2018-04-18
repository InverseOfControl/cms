package com.ymkj.cms.biz.service.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.master.BMSContractChange;

public interface IBMSContractChangeService extends BaseService<BMSContractChange> {
	
	/**
	 * 通过查询对应网点渠道产品是否可用
	 * @param params 
	 * @return
	 */
	public BMSContractChange getProductIsDisabled(Map<String,Object> params);
	
	/**
	 * 更新loanbase表的签约网点和签约客服字段
	 * @param map
	 * @return
	 */
	public Integer updateLoanBase(Map<String, Object> map);
	
	/**
	 * 获取loan_base表旧版本号
	 * @param id
	 * @return
	 */
	public Integer getLoanBaseVersion(Integer id);
	
	/**
	 * 签约改派表，人员更新，存在新人则插入,
	 * @param customerServiceList
	 * @author lix YM10160
	 * @date 2017年3月27日下午5:46:50
	 */
	public Integer insertContractChangeFrequency(List<String> customerServiceList, Long orgId);
	
	/**
	 * 获取分配最低的usercode，一条数据，id排序
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日下午7:45:54
	 */
	public String getUserCodeFrequencyLow(Map<String, Object> map);
	
	/**
	 * 签约改派表，营业部下人剔除则删除
	 * @param customerServiceList
	 * @param valueOf
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日上午10:55:12
	 */
	public int deleteContractChangeFrequency(List<String> customerServiceList, Long orgId);

	/**
	 * 改派次数加一
	 * @param map
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日上午11:53:17
	 */
	public Integer increaseContractChangeFrequency(Map<String, Object> map);

}
