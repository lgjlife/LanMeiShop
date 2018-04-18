<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%-- <jsp:include page="/WEB-INF/layouts/common/base.jsp"/> --%>
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城-用户注册</title>
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
	<h2 align="center">欢迎注册</h2>
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
				<form id="registerForm">
					<div class="form-group">
						<label  for="phoneNum" >电话号码</label>
						<input type="text" id="registerPhoneNum"  class="form-control" style="width:60%" placeholder="请输入11位电话号码"/>
						<span id="registerPhoneNumWarn" class="formWarn" ></span>						
					</div>
					<div class="form-group">
						<label  for="registerPhoneNumValidate" >
							验证码<button id="getPhoneNumValidateBtn" style="background-color:#EED8AE" disabled>获取验证码</button>
						</label>
						<input type="text" id="registerPhoneNumValidate"  class="form-control" style="width:60%" placeholder="手机验证码" disabled />
						<span id="registerPhoneNumValidateWarn" class="formWarn" ></span>
					</div>
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
					<div class="checkbox">
						<label>
							<input type="checkbox" id="registerCheckbox" >							
							<b>我同意</b><span style="color:red">蓝莓商城的注册协议</span>	
							<br>
							<span id="registerCheckboxWarn" class="formWarn" ></span>						
						</label>
					</div>
					<button type="submit" id="registerSubmit">提交</button>
				</form>
			</div>
			<div class="col-4">				
			</div>
		</div><!--<div class="row">  -->
	</div><!-- end of <div class="container-fluid">  -->
	<jsp:include page="/WEB-INF/layouts/common/footer.jsp" />
</body>
</html>