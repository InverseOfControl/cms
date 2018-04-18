package com.ymkj.cms.biz.dao.sign;

import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;

import java.util.Map;

public interface ILoanContractSignDao extends BaseDao<BMSLoanProduct>{
	/**
	 * 查询借款产品信息
	 * @param param
	 * @return
	 */
    BMSLoanProduct findBMSLoanProductByLoanNo(Map<String, Object> param);

    /**
	 * 保存合同签约信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	/*public boolean saveLoanContractSign(BMSLoanProduct bmsLoanProduct);*/
	
	
	/**
	 * 保存合同银行信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	/*public boolean saveLoanContractBankAcc(BMSLoanProduct bmsLoanProduct);*/
	
	
		
	/**
	 * 保存返回合同相关信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
/*	public boolean saveReturnLoanContract(BMSLoanProduct bmsLoanProduct);*/
	
	
	
	/**
	 * 更新借款状态
	 * @param reqLoanContractSignVo
	 * @return
	 */
	/*public boolean updateLoanState(BMSLoanProduct bmsLoanProduct);*/
	
	
	
	
}
