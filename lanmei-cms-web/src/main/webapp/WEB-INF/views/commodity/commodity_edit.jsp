<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>

<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<%-- 	<!-- jquery -->
 	<script src="${contextPathOfStatic}/jquery/jquery-3.3.1.js"></script> --%>
	<!-- bootstrap 文件 -->
	 <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/bootstrap/bootstrap.css">
	<script src="${contextPathOfStatic}/bootstrap/bootstrap.js"></script> 
	
	<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/common/common.css">

	  <!-- 增加商品 -->
	  <link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/commodity/commodity.edit.css">	
	  <script src="${contextPathOfStatic}/js/commodity/commodity.edit.js"></script>
	  <!-- 富文本wangEditor -->
	  <script type="text/javascript" src="${contextPathOfStatic}/wangEditor/wangEditor.js"></script>
	<body >
	
	   	<h2>查询商品</h2>					 
		<button id="queryDisplayCtrlBtn" class="button">展开查询列表</button>
	 　  　<!-- －－－－－－－－　 商品查询展示列表－－－－－－－－－－－ -->
		<div id="commodityListModel" >
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
		       <div class="categoryItemSelect" id="categoryItemDiv">
			    	<table class="table table-bordered　table-list" id="categoryItemTable">
				    	  <thead>
						  </thead>
						  <tbody id="categoryItemTableTbody"> 	
						  	<tr>
						  	</tr>			
						  </tbody>
					</table>
		     　  </div>		      
			  </div>
		      <!-- 动态添加 -->
		       <div class="comodityLisSelect" id="comodityListDiv" >
			    	<table class="table-list" id="comodityListDivTable">
				    	  <thead class="table-thead">
				    	  	<td >编号</td>				    			
			    	  		<td >品牌</td>			    	  		
					   		<td >名称</td>
					   		<td >标题</td>
					   		<td >创建者</td>
					   		<td >创建时间</td>
					   		<td >上架时间</td>
					   		<td >下架时间</td>
					   		<td >状态</td>
					   		<td >操作</td>
						  </thead>
						  <tbody id="commodityLisTableTbody"> 				
						  </tbody>
					</table>
		     　  </div>	
		     	
			 </div>
			
		</div><!-- <div id="commodityQueryItemPage" class="adminManagerPageDiplay"> -->
		<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
		<!-- －－－－－－－－　 商品编辑页面－－－－－－－－－－－ -->
		<!-- 
		包含模块：
			　　１．类别，名称　编辑　标题　，商品详行
	　		　　　　2.　SKU 编辑：　sku 名称　，属性列表　，相关的图片　
	　　　　　　　　　　　　3.属性编辑，所有的属性由用户自定义
	　　　　　　　　　　　　
		 -->
		<div id="commodityEditModel" style="display:div;">
			<h2>编辑商品</h2>	
			<h3 id="commodityEditModelTitle"></h3>
			<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
			<!-- 基本信息编辑 -->
			<div id="baseInfoEdit">
				<h4>基本信息</h4>
				<button id="baseInfoEditDispalyCtrlBtn" class="button">展开</button>
	    	　　　　<!-- 点击展开时才从后台获取数据 -->
	    	　　　　<div id="baseInfoEditDispalyCtrl" style="display:none">
					<button id="baseInfoBtn" class="button">编辑</button>
					
					<!-- 显示状态 -->
					<div id="baseInfoDisplayMode">
					　	<span  class="name" id="baseInfoDisMainCategory">大　　类:</span><br>
						<span class="name" id="baseInfoDisFirstCategory">一级分类:</span><br>
						<span class="name" id="baseInfoDisSecondCategory">二级分类:</span><br>
						<span class="name" id="baseInfoDisBrand">品　　牌:</span><br>
						<span class="name" id="baseInfoDisName">产品名称:</span><br>
						<span class="name" id="baseInfoDisTitle">产品标题:</span><br>
					</div><!-- end of <div id="baseInfoDisplayMode">  -->
					<!-- 编辑状态 -->
					<div id="baseInfoEditMode"  style="display:none">
						<div class="form-group">
							<label  for="btn-group" >选择大类</label>　
							<input type="text" list="datalist0" class="" id="baseInfoBigCategoryInput">
							<datalist id="datalist0">
						    </datalist>
						    <br>
						</div>
						<!-- 一级细分 -->
						<div class="form-group" >
							<label  for="btn-group" >一级细分</label>　
							<input type="text" list="datalist1" class="" id="baseInfoFirstCategoryInput">	
							<datalist id="datalist1">
						    </datalist>
						    <br>
						</div>			    
						<!-- 二级细分 -->
						<div class="form-group" >
							<label  for="btn-group" >二级细分</label>　	
						    <input type="text" list="datalist2" class="" id="baseInfoSecondCategoryInput">	
							<datalist id="datalist2">
						    </datalist>　
						    <br>
						</div>	
						<!-- 三级细分 品牌 -->
						<div class="form-group" >
							<label  for="btn-group" >品　　牌</label>　	
						    <input type="text" list="datalist3" class="" id="baseInfoThreeCategoryInput">	
							<datalist id="datalist3">
						    </datalist>　
						    <br>
						</div>			
				
					     <!-- 名称 -->
					    <div class="form-group">
					    	<label  for="btn-group" >名　　称</label>　	
							<input type="text" class="" id="baseInfoProductNameInput"　>
							<span id="baseInfoProductNameInputWarn" ></span>
					    </div>
					    
					     <!-- 显示标题 -->
					    <div class="form-group">
					    	<label  for="btn-group"  >显示标题</label>　	
							<input type="text" class="" id="baseInfoTitleInput">
					    </div>
					    <button id="baseInfoUpdateInfoBtn" class="button">更新</button>
					</div><!-- end of  <div id="baseInfoEditMode">  -->
				</div><!-- <div id="baseInfoEditDispalyCtrl" style="display:none"> -->
			</div><!-- end of  <div id="baseInfoEdit">  -->
			 <hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
			<!-- －－－－－－－－－－－ＳＫＵ　销售　属性编辑 －－－－－－－－－－－－－－-->
			<div id="skuInfoEdit">
			
				<h4>销售属性组合编辑</h4>
				<button id="skuInfoEditDispalyCtrlBtn" class="button">展开</button>
	    	　　　　<!-- 点击展开时才从后台获取数据 -->
	    	　　　　<div id="skuInfoEditDispalyCtrl" style="display:none">
					<!-- <button id="skuInfoBtn" class="button">编辑</button> -->
					<!-- 属性显示 -->
						<div id="skuAttrDisplayDiv">
							<h6>销售属性</h6>
							<div id="sku-attr-display-div">
								<ul class="list-group sku-attr-list" style="display:none">
								  <li class="list-group-item list-group-item-info">颜色</li>
								  <li class="list-group-item">玫瑰金<button>删除</button></li>
								  <li class="list-group-item">陶瓷白<button>删除</button></li>
								</ul>
								<ul class="list-group sku-attr-list" style="display:none">
								  <li class="list-group-item list-group-item-info">内存</li>
								  <li class="list-group-item">32Ｇ<button>删除</button></li>
								  <li class="list-group-item">64Ｇsadsdsssdasdas<button>删除</button></li>
								</ul>
							</div>
							<br/>
							<button id="skuAttrDataDisplayBtn">显示销售属性</button>
							
							
							<br/>
							<button class="button" data-toggle="modal" data-target="#myModal">
								添加销售属性
							</button>
							<!-- 模态框（Modal） -->
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
												&times;
											</button>
											<h4 class="modal-title" id="myModalLabel">
												添加销售属性
											</h4>
										</div>
										<div class="modal-body" id="addSkuAttrInput">
											<span>名称：</span><input type="text" id="addSkuAttrInput-name"　placeholder="颜色/尺寸/内存等"><br/>
											<span>属性：</span><input type="text" id="addSkuAttrInput-attr"><br/>
											<span id="addSkuAttrInputWarn" style="color:red;"></span>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">
												取消
											</button>
											<button type="button" class="btn btn-primary" id="addSkuAttrInputBtn">
												提交
											</button>
										</div>
									</div><!-- /.modal-content -->
								</div><!-- /.modal -->
							</div><!-- 模态框（Modal） --> 
						</div><!-- <div id="skuAttrDisplayDiv"> -->
					<!-- 显示状态 -->
					<div id="skuInfoDisplayMode">
						 <table class="table table-bordered" id="commodityList">
					    	  <thead>
					    			<td >编号</td>				    			
					    	  		<td >名称</td>
							   		<td >属性</td>
							   		<td >价格</td>
							   		<td >库存</td>
							   		<td >操作</td>						   		
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
						<!-- 图片显示 -->
						<div id="imgDisplayDiv">
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
							<input type="text" class="addCommodityInput" id="skuProductNameInput"　>
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
				</div>
			</div><!-- end of  <div id="skuInfoEdit">  -->
			 <hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
			<!-- 商品属性编辑 -->
			<div id="attrInfoEdit"　>
			
				<h4>商品属性编辑</h4>
				<button id="attrInfoEditDispalyCtrlBtn" class="button">展开</button>
	    	　　　　<!-- 点击展开时才从后台获取数据 -->
	    	　　　　<div id="attrInfoEditDispalyCtrl" style="display:none">
					<button id="attrInfoBtn" class="button">添加属性</button>	
					<!-- 显示状态 -->
					<div id="attrInfoDisplayMode">
						<table class="table table-bordered" id="attrInfoList">
						    	  <thead>	
						    	  	<tr　>
						    	  		<td style="background-color:#EEE9BF;display:none">id</td>
						    	  		<td style="background-color:#EEE9BF">类别</td>
						    	  		<td style="background-color:#EEE9BF">属性名称</td>
						    	  		<td style="background-color:#EEE9BF">属性值</td>
						    	  		<td style="background-color:#EEE9BF">操作</td>
						    	  	</tr>		   		
								  </thead>
								  <tbody id="attrInfoTableTbody">
								  		<!-- <tr　class="attrDisplayList">
								  			<td　style="display:none">3</td>
								  			<td>系统</td>
								  			<td>手机操作系统</td>
								  			<td>Android</td>
								  			<td><button type="button">编辑</button></td>
								  		</tr>	 -->				 
								　  </tbody>
							</table>
					</div><!-- end of  <div id="attrInfoDisplayMode">  -->
					<!-- 编辑状态 -->
					<div id="attrInfoEditMode"　 style="display:none">
						<div style="display:none">
							<label  for="btn-group" >属性id</label>　	
							<input type="text"  id="attrInfo-attrId">
						</div>
						<div>
							<label  for="btn-group" >属性类别</label>　	
							<input type="text"  id="attrInfo-category">
						</div>
						<div>
							<label  for="btn-group" >属性名称</label>　	
							<input type="text"  id="attrInfo-attName">
						</div>
						<div>
							<label  for="btn-group" >属性　值</label>　	
							<input type="text" id="attrInfo-attVal">
						</div>
						<button id="attrInfoSubmitBtn" class="button">提交</button>	
					</div><!-- end of  <div id="attrInfoEditMode">  -->
				</div><!-- <div id="attrInfoEditDispalyCtrl" style="dispaly:none"> -->
			</div><!-- end of  <div id="attrInfoEdit">  -->
			<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
			<!-- ---------------------商品详情编辑-------------------------- -->
		    <div id="descriptionInfoEdit"　>
		    	<h4>商品详情描述编辑</h4>
		    	<button id="descriptionInfoEditDispalyCtrlBtn" class="button">展开</button>
		    	<!-- 点击展开时才从后台获取数据 -->
		    	<div id="descriptionInfoEditDispalyCtrl" style="display:none">
		    	
					<button id="descriptionInfoBtn" class="button">编辑</button>				
			    	<div id="descriptionDisplayMode">
			    		<!-- 商品内容展示区 -->		    		
			    	</div>
			    	<div id="descriptionEdiMode">
			    		<!-- 商品内容编辑区 -->	
			    		<button id="editorSetBtn">设置内容</button>
					    <button id="editorGetBtn1">获取内容1</button>
					    <button id="editorGetBtn2">获取内容2</button>
						<div id="editor">
				           <!-- 编辑框 -->
				        </div>
				        <button id="descriptionSubmitBtn" class="button">保存</button>
			    	</div><!-- <div id="descriptionEdiMode"> -->
		    	</div>
		            
		    </div><!--  <div id="descriptionInfoEdit"　> -->	
		</div><!-- end of <div id="commodityEditModel"> --> 
　　　　　　　　<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
	</body>

</html>