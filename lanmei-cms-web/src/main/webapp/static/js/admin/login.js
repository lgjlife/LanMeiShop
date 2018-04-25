
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
		var loginValidateCode = $("#loginValidateCode").val();
		console.log("publicKey_modulus = " + publicKey_modulus);
		console.log("publicKey_exponent = " + publicKey_exponent);
		console.log("loginName = " + loginName);
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
			var jsonData={"loginName":"","loginPassword":"","loginValidateCode":"",};
			jsonData.loginName = loginName;
			jsonData.loginPassword = loginPassword;
			jsonData.loginValidateCode = loginValidateCode;
			
			/*jsonData.phoneNum = "phoneNum";
			jsonData.phoneNumValidate = "phoneNumValidate";
			jsonData.password = "loginPassword";*/
			
			$.ajax({
		        type : "post",
		        url : "login",
		        contentType : "application/json;charset=utf-8",
		        //数据格式是json串,传进一个person
				/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
		        data : JSON.stringify(jsonData),
		        dataType: "json",
		        success:function(data){

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

function checkAllInput(){
	if(($("#loginName").val().length == 0) || ($("#loginPassword").val().length == 0) || ($("#loginValidateCode").val().length == 0)){
		
		$("#loginWarn").text("输入有误");
		return false;
	}
	$("#loginWarn").text("");
	return true;
}