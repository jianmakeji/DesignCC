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
                        $().toastmessage("showSuccessToast",config.messages.optSuccess);
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

    tinymce.init({
        selector: "#emailContent",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#emailContent").val(ed.getContent());
                if(ed.getContent()){
                    $(".error[for='emailContent']").remove();
                }
            });
        }
    });
    
    $("#sendEmail").click(function(){
        sendEmail.doAction(config.ajaxUrls.sendEmail,{
            round:$("#judgeRound").val(),
        	emailContent:$("#emailContent").val(),
        	email:$("#email").val()
        });
    });
   
});