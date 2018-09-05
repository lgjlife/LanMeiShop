package com.lanmei.os.common.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionUtils {
	
	private final static Logger logger = LoggerFactory.getLogger("SessionUtils.class");
	
	public static void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }  
	
	public static Object getSession(Object key){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){ 
            	
            	Object object = null;
				try {
					object = session.getAttribute(key);
				} catch (InvalidSessionException e) {
					// TODO Auto-generated catch block
					logger.debug("key 未存在,错误为：" + e.getMessage());
					e.printStackTrace();
				} 
            	
                return  object;
            }  
            return null;
        }  
        else {
        	return null;
        }
    } 
}
