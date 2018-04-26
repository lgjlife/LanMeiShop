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
 	    <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/homepage/homepage.css">
 	    	
 	    <script src="${contextPathOfStatic}/js/admin/homepage.js"></script>
		<script src="${contextPathOfStatic}/js/admin/homepage_display_control.js"></script>
	</head>
	
	<body>
		<div id="title">
			<div id="titleName">
			   <span>蓝莓商城后台管理系统</span>
			</div>
			<div>
				
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-2 menu" >	
					<!-- 人员管理 -->
					<div >
						<div class="menuBtn">
							<button type="button" class="btn btn-primary" id="adminManagerBtn">人员管理</button>
						</div>						
						<div id="adminManager" class="collapse">
							<div>
								<span class="detaileList" id="adminListItem">管理员列表</span>		
								<span class="detaileList" id="addAdminItem">新增管理员</span>
								<span class="detaileList" id="roleItem">角色管理</span>			
								<span class="detaileList" id="authorityItem">权限管理</span>								
							</div>
						</div>
					</div>
					<!-- 个人信息-->
					<div >
						<div class="menuBtn">
							<button type="button" class="btn btn-primary" id="adminMessageBtn">个人信息</button>
						</div>						
						<div id="adminMessage" class="collapse">
							<div>
								<span class="detaileList" id="adminMesgItem">个人信息</span>
								<span class="detaileList" id="modifyMesgItem">修改信息</span>			
								<span class="detaileList" id="adminRoleItem">个人角色</span>		
								<span class="detaileList" id="adminAuthorityItem">个人权限</span>	
								<span class="detaileList" id="loginLogItem">登录记录</span>
								<span class="detaileList" id="workLogItem">操作记录</span>								
							</div>
						</div>
					</div>
					<!-- 会员管理-->
					<div>
						<div class="menuBtn">
							<button type="button" class="btn btn-primary" id="userManagerBtn">会员管理</button>
						</div>
						<div id="userManager" class="collapse">
							<span class="detaileList" id="userQueryItem">查询会员</span>	
							<span class="detaileList" id="userOnlineItem">在线会员</span>	
							<span class="detaileList" id="userOfflineItem">离线会员</span>	
						</div>
					</div>
					<!--商品管理-->
					<div>
						<div class="menuBtn">
							<button type="button" class="btn btn-primary" id="commodityManagerBtn">商品管理</button>
						</div>
						
						<div id="commodityManager" class="collapse">
							<span class="detaileList" id="categoryManagerItem">类别管理</span>	
							<span class="detaileList" id="commodityQueryItem">查询商品</span>	
							<span class="detaileList" id="addCommodityItem">增添商品</span>		
							<span class="detaileList" id="deleteCommodityItem">删除商品</span>	
						</div>
					</div>
					<!--数据管理-->
					<div>
						<div class="menuBtn">
							<button type="button" class="btn btn-primary" id="dataManagerBtn">数据管理</button>
						</div>
						
						<div id="dataManager" class="collapse">
							<span class="detaileList" id="allUserMesgItem">会员信息</span>	
							<span class="detaileList" id="CommodityIMesgItem">商品信息</span>	
						</div>
					</div>					
					<!--设计思路-->
					<div>
						<div class="menuBtn">
							<button type="button" class="btn btn-primary" id="designerBtn">设计思路</button>
						</div>
						
						<div id="designer" class="collapse">
							<span class="detaileList" id="adminManagerItem">人员管理</span>	
							<span class="detaileList" id="userMessageItem">个人信息</span>	
							<span class="detaileList" id="userManagerItem">会员管理</span>
							<span class="detaileList" id="commodityManagerItem">商品管理</span>		
							<span class="detaileList" id="dataManagerItem">数据信息</span>							
						</div>
					</div>
					
				</div>
				<div class="col-10">	
					<div id="adminManagerDisplay" class="pageDisplayCtrl" style="display:none;" >
						<jsp:include page="/WEB-INF/views/homepage/admin_manager.jsp" />
					</div> 						
					<div id="adminMessageDisplay" class="pageDisplayCtrl" style="display:none;" >
						<jsp:include page="/WEB-INF/views/homepage/admin_message.jsp" /> 
					</div>	
					<div id="userMessageDisplay" class="pageDisplayCtrl" style="display:none;" >
						<jsp:include page="/WEB-INF/views/homepage/user_message.jsp" /> 
					</div>	
					<div id="commodityManagerDisplay" class="pageDisplayCtrl" style="display:none;" >
						<jsp:include page="/WEB-INF/views/homepage/commodity_manager.jsp" /> 
					</div>	
					<div id="dataManagerDisplay" class="pageDisplayCtrl" style="display:none;" >
						<jsp:include page="/WEB-INF/views/homepage/data_manager.jsp" /> 
					</div>	
					<div id="designerDisplay" class="pageDisplayCtrl" style="display:none;" >
						<jsp:include page="/WEB-INF/views/homepage/designer.jsp" />  
					</div>
				</div><!-- end of <div class="col-10">	 -->
			</div>
		</div>	
	</body>

 	</style>
</html>