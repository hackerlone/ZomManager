(function($){
	var __validate = $.fn.validate;
	$.fn.cmsvalidate = function(opts) {
		var __rules = $.extend({
			username:"required",
			password:"required",
			corpPassword: "required",
			name:"required",
			phone:"required",
			confirmPwd:{
				equalTo:"#password"
			},
			"confirmCorpPwd":{
				equalTo:"#corpPassword"
			},
			email:"email",			
			title:"required",
			cid:{
				min:1
			},
			newName:"required",
			priority:{
				min:11,
				max:30
			},
			userPriority:{
				min:1,
				max:10
			},
			serverVersion:"required",
			clientOsType:"required",
			clientVersion:"required",
			clientUrl:"required",
			latestServer:"required",
			latestClient:"required",
			groupName:"required",
		},opts?(opts.rules||{}):{});
		var __messages = $.extend({
			username:"用户名不能为空",
			password:"用户密码不能为空",
			confirmPwd:"两次输入的密码不正确",
			confirmCorpPwd:"两次输入的密码不正确",
			email:"邮件格式不正确",
			name:"名称不能为空",
			cid:"文章必须选择所属栏目",
			title:"文章的标题必须输入",
			newName:"首页图片必须上传",
			priority:"请输入11到30优先级",
			userPriority:"请输入1到10优先级",
		    phone:"手机号码不能为空",
		    serverVersion:"服务器版本不能为空",
		    clientOsType:"客户端操作系统不能为空",
		    clientVersion:"客户端版本版本不能为空",
		    clientUrl:"客户端地址不能为空",
		    latestServer:"是否是最新服务器版本不能为空",
		    latestClient:"是否是最新客户端版本不能为空",
		    groupName:"组名不能为空"
		},opts?(opts.messages||{}):{});
		var __defaultOpts = $.extend(opts||{},{
			rules:__rules,
			messages:__messages,
			errorElement: opts?(opts.errorElement||"span"):"span",
			errorClass:opts?(opts.errorClass||"errorContainer"):"errorContainer"
		});
		$.extend($.fn.validate.prototype,__defaultOpts);
		__validate.call(this,__defaultOpts);
		return this;
	}
})(jQuery)