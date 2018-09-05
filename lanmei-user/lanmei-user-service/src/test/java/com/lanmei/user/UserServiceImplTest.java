package com.lanmei.user;

import com.lanmei.user.dao.model.OsUser;
import com.lanmei.user.impl.UserServiceImpl;
import org.junit.Test;

import java.util.Date;


public class UserServiceImplTest {

	@Test
	public void registerTest() {
		OsUser user = new OsUser() ;
		
		user.setPhoneNum("13925716741");
		user.setLoginPassword("loginPassword");
		user.setSalt("salt");
		user.setRegisterTime(new Date());
		
		UserServiceImpl userServiceImpl  = new UserServiceImpl();
		
		userServiceImpl.register(user);
	}
}
