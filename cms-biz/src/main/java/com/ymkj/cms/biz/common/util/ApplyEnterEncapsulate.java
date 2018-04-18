package com.ymkj.cms.biz.common.util;

import java.util.Date;
import java.util.Random;

/**
 * 入单使用的静态方法
 * @author YM10152
 *
 */
public class ApplyEnterEncapsulate {
	
	/**
	 * 生成	借款编号
	 * 8位创建日期+6位	16进制随机生成数
	 * @param date
	 * @return
	 */
	public static String createAppNo(Date date){
		String dateStr = DateUtil.defaultFormatYYYYMMDD(date);
		return dateStr + randomHexString(6);
	} 
	
	
	
	/**
	 * 生成	客户编号
	 * 8位创建日期+6位创建时分秒+6位随机数；
	 * @param date
	 * @return
	 */
	public static String createPersonNo(Date date){
		String appNo = DateUtil.defaultFormatMss(date);
		return appNo + random();
	}

	
	/**
	 * 随机生成六位数
	 * @return
	 */
	public static String random() {
		int count = 6;
        char start = '0';
        char end = '9';
 
        Random rnd = new Random();
 
        char[] result = new char[count];
        int len = end - start + 1;
 
        while (count-- > 0) {
            result[count] = (char) (rnd.nextInt(len) + start);
        }
        return new String(result);
	}
	
	/**
	 * 生成指定范围的随机数
	 * @return
	 */
	public static int getRandomNumber(int min,int max){
		if(min == max){
			return 0;
		}
		Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
	
	/** 
     * 获取16进制随机数 
     * @param len 长度
     * @return 
     * @throws CoderException 
     */  
    public static String randomHexString(int len)  {  
    	
        try {  
            StringBuffer result = new StringBuffer();  
            for(int i=0;i<len;i++) {  
                result.append(Integer.toHexString(new Random().nextInt(16)));  
            }  
            return result.toString().toUpperCase();  
        } catch (Exception e) {  
            return null;
        }  
          
    }
	
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			System.out.println(i+"====="+getRandomNumber(0, i));
		}
		
	}
}
