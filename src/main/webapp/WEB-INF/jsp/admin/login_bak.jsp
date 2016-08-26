<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<jsp:include page="../common/common_login.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/login.css" />
</head>

<body class="login-layout">
	<script type="text/javascript">
    if(window.self != window.top) { window.top.location = window.self.location; }
  //刷新上层页面
    //window.parent.main.document.location.reload();
  
//    window.opener.document.location.reload();
   // window.opener.document.location.reload();
   // self.location.reload();
</script>
	<div class="main-container" style="margin: 0 auto; width: 100%; height: 100%;">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">

						<div class="center">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i> <span class="blue">零壹众</span> <span class="black" id="id-text2">集群管理台</span>
							</h1>
							<h4 class="blue" id="id-company-text">&copy; 零壹众科技</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<form method="post" id="myForm" action="">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<!--  <i class="ace-icon fa fa-coffee green"></i> -->
												请输入您的账号信息
											</h4>

											<div class="space-6"></div>

											<form>
												<fieldset>
													<label class="block clearfix"> <span class="block input-icon input-icon-right"> <input type="text" class="form-control" placeholder="用户名" name="username" value="" /> <i
															class="ace-icon fa fa-user"></i>
													</span>
													</label> <label class="block clearfix"> <span class="block input-icon input-icon-right"> <input type="password" class="form-control" placeholder="密码" name="password" value="" /> <i
															class="ace-icon fa fa-lock"></i>
													</span>
													</label> <label class="block clearfix"> <span class="block input-icon input-icon-right"> <input type="text" class="form-control" placeholder="验证码" name="checkcode" /> <span style="cursor: pointer">
																<img src="drawCheckCode" onclick="reCheckcode(this)" />
														</span>
													</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline"> 
															<input type="checkbox" class="ace" id="rememberMe" /> 
															<span class="lbl"> 记住我</span>
														</label>

														<button type="submit" class="width-28 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i> <span class="bigger-110">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>


											<div class="space-6"></div>


										</div>
										<!-- /.widget-main -->


										<div class="toolbar clearfix">
											<div>
												<a> </a>
											</div>
											<div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link-1"> 忘记密码 </a>
											</div>
											<!-- 
											<div>
												<a href="#" data-target="#signup-box" class="user-signup-link">
													我要去注册
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
-->
										</div>
									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.login-box -->
								</from>
								<!-- 
	
							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
																		<div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													
													我忘记密码
												</a>
											</div>
								<a id="btn-login-dark" href="#">忘记密码</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
					
						</div>
						-->
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i> Retrieve Password
											</h4>

											<div class="space-6"></div>
											<p>Enter your email and to receive instructions</p>

											<form>
												<fieldset>
													<label class="block clearfix"> <span class="block input-icon input-icon-right"> <input type="email" class="form-control" placeholder="Email" /> <i class="ace-icon fa fa-envelope"></i>
													</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon fa fa-lightbulb-o"></i> <span class="bigger-110">Send Me!</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div>
										<!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link"> Back to login <i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.forgot-box -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.main-content -->
			</div>
			<!-- /.main-container -->

			<!-- basic scripts -->

			<!--[if !IE]> -->
			<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=request.getContextPath() %>/resources/ace/assets/js/jquery.js'>"+"<"+"/script>");
		</script>

			<!-- <![endif]-->

			<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=request.getContextPath() %>/resources/ace/assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
			<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath() %>
				/resources/assets/js/jquery.mobile.custom.js'>"
									+ "<"+"/script>");
			</script>

			<!-- inline scripts related to this page -->
			<script type="text/javascript">
				jQuery(function($) {
					$(document)
							.on(
									'click',
									'.toolbar a[data-target]',
									function(e) {
										e.preventDefault();
										var target = $(this).data('target');
										$('.widget-box.visible').removeClass(
												'visible');//hide others
										$(target).addClass('visible');//show target
									});
				});
			</script>

			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/core/jquery.cms.validate.js"></script>


			<script type="text/javascript">
				$(function() {
					$("#myForm").cmsvalidate();
				});
			</script>
			<title>后台管理登录</title>
			<script type="text/javascript">
				function reCheckcode(img) {
					img.src = "drawCheckCode?" + Math.random();
				}
			</script>
</body>
</html>
