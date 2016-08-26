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
					<a href="#">公司管理</a>
				</li>
				<li class="active">添加公司</li>
			</ul><!-- /.breadcrumb -->			
		</div>
		
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<sf:form method="post" modelAttribute="corp" id="addForm" cssClass="form-horizontal" role="form">
					<!-- #section:elements.form -->
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 用户名(必须是英文):  <i class="menu-icon fa fa-asterisk red"></i></label>
						<div class="col-sm-9">
							<sf:input path="username" size="30" cssClass="col-xs-10 col-sm-5" placeholder="用户名"/>
							<sf:errors cssClass="errorContainer" path="username"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 公司名称(可以是中文): <i class="menu-icon fa fa-asterisk red"></i></label>
						
						<div class="col-sm-9">
							<sf:input path="corpName" size="30" cssClass="col-xs-10 col-sm-5" placeholder="公司名称"/>
							<sf:errors cssClass="errorContainer" path="corpName"/>
						</div>
					</div>

					<!-- /section:elements.form -->
					<script type="text/javascript" src="base64.js"></script>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right red" for="form-field-2">用户密码:  <i class="menu-icon fa fa-asterisk red"></i></label>

						<div class="col-sm-9">
							<sf:input class="col-xs-10 col-sm-5" id="corpPassword" type="password" path="corpPassword" placeholder="用户密码" />
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"><sf:errors cssClass="errorContainer" path="corpPassword"/></span>
							</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right red" for="form-field-2">确认密码:  <i class="menu-icon fa fa-asterisk red"></i></label>

						<div class="col-sm-9">
							<input class="col-xs-10 col-sm-5" id="confirmCorpPwd" name="confirmCorpPwd" type="password" placeholder="确认密码" />
							<span class="help-inline col-xs-12 col-sm-7">
								<span class="middle"></span>
							</span>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 手机号码:  <i class="menu-icon fa fa-asterisk red"></i></label>
						<div class="col-sm-9">
							<sf:input path="phone" size="30" cssClass="col-xs-10 col-sm-5" placeholder="手机号码"/>
							<sf:errors cssClass="errorContainer" path="phone"/>
						</div>
					</div>
					
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right red" for="form-field-1"> 电子邮箱:  <i class="menu-icon fa fa-asterisk red"></i></label>
						<div class="col-sm-9">
							<sf:input path="email" size="30" cssClass="col-xs-10 col-sm-5" placeholder="电子邮箱"/>
							<sf:errors cssClass="errorContainer" path="email"/>
						</div>
					</div>
	
						<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right " for="form-field-1"> 最大用户数:  </label>
						<div class="col-sm-9">
							<sf:input type="number" path="maxUser" size="30" color="red" cssClass="col-xs-10 col-sm-5" placeholder="100"/>
							<sf:errors cssClass="errorContainer" path="maxUser"/>
						</div>
					</div>
							<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right " for="form-field-1"> 最大用组数:  </label>
						<div class="col-sm-9">
							<sf:input type="number" path="maxGroup" size="30" color="red" cssClass="col-xs-10 col-sm-5" placeholder="100"/>
							<sf:errors cssClass="errorContainer" path="maxGroup"/>
						</div>
					</div>				
					
												<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right " for="form-field-1"> 最大用调度台数:  </label>
						<div class="col-sm-9">
							<sf:input type="number" path="maxConsole" size="30" color="red" cssClass="col-xs-10 col-sm-5" placeholder="10"/>
							<sf:errors cssClass="errorContainer" path="maxConsole"/>
						</div>
					</div>	
						<div class="space-4"></div>

									
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label " for="form-field-1"> 权限 </label>
						
							<sf:select path="permissionLevel" tbindex="1">
								<sf:option value="0">普通</sf:option>
								<sf:option value="1">管理员</sf:option>
							</sf:select>
					</div>	
					<div class="form-group">
					<label class="col-sm-3 control-label " for="form-field-1"> 优先级 </label>
						
							<sf:select path="priorityLevel" tbindex="2">
								<sf:option value="0">普通</sf:option>
								<sf:option value="1">一级</sf:option>
							    <sf:option value="1">二级</sf:option>
							    <sf:option value="2">三级</sf:option>
							</sf:select>
						
					</div>
				


					
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