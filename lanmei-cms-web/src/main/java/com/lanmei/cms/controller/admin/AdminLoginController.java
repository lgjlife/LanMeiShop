package com.lanmei.cms.controller.admin;

import com.lanmei.admin.dao.model.CmsAdmin;
import com.lanmei.admin.dao.model.CmsAdminLogin;
import com.lanmei.admin.service.CmsAdminLoginService;
import com.lanmei.admin.service.CmsAdminService;
import com.lanmei.cms.common.ServletUtils.ServletUtils;
import com.lanmei.cms.common.rsa.RSAKeyFactory;
import com.lanmei.cms.common.rsa.RSAUtilNew;
import com.lanmei.common.session.SessionUtils;
import com.lanmei.enumDefine.AdminStatus;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理用户登录，注册请求Controller
 * @author lgj
 * @date:2018/05/17
 */
@Api(value="/login",description="处理用户登录Controller")
@Controller
@RequestMapping("/login")
public class AdminLoginController {

	
	
	private final static Logger logger = LoggerFactory.getLogger("AdminLoginController.class");	
	{
		logger.debug("AdminLoginController Created Bean............. ");
	}
	
	
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	CmsAdminService adminService;
	
	@Autowired
	CmsAdminLoginService adminLoginService;
	
	/**
	 * 进入登录页面
	 * @return
	 */
	@ApiOperation(value="/",notes="进入登录页面",httpMethod="GET")
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView login(){
		logger.debug("into /login  get");
		ModelAndView mv = new ModelAndView("/admin/login");
		
		KeyPair keypair = RSAKeyFactory.getInstance().getKeyPair();
		SessionUtils.setSession("keypair", keypair);
		
		RSAPublicKey pkey = (RSAPublicKey) keypair.getPublic();
		String modulus = pkey.getModulus().toString(16);
		String exponent = pkey.getPublicExponent().toString(16);		
		mv.addObject("modulus", modulus);
		mv.addObject("exponent", exponent);		
		
		return mv;
	}
	/**
	 * 提交登录
	 * @return
	 */

