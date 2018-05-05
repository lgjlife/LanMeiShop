package org.lanmei.cms.controller.seckill;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.service.SeckillService;
import org.lanmei.seckill.state.SeckillState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 秒杀模块Controller
 * @author lgj
 *
 */
@Controller
@RequestMapping(path="/seckill")
public class SeckillController {

	@Autowired
	SeckillService seckillService;
	
	private final static Logger logger = LoggerFactory.getLogger("seckillController.class");	
	{
		logger.debug("seckillController Create Bean............. ");
	}
	
	@RequestMapping(path="/test/object")
	public void testObject(@RequestBody SoftInfo SoftInfo) {
		logger.debug("SeckillControllerTest  INTO /seckill/test ");
		System.out.println("id = " + SoftInfo.getId());
	}
	@RequestMapping(path="/test/map")
	public JSONObject testMap(@RequestBody Map<String, Object> requestJsonDataMap) {
		logger.debug("SeckillControllerTest  INTO /seckill/test ");
		System.out.println("id = " + requestJsonDataMap.get("id")
						+ "   name = " + requestJsonDataMap.get("name") );
		
		Map<String,Object> map = new HashMap<String,Object>();   
        map.put("id", "789");
        map.put("name", "guanyu");
        
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject;
	}
	
	/**
	 * 删除秒杀活动
	 * @param requestJsonDataMap
	 * @return
	 */
	@RequestMapping(path="/delete/seckill")
	public JSONObject deleteSeckill(@RequestBody Map<String, Object> requestJsonDataMap) {
		logger.debug("SeckillControllerTest  INTO /seckill/delete/seckill ");
		System.out.println("id = " + requestJsonDataMap.get("seckillId"));
		
		int seckillId = (int)requestJsonDataMap.get("seckillId");
		SeckillState deleteState = seckillService.deleteSeckill(seckillId);
		
		Map<String,Object> map = new HashMap<String,Object>();   
        map.put("deleteState", deleteState);
        
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject;
	}
	/**
	 * 新增秒杀活动
	 * @param requestJsonDataMap
	 */
	
	@RequestMapping(path="/new/seckill",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public  JSONObject newSeckill(@RequestBody Map<String, Object> requestJsonDataMap) {
		logger.debug("into /seckill/new/seckill ");
		
		System.out.println("seckillName = " + requestJsonDataMap.get("seckillName"));
		System.out.println("stockCount = " + requestJsonDataMap.get("stockCount"));
		//获取客户端返回的数据		
		 String seckillName =  (String)requestJsonDataMap.get("seckillName") ;
		 Integer stockCount = Integer.valueOf((String)requestJsonDataMap.get("stockCount") );
		
		System.out.println("seckillName = " + seckillName);
		System.out.println("stockCount = " + stockCount);
		
		logger.debug("get the time....");
		Date startTime = new Date();
		startTime.setTime((Long)requestJsonDataMap.get("startTime"));
		logger.debug("startTime = " + startTime);
		Date finishTime = new Date();
		finishTime.setTime((Long)requestJsonDataMap.get("finishTime"));
		logger.debug("finishTime = " + finishTime);
		/*写入数据库*/
		Seckill seckill = new Seckill(seckillName,stockCount,new Date(),startTime,finishTime);
		SeckillState seckillState =  seckillService.createSeckill(seckill);
	
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("seckillState", seckillState);
		JSONObject  retJson = JSONObject.fromObject(retMap);
		System.out.println("retJson = "  + retJson);
		return retJson;
	}
	
	/**
	 * 获取已经结束的秒杀列表
	 * @return
	 */
	@RequestMapping(path="/get/finish/list",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public JSONArray getSeckillListFinish() {
		logger.debug("into /seckill//get/finish/list");
		List<Seckill> seckill = seckillService.getSeckilledList();
		JSONArray retJson = JSONArray.fromObject(seckill);
		return retJson;
	}
	/**
	 * 获取未结束的秒杀列表
	 * @return
	 */
	@RequestMapping(path="/get/unfinish/list",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public JSONArray getSeckillListUnFinish() {
		logger.debug("into /seckill//get/unfinish/list");
		List<Seckill> seckill = seckillService.getSeckillingList();
		JSONArray retJson = JSONArray.fromObject(seckill);
		return retJson;
	}
	
}
