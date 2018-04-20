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
 	    
		<script src="${contextPathOfStatic}/js/user/register.js"></script>
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
				<form id="loginForm" >
					<div class="form-group">
						<label  for="loginName" >登录帐号</label>
						<input type="text" id="loginName"  class="form-control" style="width:60%" 
						placeholder="电话号码/邮箱/用户名"/>
						<span id="registerPhoneNumWarn" class="formWarn" ></span>						
					</div>
					<div>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="option1" checked> 手机号
						</label>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadios4"  value="option2"> 邮箱
						</label>
						<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadios4"  value="option2"> 用户名
						</label>
					</div>
					<div class="form-group">
						<label  for="registerPassword" >密码</label>
						<br>
						<input type="text" id="registerPassword"  class="form-control" style="width:60%;display:inline"  placeholder="设置密码" value=""/>
					</div>
					<a>忘记密码？</a><a  href="${projectPath}/user-login/find-password" target="_blank" 
					style="color=#EEB422">点击找回密码</a>
					<br>
					<button type="submit" id="loginSubmit">登录</button>
				</form>
			</div>
			<div class="col-4">				
			</div>
		</div><!--<div class="row">  -->
	</div><!-- end of <div class="container-fluid">  -->
	<jsp:include page="/WEB-INF/layouts/common/footer.jsp" />
</body>
</html>