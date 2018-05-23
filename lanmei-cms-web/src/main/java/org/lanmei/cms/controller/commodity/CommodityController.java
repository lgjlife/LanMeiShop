package org.lanmei.cms.controller.commodity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lanmei.cms.controller.commodity.dto.CommodityResultDto;
import org.lanmei.commodity.dao.model.Commodity;
import org.lanmei.commodity.dao.model.CommoditySku;
import org.lanmei.commodity.dto.ClassificationDto;
import org.lanmei.commodity.dto.EditDto;
import org.lanmei.commodity.dto.ImgResultDto;
import org.lanmei.commodity.dto.TreeNodeDto;
import org.lanmei.commodity.service.ClassificationService;
import org.lanmei.commodity.service.CommodityEditService;
import org.lanmei.common.enums.CommodityState;
import org.lanmei.sysaop.syslog.anno.SyslogAnno;
import org.lanmei.sysaop.time_measurement.anno.TimeMeasurementAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping(path="/commodity")
public class CommodityController {
  
	@Autowired
	ClassificationService classificationService;
	@Autowired
	CommodityEditService   commodityEditService;
	
	@Autowired
	private  HttpServletRequest request;
	
	private static final Logger logger = LoggerFactory.getLogger("CommodityController.class");
	{
		logger.debug("CommodityController create bean");
	}
	/**
	 * 
	 * 通过父节点获取下一级的子节点
	 * @return　List<TreeNodeDto>　　
	 */
	
