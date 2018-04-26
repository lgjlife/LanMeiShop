package org.lanmei.enumDefine;

public enum AdminStatus {
	ADMIN_CREATE_SUCCESS,   /*管理员创建成功*/
	ADMIN_CREATE_FAIL,      /*管理员创建失败*/
	ADMIN_ACCOUNT_UNACTIVATED,  /*管理员等待激活*/
	ADMIN_ACCOUNT_ACTIVATED,    /*管理员已经激活*/
	EMAIL_SENDFAIL,            /*邮箱发送失败*/
}
