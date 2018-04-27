package org.lanmei.enumDefine;

public enum AdminStatus {
	ADMIN_CREATE_SUCCESS,   /*管理员创建成功*/
	ADMIN_CREATE_FAIL,      /*管理员创建失败*/
	ADMIN_ACCOUNT_UNACTIVATED,  /*管理员等待激活*/
	ADMIN_ACCOUNT_ACTIVATED,    /*管理员已经激活*/
	EMAIL_SENDFAIL,            /*邮箱发送失败*/
	VALIDATE_CODE_ERR,          /*验证码有误*/
	LOGIN_SUCCESS,				/*登录成功*/
	LOGIN_FAIL,					/*登录失败*/
	UNKNOWN_ACCOUNT,           /*未知账户*/
    PASSWORD_ERROR_TOO_MANY,   /*密码错误过多*/
    LOGGIN_LOG_SUCCESS,        /*登陆日志写入成功*/
    LOGGIN_LOG_FAIL,		   /*登陆日志写入失败*/
}
