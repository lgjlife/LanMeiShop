package com.lanmei.user.service;

import com.lanmei.user.dao.model.OsUserLogin;

/**
 * @program: com-lanmei-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-05 16:36
 **/
public interface OsUserLoginService {


     Integer setLoginLog(Integer userId, OsUserLogin userLogin) ;


     Integer addLoginLog(Integer userId, OsUserLogin userLogin) ;
}
