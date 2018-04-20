package org.lanmei.os.common.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义shiro 的realm 
 * realm：域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，
 * 那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行
 * 验证用户是否能进行操作；可以把Realm看成DataSource，即安全数据源
 * @author lgj
 *
 */
@Component
public class SystemAuthorizingRealm extends AuthorizingRealm  {

	private final static Logger logger = LoggerFactory.getLogger("SystemAuthorizingRealm.class");	
	{
		logger.debug("SystemAuthorizingRealm Create Bean............. ");
	}
	/**
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		logger.debug("into doGetAuthorizationInfo....");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		return info;
	}

	/**
	 * 登录验证
	 */

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.debug("into doGetAuthenticationInfo....");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return null;
	}
	
	
}
