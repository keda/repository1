<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/jquery/jquery-1.7.1.js"></script>
<title>Home</title>
</head>
<body>
	<h1>Welcome...</h1>
	<p>${ pageContext.request.contextPath }</p>
</body>
<script type="text/javascript">
	$(function(){ alert('hello...'); })();
</script>
</html>