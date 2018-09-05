package com.lanmei.sysaop.syslog.service;


import com.lanmei.aop.dao.model.SystemLog;

public interface SysLogService {

	/**
	 * 向数据库中写入日志
	 * @param log
	 * @return
	 */
	int saveLog(SystemLog log);
}
