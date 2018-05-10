package org.lanmei.commodity.service;

import java.util.List;

import org.lanmei.commodity.dto.TreeNodeDto;

public interface ClassificationService {

	/**
	 * 获取节点
	 * @param id
	 * @return
	 */
	List<TreeNodeDto> getTreeNode(Integer parentId);
}
