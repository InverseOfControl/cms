package com.ymkj.cms.biz.test;

import org.jboss.netty.util.internal.StringUtil;

import com.ymkj.cms.biz.common.util.StringUtils;

public class BankNumberUtil{  
    
    private static int i = 0;  
    /** 
     * 需要传入一个前缀：6、8、9中的一个。 
     * 其中：6：类型1，8：类型2，9：类型3 【<span style="font-family: Arial, Helvetica, sans-serif;">根据自己的业务定义</span>】 
     * 其他则会返回异常 
     * @param prefix 
     * @return 
     */  
    public synchronized static String getBrankNumber(String bankCode)  {
    	String prefix= getBankAccountCode(bankCode);
    	Integer length= getBankAccountLength(bankCode);
        if(prefix != null && prefix.length()>0){  
        	String sta = getUnixTime(bankCode);
            String st = prefix+sta;
            return st+getBankCardCheckCode(st);  
        }else{  
            System.out.println("银行卡号去前缀不能是空");  
        } 
        return "";
    }  
      
    

	/*** 
     * 获取当前系统时间戳 并截取  
     * @return 
     */  
    private synchronized static String getUnixTime(String bankCode){  
        try {  
            Thread.sleep(10);//线程同步执行，休眠10毫秒 防止卡号重复  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        i++;i=i>100?i%10:i;  
        
        String unixTime = "";
		switch (bankCode) {
		case "102":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "103":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "105":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "0301":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "104":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(1)+(i%10);
			break;
		case "303":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "305":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "0306":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "0308":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "0410":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "302":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "304":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "309":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "310":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "315":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "316":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		case "307":
			unixTime = ((System.currentTimeMillis()/100)+"").substring(0)+(i%10);
			break;
		default:
		}     
		return unixTime;
    }  
      
    /** 
     * 校验银行卡卡号 
     * @param cardId 
     * @return 
     */  
    public static boolean checkBankCard(String cardId) {  
         char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));  
         if(bit == 'N'){  
             return false;  
         }  
         return cardId.charAt(cardId.length() - 1) == bit;  
    }  
      
    /** 
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
     * @param nonCheckCodeCardId 
     * @return 
     */  
    public static char getBankCardCheckCode(String nonCheckCodeCardId){  
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0  
                || !nonCheckCodeCardId.matches("\\d+")) {  
            //如果传的不是数据返回N  
            return 'N';  
        }  
        char[] chs = nonCheckCodeCardId.trim().toCharArray();  
        int luhmSum = 0;  
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {  
            int k = chs[i] - '0';  
            if(j % 2 == 0) {  
                k *= 2;  
                k = k / 10 + k % 10;  
            }  
            luhmSum += k;             
        }  
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');  
    }  
    public static String getBankAccountCode(String bankCode){  
		String prefix = "";
		switch (bankCode) {
		case "102":
			prefix = "622202";
			break;
		case "103":
			prefix = "622848";
			break;
		case "105":
			prefix = "622700";
			break;
		case "0301":
			prefix = "622262";
			break;
		case "104":
			prefix = "6216613";
			break;
		case "303":
			prefix = "622663";
			break;
		case "305":
			prefix = "622617";
			break;
		case "0306":
			prefix = "622556";
			break;
		case "0308":
			prefix = "622588";
			break;
		case "0410":
			prefix = "622155";
			break;
		case "302":
			prefix = "620082";
			break;
		case "304":
			prefix = "622630";
			break;
		case "309":
			prefix = "622908";
			break;
		case "310":
			prefix = "622517";
			break;
		case "315":
			prefix = "622323";
			break;
		case "316":
			prefix = "622309";
			break;
		case "307":
			prefix = "621626";
			break;
		default:
		}     
		return prefix;
    }
    private static Integer getBankAccountLength(String bankCode) {
    	Integer length = 0;
		switch (bankCode) {
		case "102":
			length = 19;
			break;
		case "103":
			length = 19;
			break;
		case "105":
			length = 19;
			break;
		case "301":
			length = 19;
			break;
		case "104":
			length = 19;
			break;
		case "303":
			length = 16;
			break;
		case "305":
			length = 16;
			break;
		case "0306":
			length = 16;
			break;
		case "0308":
			length = 16;
			break;
		case "0410":
			length = 19;
			break;
		case "302":
			length = 16;
			break;
		case "304":
			length = 19;
			break;
		case "309":
			length = 19;
			break;
		case "310":
			length = 16;
			break;
		case "315":
			length = 16;
			break;
		case "316":
			length = 16;
			break;
		case "307":
			length = 19;
			break;
		default:
		}     
		return length;
	}
    
    
    public static void main(String[] args) {  
        try {  
        	for (int i = 0; i < 20; i++) {
        		Thread.sleep(100);//线程同步执行，休眠10毫秒 防止卡号重复  
//        		System.out.println(getBrankNumber("104"));//中国银行  
//        		System.out.println(getBrankNumber("102"));//工商银行
        		System.out.println(getBrankNumber("307"));//平安银行
			}
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  