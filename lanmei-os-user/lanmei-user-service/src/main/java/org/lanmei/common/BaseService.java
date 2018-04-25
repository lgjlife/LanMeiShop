package org.lanmei.common;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BaseService extends SqlSessionDaoSupport {
	
	 @Resource
     public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
           super.setSqlSessionFactory(sqlSessionFactory);
     }
	 
}
