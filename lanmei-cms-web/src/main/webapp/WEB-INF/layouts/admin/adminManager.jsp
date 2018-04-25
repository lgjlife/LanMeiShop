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
 	    	
 	    <style>
 	    	/* #adminManagerTr{
 	    		height:40px;
 	    	}
 	    	input{
 	    	  	 border-style:solid;
 	    		 border-color:#90EE90;
 	    	}
 	    	#btn{
 	    		border-style:solid;
 	    	    border-color:#90EE90;
 	    	    width:100px;
 	    	    background-color:#00FFFF;
 	    	    position:absolute;
				left:40px;	    	    
 	    	} */
 	    	
 	    </style>
		<script src="${contextPathOfStatic}/js/admin/admin-manager.js"></script>
	</head>
	
	<body>
		<div>
		  	 新增管理员账户
		  </div>
		  <form>
		  	<table>		  	    
		  		<tr class="adminManagerTr">
		  			<td>姓名</td>
		  			<td><input type="text"/>
		  		</tr>
		  		<tr class="adminManagerTr">
		  			<td>邀请码</td>
		  			<td><input id="invitationCode" type="text" disabled/></td>
		  		</tr>
		  		<tr class="adminManagerTr">
		  			<td>密码</td>
		  			<td><input type="text" value="123456" disabled>默认密码</td>
		  		</tr>
		  		<tr>
		  			<td>邮箱</td>
		  			<td><input type="text"></td>
		  		</tr>
		  		<tr class="adminManagerTr">
		  			<td><button class="adminbtn" id="addAdminBtn">确认</td>
		  			
		  			<td><button  class="adminbtn" type="button" id="invitationCodeBtn" style="left:160px;">生成邀请码</button></td>
		  		</tr>
		  	</table>
		  </form>	
</body>
</html>