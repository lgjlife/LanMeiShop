package org.lanmei.backup;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MysqlBackup {
	
  
    public void execute()  
    {     
      System.err.println("当前时间 = " + new Date());  
    } 
}
