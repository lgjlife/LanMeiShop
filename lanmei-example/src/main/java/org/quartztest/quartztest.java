package org.quartztest;

import java.util.Date;
import java.util.TimeZone;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.*;  
import static org.quartz.CronScheduleBuilder.*;  
import static org.quartz.DateBuilder.*;

public class quartztest {
	public void run() throws Exception {  

        System.out.println("------- 初始化 ----------------------");  
  
        // 首先要实例化scheduler  
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
        Scheduler scheduler = schedulerFactory.getScheduler();  
  
        System.out.println("------- 初始化完成 -----------");  
  
        // 获取给定时间的下一个完整分钟的时间，例如给定时间 08:13:54 则会反回 08:14:00  
        Date runTime = DateBuilder.evenMinuteDate(new Date());  
        Date runTime1 = DateBuilder.evenSecondDate(new Date());
        
        System.out.println("------- Job安排 -------------------");  
  
        // 获取job实例  
        JobDetail job = JobBuilder.newJob(HelloQuartz.class).withIdentity("job1", "group1").build();  
        
        JobDetail job1 = JobBuilder.newJob(job1.class)  
        	    .withIdentity("myJob", "group1") // name "myJob", group "group1"  
        	    .usingJobData("jobSays", "Hello World!")  
        	    .usingJobData("myFloatValue", 3.141f)  
        	    .build(); 
        JobDetail job3 = JobBuilder.newJob(job3.class).withIdentity("job3", "group1").build();  
        
        
        // 在下一轮分钟触发运行  
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();  
        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(runTime1).build();  
        Trigger trigger3 = TriggerBuilder.newTrigger()  
                .withIdentity("trigger3", "group1")  
                .withSchedule(cronSchedule("0 41 18 * * ?"))
                .build();  
        // 告诉quartz使用某个trigger执行某个job  
        scheduler.scheduleJob(job, trigger);  
        scheduler.scheduleJob(job1, trigger2);
        scheduler.scheduleJob(job3, trigger3);
        System.out.println(job.getKey() + " 将会运行于: " + runTime);  
  
        // 启动scheduler  
        scheduler.start();  
  
        System.out.println("------- 开始安排 -----------------");  
  
        System.out.println("------- 等待65秒 -------------");  
        Thread.sleep(65L * 1000L);  
  
        // 关闭scheduler  
        System.out.println("------- 关闭 ---------------------");  
       // scheduler.shutdown(true);  
        System.out.println("------- 关闭完成 -----------------");  
    }  
  
    public static void main(String[] args) throws Exception {  
    	quartztest example = new quartztest();  
        example.run();  
    }  
}
