package org.lanmei.commodity.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.commodity.dao.mapper.CommodityClassificationAssociationMapper;
import org.lanmei.commodity.dao.mapper.CommodityClassificationMapper;
import org.lanmei.commodity.dao.model.CommodityClassification;
import org.lanmei.commodity.dto.ClassificationDto;
import org.lanmei.commodity.dto.TreeNodeDto;
import org.lanmei.commodity.service.ClassificationService;
import org.lanmei.common.baseservice.BaseService;
import org.lanmei.common.convert.ConvertBeanToBean;
import org.lanmei.common.enums.CommodityState;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.pool.DruidDataSource;

@Service
public class ClassificationServiceImpl extends BaseService  implements ClassificationService{
	
	private static final Logger logger = LoggerFactory.getLogger("ClassificationServiceImpl.class");
	
	@Autowired
	private CommodityClassificationMapper classificationMapper;
	@Autowired
	CommodityClassificationAssociationMapper  associationMapper;
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	/**
	 * 获取parentId的子节点数据
	 */
	@Override
	public List<TreeNodeDto> getTreeNode(Integer parentId) {
		List<TreeNodeDto> treeNodeDtoList = new ArrayList<TreeNodeDto>();	
		logger.debug("从数据库中获取数据..... ");
		List<CommodityClassification> classification = classificationMapper.selectByＰarentId(parentId);
	    //转换
		for(int index = 0; index < classification.size() ;index++) {
			TreeNodeDto treeNode = new TreeNodeDto();
			treeNode = (TreeNodeDto)ConvertBeanToBean.convert(classification.get(index),treeNode);
			boolean flag = treeNodeDtoList.add(treeNode);
		}	
		return treeNodeDtoList;
	}
	
