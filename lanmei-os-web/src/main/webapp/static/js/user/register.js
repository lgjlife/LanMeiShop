
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
			$("#registerPhoneNumWarn").text("请输入有效的手机号码");
		}
		else{
			$("#registerPhoneNumWarn").text("");
		}
		
	});
	
});
/*校验电话码格式 */
function isTelCode(str) {
	/*匹配以1开头，后面十位为纯数字的字符串*/
	var regex = /^1[0-9]{10}$/
	return regex.test(str);
}

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
		}
		else if(!isLenMaxThan8(registerPassword) ){
			
			$("#registerPasswordWarn").text("密码长度小于8位");
		}
		else{
			$("#registerPasswordWarn").text("");
		}
		/*校验密码强度*/
		checkPasswordStrength(registerPassword);
		
	});//end of $("#registerPassword").blur(function(){
});
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
