/*输入框状态 true: 正确 ，false : 未输入或者输入不规范*/
var isRegisterPhoneNumValid = false ;
var isRegisterPhoneNumValidateValid = false;
var isRegisterPasswordValid = false;
var isRegisterPasswordAgainValid = false;
var isRegisterCheckboxValid = false;//
var endTime = 0;//倒计时截止时间
var getPhoneNumValidateFlag = false;//获取验证码标志
var COUNTDOWN_TIME_S=20;  //倒计时时间 单位：S
var registerPhoneNumValidateLength = 6; //手机验证码长度
var phoneValidateCode = null;//手机验证码

$(function(){
	$("#keytest").click(function(){
		var publicKey_modulus = $("#registerForm").attr("publicKey-modulus");
		var publicKey_exponent = $("#registerForm").attr("publicKey-exponent");
		var registerPassword = $("#registerPassword").val();
		console.log("publicKey_modulus = " + publicKey_modulus);
		console.log("publicKey_exponent = " + publicKey_exponent);
		console.log("registerPassword = " + registerPassword);
		
		/*需要设置*/
		RSAUtils.setMaxDigits(160);
		var publicKey = new RSAUtils.getKeyPair(publicKey_exponent, '', publicKey_modulus);
		console.log("获取 publicKey--- ");
		var loginPassword = RSAUtils.encryptedString(publicKey, registerPassword);
		console.log("经过加密后 loginPassword   = " + loginPassword);
	});
});
/**
 * 电话输入框失去焦点时校验
 * 1.电话号码最长度为11位
 * 2.只能为纯数字
 * 
 * */
$(function(){
	$("#registerPhoneNum").blur(function(){
		
		var phoneNum = $("#registerPhoneNum").val();
		var phoneNumLen = phoneNum.toString().length;
		console.log("phonenum = " + phoneNum + "  isNaN(y) = " + isNaN(phoneNum)  + "  lenth = " + (phoneNum.toString().length));
		console.log("isTelCode = " + isTelCode(phoneNum));
		if(!isTelCode(phoneNum) ){
			//不是电话号码
			$("#registerPhoneNumWarn").text("请输入有效的手机号码sadsa");
			isRegisterPhoneNumValid = false ;
		}
		else{
			//是电话号码
			$("#registerPhoneNumWarn").text("");
			sendCheckPhoneJson();
			isRegisterPhoneNumValid = true ;
		}
		
	});
	
});
/**
 * 向服务端发送手机号，验证该手机号是否已经注册
 * 如果未注册则可以点击获取验证码
 * @returns
 */
function sendCheckPhoneJson(){
	var jsonPhoneNum={"phoneNum":"sda"};

	jsonPhoneNum.phoneNum = $("#registerPhoneNum").val();
	
	console.log("sendCheckPhoneJson  执行开始....  ");
	console.log("PhoneNum = " + jsonPhoneNum.phoneNum);
	  $.ajax({
	        type : "post",
	        url : "checkphonenum",
	        contentType : "application/json;charset=utf-8",
	        //数据格式是json串,传进一个person
	        data : JSON.stringify(jsonPhoneNum),
	        dataType: "json",
	        success:function(data){
	            console.log("UserStatus:" + data.phoneCheckStatus);
	            if(data.phoneCheckStatus == "PHONE_NUM_REGISTER"){
	            	$("#registerPhoneNumWarn").text("该手机号码已经注册，请重新输入");
	            	$("#getPhoneNumValidateBtn").attr("disabled",true);
	            }
	            else if(data.phoneCheckStatus == "PHONE_NUM_NOT_REGISTER"){
	            	$("#registerPhoneNumWarn").text("该手机号码未注册，可以注册，请获取验证码");
	            	$("#getPhoneNumValidateBtn").attr("disabled",false);
	            }
	        }

	    });
	  console.log("sendCheckPhoneJson 执行结束....");
}

/**
 * 获取验证码按键按下处理
 * 按下进行倒计时
 * @returns
 */
$(function(){
	$("#getPhoneNumValidateBtn").click(function(){
		console.log("time display" );
		endTime =  new Date().getTime() + COUNTDOWN_TIME_S * 1000;
		showCountdownTime();
		getPhoneValidateCode();//向服务端获取验证码
		return false;
	});	
});

