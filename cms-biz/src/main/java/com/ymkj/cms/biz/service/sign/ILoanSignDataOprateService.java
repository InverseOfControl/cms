package com.ymkj.cms.biz.service.sign;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.entity.apply.APPCarInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPEstateInfoEntity;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.audit.LoanAuditEntity;
import com.ymkj.cms.biz.entity.finance.BMSLoanBank;
import com.ymkj.cms.biz.entity.sign.BMSContractLoan;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;
import com.ymkj.cms.biz.entity.sign.HttpContractReturnEntity;
import com.ymkj.pms.biz.api.vo.response.ResEmployeeVO;

public interface ILoanSignDataOprateService {
	
	@SuppressWarnings("rawtypes")
	public  String fetchAllApplyData(ReqLoanContractSignVo reqLoanContractSignVo);

	
	public  Map<String,Object> setValueToContract(HttpContractReturnEntity contractReturnEntity) throws IllegalAccessException, InvocationTargetException, IntrospectionException;

	
	/**
	 * 还款函中填充值
	 * @param map 公用map
	 * @param responseObject 接口返回值
	 * @throws JSONException
	 */
	public  void setRepayArrayToMap(Map<String, Object> map,List<BMSRepaymentDetail> repaylist);
	
	/**
	 * 保存银行时还款银行数据
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午2:50:22
	 */
	public BMSLoanBank handleBanks(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 爱特(捞财宝)请求参数封装
	 * @param loanInfo
	 * @param contractLoan
	 * @param loanContract
	 * @param tmAppPersonInfo
	 * @param tmAppPersonInfo2
	 * @param tmAppCarInfo
	 * @param tmAppEstateInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日上午10:55:25
	 */
	public Map<String, String> convertToRequestMap(BMSLoanBaseEntity loanInfo, BMSContractLoan contractLoan,
			BMSLoanContract loanContract, APPPersonInfoEntity tmAppPersonInfo, APPPersonInfoEntity tmAppPersonInfo2,
			APPCarInfoEntity tmAppCarInfo, APPEstateInfoEntity tmAppEstateInfo, LoanAuditEntity loanAuditEntity);
	
	/**
	 * 服务费赛选
	 * @param loanContract
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日上午10:58:45
	 */
	public BigDecimal filterServiceFee(BMSLoanContract loanContract);
	
	/**
	 * 爱特合同签约的常规校验，含有标的锁定
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午2:50:22
	 */
	public BMSLoanBaseEntity aiTeConventionCheck(RequestVo requestVo);
	
	
	
	public ResEmployeeVO getSignManager(String serviceCode,String managerType);

	
	/**
	 * 预审批接口  参数封装
	 * @param loanNo
	 * @return
	 * @author lix YM10160
	 * @date 2017年7月26日下午3:19:07
	 */
	public Map<String, Object> getPreliminaryAuditData(String loanNo);
	
	

}
