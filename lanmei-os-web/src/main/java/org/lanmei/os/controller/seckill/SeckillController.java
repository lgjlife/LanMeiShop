package org.lanmei.os.controller.seckill;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lanmei.common.session.SessionUtils;
import org.lanmei.os.controller.seckill.dto.SeckillResult;
import org.lanmei.os.controller.user.UserLoginController;
import org.lanmei.seckill.dao.model.Seckill;
import org.lanmei.seckill.dto.ExecutionDto;
import org.lanmei.seckill.dto.ExposerDto;
import org.lanmei.seckill.exception.InnerException;
import org.lanmei.seckill.exception.RepeatkillException;
import org.lanmei.seckill.exception.SeckillCloseException;
import org.lanmei.seckill.exception.SeckillException;
import org.lanmei.seckill.service.SeckillService;
import org.lanmei.seckill.state.SeckillState;
import org.lanmei.user.dao.model.OsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;


@Controller
@RequestMapping(path="/seckill")
public class SeckillController {
	

	
	private final static Logger logger = LoggerFactory.getLogger(UserLoginController.class);	
	{
		logger.debug("Create Bean seckillController.....");
	}
	
	@Autowired
	SeckillService  seckillService;
	
	/**
	 * 获取秒杀列表
	 * 返回的数据：当前登录的用户ID,用户名称，秒杀ID，秒杀名称，开始时间(ms)，停止时间。
	 * 返回的是还没结束的秒杀
	 * @return
	 */
	@RequestMapping(path="/list",
			        method=RequestMethod.GET)
	public ModelAndView getList() {
		logger.debug("into /seckill/list");
		List<Seckill>  seckill = seckillService.getSeckillingList();
		ModelAndView mv = new ModelAndView("/seckill/seckill_list");
		mv.addObject("seckill", seckill);
		return mv;		
	}
	/**
	 * 根据seckillId获取seckill
	 * @param seckillId
	 * @return
	 */
	@RequestMapping(path="/{seckillId}/detail",
					method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable("seckillId") Integer seckillId) {
		
		logger.debug("into /seckill/{seckillId}/detail ");
		ModelAndView mv = new ModelAndView();
				
		if(seckillId == null) {
			mv.setViewName("forward:/seckill/seckill_list");
		}
		Seckill seckill = seckillService.getSeckill(seckillId);
		if(seckill == null) {
			mv.setViewName("forward:/seckill/seckill_list");
		}
		mv.setViewName("/seckill/seckill_detail");
		mv.addObject("seckill", seckill);
		return mv;	
	}

	@ResponseBody
	@RequestMapping(path="/get/time",
					method=RequestMethod.GET)
	public JSONObject getTime() {
		logger.debug("into seckill/get/time");
		Date now = new Date();
		String  currentTime = String.valueOf(now.getTime());
		Map<String, Object> map = new HashMap<String ,Object>();
		map.put("currentTime", currentTime);
		JSONObject retJson =  JSONObject.fromObject(map);
		logger.debug("return seckill/get/time");
		return retJson;	
	}
	
	@ResponseBody
	@RequestMapping(path="/{seckillId}/exposer",
					method=RequestMethod.GET)
	public JSONObject  getExposer(@PathVariable("seckillId") Integer seckillId) {
		logger.debug("into seckill/{seckillId}/exposer  seckillId = "  + seckillId);
		
		Map<String, Object> map = new HashMap<String ,Object>();
		ExposerDto exposer = seckillService.getExposer(seckillId);
		map.put("exposer", exposer);
		JSONObject retJson =  JSONObject.fromObject(map);
	
		return retJson;	
	}
	/**
	 * 执行秒杀
	 * @param seckillId
	 * @param md5
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path="/{seckillId}/{md5}/execute",
					method=RequestMethod.POST,
					produces="application/json;charset=UTF-8")
	public SeckillResult  executeSeckill(@PathVariable("seckillId") Integer seckillId,
								  @PathVariable("md5") String md5) {
		logger.debug("into seckill/{seckillId}//{md5}exposer  seckillId = "  + seckillId);
		
		Map<String, Object> map = new HashMap<String ,Object>();
		//获取当前的用户
		OsUser user=(OsUser) SessionUtils.getSession("currenLogintUser");
		if(user == null) {
			return new SeckillResult<ExecutionDto>(false,"请先登录！");
		}
		logger.debug("登录的用户为 ： " + user.getPhoneNum());
		try {
			ExecutionDto  executionDto = seckillService.executeSeckill(seckillId, md5,1);
				return new SeckillResult<ExecutionDto>(true,executionDto);
		} catch (SeckillCloseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExecutionDto  executionDto = new ExecutionDto(seckillId,SeckillState.SECKILL_CLOSE);
			return new SeckillResult<ExecutionDto>(true,executionDto,"秒杀已经关闭");
		} catch (RepeatkillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExecutionDto  executionDto = new ExecutionDto(seckillId,SeckillState.SECKILL_REPEAT);
			return new SeckillResult<ExecutionDto>(true,executionDto,"重复秒杀");
		} catch (SeckillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExecutionDto  executionDto = new ExecutionDto(seckillId,SeckillState.SECKILL_REQUEST_ERROOR);
			return new SeckillResult<ExecutionDto>(true,executionDto,"秒杀异常");
		}catch ( InnerException e) {
			e.printStackTrace();
			ExecutionDto  executionDto = new ExecutionDto(seckillId,SeckillState.SECKILL_INNER_ERROOR);
			System.out.println("executionDto = " + executionDto.toString());
			return new SeckillResult<ExecutionDto>(true,executionDto,"内部错误");
		}
	}
}
