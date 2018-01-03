<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../head.jsp"%>

<link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="resources/backend/css/lib/jquery.dataTables.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/backend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="left">
		<%@ include file="menu.jsp"%>
	</div>

	<div class="right">
		<div class="main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">新闻</h1>
				</div>
				<div class="panel-body" id="opt-body">

					<div class="row">
						<a class="btn btn-primary" href="news/newsCOU"> <span class="glyphicon glyphicon-plus"></span> 新建
						</a>
						<!--<div class="input-group tableSearchContainer col-md-6">
                        <input type="text" id="searchContent" class="form-control" placeholder="内容">
                        <span class="input-group-btn">
                            <button id="searchBtn" class="btn btn-default" type="button">搜索</button>
                        </span>
                    </div>-->
					</div>

					<table id="myTable" class="dataTable">
						<thead>
							<tr>
								<th>标题</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="loading.jsp"%>

	<script>
		var pageName = "news";
	</script>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.validate.min.js"></script>
	<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/backend/js/lib/bootstrap.min.js"></script>
	<script src="resources/backend/js/lib/jquery.dataTables.min.js"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/backend/js/src/ZYTableHandler.js"></script>
	<script src="resources/backend/js/src/newsMgr.js"></script>

</body>
</html>
