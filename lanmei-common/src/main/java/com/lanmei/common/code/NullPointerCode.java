package com.lanmei.common.code;

/**
 * @program: shiro
 * @description: 空指针
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 01:06
 **/
public enum NullPointerCode implements  ReturnCode{

    NULL_POINT(100,"请求参数为空"),


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
