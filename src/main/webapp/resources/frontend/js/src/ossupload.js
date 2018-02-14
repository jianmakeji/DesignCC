var accessid = '';
var accesskey = '';
var host = '';
var policyBase64 = '';
var signature = '';
var callbackbody = '';
var filename = '';
var key = '';
var expire = 0;
var g_object_name = '';
var now = timestamp = Date.parse(new Date()) / 1000;
var serverUrl = '../uploadKey/1';

function send_request() {
	var xmlhttp = null;
	if(window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	if(xmlhttp != null) {
		xmlhttp.open("GET", serverUrl, false);
		xmlhttp.send(null);
		return xmlhttp.responseText
	} else {
		alert("Your browser does not support XMLHTTP.");
	}
};

function get_signature() {
	//可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
	now = timestamp = Date.parse(new Date()) / 1000;
	if(expire < now + 3) {
		body = send_request()
		var obj = eval("(" + body + ")");
		host = obj['host']
		policyBase64 = obj['policy']
		accessid = obj['accessid']
		signature = obj['signature']
		expire = parseInt(obj['expire'])
		callbackbody = obj['callback']
		key = obj['dir']
		return true;
	}
	return false;
};

function random_string(len) {
	len = len || 32;
	var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	var maxPos = chars.length;
	var pwd = '';
	for(i = 0; i < len; i++) {
		pwd += chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}

function get_suffix(filename) {
	pos = filename.lastIndexOf('.')
	suffix = ''
	if(pos != -1) {
		suffix = filename.substring(pos)
	}
	return suffix;
}

function calculate_object_name(filename) {

	suffix = get_suffix(filename)
	g_object_name = key + random_string(10) + suffix

}

function get_uploaded_object_name(filename) {
	return g_object_name;
}

function set_upload_param(up, filename, ret) {
	if(ret == false) {
		ret = get_signature()
	}
	g_object_name = key;
	if(filename != '') {
		suffix = get_suffix(filename)
		calculate_object_name(filename)
	}
	new_multipart_params = {
		'key': g_object_name,
		'policy': policyBase64,
		'OSSAccessKeyId': accessid,
		'success_action_status': '200', //让服务端返回200,不然，默认会返回204
		'callback': callbackbody,
		'signature': signature,
	};

	up.setOption({
		'url': host,
		'multipart_params': new_multipart_params
	});

	up.start();
}

function uploadOSSObject(browseButton,mimeTypes,extensions,max_file_size,infoConsole,ossProgress,fileDescribe,determinate,completePersent,uploadImg){
	this.browseButton = browseButton;
	this.mimeTypes = mimeTypes;
	this.extensions = extensions;
	this.max_file_size = max_file_size;
	this.infoConsole = infoConsole;
	this.ossProgress = ossProgress;
	this.fileDescribe = fileDescribe;
	this.determinate = determinate;
	this.completePersent = completePersent;
	this.uploadImg = uploadImg;
}

function createUploader(uploadOSSObject){
	var imageUploader = new plupload.Uploader({
		runtimes: 'html5,flash,silverlight,html4',
		browse_button: uploadOSSObject.browseButton,
		//multi_selection: false,
		//container: document.getElementById('container'),
		flash_swf_url: '../resources/js/plupload-2.3.1/Moxie.swf',
		silverlight_xap_url: '../resources/js/plupload-2.3.1/Moxie.xap',
		url: 'http://oss.aliyuncs.com',

		filters: {
			mime_types: [ //只允许上传图片和zip,rar文件
				{
					title: "Image files",
					extensions: uploadOSSObject.extensions,
					mimeTypes: uploadOSSObject.mimeTypes
				}
			],
			max_file_size: uploadOSSObject.max_file_size, //最大只能上传10mb的文件
			prevent_duplicates: true
			//不允许选取重复文件
		},

		init: {
			PostInit: function() {
				uploadOSSObject.infoConsole.html('');
				serverUrl = 'uploadKey/1';
			},

			FilesAdded: function(up, files) {
				uploadOSSObject.ossProgress.show();
				plupload.each(files,
					function(file) {
					uploadOSSObject.fileDescribe.append(file.name + ' (' + plupload.formatSize(file.size) + ') ');
					});
				uploadOSSObject.determinate.show();
				set_upload_param(imageUploader, '', false);
			},

			BeforeUpload: function(up, file) {

				set_upload_param(up, file.name, true);
			},

			UploadProgress: function(up, file) {
				uploadOSSObject.completePersent.html(file.percent + '% ,');
				uploadOSSObject.determinate.width(file.percent + '%');
			},

			FileUploaded: function(up, file, info) {
				if(info.status == 200) {
					uploadOSSObject.fileDescribe.innerHTML = '，上传成功！';
					var thumbImgUrl = host + "/" + get_uploaded_object_name(file.name) + "?x-oss-process=style/thumb_210_300";
					uploadOSSObject.uploadImg.attr('src', thumbImgUrl);

				} else {
					uploadOSSObject.fileDescribe.innerHTML = info.response;
				}
			},

			Error: function(up, err) {
				if(err.code == -600) {
					uploadOSSObject.infoConsole.html('选择的文件太大了，不能超过'+uploadOSSObject.max_file_size);

				} else if(err.code == -601) {
					uploadOSSObject.infoConsole.html('选择的文件后缀不对!');

				} else if(err.code == -602) {
					uploadOSSObject.infoConsole.html("这个文件已经上传过一遍了");
				} else {
					uploadOSSObject.infoConsole.html("上传出错！");
				}
			}
		}
	});
	return imageUploader;
}