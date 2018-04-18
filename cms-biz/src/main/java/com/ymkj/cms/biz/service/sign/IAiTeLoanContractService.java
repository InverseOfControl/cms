package com.ymkj.cms.biz.service.sign;

import java.util.List;
import java.util.Map;

import com.ymkj.cms.biz.api.vo.request.finance.ReqLoanVo;
import com.ymkj.cms.biz.api.vo.request.sign.ReqLoanContractSignVo;
import com.ymkj.cms.biz.api.vo.request.sign.RequestVo;
import com.ymkj.cms.biz.entity.apply.APPPersonInfoEntity;
import com.ymkj.cms.biz.entity.sign.BMSRepaymentDetail;

public interface IAiTeLoanContractService {
	
	/**
	 * 推送标的信息给爱特
	 * @param reqLoanContractSignVo
	 */
	public boolean targetPushedToAiTe(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 取消，只更新状态
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午6:04:36
	 */
	public boolean cancelLoan(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 拒绝，只更新状态
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日上午11:12:15
	 */
	public boolean refuseLoan(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 取消，若果已推送标的，需要调用终止借款接口
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月16日下午6:04:36
	 */
	public boolean cancelLoanHasUflo(ReqLoanContractSignVo reqLoanContractSignVo);

	/**
	 * 拒绝，若果已推送标的，需要调用终止借款接口
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日上午11:12:15
	 */
	public boolean refuseLoanHasUflo(ReqLoanContractSignVo reqLoanContractSignVo);

	/**
	 * 合同确认
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午2:04:38
	 */
	public boolean confirmLoanContract(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 放款审核通过
	 * @param reqLoanVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午2:17:53
	 */
	public boolean passAuditLoan(ReqLoanContractSignVo reqLoanContractSignVo);

	/**
	 * 合同确认退回
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午2:23:11
	 */
	public boolean backConfirm(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 放款审核回退
	 * @param reqLoanVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午2:25:57
	 */
	public boolean backAudit(ReqLoanVo reqLoanVo);
	
	/**
	 * 央行征信报告上传到爱特ftp服务器
	 * @param tmAppPersonInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午4:41:53
	 */
	public boolean creditInvestigationUploadFile(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 上海资信报告上传到爱特ftp服务器
	 * @param tmAppPersonInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午4:41:53
	 */
	public boolean creditReportUploadFile(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 人身份证信息上传到爱特ftp服务器
	 * @param tmAppPersonInfo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月17日下午4:41:53
	 */
	public boolean IDCardUploadFile(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 终止借款处理
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月20日下午4:11:31
	 */
	public boolean terminationLoanHasUflo(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 爱特,合同签订
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年3月31日下午7:21:58
	 */
	public boolean signLoanContract(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 标的锁定,
	 * @param reqLoanContractSignVo
	 * @author lix YM10160
	 * @return 
	 * @date 2017年4月1日上午10:20:03
	 */
	public boolean lockTarget(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 标的解锁锁定
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月5日上午10:16:38
	 */
	public boolean unLockTarget(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 合同签约（推送标的）后退回
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月5日上午10:53:41
	 */
	public boolean backSign(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 放款确认
	 * @param reqLoanVoBase
	 * @author lix YM10160
	 * @date 2017年4月13日下午5:30:32
	 */
	/**
	 * 放款确认通过
	 * @param reqLoanContractSignVo
	 * @return
	 */
	public Map<String, Object> grantLoan(ReqLoanVo reqLoanVo) ;
	
	/**
	 * 还款计划查询
	 * @param requestVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月18日下午4:14:01
	 */
	public Map<String,Object> queryRepaymentDetail(RequestVo requestVo);
	
	/**
	 * 调用合同确认通知捞财宝
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年4月20日上午10:18:04
	 */
	public boolean contractConfirmationToAiTe(ReqLoanContractSignVo reqLoanContractSignVo);
	
	/**
	 * 放款确认退回
	 * @param reqLoanVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月18日上午11:30:11
	 */
	public boolean backLoan(ReqLoanVo reqLoanVo);
	
	/**
	 * 取消锁定
	 * @param reqLoanContractSignVo
	 * @return
	 * @author lix YM10160
	 * @date 2017年10月11日下午2:58:48
	 */
	public boolean discardLockTarget(ReqLoanContractSignVo reqLoanContractSignVo);
}
