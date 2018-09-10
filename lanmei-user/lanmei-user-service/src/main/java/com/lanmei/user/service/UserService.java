package com.lanmei.user.service;

import com.lanmei.common.code.UserReturnCode;
import com.lanmei.user.dao.model.OsUser;

import java.util.Map;

/**
 * @program: com-lanmei-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-05 16:11
 **/
public interface UserService {
     OsUser getById(Long userId);

     boolean isRegisterOfPhoneNum(String phoneNum);
     UserReturnCode register(Map<String, Object> inputMap);
     Map<String,String> getKeyModAndExp();

     String getPhoneValidateCode( String phoneNum);

     OsUser getUser(String nickName,String phoneNum,String email);

     OsUser queryUser(String name);

     public boolean isRegistered(String name);

}
