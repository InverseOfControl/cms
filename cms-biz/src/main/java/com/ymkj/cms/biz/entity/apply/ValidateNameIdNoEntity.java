package com.ymkj.cms.biz.entity.apply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class ValidateNameIdNoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	 
 
	@NotNull(message = "000001,不能为空")  
	private String name; //申请客户姓名
	@NotNull(message = "000001,不能为空")  
	private String idNo; //申请客户身份证
	@NotNull(message = "000001,不能为空")  
	private Long owningBranchId;//录入门店ID
	@NotNull(message = "000001,不能为空")  
	private String owningBranch;//录入门店
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Long getOwningBranchId() {
		return owningBranchId;
	}
	public void setOwningBranchId(Long owningBranchId) {
		this.owningBranchId = owningBranchId;
	}
	public String getOwningBranch() {
		return owningBranch;
	}
	public void setOwningBranch(String owningBranch) {
		this.owningBranch = owningBranch;
	}
	
	
	

}
