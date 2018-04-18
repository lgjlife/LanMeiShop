<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%-- <jsp:include page="/WEB-INF/layouts/common/base.jsp"/> --%>
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城-账户设置</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">       
	</head>
	
<body>
  
	<jsp:include page="/WEB-INF/layouts/common/header-nav.jsp" />
	   <br>
	   <br>
	   <br>
	   <br>
	   <br>
	<h1 align="center">蓝莓商城</h1>
	<h2 align="center">账户设置</h2>
	   <br>
	   <br>
	<!-- 横线 -->
	<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
	
	 <div class="container-fluid">
		<div class="row">
			<div class="col-2">
				<a href="#" class="list-group-item " style="background-color:#B0E0E6">安全设置</a>
				<a href="#" class="list-group-item active">个人资料</a>
				<a href="#" class="list-group-item">收货地址</a>
				<a href="#" class="list-group-item">个人交易信息</a>
				<a href="#" class="list-group-item">收获地址</a>
			</div>
			<div class="col-8">
				<from class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label">昵称：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"/>
						</div>
						
						<label class="col-sm-2 control-label">真实姓名：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"/>
						</div>
						
						<label class="col-sm-2 control-label">真实姓名：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"/>
						</div>
						<label class="col-sm-2 control-label">性别：</label>
						<div style="">
							<label class="radio-inline">
							<input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="option1" checked> 男
							</label>
							<label class="radio-inline">
								<input type="radio" name="optionsRadiosinline" id="optionsRadios4"  value="option2"> 女
							</label>
						</div>
						
						
					</div>
				</from>
			</div><!-- end of div class="col-8  -->
			<div class="col-2">
			</div>
		</div><!-- end of  div class="row" -->
	</div><!-- end of div class="container-fluid"  -->
	
	<jsp:include page="/WEB-INF/layouts/common/footer.jsp" />
</body>
</html>