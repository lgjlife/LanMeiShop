/***
 * 提交按钮操作
 * 1.先校验各个输入框是否正确
 * 2.
 * @returns
 */
$(function(){	
	$("#loginSubmit").click(function(){
		console.log("提交登录");
		 
		var publicKey_modulus = $("#login-form").attr("publicKey-modulus");
		var publicKey_exponent = $("#login-form").attr("publicKey-exponent");
		
		var loginJobNum = $("#loginJobNum").val();
		var loginInvitationCode = $("#loginInvitationCode").val();
		var loginPassword = $("#loginPassword").val();
		var loginValidateCode = $("#loginValidateCode").val();
		console.log("publicKey_modulus = " + publicKey_modulus);
		console.log("publicKey_exponent = " + publicKey_exponent);
		console.log("loginJobNum = " + loginJobNum);
		console.log("loginInvitationCode = " + loginInvitationCode);
		console.log("loginPassword = " + loginPassword);
		console.log("loginValidateCode = " + loginValidateCode);
		
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
			
		if(checkAllInput() == true)
		{
			var jsonData={"loginJobNum":"","loginInvitationCode":"","loginPassword":"","loginValidateCode":""};
			jsonData.loginJobNum = loginJobNum;
			jsonData.loginInvitationCode = loginInvitationCode;
			jsonData.loginPassword = loginPassword;
			jsonData.loginValidateCode = loginValidateCode;
			
			/*jsonData.phoneNum = "phoneNum";
			jsonData.phoneNumValidate = "phoneNumValidate";
			jsonData.password = "loginPassword";*/
			console.log("url == login/in")
			$.ajax({
		        type : "post",
		        url : "login/in",
		        contentType : "application/json;charset=utf-8",
		        data : JSON.stringify(jsonData),
		        dataType: "json",
		        success:function(data){

		        	console.log("loginStatus = " + data.loginStatus)
		            if(data.loginStatus == "VALIDATE_CODE_ERR"){
		            	$("#loginValidateCodeWarn").text("验证码有误");
		            }
		            else if(data.loginStatus == "LOGIN_SUCCESS"){
		            	$(".loginPage").hide();
		        		$("#loginSuccessPage").show();
		            }
		            else if(data.loginStatus == "LOGIN_FAIL"){
		            	$("#loginWarn").text("用户验证失败，请输入正确的登陆名称或者密码！");
		            }
		            else if(data.loginStatus == "UNKNOWN_ACCOUNT"){
		            	$("#loginWarn").text("账户不存在");
		            }
		            else if(data.loginStatus == "PASSWORD_ERROR_TOO_MANY"){
		            	$("#loginWarn").text("密码错误次数过多！");
		            }
		        }
			 });
		}
	});
});

function checkAllInput(){
	if(($("#loginJobNum").val().length == 0) 
			|| ($("#loginInvitationCode").val().length == 0)
			|| ($("#loginPassword").val().length == 0)
			|| ($("#loginValidateCode").val().length == 0)){
		
		$("#loginWarn").text("输入有误");
		return false;
	}
	$("#loginWarn").text("");
	return true;
}