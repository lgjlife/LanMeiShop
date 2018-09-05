<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城-用户注册</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">	
 	    
 	    <script src="${contextPathOfStatic}/js/homepage/header.js"></script>
 	    	
	</head>
	
	<body>
		<ServiceInfo id="ServiceInfo" 
		user="${user}" userid="${user.userId}">		
		</info>
	    <!-- 创建顶部导航栏 -->
	     <div class="container-fluid">
			<div class="row">
				<div class="col-2">
				</div>
				<div class="col-4" >
					<ul class="nav">
						<li class="nav-item">
							<a class="nav-link" href="/lanmei-os">商城首页</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">商家入驻</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">客户服务</a>
						</li>
				 
				 		<li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			                    深圳
			                </a>
			                <ul class="dropdown-menu">
			                    <li>
			                    	<abbr ><a id="local_province" href="#">广东</a></abbr>
			                    	<span>|</span>
			                    	<abbr ><a id="local_city" href="#">广州</a></abbr>
			                    </li>
			  
			                </ul>
			            </li>
				        
					 </ul>
					
				</div>
	
				<div class="col-4">
					<ul class="nav">
						<li class="nav-item">
							<a id="loginTag" class="nav-link" href="${projectPath}/user-login" target="_blank">登录</a>
						
							<a class="nav-link" href="${projectPath}/user-info" target="_blank" 
							id="LoginedTag" style="display:none;">
							用户(${user.userId})
							</a>
							
							
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${projectPath}/user-register/register" target="_blank">注册</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" style="color: #6c757d;" id="infoLink" >个人中心</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">我的订单</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">购物车</a>
						</li>
				</ul>
				<div class="col-2">
				</div>			
			</div>
		</div>
	</div>
</body>
</html>