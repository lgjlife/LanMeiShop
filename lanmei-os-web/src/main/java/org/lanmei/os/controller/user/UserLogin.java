package org.lanmei.os.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value="登录")
@Controller
@RequestMapping("/pass")
public class UserLogin {
	
	String mavobj = "liangguojian";
	
	private final static Logger logger = LoggerFactory.getLogger(UserLogin.class);
	
	{
		logger.debug("UserLogin bean create----------");
	}
	@ApiOperation(value="登录",httpMethod="GET")
	@RequestMapping("/login")
	public String login() {
		logger.debug("into /login/login");
		return "/user/user_register";
	}
	@RequestMapping("/test")
	public ModelAndView test() {
		
		logger.debug("into /login/test");
		ModelAndView mav = new ModelAndView("/user/user_register");	
		mav.addObject("mavobj",mavobj);
		logger.debug(""+ mav);
		return mav;
	}


}
