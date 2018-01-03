var sendEmail=(function(config,functions){
    return{
        loadJudgeRound:function(callback){
            var me=this;
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.judgeRoundGetByPage,
                type:"get",
                data:{
                    iDisplayStart:0,
                    iDisplayLength:100,
                    sEcho:"xxxx"
                },
                success: function (response) {
                    if (response.success) {
                        var htmlArray=[];
                        for(var i= 0,len=response.aaData.rjList.length;i<len;i++){
                            htmlArray.push("<option value='"+response.aaData.rjList[i].id+"'>"+response.aaData.rjList[i].roundName+"</option>")
                        }
                        $("#judgeRound").html(htmlArray.join(""));
                        functions.hideLoading();
                    } else {
                        functions.ajaxReturnErrorHandler(response.message);
                    }

                },
                error: function () {
                    functions.ajaxErrorHandler();
                }
            })
        },
        doAction:function(url,data){
            $.ajax({
                url:url,
                type:"post",
                data:data,
                success:function(response){
                    if(response.success){
                        $().toastmessage("showSuccessToast",config.messages.scoreRefresh);
                        functions.hideLoading();
                    }else{
                        functions.ajaxReturnErrorHandler(response.message);
                    }
                },
                error:function(){
                    functions.ajaxErrorHandler();
                }
            })
        }
    }
})(config,functions);

$(document).ready(function(){

    sendEmail.loadJudgeRound();
    
    $("#computeScore").click(function(){
        sendEmail.doAction(config.ajaxUrls.workComputeScore,{
            round:$("#judgeRound").val()
        });
    });

});