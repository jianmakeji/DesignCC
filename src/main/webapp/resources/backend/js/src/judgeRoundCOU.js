var judgeRoundCOU=(function(config,functions){
    return{
        initData:function(id){
            ZYCOUHandler.getDataDetail(config.ajaxUrls.judgeRoundDetail.replace(":id",id),{id:id},function(data){
                $("#roundName").val(data.roundName);
                $("#describes").val(data.describes);
            });
        }
    }
})(config,functions);

$(document).ready(function(){

    var submitUrl=config.ajaxUrls.judgeRoundCreate;

    if(id){
        judgeRoundCOU.initData(id);
        submitUrl=config.ajaxUrls.judgeRoundUpdate
    }
    var zyFormHandler=new ZYFormHandler({
        submitUrl:submitUrl,
        redirectUrl:config.viewUrls.judgeRoundMgr
    });
    $("#myForm").validate({
        ignore:[],
        rules:{
            roundName:{
                required:true,
                maxlength:32
            }
        },
        messages:{
            roundName:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            }
        },
        submitHandler:function(form) {
            var data=null;
            if(id){
                data={
                    id:id
                }
            }
            zyFormHandler.submitFormWithJSON(form,data);
        }
    });
});