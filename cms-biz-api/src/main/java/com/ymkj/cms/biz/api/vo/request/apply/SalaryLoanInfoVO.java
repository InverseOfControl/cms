package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class SalaryLoanInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private Long  id;
	private String conditionType; //条件类型
 
	private Long ifEmpty = 0L;//对象是否是否为空  0是  1不是
	
	
	public Long getIfEmpty() {
		return ifEmpty;
	}
	public void setIfEmpty(Long ifEmpty) {
		this.ifEmpty = ifEmpty;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public SalaryLoanInfoVO(){	
	}
	public SalaryLoanInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
