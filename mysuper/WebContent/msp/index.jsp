<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath", contextPath);
	String servletPath = request.getServletPath();
	request.setAttribute("servletPath", servletPath);
	
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ contextPath }/resources/extjs/resources/css/ext-all.css"/>
<script type="text/javascript" src="${ contextPath }/resources/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="${ contextPath }/resources/extjs/ext-all.js"></script>
<script type="text/javascript" src="${ contextPath }/resources/appjs/app.js"></script>
<title>Home</title>
</head>
<body>
	<input type="hidden" id="ctxPath" value="${ contextPath }">
</body>
<script type="text/javascript">
	
</script>
</html>