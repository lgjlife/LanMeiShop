package com.lanmei.backup;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class MysqlBackup {
	
	@Autowired
	private DatabaseBackup databaseBackup;
	
    public void execute()  
    {     
      System.err.println("当前时间 = " + new Date());  
      databaseBackup.backup();
    } 
}
