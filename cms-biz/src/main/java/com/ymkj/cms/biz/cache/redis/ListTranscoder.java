package com.ymkj.cms.biz.cache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.ymkj.base.core.common.excption.CoreErrorCode;
import com.ymkj.cms.biz.exception.BizException;
/**
 * list集合与字节流相互转换
 * @author cj
 *1
 * @param <M>
 */
public class ListTranscoder<M extends Serializable> extends SerializeTranscoder {
	@SuppressWarnings("unchecked")
	@Override
	public byte[] serialize(Object value) {
		if (value == null) 
			throw new NullPointerException("Can't serialize null");

		List<M> values = (List<M>) value; 

		byte[] results = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;

		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(values);
			os.close();
			bos.close();
			results = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object deserialize(byte[] in) {

		List<M> list = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				list = (List<M>) is.readObject(); 
				is.close();
				bis.close();
			}
		} catch (IOException e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		} catch (ClassNotFoundException e) {
			throw new BizException(CoreErrorCode.SYSTEM_ERROR, e);
		} finally {
			close(is);
			close(bis);
		}

		return list;

	}

}
