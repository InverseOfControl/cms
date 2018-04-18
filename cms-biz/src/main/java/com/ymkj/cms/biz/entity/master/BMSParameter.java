package com.ymkj.cms.biz.entity.master;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class BMSParameter extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3826646074452923105L;
	  private Long id ;       	
	  private String code ;       		 
	 
	private String name     ;            
	  private String parameterValue    ;  
	  private Integer inputType    ;      
	  private String remark;               
	
	  
		private String creator ; 
		
		private Date createdTime ; 
		
		private Long modifierId ; 
		
		private String modifier ; 
		
		private Date modifiedTime ; 
		
		private Integer isDelete ; 
		
		private Integer verson ;
		
		 public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
	  
	public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public Date getCreatedTime() {
			return createdTime;
		}
		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}
		public Long getModifierId() {
			return modifierId;
		}
		public void setModifierId(Long modifierId) {
			this.modifierId = modifierId;
		}
		public String getModifier() {
			return modifier;
		}
		public void setModifier(String modifier) {
			this.modifier = modifier;
		}
		public Date getModifiedTime() {
			return modifiedTime;
		}
		public void setModifiedTime(Date modifiedTime) {
			this.modifiedTime = modifiedTime;
		}
		public Integer getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}
		public Integer getVerson() {
			return verson;
		}
		public void setVerson(Integer verson) {
			this.verson = verson;
		}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public Integer getInputType() {
		return inputType;
	}
	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
}
