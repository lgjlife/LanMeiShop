<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%-- <jsp:include page="/WEB-INF/layouts/common/base.jsp"/> --%>
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城-用户登录</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">      
 	    
		<script src="${contextPathOfStatic}/js/user/find-password.js"></script>

		
 
	</head>
	
<body>
  
	 <jsp:include page="/WEB-INF/layouts/common/header-nav.jsp" />
	   <br>
	   <br>
	   <br>
	   <br>
	   <br>
	<h1 align="center">蓝莓商城</h1>
	<h2 align="center">找回密码</h2>
	   <br>
	   <br>
	<!-- 横线 -->
	<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
	<!-- 注册表单 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-4">		
			</div>
			<div class="col-4">		
				<!-- 忘记密码 找回 进度条 -->
				<a style="position:absolute;left:5%;">输入账户名</a> <!-- 25% -->
				<a style="position:absolute;left:33%;">验证身份</a>  <!-- 50% -->
			    <a style="position:absolute;left:58%;">重置密码</a>  <!-- 80% -->
				<a style="position:absolute;left:83%;">重置成功</a>  <!-- 100% -->
				<div class="progress" >
				    <div class="progress-bar" id="findPasswordProgress-bar"role="progressbar" aria-valuenow="60" 
				        aria-valuemin="0" aria-valuemax="100" style="width: 25%; background-color:#EE9572">
				        <span class="sr-only">40% 完成</span>
				    </div>
				</div>
				<!--找回密码-输入账户名显示 -->
				<form id="inputNameForm"  style="display:block;">
					<div class="form-group">
						<label  for="loginName" >登录帐号</label>
						<input type="text" id="loginName"  class="form-control" style="width:60%;" 
						placeholder="电话号码/邮箱"/>
						<span id="loginNameWarn" class="formWarn" ></span>						
					</div>
					<div>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="option1" checked> 手机号
						</label>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadios2"  value="option2"> 邮箱
						</label>
					</div>
					<button type="button" id="nextBtnToValidateForm">下一步</button>
				</form>
				<!--找回密码-验证身份显示 -->
				<form id="validateForm"  style="display:none;">
					<div class="form-group">
						<label  for="loginName" >验证码</label>
						<input type="text" id="validateCode"  class="form-control" style="width:60%;" 
						placeholder="请输入手机或者邮箱收到的验证码"/>
						<span id="validateCodeWarn" class="formWarn" ></span>						
					</div>
					<button type="button" id="preBtnToInputNameForm">上一步</button>
					<button type="button" id="nextBtnToResetPasswordForm">下一步</button>
				</form>
				<!--找回密码-重置密码显示 -->
				<form id="resetPasswordForm"  style="display:none;">
					<div class="form-group">
						<label  for="registerPassword" >设置密码</label>
						<br>
						<input type="text" id="registerPassword"  class="form-control" style="width:60%;display:inline"  placeholder="设置密码" "value=""/>
						<span id="PasswordStrength" style="color:#DC143C"></span>
						<br>
						<span id="registerPasswordWarn" class="formWarn"></span>
						<span class="help-block" style="color:#8B795E">由字母、数字、特殊符号构成，不能为纯数字,至少为8位。</span>
					</div>
					<div class="form-group">
						<label  for="registerPasswordAgain" >确认密码</label>
						<input type="text" id="registerPasswordAgain"  class="form-control" style="width:60%" placeholder="重新输入密码" value=""/>
						<span id="registerPasswordAgainWarn" class="formWarn" ></span>
					</div>
					<button type="button" id="preBtnToValidateForm">上一步</button>
					<button type="button" id="nextBtnToResetSuccessForm">下一步</button>
				</form>
				<!--找回密码-重置成功显示 -->
				<form id="resetSuccessForm"  style="display:none;">					
					<h4>密码修改成功！</h4>
					<button type="button" id="returnBtnToInputNameForm">重新设置</button>
					<button type="button" id="successBtnTologin">
						<a href="${projectPath}/user-login">点击登录</a>
					</button>
				</form>
			</div>
			<div class="col-4">				
			</div>
		</div><!--<div class="row">  -->
	</div><!-- end of <div class="container-fluid">  -->
	<jsp:include page="/WEB-INF/layouts/common/footer.jsp" />
</body>
</html>