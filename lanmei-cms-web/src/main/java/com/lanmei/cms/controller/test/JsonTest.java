package com.lanmei.cms.controller.test;

import com.lanmei.cms.common.ServletUtils.ServletUtils;
import com.lanmei.user.dao.model.OsUserLogin;
import com.lanmei.user.impl.UserServiceImpl;
import com.lanmei.user.service.OsUserLoginService;
import com.wordnik.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api("Json传输测试")
//@Controller
@RequestMapping("/json")
public class JsonTest{
	

	
	private final static Logger logger = LoggerFactory.getLogger("JsonTest");
	
	{
		
		logger.debug("Create JsonTest Bean..............");
	}
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	OsUserLoginService userLoginService;
	
	//@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping("/json")
	@ResponseBody
	public com.lanmei.cms.controller.test.JsonItems JsonItemsTest(@RequestBody com.lanmei.cms.controller.test.JsonItems item) {
		
		System.out.println(item.getAge() + "\r\n" + item.getUserName() +  "\r\n"  + item.getPassWord() );
		return item;
	}
	@RequestMapping("/debug")
	public String  JsonItemsTest() {
		logger.debug("/json/debug");
		return "/test/test";
	}
	
	@ResponseBody
	@RequestMapping("/map")
	public JSONObject JSONObjectTest() {
		
		logger.debug("/json/map");
		//OsUser user = userServiceImpl.getUserByTelNum("13925716752");
		
		// userServiceImpl.checkPhoneNum("13925716752");.
		//OsUser user = userServiceImpl.getUser(null, "13925716752", null);
		OsUserLogin userLogin = new OsUserLogin();
		logger.debug("RemoteAddr = " + ServletUtils.getAddrIP(request));
		logger.debug("Agent = " + ServletUtils.getAggent(request));

		
		
		
		userLogin.setExplorer("360");
		userLoginService.addLoginLog(23, userLogin);
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("value1", 123);
		map.put("value2", "lanmei");
		map.put("value3", true);
		
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	

}