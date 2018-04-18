package com.ymkj.cms.biz.api.vo.response.channel;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import com.ymkj.base.core.biz.api.message.Request;

/**
 * @author YM10189
 * @date 2017年6月5日
 * @Description:附近下载 res Vo
 */
public class ResAccessoryVo extends Request implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6341673333539145293L;
	
	/**
	 * 文件名
	 */
	private String fileName;
	
	/**
	 * 文件字节数组
	 */
	private ByteArrayOutputStream outStream;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ByteArrayOutputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(ByteArrayOutputStream outStream) {
		this.outStream = outStream;
	}
	

	
}
