<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head.jsp"%>
<link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/backend/css/src/main.css" type="text/css" rel="stylesheet">

<script>
	var id = "${roundJudge.id}";
</script>
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
					<h1 class="panel-title">新建/修改评审轮次</h1>
				</div>
				<div class="panel-body" id="opt-body">
					<form class="form-horizontal" id="myForm" action="#" method="post">
						<input type="hidden" name="category" value="1">
						<div class="form-group">
							<label class="control-label col-md-2">名称*</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="roundName" id="roundName">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2">描述</label>
							<div class="col-md-8">
								<textarea class="form-control" name="describes" rows="3" id="describes"></textarea>
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
		var pageName = "judgeRound";
	</script>

	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/jquery.validate.min.js"></script>
	<script src="resources/js/lib/jquery.serialize-object.min.js"></script>
	<script src="resources/backend/js/lib/bootstrap.min.js"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/js/src/ZYFormHandler.js"></script>
	<script src="resources/js/src/ZYCOUHandler.js"></script>
	<script src="resources/backend/js/src/judgeRoundCOU.js"></script>

</body>
</html>