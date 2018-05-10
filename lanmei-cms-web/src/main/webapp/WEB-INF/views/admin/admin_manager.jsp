<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>	
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<script src="${contextPathOfStatic}/js/common/regex.js"></script>
	<script src="${contextPathOfStatic}/js/admin/admin_manager.js"></script>

	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/admin/admin_manager.css">
	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/common/common.css">
	<body >
		
		<div id="adminListItemPage" class="adminManagerPageDiplay">
			<div>
		  	 
		  	 	<div class="pageTitle">
		  	 		<caption>管理员列表</caption>
		  	 	</div>
		  	 	
		  	 	<button id="getAdminListBtn">获取管理员列表</button>
		  	 	<button id="PerssionBtn">权限测试0</button>
		  	 	 <shiro:hasRole name="usertest"> 
		  	 		<button id="PerssionBtn">权限测试1</button>
		  	 	 </shiro:hasRole>
		  	 					
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
		  	 		<tbody id="tbodyAdminList">		  	 			
		  	 		</tbody>	  	 		
		  	 	</table>	
		  	 	<ul class="pagination pagination-sm">
				  <li class="page-item" id="toPreviousAdminManagerPage"><a class="page-link" href="#">Previous</a></li>
				  <li class="page-item" id="toNextAdminManagerPage"><a class="page-link" href="#">Next</a></li>
				</ul>	  	 	
		  	 </div>
		</div><!-- <div id="adminListItemPage" class="adminManagerPageDiplay"> -->
		
		<div id="addAdminItemPage" class="adminManagerPageDiplay">
		  	  <div class="pageTitle">
		  	 		<span>新增管理员账户</span>
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
		  	  <div class="pageTitle">
		  	 		<span>角色管理</span>
		  	  </div>
		  	  <div>
		  	         <div class="btnTitle">
		  	         	 <input tyle="text" id="findRoleInput" placeholder="请输入角色名称"/>
		  	         </div>
		  	         <div class="btnTitle">
		  	         	<button id="findRoleBtn">查询</button>
		  	         </div>
		  	         <div class="btnTitle">
		  	  			<button id="addRoleBtn">增加角色</button>
		  	  		 <div class="btnTitle">
		  	         	<button id="deleteRoleBtn">删除角色</button>
		  	         </div>
		  	         <div class="btnTitle">
		  	         	<button id="modifyRoleBtn">修改角色</button>
		  	         </div>		  	  		 
		  	  </div>
		    
	  	 	  <table class="table table-bordered" id="addRoleTable" >
	  	 		<thead>
	  	 			<tr>
	  	 				<td>名称</td>
	  	 				<td>标志</td>
	  	 				<td>状态</td>
	  	 				<td>创建时间</td>
	  	 				<td>创建人</td>
	  	 				<td>更新时间</td>
	  	 				<td>更新人</td>
	  	 				<td>备注</td>
	  	 			</tr>		  	 			
	  	 		</thead>
	  	 		<tbody id="tbodyList">
	  	 			<!-- 列表信息 -->		  	 			
	  	 		</tbody>	
	  	 			  	 		
	  	 	  </table>		  	 	
		  	  <div>
		  	  	 <ul class="pagination pagination-sm" style="position:relative;left:40%">
				    <li class="page-item" id="toPreviousPage"><a class="page-link" href="#">Previous</a></li>
				    <li class="page-item" id="toNextPage"><a class="page-link" href="#">Next</a></li>
		         </ul>
		  	  </div>
		  	  <!-- 新建角色 -->
		  	  <span style="color:#0000FF;">新建角色</span>
		  	  <form>
				  <table>		  	    
				  		<tr class="adminManagerTr">
				  			<td>名称</td>
				  			<td><input type="text" id="actualName"/>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td>标志</td>
				  			<td><input type="text" id="jobNum"/>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td>状态</td>
				  			<td><input id="invitationCode" type="text" disabled/></td>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td>创建时间</td>
				  			<td><input type="text" value="zxcvbnm123" disabled id="adminPassword"></td>
				  		</tr>
				  		<tr>
				  			<td>创建人</td>
				  			<td><input id="email" type="text"></td>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td><button type="button" id="addAdminBtn">确认</td>
				  		</tr>			  		
				  	</table>
				  	<span id="addAdminWarn" style="color:red;"></span>
			  </form>
			  
			  <!-- 更新角色 -->
		  	  <span style="color:#0000FF;">更新角色</span>
		  	  <form>
				  <table>		  	    
				  		<tr class="adminManagerTr">
				  			<td>名称</td>
				  			<td><input type="text" id="actualName"/>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td>标志</td>
				  			<td><input type="text" id="jobNum"/>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td>状态</td>
				  			<td><input id="invitationCode" type="text" disabled/></td>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td>创建时间</td>
				  			<td><input type="text" value="zxcvbnm123" disabled id="adminPassword"></td>
				  		</tr>
				  		<tr>
				  			<td>创建人</td>
				  			<td><input id="email" type="text"></td>
				  		</tr>
				  		<tr class="adminManagerTr">
				  			<td><button type="button" id="addAdminBtn">确认</td>
				  		</tr>			  		
				  	</table>
				  	<span id="addAdminWarn" style="color:red;"></span>
			  </form>	
		  	  
		</div><!-- <div id="adminListItemPage" class="adminManagerPageDiplay"> -->
		<!-- 权限管理 -->
		<div id="authorityItemPage" class="adminManagerPageDiplay">
		  	  <div class="pageTitle">
		  	 		<span>权限管理</span>
		  	  </div>
		</div>
	</body>
</html>


