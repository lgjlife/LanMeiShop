package com.lanmei.common.utils.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: com-lanmei-parent
 * @description: Session
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-07 03:32
 **/
public class SessionUtil {

    private final static Logger log = LoggerFactory.getLogger("SessionUtil.class");

    /** 
     * @description: 保存session
     * @param:   key: session key
     *           value : session value
     *           minute : timeout ,unit : minute
     * @return:  
     * @author: Mr.lgj 
     * @date: 9/7/18 
    */ 
    public static void setSession(Object key, Object value,long minute){

        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            if(null != session){

                log.info("session id = " + session.getId());

                session.setTimeout( minute * 60 * 1000);
                session.setAttribute(key, value);

            }
            else{
                log.info("The session of the Key {" + key + " } is null");
            }
        }
        else{
            log.info("currentUser  is null");
        }

    }

    /** 
     * @description:  获取保存的session
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 9/7/18 
    */ 
    public static Object getSession(Object key){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();

            if(null != session){

                Object object = null;
                try {
                    log.info("读取session..............");
                    object = session.getAttribute(key);
                } catch (InvalidSessionException e) {
                    // TODO Auto-generated catch block
                    log.debug("key { " + key +" }获取session 异常,错误为：" + e.getMessage());
                    e.printStackTrace();

                }

                return  object;
            }
            log.info("session is null");
            return null;
        }
        else {
            log.info("currentUser is null");
            return null;
        }
    }

    /**
     * @description:  移除保存的session
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 9/7/18
     */
    public static Object removeSession(Object key){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();

            if(null != session){

                Object object = null;
                try {
                    object = session.removeAttribute(key);
                } catch (InvalidSessionException e) {
                    // TODO Auto-generated catch block
                    log.error("key { " + key +" }移除session 异常,错误为：" + e.getMessage());
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
