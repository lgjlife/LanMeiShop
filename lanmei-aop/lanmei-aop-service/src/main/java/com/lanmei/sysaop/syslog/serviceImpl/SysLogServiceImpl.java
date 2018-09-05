package com.lanmei.sysaop.syslog.serviceImpl;

import com.alibaba.druid.pool.DruidDataSource;
import com.lanmei.aop.dao.mapper.SystemLogMapper;
import com.lanmei.aop.dao.model.SystemLog;
import com.lanmei.common.baseservice.BaseService;
import com.lanmei.sysaop.syslog.service.SysLogService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description SysLogServiceImpl  系统日志服务实现类
 * @author lgj
 * 
 */
@Service
public class SysLogServiceImpl  extends BaseService implements SysLogService {

	private final static Logger logger = LoggerFactory.getLogger("SysLogServiceImpl.class");	
	{
		logger.debug("SysLogServiceImpl create bean ......");
	}
	@Autowired
	private SystemLogMapper sysLogMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	@Override
	public int saveLog(SystemLog log) {
		
		int insertCount = sysLogMapper.insert(log);
		return 0;
	}

}
