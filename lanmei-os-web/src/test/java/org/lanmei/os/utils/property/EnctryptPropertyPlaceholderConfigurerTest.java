package org.lanmei.os.utils.property;

 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类 
//此处调用Spring单元测试类  
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等  
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
public class EnctryptPropertyPlaceholderConfigurerTest {

	@Test
	public void test() {
		
	}

}