	/**
	 * 类别增加　业务分析
	 * 传入参数：　1>当前节点的id , 2>需要增加的节点的名称，是否是父节点(肯定是false,新增的节点无子节点)　
	 * 数据库操作：1.(pid,name,isParent,create_by,create_time,remarks)
	 * 　　　       2.写入成功之后将ｉd为上面的pid的字段isParent设置为true.
	 *           3.返回状态　or　返回新的节点列表？？？？
	 *           4.选择返回状态success or fail，客户端收到状态后再确认是否需要请求新的节点列表数据
	 * 需要特别注意的地方：无
	 *
	 * @param id 
	 */
	@Transactional
	@Override
	public ClassificationDto addTreeNode(Integer pid,String name) {
		
		//CmsAdmin admin = (CmsAdmin) SessionUtils.getSession("currenLogintAdmin");
		CmsAdmin admin = new CmsAdmin("测试用户");
		
		CommodityClassification classification 
		      = new CommodityClassification(pid, name, "false", admin.getLoginJobnum(), new Date());
		int insertCount = classificationMapper.insert(classification);
		if(insertCount == 0) {
			//插入数据库失败
			logger.debug("增加类目失败，数据库操作失败");
			return new ClassificationDto(false,CommodityState.ADD_CLASSIFICATION_FAIL,
										"增加类目失败，数据库操作失败");
			
		}
		else {
			//输入数据库成功
			int updateCount =  classificationMapper.updateIsParentByPrimaryKey(pid,"true");
			if(updateCount == 0) {
				logger.debug("增加类目失败，数据库操作失败");
				return new ClassificationDto(false,CommodityState.ADD_CLASSIFICATION_FAIL,
						"增加类目失败，数据库操作失败");
			}
			else {
				logger.debug("增加类目成功");
				return new ClassificationDto(true,CommodityState.ADD_CLASSIFICATION_SUCCESS,
						"增加类目成功");
			}
		}
	}
	/**
	 * 类别名称修改　业务分析
	 * 传入参数：　1>需要修改节点的id , 2>修改的新名称　
	 * 数据库操作：设置ｉd为传入id的列的name为新名称
	 * 需要注意的地方：无
	 * 更改节点名称
	 * @param id
	 */
	@Override
	public ClassificationDto modifyTreeNode(Integer id,Integer pid,String name) {
		
		//根据id 和pid 查询是否存在类目
		Integer t_pid = classificationMapper.selectPidById(id);
		if(t_pid != pid) {
			//类目不存在，新建
			logger.debug("类目不存在，进行新建");
			//CmsAdmin admin = (CmsAdmin) SessionUtils.getSession("currenLogintAdmin");
			CmsAdmin admin = new CmsAdmin("测试用户");
			
			CommodityClassification classification 
			      = new CommodityClassification(pid, name, "false", admin.getLoginJobnum(), new Date());
			int insertCount = classificationMapper.insert(classification);
			if(insertCount == 0) {
				//插入数据库失败
				logger.debug("增加类目失败，数据库操作失败");
				return new ClassificationDto(false,CommodityState.ADD_CLASSIFICATION_FAIL,
											"增加类目失败，数据库操作失败");
				
			}
			else {
				if(pid == 0) {
					//根节点,不需要设置父节点的ｉsparent状态
					logger.debug("增加类目成功");
					return new ClassificationDto(true,CommodityState.ADD_CLASSIFICATION_SUCCESS,
							"增加类目成功");
				}
				//输入数据库成功
				int updateCount =  classificationMapper.updateIsParentByPrimaryKey(pid,"true");
				if(updateCount == 0) {
					logger.debug("增加类目失败，数据库操作失败");
					return new ClassificationDto(false,CommodityState.ADD_CLASSIFICATION_FAIL,
							"增加类目失败，数据库操作失败");
				}
				else {
					logger.debug("增加类目成功");
					return new ClassificationDto(true,CommodityState.ADD_CLASSIFICATION_SUCCESS,
							"增加类目成功");
				}
			}
		}
		else {
			//类目存在更新即可
			logger.debug("类目存在,进行更新");
			int updateCount =  classificationMapper.updateRename(id, name);
			if(updateCount == 0) {
				logger.debug("修改类目失败");
				return new ClassificationDto(false,CommodityState.RENAME_CLASSIFICATION_FAIL,"重命名失败，请刷新后重试");
			}	
			logger.debug("修改类目成功");
			return new ClassificationDto(true,CommodityState.RENAME_CLASSIFICATION_SUCCESS,"重命名成功");
		}
		
		
	}
	/**
	 * 类别删除　业务分析
	 * 传入参数：　1>删除节点的id
	 * 需要注意的地方：1.删除的节点有子节点怎么处理
	 * 			　　　　2.删除的节点及其子节点有商品怎么处理
	 * 思路：1.必须保证当前节点无子节点,通过字段isParent确认
	 * 　　　　　2.必须保证当前节点无商品关联，通过查询商品分类关联表进行确认
	 * 　　　　　3.删除后需要确认父节点是否还存在子节点，如果不存在，则设置isParent为false
	 * 数据库操作：１.删除当前节点
	 * 删除节点
	 * @param id
	 */
	@Override
	public ClassificationDto deleteTreeNode(Integer id) {
		//通过ｉd 查询pid
		Integer pid = classificationMapper.selectPidById(id);
				
		if(pid == null) {
			logger.debug("pid is null");
		}
		logger.debug("id = {},pid = {}",id,pid);
		//检查是否存在子节点
		Integer isParentCount = classificationMapper.selectIsHaveChild(id);
		if(isParentCount > 0) {
			logger.debug("无法删除，该节点存在子节点！");
			return new ClassificationDto(true,CommodityState.DELETE_CLASSIFICATION_FAIL,"无法删除，该节点存在子节点！");
		}
		logger.debug("id = {},不存在子节点",id);
		//检查是否存在关联的商品
		Integer selectCount = associationMapper.selectCountById(id);
		if(selectCount != 0) {
			logger.debug("无法删除，该节点存在关联的商品！");
			return new ClassificationDto(true,CommodityState.DELETE_CLASSIFICATION_FAIL,"无法删除，该节点存在关联的商品！");
		}
		//删除数据
		Integer deleteCount =  classificationMapper.deleteByPrimaryKey(id);
		if(deleteCount != 1) {
			logger.debug("删除失败，请刷新重试！");
			//更新pid节点 的　isparent　状态
			isParentCount = classificationMapper.selectIsHaveChild(pid);
		    if(isParentCount == 0) {
		    	//不存在子节点
		    	//更新pid节点 的　isparent　状态
				classificationMapper.updateIsParentNotParent(pid, "false");
		    }
			return new ClassificationDto(true,CommodityState.DELETE_CLASSIFICATION_FAIL,"删除失败，请刷新重试！");
		}
		logger.debug("删除成功，修改父节点isparent状态");
		
	    isParentCount = classificationMapper.selectIsHaveChild(pid);
	    if(isParentCount == 0) {
	    	//不存在子节点
	    	//更新pid节点 的　isparent　状态
			classificationMapper.updateIsParentNotParent(pid, "false");
	    }		
		return new ClassificationDto(true,CommodityState.DELETE_CLASSIFICATION_SUCCESS,"删除节点成功");
	}	
}
