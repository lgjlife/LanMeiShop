package org.lanmei.commodity.service;

import java.util.List;
import java.util.Map;

import org.lanmei.commodity.dto.ClassificationDto;
import org.lanmei.commodity.dto.TreeNodeDto;
import org.lanmei.common.enums.CommodityState;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	ClassificationDto addTreeNode(Integer pid,String name);
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