	@ResponseBody
	@RequestMapping(path="/in",method=RequestMethod.POST)
	public JSONObject login(@RequestBody Map<String, Object> requestJsonMap){
		logger.debug("into /login/in  post");
		
		Map<String,Object> retmap = new HashMap<String,Object>();
		
		if(requestJsonMap ==  null) {
			logger.debug("requestJsonMap ==  null");
		}
		logger.debug("从map中获取数据");
		String loginInvitationCode = (String)requestJsonMap.get("loginInvitationCode") ;
		logger.debug("从map中获取数据1");
		String loginJobNum = (String)requestJsonMap.get("loginJobNum") ;
		String loginPassword = (String)requestJsonMap.get("loginPassword");
		String logginValidateCode = (String)requestJsonMap.get("loginValidateCode");
		logger.debug("loginJobNum = " + loginJobNum 
				+ "  loginInvitationCode = " + loginInvitationCode 
				+ "  loginPassword = " + loginPassword 
				+ "  logginValidateCode = " + logginValidateCode );
		String validateCode = (String)SessionUtils.getSession("validateCode");
		
		/*对比验证码*/
		logger.debug("之前保存的验证码 = " + validateCode);
		logger.debug("用户提交的验证码 = " + logginValidateCode);
		/*if(!validateCode.equals(logginValidateCode)) {
			logger.debug("验证码有误");
			
			retmap.put("loginStatus", AdminStatus.VALIDATE_CODE_ERR);
			JSONObject json = JSONObject.fromObject(retmap);	
			
			return json;
		}*/
		
		/*获取私钥*/
		KeyPair keypair  =(KeyPair)SessionUtils.getSession("keypair");
		RSAPrivateKey privateKey = (RSAPrivateKey) keypair.getPrivate();
		/*解密*/
		//byte[] en_result = new BigInteger(loginPassword, 16).toByteArray();
		byte[] en_result  = RSAUtilNew.hexStringToBytes(loginPassword);//解决Bad arguments问题
		byte[] pass = null;
		try {			
			 pass =RSAUtilNew.decrypt(privateKey,en_result);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String  passStr = new String(pass);
		StringBuffer StrBuf = new StringBuffer();
		StrBuf.append(passStr);
		String passWord = StrBuf.reverse().toString();
		logger.debug("解密的密码为 = " + passWord);	
		
		
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginJobNum,passWord);
		System.out.println("认证状态 = " + currentUser.isAuthenticated());
		try {
			
			token.setRememberMe(true);
			/*登录验证*/
			currentUser.login(token);
			logger.debug("用户登录成功");
			
			
			CmsAdmin admin = adminService.getAdminByJobnum(loginJobNum);
			logger.debug("admin = " + admin.getLoginJobnum());
			if(admin != null) {
				logger.debug("setSession");
				SessionUtils.setSession("currenLogintAdmin", admin);
				logger.debug("after setSession");
				/*更新登录日志*/
				CmsAdminLogin adminLogin = new CmsAdminLogin();
				logger.debug("after adminLogin");
				adminLogin.setLoginIp(ServletUtils.getAddrIP(request));
				logger.debug("after setLoginIp");
				adminLogin.setLoginTime(new Date());
				logger.debug("after setLoginTime");
				adminLogin.setExplorer(ServletUtils.getAggent(request));
				logger.debug("after setExplorer");
				adminLogin.setAdminId(admin.getAdminId());
				/*写入数据库*/
				logger.debug("save the  adminLogin");
				System.out.println(adminLogin.toString());
				adminLoginService.addAdminLoginLog(admin.getAdminId(), adminLogin);
			}
			else {
				logger.debug("admin is null");
			}
		}catch(UnknownAccountException uae){  
            System.out.println("对用户[" + loginJobNum + "]进行登录验证..验证未通过,未知账户1"); 
            retmap.put("loginStatus", AdminStatus.UNKNOWN_ACCOUNT);
    		JSONObject json = JSONObject.fromObject(retmap);		
    		return json;	
         
        }catch(IncorrectCredentialsException ice){  
            System.out.println("对用户[" + loginJobNum + "]进行登录验证..验证未通过,错误的凭证2");  
            retmap.put("loginStatus", AdminStatus.LOGIN_FAIL);
    		JSONObject json = JSONObject.fromObject(retmap);		
    		return json;	
          
        }catch(LockedAccountException lae){  
            System.out.println("对用户[" + loginJobNum + "]进行登录验证..验证未通过,账户已锁定3");  
            retmap.put("loginStatus", AdminStatus.LOGIN_FAIL);
    		JSONObject json = JSONObject.fromObject(retmap);		
    		return json;	
             
        }catch(ExcessiveAttemptsException eae){  
            System.out.println("对用户[" + loginJobNum + "]进行登录验证..验证未通过,错误次数过多4");  
            retmap.put("loginStatus", AdminStatus.PASSWORD_ERROR_TOO_MANY);
    		JSONObject json = JSONObject.fromObject(retmap);		
    		return json;	
             
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + loginJobNum + "]进行登录验证..验证未通过,堆栈轨迹如下5");  
            ae.printStackTrace();  
            retmap.put("loginStatus", AdminStatus.LOGIN_FAIL);
    		JSONObject json = JSONObject.fromObject(retmap);		
    		return json;	
        } 
		System.out.println("认证状态 = " + currentUser.isAuthenticated());
		if(currentUser.isAuthenticated()) {
			System.out.println("用户[" + loginJobNum + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)"); 
		}
		/*运行到这里说明登陆成功*/
		retmap.put("loginStatus", AdminStatus.LOGIN_SUCCESS);
		JSONObject json = JSONObject.fromObject(retmap);		
		return json;	
		
	}
	
	@RequestMapping(path="/out",method=RequestMethod.GET)
	public String logout(){
		Subject currentUser = SecurityUtils.getSubject();
		
		try {
			currentUser.logout();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "/admin/login";
	}
	
}

