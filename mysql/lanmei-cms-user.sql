/*
* Mysql 用户相关的数据表操作
* 数据库： Mysql
* 版本： 5.7.21
* 数据库名称：lanmei
* 数据表： 
*     1.  cms_admin   管理员表
* 	  2.  cms_admin_login 管理员登录表
* 创建日期：2018/04/23
* 更新日期：2018/04/23
* 修改记录：
*/

/*Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构,设置外键约束为0*/

/*头像存储的是地址，不是存储头像的二进制数据*/
/*管理员表*/
DROP TABLE IF  EXISTS `cms_admin`;
CREATE TABLE `cms_admin`(
	`admin_id` INT  AUTO_INCREMENT COMMENT "管理员ID",
	`login_jobNum` VARCHAR(20) DEFAULT NULL COMMENT "管理员工号",
	`admin_password` VARCHAR(100) DEFAULT  NULL COMMENT "管理员登录密码",
	`password_salt`  VARCHAR(50)  DEFAULT  NULL COMMENT "密码加密的盐",
	`nick_name`  VARCHAR(50) DEFAULT NULL COMMENT "管理员昵称",
	`actual_name` VARCHAR(50) DEFAULT NULL COMMENT "真实姓名",
	`department`  VARCHAR(50) DEFAULT NULL COMMENT "部门",
	`create_admin` INT  DEFAULT NULL COMMENT "创建人员",
	`invitation_code` VARCHAR(10) DEFAULT NULL COMMENT "注册时邀请码",
	`gender`   VARCHAR(10) COMMENT "性别-男/女",
	`age`    TINYINT COMMENT "年龄",
	`head_portrait` VARCHAR(50) COMMENT "头像",
	`state`  VARCHAR(50) COMMENT "状态-待激活/正常状态/登录异常/禁止登录/注销状态",
	`email`  VARCHAR(30) COMMENT "邮箱地址",
	`email_is_activate`  VARCHAR(20) DEFAULT 0 COMMENT "邮箱激活状态-激活/未激活",
	`phone_num` VARCHAR(15) COMMENT "手机号码",
	`last_login_time` DATETIME COMMENT "最后登录时间",
	`last_login_ip` VARCHAR(30) COMMENT "最后登录ip",
	`login_count` INT DEFAULT 0 COMMENT "登录次数",
	`generate_time`  DATETIME COMMENT "帐号生成时间",
	`activate_time`  DATETIME COMMENT "帐号激活时间",
	`colose_time`  DATETIME COMMENT "帐号注销时间",
	PRIMARY KEY (`admin_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="管理员表";


/*15条数据*/
insert into  `cms_admin`  value(1,201801,"e8fd93e45891573df04aeb22211efffc","64e3db703d5d69b2da2377a3bba9d973","蓝莓1","赵云1","商品部",
                                 201800,"789456","MALE",19,NULL,"UNACTIVATE","563739007@qq.com","EMAIL_ACTIVATED","12345678911",
                                 "2018-04-27 16:48:00","0.1.1.1",0,"2018-04-27 16:48:00",NULL,NULL);

insert into  `cms_admin`  value(2,201802,"d0b58d59608726daf2936788278b8c0a","51176f7925b3c73508ca4540411cf66c","蓝莓2","赵云2","商品部",
								201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(3,201803,"5842c238a2f2e0f378c03ebf55034d66","65f2f0bc0f2534d796d092f84f1dd76a","蓝莓3","赵云3","商品部",
								201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(4,201804,"4e8290d29e566d99bdcab64895175cb0","1e9c8455f695b932041b10dbd72b8247","蓝莓4","赵云4","商品部",
								201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(5,201805,"267c68c7a5e30adfd2fff162bb0a4239","cd93aec72d128f117e8881c7191a99b7","蓝莓5","赵云5","商品部",
								201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(6,201806,"545d95249d3ba9a6a95ab531bfa175f5","0bc89c62470ba7ed618fa482f0067e55","蓝莓6","赵云6","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(7,201807,"8f860cb52744c30ff6c563fd74644b2f","4015a6f793d70f6b949ced3edcfc61b8","蓝莓7","赵云7","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(8,201808,"b71f4a38e8095962051a7b02e5ba0740","5d6792337e7bb03c3445b3f4601fef34","蓝莓8","赵云8","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(9,20189,"53ab07818a69b1e10a13d8daa4c8ff85","9cc4e8cb8a100877085bcd16dc95aab3","蓝莓9","赵云9","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(10,201810,"2d11de3ecfd1c0ed7c9a3ab32137c245","b76d70b7d4b668c5e64eb9539eb453af","蓝莓10","赵云10","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(11,201811,"df1150e7ebef1b7d74b95af1559000ca","b19713326702cbe9d3f8d9f1dc4dea77","蓝莓11","赵云11","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(12,201812,"88b3e7a7551d5e50431c06124fe2f41c","8dba6d4152c463f40da4098f935f7ce9","蓝莓12","赵云12","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(13,201813,"e5cd3595fab8165675d36fb3e7210f44","1a6bce67635300ed2fadefa17ae16280","蓝莓13","赵云13","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(14,201814,"4e9840dfb85857be2b0303f41248cc48","c5db19d06f62c2bb9cc4ccb914e712d3","蓝莓14","赵云14","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
insert into  `cms_admin`  value(15,201815,"77a7cc6a2effbe628d79e108f338ab91","fa277f0b8e831b92ea5c24266994369a","蓝莓15","赵云15","商品部",201800,"789456","MALE",19,NULL,"UNACTIVATE",
								"563739007@qq.com","EMAIL_ACTIVATED","12345678911","2018-04-27 16:48:00","0.1.1.1",0,
								"2018-04-27 16:48:00",NULL,NULL);
/*管理员登录表*/
DROP TABLE IF EXISTS `cms_admin_login`;
CREATE TABLE `cms_admin_login`(
	`login_id` INT  AUTO_INCREMENT COMMENT "登录日志ID"  ,
	`login_time`  DATETIME DEFAULT NULL COMMENT "登录时间",
	`login_ip`   VARCHAR(50) DEFAULT NULL COMMENT "登录IP",
	`admin_id`  INT DEFAULT NULL COMMENT "用户id",
	`explorer` VARCHAR(150) DEFAULT NULL  COMMENT "浏览器",
	PRIMARY KEY (`login_id`)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员登录表';
