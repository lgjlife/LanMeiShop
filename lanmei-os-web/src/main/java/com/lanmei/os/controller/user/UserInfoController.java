package com.lanmei.os.controller.user;

import com.lanmei.common.code.UserReturnCode;
import com.lanmei.common.result.BaseResult;
import com.lanmei.common.result.WebResult;
import com.lanmei.common.utils.CheckNullUtil;
import com.lanmei.common.utils.UserRegexUtil;
import com.lanmei.common.utils.session.SessionKeyUtil;
import com.lanmei.common.utils.session.SessionUtil;
import com.lanmei.sysaop.syslog.anno.PrintUrlAnno;
import com.lanmei.user.dao.model.OsUser;
import com.lanmei.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 处理用户登录，注册请求Controller
 * @author lgj
 * @date:2018/05/17
 */
@Api(value="/user/info",description="处理用户信息修改Controller")
@Controller
@RequestMapping("/user/info")
public class UserInfoController {
	
	
	private final static Logger logger = LoggerFactory.getLogger("UserLoginController.class");	
	{
		logger.debug("UserInfoController Create Bean............. ");
	}


    @Autowired
    UserInfoService userInfoServiceImpl;
	/** 
	 * @description:  进入个人中心页面
	 * @param:  
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/9/18 
	*/ 
	@ApiOperation(value="/user/info",httpMethod="GET",notes="请求个人中心页面")
	@PrintUrlAnno(description = "请求个人中心页面")
	@GetMapping
	public ModelAndView  loginPage() {

		OsUser user=(OsUser) SessionUtil.getSession(SessionKeyUtil.currentLoginUser);


		if(user != null) {
			logger.info("当前登录的用户号码为 = " + user.getPhoneNum());
		}
		else {
			logger.info("UserInfoController 当前无用户登录 ..............................");
			user = new OsUser();
			user.setActualName("我是一个用户");
		}

		ModelAndView mv = new ModelAndView("/user/info-setting");
		mv.addObject("user", user);
		return mv;
	}
	
	@ApiOperation(value="/user-login",httpMethod="GET",notes="请求个人中心页面")
	@RequestMapping(path="/setting", method=RequestMethod.GET)
	public ModelAndView  infoSeting() {
		
		logger.debug("into /user-info/setting");
		
		OsUser user=(OsUser) SessionUtil.getSession("currenLogintUser");
		
		ModelAndView mv = new ModelAndView("/user/info-setting");
		mv.addObject("user", user);
		return mv;
	}


	/** 
	 * @description:
	 * @param:  
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/10/18 
	*/ 
	@PrintUrlAnno(description = "os-web 进入登陆页面")
	@ApiOperation(value="/user/info/find/password",httpMethod="GET",notes = "进入查找密码页面")
	@GetMapping("/find/password")
	public String  findPasswordPage() {
		return "/user/find-password";
	}

	/** 
	 * @description:   
	 * @param:  
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/10/18 
	*/ 
    @ApiOperation(value="/user/info/find/password/check/name",notes="注册时校验手机号是否已经注册")
    @PrintUrlAnno(description = "os-web 注册时校验手机号是否已经注册")
    @ResponseBody
    @PostMapping(path="/find/password/check/name")
    public BaseResult checkIsRegister(@RequestParam("loginName")  String loginName) {

        if(CheckNullUtil.isNullString(loginName)){
            return new WebResult(UserReturnCode.NULL_POINTER);
        }
        if(!UserRegexUtil.isMobile(loginName)){

            if(!UserRegexUtil.isEmail(loginName)){
                return new WebResult(UserReturnCode.FORMAT_EMAIL_ERR);
            }
        }


        boolean isRegister = userInfoServiceImpl.isRegistered(loginName);
        logger.info("isRegister = " + isRegister);
        if(isRegister){
            // true  已经注册
            return new WebResult(UserReturnCode.ACCOUNT_REGISTER);

        }
        else {
            // false 还未注册
            return  new WebResult(UserReturnCode.ACCOUNT_NOT_REGISTER);
        }
    }

