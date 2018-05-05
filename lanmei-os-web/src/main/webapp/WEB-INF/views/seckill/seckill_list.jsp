<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城-秒杀列表</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">      
 	    
		<script src="${contextPathOfStatic}/js/user/login.js"></script>
		<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/css/user/register.css">
		
 
	</head>
	
<body>
  
	 <jsp:include page="/WEB-INF/layouts/common/header-nav.jsp" />
	   <br>
	   <br>
	   <br>
	   <br>
	   <br>
	<h1 align="center">蓝莓商城</h1>
	<h2 align="center">秒杀活动</h2>
	   <br>
	   <br>
	<!-- 横线 -->
	<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
	<!-- 注册表单 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-2">		
			</div>
			<div class="col-8">		
				<table class="table table-bordered">
		  	 		<thead>
		  	 			<tr>
		  	 				<td>名称</td>
		  	 				<td>编号</td>
		  	 				<td>库存</td>
		  	 				<td>创建时间</td>
		  	 				<td>抢购时间</td>		  	 				
		  	 				<td>结束时间</td>
		  	 			</tr>		  	 			
		  	 		</thead>
		  	 		<tbody id="tbodyList">	
			  	 		<c:forEach var="seckillItem" items="${seckill}" >
			  	 			<tr>
			  	 				<td>
			  	 					<a href="${projectPath}/seckill/${seckillItem.seckillId}/detail">${seckillItem.seckillName}</a>
			  	 				</td>
			  	 				<td>
			  	 					<a>${seckillItem.seckillId}</a>
			  	 				</td>
			  	 				<td>
			  	 					<a>${seckillItem.stockCount}</a>
			  	 				</td>
			  	 				<td>
			  	 					<fmt:formatDate value="${seckillItem.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
			  	 				<td>
			  	 					<fmt:formatDate value="${seckillItem.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
			  	 				</td>		  	 				
			  	 				<td>
			  	 					<fmt:formatDate value="${seckillItem.finishTime}" pattern="yyyy-MM-dd HH:mm:ss" />
			  	 				</td>
			  	 			</tr>	
			  	 		</c:forEach>  	 			
		  	 		</tbody>	  	 		
		  	 	</table>	
			</div>
			<div class="col-2">				
			</div>
		</div><!--<div class="row">  -->
	</div><!-- end of <div class="container-fluid">  -->
	<jsp:include page="/WEB-INF/layouts/common/footer.jsp" />
	
</body>
</html>