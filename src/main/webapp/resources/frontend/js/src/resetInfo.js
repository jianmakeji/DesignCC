$(document).ready(function () {
    var zyFormHandler1 = new ZYFormHandler({
        submitUrl: config.ajaxUrls.resetInfo
    });
    var zyFormHandler2 = new ZYFormHandler({
        submitUrl: config.ajaxUrls.resetPwd
    });
    $("#myForm").validate({
        ignore: [],
        rules: {
            realname: {
                required: true,
                maxlength: 8
            },
            address: {
                required: true,
                maxlength: 32
            },
            mobile: {
                required: true
            }
        },
        messages: {
            realname: {
                required: config.validErrors.required,
                maxlength: config.validErrors.maxLength.replace("${max}", 8)
            },
            address: {
                required: config.validErrors.required,
                maxlength: config.validErrors.maxLength.replace("${max}", 32)
            },
            mobile: {
                required: config.validErrors.required
            }
        },
        submitHandler: function (form) {
            zyFormHandler1.submitFormWithJSON(form);
        }
    });

    $("#myForm1").validate({
        ignore: [],
        rules: {
            newPwd: {
                required: true,
                rangelength: [6, 20]
            },
            confirmPwd: {
                equalTo: "#password"
            }
        },
        messages: {
            newPwd: {
                required: config.validErrors.required,
                rangelength: config.validErrors.rangLength.replace("${max}", 20).replace("${min}", 6)
            },
            confirmPwd: {
                equalTo: config.validErrors.pwdNotEqual
            }
        },
        submitHandler: function (form) {
            zyFormHandler2.submitForm(form);
        }
    });
});
