
var judgeIndex = (function (config, functions) {
	var current = 0;
    return {
        
        initPager:function(totalCount){
            var me=this;
            var totalPage = totalCount / config.perLoadCounts.table;
            var totalRecords = totalCount;
            if (current == 0){
            	current = 1;
            }
            kkpager.generPageHtml({
                pno: current,
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
                	current = n;
                    this.selectPage(n);

                    me.loadData((n - 1) * config.perLoadCounts.table);
                    
                }
            },true);
        },
        loadData: function (callback) {
            if (current == 0){
            	current = 1;
            }
            $.ajax({
                url: config.ajaxUrls.judgeToScoreList,
                type: "post",
                data: {
                    userId: judgeId,
                    round:round,
                    offset: (current-1)*config.perLoadCounts.table,
                    scoreSign:$("#zyFilter .zyActive").data("value"),
                    limit:config.perLoadCounts.table
                },
                success: function (response) {
                    if (response.success) {
                        var results = response.object.list,
                            totalCount = response.object.count, listTpl;

                        listTpl = $("#zyListTpl").html();
                        $("#ZyList").html(juicer(listTpl, {
                            pageNo:current,
                            items: results
                        }));

                        if (typeof callback === "function") {
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

    $("#zyFilter .zyItem").click(function(){
        $("#zyFilter .zyActive").removeClass("zyActive");
        $(this).addClass("zyActive");
        judgeIndex.current = 0;
        judgeIndex.loadData(function(totalCount){
            judgeIndex.initPager(totalCount);
        });
    });

    judgeIndex.loadData(function (totalCount) {
        judgeIndex.initPager(totalCount);
    });
});