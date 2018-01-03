<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../head.jsp"%>

<link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">

</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="zyPostDetail zyMargin150">
		<h3 class="zyTitle">${news.title}</h3>
		<p class="zyDate">${fn:substring(news.publishTime, 0, 10)}</p>
		<div class="zyContent">${news.content}</div>
	</div>


	<div class="zyFooter">&copy;康乃馨养老产业集团</div>

	<script>
		var pageName = "news";
	</script>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
</body>
</html>