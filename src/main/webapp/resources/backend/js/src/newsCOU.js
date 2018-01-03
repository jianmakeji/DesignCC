var newsCOU=(function(config,functions){
    return{
        initData:function(id){
            ZYCOUHandler.getDataDetail(config.ajaxUrls.newsDetail.replace(":id",id),{id:id},function(data){
                $("#title").val(data.title);
                $("#publishTime").val(data.publishTime);
                $("#imageUrl").val(data.thumb);
                $("#image").attr("src",data.thumb);
                $("#newsAbstract").val(data.newsAbstract);
                $("#content").val(data.content);
            });
        }
    }
})(config,functions);

$(document).ready(function(){
    var submitUrl=config.ajaxUrls.newsCreate;

    if(id){
        newsCOU.initData(id);
        submitUrl=config.ajaxUrls.newsUpdate
    }
    var zyFormHandler=new ZYFormHandler({
        submitUrl:submitUrl,
        redirectUrl:config.viewUrls.newsMgr
    });

    functions.createUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadBtn",
        multiSelection:false,
        multipartParams:{
            fileType:config.uploader.fileType.newsImageFile //1-3后台已经有了，大于3表示others
        },
        uploadContainer:"uploadContainer",
        filesAddedCb:null,
        progressCb:null,
        uploadedCb:function(info,file,up){
            functions.getImageSize(config.ajaxUrls.imageGet+"?imgPath="+info.object,function(imageSizeMap){
                if(imageSizeMap.width==imageSizeMap.height){
                    $("#imageUrl").val(config.ajaxUrls.imageGet+"?imgPath="+info.object);

                    $("#image").attr("src",config.ajaxUrls.imageGet+"?imgPath="+info.object);

                    $(".error[for='imageUrl']").remove();
                }else{
                    $().toastmessage("showErrorToast",config.messages.imageSizeError);
                }
            });
        }
    });


    tinymce.init({
        selector: "#content",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#content").val(ed.getContent());
                if(ed.getContent()){
                    $(".error[for='content']").remove();
                }
            });
        }
    });

    $("#myForm").validate({
        ignore:[],
        rules:{
            thumb:{
                required:true
            },
            newsAbstract:{
                required:true
            },
            content:{
                required:true
            },
            title:{
                required:true,
                maxlength:32
            }
        },
        messages:{
            thumb:{
                required:config.validErrors.required
            },
            newsAbstract:{
                required:config.validErrors.required
            },
            content:{
                required:config.validErrors.required
            },
            title:{
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