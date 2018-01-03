/**
 * Created with JetBrains WebStorm.
 * User: ty
 * Date: 14-10-5
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
var config={
    uploader:{
        url:"file/uploadMultiFile",
        swfUrl:"resources/js/lib/Moxie.swf",
        sizes:{
            all:"5120m",
            img:"2m"
        },
        filters:{
            all:"*",
            zip:"zip,rar",
            img:"jpg,JPG,jpeg,JPEG,png,PNG"
        },
        qiNiu:{
            upTokenUrl:"qi-niu/up-token",
            uploadDomain:"http://qiniu-plupload.qiniudn.com/",
            bucketDomain:"http://7xplk9.com1.z0.glb.clouddn.com/"
        },
        aLiYun:{
            getSignatureUrl:"#",
            host: "",
            policy: "",
            accessKey: "",
            signature: "",
            expire: 0
        },
        fileType:{
            others:100,
            attachFile:1,
            newsImageFile:2,
            productionFile:3
        }
    },

    ajaxUrls:{
        imageGet:"file/image",
        newsGetAll:"news/findNewsByPage",
        newsGetByPage:"news/findNewsByPage",
        newsCreate:"news/createNews",
        newsUpdate:"news/updateNews",
        newsRemove:"news/deleteNews/:id",
        newsDetail:"news/findNewsById/:id",
        userGetByPage:"user/getDataTableUserByPage",
        userActiveAction:"user/resetUserValid",
        worksGetByPage:"production/getDataTableProductionByPage",
        workGetById:"production/getProductionDetailById/:id",
        workRemove:"production/deleteProduction/:id",
        workSetStatus:"production/updateProductionStatus",
        workSetRound:"review/bindProductAndRound",
        workComputeScore:"production/updateProductionScore",
        judgeGetByPage:"judge/findJudgesByPage",
        judgeRemove:"judge/deleteJudge/:id",
        judgeCreate:"judge/createJudge",
        judgeUpdate:"judge/updateJudge",
        judgeDetail:"judge/findJudgeById/:id",
        judgeRoundDetail:"roundJudge/getRoundJudgeById",
        judgeRoundCreate:"roundJudge/createRoundJudge",
        judgeRoundUpdate:"roundJudge/updateRoundJudge",
        judgeRoundRemove:"roundJudge/deleteRoundJudge/:id",
        judgeRoundGetByPage:"roundJudge/getRoundJudgeByPage",
        judgeRoundSetJudge:"roundJudge/bindingRoundJudge",
        sendEmail:"review/sendReviewEmail",
        getRoundScoreBean:"review/getScoreByProductId"
    },
    viewUrls:{
        newsMgr:"news/newsMgr",
        newsUpdate:"news/newsCOU/:id",
        judgeMgr:"judge/judgeMgr",
        judgeUpdate:"judge/judgeCOU/:id",
        judgeRoundMgr:"roundJudge/judgeRoundMgr",
        judgeRoundUpdate:"roundJudge/judgeRoundCOU/:id",
        manageWorkDetail:"production/manageWorkDetail/:id"
    },
    dataTable:{
        langUrl:"resources/backend/lang/de_DE.txt"
    },
    perLoadCounts:{
        table:10
    },
    workGroup: {
        "1": "产品组",
        "2": "概念组"
    },
    workType: {
        "1": "生活辅助类",
        "2": "智能养老类",
        "3": "综合设计类"
    },
    workStatus: {
        "1": "已提交",
        "2": "审核未通过",
        "3": "审核已通过",
        "4": "初选入围",
        "5": "初选未入围",
        "6": "复选入围",
        "7": "复选未入围"
    },
    userStatus:{
        "0":"激活",
        "1":"禁用"
    },
    validErrors:{
        required:"请输入此字段！",
        email:"请输入正确的邮箱格式！",
        emailExist:"邮箱已经存在！",
        uploadImg:"请上传图片！",
        number:"请输入数字",
        maxLength:"此字段最多输入${max}个字！",
        minLength:"此字段最少输入${min}个字！",
        rangLength:"此字段只能输入${min}-${max}个字！",
        rang:"此字段只能输入${min}-${max}之间的整数！",
        pwdNotEqual:"两次输入的密码不一样！"
    },
    messages:{
        successTitle:"成功提示",
        errorTitle:"错误提示",
        optSuccess:"操作成功！",
        noData:"没有数据",
        progress:"处理中...",
        uploaded:"上传完成！",
        confirmDelete:"确定删除吗？",
        confirm:"确定执行此操作吗？",
        noSelected:"没有选中任何记录！",
        notFound:"资源丢失！",
        loadDataError:"请求数据失败！",
        networkError:"网络异常，请稍后重试！",
        systemError:"系统错误，请稍后重试或者联系d_seniors@126.com！",
        optSuccRedirect:"操作成功,3秒后跳转到管理页！",
        timeout:"登录超时，3秒后自动跳到登陆页！",
        optError:"服务器端异常，请稍后重试！",
        uploadSizeError:"最大文件大小${value}！",
        uploadExtensionError:"只允许上传${value}！",
        uploadIOError:"上传出错，请稍后重试！",
        imageSizeError:"图片大小不符合！",
        scoreRefresh:"本轮作品分数已更新，请前往作品列表查看"
    }
};
$(document).ready(function(){

    $("input[type='text'],input[type='email']").blur(function(){
        $(this).val($(this).val().trim());
    });

    //阻止form提交
    $("form").keydown(function(e){
        if(e.keyCode==13){
            return false;
        }
    });

    if(pageName){
        var target=$(".item[data-page-name='"+pageName+"']");
        if(target){
            target.addClass("active");
        }

    }
});
