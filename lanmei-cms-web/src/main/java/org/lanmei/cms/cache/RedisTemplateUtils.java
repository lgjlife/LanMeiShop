package org.lanmei.cms.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTemplateUtils<K, V>  {
	
	private final static Logger logger = LoggerFactory.getLogger("RedisTemplateUtils.class");	
	
	
	@Autowired
	RedisTemplate<K, V>  redisTemplate;
	
	public void set(K key, V value) {
		
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("redis set fail : " + e.getMessage());
		}
	}
	public void set(K key, V value, long timeout, TimeUnit unit) {
		
		try {
			redisTemplate.opsForValue().set(key, value,timeout,unit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("redis set fail : " + e.getMessage());
		}
	}
	public void delete(K key) {
		try {
			redisTemplate.delete(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("redis delete fail : " + e.getMessage());
		}
	}
	
	public void create(K key, V value) {
		
		try {
			redisTemplate.opsForValue().set(key, value,30,TimeUnit.MINUTES);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("redis set fail : " + e.getMessage());
		}
	}
	
	public Session readSession(K key) {
		
		Session session = null;
		try {
			session = (Session)redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("RedisSessionDao read session occurs one error: " + e.getMessage());
		}
		return session;
	}
	public Collection<V> getList(K pattern ){
		
		Set<V> session = new HashSet<V>();
		Set<String> keys = new HashSet<String>();
		
		try {
			keys = (Set<String>)redisTemplate.keys(pattern);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Get keys occurs one error: " + e.getMessage());
		}
		for(String str:keys) {
			try {
				 V value = redisTemplate.opsForValue().get(str);
				 session.add(value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("RedisSessionDao read session occurs one error: " + e.getMessage());
			}
		}
		
		
		return session;
	}
}
