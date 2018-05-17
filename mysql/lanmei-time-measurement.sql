/*
* Mysql 计算方法花费时间相关的数据表操作
* 数据库： Mysql
* 版本： 5.7.21
* 数据库名称：lanmei
* 数据表： 
*     1.  
* 	  2.  
* 创建日期：2018/5/１6
* 更新日期：2018/5/１6
* 修改记录：
*/

/*Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构,设置外键约束为0*/


/*时间统计表表*/
DROP TABLE IF  EXISTS `time_measurement`;
CREATE TABLE `time_measurement`(
	`measurement_id` INT  AUTO_INCREMENT  COMMENT "ID",
	`method` VARCHAR(200) DEFAULT NULL COMMENT "执行方法",
	`method_desc` VARCHAR(80) DEFAULT NULL COMMENT "执行方法描述",
	`layer` VARCHAR(15) DEFAULT NULL COMMENT "层：Controller,Service,Other",
	`avr_time` BIGINT     COMMENT "平均执行时间 MS",
	`run_count`	BIGINT    COMMENT "执行次数",
	`last_time` BIGINT    COMMENT "上一次执行时间 MS",
	PRIMARY KEY (`measurement_id`),
	KEY(method)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="方法执行时间统计表";



