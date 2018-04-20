package org.lanmei.os.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;

@Api("测试")
@Controller
@RequestMapping("/test")
public class LanmeiTest {
	
	private final static Logger logger = LoggerFactory.getLogger("LanmeiTest.class");
	
	{		
		logger.debug("Create LanmeiTest bean ----");
	}
	
	@RequestMapping
	public String getTestView() {
		
		logger.debug("LanmeiTest into ----");
		return "/test/test";
	}
	
	@RequestMapping("/responsbody")
	@ResponseBody
	public String ResponseBodyTest() {
		
		logger.debug("ResponseBodyTest into ----");
		/*页面直接显示 ResponseBody test */
		return "ResponseBody test";
	}
}