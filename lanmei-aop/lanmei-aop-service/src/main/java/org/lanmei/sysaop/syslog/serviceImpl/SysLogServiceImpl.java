package org.lanmei.sysaop.syslog.serviceImpl;

import org.lanmei.common.baseservice.BaseService;
import org.lanmei.sysaop.dao.syslog.mapper.SystemLogMapper;
import org.lanmei.sysaop.dao.syslog.model.SystemLog;
import org.lanmei.sysaop.syslog.service.SysLogService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;


/**
 * @Description SysLogServiceImpl  系统日志服务实现类
 * @author lgj
 * 
 */
@Service
public class SysLogServiceImpl  extends BaseService  implements SysLogService{

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
