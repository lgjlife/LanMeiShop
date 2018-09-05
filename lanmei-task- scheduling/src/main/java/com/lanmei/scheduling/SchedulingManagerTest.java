package com.lanmei.scheduling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类  
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等  
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-quartz.xml"})//加载Spring-mvc配置文件  
public class SchedulingManagerTest {

	@Test
	public void test() {
		SchedulingManager schedulingManager = new SchedulingManager();
		
		ScheduleJob job = new ScheduleJob("JobName","JobGroup","0 1 * * * ?");
		schedulingManager.addJod(job,QuartzJobFactory.class);
		schedulingManager.runJob(job);
		while(true) {
			try {
				Thread.sleep(1);
				//schedulingManager.pauseJob(job);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
