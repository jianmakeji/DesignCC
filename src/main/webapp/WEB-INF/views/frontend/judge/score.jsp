
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:directive.page import="java.util.Hashtable" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../head.jsp"%>
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css"
	rel="stylesheet">
<link href="resources/frontend/css/src/main.css" type="text/css"
	rel="stylesheet">
<script>
	var judgeId = "${judgeId}";
	var productionId = '${production.id}';
	var round = "${production.round}";
</script>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div class="zyPanel zyMargin150">
		<h3 class="zyPanelTitle">评审作品</h3>
		<p class="zyText">注：评分制度百分之，请输入0-100之间的数字，不能包含特殊字符</p>
		<p class="zyText" style="text-indent: 2em">点击“保存”按钮保存评分，如果已经过了评分截止日期，不能再进行分数保存。</p>
		<div class="zyScoreContainer">
			<input type="text" id="zyScore" value="${score}">
			<button class="zyBtn" id="zySaveScore">保存</button>
		</div>
	</div>
	<hr>
	<div class="zyWorkDetail zyMargin150" id="zyWorkDetail">
		<h3 class="zyTitle">作品ID: ${production.id}</h3>
		<h3 class="zyTitle">${production.title}</h3>
		<%
			Hashtable table = new Hashtable();
			table.put("1", "产品组");
			table.put("2", "概念组");
			pageContext.setAttribute("group", table);
		%>
		<span class="zy20C7BE">${group[production.groupId]}</span><!-- <span>${production.participantName}</span> --> 
		<p class="zyText">${production.content}</p>

		<c:if test="${!empty production.weblink}">
			<div class="zy20C7BE">
				网页链接:&nbsp;&nbsp;<a class="zy20C7BE" href="${production.weblink}"
					target="_blank">${production.weblink}</a>
			</div>
			<br>
		</c:if>

		<c:if test="${!empty production.attachFile}">
			<div class="zy20C7BE">
				附件下载:&nbsp;&nbsp;<a class="zy20C7BE" href="${production.attachFile}"
					target="_blank">${production.attachFile}</a>
			</div>
			<br>
		</c:if>


	</div>

	<%@ include file="../loading.jsp"%>

	<script>
		var pageName = "judge";
	</script>

	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/frontend/js/src/judge/score.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
</body>
</html>