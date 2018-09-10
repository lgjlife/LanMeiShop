
var baseUrl = BaseProjectName;


var findPassword={
    "COUNTDOWN_TIME_S":20,  //倒计时时间 单位：S
    "keyModulus":"",
    "keyExponent":"",
    //服务端返回的code
    "returnCode":{
        "NULL_POINTER":0,//"输入参数无效"
		"FORMAT_PHONE_NUM_ERR":1060,//"手机号格式有误"
        "FORMAT_EMAIL_ERR":1060,//":邮箱地址格式有误"
        "ACCOUNT_REGISTER":1038,//:该帐号未注册"),
        "ACCOUNT_NOT_REGISTER":1039,//"该帐号已经注册"
        "REGISTER_GET_VALIDATE_CODE_SUCCESS":1037,//获取注册验证码成功"
        "INFO_RESET_PASSWORD_SUCCESS":1080,//"重置密码成功"),
        "INFO_RESET_PASSWORD_FAIL":1081,//"重置密码失败"),
        "VALIDATE_CODE_CHECK_PASS":100,//"验证码校验通过"),
        "VALIDATE_CODE_CHECK_FAIL":101,//,"验证码校验失败"),

    },
    //请求的url
    "requestUrl":{
        //项目基础路径

        //获取RSAKey的 modulus 和 exponent
        "getKeyModAndExpUrl": this.baseUrl + "/user/info/find/password/key",
        //t提交登录请求
        "checkNameUrl":this.baseUrl + "/user/info/find/password/check/name",
		//发送验证码请求
        "sendValidateCodeUrl":this.baseUrl + "/user/info/find/password/validate/code",
        //校对验证码请求
        "checkValidateCodeUrl":this.baseUrl + "/user/info/find/password/check/validate/code",
        //重置密码请求
        "resetPasswordUrl":this.baseUrl +"/user/info/find/password/reset/submit",
        "kaptchaUrl":this.baseUrl + "/kaptcha",
    },

	"page":{
    	//输入账户名
    	"inputNameFormPage":"inputNameFormPage",
		//验证身份
		"validateFormPage":"validateFormPage",
		//重置密码
		"resetPasswordFormPage":"resetPasswordFormPage",
		//重置成功
		"resetSuccessFormPage":"resetSuccessFormPage",
	},
    "currentPage":"inputNameFormPage",

    /**
     * 发送请求  检查手机号是否被注册
     * @param registerPhoneNum 注册号码
     */
    "requestIsRegistered":function ( loginName) {

        console.log("校验手机号是否已经被注册....  ");
        var sendData={"loginName":""};

        sendData.loginName = loginName;
        $.ajax({
            type : "post",
            url : findPassword.requestUrl.checkNameUrl,
            // contentType : "application/json;charset=utf-8",
            //数据格式是json串,传进一个person
            data :sendData,// JSON.stringify(sendData),
            //   dataType: "json",
            async:true,

            success:function(data,status){

                if(status == "success"){
                    if(data.code == findPassword.returnCode.ACCOUNT_REGISTER){
                    	console.log(data.message);
						findPassword.pageDisplayHandle(findPassword.page.validateFormPage);
                        $("#findPassword-warn").text("");
                    }else{
                        findPassword.pageDisplayHandle(findPassword.page.inputNameFormPage);
                        console.log(data.message);
                        $("#findPassword-warn").text(data.message);

                    }
                }
            }

        });
        console.log("校验手机号。邮箱是否已经被注册 执行结束....");
    },
    /**
     * 发送请求  发送手机校验码
     * @param registerPhoneNum 注册号码
     */
    "requestSendValidateCode":function ( loginName) {

        console.log("发送验证码请求....  ");
        var sendData={"loginName":""};

        sendData.loginName = loginName;
        console.log("发送的数据：" + sendData.loginName);

        $.ajax({
            type : "post",
            url : findPassword.requestUrl.sendValidateCodeUrl,
            // contentType : "application/json;charset=utf-8",
            //数据格式是json串,传进一个person
            data :sendData,// JSON.stringify(sendData),
            //   dataType: "json",
            success:function(data,status){

                if(status == "success"){
                    if(data.code != findPassword.returnCode.REGISTER_GET_VALIDATE_CODE_SUCCESS){
                        console.log("发送验证码状态：" + data.message);
                    }else{
                        console.log("发送验证码状态：" + data.message);
                    }
                }
            }

        });
        console.log("发送手机验证码请求 执行结束....");
    },
    /**
     *  校验用户输入的验证吗是否正确
     * @param loginName
     * @param validateCode
     */
    "requestCheckValidateCode":function ( loginName,validateCode) {

        console.log("发送验证码请求....  ");
        var sendData={"loginName":"","validateCode":""};

        sendData.loginName = loginName;
        sendData.validateCode = validateCode+"";
        console.log("发送的数据：" + sendData.loginName);

        $.ajax({
            type : "post",
            url : findPassword.requestUrl.checkValidateCodeUrl,
            // contentType : "application/json;charset=utf-8",
            //数据格式是json串,传进一个person
            data :sendData,// JSON.stringify(sendData),
            //   dataType: "json",
            success:function(data,status){

                if(status == "success"){
                    if(data.code != findPassword.returnCode.VALIDATE_CODE_CHECK_PASS){
                        console.log("发送验证码状态：" + data.message);
                        $("#findPassword-warn").text(data.message);
                    }else{
                        console.log("发送验证码状态：" + data.message);
                        $("#findPassword-warn").text("");
                        findPassword.pageDisplayHandle(findPassword.page.resetPasswordFormPage);
                    }
                }
            }

        });
        console.log("发送手机验证码请求 执行结束....");
    },

    "requestResetPassword":function () {

        var resetName = $("#findPassword-registerPassword").val();
        var resetPassword = $("#findPassword-registerPassword").val();


        if((findPassword.keyExponent == null)
            || (findPassword.keyModulus == null)){
            findPassword.requestKey();
            return;
        }

        console.log("register.keyExponent = " + findPassword.keyExponent  );
        console.log("register.keyModulus = " + findPassword.keyModulus );
        /*需要设置*/
        console.log("获取 publicKey--- ");
        setMaxDigits(130);
        var publicKey = new RSAKeyPair(findPassword.keyExponent,"",findPassword.keyModulus);
        if(publicKey == null){
            console.log("publicKey--- null");
        }
        console.log("publicKey--- " + publicKey);
        console.log("开始进行加密......");
        var password = encryptedString(publicKey, encodeURIComponent(resetPassword));
        console.log("加密后的密码：" + password);


        var jsonData={"resetName":"","resetPassword":""};
        jsonData.resetName = "13925716752";//resetName;
        jsonData.resetPassword = password;

        $.ajax({
            type : "post",
            url : findPassword.requestUrl.resetPasswordUrl,
            contentType : "application/json;charset=utf-8",
            //数据格式是json串,传进一个person
            /*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
            data : JSON.stringify(jsonData),
            dataType: "json",
            success:function(data,status){

                if(status == "success"){

                    if(data.code == findPassword.returnCode.INFO_RESET_PASSWORD_SUCCESS ){
                        //注册成功
                        console.log("注册状态 = " + data.message);
                       // $("#register-form").hide();
                      //  $("#register-successDisplay").show();
                        findPassword.pageDisplayHandle(findPassword.page.resetSuccessFormPage);
                    }
                    else{
                        console.log("注册状态 = " + data.message);
                        //注册失败
                       // $("#register-submit-btn-warn").text(data.message);
                    }
                }

            }
        });

    },

    /**
     *  获取key  modulus  exponent 请求
     *  return:  true :成功
     *           false ; 失败
     */"requestKey":function() {
        console.log("requestKey url = " + findPassword.requestUrl.getKeyModAndExpUrl)
        $.get(findPassword.requestUrl.getKeyModAndExpUrl,function(data,status){

            console.log("status = " + status);

            console.log("data modulus = " + data.code);
            console.log("data message = " + data.message);
            console.log("data object modulus = " +  data.object.modulus);
            console.log("data object exponent = " +  data.object.exponent);

            if(status == "success"){
                findPassword.keyModulus = data.object.modulus;
                findPassword.keyExponent = data.object.exponent;
                return true;
            }
            return false;

        });
    },

    "checkPasswordFormatAndStrength":function () {
        var registerPassword = $("#findPassword-registerPassword").val();
        /*校验密码是否符合要求 */
        if(isAllNum(registerPassword) ){

            $("#findPassword-registerPasswordWarn").text("密码不能为纯数字");
            return false;
        }
        else if(!isLenMaxThan8(registerPassword) ){

            $("#findPassword-registerPasswordWarn").text("密码长度小于8位");
            return false;
        }
        else{
            $("#findPassword-registerPasswordWarn").text("");
        }
        /*校验密码强度*/
        var strength = checkPasswordStrength(registerPassword);

        switch(strength){
            case 0:$("#findPassword-registerPasswordWarn").text("密码强度：较弱");
                break;
            case 1:$("#findPassword-registerPasswordWarn").text("密码强度：中等");
                break;
            case 2:$("#findPassword-registerPasswordWarn").text("密码强度：强");
                break;
            default:$("#findPassword-registerPasswordWarn").text("");


        }
        return true;
    },
    /**
     *  检测两次输入的密码是否一致
     * @returns {boolean} true : 输入一致  ， false: 输入不一致
     *
     */
    "checkPasswordAgain":function () {
        var registerPasswordAgain = $("#findPassword-registerPasswordAgain").val();
        var registerPassword = $("#findPassword-registerPassword").val();

        if((registerPassword.length != 0) && (registerPasswordAgain != registerPassword)){
            $("#findPassword-registerPasswordWarn").text("两次输入的密码不一致");
            return false;
        }
        else{
            $("#findPassword-registerPasswordWarn").text("");
            return true;
        }
    },
    /**
     * 校验手机号输入是否有问题
     * @param registerPhoneNum
     * @returns {boolean}  false: 有问题 true: 正常
     */
    "checkPhoneNumFormat":function (registerPhoneNum) {


        if((registerPhoneNum == null )
            || (registerPhoneNum == "")){
            console.log("手机号为空");
            return false;
        }

        if(!isTelCode(registerPhoneNum) ){
          return false;
        }
        else{
            return true;
        }
    },

    /**
     * 校验手机号输入是否有问题
     * @param registerPhoneNum
     * @returns {boolean}  false: 有问题 true: 正常
     */
    "checkEmailFormat":function (emali) {


        if((emali == null )
            || (emali == "")){
            console.log("邮箱号为空");
            return false;
        }

        if(!isEmail(emali) ){
          return false;
        }
        else{
          return true;
        }
    },

	"inputNameFormPageHandle":function () {

    	var loginName = $("#findPassword-loginName").val();

    	if(  (findPassword.checkPhoneNumFormat(loginName) == true)
			|| (findPassword.checkEmailFormat(loginName) == true)){
            return findPassword.requestIsRegistered(loginName);
    	}
    	else{
            $("#findPassword-warn").text("邮箱或者电话号码格式不正确");
            return false;
		}


    },
    "pageDisplayHandle":function (page) {

        findPassword.currentPage = page;

    	if(findPassword.currentPage == findPassword.page.inputNameFormPage){
            $("#findPassword-inputNameForm").show();
            $("#findPassword-validateForm").hide();
            $("#findPassword-resetPasswordForm").hide();
            $("#findPassword-findPasswordProgress-bar").css("width","25%");
        }
        else if(findPassword.currentPage == findPassword.page.validateFormPage){
            $("#findPassword-inputNameForm").hide();
            $("#findPassword-validateForm").show();
            $("#findPassword-resetPasswordForm").hide();
            $("#findPassword-findPasswordProgress-bar").css("width","50%");
        }
        else if(findPassword.currentPage == findPassword.page.resetPasswordFormPage){
            $("#findPassword-inputNameForm").hide();
    	    $("#findPassword-validateForm").hide();
            $("#findPassword-resetPasswordForm").show();
            $("#findPassword-findPasswordProgress-bar").css("width","80%");
        }

        else if(findPassword.currentPage == findPassword.page.resetSuccessFormPage){
            $("#findPassword-resetPasswordForm").hide();
            $("#findPassword-resetSuccessForm").show();
            $("#findPassword-findPasswordProgress-bar").css("width","100%");
            $("#findPassword-nextBtn").hide();
            $("#findPassword-preBtn").hide();
        }
    },

    "showCountdownTime":function () {
        var startTime = new Date().getTime();

        var timeDiff =  endTime - startTime;//ms
        var countDownTime =  Math.floor(timeDiff/1000 + 0.5 );
        // console.log("" + countDownTime + "秒后重新获取验证码");

        if(countDownTime >  0){
            $("#findPassword-getValidateCode-Btn").text("" + countDownTime + "秒后重新获取验证码");
            setTimeout("findPassword.showCountdownTime()",1000);
            getPhoneNumValidateFlag = true;
            //$("#getPhoneNumValidateBtn").attr("disabled",true);
            $("#findPassword-getValidateCode-Btn").attr("disabled",true);
        }
        else{
            $("#findPassword-getValidateCode-Btn").text("获取验证码");
            getPhoneNumValidateFlag = false;
            $("#findPassword-getValidateCode-Btn").attr("disabled",false);
        }
    }
};

$(function () {

	console.log("currentPage = " + findPassword.currentPage);
    findPassword.pageDisplayHandle(findPassword.currentPage);

	//上一步按钮
	$("#findPassword-preBtn").click(function () {

		if(findPassword.currentPage  == findPassword.page.inputNameFormPage){

		}
		else if(findPassword.currentPage  == findPassword.page.validateFormPage){
            findPassword.pageDisplayHandle(findPassword.page.inputNameFormPage);
        }
        else if(findPassword.currentPage  == findPassword.page.resetPasswordFormPage){
            findPassword.pageDisplayHandle(findPassword.page.validateFormPage);
        }
        else if(findPassword.currentPage  == findPassword.page.resetSuccessFormPage){
            findPassword.pageDisplayHandle(findPassword.page.resetPasswordFormPage);
        }
		
    });

	//下一步
    $("#findPassword-nextBtn").click(function () {

        if(findPassword.currentPage  == findPassword.page.inputNameFormPage){

            findPassword.inputNameFormPageHandle();
            findPassword.pageDisplayHandle(findPassword.page.validateFormPage);

        }
        else if(findPassword.currentPage  == findPassword.page.validateFormPage){

            var loginName = $("#findPassword-loginName").val();
            var validateCode = $("#findPassword-validateCode").val();
            console.log("validateCode = "  + validateCode);
            if((validateCode != null) && (loginName != null)){
                findPassword.requestCheckValidateCode(loginName,validateCode);
            }



        }
        else if(findPassword.currentPage  == findPassword.page.resetPasswordFormPage){

            if(findPassword.checkPasswordAgain() ==  true){
                findPassword.requestResetPassword();
            }
        }

    });
})

/**
 * 获取验证码按键按下处理
 * 按下进行倒计时
 * @returns
 */
$(function(){
    $("#findPassword-getValidateCode-Btn").click(function(){
        console.log("time display" );
        endTime =  new Date().getTime() + findPassword.COUNTDOWN_TIME_S * 1000;
        findPassword.showCountdownTime();
        var loginName = $("#findPassword-loginName").val();

        findPassword.requestSendValidateCode(loginName);

    });
});

$(function () {
    $("#findPassword-registerPassword").blur(function () {
        //检查密码的强度和是否符合规范
        if(findPassword.checkPasswordFormatAndStrength() == true){
            findPassword.requestKey();
        }
    })
})