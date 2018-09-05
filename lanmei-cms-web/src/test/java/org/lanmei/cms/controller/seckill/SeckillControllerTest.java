package com.lanmei.cms.controller.seckill;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lanmei.cms.controller.seckill.SeckillController;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
	
	//@org.junit.Test  
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
	///@org.junit.Test  
    public void toTestMap() throws Exception{  
		
		System.out.println("SeckillControllerTest toTestMap 开始进行测试");  
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
	
    /**
     * @throws Exception
     * 测试新建秒杀活动 
     */
	@org.junit.Test   
    public void newSeckillTest() throws Exception{  
		
		System.out.println(" SeckillControllerTest newSeckillTest 开始进行测试");  
    
          
        Map<String,Object> map = new HashMap<String,Object>();   
        map.put("seckillName", "zhangsan");
        map.put("stockCount", "1234");
        
        
        int count = 0;
        for(count = 0; count < 6 ;count++) {
        	
        Long addTime1 = 5*24*60*60*1000L;
        Long addTime2 = 5*24*60*60*1000L + 1*60*60*1000;
        if(count == 0) {
        	 //创建还未结束秒杀活动
        	 map.put("seckillName", "2000元秒杀iphone 5 还未开始");
             map.put("stockCount", "1000");
             
             Date date = new Date();
             Long time  = date.getTime();
             map.put("startTime", (time + addTime1));
             map.put("finishTime", (time + addTime2));
        }
        else if(count == 1) {
       	 //创建还未结束秒杀活动
       	 map.put("seckillName", "3000元秒杀mate 9  还未开始");
            map.put("stockCount", "2000");
            
            Date date = new Date();
            Long time  = date.getTime();
            map.put("startTime", (time + addTime1));
            map.put("finishTime", (time + addTime2));
        }
        else if(count == 2) {
          	 //创建已经结束秒杀活动
          	 map.put("seckillName", "50元秒杀小米充电宝 已经结束");
               map.put("stockCount", "400");
               
               Date date = new Date();
               Long time  = date.getTime();
               map.put("startTime", (time - addTime2));
               map.put("finishTime", (time - addTime1));
        }
        else if(count == 3) {
         	 //创建已经结束秒杀活动
         	 map.put("seckillName", "400秒杀三星内存条 已经结束");
              map.put("stockCount", "500");
              
              Date date = new Date();
              Long time  = date.getTime();
              map.put("startTime", (time - addTime2));
              map.put("finishTime", (time - addTime1));
        }
        else if(count == 4) {
        	 //创建正在进行的活动
        	 map.put("seckillName", "400秒杀三星内存条 正在进行");
             map.put("stockCount", "500");
             
             Date date = new Date();
             Long time  = date.getTime();
             map.put("startTime", (time - addTime2));
             map.put("finishTime", (time + addTime1));
       }
        else if(count == 5) {
       	 //创建正在进行的活动
       	 map.put("seckillName", "400秒杀三星内存条 正在进行");
            map.put("stockCount", "500");
            
            Date date = new Date();
            Long time  = date.getTime();
            map.put("startTime", (time - addTime2));
            map.put("finishTime", (time + addTime1));
      }
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("=====客户端发送的数据:" + jsonObject.toString());  		  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( post("/seckill/new/seckill")
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
        		.content(jsonObject.toString()))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();//.getResponse().getContentAsString(); 
        System.out.println("=====客户端获得反馈数据:" + mvcResult.getModelAndView().getModelMap().get("seckillState"));            
        }
     }
	/**
	 * @throws Exception
	 * 测试删除秒杀任务
	 */
	//@org.junit.Test  
    public void deleteSeckillTest() throws Exception{  
		
		System.out.println("deleteSeckillTest 开始进行测试");  
    
          
        Map<String,Object> map = new HashMap<String,Object>();   
        //设置需要删除的id
        map.put("seckillId", 1);
        
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("=====客户端发送的数据:" + jsonObject.toString());  		  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( post("/seckill/delete/seckill")
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
        		.content(jsonObject.toString()))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();//.getResponse().getContentAsString(); 
        System.out.println("=====客户端获得反馈数据:" + mvcResult.getModelAndView().getModelMap().get("deleteState"));            
    }
	
	/**
	 * @throws Exception
	 * 获取已经结束的秒杀活动列表
	 */
	//@org.junit.Test  
    public void getSeckillListFinishTest() throws Exception{  
		
		System.out.println("getSeckillListFinishTest 开始进行测试");  
	  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( get("/seckill/get/finish/list")
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();//.getResponse().getContentAsString(); 
        //System.out.println("=====客户端获得反馈数据:" + mvcResult.getModelAndView().getModelMap().get("deleteState"));            
    }
	/**
	 * @throws Exception
	 * 获取还未结束的秒杀活动列表
	 */
	//@org.junit.Test  
    public void getSeckillListUnFinishTest() throws Exception{  
		
		System.out.println("getSeckillListUnFinishTest 开始进行测试");  
	  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( get("/seckill/get/unfinish/list")
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();//.getResponse().getContentAsString(); 
        //System.out.println("=====客户端获得反馈数据:" + mvcResult.getModelAndView().getModelMap().get("deleteState"));            
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