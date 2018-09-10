package com.lanmei.user.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.lanmei.common.code.UserReturnCode;
import com.lanmei.common.rsa.RSAKeyFactory;
import com.lanmei.common.rsa.RSAUtilNew;
import com.lanmei.common.utils.CheckNullUtil;
import com.lanmei.common.utils.UserRegexUtil;
import com.lanmei.common.utils.session.SessionKeyUtil;
import com.lanmei.common.utils.session.SessionUtil;
import com.lanmei.user.common.BaseService;
import com.lanmei.user.dao.mapper.OsUserMapper;
import com.lanmei.user.dao.model.OsUser;
import com.lanmei.user.service.UserInfoService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class UserInfoServiceImpl extends BaseService implements UserInfoService {

	private final static  int  PHONE_VALIDATE_CODE_LENGTH = 6;
	
	private final static Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");	
	{
		logger.debug("UserServiceImpl create bean ......");
	}
	@Autowired
	private OsUserMapper userMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

	@Autowired
	UserInfoService userInfoServiceImpl;

	@Autowired
	JavaMailSenderImpl mailSender;

	/** 
	 * @description:  检查帐号是被注册
	 * @param:  
	 * @return:   false: 没有被注册
	 * 			  true：已经被注册
	 * @author: Mr.lgj 
	 * @date: 9/10/18 
	*/ 
	@Override
	public boolean isRegistered(String name) {

		if(UserRegexUtil.isMobile(name)) {
			OsUser osuser = userMapper.selectByTelNum(name);
			if(osuser == null){
				logger.info("selectByTelNum is null");
				return  false;
			}
			return  true;

		}
		else if(UserRegexUtil.isEmail(name)) {
			OsUser osuser = userMapper.selectByEmail(name);
			if(osuser == null){
				logger.info("selectByEmail is null");
				return  false;
			}
			return  true;
		}
		else {
			OsUser osuser = userMapper.selectByNickName(name);
			if(osuser == null){
				logger.info("selectByNickName is null");
				return  false;
			}
			return  true;
		}

	}


	/** 
	 * @description:  
	 * @param:
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	@Override
	public Map<String, String> getKeyModAndExp() {

		KeyPair key = RSAKeyFactory.getInstance().getKeyPair();
		RSAPublicKey pkey = (RSAPublicKey) key.getPublic();
		String modulus = pkey.getModulus().toString(16);
		String exponent = pkey.getPublicExponent().toString(16);

		logger.info("注册请求：" + " modulus =" + modulus
				+ "  exponent = " + exponent );

		SessionUtil.setSession(SessionKeyUtil.RSAkeyPair,key,30);

		Map  keyMap = new HashMap();
		keyMap.put("modulus",modulus);
		keyMap.put("exponent",exponent);

		return  keyMap;
	}

	/**
	 * @description:  获取 0 - 999999的随机数
	 * @param:
	 * @return:
	 * @author: Mr.lgj
	 * @date: 9/8/18
	 */
	public static int getRandom() {

		return (int) (Math.random() * 999999);
	}


	/** 
	 * @description:  获取手机验证码
	 * @param:  
	 * @return:    6位的手机验证码
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	@Override
	public String sendValidateCode(String name) {

		String  validateCode =String.valueOf(getRandom());
		logger.info("注册验证码 = " + validateCode);

		SessionUtil.setSession(SessionKeyUtil.currentRegisterUser+name,
				validateCode,
				30);


		if(UserRegexUtil.isEmail(name)) {

			logger.info("发送邮件验证码.....");
			SimpleMailMessage mail = new SimpleMailMessage();

			mail.setTo(name);//收件人邮箱地址
			mail.setFrom("lanmeishop1@sina.com");//发件人
			mail.setSubject("lanmei商城验证邮件");//主题
			mail.setText("您还，您的验证码是：" +  validateCode + ".");//正文
			//mailSender.send(mail);
			logger.info("发送邮件验证码{}成功",validateCode);

		}
		else if(UserRegexUtil.isMobile(name)) {
			logger.info("发送短信验证码....................");
		//	SmsUtil.sendMsg(name,validateCode);
			logger.info("发送短信验证码{}成功",validateCode);
		}
		return validateCode;
	}

	@Override
	public UserReturnCode resetPassword(Map<String, String> map) {

		if(map == null){
			return UserReturnCode.NULL_POINTER;
		}

		String resetName = (String) map.get("resetName");
		String resetPassword = (String) map.get("resetPassword");


		if((CheckNullUtil.isNullString(resetName))
				||  (CheckNullUtil.isNullString(resetPassword))){
			logger.error("输入参数为空");
			return UserReturnCode.NULL_POINTER;
		}
		logger.debug("resetName = " + resetName
				+ "\r\n  resetPassword = " + resetPassword) ;


		KeyPair key = (KeyPair)SessionUtil.getSession(SessionKeyUtil.RSAkeyPair);
		RSAPrivateKey privateKey = (RSAPrivateKey) key.getPrivate();


		logger.debug("通过 privateKeyModulus  和 privateKeyExponent 获取私钥");
		logger.debug(" 获取私钥为 = " + privateKey );
		logger.debug(" 私钥  privateKeyModulus = " + privateKey.getModulus() );
		logger.debug(" 私钥  privateKeyExponent = " + privateKey.getPrivateExponent());
		byte[] en_result = new BigInteger(resetPassword, 16).toByteArray();
		byte[] pass = null;
		try {

			pass = RSAUtilNew.decrypt(privateKey,en_result);

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


		if(UserRegexUtil.isEmail(resetName)) {

			Integer  retCode = userMapper.updatePasswordByEmail(resetName,resultPassword,random);
			if(retCode == 1){
				logger.debug("{resetName}设置密码-{passWord}-成功",resetName,passWord);
				return  UserReturnCode.INFO_RESET_PASSWORD_SUCCESS;
			}

		}
		else if(UserRegexUtil.isMobile(resetName)) {
			Integer  retCode = userMapper.updatePasswordByTelNum(resetName,resultPassword,random);
			if(retCode == 1){
				logger.debug("{resetName}设置密码-{passWord}-成功",resetName,passWord);
				return  UserReturnCode.INFO_RESET_PASSWORD_SUCCESS;
			}
		}


		logger.debug("{resetName}设置密码-{passWord}-失败",resetName,passWord);
		return UserReturnCode.REGISTER_FAIL;

	}
}

