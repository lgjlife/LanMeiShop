package com.lanmei.common.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: shiro
 * @description: 返回接口类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 00:52
 **/
@Getter
@Setter
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;
    //返回代码
    private Integer  code;

    //返回消息
    private String message;

    //返回对象
    private  Object object;

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }


}
