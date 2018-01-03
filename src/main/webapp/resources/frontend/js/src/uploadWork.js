var uploadWork = (function (config, functions) {
    return {
        createUploads: function () {
            for (var i = 1; i <= 7; i++) {

                //使用立即执行，将i作为参数传入，不然受闭包影响，i总是4
                (function (i) {
                    functions.createUploader({
                        maxSize: config.uploader.sizes.img,
                        filter: config.uploader.filters.img,
                        uploadBtn: "uploadImageBtn" + i,
                        multiSelection: false,
                        multipartParams: {
                            fileType: config.uploader.fileType.productionFile
                        },
                        uploadContainer: "uploadImageContainer" + i,
                        filesAddCb: null,
                        progressCb: null,
                        uploadedCb: function (info, file, up) {

                            $("#imageUrl" + i).val(config.ajaxUrls.imageGet + "?imgPath=" + info.object);

                            $("#image" + i).attr("src", config.ajaxUrls.imageGet + "?imgPath=" + info.object);

                            $(".error[for='imageUrl" + i + "']").remove();
                        }
                    });
                })(i);

            }
        },
        createThumbUploads: function () {
            for (var i = 1; i <= 2; i++) {

                //使用立即执行，将i作为参数传入，不然受闭包影响，i总是4
                (function (i) {
                    functions.createUploader({
                        maxSize: config.uploader.sizes.img,
                        filter: config.uploader.filters.img,
                        uploadBtn: "uploadThumbBtn" + i,
                        multiSelection: false,
                        multipartParams: {
                            fileType: config.uploader.fileType.productionFile
                        },
                        uploadContainer: "uploadThumbContainer" + i,
                        filesAddCb: null,
                        progressCb: null,
                        uploadedCb: function (info, file, up) {

                            functions.getImageSize(config.ajaxUrls.imageGet + "?imgPath=" + info.object, function (imageSizeMap) {
                                if (imageSizeMap.width == imageSizeMap.height) {
                                    $("#thumbUrl" + i).val(config.ajaxUrls.imageGet + "?imgPath=" + info.object);

                                    $("#thumb" + i).attr("src", config.ajaxUrls.imageGet + "?imgPath=" + info.object);

                                    $(".error[for='thumbUrl" + i + "']").remove();
                                } else {
                                    $().toastmessage("showErrorToast", config.messages.imageSizeError);
                                }
                            });
                        }
                    });
                })(i);

            }
        },
        initData: function (id) {
            ZYCOUHandler.getDataDetail(config.ajaxUrls.workDetail.replace(":id", id), {id: id}, function (data) {
                var pImages = JSON.parse(data.pimage), personInfoPanel, workInfoPanel, targetPanel;

                $("#zySelectPersonType input[value='" + data.participantType + "']").prop("checked", true);
                targetPanel = $("#zySelectPersonType input:checked").data("target");
                $(".zyPersonInfoPanel").addClass("zyHidden");
                $(targetPanel).removeClass("zyHidden");
                personInfoPanel = $(".zyPersonInfoPanel").not(".zyHidden");
                personInfoPanel.find('input[name="participantName"]').val(data.participantName);
                personInfoPanel.find('input[name="participantIdNumber"]').val(data.participantIdNumber);
                personInfoPanel.find('textarea[name="participantBrief"]').val(data.participantBrief);
                personInfoPanel.find('textarea[name="teamMember"]').val(data.teamMember);
                personInfoPanel.find("input[name='affiliatedUnit']").val(data.affiliatedUnit);
                
                $("#zySelectGroup input[value='" + data.groupId + "']").prop("checked", true);
                targetPanel = $("#zySelectGroup input:checked").data("target");
                $(".zyWorkInfoPanel").addClass("zyHidden");
                $(targetPanel).removeClass("zyHidden");

               
                workInfoPanel = $(".zyWorkInfoPanel").not(".zyHidden");
                workInfoPanel.find("input[name='title']").val(data.title);
                workInfoPanel.find("input[name='weblink']").val(data.weblink);
                workInfoPanel.find("textarea[name='content']").val(data.content);
                workInfoPanel.find(".zyActionThumbImageValue").val(data.thumb);
                workInfoPanel.find(".zyActionThumbImage").attr("src", data.thumb);
                
                if (data.groupId == 1){
                	 $("#zyCategory").find(".zyInput").val(config.workType[data.category]);
                     $("#zyCategory").find(".zySelectValue").val(data.category);
                	 $("#uploadAttachContainer").find(".zyActionAttachValue").val(data.attachFile);
                	 $("#uploadAttachContainer").find(".zyActionAttach").attr("href", data.attachFile).
                         text(functions.getFileInfo(data.attachFile)["filenameWithExt"]);
                }
                else if (data.groupId == 2){
                	 $("#zyConceptCategory").find(".zyInput").val(config.workType[data.category]);
                	 $("#zyConceptCategory").find(".zySelectValue").val(data.category);
                	 $("#conceptUploadAttachContainer").find(".zyActionAttachValue").val(data.attachFile);
                	 $("#conceptUploadAttachContainer").find(".zyActionAttach").attr("href", data.attachFile).
                         text(functions.getFileInfo(data.attachFile)["filenameWithExt"]);
                }
               

                for (var i = 0, len = pImages.length; i < len; i++) {
                    workInfoPanel.find(".zyActionOtherImage").eq(i).attr("src", pImages[i]);
                    workInfoPanel.find(".zyActionOtherImageValue").eq(i).val(pImages[i]);
                }
            });
        },
        getSubmitData: function () {
            var personInfoPanel, workInfoPanel;
            var obj = {};
            obj.participantType = $("#zySelectPersonType input:checked").val();
            personInfoPanel = $(".zyPersonInfoPanel").not(".zyHidden");
            obj.participantName = personInfoPanel.find('input[name="participantName"]').val();
            obj.participantIdNumber = personInfoPanel.find('input[name="participantIdNumber"]').val();
            obj.participantBrief = personInfoPanel.find('textarea[name="participantBrief"]').val();
            obj.teamMember = personInfoPanel.find('textarea[name="teamMember"]').val();
            obj.affiliatedUnit = personInfoPanel.find("input[name='affiliatedUnit']").val();

            workInfoPanel = $(".zyWorkInfoPanel").not(".zyHidden");
            obj.groupId = $("#zySelectGroup input:checked").val();
            obj.group = config.workGroup[obj.groupId];
            obj.category = workInfoPanel.find("input[name='category']").val();
            obj.title = workInfoPanel.find("input[name='title']").val();
            obj.weblink = workInfoPanel.find("input[name='weblink']").val();
            obj.content = workInfoPanel.find("textarea[name='content']").val();
            obj.thumb = workInfoPanel.find("input[name='thumb']").val();
            if (obj.groupId == 1){
            	obj.attachFile = workInfoPanel.find("input[name='attachFile']").val();
            }
            else if (obj.groupId == 2){
            	obj.attachFile = workInfoPanel.find("input[name='conceptAttachFile']").val();
            }
            
            obj.pimageArray = [];
            workInfoPanel.find("input[name='image']").each(function () {
                if ($(this).val()) {
                    obj.pimageArray.push($(this).val());
                }
            });
            obj.pimage = JSON.stringify(obj.pimageArray);
            obj.status = 1;

            if (id) {
                obj.id = id;
            }

            return obj;
        },
        goToStep: function (stepId) {
            var personInfoPanel, workInfoPanel, canGo = true, previewTpl;
            if (stepId == "#zyStep1") {
                $(".zyStepTip").addClass("zyHidden");
                $("#zyInfoPanel").removeClass("zyHidden");
                $("#zyPreview").addClass("zyHidden");
                $("#zyStep1Tip").removeClass("zyHidden");
            }
            if (stepId == "#zyStep2") {
                //检测数据
                personInfoPanel = $(".zyPersonInfoPanel").not(".zyHidden");
                personInfoPanel.find(".zyActionRequired").each(function (index, el) {
                    if (!$(this).val()) {
                        canGo = false;
                    }
                });

                if (!canGo) {
                    $().toastmessage("showErrorToast", config.messages.pleaseEnterPersonalInfo);
                    return false;
                }


                $(".zyStepTip").addClass("zyHidden");
                $("#zyInfoPanel").removeClass("zyHidden");
                $("#zyPreview").addClass("zyHidden");
                $("#zyStep2Tip").removeClass("zyHidden");

            }
            if (stepId == "#zyPreview") {
                //检测数据，设置数据
                workInfoPanel = $(".zyWorkInfoPanel").not(".zyHidden");
                workInfoPanel.find(".zyActionRequired").each(function (index, el) {
                    if (!$(this).val()) {
                        canGo = false;
                    }
                });

                if (!canGo) {
                    $().toastmessage("showErrorToast", config.messages.pleaseEnterWorkInfo);
                    return false;
                }

                previewTpl = $("#zyPreviewTpl").html();
                $("#zyPreviewContent").html(juicer(previewTpl, this.getSubmitData()));

                $("#zyInfoPanel").addClass("zyHidden");
            }
            $(".zyStep .zyStepItem.zyActive").removeClass("zyActive");
            $(".zyStepItem[data-target='" + stepId + "']").addClass("zyActive");
            $(".zyStepPanel").addClass("zyHidden");
            $(stepId).removeClass("zyHidden");
        }
    }
})(config, functions);

