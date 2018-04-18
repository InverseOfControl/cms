package com.ymkj.cms.biz.cache.redis;

import java.io.Closeable;

import org.apache.log4j.Logger;
/**
 * java对象与字节流相互转换父类
 * @author CJ
 *
 */
public abstract  class SerializeTranscoder {
protected static Logger logger = Logger.getLogger(SerializeTranscoder.class);
	/**
	 * 将对象转换成字节
	 * @param value
	 * @return
	 */
	public abstract byte[] serialize(Object value);
	/**
	 * 将字节转换成对象
	 * @param in
	 * @return
	 */
	public abstract Object deserialize(byte[] in);
	/**
	 * 关闭IO
	 * @param closeable
	 */
	public void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				 logger.info("Unable to close " + closeable, e); 
			}
		}
	}
}
