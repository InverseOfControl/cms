package com.ymkj.cms.biz.common.util;

import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Hex;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.exception.BizException;

public class MD5Utils {
	/**
	 * 将数据按照参数名首字母升序排序（空值不参与签名），以&拼接，最后加上秘钥，再进行标准MD5加密
	 * @param map
	 * @param keySign
	 * @return
	 * @throws Exception
	 * @author lix YM10160
	 * @date 2017年3月16日上午10:08:48
	 */
	public static String md5(Map<String, String> map,String keySign) throws Exception{
		//将数据按照参数名首字母升序排序
		SortedMap<String, Object> mapOrder = new TreeMap<String, Object>();
		
		mapOrder.putAll(map);
		Set<String> keySet = mapOrder.keySet();
	    Iterator<String> iter = keySet.iterator();
	    String strMd5="";
	    while (iter.hasNext()) {
	        String key = iter.next();
	        if(null == mapOrder.get(key) || "".equals(mapOrder.get(key)))
	        {
	        	continue;
	        }
	        strMd5=strMd5+key+"="+mapOrder.get(key)+"&";
	    }
	    strMd5=strMd5+"key="+keySign;
	    
	    MessageDigest m = MessageDigest.getInstance("MD5");  
        m.update(strMd5.getBytes("UTF-8"));  
        byte s[] = m.digest();  
        return Hex.encodeHexString(s).toUpperCase();  
	    
	    
		/*char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
	    try {
	        byte[] btInput = strMd5.getBytes();
	        // 获得MD5摘要算法的 MessageDigest 对象
	        MessageDigest mdInst = MessageDigest.getInstance("MD5");
	        // 使用指定的字节更新摘要
	        mdInst.update(btInput);
	        // 获得密文
	        byte[] md = mdInst.digest();
	        // 把密文转换成十六进制的字符串形式
	        int j = md.length;
	        char str[] = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	            byte byte0 = md[i];
	            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            str[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(str);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }*/
	}
	/**
	 * 加密校验
	 * @param map
	 * @param key
	 * @throws Exception
	 * @author lix YM10160
	 * @date 2017年4月12日上午11:45:33
	 */
	public static void signChecked(Map<String,Object> map,String key) throws Exception{
		SortedMap<String, Object> parameters = new TreeMap<String, Object>();
		
		parameters.putAll(map);
		
		StringBuilder signBuilder = new StringBuilder();
		for (Entry<String, Object> entry : parameters.entrySet()) {
			String k = entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v)  && !"sign".equals(k) && !"key".equals(k)) {
				signBuilder.append(k + "=" + v + "&");
			}
		}
//		log.info("传递的参数为-->{}",signBuilder.toString());
		//拼接key进行MD5签名
		signBuilder.append("key=" + key);
		String sign = MD5Util.md5(signBuilder.toString(), "UTF-8").toUpperCase();
//		log.info("前置系统sign签名为-->{}",sign);
		//验签
		if(!map.get("sign").equals(sign)){
			throw new BizException(CoreErrorCode.PARAM_ERROR, "请求端sign签名->"+map.get("sign"));
		}
	}
}
