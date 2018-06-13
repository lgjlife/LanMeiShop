package org.lanmei.cms.common.jdbc.encrypt;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanmei.cms.controller.commodity.CommodityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类  
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等  
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})//加载Spring-mvc配置文件 
public class ConvertPwdPropertyConfigurerTest {

	@Autowired  
	CommodityController commodityController;  
	
	MockMvc mockMvc;  
	@Before  
	public void setup(){  
	    mockMvc = MockMvcBuilders.standaloneSetup(commodityController).build();  
	}  
	
	@Test
	public void test() {
		
	}
	
	
	@Test
	public void testGetTreeNode() throws Exception{
		System.out.println("testGetTreeNode 开始进行测试");  
		  
        //发送请求           	
        MvcResult mvcResult = mockMvc.perform( get("/commodity/get/node")
        		.param("id", "0")
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
	}

}
