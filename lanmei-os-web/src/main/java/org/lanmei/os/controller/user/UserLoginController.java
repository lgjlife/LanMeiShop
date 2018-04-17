package org.lanmei.os.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.lanmei.common.UserStatus;
import org.lanmei.sms.SmsDemo;
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
@Api(value="/user",description="处理用户登录，注册请求Controller")
@Controller
@RequestMapping("/user")
public class UserLoginController {
	
	private int loginCount = 0;

	 
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	{
		logger.debug("UserLoginController Created Bean............. ");
	}
	
	@Autowired
	UserServiceImpl  userServiceImpl;
	
	/**
	 * 数据库访问测试
	 * @return
	 */
	@ApiOperation(value="/user/info",notes="数据库访问测试",httpMethod="GET")
	@RequestMapping(path="/info")
	public String  getUserInfoById(){
		logger.debug("into /user/info");
		OsUser user = userServiceImpl.getById(3L);
		System.out.println("PhoneNum = " + user.getPhoneNum());
		return "/user/register";
	}
	/**
	 * 
	 * @return
	 */
	@ApiOperation(value="/user/checkPhoneNum",notes="注册时校验手机号是否已经注册")
	@ResponseBody
	@RequestMapping(path="/checkphonenum",method=RequestMethod.POST)
	public JSONObject checkPhoneNum(@RequestBody OsUser user) {
		UserStatus phoneCheckStatus = null;//
		
		logger.debug("into /user/checkPhoneNum");
		phoneCheckStatus = userServiceImpl.checkPhoneNum(user.getPhoneNum());
			
		Map<String,Object> map = new HashMap<String,Object>();		
		map.put("phoneCheckStatus", phoneCheckStatus);
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	/**
	 * 客户端发送手机号、服务端随机生成6位手机验证码
	 * 将验证码发送给手机
	 * 并通过Ajax返回状态：已经发送验证码
	 */
	@ResponseBody
	@ApiOperation(value="/user/getPhoneValidateNum",notes="客户端发送json请求手机验证码，生成并返回验证码")
	@RequestMapping(value="/get-phone-validate-code",method=RequestMethod.POST)
	public JSONObject getPhoneValidateNum(@RequestBody OsUser user) {
		logger.debug("into /user/get-phone-validate-code"); 
		
		Integer phoneValidateCode = userServiceImpl.getRandom();
		logger.debug("PhoneNum = " + user.getPhoneNum()  + "  phoneValidateCode = " + phoneValidateCode); 
		
		try {
			logger.debug("+++++++++");
			SmsDemo.send();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		userServiceImpl.sendMsg(user.getPhoneNum(), String.valueOf(phoneValidateCode));
		
		Map<String,Object> map = new HashMap<String,Object>();		
		map.put("phoneValidateCode", new String());
		JSONObject json = JSONObject.fromObject(map);
		
		return json;
	}
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register() {
		logger.debug("INTO /user/register");
		/*ShiroTest.MyShiro();*/
		
		logger.debug("This a shiro test");
		
		Ini ini = new Ini();
		
		/*enable the shiro*/
		//1.
/*		Factory<SecurityManager> factory =(Factory) new IniSecurityManagerFactory("classpath:shiro/shirotest.ini");
		//2.
		SecurityManager securityManager = factory.getInstance();
		//3.
		SecurityUtils.setSecurityManager(securityManager);*/
		
		/*logger.debug("访问次数：" + loginCount);
		
		Subject currentUser = SecurityUtils.getSubject();
		
		Session session = currentUser.getSession();
		if(loginCount == 1) {
			session.setAttribute("phone",session.getId());
			logger.debug("session setAttribute..........");
		}
		if(loginCount == 5) {
			session.removeAttribute("phone");
			loginCount = 0;
		}
		
		String phone = (String)session.getAttribute("phone");
		
		if(phone == null) {
			logger.debug("第" + loginCount + "次访问的手机号码："  + "---手机号码为空");
		}
		else {
			logger.debug("第" + loginCount + "次访问的手机号码：" + phone );
		}
		loginCount++;
	
		
		//ShiroTest.PrintSession(session);
		
		System.out.println(" id = " +  session.getId() 
		+ "\r\n timeout = "  + session.getTimeout() 
		+ "\r\n startime =  " +  session.getStartTimestamp()
		+ "\r\n lastaccess = " + session.getLastAccessTime());*/
		
		
		
		
		return "/user/register";
	}
}
