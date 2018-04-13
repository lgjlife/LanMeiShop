package org.lanmei.user;

import org.lanmei.common.BaseService;
import org.lanmei.user.dao.mapper.OsUserMapper;
import org.lanmei.user.dao.model.OsUser;
import org.lanmei.user.service.UserService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;


@Service
public class UserServiceImpl extends BaseService implements  UserService{

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	{
		logger.debug("UserServiceImpl create bean ......");
	}
	@Autowired
	private OsUserMapper userMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	public OsUser getById(Long userId) {
		
		logger.debug("getByIding ...... userMapper = " +  userMapper + " userId = " + userId);
	
		System.out.println(dataSource.getUrl());
		System.out.println(dataSource.getDriverClassName());
		System.out.println(dataSource.getUsername());
		System.out.println(dataSource.getPassword());
		
    

		OsUser osuser = userMapper.selectById(userId);
		logger.debug("phone : " + osuser.getPhoneNum());
		return osuser;
	}


	
}
