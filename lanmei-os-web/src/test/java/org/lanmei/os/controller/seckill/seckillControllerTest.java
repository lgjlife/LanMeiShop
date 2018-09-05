package com.lanmei.os.controller.seckill;

import com.lanmei.seckill.dao.model.Seckill;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类  
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等  
@ContextConfiguration(locations = {"classpath*:springmvc/spring-mvc.xml",//加载Spring配置文件  
		                           "classpath*:spring/spring-context.xml"})//加载Spring-mvc配置文件  
public class seckillControllerTest {

	@Autowired  
	SeckillController seckillController;  
	  
	@Autowired  
	ServletContext context;  
	  
	MockMvc mockMvc;  
	@Before  
	public void setup(){  
	    mockMvc = MockMvcBuilders.standaloneSetup(seckillController).build();  
	}  
	/**
	 * 获取秒杀列表
	 * @throws Exception
	 */
	//@org.junit.Test  
	public void testGetList() throws Exception{
		System.out.println("testGetList 开始进行测试");  
		  
		
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( get("/seckill/list")
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();//.getResponse().getContentAsString(); 
        //System.out.println("=====客户端获得反馈数据:" + mvcResult.getModelAndView().getModelMap().get("deleteState"));            
        List<Seckill> seckills = (List<Seckill>)mvcResult.getModelAndView().getModel().get("seckill");
        
        for(Seckill sec : seckills) {
        	System.out.println("seckill = " + sec.toString() );
        }
        
        while(true);
	}
	/**
	 * 获取详情
	 * @throws Exception
	 */
	@org.junit.Test  
	public void testDetail() throws Exception{
		System.out.println("testDetail 开始进行测试");  
		  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( get("/seckill/{seckillId}/detail",16)
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();//.getResponse().getContentAsString(); 
        //System.out.println("=====客户端获得反馈数据:" + mvcResult.getModelAndView().getModelMap().get("deleteState"));            
        Seckill seckill = (Seckill)mvcResult.getModelAndView().getModel().get("seckill");
        System.out.println("seckill = " + seckill.toString() );

        
	}
	
	/**
	 * 获取时间
	 * @throws Exception
	 */
	//@org.junit.Test  
	public void tesGetTime() throws Exception{
		System.out.println("tesGetTime 开始进行测试");  
		  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( get("/seckill/get/time")
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();//.getResponse().getContentAsString(); 
        //System.out.println("=====客户端获得反馈数据:" + mvcResult.getModelAndView().getModelMap().get("deleteState"));            
       
        /*Long currentTime = (Long)mvcResult.getModelAndView().getModel().get("currentTime");
        System.out.println("currentTime = " + currentTime);*/

      /*  mvcResult.getResponse().*/
	}
	/**
	 * 获取秒杀地址测试
	 * @throws Exception
	 */
	//@org.junit.Test  
	public void  testGetExposer() throws Exception{
		System.out.println("testGetExposer 开始进行测试");  
		  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( get("/seckill/{seckillId}/exposer",13)
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
	}
	/**
	 * 执行秒杀测试
	 * @throws Exception
	 */
	//@org.junit.Test  
	public void  testExecuteSeckill() throws Exception{
		System.out.println("testGetExposer 开始进行测试");  
		  
        //发送请求  
         	
        MvcResult mvcResult = mockMvc.perform( post("/seckill/{seckillId}/{md5}/execute",5,"adsdsad")
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        
     
	}

}
