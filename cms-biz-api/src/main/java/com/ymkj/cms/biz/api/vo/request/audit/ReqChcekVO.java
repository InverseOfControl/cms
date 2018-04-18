package com.ymkj.cms.biz.api.vo.request.audit;



import com.ymkj.base.core.biz.api.message.Request;

public class ReqChcekVO extends Request{

	/**
	 * 实现自动序列化
	 */
	private static final long serialVersionUID = 7589229584687013588L;
	
	private String loginUserCode;//登录人编码
	private int pageNum;//当前页数
	private int pageSize;//分页条数
	/**
	 * 排序字段
	 */
	private String fieldSort;
	/**
	 * 排序规则
	 */
	private int rulesSort;//0 倒序  1正序  其他 顺序
	public String getLoginUserCode() {
		return loginUserCode;
	}

	public void setLoginUserCode(String loginUserCode) {
		this.loginUserCode = loginUserCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getFieldSort() {
		return fieldSort;
	}

	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}

	public int getRulesSort() {
		return rulesSort;
	}

	public void setRulesSort(int rulesSort) {
		this.rulesSort = rulesSort;
	}
	
}
