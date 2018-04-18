package com.ymkj.cms.biz.common.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

	public static final String default_pattern = "yyyy-MM-dd HH:mm:ss";
	public static final String default_pattern_minutes = "yyyyMMddHHmm";
	public static final String default_pattern_minutes_ss = "yyyyMMddHHmmss";
	public static final String default_pattern_ms = "yyyyMMddHHmmssSSS";
	public static final String default_pattern_day = "yyyy-MM-dd";
	public static final String pattern_day = "yyyyMMdd";
	public static final String default_pattern_hms = "HH:mm:ss";

	// 根据yyyyMMdd将date类型转化为string
	public static String defaultFormatYYYYMMDD(Date date){
		if (null == date)
			return null;
		return getDate(date, pattern_day);
	}
	// 根据yyyyMMddHHmmss将date类型转化为string
	public static String defaultFormatMss(Date date){
		if (null == date)
			return null;
		return getDate(date, default_pattern_minutes_ss);
	}
	// 根据HH:mm:ss将date类型转化为string
	public static String defaultFormatHMS(Date date) {
		if (null == date)
			return null;
		return getDate(date, default_pattern_hms);
	}

	// 根据yyyyMMddHHmmssSSS将date类型转化为string
	public static String defaultFormatMSDate(Date date) {
		if (null == date)
			return null;
		return getDate(date, default_pattern_ms);
	}

	// 根据yyyyMMddHHmm将date类型转化为string
	public static String defaultFormatMinutesDate(Date date) {
		if (null == date)
			return null;
		return getDate(date, default_pattern_minutes);
	}

	// 根据yyyy-MM-dd HH:mm:ss 将date类型转化为string
	public static String defaultFormatDate(Date date) {
		if (null == date)
			return null;
		return getDate(date, default_pattern);
	}
	// 根据yyyy-MM-dd将date类型转化为string
	public static String defaultFormatDay(Date date) {
		if (null == date)
			return null;
		return getDate(date, default_pattern_day);
	}
	public static String getDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 * @return formatStr
	 * @throws ParseException
	 */
	public static Date strToDate(String str, String formatStr) throws ParseException {
		if (formatStr == null) {
			formatStr = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(str);
	}

	/**
	 * 字符串转换成日期时间
	 * 
	 * @param str
	 * @return date
	 * @throws ParseException
	 */
	public static Date strToDateTimeDay(String str) throws ParseException {
		return strToDateTimeDay(str, null);
	}

	/**
	 * 字符串转换成日期时间
	 * 
	 * @param str
	 * @return date
	 * @return formatStr
	 * @throws ParseException
	 */
	public static Date strToDateTimeDay(String str, String formatStr) throws ParseException {
		if (formatStr == null) {
			formatStr = default_pattern_day;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(str);
	}

	/**
	 * 字符串转换成日期时间
	 * 
	 * @param str
	 * @return date
	 * @throws ParseException
	 */
	public static Date strToDateTime(String str) throws ParseException {
		return strToDateTime(str, null);
	}

	/**
	 * 字符串转换成日期时间
	 * 
	 * @param str
	 * @return date
	 * @return formatStr
	 * @throws ParseException
	 */
	public static Date strToDateTime(String str, String formatStr) throws ParseException {
		if (formatStr == null) {
			formatStr = default_pattern;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(str);
	}

	/**
	 * 
	 * 设置给定日期的时分秒
	 * 
	 * @param c
	 *            需要调整的日期
	 * @param hour
	 *            设定的小时值
	 * @param minute
	 *            设定的分钟值
	 * @param second
	 *            设定的秒值
	 * 
	 */
	public static void setHMS(Calendar c, int hour, int minute, int second) {
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
	}

	/**
	 * 
	 * 设置给定日期的时分秒毫秒
	 * 
	 * @param c
	 *            需要调整的日期
	 * @param hour
	 *            设定的小时值
	 * @param minute
	 *            设定的分钟值
	 * @param second
	 *            设定的秒值
	 * @param milliSecond
	 *            设定的毫秒秒值
	 */
	public static void setHMSM(Calendar c, int hour, int minute, int second, int milliSecond) {
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, milliSecond);
	}

	/**
	 * 
	 * <pre>
	 * 取得N天前的日期
	 * </pre>
	 * 
	 * @param days
	 * @return
	 */
	public static Date getDateBefore(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -days);
		setHMSM(c, 0, 0, 0, 0);
		return c.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 取得传入日期N天前的日期
	 * </pre>
	 * 
	 * @param days
	 * @return
	 */
	public static Date getDateBefore(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -days);
		setHMSM(c, 0, 0, 0, 0);
		return c.getTime();
	}
	/**
	 * 
	 * <pre>
	 * 取得N天后的日期
	 * </pre>
	 * 
	 * @param days
	 * @return
	 */
	public static Date getDateAfterDay(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		setHMSM(c, 0, 0, 0, 0);
		return c.getTime();
	}

	/**
	 * 取得某天前 x天、y小时、z分钟、s的日期
	 * 
	 * @param date
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getDateBefore(Date date, int day, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -day);
		c.add(Calendar.HOUR_OF_DAY, -hour);
		c.add(Calendar.MINUTE, -minute);
		c.add(Calendar.SECOND, -second);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 取得当天日期
	 * </pre>
	 * 
	 * @return
	 */
	public static Date getToday() {
		Calendar c = Calendar.getInstance();
		setHMSM(c, 0, 0, 0, 0);
		return c.getTime();
	}
	/**
	 * 
	 * <pre>
	 * 取得当前日期的23:59:59
	 * </pre>
	 * 
	 * @return
	 */
	public static Date getEndTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);	
		return c.getTime();
	}
	/**
	 * 
	 * <pre>
	 * 取得当前给定日期的23:59:59
	 * </pre>
	 * 
	 * @return
	 */
	public static Date getEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);	
		return c.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 取得当天日期
	 * </pre>
	 * 
	 * @return
	 */
	public static Date getTodayHHmmss() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 取得明天日期
	 * </pre>
	 * 
	 * @return
	 */
	public static Date getTomorrow() {
		return getDateBefore(-1);
	}

	/**
	 * 时间转换
	 * 
	 * @param d
	 * @return
	 */
	public static Date addHour(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.HOUR, 23);
		c.add(Calendar.MINUTE, 59);
		c.add(Calendar.SECOND, 59);
		return c.getTime();
	}

	public static boolean isSameYearMonthDay(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		if (year1 == year2 && month1 == month2 && day1 == day2) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 获得当月天数
	 */
	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	/*
	 * 获得传入时间的当月天数
	 */
	public static int getDayOfMonthByFixedDate(Date contractCreatedDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(contractCreatedDate);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getNowDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DATE);
	}
	public static int getNowDayByFixedDate(Date contractCreatedDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(contractCreatedDate);
		return cal.get(Calendar.DATE);
	}

	/**
	 *
	 * <pre>
	 * 取得N月后的日期
	 * </pre>
	 *
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(int months) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, months);
		setHMSM(c, 0, 0, 0, 0);
		return c.getTime();
	}

	/**
	 *
	 * <pre>
	 * 取得当月1日
	 * </pre>
	 *
	 * @return
	 */
	public static Date getMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		setHMSM(c, 0, 0, 0, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * 
	 * <pre>
	 * 取得当月16日
	 * </pre>
	 *
	 * @return
	 */
	public static Date getMonthSixteenDay() {
		Calendar c = Calendar.getInstance();
		setHMSM(c, 0, 0, 0, 0);
		c.set(Calendar.DAY_OF_MONTH, 16);
		return c.getTime();
	}

	/**
	 *
	 * <pre>
	 * 取得下个月1日
	 * </pre>
	 *
	 * @return
	 */
	public static Date getNextMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		setHMSM(c, 0, 0, 0, 0);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 *
	 * <pre>
	 * 取得下个月16日
	 * </pre>
	 *
	 * @return
	 */
	public static Date getNextMonthSixteenDay() {
		Calendar c = Calendar.getInstance();
		setHMSM(c, 0, 0, 0, 0);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 16);
		return c.getTime();
	}

	/**
	 *
	 * <pre>
	 * 根据传入日期,取得N月后的当天日期
	 * </pre>
	 *
	 * @param days
	 * @return
	 */
	public static Date getNowDateAfter(int months, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		setHMSM(c, 0, 0, 0, 0);
		return c.getTime();
	}

	/**
	 * 截取日期yyyy-mm-dd
	 * @param date
	 * @return
	 */
	public static Date formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			return date;
		}
	}

	/**
	 * 获取日期的day
	 * @param date
	 * @return
	 */
	public static int formatGetDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 * 增加时间
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date add(Date date, Integer field, Integer amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * 根据传入的日期得到天
	 * </pre>
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		return dayOfMonth;
	}

	/**
	 *
	 * <pre>
	 * 根据传入日期,取得N月或者是当月的某天日期
	 * </pre>
	 *
	 * @param days
	 * @return
	 */
	public static Date getDateAfterMonth(Date date, int months, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (months > 0) {
			c.add(Calendar.MONTH, months);
		}
		c.set(Calendar.DAY_OF_MONTH, days);
		setHMSM(c, 0, 0, 0, 0);
		return c.getTime();
	}

	/**
	 * <pre>
	 * 通过传入结束日期和开始日期获取天数差
	 * </pre>
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDiffDay(Date startDate, Date endDate) {
		long day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	public static Date getNextRepayDateIncludeToday(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		if (cal.get(Calendar.DAY_OF_MONTH) > 16) {
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else if (cal.get(Calendar.DAY_OF_MONTH) > 1) {
			cal.set(Calendar.DAY_OF_MONTH, 16);
		} else {
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		return cal.getTime();
	}

	public static Date getNextRepayDate(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		if (cal.get(Calendar.DAY_OF_MONTH) >= 16) {
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			cal.set(Calendar.DAY_OF_MONTH, 16);
		}
		return cal.getTime();
	}

	/** 
	 * 判断日期有效性
	 * 格式:yyyy-mm-dd 
	 *  
	 * @param sDate 
	 * @return 
	 */  
	public static boolean isValidDate(String sDate) {
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(sDate);
		return m.matches();
	}

	/*
	 * 获得当前年
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	/** 
	 *  
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度 
	 *  
	 * @param date 
	 * @return 
	 */  
	public static int getSeason(Date date) {  

		int season = 0;  

		Calendar c = Calendar.getInstance();  
		c.setTime(date);  
		int month = c.get(Calendar.MONTH);  
		switch (month) {  
		case Calendar.JANUARY:  
		case Calendar.FEBRUARY:  
		case Calendar.MARCH:  
			season = 1;  
			break;  
		case Calendar.APRIL:  
		case Calendar.MAY:  
		case Calendar.JUNE:  
			season = 2;  
			break;  
		case Calendar.JULY:  
		case Calendar.AUGUST:  
		case Calendar.SEPTEMBER:  
			season = 3;  
			break;  
		case Calendar.OCTOBER:  
		case Calendar.NOVEMBER:  
		case Calendar.DECEMBER:  
			season = 4;  
			break;  
		default:  
			break;  
		}  
		return season;  
	}
	/** 
	 * 某季度的开始时间
	 * 
	 * @return 
	 */ 
	public  static Date getQuarterStartTime(int year ,int quarter) { 
		Calendar c = Calendar.getInstance(); 

		SimpleDateFormat longSdf =  new SimpleDateFormat("yyyy-MM-dd"); 
		c.set(Calendar.YEAR, year); 
		Date now = null; 
		try { 
			if (quarter == 1  ) 
				c.set(Calendar.MONTH, 2); 
			else if (quarter == 2  ) 
				c.set(Calendar.MONTH, 5); 
			else if (quarter == 3  ) 
				c.set(Calendar.MONTH, 8); 
			else if (quarter == 4  ) 
				c.set(Calendar.MONTH, 11); 
			c.set(Calendar.DATE, 1); 
			now = longSdf.parse(longSdf.format(c.getTime())   ); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return now; 
	} 
	/** 
	 * 某年季度的最后一天
	 * 
	 * @return 
	 */ 
	public static Date getLastDayOfQuarter(int year ,int  quarter)   {     
		Calendar cDay = Calendar.getInstance();     
		cDay.set(Calendar.YEAR, year); 
		if (quarter == 1  ) 
			cDay.set(Calendar.MONTH, Calendar.MARCH);  
		else if (quarter == 2  ) 
			cDay.set(Calendar.MONTH, Calendar.JUNE);  
		else if (quarter == 3  ) 
			cDay.set(Calendar.MONTH, Calendar.AUGUST);  
		else if (quarter == 4  )  
			cDay.set(Calendar.MONTH, Calendar.DECEMBER);  
		cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return cDay.getTime();     
	}  
	/** 
	 * 上个月的截至日期
	 * 
	 * @return 
	 */ 
	public static Date getLastMonthDay(Date date)   {     
		Calendar cDay = Calendar.getInstance();     
		cDay.setTime(date); 
		cDay.set(Calendar.MONTH,cDay.get(Calendar.MONTH)-1);
		cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return cDay.getTime();     
	}  

	/**
	 * 切割时间段
	 *
	 * @param dateType 交易类型 M/D/H/N -->每月/每天/每小时/每分钟
	 * @param start yyyy-MM-dd HH:mm:ss
	 * @param end  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static List<String> cutDate(String dateType, String start, String end) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dBegin = sdf.parse(start);
			Date dEnd = sdf.parse(end);
			return findDates(dateType, dBegin, dEnd);
		} catch (Exception e) {
		}
		return null;
	}

	public static List<String> findDates(String dateType, Date dBegin, Date dEnd) throws Exception {
		List<String> listDate = new ArrayList<>();
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (calEnd.after(calBegin)) {
			switch (dateType) {
			case "M":
				calBegin.add(Calendar.MONTH, 1);
				break;
			case "D":
				calBegin.add(Calendar.DAY_OF_YEAR, 1);break;
			case "H":
				calBegin.add(Calendar.HOUR, 1);break;
			case "N":
				calBegin.add(Calendar.SECOND, 1);break;
			}
			if (calEnd.after(calBegin))
				listDate.add(new SimpleDateFormat("yyyy-MM-dd").format(calBegin.getTime()));
			else
				listDate.add(new SimpleDateFormat("yyyy-MM-dd").format(calEnd.getTime()));
		}
		return listDate;
	}

	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	public static void main(String argsp[]){
		System.out.println(DateUtil.getNowDateAfter(12,new Date()));
	}
}
