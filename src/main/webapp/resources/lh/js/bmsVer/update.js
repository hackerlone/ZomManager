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
			serverVersion: {required: true},
			clientOsType: {required: true},
			clientVersion: {required: true},
			clientUrl: {required: true},
			latestServer: {required: true},
			latestClient: {required: true},
			clientDescription: {required: true}
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


function updateMainObj(corpId){
	if(!lh.doValidate('addForm'))return false;
	var serverVersion = $("#serverVersion").val();//服务器版本
	var clientOsType = $("#clientOsType").val();//客户端系统
	var clientVersion = $("#clientVersion").val();
	var clientUrl = $("#clientUrl").val();
	var latestServer = $("#latestServer").val();
	var latestClient = $("#latestClient").val();
	var clientDescription = $("#clientDescription").val();
	
	var param = {
		id:corpId,
		serverVersion:serverVersion, 	 
		clientOsType:clientOsType,
		clientVersion:clientVersion,
		clientUrl:clientUrl,
		latestServer:latestServer,
		latestClient:latestClient,
		clientDescription:clientDescription
	};
	var url = lh.getCmsUrl("/admin/version/update/"+corpId);
	lh.post('back', url, param,function(rsp){
		if(rsp.success){
			lh.alert({content:'您已经成功修改客户端版本信息', clickYes:jumpBack});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function jumpBack(){
	lh.cmsJump('/admin/version/list');
}