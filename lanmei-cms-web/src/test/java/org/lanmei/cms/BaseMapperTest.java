package com.lanmei.cms;

import java.io.*;


import org.apache.ibatis.io.*;
import org.apache.ibatis.session.*;
import org.apache.log4j.lf5.util.Resource;
import org.junit.*;


public class BaseMapperTest {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	@BeforeClass
	public static void init() {
		
		System.out.println("sys statup");
		try {
			
			Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			reader.close();
			
		}
		catch(IOException ex) {
			
			ex.printStackTrace();
		}
	}
	
	public SqlSession getSqlSession() {
		return BaseMapperTest.sqlSessionFactory.openSession();
	}
}
