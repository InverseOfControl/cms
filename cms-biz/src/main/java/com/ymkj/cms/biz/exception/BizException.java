package com.ymkj.cms.biz.exception;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.base.core.common.excption.CoreException;

/**
 * demo 业务异常类 继承 CoreException
 * @author user
 */
public class BizException extends CoreException {

	private static final long serialVersionUID = 7946023196149777499L;
	
	protected BizErrorCode errorCode;

	public BizErrorCode getErrorCode() {
		return errorCode;
	}
	
	// 重写父类的构造方法
	public BizException(CoreErrorCode errorCode, Object... arguments) {
		super(errorCode, arguments);
	}

	public BizException(BizErrorCode errorCode, Object... arguments) {
		super();
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
		this.arguments = arguments;
	}
	public BizException(BizErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
	}
	
	public BizException(BizErrorCode errorCode,String errorMsg, Object... arguments) {
		super();
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
		this.errorMsg=errorMsg;
		this.arguments = arguments;
	}
	
	public BizException(BizErrorCode errorCode,String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.realCode = errorCode.getErrorCode();
		this.errorMsg=errorMsg;
	}
	
	@Override
	public String getMessage() {
		
		String notMessage = "not error, not message";
		String defaultMessage = "";
		
		if(StringUtils.isNotBlank(errorMsg)){
			defaultMessage = errorMsg;
		}else{
			if (errorCode != null ) {
				defaultMessage = errorCode.getDefaultMessage();
			}
		}		
		if (StringUtils.isBlank(defaultMessage)) {
			return notMessage;
		}
		return MessageFormat.format(defaultMessage, this.arguments);
	}
	
	public String getRealCode() {
		return realCode;
	}


}
