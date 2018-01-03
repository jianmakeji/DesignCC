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
					<h1 class="panel-title">评审管理</h1>
				</div>
				<div class="panel-body" id="opt-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-md-2">轮次*</label>
							<div class="col-md-8">
								<select class="form-control" name="round" id="judgeRound">

								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2">邮件描述*</label>
							<div class="col-md-8">
								<textarea class="form-control" name="emailContent" rows="3" id="emailContent"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-2">测试邮箱</label>
							<div class="col-md-8">
								<input type="text" class="form-control" name="email" id="email">
								<label style="color:red">由于邮件发送为异步发送，为了检测这批邮件是否发送成功，可以添加您自己的邮箱一起发送，如果您收到邮件，则表示群发是成功的！</label>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-2 col-md-3">
								<button id="sendEmail" class="btn btn-success form-control">发送评审邮件</button>
							</div>

						</div>

					</div>
				</div>
		</article>
	</section>

	<%@ include file="loading.jsp"%>


	<script>
		var pageName = "sendEmail";
	</script>

	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/jquery.form.js"></script>
	<script src="resources/js/lib/jquery.validate.min.js"></script>
	<script src="resources/backend/js/lib/bootstrap.min.js"></script>
	<script src="resources/backend/js/src/config.js"></script>
	<script src="resources/backend/js/lib/tinyMCE/tinymce.min.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/js/src/ZYFormHandler.js"></script>
	<script src="resources/backend/js/src/sendEmail.js"></script>

</body>
</html>