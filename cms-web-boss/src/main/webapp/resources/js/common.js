function post(api, params, dataType, callback, error) {
	var timestamp = Date.parse(new Date());
	api = api + "?timestamp=" + timestamp;
	$.ajax({
		url : api,
		dataType : dataType,
		method : 'post',
		data : params,
		success : callback,
		error : error
	});
}

function load(api) {
	var timestamp = Date.parse(new Date());
	api = api + "?timestamp=" + timestamp;
	$.ajax({
		url : api,
		success : function(result) {
			if(result.indexOf('登录页面')>=0){
				window.location.href = '/';
			}else{
				$('.main').html(result);
			}
		}
	});
}
function onSuccess(event, xhr, settings) {
	if(xhr.responseText.indexOf('登录页面')>=0){
		window.location.href = '/';
	}
}



$(document).ajaxSuccess(onSuccess);



/**格式当前时间 带时分秒 yyyy-MM-dd HH:mm:ss*/
function getLocalTime(milliseconds) {
	var datetime = new Date();
	datetime.setTime(milliseconds);
	var year = datetime.getFullYear();
	// 月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1)
			: datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime
			.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime
			.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":"
			+ second;
}

/** 时间戳=>本地时间 */
function getLocalTimeDate(milliseconds) {
	var datetime = new Date();
	datetime.setTime(milliseconds);
	var year = datetime.getFullYear();
	// 月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1)
			: datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime
			.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime
			.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date;
}


//对象空值判断
function isNotNull(obj){
	if(obj == "" || obj == null || obj == undefined){
		return false;
	}else{
		return true;
	}
}

//窗口关闭
function common_closeWindow(windName) {
	$(windName).window({
		modal : true,
		closed : true,
	});
	$(windName).form('clear');
}

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}

function date_formatter(value, row, index){
	if(isNotNull(value)){
		var oldTime = new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}else{
		oldTime="";
	}
	 return oldTime;
}
//获取dayNum天前的日期
function getDateBefore(dayNum){
	var now = new Date();
	var datetime = new Date(now.getTime() - dayNum * 24 * 3600 * 1000);
	var year = datetime.getFullYear();
	// 月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1)
			: datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime
			.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime
			.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date;
}
//国民信托，海门小贷，龙信小贷渠道code
window.gmxtChannelCode = "00004";
window.hmxdChannelCode = "00011";
window.lxxdChannelCode = "00013";
window.wmxtChannelCode = "00014";
window.emptyArray = [];
//国民信托，龙信小贷放在一个数组中，用于两个渠道展示规则一致的情况
window.glChannelList = window.emptyArray.concat(window.gmxtChannelCode).concat(window.lxxdChannelCode);
// 国民信托，海门小贷，龙信小贷放在一个数组中，用于三个渠道展示规则一致的情况
window.ghlChannelList = glChannelList.concat(window.hmxdChannelCode);
//国民信托，海门小贷，龙信小贷,外贸信托放在一个数组中，用于三个渠道展示规则一致的情况
window.ghlwChannelList = window.ghlChannelList.concat(window.wmxtChannelCode);
//excel 2007以及更高版本后缀
window.excel2007Suffix = "xlsx";
//excel 2003版本后缀
window.excel2003Suffix = "xls";
//返回码000000
window.successCode = "000000";

/**
 * url 请求url
 * params 提交到后台参数
 * successFunc 下载成功回调
 * failFunc 下载失败回调
 * maskWindow 遮罩window
 */
