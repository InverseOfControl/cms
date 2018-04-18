package com.ymkj.cms.biz.entity.apply;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class APPPersonEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * @Null 被注释的元素必须为 null
	 * @NotNull 被注释的元素必须不为 null
	 * @AssertTrue 被注释的元素必须为 true
	 * @AssertFalse 被注释的元素必须为 false
	 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
	 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
	 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
	 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值 @Size(max=, min=)
	 *                    被注释的元素的大小必须在指定的范围内
	 * @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
	 * @Past 被注释的元素必须是一个过去的日期
	 * @Future 被注释的元素必须是一个将来的日期 
	 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
	 * @NotBlank(message =) 验证字符串非null，且长度必须大于0
	 * @Email 被注释的元素必须是电子邮箱地址 @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
	 * @NotEmpty 被注释的字符串的必须非空 @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
	 * 
	 */
	private Long id;
	
	@NotNull(message = "000001, BasicInfoVO.personInfoVO不能为空")
	private String name; // 名称
	
	private String perosnNo;
	
	
//	@Pattern(regexp = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$", message = "000003,参数不合法")
	@NotNull(message = "000001, BasicInfoVO.personInfoVO不能为空")
	private String idNo;
	
	private String idType ; 
	
	private Long creatorId ; 
	
	private String creator ; 
	
	private Date createdTime ; 
	
	private Long modifierId ; 
	
	private String modifier ; 
	
	private Date modifiedTime ; 
	
	private Integer isDelete ; 
	
	private Integer verson ;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerosnNo() {
		return perosnNo;
	}

	public void setPerosnNo(String perosnNo) {
		this.perosnNo = perosnNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
