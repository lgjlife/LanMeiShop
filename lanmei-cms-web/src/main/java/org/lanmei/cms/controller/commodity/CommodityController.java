package org.lanmei.cms.controller.commodity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lanmei.cms.controller.commodity.dto.CommodityResultDto;
import org.lanmei.commodity.dto.ClassificationDto;
import org.lanmei.commodity.dto.TreeNodeDto;
import org.lanmei.commodity.service.ClassificationService;
import org.lanmei.common.syslog.annotation.SyslogAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/commodity")
public class CommodityController {
  
	@Autowired
	ClassificationService classificationService;
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
	@SyslogAnno(description="通过父节点获取下一级子节点",operationName="select")
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
	@SyslogAnno(description="增加节点",operationName="update")
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
	@SyslogAnno(description="修改节点名称",operationName="update")
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
	@SyslogAnno(description="通过父节点获取下一级子节点",operationName="select")
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
	
	
}
