<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common_css.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/validate/main.css" />
</head>
<body class="no-skin">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="javascript:" onclick="lh.mainPageReload();">首页</a></li>
				<li><a href="javascript:" onclick="lh.cmsJump('/admin/corp/users');">公司列表</a></li>
				<li class="active">修改用户密码 --> ${corporation.username}</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<sf:form method="post" id="addForm" cssClass="form-horizontal" role="form" onsubmit="return false;">
						<!-- <!-- #section:elements.form -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="oldpwd">旧密码: </label>

							<div class="col-sm-9">
								<input class="col-xs-10 col-sm-5" id="oldpwd" type="password" name="oldpwd" placeholder="用户密码" /> 
								<span class="help-inline col-xs-12 col-sm-7"> 
									<span class="middle"><sf:errors cssClass="errorContainer" path="oldpwd" /></span>
								</span>
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="password">新密码: </label>
							<div class="col-sm-9">
								<input class="col-xs-10 col-sm-5" id="password" type="password" name="password" placeholder="新密码" /> 
									<span class="help-inline col-xs-12 col-sm-7"> 
										<span class="middle"><sf:errors cssClass="errorContainer" path="password" /></span>
								</span>
							</div>
						</div>

						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="confirmPwd">确认密码: </label>
							<div class="col-sm-9">
								<input class="col-xs-10 col-sm-5" id="confirmPwd" name="confirmPwd" type="password" placeholder="确认密码" /> 
									<span class="help-inline col-xs-12 col-sm-7"> <span class="middle"></span>
								</span>
							</div>
						</div>
						<div class="space-4"></div>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" onclick="updatePassword(${corporation.id});">
									<i class="ace-icon fa fa-check bigger-110"></i> 提交
								</button>
								<button class="btn" type="reset" style="margin-left:40px;" onclick="lh.back();">
									<i class="ace-icon fa fa-undo bigger-110"></i> 返回
								</button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
			<!-- /.row -->
		</div>
	</div>
	<%@ include file="../common/common_js.jsp"%>
	<!-- <script type="text/javascript"> lh.param = ${paramJson} </script> -->
	<script src="<%=request.getContextPath()%>/resources/lh/js/corp/pwdUpdate.js" title="v"></script>

</body>
</html>