/** 基础数据和基础设置 */
$(function() {
	init();
});

function init(){
	ace.settings.check('breadcrumbs', 'fixed')
	initValiddate();
	loadMainGrid();
	loadMainGrid1();
	
	$('#datagrid_wrapper .row:first').hide();
	$('#datagridConsoler_wrapper .row:first').hide();
	
}

function initValiddate(){
	$('#addForm').validate({
		errorElement: 'span',
		errorClass: 'help-block',
		focusInvalid: false,
		ignore: "",
		rules: {
			nickname: {required: true},
			phone: {required: true},
			userPriority: {required: true}
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


function updateMainObj(userId){
//	if(!lh.doValidate('addForm'))return false;
	var nickname = $("#nickname").val();
	var phone = $("#phone").val();
	var userPriority = $("#userPriority").val();//优先级
	var consoleIds=$('input[name="consoleIds"]').val();
	var groupIdss=$('input[name="groupIds"]').val();

	var groupIds = new Array();
		groupIds[0] = 1;
		groupIds[1] = 2;
		groupIds[2] = 3;
	var param = {
		id:userId,
		nickname:nickname, 	 
		phone:phone,
		userPriority:userPriority,
		consoleIds:consoleIds
		/*groupIds:groupIds*/
	};
//	alert(groupIds.length);
	var url = lh.getCmsUrl("/admin/user/update/"+userId);
	lh.post('back', url, param,function(rsp){
		if(rsp.success){
			lh.alert({content:'您已经成功修改用户信息', clickYes:jumpBack});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}



function loadMainGrid1(){
	var userId = $("#userId").val();
	lh.gridParam1 = {groupId:userId};
	var url = lh.getCmsUrl("/admin/user/getUserConsolerList");
	$('#datagridConsoler').dataTable( {
		serverSide: true,
		processing: false,
		paging:true,
		ordering:false,
		searching:false,
		pagingType:"full_numbers",
		displayLength : 10,
		info:true,
        ajax:{
            url: url,
            method:"post",
            dataSrc:"rows",
            data: function ( d ) {  
            	if(d){
            		delete d.columns;
            		delete d.search;
            	}
                _.assign(d, lh.gridParam1);   //合并参数对象，添加额外的参数传给服务器  
            }  
        },
       columns: [
       		/*{field:'checkbox',title:'多选框',checkbox:true},*/
			/*{field:'id',title:'',hidden:true},*/
           
           	 { "data": "id", render: function(data, type, row, meta) {
            	if(data){
            		 return '<input name="groupId1" type="checkbox" value="'+row.id+'">';
            	}
            	return data;
	        }},
            { "data": "nickname" },
	         { "data": "isJoin", render: function(data, type, row, meta) {
            	if(data == 0){
            		 return '<span style="color:orange;">未加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="addUserGroup1('+row.id+');">加入</span>';
            	}else if(data > 0){
            		 return '<span style="color:green;">已加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="delUserGroup1('+row.id+');">退出</span>';
            	}
	        }}
           
        ],
        language: {
            "lengthMenu": "_MENU_ 条记录每页",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_条数据 )",//_PAGE_,_PAGES_,_MAX_
            "infoEmpty": "无记录",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页",
                "first": "首页",
                "last": "尾页"
            }
        }
    });
}
function addUserGroup1(uid){
	var url = lh.getCmsUrl("/admin/console/addConsolerUser");
	var groupId = $("#userId").val();
	var userId = new Array();
	userId[0] = uid;
	var usersId =  JSON.stringify(userId);
	lh.post('back', url, {userId:usersId,groupId:groupId}, function(rsp){
		 if(rsp.success){
			reloadMainGrid1();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
}
function delUserGroup1(uid){
	var url = lh.getCmsUrl("/admin/console/delConsolerUser");
	var groupId = $("#userId").val();
	var userId = new Array();
	userId[0] = uid;
	var usersId =  JSON.stringify(userId);
	lh.post('back', url, {userId:usersId,groupId:groupId}, function(rsp){
		 if(rsp.success){
			reloadMainGrid1();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
}

function checkAllInput1(){
	var groupIds = $("#groupIds1").is(':checked');
	if(groupIds){
		$("input[name='groupId1']").prop('checked',true);
		$("input[name='groupIds1']").prop('checked',true);
	}else{
		$("input[name='groupId1']").prop('checked',false);
		$("input[name='groupIds1']").prop('checked',false);
	}
}
function addAllUserGroup1(uid){
	var groupId = $("#userId").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/console/addConsolerUser");
	var arrChk=$("input[name='groupId1']:checked");
    var userId = new Array();
    $(arrChk).each(function(){
          userId[i] =  this.value;   
    	  i++;
    });
	var userIds = JSON.stringify(userId);
	lh.post('back', url, {userId:userIds,groupId:groupId}, function(rsp){
		 if(rsp.success){
			reloadMainGrid1();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
}
function delAllUserGroup1(uid){
	var groupId = $("#userId").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/console/delConsolerUser");
	var arrChk=$("input[name='groupId1']:checked");
    var userId = new Array();
    $(arrChk).each(function(){
          userId[i] =  this.value;                            
       		i++;
    });
	var userIds =  JSON.stringify(userId);
	lh.post('back', url, {userId:userIds,groupId:groupId}, function(rsp){
		 if(rsp.success){
			reloadMainGrid1();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
}
function reloadMainGrid1(){
	var table = $('#datagridConsoler').DataTable();
    table.ajax.reload();
}

function loadMainGrid(){
	var userId = $("#userId").val();
	lh.gridParam = {usersId:userId};
	var url = lh.getCmsUrl("/admin/group/getUserGroupList");
	$('#datagrid').dataTable( {
		serverSide: true,
		processing: false,
		paging:true,
		ordering:false,
		searching:false,
		pagingType:"full_numbers",
		displayLength : 10,
		info:true,
        ajax:{
            url: url,
            method:"post",
            dataSrc:"rows",
            data: function ( d ) {  
            	if(d){
            		delete d.columns;
            		delete d.search;
            	}
                _.assign(d, lh.gridParam);   //合并参数对象，添加额外的参数传给服务器  
            }  
        },
       columns: [
       		/*{field:'checkbox',title:'多选框',checkbox:true},*/
			/*{field:'id',title:'',hidden:true},*/
           
           	 { "data": "id", render: function(data, type, row, meta) {
            	if(data){
            		 return '<input name="groupId" type="checkbox" value="'+row.id+'">';
            	}
            	return data;
	        }},
            { "data": "groupName" },
	         { "data": "isJoin", render: function(data, type, row, meta) {
            	if(data == 0){
            		 return '<span style="color:orange;">未加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="addUserGroup('+row.id+');">加入</span>';
            	}else if(data > 0){
            		 return '<span style="color:green;">已加入</span><span style="color: #307ED3;margin-left: 15px;cursor: pointer;" onclick="delUserGroup('+row.id+');">退出</span>';
            	}
	        }}
           
        ],
        language: {
            "lengthMenu": "_MENU_ 条记录每页",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_条数据 )",//_PAGE_,_PAGES_,_MAX_
            "infoEmpty": "无记录",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页",
                "first": "首页",
                "last": "尾页"
            }
        }
    });
	
}
function addUserGroup(gid){
	var url = lh.getCmsUrl("/admin/user/addUserGroup");
	var usersId = $("#userId").val();
	var groupId = new Array();
	groupId[0] = gid;
	var groupIds =  JSON.stringify(groupId);
	lh.post('back', url, {userId:usersId,groupId:groupIds}, function(rsp){
		 if(rsp.success){
			reloadMainGrid();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
	
}
function delUserGroup(gid){
	var usersId = $("#userId").val();
	var url = lh.getCmsUrl("/admin/user/delUserGroup");
	var groupId = new Array();
	groupId[0] = gid;
	var groupIds =  JSON.stringify(groupId);
	lh.post('back', url, {userId:usersId,groupId:groupIds}, function(rsp){
		 if(rsp.success){
			reloadMainGrid();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
	
}

function checkAllInput(){
	var groupIds = $("#groupIds").is(':checked');
//	alert(groupIds);
	if(groupIds){
		$("input[name='groupId']").prop('checked',true);
		$("input[name='groupIds']").prop('checked',true);
	}else{
		$("input[name='groupId']").prop('checked',false);
		$("input[name='groupIds']").prop('checked',false);
	}
}
function addAllUserGroup(userId){
	var usersId = $("#userId").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/user/addUserGroup");
	var arrChk=$("input[name='groupId']:checked");
    var groupId = new Array();
    $(arrChk).each(function(){
          groupId[i] =  this.value;   
    	  i++;
    });
	var groupIds =  JSON.stringify(groupId);
	lh.post('back', url, {userId:usersId,groupId:groupIds}, function(rsp){
		 if(rsp.success){
			reloadMainGrid();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
}
function delAllUserGroup(userId){
	var usersId = $("#userId").val();
	var i = 0;
	var url = lh.getCmsUrl("/admin/user/delUserGroup");
	var arrChk=$("input[name='groupId']:checked");
    var groupId = new Array();
    $(arrChk).each(function(){
          groupId[i] =  this.value;                            
       		i++;
    });
	var groupIds =  JSON.stringify(groupId);
	lh.post('back', url, {userId:usersId,groupId:groupIds}, function(rsp){
		 if(rsp.success){
			reloadMainGrid();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
}


function reloadMainGrid(){
	var table = $('#datagrid').DataTable();
    table.ajax.reload();
}

function jumpBack(){
	lh.cmsJump('/admin/user/users');
}

