package org.lanmei.os.controller.user;

import org.lanmei.user.UserServiceImpl;
import org.lanmei.user.dao.model.OsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.Api;

@Api("")
@Controller
@RequestMapping("/user")
public class UserLoginController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	{
		logger.debug("UserLoginController Created Bean............. ");
	}
	
	@Autowired
	UserServiceImpl  userServiceImpl;

	@RequestMapping(path="/info")
	public String  getUserInfoById(){
		logger.debug("into /user/info");
		OsUser user = userServiceImpl.getById(3L);
		System.out.println("PhoneNum = " + user.getPhoneNum());
		return "/user/register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register() {
		logger.debug("INTO /user/register");
		return "/user/register";
	}
}
