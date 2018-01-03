function ZYTableHandler(params) {
    this.ownTable = params.ownTable();
}
ZYTableHandler.prototype.tableRedraw = function () {
    this.ownTable.fnSettings()._iDisplayStart = 0;
    this.ownTable.fnDraw();
};
ZYTableHandler.prototype.remove = function (url,data) {
    functions.showLoading();
    var me = this;
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        data:data,
        success: function (response) {
            if (response.success) {
                $().toastmessage("showSuccessToast", config.messages.optSuccess);
                me.ownTable.fnDraw();
                functions.hideLoading();
            } else {
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error: function () {
            functions.ajaxErrorHandler();
        }
    });
};