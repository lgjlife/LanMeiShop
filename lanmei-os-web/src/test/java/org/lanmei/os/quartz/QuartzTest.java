package org.lanmei.os.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类  
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等  
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})//加载Spring-mvc配置文件  
public class QuartzTest {
	
	@Test
	public void quartzTest() {
		System.out.println("quartzTest");
		while(true) {
		
		}
	}
}
