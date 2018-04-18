package com.ymkj.cms.biz.dao.apply.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ymkj.base.core.biz.dao.BaseDaoImpl;
import com.ymkj.cms.biz.dao.apply.LoanProductDao;
import com.ymkj.cms.biz.entity.apply.LoanProductEntity;
import com.ymkj.cms.biz.entity.master.BMSProduct;

@Repository
public class LoanProductDaoImpl extends BaseDaoImpl<LoanProductEntity> implements LoanProductDao{

	@Override
	public List<String> getChannelCodeByLoans(List<String> loans) {
		// TODO Auto-generated method stub
		return getSessionTemplate().selectList("getChannelCodeByLoans", loans);
	}

	@Override
	public String getContractType(String loanNo) {
		List<String> datas=getSessionTemplate().selectList("getContractType", loanNo);
		if(datas!=null){
			return datas.get(0);
		}
		return null;
	}

	
	@Override
	public BMSProduct findProductData(String productCd) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("productCd", productCd);
		return getSessionTemplate().selectOne("findProductData", map);
	}
}
