package org.lanmei.commodity.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.lanmei.commodity.dao.mapper.CommodityClassificationMapper;
import org.lanmei.commodity.dao.model.CommodityClassification;
import org.lanmei.commodity.dto.TreeNodeDto;
import org.lanmei.commodity.service.ClassificationService;
import org.lanmei.common.baseservice.BaseService;
import org.lanmei.common.convert.ConvertBeanToBean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;

@Service
public class ClassificationServiceImpl extends BaseService  implements ClassificationService{
	private static final Logger Logger = LoggerFactory.getLogger(ClassificationServiceImpl.class);
	
	@Autowired
	private CommodityClassificationMapper classificationMapper;

	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	
	@Override
	public List<TreeNodeDto> getTreeNode(Integer parentId) {
		List<TreeNodeDto> treeNodeDtoList = new ArrayList();	
		logger.debug("从数据库中获取数据..... ");
		List<CommodityClassification> classification = classificationMapper.selectByＰarentId(parentId);
		
		/*logger.debug("classification.size  = " + classification.size() );*/
		for(int index = 0; index < classification.size() ;index++) {
			TreeNodeDto treeNode = new TreeNodeDto();
			/*logger.debug("index = " + index );
			logger.debug("classification.name = " + classification.get(index).getName());
			logger.debug("classification.isParent = " + classification.get(index).getIsparent());*/
			treeNode = (TreeNodeDto)ConvertBeanToBean.convert(classification.get(index),treeNode);
			boolean flag = treeNodeDtoList.add(treeNode);
		/*	logger.debug("flag = " + flag);
			logger.debug("treeNodeDtoList.size = " + treeNodeDtoList.size());
			logger.debug("treeNodeDtoList.name = " + treeNodeDtoList.get(0).getName());
			logger.debug("treeNodeDtoList.isParent = " + treeNodeDtoList.get(0).getIsparent());
			logger.debug("\r\n");*/
		}	
		return treeNodeDtoList;
	}
	
	
	
	
}
