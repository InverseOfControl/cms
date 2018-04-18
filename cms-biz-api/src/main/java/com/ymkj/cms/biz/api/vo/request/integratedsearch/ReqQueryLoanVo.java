package com.ymkj.cms.biz.api.vo.request.integratedsearch;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqQueryLoanVo</p>
 * <p>Description:查看借款请求对象</p>
 * @uthor YM10159
 * @date 2017年3月14日 上午10:14:02
 */
public class ReqQueryLoanVo extends Request{

	private static final long serialVersionUID = 5803514340977644072L;

	public ReqQueryLoanVo(){
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
	/**
	 * 身份证号
	 */
	private String idNum;

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
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
}
