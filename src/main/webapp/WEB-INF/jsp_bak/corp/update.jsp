<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
					<a href="#">用户管理</a>
				</li>
				<li class="active">修改用户信息</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="corporation" id="addForm" cssClass="form-horizontal" role="form">
					<!-- #section:elements.form -->


					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 公司名称: </label>
						<div class="col-sm-9">
							<sf:input path="corpName" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="corpName"/>
						</div>
					</div>

					<!-- /section:elements.form -->
					<%-- <div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">用户密码: </label>

						<div class="col-sm-9">
							<sf:input class="col-xs-10 col-sm-5" id="password" type="password" placeholder="Password" path="password"/>
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"><sf:errors cssClass="errorContainer" path="password"/></span>
							</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">确认密码: </label>

						<div class="col-sm-9">
							<input class="col-xs-10 col-sm-5" id="confirmPwd" name="confirmPwd" type="password"/>
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"></span>
							</span>
						</div>
					</div>  --%>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 手机: </label>
						<div class="col-sm-9">
							<sf:input path="phone" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="phone"/>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电子邮箱: </label>
						<div class="col-sm-9">
							<sf:input path="email" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="email"/>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 最大用户数: </label>
						<div class="col-sm-9">
							<sf:input path="maxUser" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="maxUser"/>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 最大组数: </label>
						<div class="col-sm-9">
							<sf:input path="maxGroup" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="maxGroup"/>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 最大调度台数: </label>
						<div class="col-sm-9">
							<sf:input path="maxConsole" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="maxConsole"/>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 最大组成员数: </label>
						<div class="col-sm-9">
							<sf:input path="maxUserGroup" size="30" cssClass="col-xs-10 col-sm-5"/>
							<sf:errors cssClass="errorContainer" path="maxUserGroup"/>
						</div>
					</div>
					

	&nbsp; &nbsp; &nbsp;

					<div class="space-4"></div>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i>
								Submit
							</button>

							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="reset">
								<i class="ace-icon fa fa-undo bigger-110"></i>
								Reset
							</button>
						</div>
					</div>
				</sf:form>
				</div>
			</div><!-- /.row -->
		</div>
	</div>
<%@ include file="../common/common_js.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>

</body>
</html>