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
	`category_id` INT COMMENT "商品类别id 三级分类　品牌",
	`name` VARCHAR(50) COMMENT "商品名称",
	`brand` VARCHAR(50) COMMENT "商品品牌",
	`title` VARCHAR(150) COMMENT "显示标题",
	`description` LONGTEXT COMMENT "商品简介",
	`create_by` VARCHAR(20) COMMENT "创建人",
	`create_time` DATETIME COMMENT "创建时间",
	`put_on_sale_time` DATETIME COMMENT "上架时间",
	`put_off_sale_time` DATETIME COMMENT "下架时间",
	`sale_state` TINYINT  DEFAULT -1 COMMENT "销售状态: -1:新建商品，０:　已经上架，正在销售，１：已经下架，停止销售",
	PRIMARY KEY (`commodity_id`)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品表";
LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` VALUES (1,48,'魅蓝note6','魅蓝','魅蓝旗舰机',NULL,'测试用户','2018-05-22 00:33:05',NULL,NULL,-1),
(2,49,'坚果R1','坚果','坚果旗舰机',NULL,'测试用户','2018-05-22 00:33:40',NULL,NULL,-1),
(3,85,'跑步鞋　fly','阿迪达斯','跑步鞋，轻便！',NULL,'测试用户','2018-05-22 00:34:48',NULL,NULL,-1);
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

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
--大类
insert into `commodity_classification` 	(id,pid,name,isParent) values(1,0,"手机/数码/配件","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(2,0,"电脑/办公/外设","true");
insert into `commodity_classification` 	(id,pid,name,isParent) values(3,0,"厨卫电器 生活电器","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(4,0,"大家电","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(5,0,"家居/日用/厨具","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(6,0,"服装鞋帽","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(7,0,"家装主材","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(8,0,"家具","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(9,0,"运动户外","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(10,0,"钟表/礼品/乐器","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(11,0,"珠宝","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(12,0,"其他","false");
--一级分类
insert into `commodity_classification` 	(id,pid,name,isParent) values(13,1,"手机通讯","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(14,1,"手机配件","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(15,1,"摄影摄像","false");
--一级分类
insert into `commodity_classification` 	(id,pid,name,isParent) values(16,2,"网络设备","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(17,2,"办公打印","false");
insert into `commodity_classification` 	(id,pid,name,isParent) values(18,2,"存储设备","false");
--二级分类



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

/**
 * sku集合表
 */
DROP TABLE IF  EXISTS `commodity_sku_collect`;
CREATE TABLE `commodity_sku_collect`(
	`sku_collect_id` INT AUTO_INCREMENT COMMENT "sku 集合 ID",
	`commodity_id` INT   COMMENT "商品ID",
	`price` DOUBLE COMMENT "价格",
	`stock` INT COMMENT "库存",
	PRIMARY KEY (`sku_collect_id`)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品SKU集合表";

/**
 * SKU表设计
 * sku_id          sku表id
 * commodity_id    商品id
 * sku_name       　名称：颜色/内存/样式/尺寸
 * attr           　属性值
 * price          　价格
 * stock          　库存
 * image_id        sku表与图片表，不同的sku属性会有不同的展示图片
 * ｜sku_id｜commodity_id｜sku_name｜attr｜
 * ｜１　　　｜１　　　　　　｜颜色　　　｜红色　｜
 * ｜２　　　｜１　　　　　　｜颜色　　　｜黄色　｜
 * ｜３　　　｜１　　　　　　｜颜色　　　｜黑色　｜
 * ｜４　　　｜１　　　　　　｜颜色　　　｜蓝色　｜
 * 每一个款式可以添加五张照片和一张跟随属性值的照片
 */
DROP TABLE IF  EXISTS `commodity_sku`;
CREATE TABLE `commodity_sku`(
	`sku_id` INT  AUTO_INCREMENT COMMENT "sku ID",
	`sku_collect_id` INT   COMMENT "sku集合ID",
	`commodity_id` INT   COMMENT "商品ID",
	`name` VARCHAR(10) COMMENT "名称:颜色/样式／款式/尺寸等",
	`attr`  VARCHAR(30) COMMENT "属性值",
	PRIMARY KEY (`sku_id`)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品SKU表";

/**
 * 商品图片,用于列表展示商品显示的图片
 * 或者商品详情页标题处展示的图片
 * 每个商品的ＳＫＵ　ＩＤ有五张　显示图片，五张图片中有一张需要设置为主图有一张属性图片
 */
DROP TABLE IF  EXISTS `commodity_image`;
CREATE TABLE `commodity_image`(
	`image_id` INT  AUTO_INCREMENT COMMENT "商品图片ID",
	`sku_collect_id` INT  COMMENT "sku_collect_id",
	`path` VARCHAR(100) COMMENT "图片相对路径/相对于工程目录下",
	`name` VARCHAR(100) COMMENT "图片名称　动态生成",
	`is_main_img` TINYINT DEFAULT 0  COMMENT "0/1,是否是主图，单个商品仅有一个设为1",
	`is_attr_img` TINYINT DEFAULT 0  COMMENT "0/1,是否是属性图片，每个属性配一个图片",
	`upload_by` VARCHAR(20) COMMENT "上传人",
	`upload_time` DATETIME COMMENT "上传时间",
	PRIMARY KEY (`image_id`)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品图片表";
/**
 *属性表　规格表设计
 *attr_id     属性表id
 *sku_id　　　　　　sku_id
 *name    　　　　属性名称
 *val         属性值
 *category    属性类别
 * |attr_id|sku_id|attr_name|attr_val|category｜
 * |１　　　|１　　　|品牌　　　|箭牌(ARROW)　　|主体参数｜
 * |２　　　|１　　　|型号　　　|AEO4B10158-S　|主体参数｜
 * |３　　　|１　　　|表面工艺　|拉丝　　　　　　|规格参数｜
 * |４　　　|１　　　|毛重　　　|8.6千克　　　　|规格参数｜
 */
DROP TABLE IF  EXISTS `commodity_attr`;
CREATE TABLE `commodity_attr`(
	`attr_id` INT  AUTO_INCREMENT COMMENT "属性ID",
	`commodity_id` INT   COMMENT "SKU ID",
	`attr_name` VARCHAR(30) COMMENT "名称 用户自定义",
	`attr_val`  VARCHAR(50) COMMENT "属性值",
	`category`  VARCHAR(30) COMMENT "分类　主体参数/规格参数",
	PRIMARY KEY (`attr_id`)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="商品属性表";



