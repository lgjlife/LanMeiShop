package com.lanmei.os.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseBackup {
	
	private final static Logger logger = LoggerFactory.getLogger("DatabaseBackup.class");	
	{
		logger.debug("DatabaseBackup Created Bean............. ");
	}
	
	//MySQL数据库所在服务器地址IP 
	private  String hostIP;
	//进入数据库所需要的用户名 
	private String userName;
	//进入数据库所需要的密码 
	private String password;
	//数据库导出文件保存路径
	private String savePath;
	//数据库导出文件文件名 
	private String fileName;
	//要导出的数据库名 
	private String databaseName;

    public  boolean exportDatabaseTool() throws InterruptedException {  
        File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if(!savePath.endsWith(File.separator)){  
            savePath = savePath + File.separator;  
        }  
          
        PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;  
        try {  
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));  
            Process process = Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);  
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");  
            bufferedReader = new BufferedReader(inputStreamReader);  
            String line;  
            while((line = bufferedReader.readLine())!= null){  
                printWriter.println(line);  
            }  
            printWriter.flush();  
            if(process.waitFor() == 0){//0 表示线程正常终止。  
                return true;  
            }  
        }catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
                if (printWriter != null) {  
                    printWriter.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
    
    public void readConf() {
    	
    	Properties properties = new Properties();
    	Reader proreader = null;
    	Date  date = new Date();
    	/*读取配置文件的配置信息*/
    	try {
			InputStream in = Resources.getResourceAsStream("mysqlBackup.properties");
			proreader = new InputStreamReader(in);
			properties.load(proreader);
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	hostIP = properties.getProperty("hostIP");
    	userName = properties.getProperty("userName");
    	password = properties.getProperty("password");
    	savePath =  properties.getProperty(" savePath");
    	databaseName = properties.getProperty("databaseName");
    	
    	logger.debug(" hostIP = " + hostIP
    	+ "  userName = " + userName
    	+ " password = " + password
    	+ "  savePath = " + savePath
    	+ "  databaseName = " + databaseName);
    }
}
