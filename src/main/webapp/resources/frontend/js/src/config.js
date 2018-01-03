/**
 * Created with JetBrains WebStorm.
 * User: ty
 * Date: 14-10-5
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
var config = {
    uploader: {
        url: "file/uploadMultiFile",
        swfUrl: "resources/js/lib/Moxie.swf",
        sizes: {
            all: "30m",
            img: "2m"
        },
        filters: {
            all: "*",
            zip: "zip,rar",
            img: "jpg,JPG,jpeg,JPEG,png,PNG"
        },
        qiNiu: {
            upTokenUrl: "qi-niu/up-token",
            uploadDomain: "http://qiniu-plupload.qiniudn.com/",
            bucketDomain: "http://7xplk9.com1.z0.glb.clouddn.com/"
        },
        aLiYun: {
            getSignatureUrl: "#",
            host: "",
            policy: "",
            accessKey: "",
            signature: "",
            expire: 0
        },
        fileType: {
            others: 100,
            attachFile: 1,
            newsImageFile: 2,
            productionFile: 3
        }
    },
    perLoadCounts:{
        table:10
    },
    ajaxUrls: {
        imageGet: "file/image",
        fileGet: "file/downloadFile",
        forgetPwd: "user/findYourPwd",
        setPwd: "user/resetYourPwd",
        register: "user/register",
        resetInfo: "user/update",
        resetPwd: "user/resetLoginUserPwd",
        workCreate: "production/createProduction",
        workUpdate: "production/updateProduction",
        workDetail: "production/getProductionDetailById/:id",
        workRemove: "production/deleteProduction/:id",
        worksGetByPage: "production/getProductionPageByCondition",
        judgeToScoreList:"review/getReviewListByUserId",
        judgeScore:"review/updateReviewScore"
    },
    viewUrls: {
        login: "login",
        works: "production/works",
        judgeIndex:"review/judgeIndex/:round"
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
    validErrors: {
        required: "请输入此字段！",
        email: "请输入正确的邮箱格式！",
        emailExist: "邮箱已经存在！",
        uploadImg: "请上传图片！",
        number: "请输入数字",
        maxLength: "此字段最多输入${max}个字！",
        minLength: "此字段最少输入${min}个字！",
        rangLength: "此字段只能输入${min}-${max}个字！",
        rang: "此字段只能输入${min}-${max}之间的整数！",
        pwdNotEqual: "两次输入的密码不一样！"
    },
    messages: {
        successTitle: "成功提示",
        errorTitle: "错误提示",
        optSuccess: "操作成功！",
        noData: "没有数据",
        progress: "处理中...",
        uploaded: "上传完成！",
        confirmDelete: "确定删除吗？",
        confirm: "确定执行此操作吗？",
        noSelected: "没有选中任何记录！",
        notFound: "资源丢失！",
        loadDataError: "请求数据失败！",
        networkError: "网络异常，请稍后重试！",
        systemError: "系统错误，请稍后重试或者联系mail@lotusprize.com！",
        optSuccRedirect: "操作成功,3秒后跳转到管理页！",
        timeout: "登录超时，3秒后自动跳到登陆页！",
        optError: "服务器端异常，请稍后重试！",
        uploadSizeError: "最大文件大小${value}！",
        uploadExtensionError: "只允许上传${value}！",
        uploadIOError: "上传出错，请稍后重试！",
        imageSizeError: "图片大小不符合！",
        emailSend: "请进入邮箱进行密码的修改！",
        optSuccessToLogin: "成功，3秒后跳转到登陆！",
        registerSuccess: "注册成功，请进入邮箱激活账号！",
        pleaseEnterPersonalInfo: "请填写参与者信息，*为必填！",
        pleaseEnterWorkInfo: "请填写完整作品信息，*为必填！",
        scoreSaved:"评分以保存，页面2秒后自动返回到您的打分列表页！",
        scoreError:"请输入正确的分数"
    }
};
$(document).ready(function () {

    $("input[type='text'],input[type='email']").blur(function () {
        $(this).val($(this).val().trim());
    });

    //阻止form提交
    $("form").keydown(function (e) {
        if (e.keyCode == 13) {
            return false;
        }
    });

    if (pageName) {
        var target = $(".zyLink[data-page-name='" + pageName + "']");
        if (target) {
            target.addClass("zyActive");
        }

    }


    //zySelect控件
    $(document).click(function (e) {
        if ($(e.target).parents(".zySelect").length == 0) {
            $(".zySelect .zyOptionList").addClass("zyHidden");
        }
    });
    $(".zySelect .zyInput").click(function () {
        $(this).parent(".zySelect").find(".zyOptionList").removeClass("zyHidden");
    });
    $(".zySelect .zyOption").click(function () {
        var selectEl = $(this).parents(".zySelect");
        selectEl.find(".zySelectValue").val($(this).data("value"));
        selectEl.find(".zyInput").val($(this).text());
        selectEl.find(".zyOptionList").addClass("zyHidden");
    })
});
