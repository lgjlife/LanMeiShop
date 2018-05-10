/*
* Mysql 商品模块相关的数据表操作
* 数据库： Mysql
* 版本： 5.7.21
* 数据库名称：lanmei
* 数据表： 
*      commodity   商品表
* 	　　　commodity_classification_association　　商品分类关联表　
* 	　　　commodity_classification　商品分类表
* 	   commodity_ｉmage  商品图片表
* 	   commodity_reviews 商品评论表 
* 创建日期：2018/05/07
* 更新日期：2018/0５/07
** 修改记录：
*/

/*Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构,设置外键约束为0*/


/*商品表*/
DROP TABLE IF  EXISTS `commodity`;
CREATE TABLE `commodity`(
	`commodity_id` INT  AUTO_INCREMENT COMMENT "商品ID",
	`name` VARCHAR(50) COMMENT "商品名称",
	`title` VARCHAR(150) COMMENT "显示标题",
	`reference_price`  FLOAT COMMENT "参考价格",
	`activity_price` FLOAT COMMENT "活动价格",
	`description` VARCHAR(255) COMMENT "商品简介",
	`create_by` VARCHAR(20) COMMENT "创建人",
	`create_time` DATETIME COMMENT "创建时间",
	`put_on_sale_time` DATETIME COMMENT "上架时间",
	PRIMARY KEY (`commodity_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品表";
/*商品分类关联表*/
DROP TABLE IF  EXISTS `commodity_classification_association`;
CREATE TABLE `commodity_classification_association`(
	`association_id` INT  AUTO_INCREMENT COMMENT "商品分类ID",
	`commodity_id` INT  COMMENT "商品ID",
	`id` INT  COMMENT "分类ID",
	`create_by` VARCHAR(20) COMMENT "创建人",
	`create_time` DATETIME COMMENT "创建时间",
	PRIMARY KEY (`association_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品分类关联表";
/*商品分类表*/
DROP TABLE IF  EXISTS `commodity_classification`;
CREATE TABLE `commodity_classification`(
	`id` INT  AUTO_INCREMENT COMMENT "分类ID",
	`pid` INT  COMMENT "父分类ID",
	`name` VARCHAR(50)  COMMENT "分类名称",
	`isParent` VARCHAR(5) COMMENT "是否是父分类，是父分类说明有子类",
	`create_by` VARCHAR(20) COMMENT "创建人",
	`create_time` DATETIME COMMENT "创建时间",
	`remarks`  VARCHAR(255) COMMENT "备注",
	PRIMARY KEY (`id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品分类表";

insert into `commodity_classification` 	(id,pid,name,isParent) values(1,0,"手机/数码/配件","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(2,0,"电脑/办公/外设","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(3,0,"厨卫电器 生活电器","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(4,0,"大家电","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(5,0,"家居/日用/厨具","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(6,0,"服装鞋帽","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(7,0,"家装主材","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(8,0,"家具","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(9,0,"运动户外","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(10,0,"钟表/礼品/乐器","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(11,0,"珠宝","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(12,0,"其他","true");

insert into `commodity_classification` 	(id,pid,name,isParent) values(13,1,"手机通讯","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(14,1,"手机配件","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(15,1,"摄影摄像","false");

insert into `commodity_classification` 	(id,pid,name,isParent) values(16,2,"网络设备","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(17,2,"办公打印","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(18,2,"存储设备","false");

/**
 * 商品图片,用于列表展示商品显示的图片
 * 或者商品详情页标题处展示的图片
 * 每个商品最多５张
 */
DROP TABLE IF  EXISTS `commodity_ｉmage`;
CREATE TABLE `commodity_ｉmage`(
	`image_id` INT  AUTO_INCREMENT COMMENT "商品ID",
	`commodity_id` INT  COMMENT "商品ID",
	`path` VARCHAR(100) COMMENT "图片路径",
	`is_list_display` TINYINT DEFAULT 0  COMMENT "0/1,是否是搜索时列表展示的图片，单个商品仅有一个设为1",
	`upload_by` VARCHAR(20) COMMENT "上传人",
	`upload_time` DATETIME COMMENT "上传时间",
	PRIMARY KEY (`image_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品图片表";
/**
 * 商品评论表
 */
DROP TABLE IF  EXISTS `commodity_reviews`;
CREATE TABLE `commodity_reviews`(
	`reviews_id` INT  AUTO_INCREMENT COMMENT "商品ID",
	`commodity_id` INT   COMMENT "商品ID",
	`grade` TINYINT COMMENT "评价等级0-５颗星",
	`is_have_image` TINYINT COMMENT "0/1,是否是有图评价",
	`is_additional_reviews` TINYINT COMMENT "0/1,是否是追加评价",
	`content` TEXT COMMENT "评价内容",
	`reviews_by` VARCHAR(20) COMMENT "评价人，手机号",
	`reviews_time` DATETIME COMMENT "创建时间",
	PRIMARY KEY (`reviews_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品评价表";

