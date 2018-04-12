<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>测试</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
		<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
		<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script  type="text/javascript" src="static/js/test/json.js"></script>
		<script src="https://cdn.bootcss.com/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>
		<script src="https://cdn.bootcss.com/jquery.serializeJSON/2.9.0/jquery.serializejson.min.js"></script>
		</script>
		<script type="text/javascript">
		
		</script>
	</head>
	
	<body>   		
		<h1>LanMei测试页面</h1>	
		<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
		projectPath = ${projectPath}</br>
		contextPath = ${contextPath}</br>
		Scheme = ${Scheme}</br>
		ServerName = ${ServerName}</br>
		ServerPort = ${ServerPort}</br>
		
		<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />		
		<a href="${projectPath}/test/responsbody">打印responsbody 测试</a>
		</br>
		点击会直接返回Controller里的return 字符串</br>
		
		<!-- Json 传输测试 -->
		<hr style=" height:2px;border:none;border-top:2px solid #FFE7BA;" />
		<input id="inputval" type="text" name="age"/>
		<button id="json_btn" onclick="sendJson('${projectPath}')">Json测试</button>
		<a href="${projectPath}/json/debug">/json/debug</a>
		<br>
		读取服务端返回的map json数据
		</br>
		<button id="jsonreadmap_btn" onclick="sendmapJson()">jsonreadmap_btn</button>
		
		<br>
		转换表单数据并发送至服务端
	
		<form id="formDemo">
			<input type="text" name="userName" />
  			<input type="password" name="passWord" />
  			<input type="text" name="phoneNum" />			
		</form>
		<br>
		<!-- 该butto放置在表单内会导致整个页面刷新 -->
		<button id="form_btn" onclick="sendformJson()">发送</button>
	</body>
</html>