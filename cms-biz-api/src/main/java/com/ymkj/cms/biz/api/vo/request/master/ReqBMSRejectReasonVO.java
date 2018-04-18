package com.ymkj.cms.biz.api.vo.request.master;

import java.math.BigDecimal;
import java.util.Date;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 申请录入请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 
 *  
 * @author haowp
 *
 */
public class ReqBMSRejectReasonVO extends Request{
	/**
	 * 必须实现 序列化, 自动生成序列化值
	 */
	private static final long serialVersionUID = 179926471785725311L;

	private String type; // 原因类型类型
	private Long parentId ; // 上级原因id
 
	 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public ReqBMSRejectReasonVO(){	
	}
	public ReqBMSRejectReasonVO(String sysCode){
		super.setSysCode(sysCode);
	}
		
}
