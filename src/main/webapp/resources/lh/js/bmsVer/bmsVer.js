/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'bmsVer',
	mainObjUpperName : 'BmsVer'
}
lh.gridParam = {};
$(function() {
	
	/*lh.post('back', lh.getCmsUrl('/admin/corp/getCorporationList'), null, function(rsp){
		 if(rsp.success){
			conlole.log(rsp);
		 }else{
			 $.messager.alert('提示',rsp.msg);
		 }
	});*/
	//loadGrid();
	//initQueryComponent();
	//initUploadSimple({showEdBtns:true});
	
	loadMainGrid();
	$('#datagrid_wrapper .row:first').hide();
});

function loadMainGrid(){
	var url = lh.getCmsUrl("/admin/version/getVersionList");
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
            { "data": "id" },
            { "data": "serverVersion" },
           /* { "data": "status", render: function(data, type, row, meta) {
	            return data == 2 ? '停用':'启用';
	        }},*/
            { "data": "clientOsType" },
            { "data": "clientVersion" },
            { "data": "clientUrl" },
            { "data": "latestServer", render: function(data, type, row, meta) {
            	if(data == 0){
            		 return '<span style="color:orange;">NO</span>';
            	}else if(data == 1){
            		 return '<span style="color:green;">YES</span>';
            	}
	        }},
             { "data": "latestClient", render: function(data, type, row, meta) {
            	if(data == 0){
            		 return '<span style="color:orange;">NO</span>';
            	}else if(data == 1){
            		 return '<span style="color:green;">YES</span>';
            	}
	        }},
            { "data": "clientDescription" },
            {
	            "data": null,
	            render: function(data, type, row, meta) {
	            	var dom = '<div style="text-align:center;">' +
		            		  '<a class="btn btn-xs btn-success" onclick="lh.cmsJump(\'/admin/version/update?id='+row.id+'\');" href="javascript:" title="编辑"><i class="ace-icon fa fa-pencil bigger-120"></i></a>'+
	            			  '<a style="margin-left:3px;" class="btn btn-xs btn-danger" href="javascript:" onclick="confirmDelete('+row.id+');" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></a>' +
	            			  '</div>';
	                return dom;
	            }
            }
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

function doSearch(){
	var serverVersion = $('#serverVersion').val();
	var clientOsType = $('#clientOsType').val();
	var latestServer = $('#latestServer').val();
	var latestClient = $('#latestClient').val();
	lh.gridParam = {serverVersion:serverVersion,clientOsType:clientOsType,latestServer:latestServer,latestClient:latestClient};
	reloadMainGrid();
}

function clearSearch(){
	$('#serverVersion').val('');
	$('#clientOsType').val('');
	$('#latestServer').val('');
	$('#latestClient').val('');
	lh.gridParam = {};
	reloadMainGrid();
}

function reloadMainGrid(){
	var table = $('#datagrid').DataTable();
    table.ajax.reload();
}


function confirmDelete(id){
	lh.confirm({content:'是否确认删除该条数据？',clickYes:deleteById,clickYesParam:id});
}

function deleteById(id){
	if(!id){
		lh.alert('请指定需要删除的数据');return;
	}
	lh.post('back', lh.getCmsUrl('/admin/version/doDelete'), {id:id}, function(rsp){
		 if(rsp.success){
			reloadMainGrid();
		 }else{
			 lh.alert(rsp.msg);
		 }
	});
}


/** 加载主表格 */
function loadGrid(){
	lh.$grid.datagrid({
	    loadMsg:'',
		idField:'id',
		sortName:'id',
		sortOrder:'desc',
		striped:true,
		fit:false,
		fitColumns:false,
		singleSelect:true,
		selectOnCheck:false,
		checkOnSelect:false,
		pagination:true,
		url:lh.config.gridUrl,
	    queryParams:lh.config.queryObj,//查询参数
	    pageSize:lh.grid.pageSize,//每页数据条数
	    pageList:lh.grid.pageList,//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-125,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
			{field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){
				return  '<span class="opt_alive"><span style="cursor: pointer;color: #EC4949" onclick="openMainObjWin('+index+',\'update\')">修改</span>'
				+'&nbsp;|&nbsp;<span class="update" style="cursor: pointer;color: green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'
				+'<span class="opt_trash"><span style="cursor: pointer;color: #EC4949;" onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'
				+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
			}},
			{field:'logo',title:'LOGO',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:60px;">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'briefName',title:'简称',width:120,align:'center'},
	        {field:'wholeName',title:'全称',width:120,align:'center'},
	        {field:'address',title:'地址',width:120,align:'center'},
	        {field:'email',title:'邮箱',width:120,align:'center'},
	        {field:'phone',title:'手机号码',width:120,align:'center'},
	        {field:'tel',title:'座机号码',width:120,align:'center'},
	        {field:'qq',title:'qq',width:120,align:'center'},
	        {field:'weixin',title:'微信',width:120,align:'center'},
	        {field:'weibo',title:'微博',width:120,align:'center'},
	        {field:'bloodTest',title:'血液检测',width:120,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">不能</span>' : '能';
	        }},
	        {field:'introduction',title:'简介',width:120,align:'center'},
	        {field:'provinceName',title:'省(直辖市)',width:120,align:'center'},
	        {field:'cityName',title:'市(县)',width:120,align:'center'},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">停用</span>' : '启用';
	        }},
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }}
	    ]],
        onLoadError: function(data){
	    	lh.backDatagridErrorCheck(data);
	    },
	    onDblClickRow: function(index, row){
	    	openMainObjWin(index, 'read');
        },
	    onLoadSuccess:function(data){
	    	lh.filtGridOperation();
	    	lh.clipboard();//复制功能
	   }  
	});
}

