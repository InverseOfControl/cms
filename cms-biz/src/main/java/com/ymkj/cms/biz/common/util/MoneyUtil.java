package com.ymkj.cms.biz.common.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoneyUtil {
	/**
	 * 金额--表格中金额（需要分隔借款金额）
	 * @param map
	 * @param pacyMoney
	 * @param type 金额类型
	 */
	public static void setMoneyToMap(Map<String, Object> map, String pacyMoney, int type) {
		String[] sMoney = pacyMoney.split("\\.");
		String fen =  sMoney[1].substring(1);//分
		String jiao =  sMoney[1].substring(0, 1);//角
		String yuan = null;
		String ten1 = null;
		String bai = null;
		String qian = null;
		String wan = null;
		String ten = null;
		String bwan = null;
		//百
		if (sMoney[0].length() == 3) {
			yuan = sMoney[0].substring(2,3);
			ten1 = sMoney[0].substring(1,2);
			bai = sMoney[0].substring(0,1);
			qian = "¥";
		//千
		}else if (sMoney[0].length() == 4) {
			yuan = sMoney[0].substring(3,4);
			ten1 = sMoney[0].substring(2,3);
			bai = sMoney[0].substring(1,2);
			qian = sMoney[0].substring(0,1);
			wan = "¥";
		//万
		}else if (sMoney[0].length() == 5) {
			yuan = sMoney[0].substring(4,5);
			ten1 = sMoney[0].substring(3,4);
			bai = sMoney[0].substring(2,3);
			qian = sMoney[0].substring(1,2);
			wan = sMoney[0].substring(0,1);
			ten = "¥";
		//十万
		}else if (sMoney[0].length() == 6) {
			yuan = sMoney[0].substring(5,6);
			ten1 = sMoney[0].substring(4,5);
			bai = sMoney[0].substring(3,4);
			qian = sMoney[0].substring(2,3);
			wan = sMoney[0].substring(1,2);
			ten = sMoney[0].substring(0,1);
		//百万
		}else if (sMoney[0].length() == 6) {
			yuan = sMoney[0].substring(6,7);
			ten1 = sMoney[0].substring(5,6);
			bai = sMoney[0].substring(4,5);
			qian = sMoney[0].substring(3,4);
			wan = sMoney[0].substring(2,3);
			ten = sMoney[0].substring(1,2);
			bwan = sMoney[0].substring(0,1);
		}
		if (bwan != null) {
			map.put("bwan"+type, bwan);
		}
		if (ten != null) {
			map.put("ten"+type, ten);
		}
		if (wan != null) {
			map.put("wan"+type, wan);
		}
		if (qian != null) {
			map.put("qian"+type, qian);
		}
		map.put("bai"+type, bai);
		map.put("ten1"+type, ten1);
		map.put("yuan"+type, yuan);
		map.put("jiao"+type, jiao);
		map.put("fen"+type, fen);
	}

	/*
     * 将小写的人民币转化成大写
     */
    public static String NumberToChinese(double number) {
            StringBuffer chineseNumber = new StringBuffer();
            String[] num = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
            String[] unit = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟",
                            "亿", "拾", "佰", "仟", "万" };
            String tempNumber = String.valueOf(Math.round((number * 100)));
            int tempNumberLength = tempNumber.length();
            if ("0".equals(tempNumber)) {
                    return "零元整";
            }
            if (tempNumberLength > 15) {
                    try {
                            throw new Exception("超出转化范围.");
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
            }
            boolean preReadZero = true; // 前面的字符是否读零
            for (int i = tempNumberLength; i > 0; i--) {
                    if ((tempNumberLength - i + 2) % 4 == 0) {
                            // 如果在（圆,万,亿,万）位上的四个数都为零,如果标志为未读零,则只读零,如果标志为已读零,则略过这四位
                            if (i - 4 >= 0 && "0000".equals(tempNumber.substring(i - 4, i))) {
                                    if (!preReadZero) {
                                            chineseNumber.insert(0, "零");
                                            preReadZero = true;
                                    }
                                    i -= 3; // 下面还有一个i--
                                    continue;
                            }
                            // 如果当前位在（圆,万,亿,万）位上,则设置标志为已读零（即重置读零标志）
                            preReadZero = true;
                    }
                    Integer digit = Integer.parseInt(tempNumber.substring(i - 1, i));
                    if (digit == 0) {
                            // 如果当前位是零并且标志为未读零,则读零,并设置标志为已读零
                            if (!preReadZero) {
                                    chineseNumber.insert(0, "零");
                                    preReadZero = true;
                            }
                            // 如果当前位是零并且在（圆,万,亿,万）位上,则读出（圆,万,亿,万）
                            if ((tempNumberLength - i + 2) % 4 == 0) {
                                    chineseNumber.insert(0, unit[tempNumberLength - i]);
                            }
                    }
                    // 如果当前位不为零,则读出此位,并且设置标志为未读零
                    else {
                            chineseNumber
                                            .insert(0, num[digit] + unit[tempNumberLength - i]);
                            preReadZero = false;
                    }
            }
            // 如果分角两位上的值都为零,则添加一个"整"字
            if (tempNumberLength - 2 >= 0
                            && "00".equals(tempNumber.substring(tempNumberLength - 2,
                                            tempNumberLength))) {
                    chineseNumber.append("整");
            }
            return chineseNumber.toString();
    }
    
    
    
    
    public static String TransformationMsg(String model, String T) {
		Pattern pattern = Pattern.compile("截至[0-9]{4}年[0-9]{1,2}月");
		Matcher matcher = pattern.matcher(model);
		String dateStr = null;
		if (matcher.find()) {
			dateStr = matcher.group(0);
		}

		String[] arr = null;
		if (null != dateStr) {
			arr = dateStr.split("(年|月)");
		}

		if (arr != null && arr.length > 1) {
			if(isNumeric(arr[1])){
				//替换动态
				String t1 = T.replaceAll("T", arr[1]);
				//
				String t2 = cal(t1);
				//满12 要求余
				return getReplace12(t2);
			}
		}
		
		T = getReplace12(T);
		return T;
	}
    
    public static String getReplace12(String str){
    	Pattern pattern = Pattern.compile("[0-9]{2}");
		Matcher matcher = pattern.matcher(str);
		
		while(matcher.find()){
			if(matcher.group(0) != null && isNumeric(matcher.group(0))){
				Integer numb = Integer.parseInt(matcher.group(0));
				if(numb > 12){
					int i  = numb%12;
					str = str.replaceAll(matcher.group(0), i+"");
				}
			}
		}
		return str;
    }
    
    public static void main(String[] args) {
    	String aa = getReplace12("请补充12月或15月还款记录");
    	
    	System.out.println(aa);
		
	}

	public static boolean isNumeric(String str) {
		if(str == null) return false;
		
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static String cal(String src) {
		StringBuilder builder = new StringBuilder();

		Pattern pattern = Pattern.compile("([\\d.]+)\\s*([*/])\\s*([\\d.]+)");
		builder.append(src);
		Matcher matcher = pattern.matcher(builder.toString());
		while (matcher.find()) {
			float f1 = Float.parseFloat(matcher.group(1));
			float f2 = Float.parseFloat(matcher.group(3));
			float result = 0;
			switch (matcher.group(2)) {
			case "*":
				result = f1 * f2;
				break;
			case "/":
				result = f1 / f2;
				break;
			}
			builder.replace(matcher.start(), matcher.end(), String.valueOf(result));
			matcher.reset(builder.toString());
		}
		pattern = Pattern.compile("([\\d.]+)\\s*([+-])\\s*([\\d.]+)");
		matcher = pattern.matcher(builder.toString());
		while (matcher.find()) {
			int f1 = Integer.parseInt(matcher.group(1));
			int f2 = Integer.parseInt(matcher.group(3));
			int result = 0;
			switch (matcher.group(2)) {
			case "+":
				result = f1 + f2;
				break;
			case "-":
				result = f1 - f2;
				break;
			}
			builder.replace(matcher.start(), matcher.end(), String.valueOf(result));
			matcher.reset(builder.toString());
		}
		return builder.toString();
	}
}

