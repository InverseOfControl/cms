package com.ymkj.cms.biz.service.app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

public class AppApplyUtil {
	
	public static String getBirthDayByIdNo(String idNo){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isBlank(idNo)) return null;

		int year = Integer.parseInt(idNo.substring(6, 10));
		int month = Integer.parseInt(idNo.substring(10, 12));
		int date = Integer.parseInt(idNo.substring(12, 14));

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, date);
		return df.format(cal.getTime());
	}

	public static Integer getAgeByIdNo(String idNo){
		if(StringUtils.isBlank(idNo)) return 0;

		int year = Integer.parseInt(idNo.substring(6, 10));

		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);

		return (currYear-year);
	}

	public static String getGenderByIdNo(String idNo){
		String sex = idNo.substring(16, 17);
		if(Integer.parseInt(sex)%2==0){
			return "女";
		}else{
			return "男";
		}
	}
	
	public static String[] convertAreaCode(String areaCode,List<Map<String,Object>> areaList){
		String[] areaNames = new String[3];
		for (int i = 0; i < areaNames.length; i++) {
			areaNames[i] = ObjectUtils.toString(areaNames[i]);
		}
		
		for (int i = 0; i < areaList.size(); i++) {
			areaNames[i] = ObjectUtils.toString(areaList.get(i).get("name"));
		}
		
		return areaNames;
	}
}