    /** 
     * @description: 客户端发送请求手机或者验证码，生成并返回验证码
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 9/10/18 
    */ 
    @ApiOperation(value="/user/info/find/password/validate/code",notes="客户端发送请求手机或者验证码，生成并返回验证码")
    @PrintUrlAnno(description = "os-web 客户端发送请求手机或者验证码，生成并返回验证码")
    @ResponseBody
    @PostMapping(value="/find/password/validate/code")
    public BaseResult getPhoneValidateCode(@RequestParam("loginName") String loginName) {

        if(CheckNullUtil.isNullString(loginName)){
            return new WebResult(UserReturnCode.NULL_POINTER);
        }
        logger.info("loginName = " + loginName);
        if(!UserRegexUtil.isMobile(loginName)){

            if(!UserRegexUtil.isEmail(loginName)){
                return new WebResult(UserReturnCode.FORMAT_EMAIL_ERR);
            }

        }

		logger.info("userInfoServiceImpl.sendValidateCode.......... " );
        String validateCode = userInfoServiceImpl.sendValidateCode(loginName);
        if(validateCode != null){

        	logger.info("发送验证码{}成功",validateCode);
            return new WebResult(UserReturnCode.SEND_VALIDATE_CODE_SUCCESS,
                    validateCode);
        }
        else {
            return new WebResult(UserReturnCode.SEND_VALIDATE_CODE_FAIL);
        }

    }
    /** 
     * @description: 校验用户输入的验证码是否正确
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 9/10/18 
    */ 
	@ApiOperation(value="/user/info/find/password/check/validate/code",notes="校验用户输入的验证码是否正确")
	@PrintUrlAnno(description = "校验用户输入的验证码是否正确")
	@ResponseBody
	@PostMapping(value="/find/password/check/validate/code") //loginName":"","validateCode"
	public BaseResult checkPhoneValidateCode(@RequestParam("loginName") String loginName,
											 @RequestParam("validateCode") String validateCode) {


		if(CheckNullUtil.isNullString(loginName)){
			return new WebResult(UserReturnCode.NULL_POINTER);
		}
		if(CheckNullUtil.isNullString(validateCode)){
			return new WebResult(UserReturnCode.NULL_POINTER);
		}

		String saveCalidateCode = (String)SessionUtil.getSession(SessionKeyUtil.currentRegisterUser+loginName);

		logger.debug("loginName = " + loginName
				+ "  validateCode = " + validateCode
				+ "  saveCalidateCode = " + saveCalidateCode);

		if(saveCalidateCode.equals(validateCode)){
			return new WebResult(UserReturnCode.VALIDATE_CODE_CHECK_PASS);
		}

		else {
			return new WebResult(UserReturnCode.VALIDATE_CODE_CHECK_FAIL);
		}


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
	@ApiOperation(value="/user/info/find/password/key",httpMethod="GET",notes = "os-web 寻找密码页面获取RSAKey的 modulus 和 exponent")
	@PrintUrlAnno(description = "os-web 寻找密码页面获取RSAKey的 modulus 和 exponent")
	@GetMapping(path="/find/password/key")
	@ResponseBody
	public BaseResult requestKeyModAndExp(){
		Map<String,String> keyMap = userInfoServiceImpl.getKeyModAndExp();

		return  new WebResult(UserReturnCode.LOGIN_GET_KEYPAIR_SUCCESS,keyMap);
	}

	/** 
	 * @description:  重置密码请求
	 * @param:  
	 * @return:  
	 * @author: Mr.lgj 
	 * @date: 9/10/18 
	*/ 
	@ApiOperation(value="/user/info/find/password/reset/submit",httpMethod="GET",notes = "os-web 寻找密码页面获取RSAKey的 modulus 和 exponent")
	@PrintUrlAnno(description = "os-web 寻找密码页面获取RSAKey的 modulus 和 exponent")
	@PostMapping(path="/find/password/reset/submit")
	@ResponseBody
	public BaseResult resetPassword(@RequestBody Map<String,String> recMap){


		if(recMap == null){
			return new WebResult(UserReturnCode.NULL_POINTER);
		}
		UserReturnCode  retCode = userInfoServiceImpl.resetPassword(recMap);

		return  new WebResult(retCode);
	}
	
}
