/*
* Mysql 用户相关的数据表操作
* 数据库： Mysql
* 版本： 5.7.21
* 数据库名称：lanmei
* 数据表：
* 创建日期：2018/04/23
* 更新日期：2018/04/23
*/

/*Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构,设置外键约束为0*/



/*用户信息表*/
DROP TABLE IF EXISTS `cms_user`;
CREATE TABLE `cms_user`(
   `user_id` INT   AUTO_INCREMENT COMMENT "用户ID",    
   `user_number` INT  DEFAULT NULL COMMENT "用户编号",
   `nick_name`  VARCHAR(30) DEFAULT NULL COMMENT "昵称" ,
   `login_password` VARCHAR(50) NOT NULL COMMENT "登录密码",
   `salt` VARCHAR(50) DEFAULT NULL COMMENT "登录密码盐",
   `actual_name` VARCHAR(100) DEFAULT NULL COMMENT "真实姓名",
   `gender`  VARCHAR(10) DEFAULT NULL COMMENT "性别",
   `age`  TINYINT UNSIGNED DEFAULT NULL  COMMENT "年龄",
   `head_portrait` LONGBLOB COMMENT "头像",
   /*0:未注册，1：已注册，2：登录状态，3：离线状态，4,禁止登录状态，5：注销状态）*/
   `status` TINYINT DEFAULT NULL COMMENT "状态",
   `email` VARCHAR(30) COMMENT "电子邮箱",     
   `email_active` TINYINT DEFAULT NULL COMMENT "邮箱是否激活",  
   `phone_num` VARCHAR(20)  DEFAULT NULL  COMMENT "电话号码",   
   `register_time` DATETIME DEFAULT NULL COMMENT "注册时间",
   `last_login_time` DATETIME DEFAULT NULL COMMENT "最后登录时间",
   `login_nums`  INT DEFAULT NULL COMMENT "登录次数",   
    PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="用户表";

INSERT INTO `os_user` VALUES(1,1,"Fly","123456789","","梁飞飞","男",25,NULL,0,"liang@lanmei.com",0,"13888888888",NULL,NULL,5);  
INSERT INTO `os_user` VALUES(3,1,"Fly","123456789","","梁飞飞","男",25,NULL,0,"liang@lanmei.com",0,"13999999999",NULL,NULL,5);  
INSERT INTO `os_user` VALUES(4,1,"Fly","123456789","","梁国剑","男",25,NULL,0,"liang@lanmei.com",0,"13888888888",NULL,NULL,5);  
INSERT INTO `os_user` VALUES(5,1,"Fly","123456789","","和经费","男",25,NULL,0,"liang@lanmei.com",0,"12345678911",NULL,NULL,5);  
/*用户登录表*/
DROP TABLE IF EXISTS `os_user_login`;
CREATE TABLE `os_user_login`(
	`login_id` INT  AUTO_INCREMENT COMMENT "登录日志ID"  ,
	`login_time`  DATETIME DEFAULT NULL COMMENT "登录时间",
	`login_ip`   VARCHAR(50) DEFAULT NULL COMMENT "登录IP",
	`user_id`  INT DEFAULT NULL COMMENT "用户id",
	`explorer` VARCHAR(150) DEFAULT NULL  COMMENT "浏览器",
	PRIMARY KEY (`login_id`)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录表';

INSERT INTO  `os_user_login` VALUES (1,NULL,"192.172.1.123",1,"Chrom");
/*收货地址表*/
DROP TABLE IF EXISTS `os_receipt_address`;
CREATE TABLE `os_receipt_address`(
	`receipt_address_id` INT  AUTO_INCREMENT COMMENT "收货地址ID",
	`user_id`  INT DEFAULT NULL COMMENT "用户id",
	`name`   VARCHAR(30) DEFAULT NULL  COMMENT "收货姓名",
	`phone_num`  VARCHAR(30) DEFAULT NULL  COMMENT "联系电话",
	`province_id` INT DEFAULT NULL COMMENT "省份id",
	`province_name`  VARCHAR(30) DEFAULT NULL  COMMENT "省名字",
	`city_id` INT DEFAULT NULL COMMENT "市id",
	`city_name`  VARCHAR(30) DEFAULT NULL  COMMENT "市名字",
	`district_id` INT DEFAULT NULL COMMENT "区县id",
	`district_name`  VARCHAR(30) DEFAULT NULL  COMMENT "区县名字",
	`street_id` INT DEFAULT NULL COMMENT "街道id",
	`street_name`  VARCHAR(60) DEFAULT NULL  COMMENT "街道名字",
	`post_code`  VARCHAR(60) DEFAULT NULL  COMMENT "邮政编码",
	`create_time`  DATETIME  DEFAULT NULL  COMMENT "地址创建时间",
	`update_time`  DATETIME  DEFAULT NULL  COMMENT "地址更新时间",
	PRIMARY KEY (`receipt_address_id`)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收获地址表';

INSERT INTO `os_receipt_address` VALUES(1,1,"赵飞飞","13812341234",12,"广东省",6,"深圳市",8,"龙华区",9,"龙华街道","412256",NULL,NULL);
/*收藏夹*/
/*在商品模块中应该创建商品信息，收藏夹通过商品id查找商品相关信息*/
DROP TABLE IF EXISTS `os_favorites`;
CREATE TABLE `os_favorites`(
	`favorites_id` INT  AUTO_INCREMENT COMMENT "收藏夹ID",
	`user_id`  INT DEFAULT NULL COMMENT "用户id",
	`commodity_id` INT DEFAULT NULL COMMENT "商品ID",
	PRIMARY KEY (`favorites_id`)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏夹';

INSERT INTO `os_favorites` VALUES(1,1,1);



