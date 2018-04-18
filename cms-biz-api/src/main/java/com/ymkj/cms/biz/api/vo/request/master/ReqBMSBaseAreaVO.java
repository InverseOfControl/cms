package com.ymkj.cms.biz.api.vo.request.master;

import com.ymkj.base.core.biz.api.message.Request;
/**
 * 1.demo请求vo, 属性值应包含 所有可能查询用到的字段, 注解清晰
 * 2.其中必须包含 对应实体类 {Entity}中的 所有字段, 业务不同实体不同  
 * 3.如有需要可 自行扩展
 * @author 
 *
 */
public class ReqBMSBaseAreaVO extends Request{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4542256589295999243L;

	/**
	 * 地区ID
	 */
	private Long areaId;
	/**
	 * 地区名称
	 */
	private String name;
	/**
	 * 地区编码
	 */
	private String code;
	/**
	 * 父ID
	 */
	private Long parentId;
	/**
	 * 深度
	 */
	private Long deep;
	/**
	 * 版本号
	 */
	private Long version;
	/**
	 * 是否删除
	 */
	private Long isDeleted;
	
	
	
	private int pageNum;     // 当前页数
	private int pageSize;
	// 每页记录数
	//txt文件路径
	private String upload;
	
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public ReqBMSBaseAreaVO(){
		
	}
	public ReqBMSBaseAreaVO(String sysCode){
		super.setSysCode(sysCode);
	}
	
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getDeep() {
		return deep;
	}
	public void setDeep(Long deep) {
		this.deep = deep;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int numPerPage) {
		this.pageSize = numPerPage;
	}
}
