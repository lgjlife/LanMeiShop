<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	   <form method="get"  action="http://localhost:8080/lanmei-os/pass/test" >
	   		<table>
	   			<tr>
	   				<td>
	   					<input type="text" name="usernamme"/>
	   				</td>
	   				<td>
	   					<input type="submit" value="提交 y" target="_blank"/>
	   				</td>
	   			</tr>
	   			
	   		</table>
	   		
	   
	   </form>
	   <%@ include  file="/WEB-INF/layouts/common/search.jsp" %>
	   <%@ include  file="/WEB-INF/layouts/HomePage/index.jsp" %> 	   
	   <%@ include  file="/WEB-INF/layouts/common/footer.jsp" %>
	   <p>
	   		<%
	   			Date now = new Date();
	   		%>
	   		<!-- pattern="yyyy-MMM-ddd"/> -->
	   		服务器时间：<fmt:formatDate value="<%=now%>" type="BOTH"/>
	   </p>
	</body>
</html>