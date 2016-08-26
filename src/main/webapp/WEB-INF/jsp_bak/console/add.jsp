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
					<a href="#">调度台管理</a>
				</li>
				<li class="active">添加调度台</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="userDto" id="addForm" cssClass="form-horizontal" role="form">
					<!-- #section:elements.form -->
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 登录名(必须是英文): </label>
						<div class="col-sm-9">
							<sf:input path="username" size="30" cssClass="col-xs-10 col-sm-5" placeholder="用户名"/>
							<sf:errors cssClass="errorContainer" path="username"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 显示名称(可以是中文): </label>
						<div class="col-sm-9">
							<sf:input path="nickname" size="30" cssClass="col-xs-10 col-sm-5" placeholder="显示名称"/>
							<sf:errors cssClass="errorContainer" path="nickname"/>
						</div>
					</div>

					<!-- /section:elements.form -->
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">用户密码: </label>

						<div class="col-sm-9">
							<sf:input class="col-xs-10 col-sm-5" id="password" type="password" path="password" placeholder="用户密码" />
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"><sf:errors cssClass="errorContainer" path="password"/></span>
							</span>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-2">确认密码: </label>

						<div class="col-sm-9">
							<input class="col-xs-10 col-sm-5" id="confirmPwd" name="confirmPwd" type="password" placeholder="确认密码" />
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"></span>
							</span>
						</div>
					</div>
					
				
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 手机: </label>
						<div class="col-sm-9">
							<sf:input path="phone" size="30" cssClass="col-xs-10 col-sm-5" placeholder="手机号码"/>
							<sf:errors cssClass="errorContainer" path="phone"/>
						</div>
					</div>
				
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">调度台优先级（11-30，30最高）: </label>
						<div class="col-sm-9">
							<sf:input path="priority" size="30" cssClass="col-xs-10 col-sm-5"   value="11"/>
							<sf:errors cssClass="errorContainer" path="priority"/>

						</div>

					</div>
			<c:choose>
		<c:when test="${isRank1 }">
	
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 管理我的二阶管理台: </label>
						<div class="col-sm-9">
							<sf:checkboxes items="${consoles}" var="user" path="consoleIds" itemLabel="displayName" itemValue="id" cssStyle="padding-top:10px;padding-left:15px;"/>
						</div>
					</div>
					
					
				<div class="space-4"></div>
				 
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 管理的人员: </label>
						<div class="col-sm-9">
							<sf:checkboxes items="${users}" var="user" path="userIds" itemLabel="displayName" itemValue="id" cssStyle="padding-top:10px;padding-left:15px;"/>
						</div>
					</div>
		</c:when>
				<c:otherwise >
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 被管理的一阶管理台: </label>
						<div class="col-sm-9">
							<sf:checkboxes items="${consoles}" var="user" path="userIds" itemLabel="displayName" itemValue="id" cssStyle="padding-top:10px;padding-left:15px;"/>
						</div>
					</div>
					
					
				</c:otherwise>
			</c:choose>

					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i>
								提交
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="reset">
								<i class="ace-icon fa fa-undo bigger-110"></i>
								重置
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