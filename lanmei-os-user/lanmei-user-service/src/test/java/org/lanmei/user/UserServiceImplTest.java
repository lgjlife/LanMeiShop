package org.lanmei.user;

import java.util.Date;

import org.junit.Test;
import org.lanmei.user.dao.model.OsUser;

public class UserServiceImplTest {

	@Test
	public void registerTest() {
		OsUser   user = new OsUser() ;
		
		user.setPhoneNum("13925716741");
		user.setLoginPassword("loginPassword");
		user.setSalt("salt");
		user.setRegisterTime(new Date());
		
		UserServiceImpl userServiceImpl  = new UserServiceImpl();
		
		userServiceImpl.register(user);
	}
}
