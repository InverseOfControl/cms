package com.ymkj.cms.biz.service.sign;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.biz.common.PageBean;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;
import com.ymkj.cms.biz.entity.master.BMSContractTemplate;
import com.ymkj.cms.biz.entity.sign.BMSLoanBaseEntity;
import com.ymkj.cms.biz.entity.sign.BMSLoanContract;
import com.ymkj.cms.biz.entity.sign.BMSLoanProduct;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;

public interface ILoanContractSignService {
	
	
	/**
	 *  签约待办任务
	 * 
	 */
	public PageBean<BMSLoanBaseEntity> undoneContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * @Description:合同签约待办任务数</p>
	 * @uthor YM10159
	 * @date 2017年7月3日 下午3:06:45
	 */
	long queryContractSignToDoCount(Map<String,Object> paramsMap);
	
	/**
	 *  签约已办任务
	 * 
	 */
	public PageBean<BMSLoanBaseEntity> doneContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 *  签约页面信息查询
	 * 
	 */
	public BMSLoanBaseEntity findSignInfo(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	
	/**
	 * 保存合同签约信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Map<String,Object> saveLoanContractSign(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 保存合同银行信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Map<String,Object> saveLoanContractBankAcc(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
		
	/**
	 * 生成合同
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Map<String,Object> createLoanContract(ReqLoanContractSignVo reqLoanContractSignVo)throws IllegalAccessException, InvocationTargetException, IntrospectionException;
	
	
	
	
	/**
	 * 合同签订
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean signLoanContract(ReqLoanContractSignVo reqLoanContractSignVo);
	
	

	/**
	 *上一步
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean returnLastStep(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 取消
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean cancelLoan(ReqLoanContractSignVo reqLoanContractSignVo) ;
	

	/**
	 * 拒绝   
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public boolean refuseLoan(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 根据合同模板Id返回填充合同模板  Str
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public List<BMSContractTemplate> getContractUrlList(ReqLoanContractSignVo reqLoanContractSignVo);

	
	/**
	 * 退件箱办理流转
	 * @param reqLoanContractSignVo
	 * @return
	 */
	boolean returnBoxChoiceTask(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 根据loanBaseId查询合同编号
	 * @param loanBaseId
	 * @return
	 */
	public BMSLoanContract findByLoanNo(String loanNo);
	
	/**
	 * 查询合同信息
	 * @param loanBaseId
	 * @return
	 */
	public BMSLoanContract findLoanContractInfo(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 查询还款计划
	 * @param loanBaseId
	 * @return
	 */
	public List<BMSRepaymentDetail> getPayPlanList(ReqLoanContractSignVo reqLoanContractSignVo);
	

	/**
	 * 更新借款产品
	 * @param bmsLoanProduct
	 * @return
	 */
	public long update(BMSLoanProduct bmsLoanProduct);

	/**
	 * 查询借款产品信息
	 * @param loanNo
	 * @return
	 */
	public BMSLoanProduct findBMSLoanProductByLoanNo(String loanNo);
	
	/**
	 *  签约未领取任务列表
	 * 
	 */
	public PageBean<BMSLoanBaseEntity> unclaimedContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	

	/**
	 *  客服领取完成任务列表
	 * @return 
	 * 
	 */
	public boolean claimedContractSign(ReqLoanContractSignVo reqLoanContractSignVo);

	
	/**
	 *  删除文件夹下的图片
	 * @param res 
	 * @return 
	 * 
	 */
	void checkChangeAndDelFile(ReqLoanContractSignVo reqLoanContractSignVo, Response<ResLoanContractSignVo> res);
}

