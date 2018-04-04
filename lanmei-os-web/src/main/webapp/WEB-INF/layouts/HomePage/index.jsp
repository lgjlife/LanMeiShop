<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="static/js/homepage/homepage.js"></script>
		<link rel="stylesheet" href="static/css/homepage/homepage.css">
	</head>
	
	<body>
	    <!-- 创建顶部导航栏 -->
	     <div class="container-fluid">
			<div class="row">
				<div class="col-2">
				</div>
				<div class="col-2" >
					<ul class="nav ">
						<li class="nav-item">
							<a class="nav-link" href="/lanmei-os">全部商品分类</a>
						</li>
						
					</ul>
				</div>
				
				<div class="col-4" >
					<ul class="nav ">
						<li class="nav-item">
							<a class="nav-link" href="/">生活家电</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">时尚服饰</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/">电器城</a>
						</li>	
						<li class="nav-item">
							<a class="nav-link" href="/">海外购</a>
						</li>		
						<li class="nav-item">
							<a class="nav-link" href="/">特卖</a>
						</li>	
						<li class="nav-item">
							<a class="nav-link" href="/">抢购活动</a>
						</li>	        
					 </ul>
					
				</div>
	
				<div class="col-2">
				</div>
				<!-- 右侧空白 -->
				<div class="col-2">
				</div>
					
			</div>
			
		    <!-- 详细索引和图片显示 -->
			<div class="container-fluid">
				<div class="row">
					<!-- 左侧空白 -->
					<div class="col-2">
					</div>
					<!-- 全部商品分类的详细索引 -->
					<div class="col-2">
						<ul class="list-group">
							<li class="list-group-item active">
								<a href=“#” class="index-link">手机/</a>
								<a href=“#” class="index-link">运营商/</a>
								<a href=“#” class="index-link">智能数码</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">空调/</a>
								<a href=“#” class="index-link">电视/</a>
								<a href=“#” class="index-link">冰箱/</a>
								<a href=“#” class="index-link">洗衣机/</a>
							</li>
							<li class="list-group-item active">
								<a href=“#” class="index-link">厨卫电器/</a>
								<a href=“#” class="index-link">生活家电/</a>
								<a href=“#” class="index-link">厨具</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">电脑办公/</a>
								<a href=“#” class="index-link">相机/</a>
								<a href=“#” class="index-link">DIY</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">家居/</a>
								<a href=“#” class="index-link">家具/</a>
								<a href=“#” class="index-link">家装/</a>
								<a href=“#” class="index-link">家纺</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">食品/</a>
								<a href=“#” class="index-link">酒水/</a>
								<a href=“#” class="index-link">生鲜/</a>
								<a href=“#” class="index-link">特产</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">个护化妆/</a>
								<a href=“#” class="index-link">纸品清洁/</a>
								<a href=“#” class="index-link">宠物/</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">母婴/</a>
								<a href=“#” class="index-link">玩具/</a>
								<a href=“#” class="index-link">车床/</a>
								<a href=“#” class="index-link">童装</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">运动/</a>
								<a href=“#” class="index-link">户外/</a>
								<a href=“#” class="index-link">足球/</a>
								<a href=“#” class="index-link">骑行</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">男装/</a>
								<a href=“#” class="index-link">女装/</a>
								<a href=“#” class="index-link">内衣</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">鞋类/</a>
								<a href=“#” class="index-link">箱包/</a>
								<a href=“#” class="index-link">钟表/</a>
								<a href=“#” class="index-link">珠宝</a>
							</li>
							
							<li class="list-group-item active">
								<a href=“#” class="index-link">汽车摩托车/</a>
								<a href=“#” class="index-link">汽车精品/</a>
								<a href=“#” class="index-link">服务</a>
							</li>
							
							<li class="list-group-item active">							
								<a href=“#” class="index-link">图书/</a>
								<a href=“#” class="index-link">童书/</a>
								<a href=“#” class="index-link">电子书</a>
							</li>
							
							<li class="list-group-item active">							
								<a href=“#” class="index-link">理财/</a>
								<a href=“#” class="index-link">众筹/</a>
								<a href=“#” class="index-link">分期/</a>
								<a href=“#” class="index-link">保险</a>
							</li>
						</ul>
					</div>
					
					<div class="col-6" >
					
						<div id="ImgWrapper">
							 <ul  id="list-group-img">  
                               <li class="list-li"  style="display:none"><a href="#" target="_blank"><img id="img-huodong" src="static/img/recommend/recommend-2.jpg" alt="" /></a></li>  
                               <!-- <li class="list-li"  style="display:none"><a href="#" target="_blank"><img src="static/img/recommend/recommend-2.jpg" alt="" /></a></li>  
                               <li class="list-li"  style="display:none"><a href="#" target="_blank"><img src="static/img/recommend/recommend-3.jpg" alt="" /></a></li>  
                               <li class="list-li"  style="display:none"><a href="#" target="_blank"><img src="static/img/recommend/recommend-4.jpg" alt="" /></a></li>    -->                          
                              </ul>  
						</div>
						<img alt="" src="">
					</div>
					
				
					<!-- 右侧侧空白 -->
					<div class="col-2" >
					</div>
				</div>
			</div>
			
		</div>
		
	</body>
</html>