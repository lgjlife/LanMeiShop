-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: lanmei
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cms_admin`
--

DROP TABLE IF EXISTS `cms_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `login_jobNum` varchar(20) DEFAULT NULL COMMENT '管理员工号',
  `admin_password` varchar(100) DEFAULT NULL COMMENT '管理员登录密码',
  `password_salt` varchar(50) DEFAULT NULL COMMENT '密码加密的盐',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '管理员昵称',
  `actual_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `department` varchar(50) DEFAULT NULL COMMENT '部门',
  `create_admin` int(11) DEFAULT NULL COMMENT '创建人员',
  `invitation_code` varchar(10) DEFAULT NULL COMMENT '注册时邀请码',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别-男/女',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `head_portrait` varchar(50) DEFAULT NULL COMMENT '头像',
  `state` varchar(50) DEFAULT NULL COMMENT '状态-待激活/正常状态/登录异常/禁止登录/注销状态',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱地址',
  `email_is_activate` varchar(20) DEFAULT '0' COMMENT '邮箱激活状态-激活/未激活',
  `phone_num` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(30) DEFAULT NULL COMMENT '最后登录ip',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `generate_time` datetime DEFAULT NULL COMMENT '帐号生成时间',
  `activate_time` datetime DEFAULT NULL COMMENT '帐号激活时间',
  `colose_time` datetime DEFAULT NULL COMMENT '帐号注销时间',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_admin`
--

