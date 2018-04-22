package org.lanmei.user.service;

import org.lanmei.user.dao.model.OsUserLogin;

public interface OsUserLoginService {

	 /**
	  * 
	  * @param userId     用户id,用于确认哪个ID 需要更新数据
	  * @param userLogin
	  * @return
	  */
	 Integer setLoginLog(Integer userId,OsUserLogin  userLogin);
	 Integer addLoginLog(Integer userId, OsUserLogin userLogin);
}
