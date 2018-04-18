package com.ymkj.cms.biz.common.util;


import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
/**
 * 字符串工具类
 * @author YM10161
 *
 */
public class StringUtils {

	/**
	 * 正则表达式：验证用户名
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	/**
	 * 将MAP以分隔符分隔参数字符串
	 * @param paraMap
	 * @param splitSignStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String Map2StrBySplitStr(Map<String,Object> paraMap,String splitSignStr){
		StringBuffer stringBuffer =new StringBuffer();
		Set retSet=	paraMap.keySet();
		int i=0;
		for (Object paramKey : retSet) {
			if(i != 0){
				stringBuffer.append(splitSignStr);
			}
			i++;
			stringBuffer.append(paramKey);
			stringBuffer.append("=");
			Object param =paraMap.get(paramKey);
			stringBuffer.append(param);	
		}	
		return stringBuffer.toString();	
	}

	/**
	 * 将List转换成以分隔符分隔
	 * @param paraMap
	 * @param splitSignStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String List2StrBySplitStr(List paraList,String splitSignStr){
		StringBuffer stringBuffer =new StringBuffer();
		int i=0;
		for (Object param : paraList) {
			if(i != 0){
				stringBuffer.append(splitSignStr);
			}
			i++;
			stringBuffer.append(param);
		}	
		return stringBuffer.toString();	
	}

	/**
	 * 将List转换成以分隔符分隔
	 * @param paraMap
	 * @param splitSignStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String List2StrBySplitStrSE(List paraList,String splitSignStr){
		StringBuffer stringBuffer =new StringBuffer();
		stringBuffer.append("[");
		int i=0;
		for (Object param : paraList) {
			if(i != 0){
				stringBuffer.append(splitSignStr);
			}
			i++;
			stringBuffer.append(param);
		}	
		stringBuffer.append("]");
		return stringBuffer.toString();	
	}
	
	/**
	 * 字符串的正则匹配
	 * 
	 * @param test
	 *            正则表达试
	 * @param str
	 *            比较字符串
	 * @return
	 */
	public static boolean pattern(String test, String str) {
		Pattern p = Pattern.compile(test);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * @param str 要检查的字符串
	 *
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	/**
	 * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
	 * <pre>
	 * StringUtil.isEmpty(null)      = false
	 * StringUtil.isEmpty("")        = false
	 * StringUtil.isEmpty(" ")       = true
	 * StringUtil.isEmpty("bob")     = true
	 * StringUtil.isEmpty("  bob  ") = true
	 * </pre>
	 *
	 * @param str 要检查的字符串
	 *
	 * @return 如果不为空, 则返回<code>true</code>
	 */
	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	/**
	 * 获取字符串的长度
	 * 
	 * @param str
	 * @return
	 */
	public static int strLength(String str) {
		return str.length();
	}

	/**
	 * 判断是否全为数字,全为返回true,否则返回false
	 * @param str
	 * @return
	 */
	public static boolean checkInt(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * @param str 要检查的字符串
	 *
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * StringUtil.isBlank(null)      = false
	 * StringUtil.isBlank("")        = false
	 * StringUtil.isBlank(" ")       = false
	 * StringUtil.isBlank("bob")     = true
	 * StringUtil.isBlank("  bob  ") = true
	 * @param str 要检查的字符串
	 *
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isNotBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 比较两个字符串（大小写敏感）。
	 * <pre>
	 * StringUtil.equals(null, null)   = true
	 * StringUtil.equals(null, "abc")  = false
	 * StringUtil.equals("abc", null)  = false
	 * StringUtil.equals("abc", "abc") = true
	 * StringUtil.equals("abc", "ABC") = false
	 * </pre>
	 *
	 * @param str1 要比较的字符串1
	 * @param str2 要比较的字符串2
	 *
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equals(str2);
	}

	/**
	 * 比较两个字符串（大小写不敏感）。
	 * <pre>
	 * StringUtil.equalsIgnoreCase(null, null)   = true
	 * StringUtil.equalsIgnoreCase(null, "abc")  = false
	 * StringUtil.equalsIgnoreCase("abc", null)  = false
	 * StringUtil.equalsIgnoreCase("abc", "abc") = true
	 * StringUtil.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 *
	 * @param str1 要比较的字符串1
	 * @param str2 要比较的字符串2
	 *
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equalsIgnoreCase(str2);
	}

	/* ============================================================================ */
	/*  大小写转换。                                                                */
	/* ============================================================================ */

	/**
	 * 将字符串转换成大写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * <pre>
	 * StringUtil.toUpperCase(null)  = null
	 * StringUtil.toUpperCase("")    = ""
	 * StringUtil.toUpperCase("aBc") = "ABC"
	 * </pre>
	 * </p>
	 *
	 * @param str 要转换的字符串
	 *
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toUpperCase(String str) {
		if (str == null) {
			return null;
		}

		return str.toUpperCase();
	}

	/**
	 * 将字符串转换成小写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * <pre>
	 * StringUtil.toLowerCase(null)  = null
	 * StringUtil.toLowerCase("")    = ""
	 * StringUtil.toLowerCase("aBc") = "abc"
	 * </pre>
	 * </p>
	 *
	 * @param str 要转换的字符串
	 *
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toLowerCase(String str) {
		if (str == null) {
			return null;
		}

		return str.toLowerCase();
	}

	/**
	 * 返回card的前四后四位
	 * 
	 * @param card
	 * @return
	 */
	public static String parseIdCard(String card) {
		if (card == null || "".equals(card)) {
			return null;
		}
		return card.trim().substring(0, 4) + "****" + card.trim().substring(card.length() - 4, card.length());
	}

	/**
	 * 处理空字符串
	 * 
	 * @param str
	 */
	public static String handleStringNull(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str) ? "" : str;
	}

