package org.lanmei.myenum;
/**
 * 性别
 * @author lgj
 *
 */
public enum AccountState {
	/**
	 * 待激活
	 */
	UNACTIVATE,
	/**
	 * 已经激活
	 */
	ACTIVATED,
	/**
	 * 正常状态
	 */
	NORMAL,
	/**
	 * 登录异常
	 */
	LOGIN_EXCEPTION,
	/**
	 * 禁止登陆
	 */
	LOGIN_FORBID,
	/**
	 * 注销状态
	 */
	ACCOUNT_CLOSE,
	/**
	 * 邮箱待激活
	 */
	EMAIL_UNACTIVATE,
	/**
	 * 已经激活
	 */
	EMAIL_ACTIVATED,
}
