package org.lanmei.common;

/**
 * 用户相关的状态码
 * @author lgj
 *
 */
public enum UserStatus {
	PHONE_NUM_REGISTER,      //该电话号码已经注册
	PHONE_NUM_NOT_REGISTER,  //该电话号码未注册
	USER_EXIST,              //用户存在
	USER_NOT_EXIST,			 //用户不存在
	REGISTER_SUCCESS,        //注册成功
	REGISTER_FAIL,           //注册失败
	LOGIN_SUCCESS,           //登录成功
	LOGIN_FAIL,              //登陆失败
}
