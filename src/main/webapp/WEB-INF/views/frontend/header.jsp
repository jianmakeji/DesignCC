<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class = "zyHeader">
	
	<div class = "zyLogo">
	<a href=""><img alt="" src="resources/frontend/images/app/logo.png"></a>
	</div>
		<div class="zyNoticeBoard">
			<div class="zyNoticeBoardLeft"><a href="user/register">报名参赛</a></div>
			<div class="zyNoticeBoardRight">
               <div class="zyItem">
                   征稿截止剩余天数
			        <span id="countDown" class="countDown"></span>
               </div>    
			</div>
			
		</div>

		<div class="zyTopNav">
			<ul class="zyMenu">
				<li class="zyItem"><a class="zyLink" href="index" data-page-name="index">首页</a></li>
				<li class="zyItem"><a class="zyLink" href="news/news/1" data-page-name="news">新闻</a></li>
				<li class="zyItem"><a class="zyLink" href="rule/rules" data-page-name="rule">章程</a></li>
				<li class="zyItem"><a class="zyLink" href="judge/judge" data-page-name="judge">评委</a></li>
				<li class="zyItem"><a class="zyLink" href="org" data-page-name="org">组织架构</a></li>
			</ul>
			
			<ul class="zyUserMenu">
				<c:if test="${!empty sessionScope.userId}">
					<li class="zyItem"><a href="production/works" class="zyLink zy20C7BE"> [ ${sessionScope.realname} ]</a></li>
					<li class="zyItem"><a class="zyLink" href="logout">退出</a></li>
				</c:if>
				<c:if test="${empty sessionScope.userId}">
					<li class="zyItem"><a class="zyLink zyIconUser" href="login"></a></li>
				</c:if>
			</ul>

		</div>
	
</div>

