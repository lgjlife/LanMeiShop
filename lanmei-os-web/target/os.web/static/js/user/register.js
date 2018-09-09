/**注册流程
 * 输入电话号码，移除光标时，检查格式有没有问题，
 * 没问题检查该手机号是否注册，有问题显示提示，并使能获取验证码按键
 * 点击获取验证码，倒计时，请求验证码
 * 输入验证码
 * 设置密码，输入完移除光标后检查格式
 * 输入确认密码，检查格式
 * 点击同意注册协议
 * 点击注册提交按钮
 * 检查各个输入框
 * 提交请求
 */

var baseUrl = "/lanmei-os";

var register={
    "COUNTDOWN_TIME_S":20,  //倒计时时间 单位：S
    "keyModulus":"",
    "keyExponent":"",
    //服务端返回的code
    "returnCode":{
        "ACCOUNT_EXIST":1030,//帐号注册失败,帐号已经存在"
		"NAME_EXIST":1031,//该用户名已经存在"
    	"PHONE_NUM_EXIST":1032,//该手机号已经注册"
        "EMAIL_EXIST":1033,//该邮箱已经注册"
    	"CAN_REGISTER":1034,//帐号未注册，可以进行注册"

    	"REGISTER_SUCCESS":1035,//帐号注册成功"
    	"REGISTER_FAIL":1036,//帐号注册失败"
    	"REGISTER_GET_VALIDATE_CODE_SUCCESS":1037,//获取注册验证码成功"

    	"FORMAT_PHONE_NUM_ERR ":1060,//手机号格式有误"
    },
    //请求的url
    "requestUrl":{
        //进入注册页面
        "intoRegisterPage": this.baseUrl + "/user/register",
        //检查手机号是否被注册
        "checkPhoneUrl":this.baseUrl  + "/user/register/check/phone",
        //获取RSAKey的 modulus 和 exponent
        "getKeyModAndExpUrl": this.baseUrl + "/user/register/key",
		//发送手机验证码
		"sendValidateCodeUrl":this.baseUrl + "/user/register/phone/validate/code",
		//提交登录请求
		"registerSubmitUrl":this.baseUrl + "/user/register/submit",
		//获取图片验证码
        "kaptchaUrl":this.baseUrl + "/kaptcha",
    },

    /**
	 * 发送请求  检查手机号是否被注册
     * @param registerPhoneNum 注册号码
     */
	"requestIsRegistered":function ( registerPhoneNum) {

        console.log("校验手机号是否已经被注册....  ");
        var sendData={"registerPhoneNum":""};

        sendData.registerPhoneNum = registerPhoneNum;
        $.ajax({
            type : "post",
            url : register.requestUrl.checkPhoneUrl,
           // contentType : "application/json;charset=utf-8",
            //数据格式是json串,传进一个person
            data :sendData,// JSON.stringify(sendData),
         //   dataType: "json",
            success:function(data,status){

            	if(status == "success"){
            		if(data.code != register.returnCode.CAN_REGISTER){
            			//出现问题
                        console.log("校验手机号时出现问题");
            			$("#register-registerPhoneNum-warn").text(data.message);
                        $("#register-getPhoneValidateCode-Btn").attr("disabled",true);
					}else{
            			//该手机帐号还没有注册
                        console.log("该手机帐号还没有注册");
                        $("#register-getPhoneValidateCode-Btn").attr("disabled",false);
					}
				}
            }

        });
        console.log("校验手机号是否已经被注册 执行结束....");
    },

    /**
     * 发送请求  发送手机校验码
     * @param registerPhoneNum 注册号码
     */
    "requestSendPhoneValidateCode":function ( registerPhoneNum) {

        console.log("发送手机验证码请求....  ");
        var sendData={"registerPhoneNum":""};

        sendData.registerPhoneNum = registerPhoneNum;
        $.ajax({
            type : "post",
            url : register.requestUrl.sendValidateCodeUrl,
            // contentType : "application/json;charset=utf-8",
            //数据格式是json串,传进一个person
            data :sendData,// JSON.stringify(sendData),
            //   dataType: "json",
            success:function(data,status){

                if(status == "success"){
                    if(data.code != register.returnCode.REGISTER_GET_VALIDATE_CODE_SUCCESS){
						console.log("发送电话验证码状态：" + data.message);
                    }else{

                    }
                }
            }

        });
        console.log("发送手机验证码请求 执行结束....");
    },

    /**
     *  获取key  modulus  exponent 请求
     *  return:  true :成功
     *           false ; 失败
     */"requestKey":function() {
        console.log("requestKey url = " + register.requestUrl.getKeyModAndExpUrl)
        $.get(register.requestUrl.getKeyModAndExpUrl,function(data,status){

            console.log("status = " + status);

            console.log("data modulus = " + data.code);
            console.log("data message = " + data.message);
            console.log("data object modulus = " +  data.object.modulus);
            console.log("data object exponent = " +  data.object.exponent);

            if(status == "success"){
                register.keyModulus = data.object.modulus;
                register.keyExponent = data.object.exponent;

                console.log("login.keyModulus = " +  register.keyModulus);
                console.log("login.keyExponent = " +  register.keyExponent);

                return true;
            }
            return false;

        });
    },
	"requestRegisterSubmit":function () {

     	var registerPhoneNum = $("#register-registerPhoneNum-input").val();
        var registerPassword = $("#register-registerPassword-input").val();
        var registerPhoneValidateCode = $("#register-registerPhoneValidateCode-input").val();


		if((register.keyExponent == null)
			|| (register.keyModulus == null)){
				register.requestKey();
				return;
		}

		console.log("register.keyExponent = " + register.keyExponent  );
        console.log("register.keyModulus = " + register.keyModulus );
        /*需要设置*/
        console.log("获取 publicKey--- ");
        setMaxDigits(130);
        var publicKey = new RSAKeyPair(register.keyExponent,"",register.keyModulus);
        if(publicKey == null){
            console.log("publicKey--- null");
        }
        console.log("publicKey--- " + publicKey);
        console.log("开始进行加密......");
        var password = encryptedString(publicKey, encodeURIComponent(registerPassword));



		var jsonData={"registerPhoneNum":"","registerPassword":"","registerPhoneValidateCode":"",};
		jsonData.registerPhoneNum = registerPhoneNum;
		jsonData.registerPassword = password;
		jsonData.registerPhoneValidateCode = registerPhoneValidateCode;

		$.ajax({
			type : "post",
			url : register.requestUrl.registerSubmitUrl,
			contentType : "application/json;charset=utf-8",
			//数据格式是json串,传进一个person
			/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
			data : JSON.stringify(jsonData),
			dataType: "json",
			success:function(data,status){

				if(status == "success"){

					if(data.code == register.returnCode.REGISTER_SUCCESS ){
						//注册成功
						console.log("注册状态 = " + data.message);
						$("#register-form").hide();
						$("#register-successDisplay").show();

					}
					else{
						//注册失败
						$("#register-submit-btn-warn").text(data.message);
					}
				}

			}
		});

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
            //不是电话号码
            $("#register-registerPhoneNum-warn").text("请输入有效的手机号码");
            isRegisterPhoneNumValid = false ;
            return false;
        }
        else{
            //是电话号码

            register.requestIsRegistered(registerPhoneNum);
            isRegisterPhoneNumValid = true ;
            return true;
        }
    },
	"checkPasswordFormatAndStrength":function () {
        var registerPassword = $("#register-registerPassword-input").val();
        /*校验密码是否符合要求 */
        if(isAllNum(registerPassword) ){

            $("#register-registerPassword-warn").text("密码不能为纯数字");
            isRegisterPasswordValid = false;
            return false;
        }
        else if(!isLenMaxThan8(registerPassword) ){

            $("#register-registerPassword-warn").text("密码长度小于8位");
            isRegisterPasswordValid = false;
            return false;
        }
        else{
            $("#register-registerPassword-warn").text("");
            isRegisterPasswordValid = true;
        }
        /*校验密码强度*/
        var strength = checkPasswordStrength(registerPassword);

        switch(strength){
            case 0:$("#register-passwordStrength").text("密码强度：较弱");
                break;
            case 1:$("#register-passwordStrength").text("密码强度：中等");
                break;
            case 2:$("#register-passwordStrength").text("密码强度：强");
                break;
            default:$("#register-passwordStrength").text("");


        }
        return true;
    },
    /**
	 *  检测两次输入的密码是否一致
     * @returns {boolean} true : 输入一致  ， false: 输入不一致
	 *
     */
	"checkPasswordAgain":function () {
        var registerPasswordAgain = $("#register-registerPassword-again").val();
        var registerPassword = $("#register-registerPassword-input").val();

        console.log("registerPasswordAgain = " + registerPasswordAgain);
        console.log("registerPasswordAgain len = " + registerPasswordAgain.length);
        console.log("registerPassword = " + registerPassword);
        console.log("registerPassword len = " + registerPassword.length);

        if((registerPassword.length != 0) && (registerPasswordAgain != registerPassword)){
            $("#register-registerPassword-again-warn").text("两次输入的密码不一致");
            return false;
        }
        else{
            $("#register-registerPassword-again-warn").text("");
			return true;
        }
    },
    /**
	 * 检查 同意注册选项是否已经选择
     * @returns {boolean}  true: 已经选择 ， false : 还未选择
     */
	"checkAgreeSelect":function () {
        console.log("registerCheckbox = " + $("#register-checkbox").prop("checked"));
        if($("#register-agreeBox").prop("checked") == true){
            //选择
            isRegisterCheckboxValid = true;
            $("#register-agreeBox-warn").text("");
            return true;
        }
        else{
            //未选择
            isRegisterCheckboxValid = false;
            return false;
        }
    },
    /**
	 * 检查所有的输入是否有效
     */
	"checkAllInputValid":function () {
        var registerPhoneNum = $("#register-registerPhoneNum-input").val();
        //电话号码
		if(register.checkPhoneNumFormat(registerPhoneNum) == false){
			console.log("1.电话号码校验失败。。。。。。。。。。");
			return false;
		}
        console.log("1.电话号码校验成功");
		//检查验证码
		var registerPhoneValidateCode = $("#register-registerPhoneValidateCode-input").val();
		if((registerPhoneValidateCode == null)||(registerPhoneValidateCode.length == 0)){
            console.log("2.验证码校验失败。。。。。。。。。。");
			return false;
		}
        console.log("2.验证码校验成功");
		//校验密码
		if(register.checkPasswordFormatAndStrength() == false){
            console.log("3.校验密码失败。。。。。。。。。。");
			return false;
		}
        console.log("3.校验密码成功");
		if(register.checkPasswordAgain() == false){
            console.log("3.再次校验密码失败。。。。。。");
			return false;
		}
        console.log("3.再次校验密码成功");
		if(register.checkAgreeSelect() == false){
            console.log("4.没有选择同意用户协议。。。。。。。。。。");
			return false;
		}
        console.log("所有校验成功");
		return true;
    },
	
	"showCountdownTime":function () {
        var startTime = new Date().getTime();

        var timeDiff =  endTime - startTime;//ms
        var countDownTime =  Math.floor(timeDiff/1000 + 0.5 );
       // console.log("" + countDownTime + "秒后重新获取验证码");

        if(countDownTime >  0){
            $("#register-getPhoneValidateCode-Btn").text("" + countDownTime + "秒后重新获取验证码");
            setTimeout("register.showCountdownTime()",1000);
            getPhoneNumValidateFlag = true;
            //$("#getPhoneNumValidateBtn").attr("disabled",true);
            $("#register-getPhoneValidateCode-Btn").attr("disabled",true);
        }
        else{
            $("#register-getPhoneValidateCode-Btn").text("获取验证码");
            getPhoneNumValidateFlag = false;
            $("#register-getPhoneValidateCode-Btn").attr("disabled",false);
        }
    }
}

