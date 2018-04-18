package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class ResBMSBaseAreaTreeVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7203004168082795745L;
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
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建人ID
	 */
	private Long creatorId;
	/**
	 * 创建时间
	 */
	private Date creatorDate;
	/**
	 * 修改人
	 */
	private String modified;
	/**
	 * 修改人Id
	 */
	private Long modifiedId;
	/**
	 * 修改时间
	 */
	private Date modifiedDate;
	
	/**
	 * 子集合对象
	 */
	private List<ResBMSBaseAreaTreeVO> children = new ArrayList<ResBMSBaseAreaTreeVO>();
	
	
	
	
	public List<ResBMSBaseAreaTreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<ResBMSBaseAreaTreeVO> children) {
		this.children = children;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreatorDate() {
		return creatorDate;
	}
	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Long getModifiedId() {
		return modifiedId;
	}
	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	
	public void sortChildren(){
		Collections.sort(children, new Comparator<ResBMSBaseAreaTreeVO>() {
			public int compare(ResBMSBaseAreaTreeVO o1, ResBMSBaseAreaTreeVO o2) {
				if(o1.getAreaId()>o2.getAreaId()){
					return 1;
				}else{
					return -1;
				}
			}
		});
		// 对每个节点的下一层节点进行排序
	    for (Iterator<ResBMSBaseAreaTreeVO> it = children.iterator(); it.hasNext();) {
	    	it.next().sortChildren();
	    }
	}
	
}
