package com.ymkj.cms.biz.api.service.job;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.cms.biz.api.vo.request.job.ReqLoanJobVO;

public interface IBMSLoanJobExecuter {
	
	/**
	 * 录单APP/非APP进件时效超时自动拒绝
	 * 录入超时拒绝	LRCS-REJECT
	 * 复核超时拒绝	FHCS-REJECT
	 * @param reqLoanJobVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月17日下午4:08:58
	 */
	public Response<Object> recordTimeOutRefuseAPP(ReqLoanJobVO reqLoanJobVO);
	/**
	 * 超补件时效自动取消
	 * 复核超时取消	FHCS-CANCEL
	 * @param reqLoanJobVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月17日下午4:08:58
	 */
	public Response<Object> supplementTimeOutCancel(ReqLoanJobVO reqLoanJobVO);
	/**
	 * 超签约时效自动取消
	 * 签约超时取消	QYCS-CANCEL
	 * @param reqLoanJobVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月17日下午4:08:58
	 */
	public Response<Object> signTimeOutCancel(ReqLoanJobVO reqLoanJobVO);
	/**
	 * 自动绑定征信报告
	 * @param reqLoanJobVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年5月17日下午4:08:58
	 */
	public Response<Object> bindCreditReport(ReqLoanJobVO reqLoanJobVO);
	/**
	 * 放款审核代办,依据核心状态更新数据
	 * @param reqLoanJobVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月2日上午10:11:57
	 */
	public Response<Object> grantLoanUpdateByCore(ReqLoanJobVO reqLoanJobVO);
	/**
	 * PC端处于录单环节的申请，日终跑批送益百利做标识处理
	 * @param reqLoanJobVO
	 * @return
	 * @author lix YM10160
	 * @date 2017年6月13日下午3:55:25
	 */
	public Response<Object> recordLogoProcessing(ReqLoanJobVO reqLoanJobVO);

}
