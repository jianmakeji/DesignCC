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
					<h1 class="panel-title">作品管理</h1>
				</div>
				<div class="panel-body" id="opt-body">

					<!--<div class="row">
                    <div class="input-group col-md-6">
                        <input type="text" id="searchContent" class="form-control" placeholder="名称">
                        <span class="input-group-btn">
                            <button id="searchBtn" class="btn btn-default" type="button">搜索</button>
                        </span>
                    </div>
                </div>-->
					<div class="row">
						<label class="col-md-1">评分轮次：</label>
						<div class="col-md-2">
							<select class="form-control" id="searchByJudgeRound">

							</select>
						</div>

						<label class="col-md-1">状态：</label>
						<div class="col-md-2">
							<select class="form-control" id="searchByStatus">
								<option value="0">全部</option>
								<option value="1">已提交</option>
								<option value="2">审核未通过</option>
								<option value="3">审核已通过</option>
								<option value="4">初选入围</option>
								<option value="5">初选未入围</option>
								<option value="6">复选入围</option>
								<option value="7">复选未入围</option>
							</select>
						</div>
					</div>

					<table id="myTable" class="dataTable">
						<thead>
							<tr>
								<th>ID</th>
								<th>缩略图</th>
								<th>名称</th>
								<th>组别</th>
								<th>分类</th>
								<th>分数</th>
								<th>状态</th>
								<th>所在评审轮次</th>
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
	<div id="popPanel">
		<table>
			<thead>
				<tr style="background-color:dimgray;text-align: center;">
					<th style="width: 100px; color: white;">评审轮次</th>
					<th style="width: 100px; color: white;">得分</th>
				</tr>
				<tbody id="roundResultTableBody">
			
				</tbody>
			</thead>
		</table>

		
	</div>
	<script>
		var pageName = "works";
	</script>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/backend/js/lib/bootstrap.min.js"></script>
	<script src="resources/backend/js/lib/jquery.dataTables.min.js"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/backend/js/src/ZYTableHandler.js"></script>
	<script src="resources/backend/js/src/worksMgr.js"></script>

</body>
</html>
