package com.ymkj.cms.web.test;

import java.text.MessageFormat;

import org.junit.Test;

public class MessageFormatTest {
	
	@Test
	public void format(){
		String msg = "{0}{1}{2}{3}{4} 参数不合法";
		Object[] args = new Object[]{"name", "address"};
		String format = MessageFormat.format(msg, args);
		System.out.println(format);
	}
	
}
