
$(document).ready(function(){
    
    var videoPlay = $("#zyTopVideo>video")[0];
    videoPlay.addEventListener("play",function(){
        $("#zyTopVideo").css("display","block");
        $("#zyTopVideo").css("opacity","0");
        $("#zyTopVideo").clearQueue().animate({opacity:1},1000);
    })
    videoPlay.addEventListener("ended",function(){
        $("#zyTopVideo").css("display","block");
        $("#zyTopVideo").clearQueue().animate({opacity:0},1000,function(){
            $("#zyTopVideo").css("display","none");
        });
    })
    
    
    $("#watchItAgain").on("click",function(){
        videoPlay.play();
        return false;
    })
    
})