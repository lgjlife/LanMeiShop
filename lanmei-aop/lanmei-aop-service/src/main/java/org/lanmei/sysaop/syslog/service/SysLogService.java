package org.lanmei.sysaop.syslog.service;

import org.lanmei.sysaop.dao.syslog.model.SystemLog;

public interface SysLogService {

	/**
	 * 向数据库中写入日志
	 * @param log
	 * @return
	 */
	int saveLog(SystemLog log);
}
