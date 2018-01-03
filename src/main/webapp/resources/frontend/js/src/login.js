var login = (function (config) {
    return {

    }
})(config);
$(document).ready(function () {

    $("#myForm").validate({
        rules: {
            username: {
                required: true,
                email: true,
                maxlength: 30
            },
            password: {
                required: true,
                rangelength: [6, 120]
            }
        },
        messages: {
            username: {
                required: config.validErrors.required,
                email: config.validErrors.email,
                maxlength: config.validErrors.maxLength.replace("${max}", 30)
            },
            password: {
                required: config.validErrors.required,
                rangelength: config.validErrors.rangLength.replace("${min}", 6).replace("${max}", 20)
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
});
