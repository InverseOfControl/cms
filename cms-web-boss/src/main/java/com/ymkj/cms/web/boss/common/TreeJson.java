package com.ymkj.cms.web.boss.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
/**
 * easui中的tree_data.json数据,只能有一个root节点
 * [{   
    "id":1,   
    "text":"Folder1",   
    "iconCls":"icon-save",   
    "children":[{   
        "text":"File1",   
        "checked":true  
    }]   
}] 
 * 提供静态方法formatChannelProductTree(List<TreeJson> list) 返回结果
 * TreeJson.formatChannelProductTree(treeJsonlist) ;
 * @author cj
 *
 */
public class TreeJson implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id ; 
    private String pid ; 
    private String text ; 
    private String iconCls ;
    private String state ; 
    private String checked ;
    //深度 1.根 2.一级，3.二级
    private String deep;
    private JSONObject attributes = new JSONObject() ; 
    private List<TreeJson> children = new ArrayList<TreeJson>() ;
    
	public String getDeep() {
		return deep;
	}
	public void setDeep(String deep) {
		this.deep = deep;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public JSONObject getAttributes() {
		return attributes;
	}
	public void setAttributes(JSONObject attributes) {
		this.attributes = attributes;
	}
	public List<TreeJson> getChildren() {
		return children;
	}
	public void setChildren(List<TreeJson> children) {
		this.children = children;
	}
    
    
}
