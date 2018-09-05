package com.lanmei.commodity.service;

import com.lanmei.commodity.dto.ClassificationDto;
import com.lanmei.commodity.dto.TreeNodeDto;

import java.util.List;

public interface ClassificationService {

	/**
	 * 获取分类节点
	 * @param id
	 * @return
	 */
	List<TreeNodeDto> getTreeNode(Integer parentId);
	/**
	 * 增加分类节点
	 * @param id
	 */
	ClassificationDto addTreeNode(Integer pid, String name);
	/**
	 * 更改分类节点名称
	 * @param id
	 */
	ClassificationDto modifyTreeNode(Integer id,Integer pid,String name);
	/**
	 * 删除分类节点
	 * @param id
	 */
	ClassificationDto deleteTreeNode(Integer id);
	
	
	
}
