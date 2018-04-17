<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- base.jsp 路径定义 / 包含 bootstrap 和  jquery 文件-->
<%@ include file="/WEB-INF/layouts/common/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>蓝莓商城</title>
		<meta charset="utf-8">
 	    <meta name="viewport" content="width=device-width, initial-scale=1">		
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
	   <jsp:include page="/WEB-INF/layouts/common/footer.jsp" />
	   <p>
	   		<%
	   			Date now = new Date();
	   		%>
	   		<!-- pattern="yyyy-MMM-ddd"/> -->
	   		服务器时间：<fmt:formatDate value="<%=now%>" type="BOTH"/>
	   </p>
	</body>
</html>