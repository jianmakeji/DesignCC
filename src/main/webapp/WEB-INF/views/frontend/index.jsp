<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

            <!DOCTYPE html>
            <html>

            <head>
                <%@ include file="../head.jsp"%>
                    <link href="resources/frontend/css/lib/kkpager_blue.css" type="text/css" rel="stylesheet">
                    <link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
                    <link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
            </head>

            <body>

                <%@ include file="header.jsp"%>

                    <div class="zyTopShow">
                        <div class="zyItem">
                            <img src="resources/frontend/images/app/topShow.jpg">
                            <div class="zyInfo">
                                <h2 class="zyTitle">为老设计 康享生活</h2>
                                <p class="zyExcerpt">弘扬全名创新精神、促进企事业自主创新能力和核心竞争力，为老年人设计出更科学、更健康、更舒适、 更符合需求的涉老产品，进一步提高老年人的生活质量，推动老年服务业的发展。</p>
                                <a class="zyBtn" href="user/register">报名参赛</a>
                            </div>
                        </div>
                        <p class="watchItAgain" id="watchItAgain"><a href="#">播放总宣视频</a></p>
                        <div class="zyItem zyTopVideo" id="zyTopVideo">
                            <video width="100%" height="auto" autoplay="autoplay">
                                <source src="resources/video/logo_animation.mp4" type="video/mp4">
                            </video>
                        </div>
                    </div>
                    <img src="resources/frontend/images/app/schedule.png">

                    <div style="width: 100%; background-color: #f7f7f7">
                        <section style="color: #bb8229; font-size: 32pt; padding-top: 50px; margin-left: 50px">NEWS | 新闻动态</section>
                        <ul class="zyList">
                            <!--<li class="zyItem">
            <a class="zyLink">
                <img class="zyThumb" src="data/images/news.png">
                <h3 class="zyTitle">湖南省老年服务产品设计大赛</h3>
                <span class="zyDate">2019-09-09</span>
            </a>
        </li>-->

                            <c:forEach items="${newsList}" var="item">
                                <li class="zyItem">
                                    <a class="zyLink" href="news/newsDetail/${item.id}" target="_blank"> <img class="zyThumb" src="${item.thumb}">
                                        <h3 class="zyTitle">${item.title}</h3> <span class="zyDate">${fn:substring(item.publishTime, 0, 10)}</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>

                        <div class="zyTCenter">
                            <a class="zyBtn" style="margin: 50px auto;" href="news/news/1">查看更多</a>
                        </div>


                    </div>

                    <div class="zyFooter">&copy;康乃馨养老产业集团</div>


                    <script>
                        var pageName = "index";

                    </script>
                    <script src="resources/js/lib/jquery-1.10.2.min.js"></script>
                    <script src="resources/frontend/js/lib/kkpager.min.js"></script>
                    <script src="resources/js/lib/jquery.toastmessage.js"></script>
                    <script src="resources/js/lib/juicer-min.js"></script>
                    <script src="resources/frontend/js/src/config.js"></script>
                    <script src="resources/js/src/functions.js"></script>
                    <script src="resources/frontend/js/src/header.js"></script>
                    <script src="resources/frontend/js/src/index.js"></script>
            </body>

            </html>
