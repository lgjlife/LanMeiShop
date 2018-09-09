package com.lanmei.os.common.shiro;

import com.lanmei.common.redis.Utils.RedisStringsUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 用于将session持久化到Redis
 * @author lgj
 *
 */
public class RedisSessionDao extends CachingSessionDAO {

	private static final Logger log = LoggerFactory.getLogger("ShiroSessionDao");

	//用作key的前缀，key = keyPrefix + session_id
	private String keyPrefix = "SHIRO_REDIS_SESSION:";
	//session 缓存过期时间
	private static  final int cacheTimeMinute = 30;

	@Autowired
	private RedisStringsUtil redisStringsUtil;
	/**
	 * @description:
	 * @param:
	 * @return:
	 * @author: Mr.lgj
	 * @date: 9/1/18
	 */
	@Override
	public void doUpdate(Session session) {
		redisStringsUtil.set(getKey(session.getId().toString()),session,cacheTimeMinute,TimeUnit.SECONDS);
	}

	/**
	 * @description:
	 * @param:
	 * @return:
	 * @author: Mr.lgj
	 * @date: 9/1/18
	 */
	@Override
	public void doDelete(Session session) {
		redisStringsUtil.delete(getKey(session.getId().toString()));

	}

	/**
	 * @description:
	 * @param:
	 * @return:
	 * @author: Mr.lgj
	 * @date: 9/1/18
	 */
	@Override
	public Serializable doCreate(Session session) {

		if(session == null){
			log.info("session is null");
		}
		log.info("doCreate");
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		log.info("session.toString"+session.toString());
		log.info(""+session.getHost());
		log.info(session.getId()+"");
		redisStringsUtil.set(getKey(session.getId().toString()),session,cacheTimeMinute,TimeUnit.SECONDS);
		return sessionId;
	}

	/**
	 * @description:
	 * @param:
	 * @return:
	 * @author: Mr.lgj
	 * @date: 9/1/18
	 */
	@Override
	public Session doReadSession(Serializable serializable) {

		log.info("doReadSession");
		Session session =  (Session)redisStringsUtil.get(getKey(serializable.toString()));
		log.info("doReadSession -- serializable.toString()" + serializable.toString());
		if(session == null){
			log.debug("session is null!");
		}
		return session;
	}

	private String getKey(String sessionId){
		return keyPrefix + sessionId;
	}

	
}
