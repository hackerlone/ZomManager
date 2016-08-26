/** 基础数据和基础设置 */
$(function() {
			init();
		});

function init() {
	ace.settings.check('breadcrumbs', 'fixed')
	initValiddate();
}

function initValiddate() {
	$('#addForm').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : false,
		ignore : "",
		rules : {
			username : {
				required : true
			},
			corpName : {
				required : true
			},
			corpPassword : {
				required : true
			},
			confirmCorpPwd : {
				required : true
			},
			phone : {
				required : true
			},
			email : {
				required : true
			},
			expireDate : {
				required : true
			},
			maxUser : {
				required : true
			},
			maxGroup : {
				required : true
			},
			maxConsole : {
				required : true
			},
			permissionLevel : {
				required : true
			},
			priorityLevel : {
				required : true
			},
			zoneId : {
				required : true
			}
		},
		messages : {},
		highlight : function(e) {
			$(e).closest('.form-group').removeClass('has-info')
					.addClass('has-error');
		},
		success : function(e) {
			$(e).closest('.form-group').removeClass('has-error');// .addClass('has-info');
			$(e).remove();
		},
		errorPlacement : function(error, element) {
			/*
			 * if(element.is('input[type=checkbox]') ||
			 * element.is('input[type=radio]')) { var controls =
			 * element.closest('div[class*="col-"]');
			 * if(controls.find(':checkbox,:radio').length > 1)
			 * controls.append(error); else
			 * error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0)); } else
			 * if(element.is('.select2')) {
			 * error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)')); }
			 * else if(element.is('.chosen-select')) {
			 * error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)')); }
			 * else{ error.insertAfter(element); }
			 */
			error.insertAfter(element);
		},
		submitHandler : function(form) {
		},
		invalidHandler : function(form) {
		}
	});
}

function addMainObj() {
	if (!lh.doValidate('addForm'))
		return false;
	var username = $("#username").val();
	var corpName = $("#corpName").val();
	var corpPassword = $("#corpPassword").val();
	var confirmCorpPwd = $("#confirmCorpPwd").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var expireDate = $("#date-timepicker1").val();
	var newexpireDate = new Date(expireDate);
	
	var maxUser = $("#maxUser").val();
	var maxGroup = $("#maxGroup").val();
	var maxConsole = $("#maxConsole").val();
	var permissionLevel = $("#permissionLevel").val();
	var priorityLevel = $("#priorityLevel").val();
	var zone = $("#zone").val();
	var zoneId = JSON.stringify(zone);
	if (corpPassword != confirmCorpPwd) {
		lh.alert('两次输入的密码不一致');
		return;
	}
	var param = {
		username : username,
		corpName : corpName,
		corpPassword : corpPassword,
		phone : phone,
		email : email,
		expireDate : newexpireDate,
		maxUser : maxUser,
		maxGroup : maxGroup,
		maxConsole : maxConsole,
		permissionLevel : permissionLevel,
		priorityLevel : priorityLevel,
		zoneId : zoneId
	};
	var url = lh.getCmsUrl("/admin/corp/add");
	lh.post('back', url, param, function(rsp) {
				if (rsp.success) {
					lh.alert({
								content : '添加公司信息成功',
								clickYes : jumpBack
							});
				} else {
					lh.alert(rsp.msg);
				}
			}, 'json');
}

function jumpBack() {
	lh.cmsJump('/admin/corp/users');
}