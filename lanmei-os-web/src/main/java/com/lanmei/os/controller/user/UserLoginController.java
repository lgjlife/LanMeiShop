package com.lanmei.os.controller.user;


import com.lanmei.common.code.NullPointerCode;
import com.lanmei.common.code.UserReturnCode;
import com.lanmei.common.result.BaseResult;
import com.lanmei.common.result.WebResult;
import com.lanmei.common.session.SessionUtils;
import com.lanmei.common.utils.CheckNullUtil;
import com.lanmei.common.utils.session.SessionKeyUtil;
import com.lanmei.common.utils.session.SessionUtil;
import com.lanmei.os.common.ServletUtils.ServletUtils;
import com.lanmei.os.common.regex.ProjectRegex;
import com.lanmei.os.common.rsa.RSAKeyFactory;
import com.lanmei.os.common.rsa.RSAUtilNew;
import com.lanmei.sysaop.syslog.anno.PrintUrlAnno;
import com.lanmei.user.dao.model.OsUser;
import com.lanmei.user.dao.model.OsUserLogin;
import com.lanmei.user.impl.UserServiceImpl;
import com.lanmei.user.service.OsUserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(value="/user/login",description="处理用户登录Controller")
@Controller
@RequestMapping(path="/user/login")
public class UserLoginController {

	
	
	private final static Logger logger = LoggerFactory.getLogger("UserLoginController.class");	
	{
		logger.debug("UserLoginController Created Bean............. ");
	}
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	OsUserLoginService userLoginService;
	@Autowired
	private  HttpServletRequest request;

	/** 
	 * @description:  进入登录页面
	 * @param:  
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/7/18 
	*/
	@PrintUrlAnno(description = "os-web 进入登陆页面")
	@ApiOperation(value="/user/login",httpMethod="GET",notes = "进入登陆页面")
	@GetMapping
	public String  loginPage() {
		return "/user/login";
	}

	/** 
	 * @description:  获取加密密钥公钥的modulus和exponent
	 * @param:

	 * @return: BaseResult：1. UserReturnCode.LOGIN_GET_KEYPAIR_SUCCESS or  UserReturnCode.LOGIN_GET_KEYPAIR_FAIL
	                        2. keyMap(modulus , exponent)
	 * 	 @see com.lanmei.common.result
	 * @author: Mr.lgj 
	 * @date: 9/7/18 
	*/ 
	@ApiOperation(value="/user/login/key",httpMethod="GET",notes = "os-web 登陆页面获取RSAKey的 modulus 和 exponent")
	@GetMapping(path="/key")
	@ResponseBody
	@PrintUrlAnno(description = "os-web 登陆页面获取RSAKey的 modulus 和 exponent")
	public BaseResult requestKeyModAndExp(){

		KeyPair key = RSAKeyFactory.getInstance().getKeyPair();
		RSAPublicKey pkey = (RSAPublicKey) key.getPublic();
		String modulus = pkey.getModulus().toString(16);
		String exponent = pkey.getPublicExponent().toString(16);

		logger.info("登录请求：" + " modulus =" + modulus
				+ "  exponent = " + exponent );

		SessionUtil.setSession(SessionKeyUtil.RSAkeyPair,key,30);

		Map  keyMap = new HashMap();
		keyMap.put("modulus",modulus);
		keyMap.put("exponent",exponent);


		return  new WebResult(UserReturnCode.LOGIN_GET_KEYPAIR_SUCCESS,keyMap);
	}


