$(function(){
	$("#loginName").blur(function(){
		var loginName = $("#loginName").val(); 
		console.log("loginName = " + loginName);
		
		if( loginName.length != 0){
			var loginState = checkLoginState();
			loginNameCheck(loginState,loginName);
		}
		else{
			$("#loginNameWarn").text("");
		}
	});
});

function loginNameCheck(loginState,loginName){
	//0： 未选中任何一个
	console.log("loginState = " + loginState + "  loginName = " + loginName);
	if(loginState == 0)
	{
		$("#loginNameWarn").text("请选择登录帐号格式");
		
	}
	//1：选中 optionsRadiosPhone
	//校验是否是是手机号
	else if(loginState == 1){
		if(isTelCode(loginName) == false){
			$("#loginNameWarn").text("请输入正确的电话号码");
		}
		else{
			$("#loginNameWarn").text("");
		}
	}
	//2：选中 optionsRadiosEmail
	//校验是否是是邮箱
	else if(loginState == 2){
		if(isEmail(loginName) == false){
			$("#loginNameWarn").text("请输入正确的邮箱");
		}
		else{
			$("#loginNameWarn").text("");
		}
	}
	//3：选中 optionsRadiosUserName
	//以上都不是就是用户名
	else {
		$("#loginNameWarn").text("");
	}
}
/*
 * 校验当前选中的单选框
 * @return   0： 未选中任何一个
 * 			 1：选中 optionsRadiosPhone
 * 			 2：选中 optionsRadiosEmail
 *           3：选中 optionsRadiosUserName
 * */
function checkLoginState(){
	//if($)
	console.log("radio = " + $("#optionsRadiosPhone").prop("checked"));
	
	var loginState;
	
	if($("#optionsRadiosPhone").prop("checked")  == true){
		loginState = 1;
	}
	else if($("#optionsRadiosEmail").prop("checked")  == true){
		loginState = 2;
	}
	else if($("#optionsRadiosUserName").prop("checked")  == true){
		loginState = 3;
	}
	else {
		loginState = 0;
	}	
	return loginState;
}
/**
 *登录帐号方式选择事件
 * @returns
 */
$(function(){
	$(":radio[name='optionsRadiosinline']").change(function(){
		console.log("复选框改变");
		var loginState = checkLoginState();
		var loginName = $("#loginName").val(); 
		loginNameCheck(loginState,loginName);
	});
});

/***
 * 提交按钮操作
 * 1.先校验各个输入框是否正确
 * 2.
 * @returns
 */
$(function(){	
	$("#loginSubmit").click(function(){
		console.log("提交登录");
		 
		var publicKey_modulus = $("#loginForm").attr("publicKey-modulus");
		var publicKey_exponent = $("#loginForm").attr("publicKey-exponent");
		
		var loginName = $("#loginName").val();
		var loginPassword = $("#loginPassword").val();
		var logginVerificationCode = $("#logginVerificationCode").val();
		console.log("publicKey_modulus = " + publicKey_modulus);
		console.log("publicKey_exponent = " + publicKey_exponent);
		console.log("loginPassword = " + loginPassword);
		
		/*需要设置*/
		console.log("获取 publicKey--- ");
		setMaxDigits(130);
		var publicKey = new RSAKeyPair(publicKey_exponent,"",publicKey_modulus); 		
		if(publicKey == null){
			console.log("publicKey--- null");
		}
		console.log("publicKey--- " + publicKey);
		console.log("开始进行加密......");
		var loginPassword = encryptedString(publicKey, encodeURIComponent(loginPassword));
		console.log("经过加密后 loginPassword   = " + loginPassword);
			
		//if(checkAllInput() == true)
		{
			var jsonData={"loginName":"","loginPassword":"","logginVerificationCode":"",};
			jsonData.loginName = loginName;
			jsonData.loginPassword = loginPassword;
			jsonData.logginVerificationCode = logginVerificationCode;
			
			/*jsonData.phoneNum = "phoneNum";
			jsonData.phoneNumValidate = "phoneNumValidate";
			jsonData.password = "loginPassword";*/
			
			$.ajax({
		        type : "post",
		        url : "user-login/login",
		        contentType : "application/json;charset=utf-8",
		        //数据格式是json串,传进一个person
				/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
		        data : JSON.stringify(jsonData),
		        dataType: "json",
		        success:function(data){
		            console.log("手机验证码d:" + data.loginStatus);
		            if(data.loginStatus == "VALIDATE_CODE_ERR"){
		            	$("#loginValidateCodeWarn").text("验证码有误");
		            }
		            else if(data.loginStatus == "LOGIN_SUCCESS"){
		            	$("#loginForm").hide();
		        		$("#loginSuccessDisplay").show();
		            }
		            else if(data.loginStatus == "LOGIN_FAIL"){
		            	$("#loginWarn").text("用户验证失败，请输入正确的登陆名称或者密码！");
		            }
		        }
			 });
		}
	});
});