package com.ymkj.cms.biz.common.util.log;

import java.lang.reflect.Method;

import org.apache.commons.lang.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.ymkj.base.core.biz.api.message.PageResponse;
import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;
import com.ymkj.base.core.common.utils.BeanKit;
import com.ymkj.cms.biz.common.util.StringUtils;

//@Component
//@Aspect
// 该注解标示该类为切面类
public class LogAspect{

	@Value("#{env['windows_logPath']?:''}")
	private String windows_logPath;

	@Value("#{env['linux_logPath']?:''}")
	private String linux_logPath;

	private Gson gson = new Gson();

	@Around("within(com.ymkj.cms.biz.facade.*.*)")
	public Object doExecute(ProceedingJoinPoint joinPoint) throws Throwable {

		ResultModel rm = new ResultModel();
		Object[] args = joinPoint.getArgs();

		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();  
		Method method = methodSignature.getMethod(); 

		rm.setInPara(gson.toJson(BeanKit.bean2map(args[0])));
		rm.setRequestUrl(method.getDeclaringClass().getSimpleName()+"\\"+method.getName());

		Object retval = null;
		try{
			retval = joinPoint.proceed();
		}catch(Exception e){
			return exceptioncl(rm,e,joinPoint,retval);
		}

		if(retval instanceof Response){//环绕后
			rm.setResult(retval);
			
			String logPath = this.getLogPath();
			Log.Info(method.getDeclaringClass().getSimpleName()+"/"+method.getName(), rm.toString(),logPath);
			
			return retval;
		}
		return null;
	}

	private Object exceptioncl(ResultModel rm,Exception e ,JoinPoint joinPoint,Object retval){
		
		rm.setCode(ResultModel.FAIL_CODE);
		
		e.printStackTrace();
		
		Object obj = null;
		
		Signature signature = joinPoint.getSignature();  
		MethodSignature methodSignature = (MethodSignature) signature;  
		Method method = methodSignature.getMethod(); 
		
		Class returnType = methodSignature.getReturnType();
		
		if(e instanceof CoreException){
			CoreException ce = (CoreException)e;
			
			String errorMsg = ObjectUtils.toString(ce.getArguments()[0]);
			
			if(returnType.equals(Response.class)){
				obj = new Response<Object>(ce.getRealCode(), errorMsg);
			}else if(returnType.equals(PageResponse.class)){
				obj = new PageResponse<Object>(ce.getRealCode(), errorMsg);
			}else{
				obj = new Response<Object>(ce.getRealCode(), ce.getErrorMsg());
			}
		}else{
			obj = new Response<Object>(CoreErrorCode.SYSTEM_ERROR.getErrorCode(), ObjectUtils.toString(e.getMessage()));
		}
		
		//异常捕获
		StringBuilder sb = new StringBuilder();
		sb.append(e.getMessage()+"\r\n");
		for(StackTraceElement ste : e.getStackTrace()){
			sb.append(ste+"\r\n");
		}
		sb.append(obj);
		
		rm.setExceptionDesc(sb.toString());
		
		rm.setInPara(rm.getInPara());
		rm.setRequestUrl(rm.getRequestUrl());
		rm.setExceptionDesc(e.getMessage());
		
		rm.setResult(retval);
		String logPath = this.getLogPath();
		Log.Info(method.getDeclaringClass().getSimpleName()+"/"+method.getName(), rm.toString(),logPath);
		
		return obj;
	}
	
	public String getLogPath(){
		String logPath = "";
		String osType = StringUtils.osType();
		if(osType.equals("windows")){
			logPath = windows_logPath;
		}else{
			logPath = linux_logPath;
		}
		return logPath;
	}
}
