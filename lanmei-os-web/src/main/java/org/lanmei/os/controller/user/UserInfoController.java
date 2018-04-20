package org.lanmei.os.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.imageio.spi.RegisterableService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.lanmei.common.UserStatus;
import org.lanmei.user.UserServiceImpl;
import org.lanmei.user.dao.model.OsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import net.sf.json.JSONObject;

/**
 * 处理用户登录，注册请求Controller
 * @author lgj
 * @date:2018/05/17
 */
@Api(value="/user/info",description="处理用户信息修改Controller")
@Controller
@RequestMapping("/user-info")
public class UserInfoController {
	
	
	private final static Logger logger = LoggerFactory.getLogger("UserLoginController.class");	
	{
		logger.debug("UserInfoController Create Bean............. ");
	}
	
	
	@ApiOperation(value="/user-login",httpMethod="GET",notes="请求个人中心页面")
	@RequestMapping(method=RequestMethod.GET)
	public String  loginPage() {
		
		logger.debug("into /user-info");
		return "/user/info"; 
	}
	@ApiOperation(value="/user-login",httpMethod="GET",notes="请求个人中心页面")
	@RequestMapping(path="/setting", method=RequestMethod.GET)
	public String  infoSeting() {
		
		logger.debug("into /user-info/setting");
		return "/user/info-setting"; 
	}
}
