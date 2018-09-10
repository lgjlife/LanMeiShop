package com.lanmei.common.code;

/**
 * @program: shiro
 * @description: 用户相关枚举类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 01:06
 **/
public enum UserReturnCode implements  ReturnCode{


    //空参数 0
    NULL_POINTER(0,"输入参数无效"),

    //验证码 100 - 109
    VALIDATE_CODE_CHECK_PASS(100,"验证码校验通过"),
    VALIDATE_CODE_CHECK_FAIL(101,"验证码校验失败"),

    //登录相关　1000-1029
    LOGIN_SUCCESS(1000,"用户登录成功"),
    LOGIN_FAIL(1001,"用户登录失败"),
    LOGIN_PASSWORD_ERR(1002,"用户登录失败,密码错误"),
    LOGIN_LOCK_ACCOUNT(1003,"用户登录失败，账户被锁定"),
    LOGIN_PASSWORD_ERR_MORE(1004,"用户登录失败，密码错误过多"),
    LOGIN_GET_KEYPAIR_FAIL(1005,"登陆时获取keypair失败"),
    LOGIN_GET_KEYPAIR_SUCCESS(1006,"登陆时获取keypair成功"),
    LOGIN_UNKNOW_ACCOUT(1007,"帐号不存在"),

    //注册相关　1030-1059
    ACCOUNT_EXIST(1030,"帐号注册失败,帐号已经存在"),
    NAME_EXIST(1031,"该用户名已经存在"),
    PHONE_NUM_EXIST(1032,"该手机号已经注册"),
    EMAIL_EXIST(1033,"该邮箱已经注册"),
    CAN_REGISTER(1034,"帐号未注册，可以进行注册"),

    REGISTER_SUCCESS(1035,"帐号注册成功"),
    REGISTER_FAIL(1036,"帐号注册失败"),
    REGISTER_GET_VALIDATE_CODE_SUCCESS(1037,"获取注册验证码成功"),


    ACCOUNT_REGISTER(1038,"该帐号已经注册"),
    ACCOUNT_NOT_REGISTER(1039,"该帐号未注册"),
    SEND_VALIDATE_CODE_SUCCESS(1040,"获取验证码成功"),
    SEND_VALIDATE_CODE_FAIL(1041,"获取验证码失败"),


    //格式校验 1060 -- 1079
    FORMAT_PHONE_NUM_ERR (1060,"手机号格式有误"),
    FORMAT_EMAIL_ERR (1061,"邮箱地址格式有误"),



    //INFO 1080-2010
    INFO_RESET_PASSWORD_SUCCESS (1080,"重置密码成功"),
    INFO_RESET_PASSWORD_FAIL (1081,"重置密码失败"),
    ;

    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    UserReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }




}
