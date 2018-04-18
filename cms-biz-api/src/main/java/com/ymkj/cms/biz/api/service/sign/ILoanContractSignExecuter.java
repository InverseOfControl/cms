package com.ymkj.cms.biz.api.service.sign;

import java.util.List;

import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqSignElectronic;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSChannelVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSContractTemplateVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSEnumCodeVO;
import com.ymkj.cms.biz.api.vo.response.master.ResListVO;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractInfoVo;
import com.ymkj.cms.biz.api.vo.response.sign.ResLoanContractSignVo;

public interface ILoanContractSignExecuter {
	
	
	/**
	 * 签约待办任务   
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageResponse<ResLoanContractSignVo> undoneContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * @Description:查询合同签约待办任务数</p>
	 * @uthor YM10159
	 * @date 2017年7月3日 下午3:01:02
	 * @param reqLoanContractSignVo
	 */
	public Response<Object> queryContractSignToDoCount(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 签约已完成任务   
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public PageResponse<ResLoanContractSignVo> doneContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 *  签约页面信息查询
	 * 
	 */
	public Response<ResLoanContractSignVo> findSignInfo(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	
	/**
	 * 保存合同签约信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> saveLoanContractSign(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 保存合同银行信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> saveLoanContractBankAcc(ReqLoanContractSignVo reqLoanContractSignVo);	
		
	/**
	 * 保存外部平台用户信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> saveLoanContractPlatformAcc(ReqLoanContractSignVo reqLoanContractSignVo);	
	
	/**
	 * 生成合同
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> createLoanContract(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 合同签订
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> signLoanContract(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 *上一步
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> returnLastStep(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 取消
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> cancelLoan(ReqLoanContractSignVo reqLoanContractSignVo) ;
	

	/**
	 * 拒绝   
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractSignVo> refuseLoan(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 根据合同模板Id返回填充合同模板  Str
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public ResListVO<ResBMSContractTemplateVO> getContrctListByTempId(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 校验产品是否可用
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<String> valiProductIsDisabled( ReqLoanContractSignVo reqLoanContractSignVo);
	

	 
	/**
	 * 退件箱选择任务节点
	 * @param reqLoanContractSignVo
	 * @return
	 */
	Response<ResLoanContractSignVo> returnBoxChoiceTask(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 查找借款产品信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Response<ResLoanContractInfoVo> findLoanProdcut(ReqLoanContractSignVo reqLoanContractSignVo) ;


	/**
	 * 查找还款计划信息
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public ResListVO<ResLoanContractInfoVo> getPayPlanList(ReqLoanContractSignVo reqLoanContractSignVo) ;
	
	/**
	 * 合同签名结果
	 * @param appNo
	 * @return
	 */
	public Response<Boolean> querySignContractStatus(ReqSignElectronic req);
	
	/**
	 * 合同类型下拉框数据查询
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月10日上午11:08:11
	 */
	public ResListVO<ResBMSEnumCodeVO> findContractTypeList(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 合同类型是否可用验证
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月10日下午5:02:58
	 */
	public Response<String> valiContractTypeIsDisabled(ReqLoanContractSignVo reqLoanContractSignVo);
	/**
	 * 查询借款对应的渠道锁定状态（锁定Y，拒绝或退回N）
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年7月20日下午4:26:12
	 */
	public Response<ResLoanContractSignVo> findLoanChannelLockTarget(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 对捞财宝渠道借款进行终止借款
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年7月20日下午4:26:12
	 */
	public Response<ResLoanContractSignVo> aiteTerminationLoan(ReqLoanContractSignVo reqLoanContractSignVo);
	
	
	/**
	 * 签约未认领任务   
	 * @param reqLoanContractSignVo
	 * @return
	 * @author liufz YM10139
	 * @date 2017年9月11日下午4:26:12
	 */
	public PageResponse<ResLoanContractSignVo> unclaimedContractSignListBy(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 签约认领任务   
	 * @param reqLoanContractSignVo
	 * @return
	 * @author liufz YM10139
	 * @date 2017年9月11日下午4:26:12
	 */
	public Response<ResLoanContractSignVo> claimedContractSign(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 签约渠道显示校验
	 * @param reqLoanContractSignVo
	 * @return
	 * @author liufz YM10139
	 * @date 2017年9月11日下午4:26:12
	 */
	public Response<List<ResBMSChannelVO>> getChannelByOrgProAltLoanCheck(ReqLoanContractSignVo reqLoanContractSignVo);
	
	

}
