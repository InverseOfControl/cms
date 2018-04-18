package com.ymkj.cms.web.boss.exception;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;

/**
 * Web 端自定义异常(项目中建议保留 或者参考改写)
 * @author user
 */
public class BusinessException extends CoreException {
	
	private static final long serialVersionUID = -8905856985628672261L;
	//日誌工具類
	protected static final Logger logger = LoggerFactory.getLogger(BusinessException.class);
	//业务异常枚举
	protected BusinessErrorCode errorCode;
	
	public BusinessErrorCode getErrorCode() {
		return errorCode;
	}
	
	/**
	 * 此构造方法 传递 响应结果值 (建议保留)
	 * @param errorCode
	 * @param resMsg
	 * @param arguments
	 */
	public BusinessException(CoreErrorCode errorCode, Object... arguments) {
		super(errorCode, arguments);
	}
	/**
	 * 此构造方法 传递 响应结果值 (建议保留)
	 * @param errorCode
	 * @param resMsg
	 * @param arguments
	 */
	public BusinessException(CoreErrorCode errorCode,String resMsg, Object... arguments) {
		this(errorCode, arguments);
		this.resMsg = resMsg;
	}

	/**
	 * 根据 错误编码 和 异常 构造
	 * @param errorCode
	 * @param cause
	 */
	public BusinessException(BusinessErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
	}
	/**
	 * 根据 错误编码 和 参数信息构造, 一般应用 Controller 进行参数验证
	 * @param errorCode
	 * @param arguments
	 */
	public BusinessException(BusinessErrorCode errorCode, Object... arguments) {
		super();
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
		this.errorMsg=errorCode.getDefaultMessage();
		this.arguments = arguments;
	}
	/**
	 * 根据错误编码 和 自定义 错误消息 , 参数类型 构造
	 * @param errorCode
	 * @param errorMsg
	 * @param arguments
	 */
	public BusinessException(BusinessErrorCode errorCode, String errorMsg, Object... arguments) {
		super();
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
		this.errorMsg=errorMsg;
		this.arguments = arguments;
	}

	@Override
	public String getMessage() {
		String notMessage = "not error, not message";
		if (StringUtils.isBlank(realCode)) {
			return notMessage;
		}
		String defaultMessage = "";
		
		if(StringUtils.isBlank(errorMsg)){
			defaultMessage = errorCode.getDefaultMessage();
		}else{
			defaultMessage = errorMsg;
		}		
		if (StringUtils.isBlank(defaultMessage)) {
			return notMessage;
		}
		return MessageFormat.format(defaultMessage, this.arguments);
	}
	
	public String getRealCode() {
		return realCode;
	}
	
	// 如果 错误编码为  验证类型 或者 接口返回失败结果, 则错误日志不需要打印
	public boolean printable(){
		if(this.realCode != null){
			if(realCode.startsWith("0") || realCode.equals(CoreErrorCode.FACADE_RESPONSE_FAIL.getErrorCode())){
				return false;
			}
		}
		return true;
	}
}
