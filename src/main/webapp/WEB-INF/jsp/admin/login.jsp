<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../common/common_login.jsp"></jsp:include> --%>
<jsp:include page="../lh/common/common_back_css.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/lh/third-party/reset/reset.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/lh/css/login.css" title="v" />
<style type="text/css">
.cd-close-form {
  /* form X button on top right */
  display: block;
  position: absolute;
  width: 40px;
  height: 40px;
  right: 0;
  top: -40px;
  background: url("<%=request.getContextPath()%>/resources/lh/images/back/login/cd-icon-close.svg") no-repeat center center;
  text-indent: 100%;
  white-space: nowrap;
  overflow: hidden;
}
.cd-form label.cd-username {
  background-image: url("<%=request.getContextPath()%>/resources/lh/images/login/cd-icon-username.svg");
}
.cd-form label.cd-email {
  background-image: url("<%=request.getContextPath()%>/resources/lh/images/login/cd-icon-email.svg");
}
.cd-form label.cd-password {
  background-image: url("<%=request.getContextPath()%>/resources/lh/images/login/cd-icon-password.svg");
}
</style>
</head>
<body>
	<header role="banner">
		<!-- <div id="cd-logo"><a href="#0"><img src="/images/back/login/cd-logo.svg" alt="Logo"></a></div> -->
		<div id="cd-logo" style="margin: 5px 50px;">
			<a href="#0">
				<%-- <img src="<%=request.getContextPath()%>/resources/lh/images/login/cd-logo.svg" height="72px" alt="Logo"> --%>
			</a>
		</div>
		<nav class="main-nav">
			<ul>
				<!-- inser more links here -->
				<!-- <li><a class="cd-signin" href="#0">登 陆</a></li> -->
			</ul>
		</nav>
	</header>
	
	<div style="position:absolute;top:25px;width:100%;text-align:center;">
			<h1 style="color:white;font-size:23px;">
				<span class="blue" style="color:#1B9AF7">零壹众</span> <span class="black" id="id-text2">集群管理台</span>
			</h1>
		</div>

	<div class="cd-user-modal is-visible" style="background: none;">
		<!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container" style="background: #EAE9E9; top: 10%;">
			<button style="margin-left: 230px;" onclick="quickLogin();return false;" class="button button-primary button-rounded button-small">快捷登陆</button>
			<div id="cd-login" class="is-selected" style="background: none;">
				<!-- log in form -->
				<form class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signin-username">账号</label> <input class="full-width has-padding has-border" id="signin-username" type="text" placeholder="账号"> <span class="cd-error-message">请输入您的账号</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">密码</label> <input class="full-width has-padding has-border" id="signin-password" type="password" placeholder="密码"> <span
							class="cd-error-message">请输入您的密码</span>
						<!-- <a href="#0" class="hide-password">隐藏</a> -->
					</p>

					<p class="fieldset" style="position: relative; top: -19px;">
						<label class="image-replace cd-close" for="signin-verificationCode">验证码</label> 
						<input class="full-width has-padding has-border" id="signin-verificationCode" type="text" placeholder="验证码" style="min-width: 200px; width: 55%"> 
						<span class="cd-error-message">请输入验证码</span> 
						<a href='javascript:loadRandomCode();'> 
							<img src='drawCheckCode' id='imgcode' style="width:160px;height: 50px; position: relative; top: 19px;" />
						</a>
						<a href="javascript:loadRandomCode();">重新加载</a>
					</p>

					<p class="fieldset" style="position: relative; top: -19px;">
						<input type="checkbox" id="remember" checked /> <label for="remember-me">记住我</label>
					</p>

					<p class="fieldset" style="position: relative; top: -19px;">
						<input id="login_submit" class="full-width" type="submit" value="登 陆" />
					</p>
				</form>

				<p class="cd-form-bottom-message" onclick="forgetPassword();">
					<a style="cursor: pointer; color: #2f889a;">忘记密码?</a>
				</p>
			</div>
			<!-- cd-login -->

		</div>
		<!-- cd-user-modal-container -->
	</div>
	<footer class="login_foot" style="">&copy; 零壹众科技</footer>
	<jsp:include page="../common/common_js.jsp"></jsp:include>
	<script type="text/javascript"> lh.param = ${paramJson} </script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/third-party/compatible/modernizr.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lh/js/login/main.js" title="v"></script>
</body>
</html>
