/** 基础数据和基础设置 */
$(function() {
	init();
});

function init(){
	ace.settings.check('breadcrumbs', 'fixed')
	initValiddate();
//	loadMainGrid();
}

function initValiddate(){
	$('#addForm').validate({
		errorElement: 'span',
		errorClass: 'help-block',
		focusInvalid: false,
		ignore: "",
		rules: {
			nickname: {required: true},
			password: {required: true},
			userPriority: {required: true},
			confirmPwd: {required: true},
			phone: {required: true}
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
	if(!lh.doValidate('addForm'))return false;
	var nickname = $("#nickname").val();
	var password = $("#password").val();
	var confirmPwd = $("#confirmPwd").val();
	var userPriority = $("#userPriority").val();
	var phone = $("#phone").val();
	if(password != confirmPwd){
		lh.alert('两次输入的密码不一致');return;
	}
	/*var consoleIds=$('input[name="consoleIds"]').val();
	var groupIdss=$('input[name="groupIds"]').val();
	var groupIds = new Array();
		groupIds[0] = 1;
		groupIds[1] = 2;
		groupIds[2] = 3;*/
	var param = {
		nickname:nickname, 	 
		phone:phone,
		userPriority:userPriority,
		password:password
		/*groupIds:groupIds*/
	};
//	alert(groupIds.length);
	var url = lh.getCmsUrl("/admin/user/add/");
	lh.post('back', url, param,function(rsp){
		if(rsp.success){
			lh.userId = rsp.userIds;
			lh.confirm({title:'添加成功',content:'是否选择组和控制台?', clickYes:jumpToUpdate,clickCancel:jumpBack});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function jumpToUpdate(){
	lh.cmsJump('/admin/user/update?id='+lh.userId);
}

function jumpBack(){
	lh.cmsJump('/admin/user/users');
}