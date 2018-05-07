package org.lanmei.common.syslog.service;

import org.lanmei.common.syslog.model.SysLog;

public interface SysLogService {

	/**
	 * 向数据库中写入日志
	 * @param log
	 * @return
	 */
	int saveLog(SysLog log);
}