	/** 
	 * @description:  客户端提交注册按钮
	 * @param:    map: 1.loginName  登录名称
	 *                 2.loginPassword  登录密码
	 *                 3. logginVerificationCode 登录验证码
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/7/18 
	*/ 
	@ApiOperation(value="/user/login/submit",httpMethod="POST",notes = "os-web 登陆页面请求")
	@PrintUrlAnno(description = "os-web 登陆页面请求")
	@PostMapping(path="/submit")
	@ResponseBody
	public BaseResult  login(@RequestBody Map<String, Object> requestMap) {

		if(requestMap.isEmpty()){
			logger.info("请求参数为空");
			return  new WebResult(NullPointerCode.NULL_POINT);
		}

		/*接受客户端发来的数据*/
		String loginName = (String)requestMap.get("loginName") ;
		String loginPassword = (String)requestMap.get("loginPassword");
		String logginVerificationCode = (String)requestMap.get("logginVerificationCode");

		if( (CheckNullUtil.isNullString(loginName))
			|| (CheckNullUtil.isNullString(loginPassword))
			|| (CheckNullUtil.isNullString(logginVerificationCode))){
			return  new WebResult(NullPointerCode.NULL_POINT);
		}



		/*获取RSA 的keyPair */
		KeyPair key = (KeyPair)SessionUtil.getSession(SessionKeyUtil.RSAkeyPair);
		RSAPrivateKey privateKey = (RSAPrivateKey) key.getPrivate();
		/*从session获取验证码*/
		String verificationCodeSave = (String)SessionUtil.getSession(SessionKeyUtil.loginVerificationCode);
		logger.info("之前保存的验证码 = " + verificationCodeSave);
		logger.info("用户提交的验证码 = " + logginVerificationCode);
		/*if(verificationCodeSave.equals(logginVerificationCode) == false) {
			//验证码有误
			map.put("loginStatus", UserStatus.VALIDATE_CODE_ERR);
			JSONObject json = JSONObject.fromObject(map);

			return json;
		} */
		/*解密，获取原始密码*/
		logger.info("通过 privateKeyModulus  和 privateKeyExponent 获取私钥");
		logger.info(" 获取私钥为 = " + privateKey );
		logger.info(" 私钥  privateKeyModulus = " + privateKey.getModulus() );
		logger.info(" 私钥  privateKeyExponent = " + privateKey.getPrivateExponent());
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


		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName,passWord);
		System.out.println("认证状态 = " + subject.isAuthenticated());
		try {

			token.setRememberMe(true);
			/*登录验证*/
			subject.login(token);
			logger.debug("用户登录成功");
			/*登录成功 ，获取当前的用户并保存到session中*/
			OsUser user;
			if(ProjectRegex.isTelNum(loginName)) {
				user = userServiceImpl.getUser(null, loginName, null);
			}
			else if(ProjectRegex.isEmail(loginName)) {
				user = userServiceImpl.getUser(null, null, loginName);;
			}
			else {
				user = userServiceImpl.getUser(loginName, null, null);;
			}
			logger.debug("当前用户 = " + user.getUserId());
			SessionUtil.setSession(SessionKeyUtil.currentLoginUser, user,30);
			OsUser user1=(OsUser) SessionUtils.getSession("currenLogintUser");
			if(user1 != null) {
				logger.debug("当前登录的用户号码为 = " + user1.getUserId());
			}
			else {
				logger.debug("HomePageController 当前无用户登录 ");
			}
			/*更新登录日志*/
			OsUserLogin userLogin = new OsUserLogin();
			userLogin.setLoginIp(ServletUtils.getAddrIP(request));
			userLogin.setLoginTime(new Date());
			userLogin.setExplorer(ServletUtils.getAggent(request));
			userLoginService.addLoginLog(user.getUserId(), userLogin);



			if(subject.isAuthenticated()) {
				logger.info("用户[" + loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			}

			return  new WebResult(UserReturnCode.LOGIN_SUCCESS);

		}catch(UnknownAccountException uae){
			logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
			return  new WebResult(UserReturnCode.LOGIN_UNKNOW_ACCOUT);

		}catch(IncorrectCredentialsException ice){
			logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
			return  new WebResult(UserReturnCode.LOGIN_PASSWORD_ERR);

		}catch(LockedAccountException lae){
			logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");
			return  new WebResult(UserReturnCode.LOGIN_LOCK_ACCOUNT);

		}catch(ExcessiveAttemptsException eae){
			logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
			return  new WebResult(UserReturnCode.LOGIN_PASSWORD_ERR_MORE);

		}catch(AuthenticationException ae){
			//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			return  new WebResult(UserReturnCode.LOGIN_FAIL);
		}

	}

}
