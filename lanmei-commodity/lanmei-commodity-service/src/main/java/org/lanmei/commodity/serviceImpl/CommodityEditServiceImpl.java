package org.lanmei.commodity.serviceImpl;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.lanmei.admin.dao.model.CmsAdmin;
import org.lanmei.commodity.dao.mapper.CommodityClassificationAssociationMapper;
import org.lanmei.commodity.dao.mapper.CommodityClassificationMapper;
import org.lanmei.commodity.dao.mapper.CommodityImageMapper;
import org.lanmei.commodity.dao.mapper.CommodityMapper;
import org.lanmei.commodity.dao.model.Commodity;
import org.lanmei.commodity.dao.model.CommodityClassification;
import org.lanmei.commodity.dao.model.CommodityImage;
import org.lanmei.commodity.dto.ImgResultDto;
import org.lanmei.commodity.service.CommodityEditService;
import org.lanmei.commodity.utils.TreeUtils;
import org.lanmei.common.baseservice.BaseService;
import org.lanmei.common.enums.CommodityState;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;

@Service
public class CommodityEditServiceImpl extends BaseService  implements CommodityEditService{
	
	private static final Logger logger = LoggerFactory.getLogger("AddCommodityServiceImpl.class");
	
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityClassificationMapper classificationMapper;
	@Autowired
	CommodityClassificationAssociationMapper  associationMapper;
	@Autowired
	CommodityImageMapper commodityImageMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	

    /**
     * 增加商品处理
     * 
     * @param map　商品的基本信息　商品的二级分类ＩＤ
     */
	@Override
	public CommodityState addCommodity(Map<String, Object> map) {
		
		//从请求中获取数据
		Commodity commodity= JSON.parseObject(JSON.toJSONString(map),Commodity.class);
		if(commodity == null) {
			logger.error("commodity is null!!!!");
		}
		else {
			logger.debug("commodity name = " + commodity.getName());
		}
		Integer categoryId = (Integer)map.get("categoryId");
		if(categoryId == null) {
			logger.error("secondid is null!!!!");
		}
		
		logger.debug("三级分类　id = " + categoryId);
	
		//获取当前登录的管理员
		//CmsAdmin admin = (CmsAdmin) SessionUtils.getSession("currenLogintAdmin");
		CmsAdmin admin = new CmsAdmin("测试用户");
		
		commodity.setCreateBy(admin.getLoginJobnum());
		commodity.setCreateTime(new Date());
		commodity.setSaleState((byte)-1);
		//向数据库写入数据
		Integer insertCount =  commodityMapper.insert(commodity);
		if(insertCount == 0) {
			//写入失败
			logger.debug(" 创建商品失败！");
			return CommodityState.ADD_COMMODITY_FAIL;
		}
		logger.debug(" 创建商品成功！");
		//写入成功
		return CommodityState.ADD_COMMODITY_SUCCESS;
		
	}
	
	/**
	 * 通过商品id删除商品
	 * @return
	 */
	@Override
	public CommodityState  deleteComodity(Integer id) {
		
		Integer deletCount = commodityMapper.deleteByPrimaryKey(id);
		if(deletCount == 0) {
			logger.debug("商品{}-删除失败！",id);
			return CommodityState.COMMODITY_DELETE_FAIL;
		}
		else {
			logger.debug("商品{}-删除成功！",id);
			return CommodityState.COMMODITY_DELETE_SUCCESS;
		}
	}
	
	@Override
	public CommodityState checkName(Map<String, Object> map) {
		String name = (String)map.get("name");
		Commodity commodity = commodityMapper.selectByName(name);
		if(commodity != null) {
			logger.debug("commodity is null");
			return CommodityState.COMMODITY_NAME_REPEAT;
		}
		return CommodityState.COMMODITY_NAME_NOT_REPEAT;
	}

