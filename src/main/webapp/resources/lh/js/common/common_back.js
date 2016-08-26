/** 日期：2015年7月21日15:12:34 作者：虞荣华 描述：后台通用辅助JS（easyui版） */

lh = lh || {};
$(function() {
	//isBackLogin();
});

/** datagrid表格相关数据 */
lh.grid = {
	pageSize : 50,
	pageList : [ 10, 20, 30, 50, 80, 100, 200 ]
};

lh.config = {};

/** 页面跳转easyui-tab */
lh.jumpToTab = function () {

}

/** 页面新打开easyui-tab */
lh.openNewTab = function (url) {

}

lh.subShowPage = function (url){
	if(url){
		var fullUrl = lh.getCmsUrl(url);
		localStorage.setItem('cache_url', fullUrl);
		location.href = fullUrl;
		return;
	}
}

lh.initConfig = function (config){
	if(!config || !config.mainObjLowerName || !config.mainObjUpperName){
		$.messager.alert('提示', '基本设置参数对象不能为空');return;
	}
	var upper = config.mainObjUpperName;
	var lower = config.mainObjLowerName;
	lh.config = {
		mainObj : config.mainObj || {},
		mainObjUpperName : upper,
		mainObjLowerName : lower,
		gridUrl : config.gridUrl || '/back/get'+upper+'List',
		addOrUpdateUrl : config.addOrUpdateUrl || '/back/addOrUpdate'+upper,
		batchDeleteUrl : config.batchDeleteUrl || '/back/update'+upper+'Delete',
		batchThoroughDeleteUrl : config.batchThoroughDeleteUrl || '/back/delete'+upper+'Thorough',
		batchRecoverUrl : config.batchRecoverUrl || '/back/update'+upper+'Recover',
		queryObj : config.queryObj || null,
		isSearchByParamJson : config.isSearchByParamJson || false,
		gridDomSelector : config.gridDomSelector || '#datagrid',
		gridDivDomSelector : config.gridDivDomSelector || '#datagrid_div',
		mainObjForm : config.mainObjForm || '#mainObjForm',
		mainObjWin : config.mainObjWin || '#mainObjWin',
		mainObjTip : config.mainObjTip || '#mainObjTip',
		mainObjSave : config.mainObjSave || '#mainObjSave',
		gridSwitchShowField : config.gridSwitchShowField || 'deletedAt,deletedBy',
		mainObjReadOnlyTr : config.mainObjReadOnlyTr || '#mainObjTable .readOnlyTr',
		mainObjDomSelector : config.mainObjDomSelector || '#mainObjTable .domain-input',
		mainQueryDomSelector : config.mainQueryDomSelector || '#mainQueryTable .domain-input'
	}
	
	lh.$grid = $(lh.config.gridDomSelector);
	lh.$mainObj = $(lh.config.mainObjDomSelector);
	lh.$mainQuery = $(lh.config.mainQueryDomSelector);
	lh.$mainObjForm = $(lh.config.mainObjForm);
	lh.$mainObjWin = $(lh.config.mainObjWin);
	lh.$mainObjTip = $(lh.config.mainObjTip);
	lh.$mainObjSave = $(lh.config.mainObjSave);
	lh.$mainObjReadOnlyTr = $(lh.config.mainObjReadOnlyTr);
}

lh.showRepeatTip = function (tip){
	if(!tip)tip = '请不要重复提交数据';
	$.messager.alert('提示', tip);
}

lh.setDomIsReadOnly = function (param){
	var inputsDom = lh.getInputsDom(param);
	if(!param || !param.isReadOnly){
		param = {isReadOnly:true};
	}
	inputsDom.textbox('readonly',param.isReadOnly);
}

/**
 * 加载下拉列表的数据并缓存，方便复用
 * param:{url:'',paramObj:{},cacheName:'',domId:'#xx'}（url,name是必须项）
 */
lh.loadComboDataToCache = function(param){
	if(!param || !param.url || !param.cacheName)return;
	if(!param.paramObj)param.paramObj = null;
	var cacheData = lh.getDataFromCache(param.cacheName);//先从缓存中读取数据，存在即返回，不存在则选择加载
	if(cacheData){
		if(param.domId){
			$(param.domId).combobox('loadData', _.cloneDeep(rsp));
		}
		return cacheData;
	}
	
	lh.post('back', param.url, param.paramObj, function(rsp){
		lh.cache[param.cacheName] = rsp;
		if(param.domId){
			$(param.domId).combobox('loadData', _.cloneDeep(rsp));
		}
	});
}


/** ------------------------------------------------- */

/** iframe子页面调用父页面的 显示子页面 方法(easyui tabs) */
lh.subShowMain = function (name, path) {
	window.parent.showMain(name, path);
}

/** 后台登陆检查 */
lh.isBackLogin = function isBackLogin() {
	$.ajaxSetup({
		error : function(data, textStatus) {
			if (data && data.responseText == 'toBackLogin') {
				window.parent.location.href = "/back/login";
			}
		}
	});
}

