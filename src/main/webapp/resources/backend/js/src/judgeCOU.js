var judgeCOU=(function(config,functions){
    return{
        initData:function(id){
            ZYCOUHandler.getDataDetail(config.ajaxUrls.judgeDetail.replace(":id",id),{id:id},function(data){
                $("#name").val(data.name);
                $("#email").val(data.email);
                $("#password").val(data.password);
                $("#imageUrl").val(data.headicon);
                $("#image").attr("src",data.headicon);
                $("#subTitle").val(data.subTitle);
                $("#description").val(data.description);
            });
        }
    }
})(config,functions);

$(document).ready(function(){

    var submitUrl=config.ajaxUrls.judgeCreate;

    if(id){
        judgeCOU.initData(id);
        submitUrl=config.ajaxUrls.judgeUpdate
    }
    var zyFormHandler=new ZYFormHandler({
        submitUrl:submitUrl,
        redirectUrl:config.viewUrls.judgeMgr
    });


    functions.createUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadBtn",
        multiSelection:false,
        multipartParams:{
            fileType:config.uploader.fileType.others
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
        selector: "#description",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#description").val(ed.getContent());
                if(ed.getContent()){
                    $(".error[for='description']").remove();
                }
            });
        }
    });
    /*
    tinymce.init({
        selector: "#subTitle",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#subTitle").val(ed.getContent());
                if(ed.getContent()){
                    $(".error[for='subTitle']").remove();
                }
            });
        }
    });
*/
    $("#myForm").validate({
        ignore:[],
        rules:{
            headicon:{
                required:true
            },
            subTitle:{
                required:true
            },
            description:{
                required:true
            },
            name:{
                required:true,
                maxlength:32
            },
            email:{
                required:true,
                email:true,
                maxlength:32
            },
            password:{
                required:true,
                rangelength:[6,20]
            }
        },
        messages:{
            headicon:{
                required:config.validErrors.required
            },
            subTitle:{
                required:config.validErrors.required
            },
            description:{
                required:config.validErrors.required
            },
            name:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            },
            email:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32),
                email:config.validErrors.email
            },
            password:{
                required:config.validErrors.required,
                rangelength: config.validErrors.rangLength.replace("${min}", 6).replace("${max}", 20)
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