<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	
	<body>
	    <!-- 创建顶部导航栏 -->
	     <div class="container-fluid">
			<div class="row">
				<div class="col-2">
				</div>
				<div class="col-4" >
					<ul class="nav ">
						<li class="nav-item">
							<a class="nav-link" href="/lanmei-os">商城首页</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">商家入驻</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">客户服务</a>
						</li>
				 
				 		<li class="dropdown">
			                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			                    深圳
			                </a>
			                <ul class="dropdown-menu">
			                    <li>
			                    	<abbr ><a id="local_province" href="#">广东</a></abbr>
			                    	<span>|</span>
			                    	<abbr ><a id="local_city" href="#">广州</a></abbr>
			                    </li>
			  
			                </ul>
			            </li>
				        
					 </ul>
					
				</div>
	
				<div class="col-4">
					<ul class="nav">
						<li class="nav-item">
							<a class="nav-link" href="lanmei-os/login.jsp">登录</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">注册</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">我的订单</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">购物车</a>
						</li>
				</ul>
				<div class="col-2">
				</div>			
			</div>
		</div>
	</body>
</html>