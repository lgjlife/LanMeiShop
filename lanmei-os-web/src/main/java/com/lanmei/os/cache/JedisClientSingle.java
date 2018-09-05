package com.lanmei.os.cache;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Jedis 工具类
 */
public class JedisClientSingle {

	@Autowired
	private JedisPool jedisPool;
	
	/**
	 * 通过key获取String值
	 * @param key
	 * @return
	 */
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	/**
	 * 向redis存储值
	 * @param key    键
	 * @param value  值 String
	 * @return  写入状态
	 */
	public String set(final String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String status = jedis.set(key, value);
		jedis.close();
		return status;
	}
	
	
	
	
}
