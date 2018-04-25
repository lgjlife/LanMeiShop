package org.lanmei.cms.common.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.lanmei.cms.cache.RedisTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 用于将session持久化到Redis
 * @author lgj
 *
 */
public class RedisSessionDao extends AbstractSessionDAO {

	/*用作key的前缀，key = keyPrefix + session_id*/
	private String keyPrefix = "SHIRO_REDIS_SESSION";
	@Autowired
	RedisTemplateUtils<String, Object>  redisTemplateUtils;
	
	private final static Logger logger = LoggerFactory.getLogger("RedisSessionDao.class");	
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
		//logger.debug("更新session,session id = {}",session.getId().toString());
		
		redisTemplateUtils.set(getKey(session.getId().toString()), session);
	}

	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		//logger.debug("删除session,session id = {}",session.getId().toString());
		redisTemplateUtils.delete(getKey(session.getId().toString()));
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		//logger.debug("获取活动的session");
		Set<String> sessionKey = new HashSet<String>();
		/*sessions = redisTemplate.keys(keyPrefix +_"*");*/
		redisTemplateUtils.getList(keyPrefix + "*");
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);  
		//logger.debug("更新session,session id = {}",session.getId().toString());
		redisTemplateUtils.create(getKey(session.getId().toString()), session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		//logger.debug("获取session,session id = {}",sessionId.toString());	
		Session session = redisTemplateUtils.readSession(getKey(sessionId.toString()));
		if(session != null) {
		//	logger.debug(" doReadSession redis 获取session,session id = {}",session.getId().toString());
		}
		
		
		/*Set<String> keys = new HashSet<String>();*/
		/*Collection<Object> keys = session.getAttributeKeys();
		for(Object str:keys) {
			logger.debug("key = " + str + "  value = " + session.getAttribute(str));
		}*/
		return session;
	}

	private String  getKey(String value) {
		return (keyPrefix + value);
	}

	
}
