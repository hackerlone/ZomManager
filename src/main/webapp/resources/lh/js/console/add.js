/** 基础数据和基础设置 */
$(function() {
	init();
});

function init(){
	ace.settings.check('breadcrumbs', 'fixed')
	initValiddate();
}

function initValiddate(){
	$('#addForm').validate({
		errorElement: 'span',
		errorClass: 'help-block',
		focusInvalid: false,
		ignore: "",
		rules: {
			nickname: {required: true},
			username: {required: true},
			password: {required: true},
			confirmPwd: {required: true},
			phone: {phone: true},
			priority: {required: true}
		},
		messages: {},
		highlight: function (e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},
		success: function (e) {
			$(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
			$(e).remove();
		},
		errorPlacement: function (error, element) {
			/*if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
				var controls = element.closest('div[class*="col-"]');
				if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
				else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
			}
			else if(element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			}
			else if(element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			}
			else{
				error.insertAfter(element);
			}*/
			error.insertAfter(element);
		},
		submitHandler: function (form) {},
		invalidHandler: function (form) {}
	});
}


function addMainObj(){
	var isRank1 = $("#isRank1").val();
	if(!lh.doValidate('addForm'))return false;
	var nickname = $("#nickname").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var confirmPwd = $("#confirmPwd").val();
	var phone = $("#phone").val();
	var priority = $("#priority").val();
	
	if(confirmPwd != password){lh.alert('两次输入的密码不一致');return;}
	var param = {
			nickname:nickname,
			username:username,
			password:password,
			phone:phone,
			priority:priority
	};
	var urls = "";
	if(isRank1 == 'true'){
		urls = "/admin/console/addrank1";
	}else{
		urls = "/admin/console/addrank2";
	}
	var url = lh.getCmsUrl(urls);
	lh.post('back', url, param,function(rsp){
		if(rsp.success){
			lh.alert({content:'添加控制台成功!', clickYes:jumpBack});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function jumpBack(){
	var isRank1 = $("#isRank1").val();
	if(isRank1 == 'true'){
		lh.cmsJump('/admin/console/level1users');
	}else{
		lh.cmsJump('/admin/console/level2users');
	}
}