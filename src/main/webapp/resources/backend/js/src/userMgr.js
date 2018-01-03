var userMgr=(function(config,functions){

    return {

    }

})(config,functions);

$(document).ready(function(){

    var dataTable= new ZYTableHandler({
        removeUrl:"#",
        ownTable:function(){
            var ownTable=$("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.userGetByPage,
                "bInfo":true,
                "bLengthChange": false,
                "bFilter": false,
                "bProcessing":true,
                "bSort":false,
                "bAutoWidth": false,
                "iDisplayLength":config.perLoadCounts.table,
                "sPaginationType":"full_numbers",
                "oLanguage": {
                    "sUrl":config.dataTable.langUrl
                },
                "aoColumns": [
                    { "mDataProp": "email"},
                    { "mDataProp": "realname"},
                    { "mDataProp": "mobile"},
                    { "mDataProp": "address"},
                    { "mDataProp": "valid",
                        "fnRender":function(oObj){
                            return config.userStatus[oObj.aData.valid];
                    }},
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            var string='<a class="activeAction" href="'+oObj.aData.id+'" ' +
                                'data-email="'+oObj.aData.email+'" data-target-valid="1">禁用</a>';
                            if(oObj.aData.valid==config.userStatus["1"]){
                                string='<a class="activeAction" href="'+oObj.aData.id+'" ' +
                                    'data-email="'+oObj.aData.email+'" data-target-valid="0">激活</a>';
                            }
                            return string;
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {
                    /*aoData.push({
                        name:"email",
                        value:$("#searchContent").val()
                    })*/
                },
                "fnServerData": function(sSource, aoData, fnCallback) {

                    //回调函数
                    $.ajax({
                        "dataType":'json',
                        "type":"post",
                        "url":sSource,
                        "data":aoData,
                        "success": function (response) {
                            if(response.success===false){
                                functions.ajaxReturnErrorHandler(response.message);
                            }else{
                                var json = {
                                    "sEcho" : response.sEcho
                                };

                                for (var i = 0, iLen = response.aaData.length; i < iLen; i++) {
                                    response.aaData[i].opt="opt";
                                }

                                json.aaData=response.aaData;
                                json.iTotalRecords = response.iTotalRecords;
                                json.iTotalDisplayRecords = response.iTotalDisplayRecords;
                                fnCallback(json);
                            }

                        }
                    });
                },
                "fnFormatNumber":function(iIn){
                    return iIn;
                }
            });

            return ownTable;
        }
    })

    $("#searchBtn").click(function(e){
        dataTable.tableRedraw();
    });

    $("#myTable").on("click","a.activeAction",function(){
        functions.showLoading();
        var me = this,
            email = $(this).data("email"),
            valid = $(this).data("target-valid");
        $.ajax({
            url: config.ajaxUrls.userActiveAction,
            type: "post",
            dataType: "json",
            data:{
                email:email,
                valid:valid
            },
            success: function (response) {
                if (response.success) {
                    $().toastmessage("showSuccessToast", config.messages.optSuccess);
                    dataTable.tableRedraw();
                    functions.hideLoading();
                } else {
                    functions.ajaxReturnErrorHandler(response.message);
                }

            },
            error: function () {
                functions.ajaxErrorHandler();
            }
        });
        return false;
    });
});

