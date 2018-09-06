package com.lanmei.common.utils;

/**
 * @program: shiro
 * @description: 检测是否为空
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-02 13:45
 **/
public class CheckNullUtil {

    public static Boolean isNullString(String str){
        if((str == null ) || (str.equals(""))){
            return  true;
        }
        return  false;
    }
}
