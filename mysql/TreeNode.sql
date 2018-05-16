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
　　　`id` int  ;
　　　`pid` int ;

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="树形结构表";