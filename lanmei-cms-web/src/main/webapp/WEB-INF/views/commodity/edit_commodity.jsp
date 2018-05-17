<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>

<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<%-- 	<!-- jquery -->
 	<script src="${contextPathOfStatic}/jquery/jquery-3.3.1.js"></script>
	<!-- bootstrap 文件 -->
	 <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/bootstrap/bootstrap.css">
	<script src="${contextPathOfStatic}/bootstrap/bootstrap.js"></script>  --%>
<%-- 	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/commodity/commodity.manager.css">
	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/commodity/commodity.add.css"> --%>
	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/common/common.css">

	  <!-- 增加商品 -->
	  <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/commodity/commodity.edit.css">	
	  <script src="${contextPathOfStatic}/js/commodity/commodity.edit.js"></script>
	  <!-- 富文本wangEditor -->
	  <script type="text/javascript" src="${contextPathOfStatic}/wangEditor/wangEditor.js"></script>
	<body >
	
	   						 
		
	 　  　<!-- －－－－－－－－　 商品查询展示列表－－－－－－－－－－－ -->
		<div id="commodityListModel"　 style="display:none;">
			<!-- 查询管理
			怎么做
			１．
			大类＞一级分类＞二级分类＞三级分类（品牌）
			一个个选择分类
			选择大类（手机－１）　寻找1对应的三级分类，获取三级　分类的所有商品
			选择一级分类（手机配件－13）　寻找其对应的三级分类　获取所有商品　展示
			２．通过搜索查找商品　solr实现　暂时不弄
			３．商品如何展示
			　　　图片　４＊Ｘ 
			　　　标题　
			     点击图片进入商品编辑页面
			 -->
			 <div>
			    <ol　class="navigbreadcrumb">
			    	<li class="navigbreadcrumb-item" id="mainCategoryItem">所有</li>
			    	<li class="navigbreadcrumb-item" id="firstCategoryItem">所有</li>
			    	<li class="navigbreadcrumb-item" id="secondCategoryItem">所有</li>
			    	<li class="navigbreadcrumb-item" id="threeCategoryItem">所有</li>
			    </ol>	
		      <!-- 动态添加 -->
		       <div class="categoryItemSelect" id="dynamicProduceTable">
			    	<table class="table table-bordered" id="tTable">
				    	  <thead>
						  </thead>
						  <tbody id="tableTbody"> 				
						  </tbody>
					</table>
		     　  </div>		      
			 </div>
			
		</div><!-- <div id="commodityQueryItemPage" class="adminManagerPageDiplay"> -->
	
		<!-- －－－－－－－－　 商品编辑页面－－－－－－－－－－－ -->
		<!-- 
		包含模块：
			　　１．类别，名称　编辑　标题　，商品详行
	　		　　　　2.　SKU 编辑：　sku 名称　，属性列表　，相关的图片　
	　　　　　　　　　　　　3.属性编辑，所有的属性由用户自定义
	　　　　　　　　　　　　
		 -->
		<div id="commodityEditModel">
			<!-- 基本信息编辑 -->
			<div id="baseInfoEdit">
				<h4>基本信息</h4>
				<button id="baseInfoBtn" class="button">编辑</button>
				
				<!-- 显示状态 -->
				<div id="baseInfoDisplayMode">
				　	<span  class="name">大　　类:</span><br>
					<span class="name">一级分类:</span><br>
					<span class="name">二级分类:</span><br>
					<span class="name">品　　牌:</span><br>
					<span class="name">产品名称:</span><br>
					<span class="name">产品标题:</span><br>
				</div><!-- end of <div id="baseInfoDisplayMode">  -->
				<!-- 编辑状态 -->
				<div id="baseInfoEditMode"  style="display:none">
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
						<label  for="btn-group" >品　　牌</label>　	
					    <input type="text" list="datalist3" class="addCommodityInput" id="threeCategoryInput">	
						<datalist id="datalist3">
					    </datalist>　
					    <br>
					</div>			
			
				     <!-- 名称 -->
				    <div class="form-group">
				    	<label  for="btn-group" >名　　称</label>　	
						<input type="text" class="addCommodityInput" id="productNameInput"　>
						<span id="productNameInputWarn" ></span>
				    </div>
				    
				     <!-- 显示标题 -->
				    <div class="form-group">
				    	<label  for="btn-group"  >显示标题</label>　	
						<input type="text" class="addCommodityInput" id="titleInput">
				    </div>
				    <button id="ｕpdateInfoBtn" class="button">更新</button>
				</div><!-- end of  <div id="baseInfoEditMode">  -->
			</div><!-- end of  <div id="baseInfoEdit">  -->
			 <hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
			<!-- ｉ销售　属性编辑 -->
			<div id="skuInfoEdit">
			
				<h4>销售属性编辑</h4>
				<button id="skuInfoBtn" class="button">编辑</button>
				<!-- 显示状态 -->
				<div id="skuInfoDisplayMode">
					 <table class="table table-bordered" id="commodityList">
				    	  <thead>
				    			<td >编号</td>				    			
				    	  		<td >名称</td>
						   		<td >属性</td>
						   		<td >价格</td>
						   		<td >库存</td>
						   		<td >图片</td>						   		
						  </thead>
						  <tbody id="tableTbody">
						  　　 <tr>
							   		<td >２</td>
							   		<td >颜色</td>
							   		<td >红色</td>
							   		<td >1300</td>
							   		<td >5000</td>
							   		<td >
							   			<input type="radio"  name="imgDisplaySelect"> 显示图片
							   		</td>						   		
						   　	</tr>
						   
						    　　 <tr>
							   		<td >２</td>
							   		<td >颜色</td>
							   		<td >蓝色</td>
							   		<td >1420</td>
							   		<td >4562</td>
							   		<td >
							   			<input type="radio"  name="imgDisplaySelect"> 显示图片
							   		</td>						   		
						   　	</tr>
						　  </tbody>
					</table>
					<div>
						<table class="table table-bordered" id="commodityList">
					    	  <thead>
					    			<td >主图</td>				    			
					    	  		<td >副图</td>
							   		<td >属性图</td>					   		
							  </thead>
							  <tbody id="tableTbody">
							  　　 <tr>
									<td>
									
										<img src="${contextPath}/upload/222.jpg" style="height:150px;width:150px;"">
									</td>	
									<td>
										<img src="${contextPath}/upload/222.jpg" style="height:150px;width:150px;"">
										<img src="${contextPath}/upload/222.jpg" style="height:150px;width:150px;"">
										<br>
										<img src="${contextPath}/upload/222.jpg" style="height:150px;width:150px;"">
										<img src="${contextPath}/upload/222.jpg" style="height:150px;width:150px;"">
									</td>
									<td>
										<img src="${contextPath}/upload/222.jpg" style="height:150px;width:150px;"">
									</td>
											   		
							   　　</tr>						 
							　  </tbody>
						</table>
					</div>
				</div><!-- end of <div id="skuInfoDisplayMode"> -->
				
				<div id="skuInfoEditMode"　 style="display:none">
				　  <!-- 名称 -->
				    <div class="form-group">
				    	<label  for="btn-group" >名　　称</label>　	
						<input type="text" class="addCommodityInput" id="productNameInput"　>
						<span id="productNameInputWarn" ></span>
				    </div>
				    <div>
						<table class="table table-bordered" id="commodityList">
					    	  <thead>
					    			<td >主图</td>				    			
					    	  		<td >副图</td>
							   		<td >属性图</td>					   		
							  </thead>
							  <tbody id="tableTbody">
							  　　 <tr>
									<td>
										<input type="file">
									</td>	
									<td>
										<input type="file">
										<input type="file">
										<input type="file">
										<input type="file">
									</td>
									<td>
										<input type="file">
									</td>
											   		
							   　　</tr>						 
							　  </tbody>
						</table>
					</div>
				</div><!-- end of  <div id="skuInfoDisplayMode">  -->
				
			</div><!-- end of  <div id="skuInfoEdit">  -->
			 <hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
			<!-- 商品属性编辑 -->
			<div id="attrInfoEdit"　>
			
				<h4>商品属性编辑</h4>
				<button id="attrInfoBtn" class="button">编辑</button>	
				<!-- 显示状态 -->
				<div id="attrInfoDisplayMode">
					<table class="table table-bordered" id="commodityList">
					    	  <thead>			   		
							  </thead>
							  <tbody id="tableTbody">
							     <tr><td colspan="2">包装清单</td></tr>
							  　　 <tr>
									<td>包装清单</td>	
									<td>热水器x1、说明书x1、排烟直管x1、直角弯头x1	</td>											   		
							   　　</tr>
							   
							     <tr><td colspan="2" >主体</td></tr>
							     <tr>
									<td>商品名称</td>	
									<td>万家乐燃气热水器JSQ24-12W2 天然气	</td>											   		
							   　　</tr>	
							     <tr>
									<td>商品名称</td>	
									<td>万家乐燃气热水器JSQ24-12W2 天然气	</td>											   		
							   　　</tr>						 
							　  </tbody>
						</table>
				</div><!-- end of  <div id="attrInfoDisplayMode">  -->
				<!-- 编辑状态 -->
				<div id="attrInfoEditMode"　 style="display:none">
					<div>
						<label  for="btn-group" >属性类别</label>　	
						<input type="text"  class="addCommodityInput" id="threeCategoryInput">
					</div>
					<div>
						<label  for="btn-group" >属性名称</label>　	
						<input type="text"  class="addCommodityInput" id="threeCategoryInput">
					</div>
					<div>
						<label  for="btn-group" >属性　值</label>　	
						<input type="text"  class="addCommodityInput" id="threeCategoryInput">
					</div>
					<button id="attrInfoBtn" class="button">提交</button>	
				</div><!-- end of  <div id="attrInfoEditMode">  -->
			</div><!-- end of  <div id="attrInfoEdit">  -->
		</div><!-- end of <div id="commodityEditModel"> -->
		 <hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
		<!-- ---------------------商品详情编辑-------------------------- -->
	    <div id="descriptionInfoEdit"　>
	    	<h4>商品详情描述编辑</h4>
			<button id="descriptionInfoBtn" class="button">编辑</button>
	    	<div id="descriptionDisplayMode">
	    		
	    	</div>
	    	<div id="descriptionEdiMode">
	    		<button id="editorSetBtn">设置内容</button>
			    <button id="editorGetBtn1">获取内容1</button>
			    <button id="editorGetBtn2">获取内容2</button>
				<div id="editor">
		            <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
		        </div>
		        <button id="descriptionSubmitBtn" class="button">保存</button>
	    	</div>
	            
	    </div>	
	</body>

</html>