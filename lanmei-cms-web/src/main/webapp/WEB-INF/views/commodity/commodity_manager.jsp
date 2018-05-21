<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>

<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<!-- jquery -->
 	<script src="${contextPathOfStatic}/jquery/jquery-3.3.1.js"></script>
	<!-- bootstrap 文件 -->
	 <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/bootstrap/bootstrap.css">
	<script src="${contextPathOfStatic}/bootstrap/bootstrap.js"></script> 

	  <!-- zTree -->
	  <link rel="stylesheet" href="${contextPathOfStatic}/zTree/css/demo.css" type="text/css">
	  <link rel="stylesheet" href="${contextPathOfStatic}/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.all.js"></script>
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.core.js"></script>
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="${contextPathOfStatic}/zTree/js/jquery.ztree.exedit.js"></script>
	  <!-- 样式文件 -->
	  <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/commodity/commodity.manager.css">
	  <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/commodity/commodity.add.css">
	  <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/common/common.css">
	  <!-- 类别管理 -->
	  <script src="${contextPathOfStatic}/js/commodity/commodity.category.manager.js"></script>
	  <!-- 增加商品 -->
	  <script src="${contextPathOfStatic}/js/commodity/commodity.add.js"></script>	  
	  <!-- 切换模块显示 -->
	  <script src="${contextPathOfStatic}/js/commodity/commodity.menu.display.switch.js"></script>
	<body >
		
		<div class="pageTitle">
		  	 <h1>商品管理页面 </h1>		  	
		</div>
		<!-- --------------------------------类别管理 ------------------------------------------->
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
		</div><!-- <div id="categoryManagerItemPage" class="adminManagerPageDiplay"> -->
		<!----------------------------------- 查询商品 ------------------------------>
		<div id="commodityQueryItemPage" class="commopdityManagerPageDiplay">
			 <jsp:include page="/WEB-INF/views/commodity/commodity_edit.jsp" /> 
		</div><!-- <div id="commodityQueryItemPage" class="adminManagerPageDiplay"> -->
		<!------------------------------------ 增添商品------------------------------------ -->
		<div id="addCommodityItemPage" class="commopdityManagerPageDiplay">			
			<h3>选择类别</h3>
			
			<br>
			<for  m action="">
			<!--产品分类设计
			大类/一级分类/二级分类/三级细分－品牌/产品名称
			大类/一级分类/二级分类/三级细分－品牌/　这些分类在分类管理那里添加
			产品名称　在添加产品时自定义
			这里只需要把三级分类的ｉd　发送给服务端，大类/一级分类不需要
			 -->
					<!-- 选择大类 -->
				<div class="form-group">
					<label  for="btn-group" >选择大类</label>　
					<input type="text" list="datalist0" class="addCommodityInput" id="bigCategoryInput">
					<datalist id="datalist0">
				    </datalist>
				    <br>
				</div>
				<!-- 一级细分 -->
				<div class="form-group" >
					<label  for="btn-group" >一级细分</label>　
					<input type="text" list="datalist1" class="addCommodityInput" id="firstCategoryInput">	
					<datalist id="datalist1">
				    </datalist>
				    <br>
				</div>			    
				<!-- 二级细分 -->
				<div class="form-group" >
					<label  for="btn-group" >二级细分</label>　	
				    <input type="text" list="datalist2" class="addCommodityInput" id="secondCategoryInput">	
					<datalist id="datalist2">
				    </datalist>　
				    <br>
				</div>	
				<!-- 三级细分 品牌 -->
				<div class="form-group" >
					<label  for="btn-group" >三级细分</label>　	
				    <input type="text" list="datalist3" class="addCommodityInput" id="threeCategoryInput">	
					<datalist id="datalist3">
				    </datalist>　
				    <br>
				</div>			
		
			     <!-- 名称 -->
			    <div class="form-group">
			    	<label  for="btn-group" >名　　称</label>　	
					<!-- <input type="text" class="addCommodityInput" id="productNameInput"> -->
					<input type="text" class="addCommodityInput" id="productNameInput">
					<span id="productNameInputWarn" ></span>
			    </div>
			    
			     <!-- 显示标题 -->
			    <div class="form-group">
			    	<label  for="btn-group"  >显示标题</label>　	
					<input type="text" class="addCommodityInput" id="titleInput">
			    </div>			    
			</form>
			<!-------------------------------------- 图片上传--------------------------- -->
			<form id="imgUploadForm" enctype="multipart/form-data"  style="display:none;" >
			    <div>
			    	<span>图片1:<input type="file" name="files" accept="image/gif, image/jpeg"></span>
			    </div>
			    <div>
			    	<span>图片2:<input type="file" name="files" accept="image/gif, image/jpeg"></span> 
			    </div>
			    <div>
			    	<span>图片3:<input type="file" name="files" accept="image/gif, image/jpeg"></span> 
			    </div>
			    <div>
			    	<span>图片4:<input type="file" name="files" accept="image/gif, image/jpeg"></span> 
			    </div>
			    <div>
			    	<span>图片5:<input type="file" name="files" accept="image/gif, image/jpeg"></span> 
			    </div>				
			</form>			
			<button type="button" id="imgUnloadBtn" style="display:none;">上传</button>
		
			<!-- 提交按钮 -->
			<div>
				<button type="button" id="addCommoditySubmitBtn">提交</button>
				<span id="addCommoditySubmitWarn"></span>
			</div>
		
			    		

		
		</div><!-- <div id="addCommodityItemPage" class="adminManagerPageDiplay"> -->
		<!-- ------------------------------删除商品----------------------------------- -->
		<div id="deleteCommodityItemPage" class="commopdityManagerPageDiplay">
			删除管理
		</div><!-- <div id="deleteCommodityItemPage" class="adminManagerPageDiplay"> -->
		
	</body>
</html>