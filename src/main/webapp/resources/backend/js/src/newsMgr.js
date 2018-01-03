var newsMgr=(function(config,functions){

    return {

    }
})(config,functions);

$(document).ready(function(){

    var dataTable = new ZYTableHandler({
        ownTable:function(){
            var ownTable=$("#myTable").dataTable({
                "bServerSide": true,
                "sAjaxSource": config.ajaxUrls.newsGetByPage,
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
                    { "mDataProp": "title"},
                    { "mDataProp": "publishTime"},
                    { "mDataProp": "opt",
                        "fnRender":function(oObj){
                            return '<a href="'+config.viewUrls.newsUpdate.replace(":id",oObj.aData.id)+'">修改</a>&nbsp;&nbsp;'+
                                '<a href="'+oObj.aData.id+'" class="delete">删除</a>';
                        }
                    }
                ] ,
                "fnServerParams": function ( aoData ) {
                    /*aoData.push({
                        name:"content",
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

    $("#myTable").on("click","a.delete",function(){
        if(confirm(config.messages.confirmDelete)){
            dataTable.remove(config.ajaxUrls.newsRemove.replace(":id",$(this).attr("href")));
        }
        return false;
    });

});

