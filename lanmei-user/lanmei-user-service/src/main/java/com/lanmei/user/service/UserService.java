package com.lanmei.user.service;

import com.lanmei.user.common.UserStatus;
import com.lanmei.user.dao.model.OsUser;

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

     UserStatus checkPhoneNum(String phoneNum);
     OsUser getUser(String nickName,String phoneNum,String email);
     UserStatus register(OsUser osuser ) ;
     void sendMsg(String phoneNum,String code ) ;
     OsUser queryUser(String name);

}
