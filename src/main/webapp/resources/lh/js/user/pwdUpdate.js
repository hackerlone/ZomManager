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
			oldpwd: {required: true,minlength: 5},
			password: {required: true,minlength: 5},
			confirmPwd: {required: true,minlength: 5}
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

function updatePassword(corpId){
	if(!lh.doValidate('addForm'))return false;
	var oldpwd = $("#oldPwd").val();
	var password = $("#password").val();
	var confirmPwd = $("#confirmPwd").val();
	if(password.length < 5){
		lh.alert('新密码长度不能小于5位字符');return;
	}
	if(password != confirmPwd){
		lh.alert('两次输入的密码不一致');return;
	}
	var param = {
		id:corpId,
		oldpwd:oldpwd, 	 
		password:password
	};
	var url = lh.getCmsUrl("/admin/user/updatePwd");
	lh.post('back', url, param,function(rsp){
		if(rsp.success){
			lh.alert({content:'您已经成功修改密码', clickYes:lh.back});
		}else{
			lh.alert(rsp.msg);
		}
	},'json', {requesting:'/admin/user/updatePwd'});
}
