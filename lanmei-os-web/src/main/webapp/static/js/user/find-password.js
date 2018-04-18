/**
 * 帐号填写页面--下一步
 * 验证手机号码或者邮箱是否已经注册 
 * 请求服务器发送验证码到手机或者邮件
 * 跳转到下一个页面--验证码验证
 * @returns
 */
$(function(){
	$("#nextBtnToValidateForm").click(function(){
		$("#inputNameForm").hide();
		$("#validateForm").show();
		$("#findPasswordProgress-bar").css("width","50%");
	});
	
});
/**
 * 验证码页面 --上一步 和下一步按钮处理
 * 上一步：返回帐号填写页面
 * 下一步：验证验证码是否正确
 *        跳转到修改密码页面
 * @returns
 */
$(function(){
	//上一步
	$("#preBtnToInputNameForm").click(function(){
		$("#inputNameForm").show();
		$("#validateForm").hide();
		$("#findPasswordProgress-bar").css("width","25%");
	});
	//下一步
	$("#nextBtnToResetPasswordForm").click(function(){
		$("#validateForm").hide();
		$("#resetPasswordForm").show();
		$("#findPasswordProgress-bar").css("width","80%");
	});
});
/**
 * 修改密码页面 --上一步 和下一步按钮处理
 * 上一步：返回验证码填写页面
 * 下一步：校验密码是否符合规范
 * 		 发送设置的密码到服务端
 *       跳转到密码设置成功页面
 * @returns
 */
$(function(){
	//上一步
	$("#preBtnToValidateForm").click(function(){
		$("#validateForm").show();
		$("#resetPasswordForm").hide();
		$("#findPasswordProgress-bar").css("width","80%");
	});
	//下一步
	$("#nextBtnToResetSuccessForm").click(function(){
		$("#resetPasswordForm").hide();
		$("#resetSuccessForm").show();
		$("#findPasswordProgress-bar").css("width","100%");
	});
});
/**
 * 修改成功页面 --上一步 和下一步按钮处理
 * 重新设置：返回登录名填写页面
 * 登录：跳转到登录页面
 * @returns
 */
$(function(){
	//返回重新设置
	$("#returnBtnToInputNameForm").click(function(){
		$("#resetSuccessForm").hide();
		$("#inputNameForm").show();
		$("#findPasswordProgress-bar").css("width","25%");
	});
	//跳转到登录界面
	$("#successBtnTologin").click(function(){
		$("#inputNameForm").hide();
		$("#validateForm").show();
		$("#findPasswordProgress-bar").css("width","50%");
	});
});