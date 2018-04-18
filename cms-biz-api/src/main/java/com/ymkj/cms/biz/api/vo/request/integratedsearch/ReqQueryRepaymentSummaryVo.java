package com.ymkj.cms.biz.api.vo.request.integratedsearch;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqQueryRepaymentSummaryVo</p>
 * <p>Description:查询还款汇总信息请求对象</p>
 * @uthor YM10159
 * @date 2017年3月14日 下午2:10:09
 */
public class ReqQueryRepaymentSummaryVo extends Request{

	private static final long serialVersionUID = 7787777196870350967L;

	public ReqQueryRepaymentSummaryVo(){
		super.setSysCode(EnumConstants.BMS_SYSCODE);
	}
	
	/**
	 * 操作员code
	 */
	@NotEmpty(message="操作员code不能为空")
	private String userCode;
	/**
	 * 借款编号
	 */
	@NotEmpty(message="借款编号不能为空")
	private String loanNo;

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
}
