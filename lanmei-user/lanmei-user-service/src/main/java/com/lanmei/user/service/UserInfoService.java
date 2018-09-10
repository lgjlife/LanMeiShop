package com.lanmei.user.service;

import com.lanmei.common.code.UserReturnCode;

import java.util.Map;

/**
 * @program: com-lanmei-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:;
 * @create: 2018-09-05 16:11
 **/
public interface UserInfoService {

     public String sendValidateCode(String phoneNum);
     public Map<String, String> getKeyModAndExp();
     public boolean isRegistered(String name);

     public UserReturnCode resetPassword(Map<String , String> map);


}
