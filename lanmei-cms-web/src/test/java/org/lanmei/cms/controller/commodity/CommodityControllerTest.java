package org.lanmei.cms.controller.commodity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类  
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等  
@ContextConfiguration(locations = {"classpath*:springmvc/spring-mvc.xml",//加载Spring配置文件  
		                           "classpath*:spring/spring-context.xml"})//加载Spring-mvc配置文件 
public class CommodityControllerTest {

	@Autowired  
	CommodityController commodityController;  
	 
	
	
	@Autowired  
	ServletContext context;  
	  
	MockMvc mockMvc;  
	@Before  
	public void setup(){  
	    mockMvc = MockMvcBuilders.standaloneSetup(commodityController).build();  
	}  
	/**
	 * 获取节点数据　测试
	 * @throws Exception
	 */
	//@Test
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
	/**
	 * 增加节点　测试
	 * @throws Exception
	 */
	//@Test
	public void testAddTreeNode() throws Exception{
		System.out.println("testGetTreeNode 开始进行测试");  
		 Map<String,Object> map = new HashMap<String,Object>();  
		 int id = 1;
	     map.put("id", id);
	     map.put("name", "新类目"+id);
	    JSONObject jsonObject = JSONObject.fromObject(map);
        //发送请求           	
        MvcResult mvcResult = mockMvc.perform( get("/commodity/add/node")
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
        		.content(jsonObject.toString()))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
	}
	
	/**
	 * 更改节点　测试
	 * @throws Exception
	 */
	//@Test
	public void testModityTreeNode() throws Exception{
		System.out.println("testModityTreeNode 开始进行测试"); 
		
		 Map<String,Object> map = new HashMap<String,Object>();  
		 int id = 25;
	     map.put("id", id);
	     map.put("name", "修改" + Math.round((Math.random()*100)));
	    JSONObject jsonObject = JSONObject.fromObject(map);
        //发送请求           	
        MvcResult mvcResult = mockMvc.perform( get("/commodity/modify/node")
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
        		.content(jsonObject.toString()))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
	}
	
	/**
	 * 删除节点　测试
	 * @throws Exception
	 */
	@Test
	public void testＤeleteTreeNode() throws Exception{
		System.out.println("testＤeleteTreeNode 开始进行测试");  
		  
		Map<String,Object> map = new HashMap<String,Object>();  
	    int id = 23;
	    map.put("id", id);
	   JSONObject jsonObject = JSONObject.fromObject(map);
       //发送请求           	
       MvcResult mvcResult = mockMvc.perform( get("/commodity/delete/node")
       		.contentType(MediaType.APPLICATION_JSON_UTF8)
       		.content(jsonObject.toString()))
       		.andDo(print())
               .andExpect(status().isOk())
               .andReturn();
	}
}
