package com.lanmei.os.controller.homepage;

import com.lanmei.common.utils.session.SessionKeyUtil;
import com.lanmei.common.utils.session.SessionUtil;
import com.lanmei.sysaop.syslog.anno.PrintUrlAnno;
import com.lanmei.user.dao.model.OsUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @description:   主页Controllet
 * @param:  
 * @return:  
 * @author: Mr.lgj 
 * @date: 9/6/18 
*/ 
@Api(value="/",description = "主界面 controller")
@Controller("os-HomePageController")
@RequestMapping(path="/")
public class HomePageController {
	
	private final static Logger logger = LoggerFactory.getLogger("UserLoginController.class");	
	{
		logger.debug("HomePageController Created Bean............. ");
	}
	
	/** 
	 * @description: 主页面请求
	 * @param:
	 * @return:   带当前登陆的user对象的 ModelAndView
	 * @author: Mr.lgj 
	 * @date: 9/6/18 
	*/

	@ApiOperation(value = "/",httpMethod="GET",notes = "os-web 主界面请求")
	@GetMapping
	//@SyslogAnno(description = "os-web 主界面请求")
	@PrintUrlAnno(description = "os-web 主界面请求")
	public ModelAndView HomePage() {
		logger.info("into 主界面 /");
		
		OsUser user=(OsUser) SessionUtil.getSession(SessionKeyUtil.currentLoginUser);
		if(user != null) {
			logger.info("当前登录的用户号码为 = " + user.getUserId());
		}
		else {
			logger.info("HomePageController 当前无用户登录 ..............................");
            user = new OsUser();
            user.setActualName("我是一个用户");
		}

		ModelAndView mv = new ModelAndView("/homepage/homepage");
		mv.addObject("user", user);

		return mv;
	}
}