/**
 *  电话输入框失去焦点时校验
 */
$(function(){
    $("#register-registerPhoneNum-input").blur(function(){

        var registerPhoneNum = $("#register-registerPhoneNum-input").val();
		if(register.checkPhoneNumFormat(registerPhoneNum)){
            $("#register-registerPhoneNum-warn").text("");
            register.requestIsRegistered(registerPhoneNum);
		}
    });

});

/**
 * 获取验证码按键按下处理
 * 按下进行倒计时
 * @returns
 */
$(function(){
    $("#register-getPhoneValidateCode-Btn").click(function(){
        console.log("time display" );
        endTime =  new Date().getTime() + register.COUNTDOWN_TIME_S * 1000;
        register.showCountdownTime();
        var registerPhoneNum = $("#register-registerPhoneNum-input").val();
        //向服务端获取验证码
		register.requestSendPhoneValidateCode(registerPhoneNum);

    });
});


/***
 * 密码设置校验
 * 1.长度不能低于八位
 * 2.不能为纯数字
 * 3.最大长度无限制
 * @returns
 */
$(function(){
    $("#register-registerPassword-input").blur(function(){
		register.checkPasswordFormatAndStrength();

    });//end of $("#registerPassword").blur(function(){
});
/***
 * 密码重复校验
 * @returns
 */
$(function(){
    $("#register-registerPassword-again").blur(function(){
		register.checkPasswordAgain();

    });//end of $("#registerPassword").blur(function(){
});
/**
 * 同意协议选择框 点击
 */
$(function(){
    $("#register-agreeBox").click(function(){
		register.checkAgreeSelect();

        //获取key  modulus  exponent 请求
		register.requestKey();
    });
});

/***
 * 提交按钮操作
 * 1.先校验各个输入框是否正确
 * 2.
 * @returns
 */
$(function(){	
	$("#register-submit-btn").click(function(){
		console.log("提交注册");
		if(register.checkAllInputValid() == true){
			register.requestRegisterSubmit();
		}

	});
});