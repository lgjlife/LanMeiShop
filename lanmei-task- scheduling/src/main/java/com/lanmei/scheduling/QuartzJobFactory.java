package com.lanmei.scheduling;



import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuartzJobFactory implements Job {
	private final static Logger logger = LoggerFactory.getLogger("QuartzJobFactory.class");	
	
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail detail = context.getJobDetail();
        logger.info("重新执行任务.........");
        while(true) {
        	logger.info("执行任务.........");
        	try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
