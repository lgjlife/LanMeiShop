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
 	    
 	    <script src="${contextPathOfStatic}/js/security/Barrett.js"></script>
 	    <script src="${contextPathOfStatic}/js/security/BigInt.js"></script>
 	    <script src="${contextPathOfStatic}/js/security/RSA.js"></script>
 	    <script src="${contextPathOfStatic}/js/common/regex.js"></script>	
 	    <script src="${contextPathOfStatic}/js/user/register.js"></script>
		<script src="${contextPathOfStatic}/js/user/login.js"></script>
		<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/user/register.css">
		
 
	</head>
	
<body>
  
	 <jsp:include page="/WEB-INF/layouts/common/header-nav.jsp" />
	   <br>
	   <br>
	   <br>
	   <br>
	   <br>
	<h1 align="center">蓝莓商城</h1>
	<h2 align="center">欢迎登录</h2>
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
				<form id="loginForm" 
					publicKey-modulus="${modulus}" 
				    publicKey-exponent="${exponent}"
				    style="display:block;">
					<div class="form-group">
						<label  for="loginName" >登录帐号</label>
						<input type="text" id="loginName"  class="form-control" style="width:60%" 
						placeholder="电话号码/邮箱/用户名"/>
						<span id="loginNameWarn" class="formWarn" ></span>						
					</div>
					<div>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadiosPhone" value="option1" > 手机号
						</label>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadiosEmail"  value="option2"> 邮箱
						</label>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadiosUserName"  value="option3"> 用户名
						</label>
					</div>
					<div class="form-group">
						<label  for="loginPassword" >密码</label>
						<br>
						<input type="text" id="loginPassword"  class="form-control" style="width:60%;display:inline"  placeholder="请输入密码" value=""/>
					</div>
					<div class="form-group">
						<label  for="loginValidateCode" >验证码</label>
						<br>
						<input type="text" id="logginVerificationCode"  class="form-control" style="width:60%;display:inline"  placeholder="验证码" value=""/>
						<img id="vcode" title="点击更换" alt="验证图片" 
						src="${projectPath}/kaptcha" onclick="this.src='${projectPath}/kaptcha?d=+Math.random()'"
						style="vertical-align: middle;"  height="30" width="80">
						<span id="loginValidateCodeWarn" class="formWarn" ></span>
					</div>
					<a>忘记密码？</a><a  href="${projectPath}/user-login/find-password" target="_blank" 
					style="color=#EEB422">点击找回密码</a>
					<br>
					<button type="button" id="loginSubmit">登录</button>
					<span id="loginWarn" class="formWarn" ></span>
				</form>
				
				<!-- 登陆成功现实 -->
				<span  id="loginSuccessDisplay" style="display:none; font-size:40px;  position:relative; left:15%;" >
					恭喜您！登陆成功。
					<a href="${projectPath}" style="color:#DC143C;">点击返回主界面！</a>
				</span>
			</div>
			<div class="col-4">				
			</div>
		</div><!--<div class="row">  -->
	</div><!-- end of <div class="container-fluid">  -->
	<jsp:include page="/WEB-INF/layouts/common/footer.jsp" />
</body>
</html>