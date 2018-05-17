package org.lanmei.sysaop.time_measurement.service;

import org.lanmei.sysaop.dao.time_measurement.model.TimeMeasurement;

public interface TimeMeasurementService {

	/**
	 * 向数据库中写入日志
	 * @param log
	 * @return
	 */
	int saveTimeMeasurement(TimeMeasurement timeMeasurement);
}
