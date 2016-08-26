var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
lh.tabs = {};
$(function() {
	loginFrame();
	showPage();
});

function loginFrame(){
	var mainFrame = document.getElementById("mainFrame");
	var bheight = document.documentElement.clientHeight;
	mainFrame.style.width = '100%';
	mainFrame.style.height = (bheight  - 51) + 'px';
	
}
		
window.onresize=function(){  
	loginFrame();
}

function showPage(url){
	if(url){
		var fullUrl = lh.getCmsUrl(url);
		localStorage.setItem('cache_url', fullUrl);
		$('#mainFrame').attr('src', fullUrl);
		return;
	}
	if(localStorage){
		var cache_url = localStorage.getItem('cache_url');
		if(!cache_url || cache_url == '/manager/admin'){
			cache_url = lh.getCmsUrl('/admin/user/home');
		}
		$('#mainFrame').attr('src', cache_url);
	}
}

function login(){
	window.open("/");
    //window.location.href="/";
}

function refresh(){
	location.reload();
}


