<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作品视频预览</title>
<style type="text/css">
body{
	background-color:#392c3f;
}
</style>

</head>
<body>
	<div id="content">
	
	</div>
</body>
<script src="../resources/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		 $(window).resize(); 
		 
		 $("#content").append('${param.videoAddress}');
		 console.log($("#content").outerWidth());
		 console.log($(window).width());
		 
		 $("#content").css({ 
		        position: "absolute", 
		        left: ($(window).width())/4, 
		        top: ($(window).height() - $("#content").outerHeight())/2 
		  });
	});
</script>
</html>