function showCountdownTime(){
	var startTime = new Date().getTime();
	
	var timeDiff =  endTime - startTime;//ms
	var countDownTime =  Math.floor(timeDiff/1000 + 0.5 );
	console.log("" + countDownTime + "秒后重新获取验证码");
	
	if(countDownTime >  0){
		$("#getPhoneNumValidateBtn").text("" + countDownTime + "秒后重新获取验证码");
		setTimeout("showCountdownTime()",1000);
		getPhoneNumValidateFlag = true;
		//$("#getPhoneNumValidateBtn").attr("disabled",true);
		$("#registerPhoneNumValidate").attr("disabled",false);
	}
	else{
		$("#getPhoneNumValidateBtn").text("获取验证码");
		getPhoneNumValidateFlag = false;
		$("#getPhoneNumValidateBtn").attr("disabled",false);
		$("#registerPhoneNumValidate").attr("disabled",true);
	}
	
}
/**
 * 向服务端发送请求获取验证码
 * @returns
 */
function getPhoneValidateCode(){
	var jsonPhonValidateCode={"phoneNum":""};

	jsonPhonValidateCode.phoneNum = $("#registerPhoneNum").val();
	
	console.log("getPhoneValidateCode  执行开始....  ");
	console.log("PhoneNum = " + jsonPhonValidateCode.phoneNum);
	  $.ajax({
	        type : "post",
	        url : "get-phone-validate-code",
	        contentType : "application/json;charset=utf-8",
	        //数据格式是json串,传进一个person
	        data : JSON.stringify(jsonPhonValidateCode),
	        dataType: "json",
	        success:function(data){
	            console.log("手机验证码:" + data.phoneValidateCode);
	            phoneValidateCode = data.phoneValidateCode;
	        }

	    });
	  console.log("getPhoneValidateCode 执行结束....");
}
/**
 * 输入验证码框处理
 * 
 * @returns
 */
