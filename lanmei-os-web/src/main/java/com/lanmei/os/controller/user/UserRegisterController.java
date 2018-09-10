package com.lanmei.os.controller.user;

import com.lanmei.common.code.NullPointerCode;
import com.lanmei.common.code.UserReturnCode;
import com.lanmei.common.result.BaseResult;
import com.lanmei.common.result.WebResult;
import com.lanmei.common.utils.CheckNullUtil;
import com.lanmei.common.utils.UserRegexUtil;
import com.lanmei.sysaop.syslog.anno.PrintUrlAnno;
import com.lanmei.user.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;




/**
 * 处理用户注册请求Controller
 * @author lgj
 * @date:2018/05/17
 */
@Api(value="/user/register",description="处理用户注册请求Controller")
@Controller
@RequestMapping("/user/register")
public class UserRegisterController {
	 
	private final static Logger logger = LoggerFactory.getLogger("UserRegisterController.class");	
	{
		logger.debug("UserRegisterController Created Bean............. ");
	}
	
	@Autowired
	UserServiceImpl userServiceImpl;


	/**
	 * @description:  进入注册页面
	 * @param:
	 * @return:
	 * @author: Mr.lgj
	 * @date: 9/7/18
	 */
	@PrintUrlAnno(description = "os-web 进入注册页面")
	@ApiOperation(value="/user/register",httpMethod="GET",notes = "进入注册页面")
	@GetMapping
	public String  registerPage() {
		return "/user/register";
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
	@ApiOperation(value="/user/register/key",httpMethod="GET",notes = "os-web 注册页面获取RSAKey的 modulus 和 exponent")
	@PrintUrlAnno(description = "os-web 注册页面获取RSAKey的 modulus 和 exponent")
	@GetMapping(path="/key")
	@ResponseBody
	public BaseResult requestKeyModAndExp(){
		Map<String,String> keyMap = userServiceImpl.getKeyModAndExp();

		return  new WebResult(UserReturnCode.LOGIN_GET_KEYPAIR_SUCCESS,keyMap);
	}

	/** 
	 * @description:  校验注册的手机是否已经注册
	 * @param:        phoneNum : 注册手机号码
	 * @return:       NullPointerCode.NULL_POINT：请求的手机号码为空
	 *                UserReturnCode.FORMAT_PHONE_NUM_ERR
	 *                UserReturnCode.CAN_REGISTER
	 *                UserReturnCode.PHONE_NUM_EXIST
	 *
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	@ApiOperation(value="/user/info/find/password/check/name",notes="注册时校验手机号是否已经注册")
	@PrintUrlAnno(description = "os-web 注册时校验手机号是否已经注册")
	@ResponseBody
	@PostMapping(path="find/password/check/name")
	public BaseResult checkPhoneNum(@RequestParam("registerPhoneNum")  String registerPhoneNum) {

		if(CheckNullUtil.isNullString(registerPhoneNum)){
			return new WebResult(NullPointerCode.NULL_POINT);
		}
		if(!UserRegexUtil.isMobile(registerPhoneNum)){
			return new WebResult(UserReturnCode.FORMAT_PHONE_NUM_ERR);
		}

		boolean isRegister = userServiceImpl.isRegisterOfPhoneNum(registerPhoneNum);
		if(isRegister){
			// true  已经注册
			return new WebResult(UserReturnCode.PHONE_NUM_EXIST);

		}
		else {
			// false 还未注册
			return  new WebResult(UserReturnCode.CAN_REGISTER);
		}
	}

	/** 
	 * @description:    客户端发送手机号、服务端随机生成6位手机验证码
	 * @param:  
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/8/18 
	*/ 
	@ApiOperation(value="/user/register/phone/validate/code",notes="客户端发送json请求手机验证码，生成并返回验证码")
	@PrintUrlAnno(description = "os-web 客户端发送json请求手机验证码，生成并返回验证码")
	@ResponseBody
	@PostMapping(value="/phone/validate/code")
	public BaseResult getPhoneValidateCode(@RequestParam("registerPhoneNum") String registerPhoneNum) {

		if(CheckNullUtil.isNullString(registerPhoneNum)){
			return new WebResult(NullPointerCode.NULL_POINT);
		}
		if(!UserRegexUtil.isMobile(registerPhoneNum)){
			return new WebResult(UserReturnCode.FORMAT_PHONE_NUM_ERR);
		}

        String phoneValidateCode = userServiceImpl.getPhoneValidateCode(registerPhoneNum);
		if(phoneValidateCode != null){
			return new WebResult(UserReturnCode.REGISTER_GET_VALIDATE_CODE_SUCCESS,
					phoneValidateCode);
		}
		else {
			return new WebResult(NullPointerCode.NULL_POINT);
		}

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
	@ApiOperation(value="/user/register/submit",notes="提交注册请求")
	@PrintUrlAnno(description = "os-web提交注册请求")
	@ResponseBody
	@PostMapping(value="/submit")
	public BaseResult register(@RequestBody Map<String, Object> inputMap) {

		if(inputMap == null){
			return new WebResult(NullPointerCode.NULL_POINT);
		}

		UserReturnCode  retCode = userServiceImpl.register(inputMap);
		return new WebResult(retCode);
	}


	
}