function onClickRowOfGrid(){}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	$('#sc_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : false,
	    panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});
	$('#sc_ascOrdesc').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : false,
		panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '诊所名称升序'
		},{
			'id' : 2,
			'name' : '诊所名称降序'
		},{
			'id' : 3,
			'name' : '地区升序'
		},{
			'id' : 4,
			'name' : '地区降序'
		},{
			'id' : 5,
			'name' : '创建时间升序'
		},{
			'id' : 6,
			'name' : '创建时间降序'
		}]
	});
}

/** 初始化表单中的组件及数据 */
function initFormComponent(){
	initUploadSimple({showEdBtns:true,showItemDiv:true,multiFlag:false,multiReplace:true,
		successFun:function(fileId, filePath){
			$("#upld_container_"+fileId).remove();
			$("#pic").attr('src', filePath);
	}});
	$("#upload_outer_div").empty();
	
	$('#f_province').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		/*data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]*/
		url : "/back/getProvinceArray",
		onSelect: function(rec){
            var url = '/back/getCityArray?provinceId='+rec.id;
            $('#f_city').combobox('reload', url);
        }
	});
	
	$('#f_city').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:200
	});
	$('#f_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : true,
	    panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});
	$('#f_bloodTest').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '能'
		},{
			'id' : 2,
			'name' : '不能'
		}]
	});
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	var filePaths = $("#filePaths").val();
	if(!filePaths){
		//$.messager.alert('提示',"请上传用户头像"); return;
	}else{
		var ids = UPLOAD_OBJ.idsStr;
		if(filePaths.substring(0,1) != "/"){
			filePaths = filePaths.substring(1);
			ids = ids.substring(1);
		}
		mainObj.logo = filePaths;
		mainObj.logoPicId = ids;
	}
	return true;
}

function afterOpenWin(data, operation, isReadOnly){
	if(!data){
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#pic").attr('src', null);
		return;
	}
	$("#pic").attr('src', data.logo);
	$("#filePaths").val(data.logo);
	$("#fileDBIds").val(data.logoPicId);
	
	var province = $('#f_province').combobox('getValue');//更新市
	var city = $('#f_city').combobox('getValue');
    var url = '/back/getCityArray?provinceId='+province;
    $('#f_city').combobox('reload', url);
}

function getResetPasswordUrl(){
	return '/back/updateHospitalPassword';
}

function exportHospital(){
	var obj = lh.getQueryObj();
	delete obj.ascOrdesc;
	window.location.href = '/back/hospitalExcel?obj='+obj;
}