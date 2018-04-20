package org.lanmei.shiroTest;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {
	
	 private static final transient Logger log = LoggerFactory.getLogger("ShiroTest.class");
	 
	//@Test
	public  static void MyShiro() {
		log.debug("This a shiro test");
		
		Ini ini = new Ini();
		
		/*enable the shiro*/
		//1.
		Factory<SecurityManager> factory =(Factory) new IniSecurityManagerFactory("classpath:shiro/shirotest.ini");
		//2.
		SecurityManager securityManager = factory.getInstance();
		//3.
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject currentUser = SecurityUtils.getSubject();
		
		Session session = currentUser.getSession();
		PrintSession(session);
		session.setAttribute("somekey","avalue");
		
		log.debug("currentUser.isAuthenticated() = " + currentUser.isAuthenticated() );
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
			token.setRememberMe(true);		
			try {
			    currentUser.login( token );
			    //if no exception, that's it, we're done!
			} catch ( UnknownAccountException ex ) {
				log.debug("UnknownAccountException--- " + ex.toString());
			    //username wasn't in the system, show them an error message?
			} catch ( IncorrectCredentialsException ex ) {
			    //password didn't match, try again?
				log.debug("IncorrectCredentialsException--- " + ex.toString());
			} catch ( LockedAccountException ex ) {
			
				//account for that username is locked - can't login.  Show them a message?
				log.debug("LockedAccountException--- " + ex.toString());
			}
			 catch ( AuthenticationException ex ) {
			    //unexpected condition - error?
				log.debug("AuthenticationException---  " + ex.toString());
			}
			log.debug("User [" + currentUser.getPrincipal() + "] logged in successfully." );
			
			if ( currentUser.hasRole( "schwartz" ) ) {
			    log.info("May the Schwartz be with you!" );
			} else {
			    log.info( "Hello, mere mortal." );
			}
			if ( currentUser.isPermitted( "lightsaber:weild" ) ) {
			    log.info("You may use a lightsaber ring.  Use it wisely.");
			} else {
			    log.info("Sorry, lightsaber rings are for schwartz masters only.");
			}
			if ( currentUser.isPermitted( "winnebago:drive:eagle5" ) ) {
			    log.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
			                "Here are the keys - have fun!");
			} else {
			    log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
			}
			
			//currentUser.logout(); //removes all identifying information and invalidates their session too.
		}
		
		
	}
	
	public static void PrintSession(Session session) {
		System.out.println(" id = " +  session.getId() 
							+ "\r\n timeout = "  + session.getTimeout() 
							+ "\r\n startime =  " +  session.getStartTimestamp()
							+ "\r\n lastaccess = " + session.getLastAccessTime());
	}
}
