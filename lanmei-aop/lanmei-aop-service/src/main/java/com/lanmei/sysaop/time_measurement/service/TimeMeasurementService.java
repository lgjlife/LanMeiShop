package com.lanmei.sysaop.time_measurement.service;


import com.lanmei.aop.dao.model.TimeMeasurement;

public interface TimeMeasurementService {

	/**
	 * 向数据库中写入日志
	 * @param log
	 * @return
	 */
	int saveTimeMeasurement(TimeMeasurement timeMeasurement);
}
