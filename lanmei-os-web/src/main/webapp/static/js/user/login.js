


/**
 * 登录操作js
 */

var baseUrl = "/lanmei-os";
var login={

	"keyModulus":"",
	"keyExponent":"",
	//服务端返回的code
	"returnCode":{
        "LOGIN_SUCCESS":1000,//,"用户登录成功"),
		"LOGIN_FAIL":1001,//"用户登录失败"),
		"LOGIN_PASSWORD_ERR":1002,//"用户登录失败,密码错误"),
    	"LOGIN_LOCK_ACCOUNT":1003,//"用户登录失败，账户被锁定"),
    	"LOGIN_PASSWORD_ERR_MORE":1004,//"用户登录失败，密码错误过多"),
    	"LOGIN_GET_KEYPAIR_FAIL":1005,//"登陆时获取keypair失败"),
    	"LOGIN_GET_KEYPAIR_SUCCESS":1006,//"登陆时获取keypair成功"),
    	"LOGIN_UNKNOW_ACCOUT":1007,//"帐号不存在"),
	},
	//请求的url
	"requestUrl":{
		//项目基础路径
		"baseUrl": "/lanmei-os",
		//获取RSAKey的 modulus 和 exponent
		"getKeyModAndExpUrl": this.baseUrl + "/user/login/key",
		//t提交登录请求
		"loginSubmitUrl":this.baseUrl + "/user/login/submit",
		"kaptchaUrl":this.baseUrl + "/kaptcha",
	},

    /**
	 * 检查输入是否为空
	 * return : true :  为空
	 *          false : 不为空
     */
	"checkInput":function () {
		var loginName = $("#login-loginName").val();
		var loginPassword = $("#login-loginPassword").val();
        var loginValidateCode = $("#login-loginValidateCode").val();

        var isLoginNameNull = false;
        var isLoginPasswordNull = false;
        var isLoginValidateCodeNull = false;

        if((loginName == null)||(loginName.length == 0)){
            $("#login-loginName-warn").text("请输入登录名");
            isLoginNameNull = true;
        }
        else{
            $("#login-loginName-warn").text("");
            isLoginNameNull = false;
        }

        if((loginPassword == null)||(loginPassword.length == 0)){
			$("#login-loginPassword-warn").text("请输入密码");
            isLoginPasswordNull = true;
		}
		else{
            $("#login-loginPassword-warn").text("");
            isLoginPasswordNull = false;
		}



        if((loginValidateCode == null)||(loginValidateCode.length == 0)){
            $("#login-loginValidateCode-warn").text("请输入验证码");
            isLoginValidateCodeNull = true;
        }
        else{
            $("#login-loginValidateCode-warn").text("");
            isLoginValidateCodeNull = false;
        }

        if( (isLoginNameNull) || (isLoginPasswordNull) || (isLoginValidateCodeNull)){
        	return true;
		}
		return false;
    },
	"requestKeySuccess":function () {
		console.log("requestKeySuccess");
        console.log("data = " + data);
    },

    /**
	 *  获取key  modulus  exponent 请求
	 *  return:  true :成功
	 *           false ; 失败
     */
    "requestKey":function() {
        console.log("requestKey url = " + login.requestUrl.getKeyModAndExpUrl)
		$.get(login.requestUrl.getKeyModAndExpUrl,function(data,status){

            console.log("status = " + status);

            console.log("data modulus = " + data.code);
            console.log("data message = " + data.message);
            console.log("data object modulus = " +  data.object.modulus);
            console.log("data object exponent = " +  data.object.exponent);

            if(status == "success"){
                login.keyModulus = data.object.modulus;
                login.keyExponent = data.object.exponent;

                console.log("login.keyModulus = " +  login.keyModulus);
                console.log("login.keyExponent = " +  login.keyExponent);

				return true;
			}
			return false;

		});
    },
    /**
	 * 登录提交
     */
	"loginSubmit":function () {

        var loginName = $("#login-loginName").val();
        var loginPassword = $("#login-loginPassword").val();
        var loginValidateCode = $("#login-loginValidateCode").val();
        console.log("loginName = " + loginName
		+ "  loginPassword = " +  loginPassword
		+ "  loginValidateCode = " + loginValidateCode );

        console.log("keyExponent = " + login.keyExponent
            + "  keyModulus = " +  login.keyModulus);

       /!*需要设置*!/

       if((login.keyExponent.length == 0 ) || (login.keyExponent.length == null)
		|| (login.keyModulus.length == 0 ) || (login.keyModulus.length == null)){
       		return;
	   }
	   console.log("获取 publicKey--- ");
       setMaxDigits(130);
       var publicKey = new RSAKeyPair(login.keyExponent ,"",login.keyModulus);
       if(publicKey == null){
           console.log("publicKey--- null");
       }
       console.log("publicKey--- " + publicKey);
       console.log("开始进行加密......");
       var loginPassword = encryptedString(publicKey, encodeURIComponent(loginPassword));
       console.log("经过加密后 loginPassword   = " + loginPassword);


	   var jsonData={"loginName":"","loginPassword":"","logginVerificationCode":"",};
	   jsonData.loginName = loginName;
	   jsonData.loginPassword = loginPassword;
	   jsonData.logginVerificationCode = loginValidateCode;

	   $.ajax({
		   type : "post",
		   url : login.requestUrl.loginSubmitUrl,
		   contentType : "application/json;charset=utf-8",
		   //数据格式是json串,传进一个person
		   data : JSON.stringify(jsonData),
		   dataType: "json",
		   success:function(data,status){
			   console.log("status = " + status);
			   console.log("data code = " + data.code);
			   console.log("data message = " + data.message);
			   if(status == "success"){
			   	    if(data.code != login.returnCode.LOGIN_SUCCESS){
			   	    	//登录失败
			   	    	$("#login-loginSubmit-Warn").text(data.message);
					}
					else{
			   	    	//登录成功
						$("#login-form").hide();
						$("#login-successDisplay").show();

					}
			   }
		   }
	   });
	},
}


/***
 * 提交按钮操作
 * 1.先校验各个输入框是否正确
 * 2.
 * @returns
 */
$(function(){
	
	$("#login-loginName").blur(function () {
		console.log("login-loginName blur ");
        login.requestKey();
    })

    $("#login-loginSubmit-btn").click(function(){
        console.log("提交登录");
        //检查输入是否为空
        if(login.checkInput()){
        	return;
		}
		//提交登录请求
		login.loginSubmit();

    });
});