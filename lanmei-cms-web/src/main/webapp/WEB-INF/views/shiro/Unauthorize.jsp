<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城-后台管理</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">

	</head>
	
	<body>
	
		<div id="title">
			<span >蓝莓商城-后台管理</span>
		</div>
		
		
		
	    <div id="UnauthorizePage">
	    	
	    	对不起，您没有权限进行个操作！
	    	
	    </div> 
	    
	</body>
	
	<style type="text/css">
    	body{
    	   background-image:url(${contextPathOfStatic}/img/homepage/background.jpeg); 
    	}
   
    	
    	#UnauthorizePage{
    		background-image:url(${contextPathOfStatic}/img/homepage/login.jpeg); 
    		display:inline;
    		height:200px;
			width:500px;
			position:absolute;
		    left:28%;
		    top:38%;
		    
    	}
    	#title{
    		position:relative;
    		text-align:center;
		    top:26%;
		    font-size:40px;
    	}
    	
 	</style>
</html>