package org.lanmei.cms.controller.homepage;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.admin.service.CmsAdminService;
import org.lanmei.cms.common.session.SessionUtils;
import org.lanmei.sysaop.syslog.anno.SyslogAnno;
import org.lanmei.sysaop.time_measurement.anno.TimeMeasurementAnno;
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
	/**
	 * 进入主界面
	 * @return
	 */
	@SyslogAnno(layer="Controller",description="主界面访问")
	@TimeMeasurementAnno(layer="Controller",description="主界面访问")
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView HomePage() {
		logger.debug("into 主界面 ");
		
		/*获取当前登录的用户*/
		CmsAdmin admin =(CmsAdmin) SessionUtils.getSession("currenLogintAdmin");
		if(admin != null) {
			logger.debug("当前登录的用户号码为 = " + admin.getActualName());
		}
		else {
			logger.debug("HomePageController 当前无用户登录 ");
		}		

		shirotest();
		
		ModelAndView mv = new ModelAndView("/homepage/homepage");
		mv.addObject("admin", admin);
		System.out.println("mv name  = " + mv.getViewName());
		return mv;
	}
	
	@RequestMapping("/Unauthorize")
	public String Unauthorize() {
		logger.debug("into /Unauthorize ");
		return "/shiro/Unauthorize";
	}
	@RequiresRoles("user")
	@RequestMapping(path="/shirotest",method=RequestMethod.GET)
	public ModelAndView shirotest() {
		logger.debug("进入角色-权限");
		shiro();
		ModelAndView mv = new ModelAndView("/homepage/homepage");		
		return mv;
	}
	
	@RequiresRoles("usertest")
	@RequiresPermissions("sada")
	@RequestMapping(path="/shirotest1",method=RequestMethod.GET)
	public ModelAndView shirotest1() {
		logger.debug("进入角色-权限  1");
		shiro();
		ModelAndView mv = new ModelAndView("/homepage/homepage");		
		return mv;
	}
	
	@RequiresRoles("usertest1")
	@RequiresPermissions("sad1")
	public ModelAndView shiro() {
		logger.debug("进入角色-权限 shiro");
		ModelAndView mv = new ModelAndView("/homepage/homepage");		
		return mv;
	}

}


