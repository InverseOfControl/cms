package com.ymkj.cms.biz.dao.master;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.master.BMSContractChange;

public interface IBMSContactChangeDao extends BaseDao<BMSContractChange> {
	
	/**
	 * 查询对应录单网点渠道产品是否可用
	 * @param id
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
	 * 签约改派表，人员更新，存在新人则插入
	 * @param map
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月27日下午5:50:51
	 */
	public Integer insertContractChangeFrequency(Map<String, Object> map);
	
	/**
	 * 分配基础值
	 * @return
	 * @author lix YM10160
	 * @param map 
	 * @date 2017年3月27日下午7:07:05
	 */
	public Integer selectContractChangeFrequencyBase(Map<String, Object> map);
	
	/**
	 * 获取分配最低的usercode，一条数据，id排序
	 * @return
	 * @param map 
	 * @author lix YM10160
	 * @date 2017年3月27日下午7:47:27
	 */
	public String getUserCodeFrequencyLow(Map<String, Object> map);
	
	/**
	 * 删除，营业部下pic已删除的人
	 * @param map
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日上午10:27:32
	 */
	public Integer deleteContractChangeFrequency(Map<String, Object> map);
	
	/**
	 * 改派次数加一
	 * @param map
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月28日上午11:54:05
	 */
	public Integer increaseContractChangeFrequency(Map<String, Object> map);

}
