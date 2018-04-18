package com.ymkj.cms.biz.entity.apply;

import java.util.List;
import java.util.Map;

import com.ymkj.base.core.biz.entity.BaseEntity;

public class ThirdPartyInfoListDateEntity  extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Map<String, ThirdPartyInfoDateEntity>> myArrayList;


	public List<Map<String, ThirdPartyInfoDateEntity>> getMyArrayList() {
		return myArrayList;
	}


	public void setMyArrayList(List<Map<String, ThirdPartyInfoDateEntity>> myArrayList) {
		this.myArrayList = myArrayList;
	}

}
