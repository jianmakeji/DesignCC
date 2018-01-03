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
					<h1 class="panel-title">评审轮次管理</h1>
				</div>
				<div class="panel-body" id="opt-body">

					<div class="row">
						<a class="btn btn-primary" href="roundJudge/judgeRoundCOU"> <span class="glyphicon glyphicon-plus"></span> 新建
						</a>
					</div>

					<table id="myTable" class="dataTable">
						<thead>
							<tr>
								<th>名称</th>
								<th>评委</th>
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

	<!--设置评委-->
	<div class="modal fade" id="setJudgeModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="judgeRoundName">xxxx</h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
							<table id="judgeTable" class="dataTable">
								<thead>
									<tr>
										<th><input type="checkbox" id="checkAll"></th>
										<th>姓名</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-10">
								<input type="submit" id="saveJudgeOfRound" class="btn btn-primary" value="确定">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<%@ include file="loading.jsp"%>

	<script>
		var pageName = "judgeRound";
	</script>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.validate.min.js"></script>
	<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/juicer-min.js"></script>
	<script src="resources/backend/js/lib/bootstrap.min.js"></script>
	<script src="resources/backend/js/lib/jquery.dataTables.min.js"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/backend/js/src/ZYTableHandler.js"></script>
	<script src="resources/backend/js/src/judgeRoundMgr.js"></script>

</body>
</html>
