package com.ymkj.cms.biz.api.vo.request.channel;

import java.io.Serializable;

import com.ymkj.base.core.biz.api.message.Request;

public class ReqWMXTDataVo extends Request{
	//营业部ID
	private String id;
	//营业部父节点ID
	private String parent_Id;
	//渤海信托机构代码
	private String code;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent_Id() {
		return parent_Id;
	}
	public void setParent_Id(String parent_Id) {
		this.parent_Id = parent_Id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
