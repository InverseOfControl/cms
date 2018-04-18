package com.ymkj.cms.web.boss.common;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.ymkj.cms.biz.api.enums.EnumConstants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

  
/**
 * redis cache 工具类 
 * @author fuhongxing
 */
@Service
public final class RedisUtil {

	private static final Logger log = Logger.getLogger(RedisUtil.class);

	
	private String sysCode = EnumConstants.BMS_SYSCODE;	// 系统编号

	@Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;  
  
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		log.info("批量删除...");
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (!keys.isEmpty())
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 删除所有value
	 *
	 * @author wulj
	 */
	public void removeAll(){
		log.info("清空redis");
		redisTemplate.delete(redisTemplate.keys("*"));
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 读取缓存
	 * @param type	缓存类型
	 * @param key	缓存键
	 * @return
	 */
	public Object get(EnumConstants.CacheType type, final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();

		result = operations.get(sysCode + "_" + type.getPrefix() + "_" + key);

		return result;
	}


	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			log.error("redis缓存写入异常...", e);
		}

		return result;
	}

	/**
	 * 写入缓存
	 * @param key 
	 * @param value
	 * @param expireTime 有效时间
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			log.error("redis缓存写入异常...", e);
		}
		return result;
	}

	/**
	 * 写入缓存
	 * @param type	缓存类型
	 * @param key	缓存键
	 * @param value	缓存值
	 * @author ym10195
	 * @return
	 */
	public boolean set(EnumConstants.CacheType type, String key, Object value){
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = this.redisTemplate.opsForValue();
			operations.set(this.sysCode + "_" + type.getPrefix() + "_" + key, value, type.getExpire(), type.getTimeUnit());
			result = true;
		}catch (Exception e){
			log.error("redis缓存写入异常...", e);
		}

		return result;
	}
	/** 
     * 模糊查询keys 
     * @param pattern 
     * @return 
     */  
    public Set<Serializable> getKeys(String pattern){  
		return this.redisTemplate.keys(pattern+"*");   
    }  

}  