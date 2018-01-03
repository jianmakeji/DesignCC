function ZYTreeHandler(params){
    this.getAllUrl=params.getAllUrl;
    this.removeUrl=params.removeUrl;
    this.addUrl=params.addUrl;
    this.renameUrl=params.renameUrl;
    this.newDefaultName=params.newDefaultName;
}
ZYTreeHandler.prototype.getSettings=function(){
    var me=this;
    return {
        async: {
            enable: true,
            type:"GET",
            url:me.getAllUrl,
            //autoParam:["id", "name=n", "level=lv"],
            autoParam:["id"],
            //otherParam:{otherParam:"zTreeAsyncTest"},
            dataFilter: me.filter
        },
        view: {
            expandSpeed:"",
            addHoverDom: function(treeId, treeNode){
                var sObj = $("#" + treeNode.tId + "_span");

                if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
                var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add' onfocus='this.blur();'></span>";
                sObj.after(addStr);
                var btn = $("#addBtn_"+treeNode.tId);
                if (btn){
                    btn.bind("click", function(){

                        //这里讲数据发送给后台
                        me.add(treeNode);
                        return false;
                    });
                }
            },
            removeHoverDom: function(treeId, treeNode) {
                $("#addBtn_"+treeNode.tId).unbind().remove();
            },
            selectedMulti: false
        },
        data: {
            keep:{
                parent:true
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        edit: {
            enable: true,
            editNameSelectAll: true
        },
        callback: {
            beforeRemove: function(treeId, treeNode){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.selectNode(treeNode);
                return confirm("确认删除 -- " + treeNode.name + " 吗？");
            },
            onRemove:function(e, treeId, treeNode){
                //这里讲数据发给后台
                me.remove(treeNode.id,treeNode.pId);
            },
            onRename:function(e,treeId,treeNode){
                me.rename(treeNode.id,treeNode.pId,treeNode.name);
            },
            beforeRename: function(treeId, treeNode, newName) {
                if (newName.length == 0) {
                    $().toastmessage("showSuccessToast",config.validErrors.required);
                    return false;
                }
                return true;
            },
            beforeDrag: function(treeId, treeNodes){
                return false;
            }
        }
    }
},
ZYTreeHandler.prototype.filter=function(treeId, parentNode, response){
    if(!response.success){
        $().toastmessage("showSuccessToast",config.messages.loadDataError);
        return [];
    }

    //转换数据
    var childNodes=response.object;

    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].isParent=true;
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
ZYTreeHandler.prototype.remove=function(id,pId){
    functions.showLoading();
    var me=this;
    $.ajax({
        url:me.removeUrl.replace(":id",id),
        type:"post",
        data:{
            parentId:pId,
            id:id
        },
        dataType:"json",
        success:function(response){
            if(response.success){
                functions.hideLoading();
                $().toastmessage("showSuccessToast",config.messages.optSuccess);
            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });
}
ZYTreeHandler.prototype.rename=function(id,pId,name){
    functions.showLoading();
    var me=this;
    $.ajax({
        url:me.renameUrl,
        type:"post",
        data:{
            name:name,
            parentId:pId,
            id:id
        },
        dataType:"json",
        success:function(response){
            if(response.success){
                functions.hideLoading();
                $().toastmessage("showSuccessToast",config.messages.optSuccess);
            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });
}
ZYTreeHandler.prototype.add=function(treeNode){
    functions.showLoading();
    var me=this;
    var no=(new Date()).getTime();
    $.ajax({
        url:me.addUrl,
        type:"post",
        data:{
            name:me.newDefaultName+no,
            id:treeNode.id
        },
        dataType:"json",
        success:function(response){
            if(response.success){
                functions.hideLoading();
                $().toastmessage("showSuccessToast",config.messages.optSuccess);

                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if(treeNode.check_Child_State!=-1){
                    zTree.addNodes(treeNode, {id:response.object, pId:treeNode.id, name:me.newDefaultName +no,isParent:true});
                }else{
                    zTree.expandNode(treeNode);
                }

            }else{
                functions.ajaxReturnErrorHandler(response.message);
            }

        },
        error:function(){
            functions.ajaxErrorHandler();
        }
    });
}
