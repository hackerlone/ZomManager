/** 日期：2016年2月21日20:16:18 作者：虞荣华 描述：蓝海飞鱼基本库(对象化) */

/** 唯一全局对象 */
lh = lh || {};

lh.grid.frontSize = 20;

/** 复制 **/
lh.initFrontClipboard = function (){
	var clipboard = new Clipboard('.copy_btn');
	clipboard.on('success', function(e) {
		frontBaseAlert('已成功复制到粘贴版');
	});
}
lh.initFrontClipboard();

/** 返回页面顶部 */
lh.goTop = function () {
    var _top = $(window).scrollTop();
    if (_top > 300) {
        $("#pf").stop().animate({
            "top": (($(window).height() - $("#pf").height()) / 2) + $(window).scrollTop()
        }, 1000);
    } else {
        $("#pf").stop().animate({
            "top": "285px"
        }, 1000);
    }
}

/** 防止重复 */
lh.showRepeatTip = function (tip){
	if(!tip)tip = '请不要重复提交数据';
	lh.alert(tip);
}

/** 注销 */
lh.logout = function (){
	$.post('/logout',null,function(rsp){
	     if(rsp.status == 'success'){     	
	     	window.location.href = "/";
	     }else{
	     	alert('提示',rsp.msg);
	     }
	},'json');
	
}

/** 页面跳转URL地址，携带r参数 */
lh.jumpR = function (url){
	if(!url)return;
	var r = locache.get("r");
	if(r){
		var symbol = "?";
		if(url.indexOf('?') > 0)symbol = "&";
		url += symbol + "r=" + r;
	}
	lh.jump(url);
}

/** 前台页面登陆检查 */
lh.frontLoginCheck = function (){
	var loginStatus = $('#loginStatus').val();
	if(loginStatus == 'doLogin'){
		lh.jumpR('/login');
	}
	
}

/** ajax发起请求前执行 */
lh.ajaxBefore = function (){
	$('#resultTip').hide();
	$('#loadingTip').show();
}

/** ajax请求完成后执行 */
lh.ajaxAfter = function (){
	$('#loadingTip').hide();
}

/** ajax请求返回数据为空 */
lh.ajaxDataEmpty = function (){
	$('#resultTip').text('没有更多数据');
	$('#resultTip').show();
}