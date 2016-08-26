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
			groupName: {required: true},
			status: {required: true}

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
	var groupName = $("#groupName").val();
	var status = $("#status").val();
	var ownerId = $("#ownerId").val();
	if(!ownerId){lh.alert("请选择一个组管理员，如果系统没有任何用户，请先创建用户");return;}
	var param = {
		groupName:groupName, 	 
		ownerId:ownerId, 	 
		status:status
	};
	var url = lh.getCmsUrl("/admin/group/add");
	lh.post('back', url, param,function(rsp){
		if(rsp.success){
			lh.groupIds = rsp.groupIds;
			lh.confirm({title:'添加成功',content:'是否选择组和控制台?',
			clickYes:jumpToUpdate,clickCancel:jumpBack});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function jumpToUpdate(){
	lh.cmsJump('/admin/group/update?id='+lh.groupIds);
}

function jumpBack(){
	lh.cmsJump('/admin/group/groups');
}