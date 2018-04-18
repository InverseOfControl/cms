package com.ymkj.cms.biz.dao.apply;

import java.util.List;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.apply.LoanProductEntity;
import com.ymkj.cms.biz.entity.master.BMSProduct;
/**
 * dao 接口定义
 * @author haowp
 *
 */
public interface LoanProductDao extends BaseDao<LoanProductEntity>{
	
	/**
	 * 根据借款编号查询渠道号
	 * @param loans
	 * @return
	 */
	public List<String> getChannelCodeByLoans(List<String> loans);
	
	//查询全同类型
	public String getContractType(String loanNo);
	public BMSProduct findProductData(String productCd);
}
