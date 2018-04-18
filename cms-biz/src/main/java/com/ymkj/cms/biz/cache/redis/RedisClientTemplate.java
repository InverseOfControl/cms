package com.ymkj.cms.biz.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/**
 * redis 模板工具
 * @author haowp
 */
public class RedisClientTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisClientTemplate.class);
	
	// 分片连接池
	private ShardedJedisPool shardedJedisPool;
	
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	/**
	 * 获取客户端
	 * @return
	 */
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		} catch (Exception e) {
			logger.error("getRedisClent error", e);
		}
		return null;
	}

	/**
	 * 设置字符串值
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		String result = null;

		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.set(key, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			shardedJedis.close();
		}
		return result;
	}
	
	
	/**
	 * 设置对象字节流
	 * @param key
	 * @param value
	 * @return
	 */
	public String setByte(String key, byte[] value) {
		String result = null;

		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.set(key.getBytes(), value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			shardedJedis.close();
		}
		return result;
	}
	
	/**
	 * 获取对象字节流
	 * @param key
	 * @return
	 */
	public byte[] getByte(String key) {
		byte[] result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.get(key.getBytes());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			shardedJedis.close();
		}
		return result;

	}

	/**
	 * 获取字符串值
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.get(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			shardedJedis.close();
		}
		return result;

	}
    /**
     * 断开连接
     */
	public void disconnect() {
		ShardedJedis shardedJedis = getRedisClient();
		shardedJedis.disconnect();
	}
	
	/**
	 * 判断是否存在 key
	 * @param key
	 * @return
	 */
	public Boolean exists(String key) {
		Boolean result = false;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.exists(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			shardedJedis.close();
		}
		return result;
	}
	
	/**
	 * 判断 key 值对应的value 类型
	 * @param key
	 * @return
	 */
	public String type(String key) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.type(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	/**
	 * 在某段时间后实现
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expire(String key, int seconds) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.expire(key, seconds);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param unixTime
	 * @return
	 */
	public Long expireAt(String key, long unixTime) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.expireAt(key, unixTime);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}
	/**
	 * 获取对应key 的ttl 值
	 * @param key
	 * @return
	 */
	public Long ttl(String key) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.ttl(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public boolean setbit(String key, long offset, boolean value) {

		ShardedJedis shardedJedis = getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.setbit(key, offset, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public boolean getbit(String key, long offset) {
		ShardedJedis shardedJedis = getRedisClient();
		boolean result = false;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.getbit(key, offset);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public long setrange(String key, long offset, String value) {
		ShardedJedis shardedJedis = getRedisClient();
		long result = 0;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.setrange(key, offset, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String getrange(String key, long startOffset, long endOffset) {
		ShardedJedis shardedJedis = getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.getrange(key, startOffset, endOffset);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String getSet(String key, String value) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.getSet(key, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	

	

	public Long decrBy(String key, long integer) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.decrBy(key, integer);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long decr(String key) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.decr(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long incrBy(String key, long integer) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.incrBy(key, integer);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long incr(String key) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.incr(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long append(String key, String value) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.append(key, value);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String substr(String key, int start, int end) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.substr(key, start, end);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long hset(String key, String field, String value) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hset(key, field, value);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String hget(String key, String field) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hget(key, field);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long hsetnx(String key, String field, String value) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hsetnx(key, field, value);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String hmset(String key, Map<String, String> hash) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hmset(key, hash);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public List<String> hmget(String key, String... fields) {
		List<String> result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hmget(key, fields);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long hincrBy(String key, String field, long value) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hincrBy(key, field, value);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Boolean hexists(String key, String field) {
		Boolean result = false;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hexists(key, field);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long del(String key) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.del(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long hdel(String key, String field) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hdel(key, field);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long hlen(String key) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hlen(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Set<String> hkeys(String key) {
		Set<String> result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hkeys(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public List<String> hvals(String key) {
		List<String> result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hvals(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Map<String, String> hgetAll(String key) {
		Map<String, String> result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.hgetAll(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	// ================list ====== l表示 list或 left, r表示right====================
	public Long rpush(String key, String string) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.rpush(key, string);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long lpush(String key, String string) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.lpush(key, string);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long llen(String key) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.llen(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public List<String> lrange(String key, long start, long end) {
		List<String> result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.lrange(key, start, end);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String ltrim(String key, long start, long end) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.ltrim(key, start, end);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String lindex(String key, long index) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.lindex(key, index);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String lset(String key, long index, String value) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.lset(key, index, value);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long lrem(String key, long count, String value) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.lrem(key, count, value);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String lpop(String key) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.lpop(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String rpop(String key) {
		String result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.rpop(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	// return 1 add a not exist value ,
	// return 0 add a exist value
	public Long sadd(String key, String member) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.sadd(key, member);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Set<String> smembers(String key) {
		Set<String> result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.smembers(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long srem(String key, String member) {
		ShardedJedis shardedJedis = getRedisClient();

		Long result = null;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.srem(key, member);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String spop(String key) {
		ShardedJedis shardedJedis = getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.spop(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long scard(String key) {
		ShardedJedis shardedJedis = getRedisClient();
		Long result = null;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.scard(key);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Boolean sismember(String key, String member) {
		ShardedJedis shardedJedis = getRedisClient();
		Boolean result = null;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.sismember(key, member);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public String srandmember(String key) {
		ShardedJedis shardedJedis = getRedisClient();
		String result = null;
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.srandmember(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Long zadd(String key, double score, String member) {
		Long result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}

		try {
			result = shardedJedis.zadd(key, score, member);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}

	public Set<String> zrange(String key, int start, int end) {
		Set<String> result = null;
		ShardedJedis shardedJedis = getRedisClient();
		if (shardedJedis == null) {
			return result;
		}
		try {
			result = shardedJedis.zrange(key, start, end);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			shardedJedis.close();
		}
		return result;
	}
}
