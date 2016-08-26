<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
		/* try{ace.settings.check('navbar' , 'fixed')}catch(e){} */
	</script>

	<div class="navbar-container" id="navbar-container">
		<!-- #section:basics/sidebar.mobile.toggle -->
		<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>
		</button>

		<!-- /section:basics/sidebar.mobile.toggle -->
		<div class="navbar-header pull-left">
			<!-- #section:basics/navbar.layout.brand -->
			<a href="#" class="navbar-brand">
				<small>
					<i class="fa fa-globe"></i>
					零壹众管理平台
				</small>
			</a>

			<!-- /section:basics/navbar.layout.brand -->

			<!-- #section:basics/navbar.toggle -->

			<!-- /section:basics/navbar.toggle -->
		</div>

		<!-- #section:basics/navbar.dropdown -->
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
	
			<!-- #section:basics/navbar.user_menu -->
				<li class="light-blue" onclick="$('#ul_menu').toggle();">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
	<!-- 					<img class="nav-user-photo" src="<%=request.getContextPath() %>/resources/ace/assets/avatars/user.jpg" alt="Jason's Photo" />-->
						<span class="user-info">
							<small>欢迎,</small>
							${loginUser.username }
						</span>
						<i class="ace-icon fa fa-caret-down"></i>
					</a>
					<ul id="ul_menu" class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<%-- <a href="<%=request.getContextPath()%>/admin/corp/showSelf" target="mainFrame">
								<i class="ace-icon fa fa-user"></i>
								个人设置
							</a> --%>
							<a href="javascript:" target="mainFrame" onclick="showPage('/admin/corp/showSelf');">
								<i class="ace-icon fa fa-user"></i>
								个人设置
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="javascript:exitSystem()">
								<i class="ace-icon fa fa-power-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>

				<!-- /section:basics/navbar.user_menu -->
			</ul>
		</div>
		
		<div id="top_current_corp" style="text-align:center;">
			<c:if test="${isAdmin == true}">
				<span id="top_current_corp_name" style="line-height:45px;color:white;padding-right:5%">当前选择公司：${corpName}</span>
			</c:if>
		</div>

		<!-- /section:basics/navbar.dropdown -->
	</div><!-- /.navbar-container -->
</div>
<script type="text/javascript">
function exitSystem() {
	window.parent.location.href = $("#contextPath").val()+ "/admin/logout";
}
</script>
<!-- /section:basics/navbar.layout -->