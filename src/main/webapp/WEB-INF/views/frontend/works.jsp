<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../head.jsp"%>
<link href="resources/frontend/css/lib/kkpager_blue.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
<script>
	var userId = "${sessionScope.userId}";
</script>
</head>
<body>

	<%@ include file="header.jsp"%>
	<%@ include file="pageMenu.jsp"%>

	<div class="zyMargin60">
		<table class="zyTable" id="myTable">
			<thead>
				<tr>
					<th>标题</th>
					<th>组别</th>
					<th>类别</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<!--<tr>
                    <td>xxxxx</td>
                    <td>概念组</td>
                    <td>智能生活</td>
                    <td>状态</td>
                    <td>
                        <a href="#" class="zyAction zyIconCheck">查看</a>&nbsp;
                        <a href="#"  class="zyAction zyIconEdit">修改</a>&nbsp;
                        <a href="#"  class="zyAction zyIconRemove">删除</a>
                    </td>
                </tr>-->
			</tbody>
		</table>
	</div>

	<div id="kkpager"></div>


	<%@ include file="loading.jsp"%>

	<script type="text/template" id="zyTrTpl">
        {@each items as i}
        <tr>
            <td>$ZY{i.title}</td>
            <td>$ZY{i.group}</td>
            <td>$ZY{i.category}</td>
            <td>$ZY{i.status}</td>
            <td>
                <a href="production/workDetail/$ZY{i.id}" class="zyAction zyIconCheck" target="_blank">查看</a>&nbsp;
                {@if i.canEdit}
                    <a href="production/uploadWork/$ZY{i.id}"  class="zyAction zyIconEdit">修改</a>&nbsp;
                    <a href="$ZY{i.id}"  class="zyAction zyIconRemove">删除</a>
                {@/if}
            </td>
        </tr>
        {@/each}
        </script>


	<script>
		var pageName = "works";
	</script>

	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/frontend/js/lib/kkpager.min.js"></script>
	<script src="resources/js/lib/jquery.toastmessage.js"></script>
	<script src="resources/js/lib/juicer-min.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script src="resources/js/src/functions.js"></script>
	<script src="resources/frontend/js/src/works.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
</body>
</html>