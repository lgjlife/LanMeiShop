package org.lanmei.commodity.service;

import java.util.List;

import org.lanmei.commodity.dto.ClassificationDto;
import org.lanmei.commodity.dto.TreeNodeDto;

public interface ClassificationService {

	/**
	 * 获取节点
	 * @param id
	 * @return
	 */
	List<TreeNodeDto> getTreeNode(Integer parentId);
	/**
	 * 增加节点
	 * @param id
	 */
	ClassificationDto addTreeNode(Integer pid,String name);
	/**
	 * 更改节点名称
	 * @param id
	 */
	ClassificationDto modifyTreeNode(Integer id,Integer pid,String name);
	/**
	 * 删除节点
	 * @param id
	 */
	ClassificationDto deleteTreeNode(Integer id);
}
