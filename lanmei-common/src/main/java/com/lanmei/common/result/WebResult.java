package com.lanmei.common.result;

import com.lanmei.common.code.ReturnCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: shiro
 * @description: web层返回类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 00:58
 **/
@Setter
@Getter
public class WebResult extends BaseResult{

    private static final long serialVersionUID = 1L;

    public WebResult(ReturnCode code) {
        super(code.getCode(), code.getMessage());
    }

    public WebResult(ReturnCode code, Object object) {
        super(code.getCode(), code.getMessage(),object);
    }
}