$(function(){
	$("#registerPhoneNumValidate").blur(function(){
		if(getPhoneNumValidateFlag == true){
			console.log("registerPhoneNumValidate = " + $("#registerPhoneNumValidate").val());
			console.log("registerPhoneNumValidate len = " + $("#registerPhoneNumValidate").val().length);
			//说明已经点击获取验证码按钮
			if($("#registerPhoneNumValidate").val().length != registerPhoneNumValidateLength){
				//未输入验证码
				$("#registerPhoneNumValidateWarn").text("请输入正确的验证码");
				isRegisterPhoneNumValidateValid = false;
			}
			else{
				$("#registerPhoneNumValidateWarn").text("");
				isRegisterPhoneNumValidateValid = true;
				
			}
		}
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
	$("#registerPassword").blur(function(){
		var registerPassword = $("#registerPassword").val();
		/*校验密码是否符合要求 */
		if(isAllNum(registerPassword) ){
		
			$("#registerPasswordWarn").text("密码不能为纯数字");
			isRegisterPasswordValid = false;
			return;
		}
		else if(!isLenMaxThan8(registerPassword) ){
			
			$("#registerPasswordWarn").text("密码长度小于8位");
			isRegisterPasswordValid = false;
			return;
		}
		else{
			$("#registerPasswordWarn").text("");
			isRegisterPasswordValid = true;
		}
		/*校验密码强度*/
		var strength = checkPasswordStrength(registerPassword);
		
		switch(strength){
			case 0:$("#PasswordStrength").text("密码强度：较弱");
				break;
			case 1:$("#PasswordStrength").text("密码强度：中等");
				break;
			case 2:$("#PasswordStrength").text("密码强度：强");
				break;
			default:$("#PasswordStrength").text("");
		
		
		}
		
	});//end of $("#registerPassword").blur(function(){
});
/***
 * 密码重复校验
 * @returns
 */
$(function(){
	$("#registerPasswordAgain").blur(function(){
		var registerPasswordAgain = $("#registerPasswordAgain").val();
		var registerPassword = $("#registerPassword").val();
		
		console.log("registerPasswordAgain = " + registerPasswordAgain);
		console.log("registerPasswordAgain len = " + registerPasswordAgain.length);
		if((registerPassword.length != 0) && (registerPasswordAgain != registerPassword)){
			$("#registerPasswordAgainWarn").text("两次输入的密码不一致");
			isRegisterPasswordAgainValid = false;
		}
		else{
			$("#registerPasswordAgainWarn").text("");
			isRegisterPasswordAgainValid = true;
		}
		
	});//end of $("#registerPassword").blur(function(){
});
$(function(){
	$("#registerCheckbox").click(function(){
		console.log("registerCheckbox = " + $("#registerCheckbox").prop("checked"));
		if($("#registerCheckbox").prop("checked") == true){
			//选择
			isRegisterCheckboxValid = true;
			$("#registerCheckboxWarn").text("");
		}
		else{
			//未选择
			isRegisterCheckboxValid = false;
		}
	});
});
/***
 * 提交按钮操作
 * 1.先校验各个输入框是否正确
 * 2.
 * @returns
 */
$(function(){	
	$("#registerSubmit").click(function(){
		console.log("提交注册");
		 
		var publicKey_modulus = $("#registerForm").attr("publicKey-modulus");
		var publicKey_exponent = $("#registerForm").attr("publicKey-exponent");
		var registerPassword = $("#registerPassword").val();
		console.log("publicKey_modulus = " + publicKey_modulus);
		console.log("publicKey_exponent = " + publicKey_exponent);
		console.log("registerPassword = " + registerPassword);
		
		/*需要设置*/
		/*RSAUtils.setMaxDigits(160);
		console.log("获取 publicKey--- ");
		var publicKey = new RSAUtils.getKeyPair(publicKey_exponent, '', publicKey_modulus);		
		console.log("publicKey--- " + publicKey);
		var loginPassword = RSAUtils.encryptedString(publicKey, registerPassword);
		console.log("经过加密后 loginPassword   = " + loginPassword);*/
		console.log("获取 publicKey--- ");
		setMaxDigits(130);
		var publicKey = new RSAKeyPair(publicKey_exponent,"",publicKey_modulus); 		
		if(publicKey == null){
			console.log("publicKey--- null");
		}
		console.log("publicKey--- " + publicKey);
		console.log("开始进行加密......");
		var loginPassword = encryptedString(publicKey, encodeURIComponent(registerPassword));
		console.log("经过加密后 loginPassword   = " + loginPassword);
			
		if(checkAllInput() == true)
		{
			var jsonData={"phoneNum":"","phoneNumValidate":"","loginPassword":"",};
			jsonData.phoneNum = $("#registerPhoneNum").val();
			jsonData.phoneNumValidate = $("#registerPhoneNumValidate").val();
			jsonData.loginPassword = loginPassword;
			
			/*jsonData.phoneNum = "phoneNum";
			jsonData.phoneNumValidate = "phoneNumValidate";
			jsonData.password = "loginPassword";*/
			
			$.ajax({
		        type : "post",
		        url : "register-submit",
		        contentType : "application/json;charset=utf-8",
		        //数据格式是json串,传进一个person
				/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
		        data : JSON.stringify(jsonData),
		        dataType: "json",
		        success:function(data){
		            console.log("手机验证码d:" + data.registerStatus);
		        }
			 });
		}
	});
});
/**
 * 点击提交时校验是否存在表单输入有误的情况
 * 
 * @returns true:正常
 * 		    false:异常
 */

function checkAllInput(){
    var  error = 0;	
	/*校验手机号码是否未填写或填写有误*/
	if( ($("registerPhoneNum").val() != "")
		&& (isRegisterPhoneNumValid == false)){
		$("#registerPhoneNumWarn").text("请输入有效的手机号码");
		error++;
	}
	else{
		$("#registerPhoneNumWarn").text("");
	}
	/*校验验证码*/
	if( ($("registerPhoneNumValidate").val() != "")
			&& (isRegisterPhoneNumValidateValid == false)){
			$("#registerPhoneNumValidateWarn").text("请输入验证码");
			error++;
	}
	else{
		$("#registerPhoneNumValidateWarn").text("");
	}
	/*校验输入密码*/
	if( ($("registerPassword").val() != "")
			&& (isRegisterPasswordValid == false)){
			$("#registerPasswordWarn").text("请输入密码");
			error++;
	}
	else{
		$("#registerPasswordWarn").text("");
	}
	/*校验重复输入密码*/
	if((isRegisterPasswordAgainValid == false)){
			$("#registerPasswordAgainWarn").text("请输入正确的密码");
			error++;
	}
	else{
		$("#registerPasswordAgainWarn").text("");
	}
	
	
	/*校验同意注册协议复选框*/
	if((isRegisterCheckboxValid == false)){
			$("#registerCheckboxWarn").text("请选择注册协议同意复选框===");
			error++;
	}
	else{
		$("#registerCheckboxWarn").text("");

	}
	return (error == 0);
}

/*校验电话码格式 */
function isTelCode(str) {
	/*匹配以1开头，后面十位为纯数字的字符串*/
	var regex = /^1[0-9]{10}$/
	return regex.test(str);
}

/***
 * 判断输入的字符串是否为纯数字
 * @param str
 * @returns true : 输入为纯数字
 * 			false: 输入不是数字
 */
function isAllNum(str){
	var regex = /^\d+$/;
	return regex.test(str);
}
/***
 * 判断输入的字符是否大于8位
 * @param str
 * @returns true : 输入大于8位
 * 			false: 输入小于8位
 */
function isLenMaxThan8(str){
	var regex = /.{8,}/;
	return regex.test(str);
}
function isAllGrapheme(str){
	var regex = /[a-zA-Z]+/;
	return regex.test(str);
}

/***
 * 密码强度校验
 * @returns  0：低等强度密码，单纯字母或者单纯特殊字符
 * 			 1：中等强度密码，数字/字母/特殊字符（\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E）两两组合
 * 			 2：高等强度，包含三种
 */

function checkPasswordStrength(password){
	
	/*字母构成*/
	var regex  = /^[a-zA-Z]+$/;
	var passwordStrength = null;
	
	if(regex.test(password)){
		passwordStrength = 0;
		console.log("全部为纯字母 passwordStrength = " + passwordStrength );
		return passwordStrength;
	}
	/*特殊字符构成*/

	var regex  = /^[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
	if(regex.test(password)){
		passwordStrength = 0;
		console.log("全部为纯特殊字符 passwordStrength = " + passwordStrength );
		return passwordStrength;
	}
	
	var regex  = /^[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
	if(regex.test(password)){
		passwordStrength = 0;
		console.log("全部为纯特殊字符 passwordStrength = " + passwordStrength );
		return passwordStrength;
	}
	/*特殊字符和字母构成*/
	/*(?=.*?[a-z])是肯定型顺序环视，限定当前位置的后面，能匹配.*?[a-z]*/
	var regex1  =  /^(?=.*?[a-zA-Z])(?=.*?[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E])[a-zA-Z\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
	/*特殊数字和字母构成*/
	var regex2  =  /^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]+$/;
	/*特殊数字和和字符构成*/
	var regex3  =  /^(?=.*?[0-9])(?=.*?[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E])[0-9\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
	if(regex1.test(password)){
		passwordStrength = 1;
		console.log("特殊字符和字母构成 passwordStrength = " + passwordStrength );
		return passwordStrength;
	}
	if(regex2.test(password)){
		passwordStrength = 1;
		console.log("特殊数字和字母构成 passwordStrength = " + passwordStrength );
		return passwordStrength;
	}
	if(regex3.test(password)){
		passwordStrength = 1;
		console.log("特殊数字和和字符构成 passwordStrength = " + passwordStrength );
		return passwordStrength;
	}
	
	var regex  =  /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E])[a-zA-Z0-9\x21-\x2F\x3A-\x40\x5B-\x60\x7B-\x7E]+$/;
	if(regex.test(password)){
		passwordStrength = 2;
		console.log("特殊数字和和字符和字母构成 passwordStrength = " + passwordStrength );
		return passwordStrength;
	}
	return null;
}
