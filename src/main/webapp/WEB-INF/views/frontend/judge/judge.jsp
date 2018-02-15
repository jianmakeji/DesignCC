<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评审</title>
<link href="resources/frontend/css/src/style.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/iview.css" type="text/css" rel="stylesheet">
<link href="resources/css/lib/jquery.toastmessage.css" type="text/css" rel="stylesheet">
<link href="resources/frontend/css/src/main.css" type="text/css" rel="stylesheet">
<script>
	var judgeId = "${judgeId}";
	var round = "${round}";
	var pageName = "judge";
</script>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div id="productList" style="width:100%">
		<div style="text-align:center;">
			<i-menu mode="horizontal" :theme="theme1" active-name="1" style="margin-top:10px">
		        <menu-item name="1">
		            <icon type="settings"></icon>
		            所有作品
		        </menu-item>
		        <menu-item name="2">
		            <icon type="settings"></icon>
		            已评分作品
		        </menu-item>
		        
		        <menu-item name="4">
		            <icon type="settings"></icon>
		           未评分作品
		        </menu-item>
		    </i-menu>
		</div>
		
    
		<ul class="cd-items cd-container">
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
	
			<li class="cd-item">
				<img src="resources/frontend/images/app/item-1.jpg" alt="Item Preview">
				<a href="#0" class="cd-trigger">Quick View</a>
			</li> <!-- cd-item -->
		</ul> <!-- cd-items -->
		<div style="text-align:center;margin-bottom:30px;">
			<page :total="total"></page>
		</div>
		
		<div class="zyFooter">&copy;康乃馨养老产业集团</div>
		
		<div class="cd-quick-view">
			<div class="cd-slider-wrapper">
				<ul class="cd-slider">
					<li class="selected"><img src="resources/frontend/images/app/item-1.jpg" alt="Product 1"></li>
					<li><img src="resources/frontend/images/app/item-2.jpg" alt="Product 2"></li>
					<li><img src="resources/frontend/images/app/item-3.jpg" alt="Product 3"></li>
				</ul> <!-- cd-slider -->
	
				<ul class="cd-slider-navigation">
					<li><a class="cd-next" href="#0">Prev</a></li>
					<li><a class="cd-prev" href="#0">Next</a></li>
				</ul> <!-- cd-slider-navigation -->
			</div> <!-- cd-slider-wrapper -->
	
			<div class="cd-item-info">
				<h2>Produt Title</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officia, omnis illo iste ratione. Numquam eveniet quo, ullam itaque expedita impedit. Eveniet, asperiores amet iste repellendus similique reiciendis, maxime laborum praesentium.</p>
	
				<ul class="cd-item-action">
					<li><button class="add-to-cart">Add to cart</button></li>					
					<li><a href="#0">Learn more</a></li>	
				</ul> <!-- cd-item-action -->
			</div> <!-- cd-item-info -->
			<a href="#0" class="cd-close">Close</a>
		</div> <!-- cd-quick-view -->
	</div>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/vue.min.js"></script>
	<script src="resources/js/lib/velocity.min.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
	<script src="resources/js/lib/iview.min.js"></script>
	<script src="resources/frontend/js/src/judge/judge.js"></script>
	<script src="resources/frontend/js/src/config.js"></script>
	<script type="text/javascript">
	
		new Vue({
			  el: '#productList',
			  data () {
			    return {
			      total:90,
			      list:[],
			      theme1: 'light'
			    }
			  },
			  created:function(){
				  this.loadData(1);
			  },
			  methods:{
				  loadData:function(pageNum){
					  
					  $.ajax({
			                url: config.ajaxUrls.judgeToScoreList,
			                type: "post",
			                data: {
			                    userId: judgeId,
			                    round:round,
			                    offset: (pageNum-1)*12,
			                    scoreSign:$("#zyFilter .zyActive").data("value"),
			                    limit:12
			                },
			                success: function (response) {
			                    if (response.success) {
			                        var results = response.object.list,
			                            totalCount = response.object.count;

									console.log(results);
									console.log(totalCount);
									
			                    } else {
			                        
			                    }
			                },
			                error: function () {
			                    
			                }
			            })
				  }
			  },
			})

	</script>
</body>
</html>