package com.ymkj.cms.biz.entity.apply;

import javax.validation.constraints.NotNull;

import com.ymkj.base.core.biz.entity.BaseEntity;

/**
 * 实体类 对应 表 demo
 */
public class DemoEntity extends BaseEntity {

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
	
	private Long id;            // 主键ID
	
	@NotNull(message = "000001,名字不能为空")
	private String name;        // 名称

	@NotNull(message = "000001,地址不能为空")
	private String address;     // 地址
 
	private int status;         // 审核状态

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