$(document).ready(function () {

    //重新定义juicer的取变量标签，因为和jstl的重复了
    juicer.set({
        'tag::interpolateOpen': '$ZY{'
    });


    var submitUrl = config.ajaxUrls.workCreate;

    if (id) {
        uploadWork.initData(id);
        submitUrl = config.ajaxUrls.workUpdate
    }
    var zyFormHandler = new ZYFormHandler({
        submitUrl: submitUrl,
        redirectUrl: config.viewUrls.works
    });

    uploadWork.createUploads();
    uploadWork.createThumbUploads();

    functions.createUploader({
        maxSize: config.uploader.sizes.zip,
        filter: config.uploader.filters.zip,
        uploadBtn: "uploadAttachBtn",
        multiSelection: false,
        multipartParams: {
            fileType: config.uploader.fileType.attachFile
        },
        uploadContainer: "uploadAttachContainer",
        filesAddCb: null,
        progressCb: function () {
            $("#attach").text("上传中...");
        },
        uploadedCb: function (info, file, up) {
            $("#attachUrl").val(config.ajaxUrls.fileGet + "?filePath=" + info.object);

            $("#attach").attr("href", config.ajaxUrls.fileGet + "?filePath=" + info.object).text(file.name);

            $(".error[for='attachUrl']").remove();
        }
    });

    functions.createUploader({
        maxSize: config.uploader.sizes.zip,
        filter: config.uploader.filters.zip,
        uploadBtn: "conceptUploadAttachBtn",
        multiSelection: false,
        multipartParams: {
            fileType: config.uploader.fileType.attachFile
        },
        uploadContainer: "conceptUploadAttachContainer",
        filesAddCb: null,
        progressCb: function () {
            $("#conceptAttach").text("上传中...");
        },
        uploadedCb: function (info, file, up) {
            $("#conceptAttachUrl").val(config.ajaxUrls.fileGet + "?filePath=" + info.object);

            $("#conceptAttach").attr("href", config.ajaxUrls.fileGet + "?filePath=" + info.object).text(file.name);

            $(".error[for='conceptAttachUrl']").remove();
        }
    });
    
    $(".zyStep .zyStepItem, .zyActionNavBtn").click(function () {
        var targetPanel = $(this).data("target");
        uploadWork.goToStep(targetPanel);

    });

    /***************************填写参赛者信息******************************/
    $("#zySelectPersonType input[type='radio']").click(function () {
        var targetPanel = $(this).data("target");
        $(".zyPersonInfoPanel").addClass("zyHidden");
        $(targetPanel).removeClass("zyHidden");
    });

    /*****************************作品上传**********************************/
    $("#zySelectGroup input[type='radio']").click(function () {
        var targetPanel = $(this).data("target");
        $(".zyWorkInfoPanel").addClass("zyHidden");
        $(targetPanel).removeClass("zyHidden");
    });

    $("#zySubmitData").click(function () {
        zyFormHandler.submitFormWithJSON(null, uploadWork.getSubmitData());
    });
});
