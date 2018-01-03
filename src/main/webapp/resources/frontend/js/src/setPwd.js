$(document).ready(function () {
    var zyFormHandler = new ZYFormHandler({
        submitUrl: config.ajaxUrls.setPwd,
        successMessage: config.messages.optSuccessToLogin,
        redirectUrl: config.viewUrls.login
    });
    $("#myForm").validate({
        ignore: [],
        rules: {
            email: {
                required: true,
                email: true
            },
            newPwd: {
                required: true,
                rangelength: [6, 20]
            },
            confirmPwd: {
                equalTo: "#newPwd"
            }
        },
        messages: {
            email: {
                required: config.validErrors.required,
                email: config.validErrors.email
            },
            newPwd: {
                required: config.validErrors.required,
                rangelength: config.validErrors.rangLength.replace("${max}", 20).replace("${min}", 6)
            },
            confirmPwd: {
                equalTo: config.validErrors.pwdNotEqual
            }
        },
        submitHandler: function (form) {
            zyFormHandler.submitForm(form, null);
        }
    });
});
