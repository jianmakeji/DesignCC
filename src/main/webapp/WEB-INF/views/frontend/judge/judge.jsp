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
	var judgeId = "${param.judgeId}";
	var round = "${param.round}";
	var pageName = "judge";
	var sliderFinalWidth = 400,
	maxQuickWidth = 1200;
</script>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div id="productList" style="width:100%">
		<div style="text-align:center">
			<i-menu mode="horizontal" :theme="theme1" active-name="1" @on-select="menuSelect" style="width:100%;margin-top:10px;z-index:0">
		        <menu-item name="1">
		            <icon type="settings"></icon>
		            所有作品
		        </menu-item>
		        <menu-item name="2">
		            <icon type="settings"></icon>
		            已评分作品
		        </menu-item>
		        
		        <menu-item name="3">
		            <icon type="settings"></icon>
		           未评分作品
		        </menu-item>
		    </i-menu>
		</div>
		
    
		<ul class="cd-items cd-container">
			<li v-for="item in list" class="cd-item" >
				<p class="cd-score">{{item.score}}分</p>
				<img :src="item.pimage" :id="item.id" v-on:click="openDetail" style="cursor:pointer;">
				<p class="cd-trigger">{{item.title}}</p>
			</li>
		</ul>
		<div style="text-align:center;margin-bottom:30px;">
			<page :total="total" @on-change="loadData"></page>
		</div>
		
		<div class="zyFooter">&copy;康乃馨养老产业集团</div>
		
		<div class="cd-quick-view">
			<div class="cd-slider-wrapper">
				<ul class="cd-slider">
					<li class="selected"><img id="productImage" src=""></li>
				</ul>
			</div>
	
			<div class="cd-item-info">
				<h2>{{title}}</h2>
				<p>{{content}}</p>
				<div v-if="h5Address != '' ">
					H5演示版本：<a :href="h5Address"  target="_blank">点击查看详情</a>
				</div>
				<div v-if="videoAddress != '' ">
					视频演示版本：<a :href="videoAddress" target="_blank">点击查看详情</a>
				</div>
				<div>
					<ul class="cd-item-action">
						<li><input type="text" v-model="score" placeholder="请输入分数  0-100 之间数字" style="line-height: 1.5em; color: #ff0000;font-size: 32px;text-align: center;display: block;width: 96px;margin-top:5px;"/></li>
						<li><button class="add-to-cart" v-on:click="scoreBtnClick">打   分</button></li>
					</ul>
				</div>
			</div>
			
			<a href="#0" class="cd-close" v-on:click="closeQuickView">Close</a>
		</div>
	</div>
	<script src="resources/js/lib/jquery-1.10.2.min.js"></script>
	<script src="resources/js/lib/vue.min.js"></script>
	<script src="resources/js/lib/velocity.min.js"></script>
	<script src="resources/frontend/js/src/header.js"></script>
	<script src="resources/js/lib/iview.min.js"></script>
	<!--<script src="resources/frontend/js/src/judge/judge.js"></script>  -->
	<script src="resources/frontend/js/src/config.js"></script>
	<script type="text/javascript">
	
		new Vue({
			  el: '#productList',
			  data () {
			    return {
			      total:0,
			      list:[],
			      theme1: 'light',
			      score:0,
			      title:'',
				  content:'',
				  h5Address:'',
				  videoAddress:'',
				  productionId:'',
				  scoreSign:0,
			    }
			  },
			  created:function(){
				  this.loadData(1);
			  },
			  methods:{
				  menuSelect:function(name){
					if(name == 1){
						this.scoreSign = 0;
					}
					else if (name == 2){
						this.scoreSign = 1;
					}
					else if (name == 3){
						this.scoreSign = 2;
					}
					this.loadData(1);
				  },
				  loadData:function(pageNum){
					  var that = this;
					  $.ajax({
			                url: config.ajaxUrls.judgeToScoreList,
			                type: "post",
			                data: {
			                    userId: judgeId,
			                    round:round,
			                    offset: (pageNum-1)*12,
			                    scoreSign:that.scoreSign,
			                    limit:12
			                },
			                success: function (response) {
			                    if (response.success) {
			                        var results = response.object.list;
			                        that.total = response.object.count;

									for (var i = 0; i < results.length; i++){
										results[i].pimage = results[i].pimage + '?x-oss-process=style/thumb_210_300';
									}
									that.list = results;
			                    } else {
			                        
			                    }
			                },
			                error: function () {
			                    
			                }
			            })
				  },
				  openDetail:function(event){

					  var slectedImageUrl = '';
					  var title = '';
					  var content = '';
					  var score = '';
					  for (var i = 0; i < this.list.length; i++){
						  
						  if (this.list[i].id == event.target.id){
							  slectedImageUrl = this.list[i].pimage;
							  this.content = this.list[i].content;
							  this.score = this.list[i].score;
							  this.title = this.list[i].title;
							  this.h5Address = this.list[i].h5Address;
							  var tempVideoAddress = this.list[i].videoAddress;
							  if (tempVideoAddress.indexOf('width="480" height="400"') >= 0){
								  tempVideoAddress = tempVideoAddress.replace('width="480" height="400"','width="720" height="600"');
							  }
							 
							  if (tempVideoAddress.indexOf('width="640" height="498"') >= 0){
								  tempVideoAddress = tempVideoAddress.replace('width="640" height="498"','width="720" height="600"');
							  }
							  
							  this.videoAddress = "review/videoPreview?videoAddress="+tempVideoAddress.replace(/&/g,'%26');
							  this.productionId = this.list[i].id;
							  break;  
						  }
					  }
					  
					  var selectedImage = $("#"+event.target.id);

					  $('body').addClass('overlay-layer');
					  this.animateQuickView(selectedImage, sliderFinalWidth, maxQuickWidth, 'open');
					  this.updateQuickView(slectedImageUrl.replace('?x-oss-process=style/thumb_210_300',''));
					
				  },
				  updateSlider:function(navigation) {
						var sliderConatiner = navigation.parents('.cd-slider-wrapper').find('.cd-slider'),
							activeSlider = sliderConatiner.children('.selected').removeClass('selected');
						if ( navigation.hasClass('cd-next') ) {
							( !activeSlider.is(':last-child') ) ? activeSlider.next().addClass('selected') : sliderConatiner.children('li').eq(0).addClass('selected'); 
						} else {
							( !activeSlider.is(':first-child') ) ? activeSlider.prev().addClass('selected') : sliderConatiner.children('li').last().addClass('selected');
						} 
				  },
				  updateQuickView:function(url) {
						$('.cd-quick-view .cd-slider li').removeClass('selected')
						$('#productImage').attr('src',url);
						$('.cd-quick-view .cd-slider li').addClass('selected');
				  },
				  resizeQuickView:function() {
						var quickViewLeft = ($(window).width() - $('.cd-quick-view').width())/2,
							quickViewTop = ($(window).height() - $('.cd-quick-view').height())/2;
						$('.cd-quick-view').css({
						    "top": quickViewTop,
						    "left": quickViewLeft,
						});
				  }, 
				  closeQuickView:function(finalWidth, maxQuickWidth) {
						var close = $('.cd-close'),
							activeSliderUrl = close.siblings('.cd-slider-wrapper').find('.selected img').attr('src'),
							selectedImage = $('.empty-box').find('img');
						//update the image in the gallery
						if( !$('.cd-quick-view').hasClass('velocity-animating') && $('.cd-quick-view').hasClass('add-content')) {
							selectedImage.attr('src', activeSliderUrl +'?x-oss-process=style/thumb_210_300');
							this.animateQuickView(selectedImage, finalWidth, maxQuickWidth, 'close');
						} else {
							this.closeNoAnimation(selectedImage, finalWidth, maxQuickWidth);
						}
				  },
				  animateQuickView:function(image, finalWidth, maxQuickWidth, animationType) {
						//store some image data (width, top position, ...)
						//store window data to calculate quick view panel position
						var parentListItem = image.parent('.cd-item'),
							topSelected = image.offset().top - $(window).scrollTop(),
							leftSelected = image.offset().left,
							widthSelected = image.width(),
							heightSelected = image.height(),
							windowWidth = $(window).width(),
							windowHeight = $(window).height(),
							finalLeft = (windowWidth - finalWidth)/2,
							finalHeight = finalWidth * heightSelected/widthSelected + 240,
							finalTop = (windowHeight - finalHeight)/2 - 100,
							quickViewWidth = ( windowWidth * .8 < maxQuickWidth ) ? windowWidth * .8 : maxQuickWidth ,
							quickViewLeft = (windowWidth - quickViewWidth)/2;
						
						if (windowHeight < 800){
							finalTop = 20;
						}
						else{
							finalTop = 100;
						}
						
						if( animationType == 'open') {
							//hide the image in the gallery
							parentListItem.addClass('empty-box');
							//place the quick view over the image gallery and give it the dimension of the gallery image
							$('.cd-quick-view').css({
							    "top": topSelected,
							    "left": leftSelected,
							    "width": widthSelected,
							}).velocity({
								//animate the quick view: animate its width and center it in the viewport
								//during this animation, only the slider image is visible
							    'top': finalTop + 'px',
							    'left': finalLeft +'px',
							    'width': finalWidth +'px',
							}, 1000, [ 400, 20 ], function(){
								//animate the quick view: animate its width to the final value
								$('.cd-quick-view').addClass('animate-width').velocity({
									'left': quickViewLeft+'px',
							    	'width': quickViewWidth+'px',
								}, 300, 'ease' ,function(){
									//show quick view content
									$('.cd-quick-view').addClass('add-content');
								});
							}).addClass('is-visible');
						} else {
							//close the quick view reverting the animation
							$('.cd-quick-view').removeClass('add-content').velocity({
							    'top': 20 + 'px',
							    'left': 400+'px',
							    'width': finalWidth+'px',
							}, 200, 'ease', function(){
								$('body').removeClass('overlay-layer');
								$('.cd-quick-view').removeClass('animate-width').velocity({
									"top": topSelected,
								    "left": leftSelected,
								    "width": widthSelected,
								}, 300, 'ease', function(){
									$('.cd-quick-view').removeClass('is-visible');
									parentListItem.removeClass('empty-box');
								});
							});
						}
					},
					closeNoAnimation:function(image, finalWidth, maxQuickWidth) {
						var parentListItem = image.parent('.cd-item'),
							topSelected = image.offset().top - $(window).scrollTop(),
							leftSelected = image.offset().left,
							widthSelected = image.width();

						//close the quick view reverting the animation
						$('body').removeClass('overlay-layer');
						parentListItem.removeClass('empty-box');
						$('.cd-quick-view').velocity("stop").removeClass('add-content animate-width is-visible').css({
							"top": topSelected,
						    "left": leftSelected,
						    "width": widthSelected,
						});
					},
					scoreBtnClick:function(){
				            reg=/^[0-9]+$/;
				            let that = this;
					        if(reg.test(this.score)){
					            $.ajax({
					                url:config.ajaxUrls.judgeScore,
					                type:"post",
					                data:{
					                    score:parseInt(this.score),
					                    userId:judgeId,
					                    round:round,
					                    productionId:this.productionId
					                },
					                success:function(response){
					                    if (response.success) {
					                        for (var i = 0; i < that.list.length; i++){
					  						  if (that.list[i].id == that.productionId){
					  							  that.list[i].score = that.score;
					  							  break;  
					  						 	}
					  					  	}
					                        that.closeQuickView();
					                    } else {
					                        functions.ajaxReturnErrorHandler(response.message);
					                    }
					                },
					                error:function(){
					                    functions.ajaxErrorHandler();
					                }
					            })
					        }else{
					            $().toastmessage("showErrorToast",config.messages.scoreError);
					        }
					}
			  },
			})

	</script>
</body>
</html>