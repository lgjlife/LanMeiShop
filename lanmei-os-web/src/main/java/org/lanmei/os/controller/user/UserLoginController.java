package org.lanmei.os.controller.user;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.lanmei.common.UserStatus;
import org.lanmei.os.common.rsa.RSAKeyFactory;
import org.lanmei.os.common.rsa.RSAUtilNew;
import org.lanmei.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import net.sf.json.JSONObject;

/**
 * 处理用户登录，注册请求Controller
 * @author lgj
 * @date:2018/05/17
 */
@Api(value="/user-login",description="处理用户登录Controller")
@Controller
@RequestMapping("/user-login")
public class UserLoginController {

	
	
	private final static Logger logger = LoggerFactory.getLogger("UserLoginController.class");	
	{
		logger.debug("UserLoginController Created Bean............. ");
	}
	
	@Autowired
	UserServiceImpl  userServiceImpl;
	
	
	@ApiOperation(value="/user-login",httpMethod="GET")
	@RequestMapping(path="/find-password")
	public String  findPassword() {
		
		logger.debug("into user-login/find-password");
		return "/user/find-password"; 
	}
	
	@ApiOperation(value="/user-login",httpMethod="GET")
	@RequestMapping(path="/test")
	public String  loginTest() {
		
		logger.debug("into user-login/user-test");
		return "/user/login"; 
	}
	/**
	 * 进入登录界面
	 * @return
	 */
	@ApiOperation(value="/user-login",httpMethod="GET")
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView  loginPage() {
		
		logger.debug("into /user-login");
		
		/*将私钥的Modulus和Exponent 保存在session中*/
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		/*将公钥的Modulus和Exponent 发送给客户端*/
		
		KeyPair key = RSAKeyFactory.getInstance().getKeyPair();
		session.setAttribute("KeyPair",key);
		
		RSAPublicKey pkey = (RSAPublicKey) key.getPublic();
		String modulus = pkey.getModulus().toString(16);
		String exponent = pkey.getPublicExponent().toString(16);		
		ModelAndView mv = new ModelAndView("/user/login");
		mv.addObject("modulus", modulus);
		mv.addObject("exponent", exponent);	
		
		return mv;
	}
	/**
	 * 客户端提交注册按钮
	 * 1.校验验证码是否正确
	 * 2.对加密的密码进行解密。获取原始密码
	 * 3.对原始密码使用MD5进行加密
	 * 3.将手机号码和加密的密码写入到数据库中，用户创建成功
	 * @return JSONObject 注册成功：UserStatus.REGISTER_SUCCESS
	 * 					  注册失败：UserStatus.REGISTER_FAIL
	 *                    验证码有误：UserStatus.VALIDATE_CODE_ERR
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public JSONObject register(@RequestBody Map<String, Object> models) {		
		logger.debug("INTO /user-login/login");
		
		Map<String,Object> map = new HashMap<String,Object>();	
		
		//OsUser user= JSON.toJSONString(OsUser,OsUser.class);
		/*接受客户端发来的数据*/
		/*获取电话号码和密码(使用RSA进行加密)*/
		String loginName = (String)models.get("loginName") ;
		String loginPassword = (String)models.get("loginPassword");
		String logginVerificationCode = (String)models.get("logginVerificationCode");
		
		
		
		/*获取Modulus和Exponent 保存在session中*/
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		/*获取RSA 的keyPair */
		KeyPair key = (KeyPair)session.getAttribute("KeyPair");
		RSAPrivateKey privateKey = (RSAPrivateKey) key.getPrivate();
		/*从session获取验证码*/
		String verificationCodeSave = (String)session.getAttribute("verificationCode");
		logger.debug("之前保存的验证码 = " + verificationCodeSave);
		logger.debug("用户提交的验证码 = " + logginVerificationCode);
		if(verificationCodeSave.equals(logginVerificationCode) == false) {
			//验证码有误
			map.put("loginStatus", UserStatus.VALIDATE_CODE_ERR);
			JSONObject json = JSONObject.fromObject(map);
			
			return json;
		} 
	    /*解密，获取原始密码*/
		logger.debug("通过 privateKeyModulus  和 privateKeyExponent 获取私钥");
		logger.debug(" 获取私钥为 = " + privateKey );
	    logger.debug(" 私钥  privateKeyModulus = " + privateKey.getModulus() );
		logger.debug(" 私钥  privateKeyExponent = " + privateKey.getPrivateExponent());
		byte[] en_result = new BigInteger(loginPassword, 16).toByteArray();
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

		UsernamePasswordToken token = new UsernamePasswordToken(loginName,passWord);
		System.out.println("认证状态 = " + currentUser.isAuthenticated());
		try {
			currentUser.login(token);
			
		}catch(UnknownAccountException uae){  
            System.out.println("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");  
         
        }catch(IncorrectCredentialsException ice){  
            System.out.println("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");  
          
        }catch(LockedAccountException lae){  
            System.out.println("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");  
             
        }catch(ExcessiveAttemptsException eae){  
            System.out.println("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");  
             
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");  
            ae.printStackTrace();  
           
        } 
		System.out.println("认证状态 = " + currentUser.isAuthenticated());
		if(currentUser.isAuthenticated()) {
			System.out.println("用户[" + loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)"); 
		}
		
		UserStatus loginStatus = UserStatus.LOGIN_SUCCESS;
		
			
		map.put("loginStatus", loginStatus);
		JSONObject json = JSONObject.fromObject(map);
		
		return json;
	}
}
