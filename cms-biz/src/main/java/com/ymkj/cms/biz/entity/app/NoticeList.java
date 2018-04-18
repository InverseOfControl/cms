package com.ymkj.cms.biz.entity.app;

import java.util.Date;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class NoticeList extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2443971187196857398L;
	private String	title; //公告标题
	private String	content;//公告内容
	private String createTime;//公告时间
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
