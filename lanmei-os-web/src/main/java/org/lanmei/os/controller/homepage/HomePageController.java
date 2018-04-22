package org.lanmei.os.controller.homepage;

import org.lanmei.os.common.session.SessionUtils;
import org.lanmei.user.dao.model.OsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;

@Api(value="/",description = "主界面 controller")
@Controller
@RequestMapping("/")
public class HomePageController {
	
	private final static Logger logger = LoggerFactory.getLogger("UserLoginController.class");	
	{
		logger.debug("UserLoginController Created Bean............. ");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView HomePage() {
		logger.debug("into 主界面 ");
		
		OsUser user=(OsUser) SessionUtils.getSession("currenLogintUser");
		if(user != null) {
			logger.debug("当前登录的用户号码为 = " + user.getUserId());
		}
		else {
			logger.debug("当前无用户登录 ");
		}
		OsUser user1 = new OsUser();
		user1.setUserId(1235);
		Integer num = 465;
		ModelAndView mv = new ModelAndView("/homepage/HomePage");
		mv.addObject("user", user);
		mv.addObject("num", num);
		return mv;
	}
}
