<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../head.jsp"%>
<link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>

	<%@ include file="header.jsp"%>

	<ul class="zyList2">
		<!--<li class="zyItem">
        <a class="zyLink">
            <img class="zyThumb" src="data/images/judge.jpg">
            <div class="zyInfo">
                <h3 class="zyTitle">xxx</h3>
                <span class="zyCountry">中国</span>
                <div class="zyExcerpt">
                    <p>这里是描述</p>
                </div>
            </div>
        </a>
    </li>-->

		<c:forEach items="${judgeList}" var="item">

			<li class="zyItem"><a class="zyLink" href="judge/judgeDetail/${item.id}"> <img class="zyThumb" src="${item.headicon}">
					<div class="zyInfo">
						<h3 class="zyTitle">${item.name}</h3>
						<!--<span class="zyCountry">中国</span>-->
						<div class="zyExcerpt">${item.subTitle}</div>
					</div>
			</a></li>

		</c:forEach>
	</ul>


	<div class="zyFooter">&copy;康乃馨养老产业集团</div>

	<script>
		var pageName = "judge";
	</script>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
</body>
</html>