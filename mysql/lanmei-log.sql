/*
* Mysql 日志管理相关的数据表操作
* 数据库： Mysql
* 版本： 5.7.21
* 数据库名称：lanmei
* 数据表： 
*     1.  
* 	  2.  
* 创建日期：2018/5/6
* 更新日期：2018/5/6
* 修改记录：
*/

/*Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构,设置外键约束为0*/


/*系统日志表*/
DROP TABLE IF  EXISTS `sys_log`;
CREATE TABLE `sys_log`(
	`log_id` INT  AUTO_INCREMENT  COMMENT "日志ID",
	`description` TEXT DEFAULT NULL COMMENT "日志描述",
	`method` VARCHAR(100) DEFAULT NULL COMMENT "执行方法",
	`LogType` TINYINT  DEFAULT NULL COMMENT "0：正常日志，1：异常日志",
	`request_ip` VARCHAR(50) DEFAULT NULL COMMENT "请求IP",
	`exception_code` VARCHAR(50)  DEFAULT NULL COMMENT "异常代码",
	`exception_detail` VARCHAR(50)  DEFAULT NULL COMMENT "异常信息",
	`params` VARCHAR(50) DEFAULT NULL COMMENT "请求参数",
	`create_by` VARCHAR(50)  DEFAULT NULL COMMENT "操作人",
	`create_date` DATETIME  DEFAULT NULL COMMENT "操作时间",
	PRIMARY KEY (`log_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="系统日志表";
