package com.ymkj.cms.biz.common.util.log;

import java.util.Map;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.json.JSONArray;

import com.ymkj.cms.biz.common.util.StringUtils;

public class Log {
	
	public final static Object lock = new Object();
	/*static
	{
		URL url = Log.class.getClassLoader().getResource("./log4j.properties");
		PropertyConfigurator.configureAndWatch(url.getFile(), 5000);
	}*/

	private static Logger getLogger(String fun,String logPath){
		Logger log = null;
		synchronized (lock) {
			if(StringUtils.isBlank(fun)){
				log = LogManager.getRootLogger();
			}else if(LogManager.exists(fun)==null){
				log = LogManager.getLogger(fun);
				PatternLayout pl = new PatternLayout();
				pl.setConversionPattern("%d{HH:mm:ss} [%p] %m %n ");
				DailyRollingFileAppender df = new DailyRollingFileAppender();
				df.setAppend(true);
				df.setFile(logPath+fun+"/Log.log");
				df.setDatePattern("'.'yyyy-MM-dd");
				df.setLayout(pl);
				log.addAppender(df);
				log.setAdditivity(false);
				df.activateOptions();
			}else{
				log = LogManager.getLogger(fun);
			}
		}
		return log;
	}

	public static void Info(String fun,String str,String logPath){
		Logger log = getLogger(fun,logPath);
		log.info(str);
	}

	/*public static void Info(String fun,StringBuilder str){
		Info(fun,str.toString());
	}

	public static void Debug(String fun,String str){
		Logger log = getLogger(fun);
		log.debug(str);
	}

	public static void Debug(String fun,StringBuilder str){
		Debug(fun,str.toString());
	}

	public static void Error(String fun,String str){
		Logger log = getLogger(fun);
		log.error(str);
	}

	public static void Error(String fun,StringBuilder str){
		Error(fun,str.toString());
	}*/

	public static String getErrorDetail(Exception e){
		StringBuilder sb = new StringBuilder();
		sb.append(e.getMessage()+"\r\n");
		for(StackTraceElement ste : e.getStackTrace()){
			sb.append(ste+"\r\n");
		}

		return sb.toString();
	}

	public static String getJsonStr(Map map){
		JSONArray json = new JSONArray();
		json.put(map);
		String result = json.toString();

		return result;
	}
	
}