lh.formatGridDate = function (value){
	return !value ? '' : lh.formatDate({date : new Date(value)})
}

lh.formatGridDateTime = function (value){
	return !value ? '' : lh.formatDate({flag:'datetime', date : new Date(value)})
}

/** 复制 * */
lh.clipboard = function () {
	var clipboard = new Clipboard('.copy_btn');
	clipboard.on('success', function(e) {
		console.info('Action:', e.action);
		console.info('Text:', e.text);
		console.info('Trigger:', e.trigger);
		$.messager.show({
			title : '提示',
			msg : '已成功复制：' + e.text,
			timeout : 2000,
			showType : 'slide'
		});
	});
}


/**
 * 描述：公用JS
 * 日期：2013年12月30日9:50:22
 */
LOADEDGRID = [];
/**
 * 判断用户是否登陆
 */
function isLogin(){
	$.ajaxSetup({
		error:function(data, textStatus) {
			if(data&&data.responseText=='toLogin'){
				window.parent.location.href="/index";
			}
		}
	});
}

/**
 * 描述：将简写的Json转换为Tree的标准Json格式
 * 作者：虞荣华
 * 日期：2013年12月30日9:50:22
 * @param rows:简写的json数据
 * @return 标准的json数据
 */

lh.convertTreeJsonData = function (rows) {
	//var rows = rows.data;//data才是实际数据 -- 暂时注释掉
    function exists(rows, parentId){
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) return true;
        }
        return false;
    }
    
    var nodes = [];
    // 得到根节点们
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
        	var attr = {url:row.url,parentId:row.parentId,isBlnToRole:row.isBlnToRole};//添加自定义属性
            var root = {id:row.id,text:row.name,checked:row.checked,iconCls:row.iconCls,attr:attr};
            nodes.push(root);
        }
    }
    
    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while(toDo.length){
        var node = toDo.shift();    // 父节点
        // 得到子节点们
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (row.parentId == node.id){
            	var attr = {url:row.url,parentId:row.parentId,isBlnToRole:row.isBlnToRole};//添加自定义属性
                var child = {id:row.id,text:row.name,checked:row.checked,iconCls:row.iconCls,attr:attr};
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}

/**
 * 描述：将简写的Json转换为Treegrid的标准Json格式
 * 作者：王玉川
 * 日期：2014年1月27日
 * @param rows:简写的json数据
 * @return 标准的json数据
 */
lh.convertTreeGridJsonData = function (rows) {
	var rows = rows.data;//data才是实际数据 -- 暂时注释掉
    function exists(rows, parentId){
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) return true;
        }
        return false;
    }
    
    var nodes = [];
    // 得到根节点们
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
        	var attr = {url:row.url,parentId:row.parentId,isBlnToRole:row.isBlnToRole};//添加自定义属性
            var root = {id:row.id,text:row.deptName,higherDept:row.higherDept,deptNote:row.deptNote,orderStatus:row.orderStatus,
            		businessId:row.businessId,business:row.business,deptTypeId:row.deptTypeId,
            		deptType:row.deptType,foundId:row.foundId,founder:row.founder,foundTime:row.foundTime,
            		editId:row.editId,editor:row.editor,editTime:row.editTime,remark:row.remark,attr:attr};
            nodes.push(root);
        }
    }
    
    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while(toDo.length){
        var node = toDo.shift();    // 父节点
        // 得到子节点们
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (row.parentId == node.id){
            	var attr = {url:row.url,parentId:row.parentId,isBlnToRole:row.isBlnToRole};//添加自定义属性
                var child = {id:row.id,text:row.deptName,higherDept:row.higherDept,deptNote:row.deptNote,orderStatus:row.orderStatus,
                		businessId:row.businessId,business:row.business,deptTypeId:row.deptTypeId,
                		deptType:row.deptType,foundId:row.foundId,founder:row.founder,foundTime:row.foundTime,
                		editId:row.editId,editor:row.editor,editTime:row.editTime,remark:row.remark,attr:attr};
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}

/**验证数字
 * inputDomId:需要验证的输入框的ID
 * */
lh.validateNum = function (inputDomId) {
	if(inputDomId && (typeof inputDomId =="string")){
		var value = $('#'+inputDomId).val();
		var flag = /^[0-9]+\.?[0-9]{0,9}$/i.test(value);
		if(!flag){
			$('#'+inputDomId).val('');
		}
	}
}

/**验证数字
 * 只能输入正整数 
 * inputDomId:需要验证的输入框的ID
 * */

lh.validateNums = function (inputDomId) {
	if(inputDomId && (typeof inputDomId =="string")){
		var value = $('#'+inputDomId).val();
		var flag = /^[+]?[1-9]+\d*$/i.test(value);
		if(!flag){
			$('#'+inputDomId).val('');
		}
	}
}


