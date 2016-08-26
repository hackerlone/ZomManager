/** 日期：2016年2月21日20:16:18 作者：虞荣华 描述：蓝海飞鱼基本库(对象化) */

/** 唯一全局对象 */
var lh = {
	ui : {},//ui相关
	dom : {
		clientHeight:document.documentElement.clientHeight,
		clientWidth:document.documentElement.clientWidth,
		clientSafeWidth:document.documentElement.clientWidth < 1000 ? 1000 : document.documentElement.clientWidth
		},//dom相关
	data : {},//数据
	grid : {frontSize:15},//表格相关数据
	cache : {},//缓存
	status : {currentLayout:1, savingFlag:false,trashShow:false,scrollLoading:false,optTimeRec:0,optTimeGap:2},//状态设置相关
	ajax : {ajaxOver:false, repeatGap:10},//ajax请求相关参数或标识
	page : {currentPage:1, rows:15},//分页相关
	plugins : {},//插件
	paramJson : null,//参数Json
	paramJsonStr : null,//参数Json字符串
	dict : {},//业务相关状态值
	param : {},//业务相关参数对象
	domain : {},//具体业务对象
	current : {num:0},//当前操作对象
	ext : {} //扩展对象
};

/** 初始化组件，扩展插件 */
(function ($) {
	$.fn.extend({//将animat.css扩展至jquery方法
	    animates: function (animationName) {
	        var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
	        $(this).addClass('animated ' + animationName).one(animationEnd, function() {
	            $(this).removeClass('animated ' + animationName);
	        });
	    }
	});
})($);

/** 配置全局ajax参数 */
$.ajaxSetup({
  url: "/xmlhttp/",
  global: false,
  type: "POST"
});
	

/** jquery post 包装方法 */
lh.post = function(pos, url, obj, fun, type, param){
	if(!pos)return;
	if(_.isObject(type)){
		param = type;
		type = null;
	}
	if(!param)param = {};
	if(lh.ajaxRequesting(param)){
		return '正在执行请求，请不要重复操作';
	}
	if(pos == 'back'){//后台请求
		
	}else if(pos == 'front'){//前台请求
		
	}
	if(!type)type = 'json';
	lh.ajax.ajaxOver = false;
	$.post(url, obj, function(rsp){
		//lh.hideLoading();
		lh.ajax.ajaxOver = true;
		if(param.requesting){
			lh.ajax[param.requesting] = 0;//对应正在请求的时间设置为0
		}
		//frontLoginCheck(rsp);
		fun(rsp);
	}, type);
}

lh.ajaxRequesting = function (param){
	if(!param.requesting)return false;
	var stamp = lh.ajax[param.requesting];
	if(!stamp)stamp = 0;
	var seconds = new Date().getTime()/1000;
	if( stamp === 0 || (stamp + lh.ajax.repeatGap < seconds) ){//lh.ajax.repeatGap:默认ajax请求间隔时间
		lh.ajax[param.requesting] = seconds;
		return false;
	}else {
		console.log('请不要重复进行ajax请求-stamp:'+stamp+' - seconds:'+seconds);
		return true;//尚在防止重复提交时间间隔内，直接返回
	}
}

/** 页面跳转URL地址 */
lh.jump = function (url){
	if(!url)return;
	window.location.href = url; 
}

/** 页面顶部 */
lh.goTop = function (){
	document.documentElement.scrollTop = document.body.scrollTop = 0;
}

/** 刷新当前页 */
lh.reload = function (){
	window.location.reload();
}

/** 刷新当前页 */
lh.refresh = function (){
	window.location.reload();
}

/** 打开新页面 */
lh.open = function (url){
	window.open(url);
}

/** history.back 页面回退返回 */
lh.back = function (index){
	if(index){
		window.history.back(index);
	}else{
		var preUrl = localStorage.getItem('pre_cache_url');
		if(preUrl)localStorage.setItem('cache_url', preUrl);//恢复上一个页面的URL
		window.history.back();
	}
}

/**
 * 向缓存对象中存入数据（lh.cache）
 * param:{url:'',paramObj:{},name:'',domId:'#xx'}（url,name是必须项）
 */
lh.addDataToCache = function(param){
	if(!param || !param.cacheName || !param.data)return;
	lh.cache[param.cacheName] = param.data;
}

/**
 * 从缓存对象中查询获取缓存的对象（复制一份返回）
 * name:使用存入缓存时使用的name做为访问key
 */
lh.getDataFromCache = function(cacheName){
	if(!cacheName)return;
	var data = lh.cache[cacheName];
	if(!data)return null;
	return _.cloneDeep(data);
}

/**
 * 防止重复提交
 * @param {防止重复提交的秒数，即多少秒内禁止重复提交} timeGap
 * @param {记录的操作时间，以此来判断是否超过指定秒数} timeRec
 * @return {成功则返回操作时间，否则返回false}
 */
