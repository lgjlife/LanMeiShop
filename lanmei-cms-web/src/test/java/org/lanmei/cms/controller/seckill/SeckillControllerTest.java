package org.lanmei.cms.controller.seckill;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.serviceImpl.SeckillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
//@TransactionConfiguration( transactionManager = "transactionManager",defaultRollback = true)
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)//此处调用Spring单元测试类
@WebAppConfiguration  //调用javaWEB的组件，比如自动注入
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml","classpath:springmvc/spring-mvc.xml"})//加载spring容器
public class SeckillControllerTest {

	@Resource
    private SeckillController seckillController;
	//Spring提供的测试类
    private MockMvc mockMvc;    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    
	//@Test
	public void testSeckillController() {
		System.out.println("test SeckillController");
		
		MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
		
		Date date = new Date(2018, 5, 1, 10, 0);
		params.add("seckillName", "100元秒杀充电宝");
		params.add("stockCount", "999");
		params.add("startTime", date.getTime()+"");
		params.add("finishTime", date.getTime()+"");
		ResultActions resultActions = null;
		try {
			resultActions = this.mockMvc.perform(get("/seckill/test")
					.accept(MediaType.APPLICATION_JSON).param("seckillName","100元秒杀充电宝"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//MvcResult是获得服务器的Response内容。
        String result = null;
		try {
			MvcResult mvcResult = resultActions.andReturn();
			result = mvcResult.getResponse().getContentAsString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("*******:" + result);
	}
	  @Test 
	  public void mytest() {
		  Seckill seckill = new Seckill("手机秒杀", 100, new Date(), new Date(), new Date());
			System.out.println("seckill = " + seckill.getCreateTime());
			SeckillServiceImpl seckillService = new SeckillServiceImpl();
			seckillService.createSeckill(seckill);
	  }
	    /*@Test
	    public void getAllCategoryTest(){
	    	
	    	 System.out.println("getAllCategoryTest");
	        String responseString = null;
			try {
				responseString = mockMvc.perform(
				        get("/seckill/test")    //请求的url,请求的方法是get
				        .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //数据的格式
				        .param("pcode","root")         //添加参数
				).andExpect(status().isOk())    //返回的状态是200
				        .andDo(print())         //打印出请求和相应的内容
				        .andReturn().getResponse().getContentAsString();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("--------返回的json = " + responseString);
	    }
	*/

}