	@ResponseBody
	@RequestMapping(path="/get/node")
	public List<TreeNodeDto> getTreeNode() {
		
		String pid = request.getParameter("id");
		if(pid == null) {
			//为null说明请求的是跟节点
			pid = "0";
		}
		logger.debug("pid = " + pid);
		List<TreeNodeDto>  treeNodeDto  = null;
		treeNodeDto = classificationService.getTreeNode(Integer.valueOf(pid));
		return treeNodeDto;
	}
	/**
	 * 类别增加　业务分析
	 * 传入参数：　1>当前节点的id , 2>需要增加的节点的名称，是否是父节点(肯定是false,新增的节点无子节点)　
	 * 数据库操作：1.(pid,name,isParent,create_by,create_time,remarks)
	 * 　　　       2.写入成功之后将ｉd为上面的pid的字段isParent设置为true.
	 *           3.返回状态　or　返回新的节点列表？？？？
	 *           4.选择返回状态success or fail，客户端收到状态后再确认是否需要请求新的节点列表数据
	 * 需要特别注意的地方：无
	 */
	@ResponseBody
	@RequestMapping(path="/add/node")
	public CommodityResultDto addTreeNode(@RequestBody Map<String, Object> reqMap)  {
		
		Integer  id = (Integer)reqMap.get("id");
		String name = (String) reqMap.get("name");
		if(id == null) {
			CommodityResultDto commodityResultDto = new CommodityResultDto(false,"未知的请求");
			return commodityResultDto;
		}
		logger.debug("id = {},name = {}",id,name);
		ClassificationDto classificationDto = classificationService.addTreeNode(id, name);
		CommodityResultDto<ClassificationDto> commodityResultDto 
			= new CommodityResultDto<ClassificationDto>(true,classificationDto);
		return commodityResultDto;		
	}
	/**
	 * 类别名称修改　业务分析
	 * 传入参数：　1>需要修改节点的id , 2>修改的新名称　
	 * 数据库操作：设置ｉd为传入id的列的name为新名称
	 * 需要注意的地方：无
	 */
	@ResponseBody
	@RequestMapping(path="/modify/node")
	public CommodityResultDto modifyTreeNode(@RequestBody Map<String, Object> reqMap) {
		Integer  id = (Integer)reqMap.get("id");
		Integer  pid = (Integer)reqMap.get("pid");
		String name = (String) reqMap.get("name");
		if(id == null) {
			CommodityResultDto commodityResultDto = new CommodityResultDto(false,"未知的请求");
			return commodityResultDto;
		}
		logger.debug("id = {},pid = {},name = {}",id,pid,name);
		ClassificationDto classificationDto = classificationService.modifyTreeNode(id, pid,name);		
		CommodityResultDto<ClassificationDto> commodityResultDto 
			= new CommodityResultDto<ClassificationDto>(true,classificationDto);
		return commodityResultDto;
		
		
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
	 * 
	 */
	
	@ResponseBody
	@RequestMapping(path="/delete/node")
	public CommodityResultDto deleteTreeNode(@RequestBody Map<String, Object> reqMap) {
		
		Integer  id = (Integer)reqMap.get("id");
		if(id == null) {
			CommodityResultDto commodityResultDto = new CommodityResultDto(false,"未知的请求");
			return commodityResultDto;
		}
		logger.debug("id = {}",id);
		ClassificationDto classificationDto = classificationService.deleteTreeNode(id);	
		CommodityResultDto<ClassificationDto> commodityResultDto 
			= new CommodityResultDto<ClassificationDto>(true,classificationDto);
		return commodityResultDto;		
	}	
	@ResponseBody
	@RequestMapping(path="/upload/img")
	public CommodityResultDto uploadImg(@RequestParam("files") CommonsMultipartFile[] files,
			@RequestParam("commodityId")  int commodityId) {		
		String imgUploadAbsolutePath = request.getServletContext().getInitParameter("imgUploadAbsolutePath");
		String imgUploadRelativePath = request.getServletContext().getInitParameter("imgUploadRelativePath");
		
		logger.debug("commodityId = " + commodityId);
		CommodityState commodityState 
				= commodityEditService.upLoadImg(files, imgUploadAbsolutePath, 
						                        imgUploadRelativePath,commodityId);
		
		CommodityResultDto<CommodityState> commodityResultDto 
		= new CommodityResultDto<CommodityState>(true,CommodityState.ADD_COMMODITY_IMG_SUCCESS);
			return commodityResultDto;			
	}
	
	@ResponseBody
	@RequestMapping(path="/upload/editor/img")
	public ImgResultDto uploadEditorImg(@RequestParam("img") List<MultipartFile> list) {		
		String imgUploadAbsolutePath = request.getServletContext().getInitParameter("imgUploadAbsolutePath");
		String imgUploadRelativePath = request.getServletContext().getInitParameter("imgUploadRelativePath");
		
		//logger.debug("commodityId = " + commodityId);
		ImgResultDto imgResult
				= commodityEditService.upLoadEditorImg(list, imgUploadAbsolutePath, 
						                        imgUploadRelativePath,1);
			return imgResult;			
	}
	/**
	 * 
	 * 添加商品请求处理
	 * 1.写入商品表
	 * 2.写入
	 */
	
	@ResponseBody
	@RequestMapping(path="/new/commodity")
	public CommodityResultDto newCommodity(@RequestBody Map<String,Object> requestMap) {
		logger.debug("into /new/commodity");
		CommodityState commodityState = commodityEditService.addCommodity(requestMap);
		
		CommodityResultDto<CommodityState> commodityResultDto 
		= new CommodityResultDto<CommodityState>(true,commodityState);
			return commodityResultDto;	

	}
	/**
	 * 
	 * 获取商品列表请求
	 */
	//@TimeMeasurementAnno(description="获取商品列表请求",layer="Controller")
	//@SyslogAnno(description="获取商品列表请求",layer="Controller")
	@ResponseBody
	@RequestMapping(path="/get/commodity/list",method=RequestMethod.GET)
	public CommodityResultDto getCommodityList(@RequestParam("id") Integer id) {//(@RequestBody Map<String,Object> requestMap) {
		
		//Integer id = (Integer)requestMap.get("id");
		logger.debug("id = " + id);
		List<Commodity> commodity = commodityEditService.getComodityList(id);
		CommodityResultDto<List> commodityResultDto 
		= new CommodityResultDto<List>(true,commodity);
			return commodityResultDto;	

	}
	@ResponseBody        
	@RequestMapping(path="/delete/commodity",method=RequestMethod.GET)
	public CommodityResultDto deleteCommodity(@RequestParam("id") Integer id) {//(@RequestBody Map<String,Object> requestMap) {
		
		//Integer id = (Integer)requestMap.get("id");
		logger.debug("id = " + id);
		CommodityState commodityState = commodityEditService.deleteComodity(id);
		CommodityResultDto<CommodityState> commodityResultDto 
		= new CommodityResultDto<CommodityState>(true,commodityState);
			return commodityResultDto;	

	}
	

	@ResponseBody
	@RequestMapping(path="/check/name")
	public CommodityResultDto checkName(@RequestBody Map<String,Object> requestMap) {
		
		CommodityState commodityState = commodityEditService.checkName(requestMap);
		
		CommodityResultDto<CommodityState> commodityResultDto 
		= new CommodityResultDto<CommodityState>(true,commodityState);
			return commodityResultDto;	

	}

	@ResponseBody
	@RequestMapping(path="/set/content")
	public void setContent(@RequestBody Map<String,Object> requestMap) {
		logger.debug("into /set/content ");
		String content = (String)requestMap.get("content");
		System.out.println(content);

	}
	/**
	 * 编辑商品描述
	 * @param requestMap
	 */
	
	
	
	/**
	 * 编辑商品商品属性
	 * @param requestMap
	 */
	@SyslogAnno(layer="Controller",description="编辑商品属性")
	@TimeMeasurementAnno(layer="Controller",description="编辑商品属性")
	@ResponseBody
	@RequestMapping(path="/edit/attribution",method=RequestMethod.POST)
	public CommodityResultDto editAttribution(@RequestBody Map<String,Object> requestMap) {
		
		
		
		return null;

	}
	/**
	 * 获取商品描述
	 * @param requestMap
	 */
	@SyslogAnno(layer="Controller",description="获取商品属性")
	@TimeMeasurementAnno(layer="Controller",description="获取商品属性")
	@ResponseBody
	@RequestMapping(path="/get/attribution")
	public CommodityResultDto getAttribution(@RequestBody Map<String,Object> requestMap){	
		
		
		return null;
	}
	
	/**
	 * 获取商品sku属性
	 * @param requestMap
	 */
	@SyslogAnno(layer="Controller",description="获取商品sku属性")
	@TimeMeasurementAnno(layer="Controller",description="获取商品sku属性")
	@ResponseBody
	@RequestMapping(path="/get/sku/info")
	public CommodityResultDto getSkuInfo(@RequestParam("commodityId")   Integer commodityId){	
		
		if(commodityId == null) {
			return  new CommodityResultDto(false,"未知的错误请求");
		}
		
		
		return null;
	}
	
	/**
	 * 设置商品销售属性
	 * @param requestMap
	 * @return
	 */
	//@SyslogAnno(layer="Controller",description="设置商品sku属性")
	//@TimeMeasurementAnno(layer="Controller",description="设置商品sku属性")
	@ResponseBody
	@RequestMapping(path="/set/sku/attr")
	public CommodityResultDto setSkuAttr(@RequestBody Map<String, Object>  requestMap){	
		
		CommodityState commodityState = commodityEditService.setSkuAttr(requestMap);
		
		CommodityResultDto<CommodityState> commodityResultDto 
		= new CommodityResultDto<CommodityState>(true,commodityState);
	
		return commodityResultDto;	

	}
	/**
	 * 获取商品sku属性
	 * @param commodityId
	 * @return
	 */
	@ResponseBody         
	@RequestMapping(path="/get/sku/attr",method=RequestMethod.GET)
	public CommodityResultDto setSkuAttr(@RequestParam("commodityId")   Integer commodityId){	
		
		logger.debug("commodityId = " + commodityId);
		List<CommoditySku>  commoditySku = commodityEditService.getSkuAttr(commodityId);
		
		CommodityResultDto<List> commodityResultDto 
		= new CommodityResultDto<List>(true,commoditySku);
	
		return commodityResultDto;	

	}
	/**
	 * 删除商品sku属性
	 * @param skuId
	 * @return
	 */	
	@ResponseBody
	@RequestMapping(path="/delete/sku/attr",method=RequestMethod.DELETE)
	public CommodityResultDto deleteSkuAttr(@RequestBody Map<String, Object>  requestMap){	
		
		logger.debug("访问deleteSkuAttr");
		Integer skuId = (Integer)requestMap.get("skuId");
		logger.debug("skuId = "+skuId);
		CommodityState commodityState  = commodityEditService.deleteSkuAttr(skuId);
		
		CommodityResultDto<CommodityState> commodityResultDto 
		= new CommodityResultDto<CommodityState>(true,commodityState);
	
		return null;	

	}
	
	/**
	 * 更新商品描述
	 * @param skuId
	 * @return
	 */	
	@ResponseBody
	@RequestMapping(path="/set/description",method=RequestMethod.POST)
	public CommodityResultDto setDescription(@RequestBody Map<String, Object>  requestMap){	
		
		logger.debug("访问setDescription");
		
		EditDto editDto  = commodityEditService.setDescription(requestMap);
		
		CommodityResultDto<EditDto> commodityResultDto 
		= new CommodityResultDto<EditDto>(true,editDto);
	
		return commodityResultDto;	

	}
	
	/**
	 * 获取商品描述
	 * 
	 * @param skuId
	 * @return
	 */	
	@ResponseBody
	@RequestMapping(path="/get/description",method=RequestMethod.GET)
	public CommodityResultDto getDescription(@RequestParam("commodityId")   Integer commodityId){	
		
		logger.debug("访问getDescription");

		EditDto editDto  = commodityEditService.getDescription(commodityId);
		
		CommodityResultDto<EditDto> commodityResultDto 
		= new CommodityResultDto<EditDto>(true,editDto);
	
		return commodityResultDto;	

	}
}
