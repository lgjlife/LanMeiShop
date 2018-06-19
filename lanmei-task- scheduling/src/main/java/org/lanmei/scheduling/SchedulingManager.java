package org.lanmei.scheduling;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;


public class SchedulingManager {

	private final static Logger logger = LoggerFactory.getLogger("SchedulingManager.class");	
	{
		logger.info("SchedulingManager 创建bean ");
	}
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	private Scheduler scheduler;
	
	public SchedulingManager() {
		init(); 
	}
	/**
	 * 初始化
	 * 获取scheduler
	 */
	public void init() {
		/*SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
		try {
			scheduler = schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */
		//schedulerFactoryBean = new SchedulerFactoryBean();
		//scheduler = schedulerFactoryBean.getScheduler();
	}
	/**
	 * 添加任务
	 * @param job
	 * @param jobClass
	 */
    public void addJod(ScheduleJob job,Class <? extends Job> jobClass) {
    	
    	logger.info("向调度器中添加任务");
    	if(job == null) {
    		logger.info("调度器任务为空，向调度器中添加任务失败");
    		return;
    	}
    	 // 获取job实例  
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
        		.withIdentity(job.getJobName(),job.getJobGroup()).build();  
       // jobDetail.getJobDataMap().put("", job);
        
        Trigger trigger = TriggerBuilder.newTrigger()  
                .withIdentity(job.getJobName(),job.getJobGroup())  
                .withSchedule(cronSchedule(job.getCronExpression()))
                .build();
        
        try {
			scheduler.scheduleJob(jobDetail,trigger);
			//scheduler.start();
			JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
			 scheduler.triggerJob(jobKey);
			logger.info("向调度器中添加任务成功");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.info("向调度器中添加任务失败");
			e.printStackTrace();
		}
    }
    /**
     * 启动任务
     * @param job
     */
    public void runJob(ScheduleJob job) {
    	logger.info("执行调度器任务");
    	if(job == null) {
    		logger.info("调度器任务为空，执行调度器任务失败");
    		return;
    	}
    	
    	JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
    	if(jobKey == null) {
    		logger.info("任务[{}]不存在，执行调度器任务失败",job.getJobName());
    		return;
    	}   	
    	logger.info("name = " + jobKey.getName() + "  group = " + jobKey.getGroup());
    	try {
    		 scheduler.triggerJob(jobKey);
    		 logger.info("执行调度器任务成功");
 		} catch (SchedulerException e) {
 			// TODO Auto-generated catch block
 			 logger.info("执行调度器任务失败" + e.getMessage());
 		}
    }
    /**
     * 暂停任务
     * @param job
     */
    public void pauseJob(ScheduleJob job) {
    	logger.info("暂停调度器任务");
    	if(job == null) {
    		logger.info("调度器任务为空，暂停调度器任务失败");
    		return;
    	}
    	
    	JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
    	if(jobKey == null) {
    		logger.info("任务[{}]不存在，暂停调度器任务失败",job.getJobName());
    		return;
    	}   	
    	logger.info("name = " + jobKey.getName() + "  group = " + jobKey.getGroup());
    	try {
    		 scheduler.pauseJob(jobKey);
    		 logger.info("暂停调度器任务成功");
 		} catch (SchedulerException e) {
 			// TODO Auto-generated catch block
 			 logger.info("暂停调度器任务失败" + e.getMessage());
 		}
    }
    /**
     * 恢复任务
     * @param job
     */
    public void resumeJob(ScheduleJob job) {
    	logger.info("恢复调度器任务");
    	if(job == null) {
    		logger.info("调度器任务为空，恢复调度器任务失败");
    		return;
    	}
    	
    	JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
    	if(jobKey == null) {
    		logger.info("任务[{}]不存在，恢复调度器任务失败",job.getJobName());
    		return;
    	}   	
    	logger.info("name = " + jobKey.getName() + "  group = " + jobKey.getGroup());
    	try {
    		 scheduler.resumeJob(jobKey);
    		 logger.info("恢复调度器任务成功");
 		} catch (SchedulerException e) {
 			// TODO Auto-generated catch block
 			 logger.info("恢复调度器任务失败" + e.getMessage());
 		}
    }
    /**
     * 删除任务
     * @param job
     */
    public void deleteJob(ScheduleJob job) {
    	logger.info("删除调度器任务");
    	if(job == null) {
    		logger.info("调度器任务为空，删除调度器任务失败");
    		return;
    	}
    	
    	JobKey jobKey = JobKey.jobKey(job.getJobName(),job.getJobGroup());
    	if(jobKey == null) {
    		logger.info("任务[{}]不存在，删除调度器任务失败",job.getJobName());
    		return;
    	}   	
    	logger.info("name = " + jobKey.getName() + "  group = " + jobKey.getGroup());
    	try {
    		 scheduler.deleteJob(jobKey);
    		 logger.info("删除调度器任务成功");
 		} catch (SchedulerException e) {
 			// TODO Auto-generated catch block
 			 logger.info("删除调度器任务失败" + e.getMessage());
 		}
    }
    
}

