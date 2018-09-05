package com.lanmei.os.controller.user;

import com.alibaba.fastjson.JSON;
import com.lanmei.common.UserStatus;
import com.lanmei.os.common.rsa.RSAKeyFactory;
import com.lanmei.os.common.rsa.RSAUtilNew;
import com.lanmei.user.dao.model.OsUser;
import com.lanmei.user.impl.UserServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
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

import java.math.BigInteger;
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
@Api(value="/user",description="处理用户登录，注册请求Controller")
@Controller
@RequestMapping("/user-register")
public class UserRegisterController {
	
	private int loginCount = 0;

	 
	private final static Logger logger = LoggerFactory.getLogger("UserRegisterController.class");	
	{
		logger.debug("UserRegisterController Created Bean............. ");
	}
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	/**
	 * 数据库访问测试
	 * @return
	 */
	@ApiOperation(value="/user-register/info",notes="数据库访问测试",httpMethod="GET")
	@RequestMapping(path="/info")
	public String  getUserInfoById(){
		logger.debug("into /user-register/info");
		OsUser user = userServiceImpl.getById(3L);
		System.out.println("PhoneNum = " + user.getPhoneNum());
		return "/user/register";
	}
	/**
	 * 校验注册的手机是否已经注册
	 * 返回  UserStatus 状态
	 * @return
	 */
	@ApiOperation(value="/user-register/checkPhoneNum",notes="注册时校验手机号是否已经注册")
	@ResponseBody
	@RequestMapping(path="/checkphonenum",method=RequestMethod.POST)
	public JSONObject checkPhoneNum(@RequestBody OsUser user) {
		UserStatus phoneCheckStatus = null;//
		
		logger.debug("into /user-register/checkPhoneNum");
		
		phoneCheckStatus = userServiceImpl.checkPhoneNum(user.getPhoneNum());
			
		
		Map<String,Object> map = new HashMap<String,Object>();		
		map.put("phoneCheckStatus", phoneCheckStatus);
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	/**
	 * 客户端发送手机号、服务端随机生成6位手机验证码
	 * 将验证码发送给手机
	 * 并通过Ajax返回状态：已经发送验证码
	 */
	@ResponseBody
	@ApiOperation(value="/user-register/getPhoneValidateNum",notes="客户端发送json请求手机验证码，生成并返回验证码")
	@RequestMapping(value="/get-phone-validate-code",method=RequestMethod.POST)
	public JSONObject getPhoneValidateNum(@RequestBody OsUser user) {
		logger.debug("into /user-register/get-phone-validate-code"); 
		
		String  phoneValidateCode =String.valueOf(userServiceImpl.getRandom());
		//phoneValidateCode = "123456";
		logger.debug("PhoneNum = " + user.getPhoneNum()  + "  phoneValidateCode = " + phoneValidateCode); 
		
		Subject currentUser = SecurityUtils.getSubject();		
		Session session = currentUser.getSession();
		session.setAttribute("PhoneNum", user.getPhoneNum());
		session.setAttribute("phoneValidateCode", phoneValidateCode);
		//userServiceImpl.sendMsg(user.getPhoneNum(), String.valueOf(phoneValidateCode));
		
		//返回手机验证码
		Map<String,Object> map = new HashMap<String,Object>();		
		map.put("phoneValidateCode",phoneValidateCode);
		JSONObject json = JSONObject.fromObject(map);
		
		return json;
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
	@RequestMapping(value="/register-submit",method=RequestMethod.POST)
	public JSONObject register(@RequestBody Map<String, Object> models) {		
		logger.debug("INTO /user-register/register-submit");
		
		//OsUser user= JSON.toJSONString(OsUser,OsUser.class);
		/*接受客户端发来的数据*/
		/*获取电话号码和密码(使用RSA进行加密)*/
		OsUser user= JSON.parseObject(JSON.toJSONString(models),OsUser.class);
		/*获取验证码*/
		String phoneNumValidate = (String)models.get("phoneNumValidate");
		
		logger.debug("PhoneNum = " + user.getPhoneNum() 
					 + "\r\n  password = " + user.getLoginPassword() 
					 + "\r\n  phoneNumValidate  = " + phoneNumValidate) ; 
		
		/*获取Modulus和Exponent 保存在session中*/
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		KeyPair key = (KeyPair)session.getAttribute("KeyPair");
		RSAPrivateKey privateKey = (RSAPrivateKey) key.getPrivate();
		
	
		logger.debug("通过 privateKeyModulus  和 privateKeyExponent 获取私钥");
		logger.debug(" 获取私钥为 = " + privateKey );
	    logger.debug(" 私钥  privateKeyModulus = " + privateKey.getModulus() );
		logger.debug(" 私钥  privateKeyExponent = " + privateKey.getPrivateExponent());
		byte[] en_result = new BigInteger(user.getLoginPassword(), 16).toByteArray();
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

		/*对原始密码进行MD5加密*/
		//生成盐（部分，需要存入数据库中）
		String random=new SecureRandomNumberGenerator().nextBytes().toHex();	
		//将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
		String resultPassword = new Md5Hash(passWord,random,3).toString();
		logger.debug("进行MD5加密的密码 = " + resultPassword);
		
		/*将手机/盐/密码/注册时间保存到数据库中*/
		user.setLoginPassword(resultPassword);
		user.setSalt(random);
		user.setRegisterTime(new Date());
		userServiceImpl.register(user);
		
		UserStatus registerStatus = UserStatus.REGISTER_SUCCESS;
		
		Map<String,Object> map = new HashMap<String,Object>();		
		map.put("registerStatus", registerStatus);
		JSONObject json = JSONObject.fromObject(map);
		
		return json;
	}
	/**
	 * 进入注册页面
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register() {
		logger.debug("INTO /user/register");
		
		
		
		/*将私钥的Modulus和Exponent 保存在session中*/
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		/*将公钥的Modulus和Exponent 发送给客户端*/
		
		KeyPair key = RSAKeyFactory.getInstance().getKeyPair();
		session.setAttribute("KeyPair",key);
		
		RSAPublicKey pkey = (RSAPublicKey) key.getPublic();
		String modulus = pkey.getModulus().toString(16);
		String exponent = pkey.getPublicExponent().toString(16);		
		ModelAndView mv = new ModelAndView("/user/register");
		mv.addObject("modulus", modulus);
		mv.addObject("exponent", exponent);	
		
		return mv;
	}
	
}
