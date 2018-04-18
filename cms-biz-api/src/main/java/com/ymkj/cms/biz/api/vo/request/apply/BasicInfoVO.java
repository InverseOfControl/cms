package com.ymkj.cms.biz.api.vo.request.apply;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class BasicInfoVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;
	
	private PersonInfoVO personInfoVO;         // 个人信息	
	private WorkInfoVO workInfoVO;         //工作信息	
	private PrivateOwnerInfoVO privateOwnerInfoVO;         //私营业主信息	
	
		
	 
	
		
		

	
	public PersonInfoVO getPersonInfoVO() {
		return personInfoVO;
	}
	public void setPersonInfoVO(PersonInfoVO personInfoVO) {
		this.personInfoVO = personInfoVO;
	}
	public WorkInfoVO getWorkInfoVO() {
		return workInfoVO;
	}
	public void setWorkInfoVO(WorkInfoVO workInfoVO) {
		this.workInfoVO = workInfoVO;
	}
	public PrivateOwnerInfoVO getPrivateOwnerInfoVO() {
		return privateOwnerInfoVO;
	}
	public void setPrivateOwnerInfoVO(PrivateOwnerInfoVO privateOwnerInfoVO) {
		this.privateOwnerInfoVO = privateOwnerInfoVO;
	}
	public BasicInfoVO(){
		
	}
	public BasicInfoVO(String sysCode){
		super.setSysCode(sysCode);
	}
	 
	 
}
