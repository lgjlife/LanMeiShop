package org.lanmei.sysaop.time_measurement.serviceImpl;

import org.lanmei.common.baseservice.BaseService;
import org.lanmei.sysaop.dao.time_measurement.mapper.TimeMeasurementMapper;
import org.lanmei.sysaop.dao.time_measurement.model.TimeMeasurement;
import org.lanmei.sysaop.time_measurement.service.TimeMeasurementService;
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
public class TimeMeasurementServiceImpl  extends BaseService  implements TimeMeasurementService{

	private final static Logger logger = LoggerFactory.getLogger("SysLogServiceImpl.class");	
	{
		logger.debug("SysLogServiceImpl create bean ......");
	}
	@Autowired
	private TimeMeasurementMapper timeMeasurementMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	@Override
	public int saveTimeMeasurement(TimeMeasurement timeMeasurement){
		
		TimeMeasurement measurement = timeMeasurementMapper.selectAllByMethod(timeMeasurement.getMethod());
		if(measurement == null) {
			//第一次运行
			timeMeasurementMapper.insert(timeMeasurement);
		}		
		else {
			Long avrRunTime = (measurement.getAvrTime() + timeMeasurement.getLastTime() ) / 2;
			timeMeasurement.setAvrTime(avrRunTime);
			timeMeasurement.setRunCount(measurement.getRunCount() + 1);
			timeMeasurement.setMeasurementId(measurement.getMeasurementId());
			timeMeasurementMapper.updateByPrimaryKey(timeMeasurement);
		}
		//打印时间
		logger.debug("\r\n method = " + timeMeasurement.getMethod()
				+ "\r\n avrRunTime =  " + timeMeasurement.getAvrTime()
				+ "\r\n RunCount =  " + timeMeasurement.getRunCount()
				+ "\r\n LastRunTime =  " + timeMeasurement.getLastTime());
		
		return 0;
	}

}
