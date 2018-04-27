package org.lanmei.cms.controller.homepage;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.admin.service.CmsAdminService;
import org.lanmei.cms.common.session.SessionUtils;
import org.lanmei.user.dao.model.OsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;

@Api(value="/",description = "主界面 controller")
@Controller
@RequestMapping("/")
public class HomePageController {
	
	private final static Logger logger = LoggerFactory.getLogger("HomePageController.class");	
	{
		logger.debug("HomePageController Created Bean............. ");
	}
	
	@Autowired
	CmsAdminService  adminService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView HomePage() {
		logger.debug("into 主界面 ");
		
		CmsAdmin admin =(CmsAdmin) SessionUtils.getSession("currenLogintAdmin");
		if(admin != null) {
			logger.debug("当前登录的用户号码为 = " + admin.getActualName());
		}
		else {
			logger.debug("HomePageController 当前无用户登录 ");
		}
       List<CmsAdmin>  adminList = adminService.getAllAdmin(1);
       if(adminList != null) {
    	   for(CmsAdmin ad:adminList) {
    		   System.out.println(ad.getActualName());
    	   }
       }
		

		ModelAndView mv = new ModelAndView("/homepage/homepage");
		mv.addObject("admin", admin);
		
		return mv;
	}
	@RequestMapping(value="/redistest",method=RequestMethod.GET)
	public ModelAndView redis() {
		logger.debug("\r\n-------/redistest");
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("testredis","testvalue");
		logger.debug("\r\n-------获取的session value "+session.getAttribute("testredis"));
		
		OsUser user = new OsUser();
		user.setUserId(12);
		session.setAttribute("testUser",user);
		
		
		
		ModelAndView mv = new ModelAndView("/homepage/HomePage");

		return mv;
	}
}
