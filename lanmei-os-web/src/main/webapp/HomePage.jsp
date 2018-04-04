<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		
	</head>
	
	<body>
	   <%@ include  file="/WEB-INF/layouts/common/header-nav.jsp" %>
	   <br>
	   <br>
	   <br>
	   <br>
	   <br>	
	   <%@ include  file="/WEB-INF/layouts/common/search.jsp" %>
	   <%@ include  file="/WEB-INF/layouts/HomePage/index.jsp" %> 	   
	   <%@ include  file="/WEB-INF/layouts/common/footer.jsp" %>
	</body>
</html>