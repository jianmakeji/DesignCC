$(document).ready(function () {
    var zyFormHandler = new ZYFormHandler({
        submitUrl: config.ajaxUrls.forgetPwd,
        successMessage: config.messages.emailSend,
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
            rand: {
                required: true
            }
        },
        messages: {
            email: {
                required: config.validErrors.required,
                maxlength: config.validErrors.maxLength.replace("${max}", 32),
                email: config.validErrors.email
            },
            rand: {
                required: config.validErrors.required
            }
        },
        submitHandler: function (form) {
            zyFormHandler.submitForm(form, null);
        }
    });
});
