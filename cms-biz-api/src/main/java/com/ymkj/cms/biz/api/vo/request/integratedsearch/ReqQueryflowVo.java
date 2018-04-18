package com.ymkj.cms.biz.api.vo.request.integratedsearch;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ymkj.base.core.biz.api.message.Request;
import com.ymkj.cms.biz.api.enums.EnumConstants;

/**
 * @company:上海郁敏网络科技有限公司
 * <p>Title:ReqQueryflowVo</p>
 * <p>Description:帐卡信息接口请求对象</p>
 * @uthor YM10159
 * @date 2017年3月15日 上午9:52:16
 */
public class ReqQueryflowVo extends Request{

	private static final long serialVersionUID = 6951544260934388599L;

	public ReqQueryflowVo(){
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
	private String loanNo;
	
	/**
	 * 每页显示条数
	 */
	private long max;
	
	/**
	 * 显示指定页数
	 */
	private long offset;

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

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}
	
}
