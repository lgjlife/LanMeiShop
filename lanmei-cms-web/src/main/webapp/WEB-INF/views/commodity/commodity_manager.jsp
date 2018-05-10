<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>

<!DOCTYPE html>
<html>

 <script src="${contextPathOfStatic}/jquery/jquery-3.3.1.js"></script> 
<!-- bootstrap 文件 -->
 <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/bootstrap/bootstrap.css">
<script src="${contextPathOfStatic}/bootstrap/bootstrap.js"></script> 

	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/commodity/commodity_manager.css">
	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/common/common.css">
	<script src="${contextPathOfStatic}/js/commodity/menu_display_switch.js"></script>
	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/bootstrap-treeview/bootstrap-treeview.min.css">
	<script src="${contextPathOfStatic}/jquery/jquery-3.3.1.js"></script>
	<script src="${contextPathOfStatic}/bootstrap-treeview/bootstrap-treeview.min.js"></script>
	

	
	　　<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <link rel="stylesheet" href="${contextPathOfStatic}/zTree/css/demo.css" type="text/css">
	  <link rel="stylesheet" href="${contextPathOfStatic}/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.all.js"></script>
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.core.js"></script>
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.exedit.js"></script>
	  <script src="${contextPathOfStatic}/js/commodity/categoryManager.js"></script>

	  <style type="text/css">
		.ztree li span.button.add 
		{margin-left:2px;
		 	margin-right: -1px;
		  	background-position:-144px 0; 
		    vertical-align:top;
		   *vertical-align:middle
		 }
		</style>
	<body >
		
		<div class="pageTitle">
		  	 <caption>商品管理页面 </caption>
		</div>
		<!-- 类别管理 -->
		<div id="categoryManagerItemPage" class="commopdityManagerPageDiplay">
			<div>
				类别管理
			    
			    <div class="zTreeDemoBackground1 left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
				<div>
					 <span>新增类别名称</span><input type="text" id="mainCategoryInput"/></span>
					 <button id="addNewNode">增加大类</button>
				</div>
			   
			</div>
			<!-- 新增类别 -->
			<div>
				新增类别
			</div>
		</div><!-- <div id="categoryManagerItemPage" class="adminManagerPageDiplay"> -->
		<!-- 查询商品 -->
		<div id="commodityQueryItemPage" class="commopdityManagerPageDiplay">
			查询管理
		</div><!-- <div id="commodityQueryItemPage" class="adminManagerPageDiplay"> -->
		<!-- 增添商品 -->
		<div id="addCommodityItemPage" class="commopdityManagerPageDiplay">
			增添管理
		</div><!-- <div id="addCommodityItemPage" class="adminManagerPageDiplay"> -->
		<!-- 删除商品 -->
		<div id="deleteCommodityItemPage" class="commopdityManagerPageDiplay">
			删除管理
		</div><!-- <div id="deleteCommodityItemPage" class="adminManagerPageDiplay"> -->
		
	</body>
</html>