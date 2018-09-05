package com.lanmei.commodity.serviceImpl;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.lanmei.admin.dao.model.CmsAdmin;
import com.lanmei.commodity.dao.mapper.*;
import com.lanmei.commodity.dao.model.*;
import com.lanmei.commodity.dto.EditDto;
import com.lanmei.commodity.dto.ImgResultDto;
import com.lanmei.commodity.service.CommodityEditService;
import com.lanmei.commodity.utils.TreeUtils;
import com.lanmei.common.baseservice.BaseService;
import com.lanmei.common.enums.CommodityState;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommodityEditServiceImpl extends BaseService implements CommodityEditService {
	
	private static final Logger logger = LoggerFactory.getLogger("AddCommodityServiceImpl.class");
	
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityClassificationMapper classificationMapper;
	@Autowired
	CommodityClassificationAssociationMapper associationMapper;
	@Autowired
	CommodityImageMapper commodityImageMapper;
	@Autowired
	CommoditySkuMapper commoditySkuMapper;
	@Autowired
	CommodityAttrMapper commodityAttrMapper;
	
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
				CommodityImage commodityImage = new CommodityImage(commodityId, imgUploadRelativePath,
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
	 * 商品详情编辑页
	 * wangEditor编辑器图片上传处理
	 * 图片上传处理
	 */
	@Override
	public ImgResultDto upLoadEditorImg(List<MultipartFile> list,
										String UploadRelativePath,
										File file) {
		String imgUploadRelativePath = UploadRelativePath;
		logger.debug("files.length = " + list.size() );
		ImgResultDto imgResultDto = new ImgResultDto();
		String[] urlData = new String[5];
		int index = 0;
		try {
			for(MultipartFile img : list) {
				String fileName = img.getOriginalFilename();
				
				logger.debug("file  name = " + fileName);
				//生成动态的文件名
				String finalFileName =	
						(new Date().getTime()) + Math.round(Math.random() * 1000)  //文件名动态部分，时间　＋　随机数
						+ fileName;	//文件名　原始文件名 
				//创建文件
				File imgFile = new File(file,finalFileName);
				
				//保存文件
				img.transferTo(imgFile);
				logger.debug("上传图片成功");
				//需要将保存图片的路径返回给客户端
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
	
	/**
	 * 获取sku的属性信息
	 * 1.通过商品id commodityId 获取所有的　sku_collect_id　，每一个sku_collect_id代表一个品类，包含价格和库存
	 * 2.通过　sku_collect_id　获取　commodity_sku　的信息，包含名称和属性
	 * 3.通
	 * 
	 */
	public void getSkuInfo(Integer commodityId) {
		
	}
	
	/**
	 * 设置销售属性
	 * @param map
	 */
	@Override
	public CommodityState setSkuAttr(Map<String,Object> map) {
		
		CommoditySku  commoditySku = JSON.parseObject(JSON.toJSONString(map),CommoditySku.class);
		Integer insertCount = commoditySkuMapper.insert(commoditySku);
		if(insertCount == 0) {
			logger.debug("插入销售：{}-{}属性失败",commoditySku.getName(),commoditySku.getAttr());
			return CommodityState.SET_SKU_ATTR_FAIL;
		}
		else {
			logger.debug("插入销售：{}-{}属性成功",commoditySku.getName(),commoditySku.getAttr());
			return CommodityState.SET_SKU_ATTR_SUCCESS;
		}
		
	}
	
	/**
	 * 获取销售属性
	 * @param map
	 */
	@Override
	public List<CommoditySku> getSkuAttr(Integer commodityId){
		
		List<CommoditySku> commoditySku = commoditySkuMapper.selectByCommodityId(commodityId);
		if(commoditySku  == null) {
			logger.debug("获取销售属性失败，不存在数据");
		}
		else {
			logger.debug("获取销售属性成功");
		}
		return commoditySku;
	}
	/**
	 * 获取销售属性
	 * @param map
	 */
	@Override
	public CommodityState deleteSkuAttr( Integer skuId){
		
		Integer deleteCount = commoditySkuMapper.deleteByPrimaryKey(skuId);
		if(deleteCount  == 0) {
			logger.debug("删除销售属性失败，不存在数据");
			return CommodityState.DELETE_SKU_ATTR_FAIL;
		}
		else {
			logger.debug("删除销售属性成功");
			return CommodityState.DELETE_SKU_ATTR_SUCCESS;
		}
		
	}
	
	/**
	 * 新建商品属性
	 * @param map 
	 * @return
	 */
	@Override
	public EditDto newAttribution(Map<String,Object> map) {
		
		CommodityAttr commodityAttr = JSON.parseObject(JSON.toJSONString(map),CommodityAttr.class);
		Integer insertCount = commodityAttrMapper.insert(commodityAttr);
		if(insertCount == 0) {
			logger.debug("新建商品属性失败！");
			return	new EditDto(false,"新建商品属性失败！");
		}
		else {
			logger.debug("新建商品属性成功！");
			return	new EditDto(true,"新建商品属性成功！");
		}
	}
	/**
	 * 编辑商品属性
	 * @param Integer　商品ＩＤ
	 * @return
	 */
	@Override
	public EditDto editAttribution(Map<String,Object> map) {
		CommodityAttr  commodityAttr = JSON.parseObject(JSON.toJSONString(map),CommodityAttr.class);
		Integer updateCount = commodityAttrMapper.updateByPrimaryKey(commodityAttr);
		if(updateCount == 0) {
			logger.debug("编辑商品属性失败！");
			return	new EditDto(false,"编辑商品属性失败！");
		}
		else {
			logger.debug("编辑商品属性成功！");
			return	new EditDto(true,"编辑商品属性成功！");
		}
	}
	/**
	 * 删除商品属性
	 * @param map 
	 * @return
	 */
	@Override
	public EditDto deleteAttribution(Map<String,Object> map) {
		
		Integer attrId = (Integer)map.get("attrId");
		Integer updateCount = commodityAttrMapper.deleteByPrimaryKey(attrId);
		if(updateCount == 0) {
			logger.debug("删除商品属性失败！");
			return	new EditDto(false,"删除商品属性失败！");
		}
		else {
			logger.debug("删除商品属性成功！");
			return	new EditDto(true,"删除商品属性成功！");
		}
	}
	/**
	 * 获取商品属性
	 * @param Integer　商品ＩＤ
	 * @return
	 */
	@Override
	public EditDto getAttribution(Integer commodityId) {
		List<CommodityAttr> commodityAttr  = commodityAttrMapper.selectByCommodityId(commodityId);
		if(commodityAttr == null) {
			logger.debug("获取商品属性失败！");
			return	new EditDto(false,"获取商品属性失败！");
		}
		else {
			logger.debug("获取商品属性成功！");
			return	new EditDto<List>(true,commodityAttr,"获取商品属性成功！");
		}
	}
	
	/**
	 * 设置商品描述
	 * @param map 
	 * @return
	 */
	public EditDto setDescription(Map<String,Object> map) {
		
		Integer commodityId = (Integer)map.get("commodityId");
		String description = (String)map.get("descriptionInfo");
		logger.debug("commodityId = {},description = {}",commodityId,description);
		Integer updateConut = commodityMapper.updateDescByPrimaryKey(commodityId,description);
		if(updateConut == 0) {
			return	new EditDto(false,"更新商品描述失败，该商品未存在，请刷新重试！");
		}
		else {
			return	new EditDto(true,"更新商品描述成功！");
		}
	}
	/**
	 * 获取商品描述
	 * @param Integer　商品ＩＤ
	 * @return
	 */
	public EditDto getDescription(Integer commodityId) {
	
		logger.debug("commodityId = {}",commodityId);
		String description = commodityMapper.selectDescByPrimaryKey(commodityId);
		if(description != null) {
			return	new EditDto(true,description,"获取商品描述成功！");
		}
		else {
			return	new EditDto(true,"获取商品描述失败，请刷新重试！");
		}
	}
	
	
	
}
