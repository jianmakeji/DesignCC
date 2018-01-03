var works = (function (config, functions) {
    return {
        remove: function (el) {
            functions.showLoading();
            var me = this;
            $.ajax({
                url: config.ajaxUrls.workRemove.replace(":id", el.attr("href")),
                type: "get",
                dataType: "json",
                success: function (response) {
                    if (response.success) {
                        $().toastmessage("showSuccessToast", config.messages.optSuccess);
                        el.parents("tr").remove();
                        functions.hideLoading();
                    } else {
                        functions.ajaxReturnErrorHandler(response.message);
                    }

                },
                error: function () {
                    functions.ajaxErrorHandler();
                }
            });
        },
        loadData: function (start, callback) {
            $.ajax({
                url: config.ajaxUrls.worksGetByPage,
                type: "get",
                data: {
                    groupId: 0,
                    category: 0,
                    status: 0,
                    userId: 0,
                    round:0,
                    iDisplayStart: start,
                    iDisplayLength: 10,
                    sEcho: "zy"
                },
                success: function (response) {
                    if (response.success) {
                        var results = response.aaData,
                            totalCount = response.iTotalRecords, trTpl;
                        for (var i = 0, len = results.length; i < len; i++) {
                            results[i].canEdit = true;
                            if (results[i].status > 1) {
                                results[i].canEdit = false;
                            }
                            results[i].category = config.workType[results[i].category];
                            results[i].status = config.workStatus[results[i].status];
                            results[i].group = config.workGroup[results[i].groupId];
                        }

                        trTpl = $("#zyTrTpl").html();
                        $("#myTable tbody").html(juicer(trTpl, {
                            items: results
                        }));

                        if (callback) {
                            callback(totalCount);
                        }

                        functions.hideLoading();
                    } else {
                        functions.ajaxReturnErrorHandler(response.message);
                    }
                },
                error: function () {
                    functions.ajaxErrorHandler();
                }
            })
        }
    }
})(config, functions);

$(document).ready(function () {
    //重新定义juicer的取变量标签，因为和jstl的重复了
    juicer.set({
        'tag::interpolateOpen': '$ZY{'
    });

    works.loadData(0, function (totalCount) {
        var totalPage = totalCount / 10;
        var totalRecords = totalCount;
        var pageNo = 1;
        kkpager.generPageHtml({
            pno: pageNo,
            isGoPage: false,
            isShowTotalPage: false,
            isShowCurrPage: false,
            mode: 'click', //设置为click模式
            //总页码
            total: totalPage,
            //总数据条数
            totalRecords: totalRecords,
            //点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
            //适用于不刷新页面，比如ajax
            click: function (n) {
                //这里可以做自已的处理
                //...
                //处理完后可以手动条用selectPage进行页码选中切换
                this.selectPage(n);

                works.loadData((n - 1) * 10);

            }
        });
    });

    $("#myTable").on("click", ".zyAction.zyIconRemove", function () {
        if (confirm(config.messages.confirmDelete)) {
            works.remove($(this));
        }
        return false;
    })
});