lh.preventRepeat = function (timeRec, timeGap){
	if(!timeRec)timeRec = lh.status.optTimeRec;
	if(!timeGap)timeGap = lh.status.optTimeGap;
	var now = new Date(); 
	var hh = now.getHours(); 
	var mm = now.getMinutes(); 
	var ss = now.getSeconds(); 
	var sec = (hh * 3600) + (mm * 60) + ss; 
	if((sec - timeRec) > timeGap){//lh.status.optTimeGap:间隔秒数，指定秒内禁止重复提交 
		lh.status.optTimeRec = sec;
		return sec;
	}
	return false;
}

/** 页面底部加载数据 */
lh.scrollBottom = function (func){
	$(window).scroll(function () {
		if(lh.status.scrollLoading)return;
		lh.status.scrollLoading = true;
	    var scrollTop = $(this).scrollTop();
	    var scrollHeight = $(document).height();
	    var windowHeight = $(this).height();
	    if (scrollTop + windowHeight == scrollHeight) {
	    	//此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
			func();	
	    }else{
	    	lh.status.scrollLoading = false;
	    }
	});
}

/** 检查返回登陆错误 */
lh.checkLoginError = function (){
	$.ajaxSetup({
		error:function(data, textStatus) {
			if(data && data.responseText=='toLogin'){
				window.parent.location.href=url;
			}
		}
	});
}

/** 格式化日期，参数：日期，时分-标识，秒-标识 */
lh.formatDate = function (param){
	if(!param)param = {};
	var obj = {
		date: param.date || new Date(), 
		format : param.format, 
		flag : param.flag || 'date'
	};
	if(!_.isDate(param.date))param.date = new Date(param.date);
	if(!obj.format){
		if(obj.flag == 'date'){
			obj.format = 'YYYY-MM-DD';
		}else if(obj.flag == 'datetime'){
			obj.format = 'YYYY-MM-DD HH:mm:ss';
		}
	}
	return moment(obj.date).format(obj.format);
}

/** 生成唯一序号（serial,id...） */
lh.createSerial = function (prefix){
	var str = new Date().getTime() +'_'+ Math.floor(Math.random()*100000);
	if(prefix)str = prefix + '_' + str;
	return str
}

/** 初始化方法 */
lh.init = function (){
	
};

/** 默认执行内容 */
$(function() {
	//lh.checkLoginError();
	
	/** 支持拖动：申明了draggable=true的DIV可直接拖动 */
	/*$('div[draggable=true]').drag(function( ev, dd ){
		$( this ).css({
			top: dd.offsetY,
			left: dd.offsetX
		});
	});*/
})

lh.getCmsPath = function(){
	return localStorage.getItem('cmsPath') || '/manager';
}

lh.getCmsUrl = function(url){
	var contextPath = localStorage.getItem('cmsPath') || '/manager';
	return contextPath + url;
}

lh.cmsJump = function(url){
	var contextPath = localStorage.getItem('cmsPath') || '/manager';
	var fullUrl = contextPath + url;
	if(localStorage){//缓存url,页面刷新后回到此Url
		//var preUrl = localStorage.getItem('cache_url');
		localStorage.setItem('pre_cache_url', location.href);//记录上一个缓存URL地址，调用lh.back()方法时使用此URL
		localStorage.setItem('cache_url', fullUrl);
	}
	location.href = fullUrl;
}

lh.mainPageReload = function(){
	var fullUrl =lh.getCmsUrl('/admin/user/home');
	if(localStorage){//缓存url,页面刷新后回到此Url
		localStorage.setItem('cache_url', fullUrl);
	}
	window.parent.location.reload();
}

/** 表单验证默认提示 */
jQuery.validator.addMethod("phone", function (value, element) {
	return this.optional(element) || /^1\d{10}$/.test(value);
}, "请输入正确格式的电话号码");
jQuery.extend(jQuery.validator.messages, {  
      required: "该字段为必填字段",  
	  remote: "请修正该字段",  
	  email: "请输入正确格式的电子邮件",  
	  url: "请输入合法的网址",  
	  date: "请输入合法的日期",  
	  dateISO: "请输入合法的日期 (ISO).",  
	  number: "请输入合法的数字",  
	  digits: "只能输入整数",  
	  creditcard: "请输入合法的信用卡号",  
	  equalTo: "请再次输入相同的值",  
	  accept: "请输入拥有合法后缀名的字符串",  
	  maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),  
	  minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),  
	  rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),  
	  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),  
	  max: jQuery.validator.format("请输入一个最大为 {0} 的值"),  
	  min: jQuery.validator.format("请输入一个最小为 {0} 的值")  
}); 

lh.doValidate = function (domId, dontAlert){
	var form = $("#"+domId);
	form.validate();
	var validFlag = form.valid();
	if(!validFlag){
		if(!dontAlert){//lh.tip.formInvalid:common.base底部定义
			lh.alert(lh.tip.formInvalid);return false;
		}
		return false;
	}
	return true;
}

lh.tip = {
	formInvalid:'您输入的数据不符合规范，请按照提示信息进行修改'
};

