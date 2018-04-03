<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</head>
	
	<body>
	    <!-- 创建顶部导航栏 -->
	     <div class="container-fluid">
			<div class="row">
				<div class="col-2">
				</div>
				<div class="col-4" >
					<ul class="nav">
						<li class="nav-item">
							<a class="nav-link" href="/lanmei-os">商城首页</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">商家入驻</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">客户服务</a>
						</li>
				 
					    <li class="nav-item dropdown">
					      <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">深圳</a>
					      <div class="dropdown-menu">
					        <a class="dropdown-item" href="#">Link 1</a>
					        <a class="dropdown-item" href="#">Link 2</a>
					        <a class="dropdown-item" href="#">Link 3</a></div>
					    </li>
					 </ul>
					
				</div>
				<div class="col-4">
					<ul class="nav">
						<li class="nav-item">
							<a class="nav-link" href="/">登录</a>
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