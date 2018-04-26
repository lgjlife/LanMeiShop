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
	`admin_account` INT DEFAULT NULL COMMENT "管理员帐号",
	`admin_password` VARCHAR(100) DEFAULT  NULL COMMENT "管理员登录密码",
	`password_salt`  VARCHAR(50)  DEFAULT  NULL COMMENT "密码加密的盐",
	`nick_name`  VARCHAR(50) DEFAULT NULL COMMENT "管理员昵称",
	`actual_name` VARCHAR(50) DEFAULT NULL COMMENT "真实姓名",
	`create_admin` INT  DEFAULT NULL COMMENT "创建人员",
	`invitation_code` VARCHAR(10) DEFAULT NULL COMMENT "注册时邀请码",
	`gender`   TINYINT COMMENT "性别-男/女",
	`age`    TINYINT COMMENT "年龄",
	`head_portrait` VARCHAR(50) COMMENT "头像",
	`state`  VARCHAR(50) COMMENT "状态-待激活/正常状态/登录异常/禁止登录/注销状态",
	`email`  VARCHAR(30) COMMENT "邮箱地址",
	`email_is_activate`  TINYINT DEFAULT 0 COMMENT "邮箱激活状态-激活/未激活",
	`phone_num` VARCHAR(15) COMMENT "手机号码",
	`last_login_time` DATETIME COMMENT "最后登录时间",
	`last_login_ip` VARCHAR(30) COMMENT "最后登录ip",
	`login_count` INT DEFAULT 0 COMMENT "登录次数",
	`generate_time`  DATETIME COMMENT "帐号生成时间",
	`activate_time`  DATETIME COMMENT "帐号激活时间",
	`colose_time`  DATETIME COMMENT "帐号注销时间",
	PRIMARY KEY (`admin_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="管理员表";

insert into  `cms_admin` (`actual_name`) value("liang");
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
