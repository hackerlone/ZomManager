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
			email: {required: true,email:true},
			corpName: {required: true,minlength: 2},
			phone: {required: true,phone:true},
			maxUser: {required: true,digits:true,min:0},
			maxGroup: {required: true,digits:true,min:0},
			maxConsole: {required: true,digits:true,min:0},
			maxUserGroup: {required: true,digits:true,min:0}
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


function updateManiObj(corpId){
	if(!lh.doValidate('addForm'))return false;
	var corpName = $("#corpName").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var maxUser = $("#maxUser").val();
	var maxGroup = $("#maxGroup").val();
	var maxConsole = $("#maxConsole").val();
	var maxUserGroup = $("#maxUserGroup").val();
	/*var zone = $("#zone").val();*/
	var param = {
		id:corpId,
		corpName:corpName, 	 
		phone:phone,
		email:email,
		maxUser:maxUser,
		maxGroup:maxGroup,
		maxConsole:maxConsole,
		maxUserGroup:maxUserGroup
		/*zoneId:zone*/
	};
	var url = lh.getCmsUrl("/admin/corp/update/"+corpId);
	lh.post('back', url, param,function(rsp){
		if(rsp.success){
			lh.alert({content:'您已经成功修改公司基本信息', clickYes:jumpBack});
		}else{
			lh.alert(rsp.msg);
		}
	},'json', {requesting:'/admin/corp/update'});
}

function jumpBack(){
	lh.cmsJump('/admin/corp/users');
}