package com.lanmei.user.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService extends SqlSessionDaoSupport {

    @Autowired
     public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
           super.setSqlSessionFactory(sqlSessionFactory);
     }
	 
}
