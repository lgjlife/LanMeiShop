package org.lanmei.admin.impl;

import org.lanmei.admin.dao.mapper.CmsAdminLoginMapper;
import org.lanmei.admin.dao.model.CmsAdminLogin;
import org.lanmei.admin.service.CmsAdminLoginService;
import org.lanmei.common.BaseService;
import org.lanmei.enumDefine.AdminStatus;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.pool.DruidDataSource;


@Transactional
@Service
public class CmsAdminLoginServiceImpl  extends BaseService implements CmsAdminLoginService{

	private final static Logger logger = LoggerFactory.getLogger("CmsAdminLoginServiceImpl.class");	
	{
		logger.debug("UserServiceImpl create bean ......");
	}
	@Autowired
	private CmsAdminLoginMapper adminLoginMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	
	@Override
	public AdminStatus addAdminLoginLog(Integer adminId, CmsAdminLogin adminLogin) {
		// TODO Auto-generated method stub
		
		int loginid = adminLoginMapper.insertByAdminId(adminId,adminLogin);
		//int loginid = adminLoginMapper.insert(adminLogin);
		if(loginid == 0) {
			logger.debug("写入登陆日志失败");
			return AdminStatus.LOGGIN_LOG_FAIL;
		}
		else {
			logger.debug("写入登陆日志成功");
			return AdminStatus.LOGGIN_LOG_SUCCESS;
		}

	}	
}
