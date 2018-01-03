$(document).ready(function () {
    var zyFormHandler = new ZYFormHandler({
        submitUrl: config.ajaxUrls.register,
        successMessage: config.messages.registerSuccess,
        redirectUrl: null
    });
    $("#myForm").validate({
        ignore: [],
        rules: {
            email: {
                required: true,
                maxlength: 32,
                email: true
            },
            mobile: {
                required: true,
                maxlength: 18
            },
            realname: {
                required: true,
                maxlength: 32
            },
            address: {
                required: true,
                maxlength: 32
            },
            password: {
                required: true,
                rangelength: [6, 20]
            },
            confirmPwd: {
                equalTo: "#password"
            },
            activecode: {
                required: true
            }
        },
        messages: {
            email: {
                required: config.validErrors.required,
                maxlength: config.validErrors.maxLength.replace("${max}", 32),
                email: config.validErrors.email
            },
            mobile: {
                required: config.validErrors.required,
                maxlength: config.validErrors.maxLength.replace("${max}", 18)
            },
            realname: {
                required: config.validErrors.required,
                maxlength: config.validErrors.maxLength.replace("${max}", 32)
            },
            address: {
                required: config.validErrors.required,
                maxlength: config.validErrors.maxLength.replace("${max}", 32)
            },
            password: {
                required: config.validErrors.required,
                rangelength: config.validErrors.rangLength.replace("${max}", 20).replace("${min}", 6)
            },
            confirmPwd: {
                equalTo: config.validErrors.pwdNotEqual
            },
            activecode: {
                required: config.validErrors.required
            }
        },
        submitHandler: function (form) {
            zyFormHandler.submitFormWithJSON(form, null);
        }
    });
    
    $(".zyActiveCode").on("click",function(){
        var timeStamp = '?' + new Date().getTime() + 'r' + Math.random();
        $(this).attr("src","user/getCode"+timeStamp);
    })
});