LOCK TABLES `cms_admin` WRITE;
/*!40000 ALTER TABLE `cms_admin` DISABLE KEYS */;
INSERT INTO `cms_admin` VALUES (1,'201801','e8fd93e45891573df04aeb22211efffc','64e3db703d5d69b2da2377a3bba9d973','蓝莓1','赵云1','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(2,'201802','d0b58d59608726daf2936788278b8c0a','51176f7925b3c73508ca4540411cf66c','蓝莓2','赵云2','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(3,'201803','5842c238a2f2e0f378c03ebf55034d66','65f2f0bc0f2534d796d092f84f1dd76a','蓝莓3','赵云3','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(4,'201804','4e8290d29e566d99bdcab64895175cb0','1e9c8455f695b932041b10dbd72b8247','蓝莓4','赵云4','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(5,'201805','267c68c7a5e30adfd2fff162bb0a4239','cd93aec72d128f117e8881c7191a99b7','蓝莓5','赵云5','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(6,'201806','545d95249d3ba9a6a95ab531bfa175f5','0bc89c62470ba7ed618fa482f0067e55','蓝莓6','赵云6','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(7,'201807','8f860cb52744c30ff6c563fd74644b2f','4015a6f793d70f6b949ced3edcfc61b8','蓝莓7','赵云7','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(8,'201808','b71f4a38e8095962051a7b02e5ba0740','5d6792337e7bb03c3445b3f4601fef34','蓝莓8','赵云8','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(9,'20189','53ab07818a69b1e10a13d8daa4c8ff85','9cc4e8cb8a100877085bcd16dc95aab3','蓝莓9','赵云9','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(10,'201810','2d11de3ecfd1c0ed7c9a3ab32137c245','b76d70b7d4b668c5e64eb9539eb453af','蓝莓10','赵云10','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(11,'201811','df1150e7ebef1b7d74b95af1559000ca','b19713326702cbe9d3f8d9f1dc4dea77','蓝莓11','赵云11','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(12,'201812','88b3e7a7551d5e50431c06124fe2f41c','8dba6d4152c463f40da4098f935f7ce9','蓝莓12','赵云12','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(13,'201813','e5cd3595fab8165675d36fb3e7210f44','1a6bce67635300ed2fadefa17ae16280','蓝莓13','赵云13','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(14,'201814','4e9840dfb85857be2b0303f41248cc48','c5db19d06f62c2bb9cc4ccb914e712d3','蓝莓14','赵云14','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(15,'201815','77a7cc6a2effbe628d79e108f338ab91','fa277f0b8e831b92ea5c24266994369a','蓝莓15','赵云15','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(16,'201816','77a7cc6a2effbe628d79e108f338ab91','fa277f0b8e831b92ea5c24266994369a','蓝莓15','赵云16','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL),(17,'201817','77a7cc6a2effbe628d79e108f338ab91','fa277f0b8e831b92ea5c24266994369a','蓝莓15','赵云17','商品部',201800,'789456','MALE',19,NULL,'UNACTIVATE','563739007@qq.com','EMAIL_ACTIVATED','12345678911','2018-04-27 16:48:00','0.1.1.1',0,'2018-04-27 16:48:00',NULL,NULL);
/*!40000 ALTER TABLE `cms_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_admin_login`
--

DROP TABLE IF EXISTS `cms_admin_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_admin_login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志ID',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登录IP',
  `admin_id` int(11) DEFAULT NULL COMMENT '用户id',
  `explorer` varchar(150) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='管理员登录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_admin_login`
--

LOCK TABLES `cms_admin_login` WRITE;
/*!40000 ALTER TABLE `cms_admin_login` DISABLE KEYS */;
INSERT INTO `cms_admin_login` VALUES (1,'2018-05-08 07:01:40','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(2,'2018-05-08 15:29:19','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(3,'2018-05-11 00:29:28','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(4,'2018-05-11 01:25:44','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(5,'2018-05-11 13:59:02','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(6,'2018-05-11 20:49:15','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(7,'2018-05-11 23:54:35','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(8,'2018-05-12 00:19:09','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(9,'2018-05-12 01:48:59','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(10,'2018-05-12 03:55:53','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(11,'2018-05-12 05:20:42','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(12,'2018-05-12 18:08:31','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(13,'2018-05-12 22:37:17','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(14,'2018-05-13 15:35:20','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(15,'2018-05-13 21:17:26','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(16,'2018-05-13 21:32:21','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(17,'2018-05-14 14:15:54','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(18,'2018-05-14 17:48:33','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(19,'2018-05-14 22:46:35','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(20,'2018-05-14 23:42:24','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(21,'2018-05-15 01:41:34','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(22,'2018-05-15 02:15:05','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(23,'2018-05-15 18:48:43','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(24,'2018-05-15 23:17:51','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(25,'2018-05-16 01:58:08','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(26,'2018-05-16 03:39:34','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(27,'2018-05-16 04:55:04','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(28,'2018-05-16 14:27:36','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(29,'2018-05-16 16:05:41','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(30,'2018-05-16 21:18:40','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(31,'2018-05-16 22:05:10','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(32,'2018-05-16 23:36:00','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(33,'2018-05-17 04:24:06','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(34,'2018-05-17 05:01:07','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(35,'2018-05-17 14:23:08','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(36,'2018-05-17 17:52:05','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(37,'2018-05-17 22:18:38','127.0.0.1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(38,'2018-05-17 22:40:17','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(39,'2018-05-17 23:43:57','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(40,'2018-05-18 15:37:22','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(41,'2018-05-18 20:30:57','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(42,'2018-05-19 15:03:08','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(43,'2018-05-19 22:45:58','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(44,'2018-05-20 00:58:28','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(45,'2018-05-20 02:14:16','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(46,'2018-05-21 15:50:42','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(47,'2018-05-21 22:57:28','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36'),(48,'2018-05-22 01:32:42','0:0:0:0:0:0:0:1',1,'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36');
/*!40000 ALTER TABLE `cms_admin_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_menu`
--

DROP TABLE IF EXISTS `cms_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_menu` (
  `menu_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级编号',
  `menu_type` tinyint(2) DEFAULT NULL COMMENT '权限类型 1=菜单/2=功能/3=子功能/0=操作',
  `menu_code` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限代码',
  `menu_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限名称',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `href` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '链接地址',
  `icon` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '图标名称',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 0=隐藏/1=显示',
  `permission` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建人时间',
  `create_admin_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_admin_id` int(11) DEFAULT NULL COMMENT '更新人ID',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_menu`
--

LOCK TABLES `cms_menu` WRITE;
/*!40000 ALTER TABLE `cms_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_role`
--

DROP TABLE IF EXISTS `cms_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_flag` varchar(20) DEFAULT NULL COMMENT '角色标志',
  `state` varchar(20) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建人时间',
  `create_admin_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_admin_id` int(11) DEFAULT NULL COMMENT '更新人ID',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_role`
--

LOCK TABLES `cms_role` WRITE;
/*!40000 ALTER TABLE `cms_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_role_permission`
--

DROP TABLE IF EXISTS `cms_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_role_permission` (
  `role_permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_admin_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`role_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_role_permission`
--

LOCK TABLES `cms_role_permission` WRITE;
/*!40000 ALTER TABLE `cms_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity` (
  `commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `category_id` int(11) DEFAULT NULL COMMENT '商品类别id 三级分类　品牌',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `brand` varchar(50) DEFAULT NULL COMMENT '商品品牌',
  `title` varchar(150) DEFAULT NULL COMMENT '显示标题',
  `description` longtext COMMENT '商品简介',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `put_on_sale_time` datetime DEFAULT NULL COMMENT '上架时间',
  `put_off_sale_time` datetime DEFAULT NULL COMMENT '下架时间',
  `sale_state` tinyint(4) DEFAULT '-1' COMMENT '销售状态: -1:新建商品，０:　已经上架，正在销售，１：已经下架，停止销售',
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity`
--

LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` VALUES (1,48,'魅蓝note6','魅蓝','魅蓝旗舰机',NULL,'测试用户','2018-05-22 00:33:05',NULL,NULL,-1),(2,49,'坚果R1','坚果','坚果旗舰机',NULL,'测试用户','2018-05-22 00:33:40',NULL,NULL,-1),(3,85,'跑步鞋　fly','阿迪达斯','跑步鞋，轻便！',NULL,'测试用户','2018-05-22 00:34:48',NULL,NULL,-1);
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_ｉmage`
--

DROP TABLE IF EXISTS `commodity_ｉmage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_ｉmage` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片ID',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `path` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `is_list_display` tinyint(4) DEFAULT '0' COMMENT '0/1,是否是搜索时列表展示的图片，单个商品仅有一个设为1',
  `upload_by` varchar(20) DEFAULT NULL COMMENT '上传人',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_ｉmage`
--

LOCK TABLES `commodity_ｉmage` WRITE;
/*!40000 ALTER TABLE `commodity_ｉmage` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity_ｉmage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_attr`
--

DROP TABLE IF EXISTS `commodity_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_attr` (
  `attr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性ID',
  `sku_id` int(11) DEFAULT NULL COMMENT 'SKU ID',
  `attr_name` varchar(30) DEFAULT NULL COMMENT '名称 用户自定义',
  `attr_val` varchar(50) DEFAULT NULL COMMENT '属性值',
  `category` varchar(30) DEFAULT NULL COMMENT '分类　主体参数/规格参数',
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_attr`
--

LOCK TABLES `commodity_attr` WRITE;
/*!40000 ALTER TABLE `commodity_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_classification`
--

DROP TABLE IF EXISTS `commodity_classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_classification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `pid` int(11) DEFAULT NULL COMMENT '父分类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `isParent` varchar(5) DEFAULT NULL COMMENT '是否是父分类，是父分类说明有子类',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='商品分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_classification`
--

LOCK TABLES `commodity_classification` WRITE;
/*!40000 ALTER TABLE `commodity_classification` DISABLE KEYS */;
INSERT INTO `commodity_classification` VALUES (1,0,'手机/数码/配件','true',NULL,NULL,NULL),(2,0,'电脑/办公/外设','true',NULL,NULL,NULL),(3,0,'厨卫电器 生活电器','false',NULL,NULL,NULL),(4,0,'大家电','false',NULL,NULL,NULL),(5,0,'家居/日用/厨具','false',NULL,NULL,NULL),(6,0,'服装鞋帽','false',NULL,NULL,NULL),(7,0,'家装主材','false',NULL,NULL,NULL),(8,0,'家具','false',NULL,NULL,NULL),(9,0,'运动户外','true',NULL,NULL,NULL),(10,0,'钟表/礼品/乐器','false',NULL,NULL,NULL),(11,0,'珠宝','false',NULL,NULL,NULL),(12,0,'其他','false',NULL,NULL,NULL),(13,1,'手机通讯','true',NULL,NULL,NULL),(16,2,'网络设备','true',NULL,NULL,NULL),(17,2,'办公打印','false',NULL,NULL,NULL),(18,2,'存储设备','false',NULL,NULL,NULL),(19,1,'数码配件','true','测试用户','2018-05-11 21:00:41',NULL),(20,1,'影音电子','true','测试用户','2018-05-11 21:01:11',NULL),(21,1,'电子教育','false','测试用户','2018-05-11 21:01:26',NULL),(22,1,'运营商','false','测试用户','2018-05-11 21:01:47',NULL),(23,1,'智能设备','false','测试用户','2018-05-11 21:01:57',NULL),(24,1,'手机维修','false','测试用户','2018-05-11 21:02:17',NULL),(25,1,'智能酷玩','false','测试用户','2018-05-11 21:02:40',NULL),(39,13,'手机','true','测试用户','2018-05-11 21:19:03',NULL),(41,19,'滤镜','true','测试用户','2018-05-11 21:20:26',NULL),(42,19,'存储卡','true','测试用户','2018-05-11 21:21:44',NULL),(43,19,'闪光灯','true','测试用户','2018-05-11 21:21:52',NULL),(44,20,'录音笔','false','测试用户','2018-05-11 21:22:30',NULL),(45,20,'耳机耳麦','false','测试用户','2018-05-11 21:22:40',NULL),(46,20,'MP3/MP4','false','测试用户','2018-05-11 21:22:57',NULL),(48,39,'魅蓝','false','测试用户','2018-05-13 23:45:16',NULL),(49,39,'坚果','false','测试用户','2018-05-13 23:45:34',NULL),(50,39,'锤子','false','测试用户','2018-05-13 23:45:46',NULL),(51,39,'红米','false','测试用户','2018-05-13 23:45:58',NULL),(53,39,'荣耀','false','测试用户','2018-05-13 23:46:15',NULL),(54,39,'其他','false','测试用户','2018-05-14 22:47:48',NULL),(55,42,'三星','false','测试用户','2018-05-14 22:49:24',NULL),(56,42,'尼康','false','测试用户','2018-05-14 22:49:33',NULL),(57,41,'耐司','false','测试用户','2018-05-14 22:50:13',NULL),(58,41,'莱卡','false','测试用户','2018-05-14 22:50:20',NULL),(59,43,'佳能','false','测试用户','2018-05-14 22:50:43',NULL),(60,43,'银燕','false','测试用户','2018-05-14 22:50:54',NULL),(61,16,'交换机','false','测试用户','2018-05-14 22:52:33',NULL),(62,16,'路由器','false','测试用户','2018-05-14 22:52:39',NULL),(68,9,'运动鞋','true','测试用户','2018-05-15 02:51:36',NULL),(69,9,'户外装备','false','测试用户','2018-05-15 02:51:58',NULL),(82,68,'跑步鞋','true','测试用户','2018-05-15 02:58:03',NULL),(85,82,'阿迪达斯','false','测试用户','2018-05-15 03:12:14',NULL);
/*!40000 ALTER TABLE `commodity_classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_classification_association`
--

DROP TABLE IF EXISTS `commodity_classification_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_classification_association` (
  `association_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类ID',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `id` int(11) DEFAULT NULL COMMENT '分类ID',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`association_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_classification_association`
--

LOCK TABLES `commodity_classification_association` WRITE;
/*!40000 ALTER TABLE `commodity_classification_association` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity_classification_association` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_image`
--

DROP TABLE IF EXISTS `commodity_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片ID',
  `sku_id` int(11) DEFAULT NULL COMMENT 'sku ID',
  `path` varchar(100) DEFAULT NULL COMMENT '图片相对路径/相对于工程目录下',
  `name` varchar(100) DEFAULT NULL COMMENT '图片名称　动态生成',
  `is_main_img` tinyint(4) DEFAULT '0' COMMENT '0/1,是否是主图，单个商品仅有一个设为1',
  `is_attr_img` tinyint(4) DEFAULT '0' COMMENT '0/1,是否是属性图片，每个属性配一个图片',
  `upload_by` varchar(20) DEFAULT NULL COMMENT '上传人',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='商品图片表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_image`
--

LOCK TABLES `commodity_image` WRITE;
/*!40000 ALTER TABLE `commodity_image` DISABLE KEYS */;
INSERT INTO `commodity_image` VALUES (1,NULL,'upload/img/','1526479530951222.jpg',NULL,NULL,'测试用户','2018-05-16 22:05:30'),(2,NULL,'upload/img/','1526479569146222.jpg',NULL,NULL,'测试用户','2018-05-16 22:06:09'),(3,NULL,'upload/img/','1526480547469222.jpg',NULL,NULL,'测试用户','2018-05-16 22:22:27'),(4,NULL,'upload/img/','1526480624005222.jpg',NULL,NULL,'测试用户','2018-05-16 22:23:43'),(5,NULL,'upload/img/','1526480907592222.jpg',NULL,NULL,'测试用户','2018-05-16 22:28:27'),(6,NULL,'upload/img/','1526480985331222.jpg',NULL,NULL,'测试用户','2018-05-16 22:29:45'),(7,NULL,'upload/img/','1526481428946222.jpg',NULL,NULL,'测试用户','2018-05-16 22:37:08'),(8,NULL,'upload/img/','1526481509299222.jpg',NULL,NULL,'测试用户','2018-05-16 22:38:29'),(9,NULL,'upload/img/','1526481697951222.jpg',NULL,NULL,'测试用户','2018-05-16 22:41:38'),(10,NULL,'upload/img/','1526481716022222.jpg',NULL,NULL,'测试用户','2018-05-16 22:41:55'),(11,NULL,'upload/img/','1526481733777222.jpg',NULL,NULL,'测试用户','2018-05-16 22:42:13'),(12,NULL,'upload/img/','1526482152626222.jpg',NULL,NULL,'测试用户','2018-05-16 22:49:12'),(13,NULL,'upload/img/','1526482248848222.jpg',NULL,NULL,'测试用户','2018-05-16 22:50:48'),(14,NULL,'upload/img/','1526482579313222.jpg',NULL,NULL,'测试用户','2018-05-16 22:56:19'),(15,NULL,'upload/img/','1526482596569222.jpg',NULL,NULL,'测试用户','2018-05-16 22:56:36'),(16,NULL,'upload/img/','1526482673058222.jpg',NULL,NULL,'测试用户','2018-05-16 22:57:52'),(17,NULL,'upload/img/','1526482693554222.jpg',NULL,NULL,'测试用户','2018-05-16 22:58:13'),(18,NULL,'upload/img/','1526482704736图片5.jpg',NULL,NULL,'测试用户','2018-05-16 22:58:24'),(19,NULL,'upload/img/','1526482778356222.jpg',NULL,NULL,'测试用户','2018-05-16 22:59:38'),(20,NULL,'upload/img/','1526482843017图片5.jpg',NULL,NULL,'测试用户','2018-05-16 23:00:42'),(21,NULL,'upload/img/','1526482910940222.jpg',NULL,NULL,'测试用户','2018-05-16 23:01:51'),(22,NULL,'upload/img/','1526482983565222.jpg',NULL,NULL,'测试用户','2018-05-16 23:03:03'),(23,NULL,'upload/img/','1526483021626222.jpg',NULL,NULL,'测试用户','2018-05-16 23:03:41'),(24,NULL,'upload/img/','1526483904034123.jpg',NULL,NULL,'测试用户','2018-05-16 23:18:24'),(25,NULL,'upload/img/','1526483972119222.jpg',NULL,NULL,'测试用户','2018-05-16 23:19:31'),(26,NULL,'upload/img/','1526484607652222.jpg',NULL,NULL,'测试用户','2018-05-16 23:30:08'),(27,NULL,'upload/img/','1526484979499222.jpg',NULL,NULL,'测试用户','2018-05-16 23:36:19'),(28,NULL,'upload/img/','1526485192419222.jpg',NULL,NULL,'测试用户','2018-05-16 23:39:52'),(29,NULL,'upload/img/','1526485555960222.jpg',NULL,NULL,'测试用户','2018-05-16 23:45:55'),(30,NULL,'upload/img/','1526485884698222.jpg',NULL,NULL,'测试用户','2018-05-16 23:51:24'),(31,NULL,'upload/img/','1526486059817222.jpg',NULL,NULL,'测试用户','2018-05-16 23:54:19'),(32,NULL,'upload/img/','1526486285563123.jpg',NULL,NULL,'测试用户','2018-05-16 23:58:05'),(33,NULL,'upload/img/','1526486451881222.jpg',NULL,NULL,'测试用户','2018-05-17 00:00:52'),(34,NULL,'upload/img/','1526486510526图片5.jpg',NULL,NULL,'测试用户','2018-05-17 00:01:50'),(35,NULL,'upload/img/','1526486665441222.jpg',NULL,NULL,'测试用户','2018-05-17 00:04:25'),(36,NULL,'upload/img/','1526486731819222.jpg',NULL,NULL,'测试用户','2018-05-17 00:05:31'),(37,NULL,'upload/img/','152648680428182032e4f9c9e3cc798c69931509885b9_r.jpg',NULL,NULL,'测试用户','2018-05-17 00:06:43'),(38,NULL,'upload/img/','1526486893609222.jpg',NULL,NULL,'测试用户','2018-05-17 00:08:13'),(39,NULL,'upload/img/','1526486909523123.jpg',NULL,NULL,'测试用户','2018-05-17 00:08:29'),(40,NULL,'upload/img/','1526486938113222.jpg',NULL,NULL,'测试用户','2018-05-17 00:08:57'),(41,NULL,'upload/img/','1526486954908123.jpg',NULL,NULL,'测试用户','2018-05-17 00:09:14'),(42,NULL,'upload/img/','152648700548882032e4f9c9e3cc798c69931509885b9_r.jpg',NULL,NULL,'测试用户','2018-05-17 00:10:05'),(43,NULL,'upload/img/','1526487028052222.jpg',NULL,NULL,'测试用户','2018-05-17 00:10:28'),(44,NULL,'upload/img/','1526487042688222.jpg',NULL,NULL,'测试用户','2018-05-17 00:10:43'),(45,NULL,'upload/img/','1526487114943222.jpg',NULL,NULL,'测试用户','2018-05-17 00:11:55');
/*!40000 ALTER TABLE `commodity_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_reviews`
--

DROP TABLE IF EXISTS `commodity_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_reviews` (
  `reviews_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `grade` tinyint(4) DEFAULT NULL COMMENT '评价等级0-５颗星',
  `is_have_image` tinyint(4) DEFAULT NULL COMMENT '0/1,是否是有图评价',
  `is_additional_reviews` tinyint(4) DEFAULT NULL COMMENT '0/1,是否是追加评价',
  `content` text COMMENT '评价内容',
  `reviews_by` varchar(20) DEFAULT NULL COMMENT '评价人，手机号',
  `reviews_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`reviews_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_reviews`
--

LOCK TABLES `commodity_reviews` WRITE;
/*!40000 ALTER TABLE `commodity_reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity_reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_sku`
--

DROP TABLE IF EXISTS `commodity_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_sku` (
  `sku_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'sku ID',
  `sku_collect_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `name` varchar(10) DEFAULT NULL COMMENT '名称:颜色/样式／款式/尺寸等',
  `attr` varchar(30) DEFAULT NULL COMMENT '属性值',
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SKU表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_sku`
--

LOCK TABLES `commodity_sku` WRITE;
/*!40000 ALTER TABLE `commodity_sku` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity_sku_collect`
--

DROP TABLE IF EXISTS `commodity_sku_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity_sku_collect` (
  `sku_collect_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'sku 集合 ID',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `price` double DEFAULT NULL COMMENT '价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`sku_collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SKU集合表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity_sku_collect`
--

LOCK TABLES `commodity_sku_collect` WRITE;
/*!40000 ALTER TABLE `commodity_sku_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity_sku_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbmaintain_scripts`
--

DROP TABLE IF EXISTS `dbmaintain_scripts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbmaintain_scripts` (
  `file_name` varchar(150) DEFAULT NULL,
  `version` varchar(25) DEFAULT NULL,
  `file_last_modified_at` bigint(20) DEFAULT NULL,
  `checksum` varchar(50) DEFAULT NULL,
  `executed_at` varchar(20) DEFAULT NULL,
  `succeeded` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbmaintain_scripts`
--

LOCK TABLES `dbmaintain_scripts` WRITE;
/*!40000 ALTER TABLE `dbmaintain_scripts` DISABLE KEYS */;
INSERT INTO `dbmaintain_scripts` VALUES ('001_seckill.sql','1',1525156416000,'22b824f0d3e24fcf72f011de8a4e4f9d','2018-05-02 01:28:22',1);
/*!40000 ALTER TABLE `dbmaintain_scripts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `os_favorites`
--

DROP TABLE IF EXISTS `os_favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `os_favorites` (
  `favorites_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏夹ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`favorites_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏夹';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `os_favorites`
--

LOCK TABLES `os_favorites` WRITE;
/*!40000 ALTER TABLE `os_favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `os_favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `os_receipt_address`
--

DROP TABLE IF EXISTS `os_receipt_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `os_receipt_address` (
  `receipt_address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(30) DEFAULT NULL COMMENT '收货姓名',
  `phone_num` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `province_id` int(11) DEFAULT NULL COMMENT '省份id',
  `province_name` varchar(30) DEFAULT NULL COMMENT '省名字',
  `city_id` int(11) DEFAULT NULL COMMENT '市id',
  `city_name` varchar(30) DEFAULT NULL COMMENT '市名字',
  `district_id` int(11) DEFAULT NULL COMMENT '区县id',
  `district_name` varchar(30) DEFAULT NULL COMMENT '区县名字',
  `street_id` int(11) DEFAULT NULL COMMENT '街道id',
  `street_name` varchar(60) DEFAULT NULL COMMENT '街道名字',
  `post_code` varchar(60) DEFAULT NULL COMMENT '邮政编码',
  `create_time` datetime DEFAULT NULL COMMENT '地址创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '地址更新时间',
  PRIMARY KEY (`receipt_address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收获地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `os_receipt_address`
--

LOCK TABLES `os_receipt_address` WRITE;
/*!40000 ALTER TABLE `os_receipt_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `os_receipt_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `os_user`
--

DROP TABLE IF EXISTS `os_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `os_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_number` int(11) DEFAULT NULL COMMENT '用户编号',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `login_password` varchar(50) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL COMMENT '登录密码盐',
  `actual_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `head_portrait` longblob COMMENT '头像',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `email_active` tinyint(4) DEFAULT NULL COMMENT '邮箱是否激活',
  `phone_num` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_nums` int(11) DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `os_user`
--

LOCK TABLES `os_user` WRITE;
/*!40000 ALTER TABLE `os_user` DISABLE KEYS */;
INSERT INTO `os_user` VALUES (1,NULL,NULL,'0d89dd7951ca71cff81d188bba652a7f','d80e1cb0c797ca46d05e51b202c9ba99',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'13925716752','2018-05-04 16:08:01',NULL,NULL);
/*!40000 ALTER TABLE `os_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `os_user_login`
--

DROP TABLE IF EXISTS `os_user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `os_user_login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志ID',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登录IP',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `explorer` varchar(150) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `os_user_login`
--

LOCK TABLES `os_user_login` WRITE;
/*!40000 ALTER TABLE `os_user_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `os_user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seckill`
--

DROP TABLE IF EXISTS `seckill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seckill` (
  `seckill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '秒杀ID',
  `seckill_name` varchar(50) DEFAULT NULL COMMENT '秒杀名称',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `start_time` datetime DEFAULT NULL COMMENT '秒杀时间',
  `finish_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`seckill_id`),
  KEY `index_create_time` (`create_time`),
  KEY `index_start_time` (`start_time`),
  KEY `index_finish_time` (`finish_time`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='秒杀表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seckill`
--

LOCK TABLES `seckill` WRITE;
/*!40000 ALTER TABLE `seckill` DISABLE KEYS */;
INSERT INTO `seckill` VALUES (1,'2000元秒杀iphone 5 还未开始',999,'2018-05-05 14:33:30','2018-05-10 14:33:30','2018-05-10 15:33:30'),(2,'3000元秒杀mate 9  还未开始',1999,'2018-05-05 14:33:31','2018-05-10 14:33:31','2018-05-10 15:33:31'),(3,'50元秒杀小米充电宝 已经结束',399,'2018-05-05 14:33:31','2018-04-30 13:33:31','2018-04-30 14:33:31'),(4,'400秒杀三星内存条 已经结束',499,'2018-05-05 14:33:31','2018-04-30 13:33:31','2018-04-30 14:33:31'),(5,'400秒杀三星内存条 正在进行',461,'2018-05-05 14:33:32','2018-04-30 13:33:32','2018-05-10 14:33:32'),(6,'400秒杀三星内存条 正在进行',489,'2018-05-05 14:33:32','2018-04-30 13:33:32','2018-05-10 14:33:32');
/*!40000 ALTER TABLE `seckill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seckill_success`
--

DROP TABLE IF EXISTS `seckill_success`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seckill_success` (
  `seckill_id` int(11) NOT NULL COMMENT '秒杀ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `state` varchar(30) DEFAULT '-1' COMMENT '秒杀状态，-1：无效，0：成功，1：已付款，2：已发货，3：已收货，4：交易完成',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_id`),
  KEY `index_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seckill_success`
--

LOCK TABLES `seckill_success` WRITE;
/*!40000 ALTER TABLE `seckill_success` DISABLE KEYS */;
INSERT INTO `seckill_success` VALUES (6,1,'SECKILL_SUCCESS','2018-05-05 23:20:49');
/*!40000 ALTER TABLE `seckill_success` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `description` text COMMENT '日志描述',
  `method` varchar(200) DEFAULT NULL COMMENT '执行方法',
  `LogType` tinyint(4) DEFAULT NULL COMMENT '0：正常日志，1：异常日志',
  `request_ip` varchar(50) DEFAULT NULL COMMENT '请求IP',
  `exception_code` varchar(50) DEFAULT NULL COMMENT '异常代码',
  `exception_detail` varchar(200) DEFAULT NULL COMMENT '异常信息',
  `params` varchar(200) DEFAULT NULL COMMENT '请求参数',
  `create_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `create_date` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,'增加节点','org.lanmei.cms.controller.commodity.CommodityController.addTreeNode()',1,'127.0.0.1','java.lang.NumberFormatException','messsge = For input string: \"{\"name\":\"新类目1\",\"id\":\"1\"}\"   cause = null','[{\"name\":\"新类目1\",\"id\":\"1\"}, {\"name\":\"新类目1\",\"id\":\"1\"}]','非登陆用户','2018-05-10 18:30:41');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_solr`
--

DROP TABLE IF EXISTS `sys_solr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_solr` (
  `solrid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `solrname` varchar(50) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`solrid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='solr';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_solr`
--

LOCK TABLES `sys_solr` WRITE;
/*!40000 ALTER TABLE `sys_solr` DISABLE KEYS */;
INSERT INTO `sys_solr` VALUES (1,'但是规范的'),(2,'的的发送给'),(3,'ｖ吃饭的的不'),(4,'地方噶的是个'),(5,'啊风格'),(6,'埃弗顿是');
/*!40000 ALTER TABLE `sys_solr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_log`
--

DROP TABLE IF EXISTS `system_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `description` varchar(100) DEFAULT NULL COMMENT '日志描述',
  `layer` varchar(25) DEFAULT NULL COMMENT '层　Controller Service Other',
  `method` varchar(200) DEFAULT NULL COMMENT '执行方法',
  `LogType` tinyint(4) DEFAULT NULL COMMENT '0：正常日志，1：异常日志',
  `request_ip` varchar(50) DEFAULT NULL COMMENT '请求IP',
  `exception_code` varchar(50) DEFAULT NULL COMMENT '异常代码',
  `exception_detail` varchar(200) DEFAULT NULL COMMENT '异常信息',
  `params` varchar(200) DEFAULT NULL COMMENT '请求参数',
  `create_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `create_date` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_log`
--

LOCK TABLES `system_log` WRITE;
/*!40000 ALTER TABLE `system_log` DISABLE KEYS */;
INSERT INTO `system_log` VALUES (1,'获取商品列表请求','Controller','org.lanmei.cms.controller.commodity.CommodityController.getCommodityList()',1,'127.0.0.1','java.lang.NoSuchMethodException','messsge = org.lanmei.cms.controller.commodity.CommodityController.getCommodityList()   cause = null','[{id=2}]','非登陆用户','2018-05-21 22:48:40');
/*!40000 ALTER TABLE `system_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_measurement`
--

DROP TABLE IF EXISTS `time_measurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_measurement` (
  `measurement_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `method` varchar(200) DEFAULT NULL COMMENT '执行方法',
  `method_desc` varchar(80) DEFAULT NULL COMMENT '执行方法描述',
  `layer` varchar(15) DEFAULT NULL COMMENT '层：Controller,Service,Other',
  `avr_time` bigint(20) DEFAULT NULL COMMENT '平均执行时间 MS',
  `run_count` bigint(20) DEFAULT NULL COMMENT '执行次数',
  `last_time` bigint(20) DEFAULT NULL COMMENT '上一次执行时间 MS',
  PRIMARY KEY (`measurement_id`),
  KEY `method` (`method`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='方法执行时间统计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_measurement`
--

LOCK TABLES `time_measurement` WRITE;
/*!40000 ALTER TABLE `time_measurement` DISABLE KEYS */;
INSERT INTO `time_measurement` VALUES (1,'org.lanmei.cms.controller.homepage.HomePageController.HomePage','主界面访问','Controller',10,253,19);
/*!40000 ALTER TABLE `time_measurement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tree_node`
--

DROP TABLE IF EXISTS `tree_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tree_node` (
  `id` int(11) NOT NULL COMMENT '登录日志ID',
  `pid` int(11) DEFAULT NULL,
  `name` varchar(5) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='树形结构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tree_node`
--

LOCK TABLES `tree_node` WRITE;
/*!40000 ALTER TABLE `tree_node` DISABLE KEYS */;
INSERT INTO `tree_node` VALUES (1,0,'A',0),(2,1,'B',3),(4,1,'D',5),(5,4,'E',6),(6,4,'F',7),(7,0,'G',2),(8,7,'H',8),(9,7,'I',9),(10,7,'J',10),(11,10,'K',11),(12,10,'L',12),(13,11,'M',13),(14,11,'N',14);
/*!40000 ALTER TABLE `tree_node` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-22  1:36:21
