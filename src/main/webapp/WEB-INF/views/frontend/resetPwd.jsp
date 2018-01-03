<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head.jsp"%>

<link href="resources/backend/css/lib/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp"%>
	<form id="myForm" class="form-horizontal" method="post" action="#">
		<div class="form-group">
			<label class="control-label col-md-2">新密码*</label>
			<div class="col-md-8">
				<input type="password" class="form-control" name="newPwd" id="password">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">确认密码*</label>
			<div class="col-md-8">
				<input type="password" class="form-control" name="confirmPwd">
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-2 col-md-8">
				<button type="submit" class="btn btn-success form-control">确定</button>
			</div>
		</div>
	</form>

	<div class="zyFooter">&copy;康乃馨养老产业集团</div>
	
	<script>
		var pageName = "setting";
	</script>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/jquery.form.js"></script>
	<script src="resources/js/lib/jquery.validate.min.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/js/src/ZYFormHandler.js"></script>
	<script src="resources/frontend/js/src/resetPwd.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
</body>
</html>