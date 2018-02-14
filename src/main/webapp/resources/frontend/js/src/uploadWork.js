var uploadWork = (function (config, functions) {
    return {
        
        initData: function (id) {
            ZYCOUHandler.getDataDetail(config.ajaxUrls.workDetail.replace(":id", id), {id: id}, function (data) {
                var personInfoPanel, workInfoPanel, targetPanel;

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
                
                
                workInfoPanel = $(".zyWorkInfoPanel");
                workInfoPanel.find("input[name='title']").val(data.title);
                workInfoPanel.find("input[name='h5Address']").val(data.h5Address);
                workInfoPanel.find("textarea[name='videoAddress']").val(data.videoAddress);
                workInfoPanel.find("textarea[name='content']").val(data.content);
                workInfoPanel.find(".zyActionOtherImage").attr("src", data.pimage + "?x-oss-process=style/thumb_210_300");
                
                
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
            
            obj.title = workInfoPanel.find("input[name='title']").val();
            obj.content = workInfoPanel.find("textarea[name='content']").val();
            obj.videoAddress = workInfoPanel.find("textarea[name='videoAddress']").val();
            obj.h5Address = workInfoPanel.find("input[name='h5Address']").val();
            obj.pimage = $("#uploadBg").attr('src').replace("?x-oss-process=style/thumb_210_300","");
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
                
                $("#videoContainer").html($("#videoAddress").val());
            }
            $(".zyStep .zyStepItem.zyActive").removeClass("zyActive");
            $(".zyStepItem[data-target='" + stepId + "']").addClass("zyActive");
            $(".zyStepPanel").addClass("zyHidden");
            $(stepId).removeClass("zyHidden");
        }
    }
})(config, functions);

$(document).ready(function () {

    var submitUrl = config.ajaxUrls.workCreate;

    juicer.set({
        'tag::interpolateOpen': '$ZY{'
    });
    
    if (id) {
        uploadWork.initData(id);
        submitUrl = config.ajaxUrls.workUpdate
    }
    var zyFormHandler = new ZYFormHandler({
        submitUrl: submitUrl,
        redirectUrl: config.viewUrls.works
    });

    var productOSSUploaderObject = new uploadOSSObject("uploadBg","image/jpg,image/jpeg,image/png","jpg,jpeg,png",'10mb',
			$("#bgConsole"),$("#ossBgProgress"),$("#bgFileDescribe"),$("#ossBgfile .determinate"),$("#bgFileCompletePersent"),$("#uploadBg"));
	var productUploader = createUploader(productOSSUploaderObject);
	productUploader.init();

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
