package com.ymkj.cms.biz.common.util;

import java.util.Comparator;

import com.ymkj.cms.biz.entity.audit.BMSFirstAuditEntity;

public class SortClassUtil implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		BMSFirstAuditEntity firstAuditEntity1=(BMSFirstAuditEntity) o1;
		BMSFirstAuditEntity firstAuditEntity2=(BMSFirstAuditEntity) o2;
		 int flag = firstAuditEntity2.getOperationTime().compareTo(firstAuditEntity1.getOperationTime());  
	        return flag;  
	}

}
