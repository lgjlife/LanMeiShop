package org.lanmei.user.service;

import org.lanmei.user.dao.model.OsUser;

public interface UserService {

	/**
	 * 根据userId获取用户信息
	 * @param userId
	 * @return
	 */
	OsUser getById(Long userId); 
}
