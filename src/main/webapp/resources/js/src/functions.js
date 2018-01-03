/**
 * Created with JetBrains WebStorm.
 * User: ty
 * Date: 14-10-13
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */
var functions=(function(config){

    //浏览器前缀
    var prefixes=["Moz",'webkit','ms','O'];

    return {
        findObjInArray:function(array,key,keyValue){
            for(var i= 0,len=array.length;i<len;i++){
                if(array[i][key]==keyValue){
                    return array[i];
                }
            }

            return false;
        },
        /**
         * 获取浏览器前缀
         * @returns prefix {string}
         */
        getPrefix:function(){
            var length=prefixes.length;
            var prefix="";
            for(var i=0;i<length;i++){
                if((prefixes[i]+"Animation") in document.body.style){
                    prefix=prefixes[i];
                }
            }

            return prefix;
        },
        /**
         * 3秒跳转
         * @param url 需要跳转到的url
         */
        timeoutRedirect:function(url){
            setTimeout(function(){
                window.location.href=url;
            },3000);
        },
        /**
         * 产生随机数，可以带前缀
         * @returns retValue {string}
         */
        getRandom:function(){
            var date = new Date();
            var mo = (date.getMonth() + 1) < 10 ? ('0' + '' + (date.getMonth() + 1)) : date.getMonth() + 1;
            var dd = date.getDate() < 10 ? ('0' + '' + date.getDate()) : date.getDate();
            var hh = date.getHours() < 10 ? ('0' + '' + date.getHours()) : date.getHours();
            var mi = date.getMinutes() < 10 ? ('0' + '' + date.getMinutes()) : date.getMinutes();
            var ss = date.getSeconds() < 10 ? ('0' + '' + date.getSeconds()) : date.getSeconds();
            var retValue = date.getFullYear() + '' + mo + '' + dd + '' + hh + '' + mi + '' + ss + '';
            for (var j = 0; j < 4; j++) {
                retValue += '' + parseInt(10 * Math.random()) + '';
            }
            if (arguments.length == 1) {
                return arguments[0] + '' + retValue;
            }else{
                return retValue;
            }
        },
        /**
         * 获取邮箱的域名主体部分
         * @param email {string} 需要获取的邮箱
         * @returns domain {string}
         */
        getEmailDomain:function(email){
            var domain=email.substring(email.indexOf("@")+1);
            domain=domain.substring(0,domain.lastIndexOf("."));
            domain=domain.replace(/vip.|.com|.cn/g,"");
            return domain;
        },
        /**
         * 格式化日期
         * @param format {string} 格式化
         * y:四位数的年份
         * m:两位数的月份
         * d:两位数的日期
         * h:两位数的时间
         * i:两位数的分钟
         * s:两位数的秒钟
         * 支持格式:
         * @param dateTime {number} 毫秒表示的日期
         * @returns string {string}格式好的字符串日期，默认是2014-09-10 12:03:23格式
         */
        formatDate:function(format,dateTime){
            var string,currentDate,year,month,day, h, m, s,fYear,fMonth,fDay,fH,fM,fS;

            if(typeof format ==="number"){
                dateTime=format;
                format=null;
            }

            currentDate =dateTime?new Date(dateTime):new Date();
            fYear=currentDate.getFullYear();
            year=fYear.toString().slice(2);
            fMonth=month=currentDate.getMonth()+1;
            fDay=day=currentDate.getDate();
            fH=h=currentDate.getHours();
            fM=m=currentDate.getMinutes();
            fS=s=currentDate.getSeconds();

            if(fMonth<10){
                fMonth="0"+fMonth;
            }
            if(fDay<10){
                fDay="0"+fDay;
            }
            if(fH<10){
                fH="0"+fH;
            }
            if(fM<10){
                fM="0"+fM;
            }
            if(fS<10){
                fS="0"+fS;
            }

            switch(format){
                case "y-m-d":
                    string=fYear+"-"+fMonth+"-"+fDay;
                    break;
                case "y-m-d h:m":
                    string=fYear+"-"+fMonth+"-"+fDay+" "+fH+":"+fM;
                    break;
                case "y/m/d h:m:s":
                    string=fYear+"/"+fYear+"/"+day+" "+fH+":"+fH+":"+fH;
                    break;
                case "y/m/d h:m":
                    string=fYear+"/"+fMonth+"/"+fDay+" "+fH+":"+fM;
                    break;
                default :
                    string=fYear+"-"+fMonth+"-"+fDay+" "+fH+":"+fM+":"+fS;
                    break;
            }

            return string;
        },
        /**
         * 设置cookie
         * @param name {string} cookie的名称
         * @param value {string} cookie的值
         * @param days {number} cookie的保存时间
         */
        setCookie:function(name,value,days){
            var date = new Date();
            date.setTime(date.getTime()+(days*24*60*60*1000));
            var thisCookie = name + "=" + encodeURIComponent(value) +
                ((days) ? "; expires=" + date.toGMTString() : "");
            document.cookie = thisCookie;
        },
        /**
         * 获取cookie
         * @param name {string} cookie的名称
         * @returns {*}
         */
        getCookie:function(name){
            var nameSG = name + "=";
            if (document.cookie == null || document.cookie.indexOf(nameSG) == -1){
                return null;
            }


            var ca = document.cookie.split(';');
            for(var i=0; i<ca.length; i++) {
                var c = ca[i];
                if (c.charAt(0)==' '){
                    c = c.substring(1,c.length);
                }
                if (c.indexOf(nameSG) == 0){
                    return decodeURIComponent(c.substring(nameSG.length,c.length));
                }
            }

            return null;
        },
        /**
         * 删除cookie
         * @param name {string} cookie的名称
         */
        deleteCookie:function(name){
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var oldValue=this.getCookie(name);
            if(oldValue!=null){
                document.cookie= name + "="+oldValue+";expires="+exp.toGMTString();
            }
        },
        /**
         * 获取文件的信息
         * @param fileName
         * @returns {{filePath: string, filename:string, ext: string}}
         */
        getFileInfo:function(fileName){
            var extPos=fileName.lastIndexOf(".");
            var pathPost=fileName.lastIndexOf("\\");
            return {
                filePath:pathPost!=-1?fileName.substring(0,pathPost+1):"",
                filename:fileName.substring(pathPost+1,extPos),
                filenameWithExt:fileName.substring(pathPost+1),
                ext:fileName.substring(extPos+1)
            }
        },
        	
        getSuffixFile:function(file,suffix){
            var pathInfo=this.getFileInfo(file);
            return pathInfo.filePath+pathInfo.filename+suffix+"."+pathInfo.ext;
        },
        /**
         * 检测是否是移动设备
         * @returns {boolean}
         */
        checkMobile:function(){
            var userAgentList = new Array("2.0 MMP", "240320", "AvantGo","BlackBerry", "Blazer",
                "Cellphone", "Danger", "DoCoMo", "Elaine/3.0", "EudoraWeb", "hiptop", "IEMobile", "KYOCERA/WX310K", "LG/U990",
                "MIDP-2.0", "MMEF20", "MOT-V", "NetFront", "Newt", "Nintendo Wii", "Nitro", "Nokia",
                "Opera Mini", "Opera Mobi",
                "Palm", "Playstation Portable", "portalmmm", "Proxinet", "ProxiNet",
                "SHARP-TQ-GX10", "Small", "SonyEricsson", "Symbian OS", "SymbianOS", "TS21i-10", "UP.Browser", "UP.Link",
                "Windows CE", "WinWAP", "Android", "iPhone", "iPod", "iPad", "Windows Phone", "HTC"/*, "GTB"*/);
            var appNameList = new Array("Microsoft Pocket Internet Explorer");

            var userAgent = navigator.userAgent.toString();
            var appName = navigator.appName.toString();
            var agentLength=userAgentList.length,appLength=appNameList.length;
            var i= 0,j=0;

            for (; i<agentLength; i++) {
                if (userAgent.indexOf(userAgentList[i]) >= 0) {
                    return true;
                }
            }

            for (; j<appLength; j++) {
                if (appName.indexOf(appNameList[j]) >= 0) {
                    return true;
                }
            }

            return false;
        },
        /**
         * 显示loading遮盖层
         */
        showLoading:function(){
            $("#zyLoading").removeClass("zyHidden");
        },
        /**
         * 隐藏loading遮盖层
         */
        hideLoading:function(){
            $("#zyLoading").addClass("zyHidden");
        },
        /**
         * ajax网络错误处理
         */
        ajaxErrorHandler:function(){
            this.hideLoading();
            $().toastmessage("showErrorToast",config.messages.networkError);
        },
        /**
         * ajax后台返回错误处理
         * @param message {string} 错误信息
         */
        ajaxReturnErrorHandler:function(message){
            this.hideLoading();
            if(!message){
                message=config.messages.systemError;
            }
            $().toastmessage("showErrorToast",message);
        },

        /**
         * 动态获取图片大小
         * @param sUrl
         * @param fCallback
         */
        getImageSize:function(sUrl, fCallback){
            var img = new Image();
            img.src = sUrl;
            //注意这里没有对IE做区分
            img.onload = function(){
                fCallback({width:img.width, height:img.height});
            };
        },
        /**
         * plupload版本1.5.7
         * @param params
         * @returns {plupload.Uploader}
         */
        createUploader:function(params){
            var uploader=new plupload.Uploader({
                runtimes:"html5,flash",
                multi_selection:params.multiSelection,
                max_file_size:params.maxSize,
                browse_button:params.uploadBtn,
                container:params.uploadContainer,
                multipart_params:params.multipartParams,
                url:document.getElementById("baseUrl").getAttribute("href")+config.uploader.url,
                flash_swf_url:document.getElementById("baseUrl").getAttribute("href")+config.uploader.swfUrl,
                filters : [
                    {title : "Media files", extensions : params.filter}
                ]
            });

            //初始化
            uploader.init();

            //文件添加事件
            uploader.bind("FilesAdded", function (up, files) {
                if(typeof params.filesAddedCb ==="function"){
                    params.filesAddedCb(files,up);
                }

                //开始上传
                up.start();

            });

            //文件上传进度条事件
            uploader.bind("UploadProgress", function (up, file) {
                if(typeof params.progressCb ==="function"){
                    params.progressCb(file);
                }
            });

            //出错事件
            uploader.bind("Error", function (up, err) {

                var message=err.message;
                if(message.match("Init")==null){
                    if(message.match("size")){
                        $().toastmessage("showErrorToast",
                            config.messages.uploadSizeError+config.uploader.sizes.img);
                    }else if(message.match("extension")){
                        $().toastmessage("showErrorToast",
                            config.messages.uploadExtensionError+config.uploader.filters.img);
                    }else{
                        $().toastmessage("showErrorToast",config.messages.uploadIOError);
                    }
                }
                up.refresh();
            });

            //上传完毕事件
            uploader.bind("FileUploaded", function (up, file, res) {
                var response = JSON.parse(res.response);
                if (response.success) {
                    if(typeof params.uploadedCb === "function"){
                        params.uploadedCb(response,file,up);
                    }
                } else {
                    $().toastmessage(config.messages.errorTitle,config.messages.uploadIOError);
                }
            });


            return uploader;
        },

        /**
         * plupload版本1.2.1,采用all版本，需要使用qiniu.js
         * @param params
         * @returns {*}
         */
        createQiNiuUploader:function(params){
            var uploader = Qiniu.uploader({
                runtimes: 'html5',    //上传模式,依次退化
                browse_button: params.uploadBtn,       //上传选择的点选按钮，**必需**
                uptoken_url:  config.uploader.qiNiu.upTokenUrl,
                multi_selection:params.multiSelection,
                domain: config.uploader.qiNiu.uploadDomain,
                container: params.uploadContainer,//上传区域DOM ID，默认是browser_button的父元素，
                filters: {
                    mime_types : [
                        { title : "media files", extensions : params.filter }
                    ]
                    //max_file_size:'1m'
                },
                multipart_params:params.multipartParams,
                max_file_size: params.maxSize,    //最大文件体积限制,qiniu中需要写在这里，而不是卸载filters中
                max_retries: 3,                   //上传失败最大重试次数
                chunk_size: '4mb',                //分块上传时，每片的体积
                auto_start: true,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
                init: {
                    'Init':function(up,info){
                        //console.log(up.getOption("max_file_size"));
                    },
                    'FilesAdded': function(up, files) {
                        if(typeof params.filesAddedCb ==="function"){
                            params.filesAddedCb(files,up);
                        }
                    },
                    'BeforeUpload':function(up,file){

                    },
                    'UploadProgress': function(up, file) {
                        if(typeof params.progressCb ==="function"){
                            params.progressCb(file);
                        }
                    },
                    'FileUploaded': function(up, file, info) {
                        if(typeof params.uploadedCb === "function"){
                            var response = JSON.parse(info);
                            response.url=config.uploader.qiNiu.bucketDomain + response.key;
                            params.uploadedCb(response,file,up);
                        }
                    },
                    'Error': function(up, err, errTip) {
                        $().toastmessage("showErrorToast",errTip);

                        up.refresh();
                    },
                    'Key': function(up, file) {

                        // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
                        // 该配置必须要在 unique_names: false , save_key: false 时才生效
                        var random=Math.floor(Math.random()*10+1)*(new Date().getTime());
                        var filename=file.name;
                        var extPos=filename.lastIndexOf(".");


                        // do something with key here
                        return random+filename.substring(extPos);

                        //return file.name;
                    }
                }
            });

            return uploader;
        },
        /****************************************创建适合阿里云的uploader**********************/
        generateUniqueFileName: function (filename) {
            var random = Math.floor(Math.random() * 10 + 1) * (new Date().getTime());
            var extPos = filename.lastIndexOf(".");


            // do something with key here
            return filename.substring(0,extPos)+"-"+random + filename.substring(extPos);
        },
        sendRequest: function () {
            var xmlhttp = null,
                serverUrl = config.uploader.aLiYun.getSignatureUrl;

            if (window.XMLHttpRequest) {
                xmlhttp = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }

            if (xmlhttp != null) {
                xmlhttp.open("GET", serverUrl, false);
                xmlhttp.send(null);
                return xmlhttp.responseText
            } else {
                alert("Your browser does not support XMLHTTP.");
            }
        },
        getSignature: function () {
            var now = Date.parse(new Date()) / 1000;
            var body, obj;

            if (config.aLiYun.expire < now + 3) {
                body = this.sendRequest();
                obj = eval("(" + body + ")");
                config.aLiYun.host = obj['host'];
                config.aLiYun.policy = obj['policy'];
                config.aLiYun.accessKey = obj['accessKey'];
                config.aLiYun.signature = obj['signature'];
                config.aLiYun.expire = parseInt(obj['expire']);
                return true;
            }
            return false;
        },
        setUploadParams: function (up,filename,ret) {

            if (ret == false){
                ret = this.getSignature();
            }
            filename=this.generateUniqueFileName(filename);
            var new_multipart_params = {
                'key' : filename,
                'policy': config.aLiYun.policy,
                'OSSAccessKeyId': config.aLiYun.accessKey,
                'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
                'signature': config.aLiYun.signature
            };

            up.setOption({
                'url': config.aLiYun.host,
                'multipart_params': new_multipart_params
            });
        },
        /**
         * plupload版本1.5.7
         * @param params
         * @returns {plupload.Uploader}
         */
        createALiYunUploader: function (params) {
            var me=this;
            var uploader = new plupload.Uploader({
                runtimes: "html5,flash",
                multi_selection: params.multiSelection,
                max_file_size: params.maxSize,
                browse_button: params.uploadBtn,
                container: params.uploadContainer,
                multipart_params: params.multipartParams,
                url: config.uploader.url,
                flash_swf_url: config.baseUrl + config.uploader.swfUrl,
                filters: [
                    {title: "Media files", extensions: params.filter}
                ]
            });

            //初始化
            uploader.init();

            //文件添加事件
            uploader.bind("FilesAdded", function (up, files) {
                if (typeof params.filesAddedCb === "function") {
                    params.filesAddedCb(files, up);
                }

                //开始上传
                up.start();
            });

            uploader.bind("BeforeUpload",function(up, file) {
                me.setUploadParams(up, file.name, false);
            });


            //文件上传进度条事件
            uploader.bind("UploadProgress", function (up, file) {
                if (typeof params.progressCb === "function") {
                    params.progressCb(file, up);
                }
            });

            //出错事件
            uploader.bind("Error", function (up, err) {

                var message = err.message;
                if (message.match("Init") == null) {
                    if (message.match("size")) {
                        $().toastmessage("showErrorToast",
                            config.messages.uploadSizeError + config.uploader.sizes.img);
                    } else if (message.match("extension")) {
                        $().toastmessage("showErrorToast",
                            config.messages.uploadExtensionError + config.uploader.filters.img);
                    } else {
                        $().toastmessage("showErrorToast", config.messages.uploadIOError);
                    }
                }

                up.refresh();
            });

            //上传完毕事件
            uploader.bind("FileUploaded", function (up, file, res) {
                if (res.status == 200) {
                    var key=up.getOption("multipart_params")["key"];
                    res.url=config.aLiYun.host+"/"+key;
                    if (typeof params.uploadedCb === "function") {
                        params.uploadedCb(res, file, up);
                    }
                } else {
                    $().toastmessage(config.messages.errorTitle, config.messages.uploadIOError);
                }
            });


            return uploader;
        }



    }

})(config);
