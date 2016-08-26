<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<%@ include file="../common/common_css.jsp"%>
</head>
<body class="no-skin">

	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">首页</a>
				</li>
				<li class="active">平台管理</li>
			</ul><!-- /.breadcrumb -->

			<!-- #section:basics/content.searchbox -->
			<!-- 
			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="ace-icon fa fa-search nav-search-icon"></i>
					</span>
				</form>
			</div>
            -->
            
			<!-- /section:basics/content.searchbox -->
		</div>

		<!-- /section:basics/content.breadcrumbs -->
		<div class="page-content">
			<!-- /section:settings.box -->
			<div class="page-header">
				<h1>
					欢迎使用零壹众集群管理平台
				</h1>
			</div><!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->


							<div class="row">
								<div class="space-6"></div>

								<div class="col-sm-9 infobox-container">
									<!-- #section:pages/dashboard.infobox -->
							<div style = "width: 280px" class="infobox infobox-green">
								<div class="infobox-icon">
									<i class="ace-icon fa fa-user"></i>
								</div>

								<div class="infobox-data">
									<span  style="float:left" class="infobox-data-number">${user.maxUser}</span>
							        
							        <span style = "padding-left:110px" class="infobox-data-number">${uCount}</span>
									<div style="float:left" class="infobox-content">总用户</div>
									<div style="float:left;padding-left:50px"  class="infobox-content">已注册用户</div>	
								</div>

							</div>

							<div style = "width: 280px" class="infobox infobox-blue">
								<div class="infobox-icon">
									<i class="ace-icon fa fa-group"></i>
								</div>

								<div class="infobox-data">
									<span  style="float:left" class="infobox-data-number">${user.maxGroup}</span>
							        
							        <span style = "padding-left:110px" class="infobox-data-number">${gCount}</span>
									<div style="float:left" class="infobox-content">总用户组</div>
									<div style="float:left;padding-left:50px"  class="infobox-content">已注册用户组</div>	
								</div>

							</div>
							<div style = "width: 280px" class="infobox infobox-red">
								<div class="infobox-icon">
									<i class="ace-icon fa fa-desktop"></i>
								</div>

								<div class="infobox-data">
									<span  style="float:left" class="infobox-data-number">${user.maxConsole}</span>
									
									<span style="padding-left:110px"   class="infobox-data-number">${cCount}</span>
									<div  style="float:left"  class="infobox-content">总调度台</div>
									<div  style="float:left;padding-left:50px"  class="infobox-content">已注册调度台</div>
								</div>
								
							</div>
							<div style = "width: 280px" class="infobox infobox-pink">
								<div class="infobox-icon">
									<i class="ace-icon fa fa-group"></i>
								</div>

								<div class="infobox-data">
									<span style="float:left" class="infobox-data-number">11</span>
									<span style="padding-left:110px" class="infobox-data-number">11</span>
									<div style="float:left" class="infobox-content">总调度台组</div>
									<div  style="float:left;padding-left:45px"  class="infobox-content">已注册调度台组</div>
								</div>

						</div>
			
					<!-- PAGE CONTENT ENDS -->
				
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div>
	</div>
	</div>
	</div>
	
	
<!-- /.main-content -->
<%@ include file="../common/common_js.jsp"%>
</body>
</html>
