package com.lanmei.cms.controller.admin;

import com.alibaba.fastjson.JSON;
import com.lanmei.admin.dao.model.CmsAdmin;
import com.lanmei.admin.impl.CmsAdminServiceImpl;
import com.lanmei.cms.email.UserMailSender;
import com.lanmei.enumDefine.AdminStatus;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RequestMapping("/admin")
public class AdminManagerController {

	private final static Logger logger = LoggerFactory.getLogger("AdminManagerController.class");	
	{
		logger.debug("AdminManagerController Create Bean............. ");
		
	}
	@Autowired
	CmsAdminServiceImpl adminService;

	@Autowired
	UserMailSender sender;
	
	@RequestMapping(path="/get/adminlist",produces="text/html;charset=UTF-8")
	public @ResponseBody String  getAdminList(@RequestBody Map<String, Object> requestJsonDataMap) {
		
		logger.debug("into /admin/get/adminlist");
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		logger.debug("page = " + requestJsonDataMap.get("page"));
		String strPage = (String)requestJsonDataMap.get("page");
		
		Integer page = Integer.valueOf(strPage) ;
		logger.debug("page = " + page);
		List<CmsAdmin>  adminList = adminService.getAllAdmin(page);
		 
		Integer allListCount = adminService.getAdminCount();

		JSONObject json = new JSONObject();
		json.put("allListCount", allListCount);
		System.out.println("json = " + json.toString());
		//json.putAll(JSONObject.fromObject(adminList));
		JSONArray json1 = JSONArray.fromObject(adminList);
		System.out.println(json1.toString());
		json1.add(json);	
		System.out.println(json1.toString());
	
		return json1.toString();
	}
	/**
	 * 处理新增管理员请求
	 * 1.向数据库中写入数据
	 * 2.通过邮箱发送激活邮件
	 * 3.返回状态
	 * @param requestJsonDataMap -> jsonData={"actualName":"","jobNum":"","invitationCode":"","adminPassword":"","email":""};
	 * @return jsonData{"adminCreateStatus":""} 
	 */
	@ResponseBody
	@RequestMapping(path="/newadmin",method=RequestMethod.POST)
	public JSONObject checkPhoneNum(@RequestBody Map<String, Object> requestJsonDataMap) {
		logger.debug("into /admin/newadmin");
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		CmsAdmin admin = null;
	    
	    logger.debug("admin get");
		try {
			admin = (CmsAdmin)JSON.parseObject(JSON.toJSONString(requestJsonDataMap),CmsAdmin.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(admin == null) {
			logger.debug("admin = null");
		}
		logger.debug("actualName = " + admin.getActualName()
					+ "   loginJobnum = " + admin.getLoginJobnum()
					+ "   invitationCode = " + admin.getInvitationCode()
					+ "   adminPassword = " + admin.getAdminPassword()
					+ "   email = " + admin.getEmail());
		
		//生成盐（部分，需要存入数据库中）
		String random=new SecureRandomNumberGenerator().nextBytes().toHex();	
		//将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
		String resultPassword = new Md5Hash( admin.getAdminPassword(),random,3).toString();
		/*写入数据库*/
		admin.setPasswordSalt(random);
		admin.setAdminPassword(resultPassword);
		admin.setState(AdminStatus.ADMIN_ACCOUNT_UNACTIVATED.toString());
		admin.setGenerateTime(new Date());
		AdminStatus addAdminStatus = adminService.addAdmin(admin);
        
		/*发送邮件*/
		SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(admin.getEmail());//收件人邮箱地址
        mail.setFrom("lanmeishop1@sina.com");//收件人
        mail.setSubject("蓝莓商城后台管理员帐号激活");//主题
        String text = "这是来自蓝莓商城的邮件。" 
        		+ "欢迎注册成为蓝莓商城后台管理员，您的注册邀请码为：" + admin.getInvitationCode()
        		+ ",点击链接进行注册激活："
        		+ "localhost:8080/lanmei-os/login"
        		+ "请使用工号进行登录激活";  
            
        mail.setText(text );//正文        
        try {
        	logger.debug("开始发送邮件");
			sender.send(mail);
			logger.debug("邮件发送成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("邮件发送失败，失败原因：" + e.getMessage());
			returnMap.put("adminCreateStatus", "EMAIL_SENDFAIL");
			JSONObject json = JSONObject.fromObject(returnMap);			
			return json;
			
		}		
        returnMap.put("adminCreateStatus", addAdminStatus);
		JSONObject json = JSONObject.fromObject(returnMap);	
			
		return json;
	}
}
