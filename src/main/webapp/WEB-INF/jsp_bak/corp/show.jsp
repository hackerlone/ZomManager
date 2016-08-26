<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/ace/assets/css/jquery-ui.custom.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/ace/assets/css/jquery.gritter.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/ace/assets/css/select2.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/ace/assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/ace/assets/css/bootstrap-editable.css" />
</head>
<body class="no-skin">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">用户管理</a>
				</li>
				<li class="active">个人设置</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<div class="hr dotted"></div>
					<div>
						<div class="user-profile row" id="user-profile-1">
							<div class="col-xs-12 col-sm-3 center">
								<div class="space-6"></div>

　<!-- 
								<div class="profile-contact-info">
									<div class="profile-contact-links align-left">
										<a class="btn btn-link" href="#">
											<i class="ace-icon fa fa-envelope bigger-120 pink"></i>
											发送消息
										</a>
										
										<c:if test="${isAdmin}">
											<a href="update/${corporation.id }" class="btn btn-link">
												<i class="ace-icon fa fa-users bigger-120 pink"></i>									
												修改ssss
											</a>
										</c:if>
										<c:if test="${not isAdmin}">
											<a href="<%=request.getContextPath()%>/admin/corp/updateSelf" class="btn btn-link">
												<i class="ace-icon fa fa-users bigger-120 pink"></i>
												修改个人信息
											</a>
										</c:if>
										
										<a class="btn btn-link" href="<%=request.getContextPath()%>/admin/corp/updatePwd">
											<i class="ace-icon fa 	fa-lock bigger-120 pink"></i>
											修改密码
										</a>
									</div>
								</div>
-->

							</div>

							<div class="col-md-12 col-sm-9">

								<!-- #section:pages/profile.info -->
								<div class="profile-user-info profile-user-info-striped">


									<div class="profile-info-row">
										<div class="profile-info-name"> 公司名字 </div>

										<div class="profile-info-value">
												<span class="editable editable-click" id="corpName" style="display: inline;">${corporation.corpName }</span>
										</div>
									</div>

									<div class="profile-info-row">
										<div class="profile-info-name"> 注册时间 </div>

										<div class="profile-info-value">
											<span class="editable editable-click" id="age" style="display: inline;">${corporation.registerDate }</span>
										</div>
									</div>
									<div class="profile-info-row">
										<div class="profile-info-name"> 服务过期时间 </div>

										<div class="profile-info-value">
											<span class="editable editable-click" id="age" style="display: inline;">${corporation.expireDate }</span>
										</div>
									</div>
									<div class="profile-info-row">
										<div class="profile-info-name"> 联系电话 </div>

										<div class="profile-info-value">
											<span class="editable editable-click" id="signup" style="display: inline;">${corporation.phone}</span>
										</div>
									</div>

									<div class="profile-info-row">
										<div class="profile-info-name"> 电子邮件 </div>

										<div class="profile-info-value">
											<span class="editable editable-click" id="login" style="display: inline;">${corporation.email }</span>
										</div>
									</div>

					<div class="space-6"></div>
						

									<div class="profile-info-value">
										<span class="editable editable-click" style="display: inline;">
											<a class="btn btn-primary no-radius" href="update/${corporation.id }">
												<i class="ace-icon fa fa-pencil-square-o"></i>
												修改用户信息
											</a>
										</span>
										<span class="editable editable-click" style="display: inline;">
											<a class="btn btn-primary no-radius" href="<%=request.getContextPath()%>/admin/corp/updatePwd">
												<i class="ace-icon fa 	fa-lock bigger-120 pink"></i>
												修改密码
											</a>
								
										</span>
									</div>
								
								</div>

										
								<!-- /section:pages/profile.info -->
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div>
	</div>



</body>
</html>