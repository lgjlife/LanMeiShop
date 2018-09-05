package com.lanmei.user.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.lanmei.common.BaseService;
import com.lanmei.common.UserStatus;
import com.lanmei.sms.SmsDemo;
import com.lanmei.user.dao.mapper.OsUserMapper;
import com.lanmei.user.dao.model.OsUser;
import com.lanmei.user.service.UserService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl extends BaseService implements UserService {

	private final static  int  PHONE_VALIDATE_CODE_LENGTH = 6;
	
	private final static Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");	
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

	public UserStatus checkPhoneNum(String phoneNum) {
		
		OsUser osuser = userMapper.selectByPhoneNum(phoneNum);
		
		if(osuser == null ) {
			logger.debug("您查找的手机号码" + phoneNum +"不存在");
			return UserStatus.PHONE_NUM_NOT_REGISTER;
		}
		else {
			logger.debug("您查找的手机号码" + phoneNum +"已经注册");
			System.out.println(osuser.toString());			
			return UserStatus.PHONE_NUM_REGISTER; 
		}		
	}
	public OsUser getUser(String nickName,String phoneNum,String email){
		
		OsUser osuser = userMapper.selectByUser(nickName,phoneNum,email);
		
		if(osuser == null ) {
			logger.debug("您查找的用户不存在");
			
		}
		else {
			logger.debug("您查找的用户" + osuser.getUserId() +"存在");
		}		
		return osuser;
	}
	public static int getRandom() {
		
		return (int) (Math.random() * 999999);
	}
	/**
	 * 用户注册
	 * @param userName       用户名
	 * @param userPassword   密码
	 * @return
	 */
	public UserStatus register(OsUser osuser ) {
		UserStatus status = null;
		
		Integer id = userMapper.insertRegister(osuser);
		if(id == null) {
			logger.debug("用户创建失败");
			return UserStatus.REGISTER_FAIL;
		}
		else {
			logger.debug("用户创建成功，id = " + osuser.getUserId());
			return UserStatus.REGISTER_SUCCESS;
		}
	}
	
	public  void sendMsg(String phoneNum,String code ) {	
		try {
			SmsDemo.sendSms(phoneNum,code);
			logger.debug("发送结束");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.debug("PhoneNum = " + phoneNum  + "  phoneValidateCode = " + code); 
		
	}
	public OsUser getUserByTelNum(String phoneNum) {
		OsUser osuser = userMapper.selectByTelNum(phoneNum);
		return osuser;
	}
	public OsUser getUserByEmail(String email) {
		OsUser osuser = userMapper.selectByEmail(email);
		return osuser;
	}
	public OsUser getUserByNickName(String nickName ) {
		OsUser osuser = userMapper.selectByNickName(nickName);
		return osuser;
	}
	
	
}

