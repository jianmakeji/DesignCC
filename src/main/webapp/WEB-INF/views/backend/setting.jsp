<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head.jsp"%>

<link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/backend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>

	<%@ include file="header.jsp"%>

	<div class="left">
		<%@ include file="menu.jsp"%>
	</div>

	<section class="right">
		<article class="main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">阶段设置</h1>
				</div>
				<div class="panel-body" id="opt-body">
					<form class="form-horizontal" id="myForm" action="#" method="post">
						<div class="form-group">
							<label class="control-label col-md-2">当前阶段</label>
							<div class="col-md-8">
								<label class="control-label">作品投递</label>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2">阶段*</label>
							<div class="col-md-8">
								<select class="form-control" name="stage">
									<option value="1">作品投递</option>
									<option value="2">评审</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2 col-md-8">
								<button type="submit" class="btn btn-success form-control">确定</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</article>
	</section>

	<%@ include file="loading.jsp"%>


	<script>
		var pageName = "setting";
	</script>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/jquery.validate.min.js"></script>
	<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
	<script src="resources/backend/js/lib/bootstrap.min.js"></script>
	<script src="resources/js/src/ZYFormHandler.js"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/backend/js/src/setting.js"></script>

</body>
</html>