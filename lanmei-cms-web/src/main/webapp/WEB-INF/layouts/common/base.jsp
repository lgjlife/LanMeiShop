<%@ page language="java" import="java.lang.String"  import="java.lang.Object" 
 contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String projectPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	String staticPath = projectPath + "/static";
	String imagePath = projectPath + "/uploads";
%>
<%--项目路径 --%>
<%--
projectPath = http://localhost:8080/lanmei-os
contextPath = /lanmei-os
Scheme = http
ServerName = localhost
ServerPort = 8080
 --%>
<c:set var="projectPath" value="<%=projectPath%>"></c:set>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="Scheme" value="${pageContext.request.scheme}"/>
<c:set var="ServerName" value="${pageContext.request.serverName}"/>
<c:set var="ServerPort" value="${pageContext.request.serverPort}"/>
<!-- 静态资源路径 -->
<c:set var="contextPathOfStatic" value="${pageContext.request.contextPath}/static"/>

<contextPathData	contextPathValue="${contextPath}"></contextPathData>

<!-- jquery -->
<script src="${contextPathOfStatic}/jquery/jquery-3.3.1.js"></script>
<!-- bootstrap 文件 -->
<link rel="stylesheet" type="text/css"  href="${contextPathOfStatic}/bootstrap/bootstrap.css">
<script src="${contextPathOfStatic}/bootstrap/bootstrap.js"></script>


<!-- 自定义 --> 


