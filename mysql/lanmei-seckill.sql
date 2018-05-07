/*
* Mysql 秒杀模块相关的数据表操作
* 数据库： Mysql
* 版本： 5.7.21
* 数据库名称：lanmei
* 数据表： 
*     1.  
* 	  2.  
* 创建日期：2018/04/30
* 更新日期：2018/04/30
* 修改记录：
*/

/*Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构,设置外键约束为0*/


/*秒杀表*/
DROP TABLE IF  EXISTS `seckill`;
CREATE TABLE `seckill`(
	`seckill_id` INT  AUTO_INCREMENT  COMMENT "秒杀ID",
	`seckill_name` VARCHAR(50) DEFAULT NULL COMMENT "秒杀名称",
	`stock_count` INT DEFAULT NULL COMMENT "库存数量",
	`create_time` DATETIME  DEFAULT NULL COMMENT "创建时间",
	`start_time` DATETIME DEFAULT NULL COMMENT "秒杀时间",
	`finish_time` DATETIME  DEFAULT NULL COMMENT "结束时间",
	PRIMARY KEY (`seckill_id`),	
	KEY index_create_time(create_time),
	KEY index_start_time(start_time),
	KEY index_finish_time(finish_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="秒杀表";

update  `seckill`
	set stock_count = (stock_count - 1)
DROP TABLE IF  EXISTS `seckill_success`;
CREATE TABLE `seckill_success`(
	`seckill_id` INT     COMMENT "秒杀ID",
	`user_id` INT  COMMENT "用户ID",
	`state` VARCHAR(30)  DEFAULT -1 COMMENT "秒杀状态，-1：无效，0：成功，1：已付款，2：已发货，3：已收货，4：交易完成", 
	`create_time` DATETIME  DEFAULT NULL COMMENT "创建时间",
	PRIMARY KEY (`seckill_id`,`user_id`),	
	KEY index_create_time(`create_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="秒杀成功表";

select seckill_id, user_id, state, create_time
    from seckill_success
        where seckill_id = 5
        and user_id = 1       
