$(document).ready(function () {
    var zyFormHandler = new ZYFormHandler({
        submitUrl: config.ajaxUrls.resetPwd
    });
    $("#myForm").validate({
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
            zyFormHandler.submitForm(form);
        }
    });
});