	/**
	 * 查询条件空处理
	 * 
	 * @param str
	 * @return
	 */
	public static String handleQueryNull(String str) {
		return isBlank(str) ? null : str.trim();
	}

	/**
	 * 处理HTML字符串
	 * 
	 * @param str
	 */
	public static String handleHtmlString(String str) {
		return handleStringNull(str) == "" ? "" : str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}


	/**
	 * 判断是否为多音字
	 */
	public static boolean isPolyphone(char t1) {
		boolean flag = false;
		if (t1 == '重' || t1 == '厦' || t1 == '长' || t1 == '柏' || t1 == '蚌') {
			flag = true;
		}
		return flag;
	}

	public static String handleFlowOperate(Integer operatorId) {
		String [] operatorName=new String[]{"移动","联通","电信"};
		return operatorName[operatorId];
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符、问号和逗号
                      注： \n 回车(\u000a)    
          \t 水平制表符(\u0009)    
          \s 空格(\u0008)    
          \r 换行(\u000d) 
	 * @return dest
	 */
	public static String handleTrim(String str){
		String dest = "";    
		if (str!=null) {   
			Pattern p = Pattern.compile("[\\s]|[\t]|[\r]|[\n]|[?]|[,]");
			Matcher m = p.matcher(str);   
			dest = m.replaceAll("");    
		}    
		return dest; 
	}

	/**
	 * <p>Description:把map类型为Map<String,Object>转换成url</p>
	 * @uthor YM10159
	 * @date 2017年3月15日 上午11:42:56
	 * @param @param map<String,Object>
	 */
	public static String map2UrlParams(Map<String, Object> map) {

		if(map.isEmpty()){
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry : map.entrySet()) {
			if (!isEmpty(ObjectUtils.toString(entry.getValue()))) {
				String key = entry.getKey();
				try {
					String value = URLEncoder.encode(ObjectUtils.toString(entry.getValue()), "UTF-8");
					sb.append("&" + key + "=" + value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (sb.length() > 0) {
			return sb.substring(1);
		}
		return null;
	}

	/**
	 * <p>Description:获取操作系统类型</p>
	 * @uthor YM10159
	 * @date 2017年3月21日 上午11:18:59
	 * @param @return
	 */
	public static String osType(){
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");

		if(toLowerCase(os).startsWith("win")){
			return "windows";
		}else{
			return "linux";
		}
	}
	
	
	/**
	02
	  * 获得主机IP
	03
	  *
	04
	  * @return String
	05
	  */

	 public static boolean isWindowsOS(){
	
	    boolean isWindowsOS = false;

	    String osName = System.getProperty("os.name");
	
	    if(osName.toLowerCase().indexOf("windows")>-1){
	
	     isWindowsOS = true;
	
	    }
	
	    return isWindowsOS;
	
	  }
	
	 /**
	  * <p>Description:将字符串转为html格式</p>
	  * @uthor YM10139
	 * @date 2017年4月18日 上午11:18:59
	 * @param @return
	 */
	 public static String str2HtmlStr(String titleName, String content){
		 String module = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd \">   ";
		 String headModule = "<html xmlns=\"http://www.w3.org/1999/xhtml \">";
		 String startModule = headModule+"<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>"+content+"</title></head><body>";
		 String endModule="</body></html>";
		 String htmlStr=module+ startModule+content+endModule;
		 return htmlStr;
	 }

	  /**
	
	    * 获取本机ip地址，并自动区分Windows还是linux操作系统
	
	    * @return String
	
	    */
	
	  public static String getLocalIP(){
	    String sIP = "";
	    InetAddress ip = null;
	    try {
	     //如果是Windows操作系统
	     if(isWindowsOS()){
	      ip = InetAddress.getLocalHost();
	     }
	     //如果是Linux操作系统
	     else{
	      boolean bFindIP = false;
	      Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
	        .getNetworkInterfaces();
	      while (netInterfaces.hasMoreElements()) {
	       if(bFindIP){
	        break;
	       }
	       NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
	       //----------特定情况，可以考虑用ni.getName判断
	       //遍历所有ip
	       Enumeration<InetAddress> ips = ni.getInetAddresses();
	       while (ips.hasMoreElements()) {
	        ip = (InetAddress) ips.nextElement();
	        if( ip.isSiteLocalAddress() 
	                   && !ip.isLoopbackAddress()   //127.开头的都是lookback地址
	                   && ip.getHostAddress().indexOf(":")==-1){
	            bFindIP = true;
	               break; 
	           }
	       }
	      }
	     }
	    }
	    catch (Exception e) {
	     e.printStackTrace();
	    }
	    if(null != ip){
	     sIP = ip.getHostAddress();
	    }
	    return sIP;
	  }

	  public static void main(String[] args) {
		System.err.println(getLocalIP());  
	}
	  
	  
	  
	  /**
	     * 校验银行卡号是否有效
	     * @param cardId
	     * @return
	     */
	    public static  boolean checkBankCard(String cardId) {
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
	  
	  
	    /**
	     * 限定字符串长度
	     * @param nonCheckCodeCardId
	     * @return
	     */
	    public static String stringLength2Limit(String str,int strLength){
	    	String returnStr="";
	    	if(str == null){
	    		return returnStr;
	    	}
	        if(str.length() < strLength){
	        	returnStr=str;
	        }else{
	        	returnStr=	str.substring(strLength);
	        }
			return returnStr;
	    }  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
