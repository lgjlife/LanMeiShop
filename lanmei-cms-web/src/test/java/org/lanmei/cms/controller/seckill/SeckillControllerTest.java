package org.lanmei.cms.controller.seckill;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类  
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等  
@ContextConfiguration(locations = {"classpath*:springmvc/spring-mvc.xml","classpath*:spring/spring-context.xml"})//加载Spring配置文件  
public class SeckillControllerTest  {
	
	@Autowired  
	SeckillController seckillController;  
	  
	@Autowired  
	ServletContext context;  
	  
	MockMvc mockMvc;  
	@Before  
	public void setup(){  
	    mockMvc = MockMvcBuilders.standaloneSetup(seckillController).build();  
	}  
	
	@org.junit.Test  
    public void toTestObject() throws Exception{  
		
		System.out.println("toTestObject 开始进行测试");  
        //准备参数  
        String postJson = "{\"mac\":\"h\",\"dtClient\":\"2015-06-03 13:20:10\"}";  
          
        SoftInfo softInfo = new SoftInfo();
        softInfo.setId("123");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(softInfo);
        System.out.println("=====客户端发送的数据:" + requestJson);  		  
        //发送请求  

        String responseString = mockMvc.perform( post("/seckill/test/object")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(requestJson)).andDo(print())
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString(); 
;//param("criJson",postJson));  
       /* MvcResult mvcResult = resultActions.andReturn();  
        String result = mvcResult.getResponse().getContentAsString();  */
        System.out.println("=====客户端获得反馈数据:" + responseString);            
    }
	@org.junit.Test  
    public void toTestMap() throws Exception{  
		
		System.out.println("toTestMap 开始进行测试");  
        //准备参数  
        String postJson = "{\"mac\":\"h\",\"dtClient\":\"2015-06-03 13:20:10\"}";  
          
        Map<String,Object> map = new HashMap<String,Object>();   
        map.put("id", "456");
        map.put("name", "zhangsan");
        
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("=====客户端发送的数据:" + jsonObject.toString());  		  
        //发送请求  
         	
        String responseString = mockMvc.perform( post("/seckill/test/map")
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
        		.content(jsonObject.toString()))
        		.andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(); 
;//param("criJson",postJson));  
       /* MvcResult mvcResult = resultActions.andReturn();  
        String result = mvcResult.getResponse().getContentAsString();  */
        System.out.println("=====客户端获得反馈数据:" + responseString);            
    }
	
	@org.junit.Test  
    public void newSeckillTest() throws Exception{  
		
		System.out.println("newSeckillTest 开始进行测试");  
        //准备参数  
        String postJson = "{\"mac\":\"h\",\"dtClient\":\"2015-06-03 13:20:10\"}";  
          
        Map<String,Object> map = new HashMap<String,Object>();   
        map.put("seckillName", "zhangsan");
        map.put("stockCount", "1234");
        
        Date date = new Date();
        Long time  = date.getTime();
        map.put("startTime", time);
        map.put("finishTime", time);
        
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("=====客户端发送的数据:" + jsonObject.toString());  		  
        //发送请求  
         	
        String responseString = mockMvc.perform( post("/seckill/new/seckill")
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
        		.content(jsonObject.toString()))
        		.andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(); 
        System.out.println("=====客户端获得反馈数据:" + responseString);            
    }
}
class SoftInfo {
    private String id;
    private String name;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
}