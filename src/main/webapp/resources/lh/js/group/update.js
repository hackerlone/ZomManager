/** 基础数据和基础设置 */
$(function() {
			init();
		});

function init() {
	ace.settings.check('breadcrumbs', 'fixed')
	initValiddate();
	loadMainGrid();
	$('#datagrid_wrapper .row:first').hide();
	$('#datagridConsoler_wrapper .row:first').hide();
}

function initValiddate() {
	$('#addForm').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : false,
		ignore : "",
		rules : {
			groupName : {
				required : true
			},
			status : {
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

function updateMainObj(corpId) {
	if (!lh.doValidate('addForm'))
		return false;
	var groupName = $("#groupName").val();// 服务器版本
	var status = $("#status").val();// 客户端系统

	var param = {
		id : corpId,
		groupName : groupName,
		status : status
	};
	var url = lh.getCmsUrl("/admin/group/update/" + corpId);
	lh.post('back', url, param, function(rsp) {
				if (rsp.success) {
					lh.alert({
								content : '您已经成功修改组信息',
								clickYes : jumpBack
							});
				} else {
					lh.alert(rsp.msg);
				}
			}, 'json');
}

function jumpBack() {
	lh.cmsJump('/admin/group/groups');
}

function loadMainGrid() {
	var groupId = $("#groupId").val();
	lh.gridParam = {
		groupId : groupId
	};
	var url = lh.getCmsUrl("/admin/user/getGroupUserList");
	$('#datagrid').dataTable({
		serverSide : true,
		processing : false,
		paging : true,
		ordering : false,
		searching : false,
		pagingType : "full_numbers",
		displayLength : 10,
		info : true,
		ajax : {
			url : url,
			method : "post",
			dataSrc : "rows",
			data : function(d) {
				if (d) {
					delete d.columns;
					delete d.search;
				}
				_.assign(d, lh.gridParam); // 合并参数对象，添加额外的参数传给服务器
			}
		},
		columns : [{
			"data" : "id",
			render : function(data, type, row, meta) {
				if (data) {
					return '<input name="groupId" type="checkbox" value="'
							+ row.id + '">';
				}
				return data;
			}
		}, {
			"data" : "nickname"
		}, {
			"data" : "isJoin",
			render : function(data, type, row, meta) {
				if (data == 0) {
					return '<span style="color:orange;">未加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="addGroupUser('
							+ row.id + ');">加入</span>';
				} else if (data > 0) {
					return '<span style="color:green;">已加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="delGroupUser('
							+ row.id + ');">退出</span>';
				}
			}
		}

		],
		language : {
			"lengthMenu" : "_MENU_ 条记录每页",
			"zeroRecords" : "没有找到记录",
			"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_条数据 )",// _PAGE_,_PAGES_,_MAX_
			"infoEmpty" : "无记录",
			"infoFiltered" : "(从 _MAX_ 条记录过滤)",
			"paginate" : {
				"previous" : "上一页",
				"next" : "下一页",
				"first" : "首页",
				"last" : "尾页"
			}
		}
	});

}
function addGroupUser(uid) {
	var url = lh.getCmsUrl("/admin/group/addGroupUser");
	var groupId = $("#groupId").val();
	var userId = new Array();
	userId[0] = uid;
	var usersId = JSON.stringify(userId);
	lh.post('back', url, {
				userId : usersId,
				groupId : groupId
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function delGroupUser(uid) {
	var url = lh.getCmsUrl("/admin/group/delGroupUser");
	var groupId = $("#groupId").val();
	var userId = new Array();
	userId[0] = uid;
	var usersId = JSON.stringify(userId);
	lh.post('back', url, {
				userId : usersId,
				groupId : groupId
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid();
				} else {
					lh.alert(rsp.msg);
				}
			});
}

function checkAllInput() {
	var groupIds = $("#groupIds").is(':checked');
	if (groupIds) {
		$("input[name='groupId']").prop('checked', true);
		$("input[name='groupIds']").prop('checked', true);
	} else {
		$("input[name='groupId']").prop('checked', false);
		$("input[name='groupIds']").prop('checked', false);
	}
}
function addAllGroupUser(uid) {
	var groupId = $("#groupId").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/group/addGroupUser");
	var arrChk = $("input[name='groupId']:checked");
	var userId = new Array();
	$(arrChk).each(function() {
				userId[i] = this.value;
				i++;
			});
	var userIds = JSON.stringify(userId);
	lh.post('back', url, {
				userId : userIds,
				groupId : groupId
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function delAllGroupUser(uid) {
	var groupId = $("#groupId").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/group/delGroupUser");
	var arrChk = $("input[name='groupId']:checked");
	var userId = new Array();
	$(arrChk).each(function() {
				userId[i] = this.value;
				i++;
			});
	var userIds = JSON.stringify(userId);
	lh.post('back', url, {
				userId : userIds,
				groupId : groupId
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function reloadMainGrid() {
	var table = $('#datagrid').DataTable();
	table.ajax.reload();
}
