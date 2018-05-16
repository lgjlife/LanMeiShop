/**
* 用于测试使用存储过程获取树形结构某个节点的所有最中节点
*
*/
/**
 *创建表
 *  
 */



DROP TABLE IF EXISTS `tree_node`;
CREATE TABLE `tree_node`(
	`id` INT   COMMENT "登录日志ID"  ,
    `pid` INT NULL,
    `name` VARCHAR(5),
    `sort` INT NULL,
	PRIMARY KEY (`id`)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='树形结构表';
/**
*表结构
*  0 x---1 A---2 B
*           ---3 C
*           ---4 D----5E
*                 ----6F
*     ---7 G---8 H
*           ---9 I       -----14N
*           ---10 J----11K----13M
*                  ----12L
*/
/**
 * １．　0 查找　１　和　７　放入临时表
 * ２．　获取临时表的　１　，获得　2　3　4，有结果　移除　１，　放入临时表　7　　2　3　4;
 * 2.   获取临时表　7　查询　，获得　8　9　10，　移除　７　，放临时表　2　3　4　8　9　10　　　　　
 */
insert into  tree_node values(1,0,"A",0);
insert into tree_node values(7,0,"G",2);
insert into tree_node values(2,1,"B",3);
insert into tree_node values(3,1,"C",4);
insert into tree_node values(4,1,"D",5);
insert into tree_node values(5,4,"E",6);
insert into tree_node values(6,4,"F",7);

insert into tree_node values(8,7,"H",8);
insert into tree_node values(9,7,"I",9);
insert into tree_node values(10,7,"J",10);
insert into tree_node values(11,10,"K",11);
insert into tree_node values(12,10,"L",12);
insert into tree_node values(13,11,"M",13);
insert into tree_node values(14,11,"N",14);

/**
 *创建存储过程
 */
DROP procedure IF EXISTS getTreeChild;

DELIMITER  //
CREATE procedure `getTreeChild`(IN pidIn INT)

BEGIN
	DROP TEMPORARY TABLE IF EXISTS `temp_tabel`;
	
	CREATE TEMPORARY TABLE `temp_tabel`(
	`id`  INT
	);
	
	SELECT id INTO  `temp_tabel` FROM `tree_node` WHERE pid = pidIn;
END //

DROP procedure IF EXISTS getTreeChild;

DELIMITER  //
CREATE FUNCTION `getTreeChild`( p_id INT)     
BEGIN
DECLARE sTemp VARCHAR(200);  
DECLARE sTempChd VARCHAR(200);  
SET sTemp = '$';  
SET sTempChd = cast(startId as char);  
WHILE sTempChd is not NULL DO  
SET sTemp = CONCAT(sTemp,',',sTempChd);  
SELECT group_concat(id) INTO sTempChd FROM tree_node where FIND_IN_SET(p_id,sTempChd)>0;  
END WHILE;  
return sTemp;  
END //

DELIMITER  //
CREATE FUNCTION   `myfunction`()
BEGIN
END //


/**
 * 调用存储过程
 * 
 */
call `getTreeChild`(0);
select temp_tabel;

-------------------------------------------
set max_sp_recursion_depth=255;  
call showChildLst(1);  
  
  
DROP PROCEDURE IF EXISTS `createChildLst`;  
delimiter ;;  
CREATE DEFINER=`root`@`localhost` PROCEDURE `createChildLst`(IN pidin INT,IN nDepth INT)  
    COMMENT '入口过程'  
BEGIN  
    DECLARE done INT DEFAULT 0;  
    DECLARE b INT;  
    DECLARE cur1 CURSOR FOR SELECT id FROM tree_node where pid=pidin order by sort;  
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1; 
      
    OPEN cur1;  
  
    FETCH cur1 INTO b;  
    INSERT INTO tmpLst VALUES (NULL,pidin,nDepth,done);  
      
    WHILE done=0 DO  
         CALL createChildLst(b,nDepth+1);  
         
         FETCH cur1 INTO b;  
           
    END WHILE;  
  
    CLOSE cur1;  
 END  
 ;;  
delimiter ;  
  
-- ----------------------------  
--  Procedure structure for `showChildLst`  
-- ----------------------------  
DROP PROCEDURE IF EXISTS `showChildLst`;  
delimiter ;;  
CREATE DEFINER=`root`@`localhost` PROCEDURE `showChildLst`(IN pid INT)  
    COMMENT '递归过程'  
BEGIN  
    CREATE TEMPORARY TABLE IF NOT EXISTS tmpLst(sno int primary key auto_increment,id int,depth int,isLeaf int);  
    DELETE FROM tmpLst;  
  
    CALL createChildLst(pid,0);  
    select tmpLst.*,tree_node.* from tmpLst,tree_node where tmpLst.id=tree_node.id order by tmpLst.sno;  
END  
 ;;  
delimiter ;  

