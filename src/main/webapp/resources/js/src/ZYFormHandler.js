function ZYFormHandler(params){
    this.submitUrl=params.submitUrl;
    this.redirectUrl=params.redirectUrl;
    this.callback=params.callback;
    this.successMessage=params.successMessage;
}
/**
 * form控件提交form，form-data，需要jquery-form插件
 * @param form
 * @param data 额外的参数
 */
ZYFormHandler.prototype.submitForm=function(form,data){
    var me=this;
    functions.showLoading();
    $(form).ajaxSubmit({
        url:me.submitUrl,
        type:"post",
        dataType:"json",
        data:data,
        success:function(response){
            if(response.success){
                if(me.redirectUrl){
                    $().toastmessage("showSuccessToast",me.successMessage?
                        me.successMessage:config.messages.optSuccRedirect);
                    setTimeout(function(){
                        window.location.href=me.redirectUrl;
                    },3000);
                }else{
                    $().toastmessage("showSuccessToast",me.successMessage?
                        me.successMessage:config.messages.optSuccess);
                    functions.hideLoading();
                    if(me.callback){
                        me.callback(response);
                    }
                }

            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }
        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });
}
/**
 * 用ajax提交form，数据以json字符串的形式提交，需要jquery.serialize-object插件
 * @param form
 * @param data 额外参数
 */
ZYFormHandler.prototype.submitFormWithJSON=function(form,data){
    var me=this,
        formObj=form?$(form).serializeObject():{};

    if(data){
        $.extend(formObj,data);
    }
    functions.showLoading();
    $.ajax({
        url:me.submitUrl,
        type:"post",
        dataType:"json",
        contentType :"application/json; charset=UTF-8",
        data:JSON.stringify(formObj),
        success:function(response){
            if(response.success){
                if(me.redirectUrl){
                    $().toastmessage("showSuccessToast",me.successMessage?
                        me.successMessage:config.messages.optSuccRedirect);
                    setTimeout(function(){
                        window.location.href=me.redirectUrl;
                    },3000);
                }else{
                    $().toastmessage("showSuccessToast",me.successMessage?
                        me.successMessage:config.messages.optSuccess);
                    functions.hideLoading();
                    if(me.callback){
                        me.callback(response);
                    }
                }
            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }
        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });
}
