/** 基础数据和基础设置 */
$(function() {
			init();
		});

function init() {
	ace.settings.check('breadcrumbs', 'fixed')
	initValiddate();
	loadMainGrid();
	loadMainGrid1();
	loadMainGrid2();
	$('#datagrid1_wrapper .row:first').hide();
	$('#consol2Table_wrapper .row:first').hide();
	$('#consol1Table_wrapper .row:first').hide();
}

function initValiddate() {
	$('#addForm').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : false,
		ignore : "",
		rules : {
			nickname : {
				required : true
			},
			phone : {
				required : true
			},
			priority : {
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
	var nickname = $("#nickname").val();
	var phone = $("#phone").val();
	var priority = $("#priority").val();

	var param = {
		id : corpId,
		nickname : nickname,
		phone : phone,
		priority : priority
	};
	var url = lh.getCmsUrl("/admin/console/update/" + corpId);
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

function loadMainGrid() {
	var groupId = $("#groupId").val();
	lh.gridParam = {
		groupId : groupId
	};
	var url = lh.getCmsUrl("/admin/user/getConsolerUserList");
	$('#datagrid1').dataTable({
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
	var url = lh.getCmsUrl("/admin/console/addConsolerUser");
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
	var url = lh.getCmsUrl("/admin/console/delConsolerUser");
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
	var url = lh.getCmsUrl("/admin/console/addConsolerUser");
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
	var url = lh.getCmsUrl("/admin/console/delConsolerUser");
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
	var table = $('#datagrid1').DataTable();
	table.ajax.reload();
}

function loadMainGrid1() {
	var logonName = $("#logonName").val();
	lh.gridParam1 = {
		logonName : logonName
	};
	var url = lh.getCmsUrl("/admin/user/getUserConsoler2List");
	$('#consol2Table').dataTable({
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
				_.assign(d, lh.gridParam1); // 合并参数对象，添加额外的参数传给服务器
			}
		},
		columns : [{
			"data" : "id",
			render : function(data, type, row, meta) {
				if (data) {
					return '<input name="groupId1" type="checkbox" value="'
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
					return '<span style="color:orange;">未加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="addGroupUser1('
							+ row.id + ');">加入</span>';
				} else if (data > 0) {
					return '<span style="color:green;">已加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="delGroupUser1('
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
function addGroupUser1(uid) {
	var url = lh.getCmsUrl("/admin/console/addConsoler1To2");
	var logonName = $("#logonName").val();
	var adminId = new Array();
	adminId[0] = uid;
	var adminsId = JSON.stringify(adminId);
	lh.post('back', url, {
				adminIds : adminsId,
				logonName : logonName
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid1();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function delGroupUser1(uid) {
	var url = lh.getCmsUrl("/admin/console/delConsoler1To2");
	var logonName = $("#logonName").val();
	var adminId = new Array();
	adminId[0] = uid;
	var adminsId = JSON.stringify(adminId);
	lh.post('back', url, {
				adminIds : adminsId,
				logonName : logonName
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid1();
				} else {
					lh.alert(rsp.msg);
				}
			});
}

function checkAllInput1() {
	var groupIds = $("#groupIds1").is(':checked');
	if (groupIds) {
		$("input[name='groupId1']").prop('checked', true);
		$("input[name='groupIds1']").prop('checked', true);
	} else {
		$("input[name='groupId1']").prop('checked', false);
		$("input[name='groupIds1']").prop('checked', false);
	}
}
function addAllGroupUser1(uid) {
	var logonName = $("#logonName").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/console/addConsoler1To2");
	var arrChk = $("input[name='groupId1']:checked");
	var adminId = new Array();
	$(arrChk).each(function() {
				adminId[i] = this.value;
				i++;
			});
	var adminIds = JSON.stringify(adminId);
	lh.post('back', url, {
				adminIds : adminIds,
				logonName : logonName
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid1();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function delAllGroupUser1(uid) {
	var logonName = $("#logonName").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/console/delConsoler1To2");
	var arrChk = $("input[name='groupId1']:checked");
	var adminId = new Array();
	$(arrChk).each(function() {
				adminId[i] = this.value;
				i++;
			});
	var adminIds = JSON.stringify(adminId);
	lh.post('back', url, {
				adminIds : adminIds,
				logonName : logonName
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid1();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function reloadMainGrid1() {
	var table = $('#consol2Table').DataTable();
	table.ajax.reload();
}

function loadMainGrid2() {
	var userId = $("#userId").val();
	lh.gridParam2 = {
		usersId : userId
	};
	var url = lh.getCmsUrl("/admin/user/getUserConsoler1List");
	$('#consol1Table').dataTable({
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
				_.assign(d, lh.gridParam2); // 合并参数对象，添加额外的参数传给服务器
			}
		},
		columns : [{
			"data" : "username",
			render : function(data, type, row, meta) {
				if (data) {
					return '<input name="groupId2" type="checkbox" value="'
							+ row.username + '">';
				}
				return data;
			}
		}, {
			"data" : "nickname"
		}, {
			"data" : "isJoin",
			render : function(data, type, row, meta) {
				if (data == 0) {
					return '<span style="color:orange;">未加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="addGroupUser2('
							+ "'" + row.username + "'" + ');">加入</span>';
				} else if (data > 0) {
					return '<span style="color:green;">已加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="delGroupUser2('
							+ "'" + row.username + "'" + ');">退出</span>';
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
function addGroupUser2(userName) {
	var url = lh.getCmsUrl("/admin/console/addConsoler2To1");
	var adminId = $("#userId").val();
	var userNames = new Array();
	userNames[0] = userName;
	var userNamess = JSON.stringify(userNames);
	lh.post('back', url, {
				adminId : adminId,
				userNamess : userNamess
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid2();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function delGroupUser2(userName) {
	var url = lh.getCmsUrl("/admin/console/delConsoler2To1");
	var adminId = $("#userId").val();
	var userNames = new Array();
	userNames[0] = userName;
	var userNamess = JSON.stringify(userNames);
	lh.post('back', url, {
				adminId : adminId,
				userNamess : userNamess
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid2();
				} else {
					lh.alert(rsp.msg);
				}
			});
}

function checkAllInput2() {
	var groupIds = $("#groupIds2").is(':checked');
	if (groupIds) {
		$("input[name='groupId2']").prop('checked', true);
		$("input[name='groupIds2']").prop('checked', true);
	} else {
		$("input[name='groupId2']").prop('checked', false);
		$("input[name='groupIds2']").prop('checked', false);
	}
}
function addAllGroupUser2(adminId) {
	// var logonName = $("#logonName").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/console/addConsoler2To1");
	var arrChk = $("input[name='groupId2']:checked");
	var userNames = new Array();
	$(arrChk).each(function() {
				userNames[i] = this.value;
				i++;
			});
	var userNamess = JSON.stringify(userNames);
	lh.post('back', url, {
				adminId : adminId,
				userNamess : userNamess
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid2();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function delAllGroupUser2(adminId) {
	// var logonName = $("#logonName").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/console/delConsoler2To1");
	var arrChk = $("input[name='groupId2']:checked");
	var userNames = new Array();
	$(arrChk).each(function() {
				userNames[i] = this.value;
				i++;
			});
	var userNamess = JSON.stringify(userNames);
	lh.post('back', url, {
				adminId : adminId,
				userNamess : userNamess
			}, function(rsp) {
				if (rsp.success) {
					reloadMainGrid2();
				} else {
					lh.alert(rsp.msg);
				}
			});
}
function reloadMainGrid2() {
	var table = $('#consol1Table').DataTable();
	table.ajax.reload();
}

function jumpBack() {
	var isRank1 = $("#isRank1").val();
	if (isRank1 == 'true') {
		lh.cmsJump('/admin/console/level1users');
	} else {
		lh.cmsJump('/admin/console/level2users');
	}
}