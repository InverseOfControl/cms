package com.ymkj.cms.biz.service.apply;

import java.util.List;

import com.ymkj.base.core.biz.service.BaseService;
import com.ymkj.cms.biz.entity.apply.LoanProductEntity;
import com.ymkj.cms.biz.entity.master.BMSProduct;
/**
 * demo 服务层
 * @author haowp
 *
 */
public interface LoanProductService extends BaseService<LoanProductEntity>{
	
	public Long saveOrUpdate(LoanProductEntity loanProductEntity);
	
	public boolean saveList(List<LoanProductEntity> loanProductEntity);
	
	public List<String> getChannelCodeByLoans(List<String> loans);
	
	public String getContractType(String loanNo);
	public BMSProduct findProductData(String productCd);
}
	