var global = {};
global.contextPath = '<%=request.getContextPath()%>';
$.downloadFile = function(options){
	var opts = $.extend({}, $.downloadFile.defaults,options);
	
	opts.htmlDownloadFileToken=window.getAppUuid();
	opts.r = new Date().getTime();
	opts.url=opts.url||"";
	opts.params=opts.params||{};
	opts.maskWindow=opts.maskWindow||window;
	if(!$.isWindow(opts.maskWindow)){
		opts.maskWindow=window;
	}
	opts.htmlDownloadFileTokenName = "htmlDownloadFileToken";
	
	if(opts.isDownloadBigFile === true ){
		opts.params.isBigFile=true;
	}
	
	if($.trim(opts.url)=="" ){
		return;
	}

	if(!$.isEmptyObject(opts.params)){
		var queryString = $.param(opts.params)||"";
		queryString=$.trim(queryString);
		if(queryString.length>0 && opts.url.indexOf("&")==-1){
			queryString="?"+queryString;
		}
		opts.url=opts.url+queryString;
	}	
	
	var frameId = 'downloadFile_Frame_' + (new Date().getTime());
	var $frame = $('<iframe id='+frameId+' name='+frameId+'></iframe>').appendTo('body');
	$frame.css({
		position:'absolute',
		top:-1000,
		left:-1000
	});
	$frame.on('load', function(event){
		completeHandle(false);
	});
	
	//cookie检查
	function cookieCheckFunction(){
		try{
			var cookieValue  = $.cookie(opts.htmlDownloadFileToken);
			if(cookieValue!=null){
				cookieCheckCompleteHandle.timeOutId = setTimeout(cookieCheckCompleteHandle,3000);
			}else{
				cookieCheckFunction.timeOutId=setTimeout(cookieCheckFunction,1000);
			}
		}catch(e){
			console.info(e);
		}
	}
	
	//发现cookie
	function cookieCheckCompleteHandle(){
		if(opts.isDownloadBigFile === true ){
			var params = {
				htmlDownloadFileToken:opts.htmlDownloadFileToken
			};
			
			function tempFunction(){
				$.ajaxPackage({
					url:global.contextPath + '/frameWork/downloadBigFile',
					async:true,
					type:"POST",
					data:params,
					dataType:"json",
					isShowLoadMask:false,
					success:function(response, textStatus, jqXHR){
						if(response.resCode == "000000" && response.attachment.downloadFileState === true ){
							 completeHandle(true);
						}else{
							setTimeout(tempFunction,5000);
						}
					},
					error:function(response, textStatus, jqXHR){
						completeHandle(true);
					},
					complete:function(jqXHR,textStatus){
					}
				});
			}
			setTimeout(tempFunction,0);
		}else{
			 completeHandle(true);
		}
	}

	//下载完成后处理
	function completeHandle(result){
		try{
			if(cookieCheckCompleteHandle.timeOutId!=null){
				clearTimeout(cookieCheckCompleteHandle.timeOutId);
				cookieCheckCompleteHandle.timeOutId=null;
			}
		}catch(e){
		}
		try{
			if(cookieCheckFunction.timeOutId!=null){
				clearTimeout(cookieCheckFunction.timeOutId);
				cookieCheckFunction.timeOutId=null;
			}
		}catch(e){
		}
		
		if(completeHandle.cbInvoked==null){
			completeHandle.cbInvoked=0;
		}
		if(completeHandle.cbInvoked>0){
			return;
		}
		
		if(result==true){
			if($.isFunction(opts.successFunc)){
				try{
					opts.successFunc();	
				}catch(e){
					console.info(e);
				}
			}else{
				console.info(opts.successFunc+"不是函数");
			}
		}else{
			var data = null;
			try{
				var body = $($frame[0].contentWindow.document.body);
				data = body.html();
				var ta = body.find('>textarea');
				if (ta.length){
					data = ta.val();
				} else {
					var pre = body.find('>pre');
					if (pre.length){
						data = pre.html();
					}else{
						var div = body.find('>div');
						if(div.length>0){
							try{
								data=JSON.stringify(div.data());
							}catch(e){
							}
						}
					}
				}
				eval("data = " + data);
				if($.isFunction(opts.successFunc)){
					try{
						opts.successFunc(data);	
					}catch(e){
						console.info(e);
					}
				}else{
					console.info(opts.successFunc+"不是函数");
				}
			}catch(e){
				if($.isFunction(opts.failFunc)){
					try{
						opts.failFunc(e);	
					}catch(e){
						console.info(e);
					}
				}else{
					console.info(opts.failFunc+"不是函数");
				}
			}
		}
			
		opts.maskWindow.$.loadMask.hide(opts.r);
		try{
			$.cookie(opts.htmlDownloadFileToken, '', { expires: -1,path:global.contextPath });
		}catch(e){
			console.info(e);
		}	
		try{
			$.cookie(opts.htmlDownloadFileTokenName, '', { expires: -1,path:global.contextPath });
		}catch(e){
			console.info(e);
		}
		opts=null;
		$frame.unbind();
		$frame.remove();
	}
	
	
	opts.maskWindow.$.loadMask.show(opts.r);
	$.cookie(opts.htmlDownloadFileTokenName,opts.htmlDownloadFileToken,{path:global.contextPath });
	cookieCheckFunction();
	$frame.attr('src', opts.url);
};