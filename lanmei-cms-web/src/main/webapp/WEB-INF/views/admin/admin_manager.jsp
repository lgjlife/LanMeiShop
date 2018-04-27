<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<script src="${contextPathOfStatic}/js/common/regex.js"></script>
	<script src="${contextPathOfStatic}/js/admin/admin_manager.js"></script>

	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/homepage/admin_manager.css">
	<body >
		
		<div id="adminListItemPage" class="adminManagerPageDiplay">
			<div>
		  	 
		  	 	<caption>管理员列表</caption>
		  	 	<button id="getAdminListBtn">获取管理员列表</button>
		  	 	<ul class="pagination pagination-sm">
				  <li class="page-item" id="toBeforPage"><a class="page-link" href="#">Previous</a></li>
				  <!-- <li class="page-item"><a class="page-link" href="#">1</a></li>
				  <li class="page-item"><a class="page-link" href="#">2</a></li>
				  <li class="page-item"><a class="page-link" href="#">3</a></li> -->
				  <li class="page-item" id="toNextPage"><a class="page-link" href="#">Next</a></li>
				</ul>				
		  	 	<table class="table table-bordered">
		  	 		<thead>
		  	 			<tr>
		  	 				<td>姓名</td>
		  	 				<td>工号</td>
		  	 				<td>性别</td>
		  	 				<td>部门</td>
		  	 				<td>状态</td>
		  	 				<td>创建时间</td>
		  	 			</tr>		  	 			
		  	 		</thead>
		  	 		<tbody id="tbodyList">			
		  	 			
		  	 		</tbody>
		  	 		
		  	 	</table>
		  	 	
		  	 	
		  	 </div>
		</div><!-- <div id="adminListItemPage" class="adminManagerPageDiplay"> -->
		
		<div id="addAdminItemPage" class="adminManagerPageDiplay">
			 <div>
		  	 新增管理员账户
		  	 </div>
			  <form>
			  <table>		  	    
			  		<tr class="adminManagerTr">
			  			<td>姓名</td>
			  			<td><input type="text" id="actualName"/>
			  		</tr>
			  		<tr class="adminManagerTr">
			  			<td>工号</td>
			  			<td><input type="text" id="jobNum"/>
			  		</tr>
			  		<tr class="adminManagerTr">
			  			<td>邀请码</td>
			  			<td><input id="invitationCode" type="text" disabled/></td>
			  		</tr>
			  		<tr class="adminManagerTr">
			  			<td>密码</td>
			  			<td><input type="text" value="zxcvbnm123" disabled id="adminPassword">默认密码</td>
			  		</tr>
			  		<tr>
			  			<td>邮箱</td>
			  			<td><input id="email" type="text"></td>
			  		</tr>
			  		<tr class="adminManagerTr">
			  			<td><button type="button" id="addAdminBtn">确认</td>
			  			<td><button type="button" id="invitationCodeBtn" style="left:160px;">生成邀请码</button></td>
			  		</tr>			  		
			  	</table>
			  	<span id="addAdminWarn" style="color:red;"></span>
			  </form>	
		</div>
		<!-- 角色管理 -->
		<div id="roleItemPage" class="adminManagerPageDiplay">
			<div>
		  	 角色管理
		  	 </div>
		</div>
		<!-- 权限管理 -->
		<div id="authorityItemPage" class="adminManagerPageDiplay">
			<div>
		  	 权限管理
		  	 </div>
		</div>
	</body>
</html>


