package com.ymkj.cms.web.boss.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * easui中的tree_data.json数据,只能有一个root节点
 * [{   
    "code":1,   
    "name":"parent",   
    "url":"",   
    "hasChildren":true
    "children":[{   
    	"code":101,
        "name":"children",   
        "url":"url",
        "hasChildren":false
    }]   
}] 
 * 提供静态方法formatMenuTree(List<TreeJson> list) 返回结果
 * TreeUtil.formatMenuTree(treeJsonlist) ;
 * @author lix
 *
 */
public class MenuTreeJson implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 资源编码
     */
    private String code ; 
    /**
     * 资源父编码
     */
    private String parentCode ; 
    /**
     * 资源名称
     */
    private String name ; 
    /**
     * 系统编码
     */
    private String systemCode ;
    /**
     * 资源类型
     */
    private String resType ; 
    /**
     * 资源描述
     */
    private String memo ;
    /**
     * 链接地址
     */
    private String url ;
    /**
     * 图标
     */
    private String icon ;
    /**
     * 是否展示(0: 能, 1:不 能)
     */
    private String isDisplay ;
    /**
     * 打开方式
     */
    private String openMode ;
    /**
     * 深度 0.根 1.一级，2.二级....
     */
    private String deep;
    /**
     * 是否含有子节点,true有，false无
     */
    private boolean hasChildren;
    private JSONObject attributes = new JSONObject() ; 
    private List<MenuTreeJson> children = new ArrayList<MenuTreeJson>() ;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	public String getOpenMode() {
		return openMode;
	}
	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}
	public String getDeep() {
		return deep;
	}
	public void setDeep(String deep) {
		this.deep = deep;
	}
	public JSONObject getAttributes() {
		return attributes;
	}
	public void setAttributes(JSONObject attributes) {
		this.attributes = attributes;
	}
	public List<MenuTreeJson> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeJson> children) {
		this.children = children;
	}
	public boolean isHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
    
}
