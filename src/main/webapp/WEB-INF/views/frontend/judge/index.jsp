<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../head.jsp"%>

<link href="resources/frontend/css/lib/kkpager_blue.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
<script>
	var judgeId = "${judgeId}";
	var round = "${round}";
</script>
</head>
<body>

	<%@ include file="../header.jsp"%>

	<div class="zyPanel zyMargin60">
		<p class="zyText">
			尊敬的评委您好！<br> 请点击作品开始评分，点击“保存”按钮来保存您的打分。<br> 截止日期前，都可以修改打分。<br> 所有通过该链接的打分都将视作您的个人行为，请勿与他人分享该链接。
		</p>

		<ul class="zyList3" id="zyFilter">
			<li class="zyItem zyActive" data-value="0">全部作品</li>
			<li class="zyItem" data-value="1">已打分</li>
			<li class="zyItem" data-value="2">未打分</li>
		</ul>

		<ul class="zyList" id="ZyList" style="padding: 0px;">
			<li class="zyItem">
                <a class="zyLink" href="review/score/{{id}}">
                    <img class="zyThumb" :src="thumb">
                    <h3 class="zyTitle">{{title}}</h3>
                    <span class="zy20C7BE" style="font-size:32px">{{score}}</span>
                </a>
            </li>
		</ul>

		<div id="kkpager"></div>


	</div>


	<%@ include file="../loading.jsp"%>

	<script>
		var pageName = "judge";
	</script>

	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/frontend/js/lib/kkpager.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/juicer-min.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/frontend/js/src/judge/index.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
	<script src="resources/js/lib/vue.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>