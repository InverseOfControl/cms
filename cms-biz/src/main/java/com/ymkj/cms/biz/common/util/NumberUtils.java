package com.ymkj.cms.biz.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数字处理类
 * @author 李璇
 * @date 2017年3月14日 下午6:23:09
 */
public class NumberUtils {
	/**
	 * null判断，null返回指定进度string，如：0.00
	 * @param num
	 * @param digits
	 * @return
	 */
	public static Object isNull(Object num, int digits) {
		if (num == null) {
			return format(0D,digits);
		}
		return num;
	}
	/**
	 * 格式化精度，返回string
	 * @param num
	 * @param digits
	 * @return
	 */
	public static String format(Double num, int digits) {
		String fm = "#0";
		if(digits>0) fm += ".";
		for (int i = 0; i < digits; i++) {
			fm += "0";
		}
		DecimalFormat df = new DecimalFormat(fm);
		return df.format(num);
	}
}
