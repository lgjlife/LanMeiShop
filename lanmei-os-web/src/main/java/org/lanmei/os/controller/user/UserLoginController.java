package org.lanmei.os.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 处理用户登录，注册请求Controller
 * @author lgj
 * @date:2018/05/17
 */
@Api(value="/user-login",description="处理用户登录Controller")
@Controller
@RequestMapping("/user-login")
public class UserLoginController {

	private final static Logger logger = LoggerFactory.getLogger("UserLoginController.class");	
	{
		logger.debug("UserLoginController Created Bean............. ");
	}
	
	
	
	@ApiOperation(value="/user-login",httpMethod="GET")
	@RequestMapping(path="/find-password")
	public String  findPassword() {
		
		logger.debug("into user-login/find-password");
		return "/user/find-password"; 
	}
	
	@ApiOperation(value="/user-login",httpMethod="GET")
	@RequestMapping(path="/test")
	public String  loginTest() {
		
		logger.debug("into user-login/user-test");
		return "/user/login"; 
	}
	@ApiOperation(value="/user-login",httpMethod="GET")
	@RequestMapping(method=RequestMethod.GET)
	public String  loginPage() {
		
		logger.debug("into /user-login");
		return "/user/login"; 
	}
}
