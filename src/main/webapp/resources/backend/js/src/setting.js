$(document).ready(function(){
    var zyFormHandler = new ZYFormHandler({
        submitUrl:"#",
        redirectUrl:null
    });

    $("#myForm").submit(function(){
        zyFormHandler.submitFormWithPS($(this));
        return false;
    });
});