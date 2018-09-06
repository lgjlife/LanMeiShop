package com.lanmei.common.code;

/**
 * @program: shiro
 * @description: 用户相关枚举类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 01:06
 **/
public enum NullPointerCode implements  ReturnCode{


    //登录相关　1000-1029

    //注册相关　1030-1059
    Null_Pointer(100,"请求参数为空"),


    ;
    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    NullPointerCode(Integer code, String message) {
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
