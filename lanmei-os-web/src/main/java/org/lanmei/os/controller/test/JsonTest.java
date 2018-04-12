package org.lanmei.os.controller.test;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;

import net.sf.json.JSONObject;

@Api("Json传输测试")
@Controller
@RequestMapping("/json")
public class JsonTest {
	
	private final static Logger logger = LoggerFactory.getLogger(LanmeiTest.class);
	
	{
		
		logger.debug("Create JsonTest Bean..............");
	}
	@RequestMapping("/json")
	@ResponseBody
	public JsonItems  JsonItemsTest(@RequestBody JsonItems item) {
		
		System.out.println(item.getAge() + "\r\n" + item.getUserName() + "\r\n" + item.getPassWord() );
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
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("value1", 123);
		map.put("value2", "lanmei");
		map.put("value3", true);
		
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/form")
	public JSONObject JSONObjectFormTest(@RequestBody JsonItems item ) {
		
		logger.debug("/json/map");
		logger.debug("接收的Json 数据--" + " userName = " + item.getUserName() 
					+ "  passWord = " + item.getPassWord()
					+ "  phoneNum = " + item.getPhoneNum() );
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("userName", item.getUserName());
		map.put("passWord", item.getPassWord());
		map.put("phoneNum", item.getPhoneNum());
		
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
}
