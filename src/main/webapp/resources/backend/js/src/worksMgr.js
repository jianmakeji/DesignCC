function getRoundScore(event,productionId){
		console.log(event.pageX);
		console.log(event.pageY);
    	$.ajax({
            url:config.ajaxUrls.getRoundScoreBean,
            type:"post",
            data:{
            	productionId:productionId
            },
            success: function (response) {
                if (response.success) {
                	console.log(response.object);
                	var length = response.object.length;
                	var height = (length + 1 ) * 25;
                	$("#popPanel").show();
                	$("#popPanel").css({"width":"200px","height":"auto","position":"absolute","top":event.pageY - height,"left":event.pageX,"border":"1px solid","background-color":"#eaeaea","box-shadow":"5px 5px 5px #888888"});
                	$("#roundResultTableBody").empty();
                	for (var i = 0; i < length; i++){
                		$("#roundResultTableBody").append("<tr><td> "+response.object[i].round+"</td><td>"+response.object[i].averageScore+"</td></tr>");
                	}
                	
                } else {
                    functions.ajaxReturnErrorHandler(response.message);
                }

            },
            error: function () {
                functions.ajaxErrorHandler();
            }
        })
    }

var worksMgr=(function(config,functions){

    return {
        judgeRoundList:null,
        dataTable:null,
        initTable:function(){
            var me=this;
            this.dataTable= new ZYTableHandler({
                ownTable:function(){
                    var ownTable=$("#myTable").dataTable({
                        "bServerSide": true,
                        "sAjaxSource": config.ajaxUrls.worksGetByPage,
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
							{ "mDataProp": "id"},
                            { "mDataProp": "thumb",
                                "fnRender":function(oObj){
                                    return '<img class="thumb" src="'+oObj.aData.thumb+'">';
                                }
                            },
                            { "mDataProp": "title"},
                            { "mDataProp": "groupId",
                                "fnRender":function(oObj){
                                    return config.workGroup[oObj.aData.groupId];
                                }
                            },
                            { "mDataProp": "category",
                               "fnRender":function(oObj){
                                	if (oObj.aData.category == 1){
                                		return '生活辅助类';
                                	}
                                	else if (oObj.aData.category == 2){
                                		return '智能养老类';
                                	}
                                	else if (oObj.aData.category == 3){
                                		return '综合设计类';
                                	}
                                }
                             },
                            { "mDataProp": "score",
                                "fnRender":function(oObj){
                                	if (oObj.aData.score){
                                		return '<a href="javascript:void(0)" onclick="getRoundScore(event,'+oObj.aData.id+')">'+oObj.aData.score+'</a>';
                                	}
                                	else{
                                		return '';
                                	}
                                }
                             },
                            { "mDataProp": "status",
                                "fnRender":function(oObj){
                                    var htmlArray=[], selected="";
                                    htmlArray.push("<select class='setWorkStatus' data-id='"+oObj.aData.id+"'>");
                                    for(var o in config.workStatus){
                                        if(oObj.aData.status == o){
                                            htmlArray.push("<option value='"+o+"' selected='"+selected+"'>"+config.workStatus[o]+"</option>");
                                        }else{
                                            htmlArray.push("<option value='"+o+"'>"+config.workStatus[o]+"</option>");
                                        }

                                    }

                                    htmlArray.push("</select>");
                                    return htmlArray.join("");
                                }},
                            { "mDataProp": "round",
                                "fnRender":function(oObj){
                                    var htmlArray=[], selected="";
                                    htmlArray.push("<select class='setWorkRound' data-id='"+oObj.aData.id+"'>");
                                    htmlArray.push("<option value='0'>未设置</option>")
                                    for(var i= 0,len=me.judgeRoundList.length;i<len;i++){
                                        if(me.judgeRoundList[i].id == oObj.aData.round){
                                            htmlArray.push("<option value='"+me.judgeRoundList[i].id+"' selected='"+selected+"'>"+me.judgeRoundList[i].roundName+"</option>");
                                        }else{
                                            htmlArray.push("<option value='"+me.judgeRoundList[i].id+"'>"+me.judgeRoundList[i].roundName+"</option>");
                                        }

                                    }

                                    htmlArray.push("</select>");
                                    return htmlArray.join("");
                                }},
                            { "mDataProp": "opt",
                                "fnRender":function(oObj){
                                    //return '<a href="'+oObj.aData.pId+'" class="delete">删除</a>';

                                    return '<a href="'+config.viewUrls.manageWorkDetail.replace(":id",oObj.aData.id)+'" target="_blank">详情</a>';
                                }
                            }
                        ] ,
                        "fnServerParams": function ( aoData ) {
                            aoData.push(/*{
                             name:"title",
                             value:$("#searchContent").val()
                             },*/{
                                name:"groupId",
                                value:0
                            },{
                                name:"round",
                                value:$("#searchByJudgeRound").val()
                            },{
                                name:"status",
                                value:$("#searchByStatus").val()
                            })
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
                        },
                        'fnDrawCallback': function(table) { 
                        	$("#skipPage").remove();
                            $("#myTable_paginate").append("<p id='skipPage' style='display:inline'>&nbsp;&nbsp;&nbsp;到第 <input type='text' id='changePage' class='input-text' style='width:50px;height:27px'> 页    <a class='btn btn-default shiny' href='javascript:void(0);' id='dataTable-btn' style='text-align:center'>确认</a></p>");    
                              var oTable = $("#myTable").dataTable();    
                              $('#dataTable-btn').click(function(e) {    
                                  if($("#changePage").val() && $("#changePage").val() > 0) {    
                                      var redirectpage = $("#changePage").val() - 1;    
                                  } else {    
                                      var redirectpage = 0;    
                                  }    
                                  oTable.fnPageChange(redirectpage);    
                              });    
                          },  
                    });

                    return ownTable;
                }
            })
        },
        loadJudgeRound:function(callback){
            var me=this;
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.judgeRoundGetByPage,
                type:"get",
                data:{
                    iDisplayStart:0,
                    iDisplayLength:100,
                    sEcho:"xxxx"
                },
                success: function (response) {
                    if (response.success) {
                        var htmlArray=["<option value='0'>全部</option>"];
                        for(var i= 0,len=response.aaData.rjList.length;i<len;i++){
                            htmlArray.push("<option value='"+response.aaData.rjList[i].id+"'>"+response.aaData.rjList[i].roundName+"</option>")
                        }
                        $("#searchByJudgeRound").html(htmlArray.join(""));
                        me.judgeRoundList=response.aaData.rjList;
                        if(callback){
                            callback();
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
        },
        setStatusOfWork:function(id,statusValue){
            var me=this;
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.workSetStatus,
                type:"get",
                data:{
                    id:id,
                    status:statusValue
                },
                success: function (response) {
                    if (response.success) {
                        me.dataTable.tableRedraw();
                        $().toastmessage("showSuccessToast",config.messages.optSuccess);
                        functions.hideLoading();
                    } else {
                    	
                        functions.ajaxReturnErrorHandler(response.message);
                    }

                },
                error: function () {
                    functions.ajaxErrorHandler();
                }
            })
        },
        setRoundOfWork:function(id,roundId){
            var me=this;
            functions.showLoading();
            $.ajax({
                url:config.ajaxUrls.workSetRound,
                type:"post",
                data:{
                    productId:id,
                    round:roundId
                },
                success: function (response) {
                    if (response.success) {
                        me.dataTable.tableRedraw();
                        $().toastmessage("showSuccessToast",config.messages.optSuccess);
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

})(config,functions);

$(document).ready(function(){

    worksMgr.loadJudgeRound(function(){
        worksMgr.initTable();
    });

    $("#popPanel").hide();

    $("#myTable").on("change", ".setWorkStatus", function () {
        worksMgr.setStatusOfWork($(this).data("id"),$(this).val());
    }).on("change", ".setWorkRound", function () {
            worksMgr.setRoundOfWork($(this).data("id"),$(this).val());
        });
    $("#searchBtn").click(function(e){
        worksMgr.dataTable.tableRedraw();
    });
    $("#searchByJudgeRound").change(function(){
        worksMgr.dataTable.tableRedraw();
    });
    $("#searchByStatus").change(function(){
        worksMgr.dataTable.tableRedraw();
    })
    
    $(document).click(function(){  
        $("#popPanel").hide();  
    }); 
    
});