	/**
	 * 图片上传处理
	 */
	@Override
	public CommodityState upLoadImg(CommonsMultipartFile[] files,
			String UploadAbsolutePath,
			String UploadRelativePath,
			int commodityId) {
		// TODO Auto-generated method stub
		//获取当前登录的管理员
		//CmsAdmin admin = (CmsAdmin) SessionUtils.getSession("currenLogintAdmin");
		CmsAdmin admin = new CmsAdmin("测试用户");
		String imgUploadAbsolutePath = UploadAbsolutePath;
		String imgUploadRelativePath = UploadRelativePath;
		logger.debug("files.length = " + files.length );
		try {
			for(int i  = 0 ;i < files.length ; i++) {
				String fileName = files[i].getOriginalFilename();
				if(fileName == "") {
					continue;
				}
				logger.debug("file  name = " + fileName);
				String finalPath = imgUploadAbsolutePath + imgUploadRelativePath;  //绝对路径　＋　相对路径
				String finalFileName =	(new Date().getTime()) + Math.round(Math.random() * 1000)  //文件名动态部分
					                + fileName;	//文件名　原始文件名        
				File newfile = new File( finalPath + finalFileName);
				logger.debug("创建文件夹　= " + newfile.mkdirs() +  "  path = " + newfile.getPath());
				logger.debug("" + newfile.getAbsolutePath());
				//保存文件
				files[i].transferTo(newfile);
				logger.debug("上传图片成功");
				//持久化到数据库
				CommodityImage commodityImage = new  CommodityImage(commodityId, imgUploadRelativePath,
						finalFileName,(byte)(0),admin.getLoginJobnum(), new Date());
				
				commodityImageMapper.insert(commodityImage);
				logger.debug("数据库写入图片成功");		
			}
			return CommodityState.ADD_COMMODITY_IMG_SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return CommodityState.ADD_COMMODITY_IMG_FAIL;
		}
		
	}	
	/**
	 * 图片上传处理
	 */
	@Override
	public ImgResultDto upLoadEditorImg(List<MultipartFile> list,
			String UploadAbsolutePath,
			String UploadRelativePath,
			int commodityId) {
		// TODO Auto-generated method stub
		//获取当前登录的管理员
		//CmsAdmin admin = (CmsAdmin) SessionUtils.getSession("currenLogintAdmin");
		CmsAdmin admin = new CmsAdmin("测试用户");
		String imgUploadAbsolutePath = UploadAbsolutePath;
		String imgUploadRelativePath = UploadRelativePath;
		logger.debug("files.length = " + list.size() );
		ImgResultDto imgResultDto = new ImgResultDto();
		String[] urlData = new String[5];
		int index = 0;
		try {
			for(MultipartFile img : list) {
				String fileName = img.getOriginalFilename();
				if(fileName == "") {
					continue;
				}
				logger.debug("file  name = " + fileName);
				String finalPath = imgUploadAbsolutePath + imgUploadRelativePath;  //绝对路径　＋　相对路径
				String finalFileName =	(new Date().getTime()) + Math.round(Math.random() * 1000)  //文件名动态部分
					                + fileName;	//文件名　原始文件名        
				File newfile = new File( finalPath + finalFileName);
				logger.debug("创建文件夹　= " + newfile.mkdirs() +  "  path = " + newfile.getPath());
				logger.debug("" + newfile.getAbsolutePath());
				//保存文件
				img.transferTo(newfile);
				logger.debug("上传图片成功");
				//持久化到数据库
				CommodityImage commodityImage = new  CommodityImage(commodityId, imgUploadRelativePath,
						finalFileName,(byte)(0),admin.getLoginJobnum(), new Date());
				
				commodityImageMapper.insert(commodityImage);
				logger.debug("数据库写入图片成功");	
				//
				urlData[index++] = "http://localhost:8080/lanmei-cms/"+imgUploadRelativePath + finalFileName;
				logger.debug("index = " + index 
						+ "  url = " + urlData[0]);
				imgResultDto.setErrno(0);
			}
			imgResultDto.setData(urlData);
			return imgResultDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return imgResultDto;
		}
		
	}	
	
	/**
	 * 通过分类id 获取商品列表
	 * @return
	 */
	public List<Commodity>  getComodityList(Integer id){
		//获取最终子节点
		//从数据库获取所以的分类数据
		List<CommodityClassification> classification = classificationMapper.selectAll();
		//获取指定ｉd　的所有最终子节点
		List<CommodityClassification> lastChildNode = new  ArrayList<CommodityClassification>();
		lastChildNode = TreeUtils.getLastChildNode(classification, lastChildNode, id);
		
		
		
		//保存到list
		List<Integer> lastNodeId = new ArrayList<Integer>();
		
		if(lastChildNode.isEmpty()) {
			logger.debug("lastChildNode is empty");
			lastNodeId.add(id);
		}
		else {
			for(CommodityClassification node : lastChildNode) {
				lastNodeId.add(node.getId());
			}
		}
		
		
		for(Integer nodeId : lastNodeId) {
			System.out.println("nodeId = " + nodeId);
		}
		
		List<Commodity> commodity = commodityMapper.selectByCategoryId(lastNodeId);
		
		return commodity;
	}
	
}
