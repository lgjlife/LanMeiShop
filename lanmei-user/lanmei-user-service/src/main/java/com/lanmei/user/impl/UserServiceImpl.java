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
import com.lanmei.user.service.UserService;
import com.lanmei.user.sms.SmsDemo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class UserServiceImpl extends BaseService implements UserService {

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
	
	public OsUser getById(Long userId) {
		
		logger.debug("getByIding ...... userMapper = " +  userMapper + " userId = " + userId);
	
		System.out.println(dataSource.getUrl());
		System.out.println(dataSource.getDriverClassName());
		System.out.println(dataSource.getUsername());
		System.out.println(dataSource.getPassword());
		OsUser osuser = userMapper.selectById(userId);
		logger.debug("phone : " + osuser.getPhoneNum());
		return osuser;
	}

	/** 
	 * @description:  检测手机号是否已经注册
	 * @param:    phoneNum ： 手机号码
	 * @return:   true : 已经注册
	 *            false : 还未注册
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	public boolean isRegisterOfPhoneNum(String phoneNum) {
		
		OsUser osuser = userMapper.selectByPhoneNum(phoneNum);
		
		if(osuser == null ) {
			logger.debug("您查找的手机号码" + phoneNum +"不存在");
			return false;
		}
		else {
			logger.debug("您查找的手机号码" + phoneNum +"已经注册");
			return true;
		}		
	}
	public OsUser getUser(String nickName,String phoneNum,String email){
		
		OsUser osuser = userMapper.selectByUser(nickName,phoneNum,email);
		
		if(osuser == null ) {
			logger.debug("您查找的用户不存在");
			
		}
		else {
			logger.debug("您查找的用户" + osuser.getUserId() +"存在");
		}		
		return osuser;
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

	public  void sendMsg(String phoneNum,String code ) {	
		try {
			SmsDemo.sendSms(phoneNum,code);
			logger.debug("发送短信{}-{}成功",phoneNum,code);
			
		} catch (Exception e) {
			logger.debug("发送短信{}-{}失败",phoneNum,code);
		}
	}

	/** 
	 * @description:  获取用户信息
	 * @param:   name ： nickName , phone ,email
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	@Override
	public OsUser queryUser(String name) {


		if(UserRegexUtil.isMobile(name)) {
			OsUser osuser = userMapper.selectByTelNum(name);
			return osuser;

		}
		else if(UserRegexUtil.isEmail(name)) {
			OsUser osuser = userMapper.selectByEmail(name);
			return osuser;
		}
		else {
			OsUser osuser = userMapper.selectByNickName(name);
			return osuser;
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
	 * @description: 帐号注册
	 * @param:    map :   registerName /  registerPassword / registerPhoneValidateCode
	 * @return:
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	@Override
	public UserReturnCode register(Map<String, Object> inputMap) {

		if(inputMap == null){
			return UserReturnCode.NULL_POINTER;
		}

		String registerPhoneNum = (String) inputMap.get("registerPhoneNum");
		String registerPassword = (String) inputMap.get("registerPassword");
		String registerPhoneValidateCode = (String) inputMap.get("registerPhoneValidateCode");

		if((CheckNullUtil.isNullString(registerPhoneNum))
		||  (CheckNullUtil.isNullString(registerPassword))
		|| (CheckNullUtil.isNullString(registerPhoneValidateCode))){
			logger.error("输入参数为空");
			return UserReturnCode.NULL_POINTER;
		}
		logger.debug("registerPhoneNum = " + registerPhoneNum
				+ "\r\n  registerPassword = " + registerPassword
				+ "\r\n  registerPhoneValidateCode  = " + registerPhoneValidateCode) ;


		KeyPair key = (KeyPair)SessionUtil.getSession(SessionKeyUtil.RSAkeyPair);
		RSAPrivateKey privateKey = (RSAPrivateKey) key.getPrivate();


		logger.debug("通过 privateKeyModulus  和 privateKeyExponent 获取私钥");
		logger.debug(" 获取私钥为 = " + privateKey );
		logger.debug(" 私钥  privateKeyModulus = " + privateKey.getModulus() );
		logger.debug(" 私钥  privateKeyExponent = " + privateKey.getPrivateExponent());
		byte[] en_result = new BigInteger(registerPassword, 16).toByteArray();
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

		OsUser user = new OsUser();
		/*将手机/盐/密码/注册时间保存到数据库中*/
		user.setPhoneNum(registerPhoneNum);
		user.setLoginPassword(resultPassword);
		user.setSalt(random);
		user.setRegisterTime(new Date());
		Integer  retCode = userMapper.insert(user);
		if(retCode == 1){
			return  UserReturnCode.REGISTER_SUCCESS;
		}

		return UserReturnCode.REGISTER_FAIL;
	}

	/** 
	 * @description:  获取手机验证码
	 * @param:  
	 * @return:    6位的手机验证码
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	@Override
	public String getPhoneValidateCode(String phoneNum) {
		String  phoneValidateCode =String.valueOf(getRandom());
		logger.info("注册验证码 = " + phoneValidateCode);
		SessionUtil.setSession(SessionKeyUtil.currentRegisterUser+phoneNum,
				phoneValidateCode,
				30);
		sendMsg(phoneNum,phoneValidateCode);
		return phoneNum;
	}
}

