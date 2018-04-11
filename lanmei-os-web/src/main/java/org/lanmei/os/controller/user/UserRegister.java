package org.lanmei.os.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import log4j2.log4j2Test;

@Controller
@RequestMapping("/Register")
public class UserRegister {
	
	private final static Logger logger = LoggerFactory.getLogger(log4j2Test.class);
	
	{
		
		System.out.println("UserRegister create bean----------------------");
	}
	@RequestMapping("/Register")
	public String login() {
		
		System.out.println("Register/Register");
		logger.trace("into /login/login");
		return "/user/user_register";
	}

}
