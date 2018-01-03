<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head.jsp"%>

<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="zyMargin60">
		<form id="myForm" class="zyForm" method="post" action="#">
			<div class="zyFormRow">
				<label class="zyFormLabel">邮箱*</label>
				<div class="zyFormControl">
					<input type="text" class="zyInput" name="email">
				</div>
			</div>
			<div class="zyFormRow">
				<label class="zyFormLabel">验证码*</label>
				<div class="zyFormControl">
					<input type="text" class="zyInput" name="rand" style="width: 200px;"> <img src="user/getCode"
						style="display: inline-block; vertical-align: middle; height: 32px; width: auto;">
				</div>
			</div>
			<div class="zyFormRow">
				<div class="zyFormControl">
					<button type="submit" class="zyBtn">确定</button>
				</div>
			</div>
		</form>
		
	</div>
<div class="zyFooter">&copy;康乃馨养老产业集团</div>
	<%@ include file="loading.jsp"%>

	<script>
		var pageName = "forgetPwd";
	</script>

	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/jquery.form.js"></script>
	<script src="resources/js/lib/jquery.validate.min.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/js/src/ZYFormHandler.js"></script>
	<script src="resources/frontend/js/src/forgetPwd.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
</body>
</html>