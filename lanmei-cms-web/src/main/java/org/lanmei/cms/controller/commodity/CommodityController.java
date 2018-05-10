package org.lanmei.cms.controller.commodity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.lanmei.commodity.dto.TreeNodeDto;
import org.lanmei.commodity.service.ClassificationService;
import org.lanmei.common.syslog.annotation.SyslogAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@SyslogAnno(description="通过父节点获取所有的子节点",operationName="select")
	@ResponseBody
	@RequestMapping(path="/get/node")//@RequestParam("id")
	public List<TreeNodeDto> getTreeNode() {
		
		String pid = request.getParameter("id");
		if(pid == null) {
			pid = "0";
		}
		logger.debug("pid = " + pid);
		List<TreeNodeDto>  treeNodeDto  = null;
		treeNodeDto = classificationService.getTreeNode(Integer.valueOf(pid));
		return treeNodeDto;
	}
	
}
