package com.ymkj.cms.biz.api.vo.response.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Res_VO_600013 implements Serializable{
	private String userCode;//员工工号
	private Integer totalNo;//总条数 
	//申请件选择列表
	private List<Map<String,Object>> appInputListData  =new ArrayList<Map<String,Object>>();
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getTotalNo() {
		return totalNo;
	}
	public void setTotalNo(Integer totalNo) {
		this.totalNo = totalNo;
	}
	public List<Map<String, Object>> getAppInputListData() {
		return appInputListData;
	}
	public void setAppInputListData(List<Map<String, Object>> appInputListData) {
		this.appInputListData = appInputListData;
	}
	

}
