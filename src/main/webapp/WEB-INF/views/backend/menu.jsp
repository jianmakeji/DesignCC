<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="menu">

	<li class="item" data-page-name="news"><span class="glyphicon glyphicon-file"></span> <a class="link" href="news/newsMgr">新闻</a></li>
	<li class="item" data-page-name="judge"><span class="glyphicon glyphicon-user"></span> <a class="link" href="judge/judgeMgr">评委</a></li>
	<li class="item" data-page-name="works"><span class="glyphicon glyphicon-th-list"></span> <a class="link" href="production/worksMgr">作品</a></li>
	<li class="item" data-page-name="user"><span class="glyphicon glyphicon-user"></span> <a class="link" href="user/userMgr">用户</a></li>

	<li class="item" data-page-name="judgeRound"><span class="glyphicon glyphicon-th-list"></span> <a class="link" href="roundJudge/judgeRoundMgr">评审轮次</a></li>
<!--
	<li class="item" data-page-name="sendEmail"><span class="glyphicon glyphicon-th-list"></span> <a class="link" href="review/sendEmail">评审管理</a></li>
	<li class="item" data-page-name="setting">
        <span class="glyphicon glyphicon-cog"></span>
        <a class="link" href="views/backend/setting.html">设置</a>
    </li>-->
    <li class="item">
        <span class="glyphicon glyphicon-th-list"></span>
        <a class="link">评审管理</a>
        <span class="glyphicon glyphicon-chevron-down rightIcon"></span>
        <ul class="subMenu">
            <li class="sItem">
                <span class="circle">原点</span>
                <a class="sLink" href="review/sendEmail">邮件管理</a>
            </li>
            <li class="sItem">
                <span class="circle">原点</span>
                <a class="sLink" href="review/scoreMgr">分数管理</a>
            </li>
           
        </ul>
    </li>
</ul>